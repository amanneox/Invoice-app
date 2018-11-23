package com.paperless.app.controller;

import com.paperless.app.exception.ResourceNotFoundException;
import com.paperless.app.model.UserInvoice;
import com.paperless.app.repository.InvoiceRepo;
import com.paperless.app.repository.UserInvoiceRepo;
import com.paperless.app.service.DocumentService;
import com.paperless.app.service.S3Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserInvoiceController {

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
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id " + invoiceId));
    }
    @PostMapping("/user/invoice/{invoiceId}")
    public UserInvoice createUserInvoice(@Valid @PathVariable Long invoiceId, @RequestBody UserInvoice invoice) {

        return invoiceRepo.findById(invoiceId).map(data -> {

            PDDocument doc = documentService.createPDF(data,invoice);
            String url = s3Service.uploadFile(doc);
            invoice.setInvoice_url(url);
            invoice.setData(data);
            return userInvoiceRepo.save(invoice);
        }).orElseThrow(() -> new ResourceNotFoundException("InvoiceId " + invoiceId + " not found"));
    }
}
