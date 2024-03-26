package com.onlinesareesshopping.entities;



	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.OneToOne;
	import javax.persistence.Table;

	@Entity
	@Table(name = "Online_Payment")
	public class Onlinepayment {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "Online_Id")
		private int onlineId;

		@Column(name = "UPi_Payment")
		private int upiPayment;

		@Column(name = "Card_Payment")
		private int cardPayment;

		@Column(name = "Net_Banking")
		private int netBanking;

		@OneToOne
		@JoinColumn(name = "Payment_Id", referencedColumnName = "Payment_Id")
		private Payment payment;

		public int getOnlineId() {
			return onlineId;
		}

		public void setOnlineId(int onlineId) {
			this.onlineId = onlineId;
		}

		public int getUpiPayment() {
			return upiPayment;
		}

		public void setUpiPayment(int upiPayment) {
			this.upiPayment = upiPayment;
		}

		public int getCardPayment() {
			return cardPayment;
		}

		public void setCardPayment(int cardPayment) {
			this.cardPayment = cardPayment;
		}

		public int getNetBanking() {
			return netBanking;
		}

		public void setNetBanking(int netBanking) {
			this.netBanking = netBanking;
		}

		public Payment getPayment() {
			return payment;
		}

		public void setPayment(Payment payment) {
			this.payment = payment;
		}

		@Override
		public String toString() {
			return "Onlinepayment [onlineId=" + onlineId + ", upiPayment=" + upiPayment + ", cardPayment=" + cardPayment
					+ ", netBanking=" + netBanking + ", payment=" + payment + "]";
		}

		public Onlinepayment() {
			super();
			// TODO Auto-generated constructor stub
		}

		
	}




