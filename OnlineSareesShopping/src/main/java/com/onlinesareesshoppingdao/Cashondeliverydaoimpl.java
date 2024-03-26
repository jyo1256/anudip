package com.onlinesareesshoppingdao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.onlinesareesshopping.entities.Cashondelivery;

public class Cashondeliverydaoimpl implements Cashondeliverydao {

    private final Session session;

    public Cashondeliverydaoimpl(Session session) {
        this.session = session;
    }

    public void insert(Cashondelivery codobj) {
    	 Transaction tx = null;
         try {
             tx = session.beginTransaction();
            
             session.save(codobj);
             tx.commit();
             System.out.println("Record inserted into cashondelivery table: " +codobj);
         } catch (Exception e) {
             if (tx != null) {
                 tx.rollback();
             }
             e.printStackTrace();
         }
    }

	
	@Override
	public Cashondelivery getcashondelivery(int codId, int mobNo, String status,String address) {
	    Transaction tx = null;
	    Cashondelivery cod = null;
	    try {
	        tx = session.beginTransaction();

	        // Constructing the query to fetch Cashondelivery object based on codId, mobNo, and status
	        Query<Cashondelivery> query = session.createQuery("FROM Cashondelivery WHERE codId = :codId AND mobNo = :mobNo AND status = :status", Cashondelivery.class);
	        query.setParameter("codId", codId);
	        query.setParameter("mobNo", mobNo);
	        query.setParameter("status", status);

	        // Execute the query and get the result
	        cod = query.uniqueResult();

	        tx.commit();
	        System.out.println("Cashondelivery object retrieved: " + cod);
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    }
	    return cod;
	}

	

	

	
    // Implement other methods if needed
}
