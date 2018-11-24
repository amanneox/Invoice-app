package com.paperless.app.controller;

import com.paperless.app.exception.ResourceNotFoundException;
import com.paperless.app.model.InvoiceModel;
import com.paperless.app.repository.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InvoiceController {
    @Autowired
    protected InvoiceRepo invoiceRepo;

    @GetMapping("/invoice")
    public Page<InvoiceModel> getInvoice(Pageable pageable) {
        return invoiceRepo.findAll(pageable);
    }

    @GetMapping("/invoice/{invoiceId}")
    public Optional<InvoiceModel> getInvoiceById(@PathVariable Long invoiceId) {
        return invoiceRepo.findById(invoiceId);

    }
    @PostMapping("/invoice")
    public InvoiceModel createInvoice(@Valid @RequestBody InvoiceModel invoice) {
        invoice.setName(UUID.randomUUID().toString());
        return invoiceRepo.save(invoice);
    }

    @PostMapping("/invoice/update/{invoiceId}")
    public InvoiceModel updateInvoice(@PathVariable Long invoiceId,
                                @Valid @RequestBody InvoiceModel userRequest) {
        return invoiceRepo.findById(invoiceId)
                .map(invoice -> {
                    invoice.setAddress(userRequest.getAddress());
                    invoice.setCname(userRequest.getCname());
                    invoice.setEmail(userRequest.getEmail());
                    invoice.setLogo(userRequest.getLogo());
                    invoice.setTerms(userRequest.getTerms());
                    return invoiceRepo.save(invoice);
                }).orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id " + invoiceId));
    }


    @PostMapping("/invoice/delete/{invoiceId}")
    public ResponseEntity<?> deleteInvoice(@PathVariable Long invoiceId) {
        return invoiceRepo.findById(invoiceId)
                .map(invoice -> {
                    invoiceRepo.delete(invoice);
                    return ResponseEntity.ok().body("OK");
                }).orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id " + invoiceId));
    }
}
