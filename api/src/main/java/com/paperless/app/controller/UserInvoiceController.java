package com.paperless.app.controller;

import com.paperless.app.exception.ResourceNotFoundException;
import com.paperless.app.model.UserInvoice;
import com.paperless.app.repository.InvoiceRepo;
import com.paperless.app.repository.UserInvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserInvoiceController {
    @Autowired
    protected UserInvoiceRepo userInvoiceRepo;
    @Autowired
    protected InvoiceRepo invoiceRepo;
    @GetMapping("/user/invoice")
    public Page<UserInvoice> getInvoice(Pageable pageable) {
        return userInvoiceRepo.findAll(pageable);
    }

    @GetMapping("/user/invoice/{invoiceId}")
    public Page<UserInvoice> getInvoiceById(@PathVariable (value = "invoiceId") Long invoiceId,Pageable pageable) {
        return userInvoiceRepo.findById(invoiceId,pageable);

    }
    @PostMapping("/user/invoice/{invoiceId}")
    public UserInvoice createInvoice(@Valid @PathVariable Long invoiceId, @RequestBody UserInvoice invoice) {

        return invoiceRepo.findById(invoiceId).map(data -> {
            invoice.setData(data);
            return userInvoiceRepo.save(invoice);
        }).orElseThrow(() -> new ResourceNotFoundException("InvoiceId " + invoiceId + " not found"));
    }
}
