package com.onlinesareesshopping.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_id")
    private int loginId;


	   @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "register_id") // Adjust column names if necessary
    private Register registration;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
    
    
	public Register getRegistration() {
		return registration;
	}
	public void setRegistration(Register registration) {
		this.registration = registration;
	}
	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Login [loginId=" + loginId + ", registration=" + registration + ", email=" + email + ", password="
				+ password + "]";
	}
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}