package com.paperless.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "invoice_list")
public class UserInvoice extends AuditModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Size(min = 3, max = 100)
    private String user_name;

    @ElementCollection
    @JsonProperty("items")
    private List<ItemData> items;

    public List<ItemData> getItems() {
        return items;
    }

    public void setItems(List<ItemData> items) {
        this.items = items;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "invoice_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InvoiceModel data;

    private Long total_value;

    private String invoice_url;

    public String getInvoice_url() {
        return invoice_url;
    }

    public void setInvoice_url(String invoice_url) {
        this.invoice_url = invoice_url;
    }

    public InvoiceModel getData() {
        return data;
    }

    public void setData(InvoiceModel data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }


    public Long getTotal_value() {
        return total_value;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setTotal_value(Long total_value) {
        this.total_value = total_value;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

}
