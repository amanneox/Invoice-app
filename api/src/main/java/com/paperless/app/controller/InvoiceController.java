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
        return invoiceRepo.save(invoice);
    }

    @PutMapping("/user/{invoiceId}")
    public InvoiceModel updateInvoice(@PathVariable Long invoiceId,
                                @Valid @RequestBody InvoiceModel userRequest) {
        return invoiceRepo.findById(invoiceId)
                .map(invoice -> {
                    userRequest.setAddress(userRequest.getAddress());
                    userRequest.setCname(userRequest.getCname());
                    return invoiceRepo.save(userRequest);
                }).orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id " + invoiceId));
    }


    @DeleteMapping("/user/{invoiceId}")
    public ResponseEntity<?> deleteInvoice(@PathVariable Long invoiceId) {
        return invoiceRepo.findById(invoiceId)
                .map(invoice -> {
                    invoiceRepo.delete(invoice);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id " + invoiceId));
    }
}
