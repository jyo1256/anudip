package com.onlinesareesshoppingdao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.onlinesareesshopping.entities.Payment;

public class Paymentdaoimpl implements Paymentdao {
private Session session;
	@Override
	public Payment getPaymentById(int paymentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPayment(Payment payment) {
		Transaction tx = null;
        try {
            tx = session.beginTransaction();
           
            session.save(payment);
            tx.commit();
            System.out.println("Record inserted into payment table: " +payment);
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
		
	}

	@Override
	public void updatePayment(Payment payment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePayment(int paymentId) {
		// TODO Auto-generated method stub
		
	}

	public List<Payment> getAllPayments() {
		// TODO Auto-generated method stub
		return null;
	}

}
