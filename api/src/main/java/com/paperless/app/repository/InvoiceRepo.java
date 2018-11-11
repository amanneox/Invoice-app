package com.paperless.app.repository;
import com.paperless.app.model.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Repository
public interface InvoiceRepo extends JpaRepository<InvoiceModel, Long> {

}
