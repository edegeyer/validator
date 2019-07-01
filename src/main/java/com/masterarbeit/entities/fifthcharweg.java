package com.masterarbeit.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;


@Entity
public class fifthcharweg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date birthday;
    private String email;
    private String firstName;
    private String insurance;
    private String lastInvoiceTotal;
    private String surname;
    private Integer contact;
    private String phoneNumber;
    private Integer privateInsurance;

    public fifthcharweg() {
    }

    public fifthcharweg(int id, Date birthday, String email, String contactFirstName,
                        String insurance, String lastInvoice, String surname,
                        int contact, String contactPhone, int privateInsurance) {
        this.birthday = birthday;
        this.contact = contact;
        this.email = email;
        this.firstName = contactFirstName;
        this.surname = surname;
        this.phoneNumber = contactPhone;
        this.id = id;
        this.insurance = insurance;
        this.lastInvoiceTotal = lastInvoice;
        this.privateInsurance = privateInsurance;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContact() {
        return contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPrivateInsurance() {
        return privateInsurance;
    }

    public String getContactFirstName() {
        return firstName;
    }

    public String getContactLastName() {
        return surname;
    }

    public String getContactPhone() {
        return phoneNumber;
    }

    public String getInsurance() {
        return insurance;
    }

    public String getLastInvoice() {
        return lastInvoiceTotal;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }

    public void setContactFirstName(String contactFirstName) {
        this.firstName = contactFirstName;
    }

    public void setContactLastName(String surname) {
        this.surname = surname;
    }

    public void setContactPhone(String contactPhone) {
        this.phoneNumber = contactPhone;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public void setLastInvoice(String lastInvoice) {
        this.lastInvoiceTotal = lastInvoice;
    }

    public void setPrivateInsurance(Integer privateInsurance) {
        this.privateInsurance = privateInsurance;
    }


}

