package com.onlinesareesshoppingdao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.onlinesareesshopping.entities.Order;

public class Orderdaoimpl implements Orderdao{
	  private Session session;

	@Override
	public Order getOrderById(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addOrder(Order orderobj) {
		Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(orderobj);
            tx.commit();
            System.out.println("Record inserted into order table: " + orderobj);
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
		
	}

	@Override
	public void updateOrder(Order order) {
		  Transaction tx = null;
	        try {
	           Order order1 = session.find(Order.class, order);
	            if (order == null) {
	                System.out.print("Record not found");
	            } else {
	               order.setOrderid(0);
	             order.setTotalamount(0);
	                tx = session.beginTransaction();
	                session.merge(order);
	                tx.commit();
	                System.out.println(order);
	                System.out.println("Record updated into order table");
	            }
	        } catch (Exception e) {
	            if (tx != null) {
	                tx.rollback();
	            }
	            e.printStackTrace();
	        }
		
	}

	@Override
	public void deleteOrder(int orderId) {
		// TODO Auto-generated method stub
		
	}

	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(Order order) {
		Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(order);
            tx.commit();
            System.out.println("Record inserted into order table: " + order);
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
		
		
	}

}
