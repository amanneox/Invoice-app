package com.paperless.app.model;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "invoice_template")
public class InvoiceModel extends AuditModel {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;


    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank
    private String logo;

    @NotBlank
    private String address;

    @NotBlank
    private String cname;

    private int number;

    @NotBlank
    private String email;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getLogo() {
        return logo;
    }

    @Override
    public Date getCreatedAt() {
        return super.getCreatedAt();
    }

    public String getAddress() {
        return address;
    }

    @Override
    public Date getUpdatedAt() {
        return super.getUpdatedAt();
    }

    public String getCname() {
        return cname;
    }

    public int getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void setCreatedAt(Date createdAt) {
        super.setCreatedAt(createdAt);
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public void setUpdatedAt(Date updatedAt) {
        super.setUpdatedAt(updatedAt);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
