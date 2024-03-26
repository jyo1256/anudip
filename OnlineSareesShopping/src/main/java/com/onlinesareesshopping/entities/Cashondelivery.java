package com.onlinesareesshopping.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cashondelivery")
public class Cashondelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codId;
    
    private int paymentId; // Foreign key to Payment table
    private int mobNo;
    private String address;
    private String status;

    @OneToOne
    @JoinColumn(name = "payment_id") // Adjust the column name as per your schema
    private Payment payment;
    
    // Getters and setters
    public int getCodId() {
        return codId;
    }

    public void setCodId(int codId) {
        this.codId = codId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getMobNo() {
        return mobNo;
    }

    public void setMobNo(int mobNo) {
        this.mobNo = mobNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

   

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    // toString method
    @Override
    public String toString() {
        return "Cashondelivery [codId=" + codId + ", paymentId=" + paymentId + ", mobNo=" + mobNo + ", address="
                + address + ", status=" + status + ", payment=" + payment + "]";
    }

    // Constructors
    public Cashondelivery() {
        super();
    }
}
