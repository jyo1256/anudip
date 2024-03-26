package com.onlinesareesshoppingdao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.onlinesareesshopping.entities.Login;

public class Logindaoimpl implements Logindao {

    private Session session;

    public Logindaoimpl(Session session) {
        this.session = session;
    }

    @Override
    public Login getLoginById(int loginId) {
        try {
            Login login = session.find(Login.class, loginId);
            if (login == null)
                System.out.println("Record not found");
            else
                System.out.println(login);
            return login;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateLogin(int loginId, String email, String password) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Login login = session.find(Login.class, loginId);
            if (login == null) {
                System.out.println("Record not found");
            } else {
                login.setEmail(email);
                login.setPassword(password);
                session.merge(login);
                tx.commit();
                System.out.println(login);
                System.out.println("Record updated into login table");
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void insertLogin(Login loginobj) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
           
            session.save(loginobj);
            tx.commit();
            System.out.println("Record inserted into login table: " + loginobj);
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
}
