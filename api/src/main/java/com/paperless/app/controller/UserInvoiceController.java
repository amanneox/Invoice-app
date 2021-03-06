package com.paperless.app.controller;

import com.paperless.app.exception.ResourceNotFoundException;
import com.paperless.app.model.AmazonAttachment;
import com.paperless.app.model.AmazonEmail;
import com.paperless.app.model.SESFrom;
import com.paperless.app.model.UserInvoice;
import com.paperless.app.repository.InvoiceRepo;
import com.paperless.app.repository.UserInvoiceRepo;
import com.paperless.app.service.DocumentService;
import com.paperless.app.service.S3Service;
import com.paperless.app.service.SESProcessor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserInvoiceController {

    @Autowired
    protected SESProcessor sesProcessor;

    @Autowired
    protected S3Service s3Service;
    @Autowired
    protected DocumentService documentService;
    @Autowired
    protected UserInvoiceRepo userInvoiceRepo;
    @Autowired
    protected InvoiceRepo invoiceRepo;
    @GetMapping("/user/invoice")
    public Page<UserInvoice> getUserInvoice(Pageable pageable) {
        return userInvoiceRepo.findAll(pageable);
    }

    @GetMapping("/user/invoice/{invoiceId}")
    public Page<UserInvoice> getUserInvoiceById(@PathVariable (value = "invoiceId") Long invoiceId,Pageable pageable) {
        return userInvoiceRepo.findById(invoiceId,pageable);
    }

    @PostMapping("/user/invoice/delete/{invoiceId}")
    public ResponseEntity<?> deleteUserInvoice(@PathVariable Long invoiceId) {
        return userInvoiceRepo.findById(invoiceId)
                .map(invoice -> {
                    userInvoiceRepo.delete(invoice);
                    return ResponseEntity.ok().body("OK");
                }).orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id " + invoiceId));
    }
    @PostMapping("/user/invoice/{invoiceId}")
    public UserInvoice createUserInvoice(@Valid @PathVariable Long invoiceId, @RequestBody UserInvoice invoice) {

        return invoiceRepo.findById(invoiceId).map(data -> {

            PDDocument doc = documentService.createPDF(data,invoice);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                doc.save(byteArrayOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            String url = s3Service.uploadFile(doc);
            invoice.setInvoice_url(url);
            invoice.setData(data);


            if (invoice.getEmail()!=null){
                AmazonAttachment file = new AmazonAttachment("Invoice",byteArrayOutputStream.toByteArray(),"application/pdf");

                SESProcessor.getInstance().add(new AmazonEmail(
                        invoice.getEmail(),
                        SESFrom.ATTA,
                        data.getCname()+" Invoice",
                        "Your Invoice Is ready to download",
                        false,
                        file
                ));
            }




            /*
            SESProcessor.getInstance().add(new AmazonEmail(
                    "amanadhikari2@gmail.com",
                    SESFrom.NO_REPLY,
                    "Hey Aman",
                    "Invoice Created :)"
            ));
            */
            return userInvoiceRepo.save(invoice);
        }).orElseThrow(() -> new ResourceNotFoundException("InvoiceId " + invoiceId + " not found"));
    }
}
