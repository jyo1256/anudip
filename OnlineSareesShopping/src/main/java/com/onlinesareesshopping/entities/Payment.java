package com.onlinesareesshopping.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;
	private int paymentDate;
	private int orderid;
	 @OneToOne(mappedBy = "payment")
	    private Cashondelivery cashOnDelivery;
	 
	public Cashondelivery getCashOnDelivery() {
		return cashOnDelivery;
	}
	public void setCashOnDelivery(Cashondelivery cashOnDelivery) {
		this.cashOnDelivery = cashOnDelivery;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(int paymentDate) {
		this.paymentDate = paymentDate;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	@Override
	public String toString() {
		return "Payment [ paymentDate=" + paymentDate + ", orderid=" + orderid
				+ "]";
	}
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static List<Payment> getAllPayments() {
		// TODO Auto-generated method stub
		return null;
	}


}


