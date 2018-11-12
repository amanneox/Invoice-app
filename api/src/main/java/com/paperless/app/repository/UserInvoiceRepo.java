package com.paperless.app.repository;

import com.paperless.app.model.UserInvoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInvoiceRepo extends JpaRepository<UserInvoice, Long> {
    Page<UserInvoice> findById (Long invoiceId, Pageable pageable);
}