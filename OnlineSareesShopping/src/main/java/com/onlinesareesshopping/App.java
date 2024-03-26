package com.onlinesareesshopping;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import com.onlinesareesshopping.entities.Admin;
import com.onlinesareesshopping.entities.Cashondelivery;
import com.onlinesareesshopping.entities.Customer;
import com.onlinesareesshopping.entities.Login;
import com.onlinesareesshopping.entities.Order;
import com.onlinesareesshopping.entities.Payment;
import com.onlinesareesshopping.entities.Products;
import com.onlinesareesshopping.entities.Register;
import com.onlinesareesshoppingdao.Admindaoimpl;
import com.onlinesareesshoppingdao.Cashondeliverydao;
import com.onlinesareesshoppingdao.Cashondeliverydaoimpl;
import com.onlinesareesshoppingdao.Customerdaoimpl;
import com.onlinesareesshoppingdao.Logindaoimpl;
import com.onlinesareesshoppingdao.Orderdao;
import com.onlinesareesshoppingdao.Orderdaoimpl;
import com.onlinesareesshoppingdao.Paymentdao;
import com.onlinesareesshoppingdao.Productsdaoimpl;
import com.onlinesareesshoppingdao.Registerdaoimpl;

public class App {

	private static StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	private static Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
	private static SessionFactory sf = meta.getSessionFactoryBuilder().build();
	private static  Session session = sf.openSession();
	private static Productsdaoimpl productdao = new Productsdaoimpl(session);
	private static Logindaoimpl loginDao = new Logindaoimpl(session);
	private static Registerdaoimpl registerDao = new Registerdaoimpl(session);
	private static Admindaoimpl adminDao = new Admindaoimpl(session);
	private static Customerdaoimpl customerDao = new Customerdaoimpl(session);
	
	public static void main(String[] args) {
		try {
			System.out.println("Welcome to Online Sarees Shopping System!");

			Scanner scanner = new Scanner(System.in);
			int choice;

			do {
				System.out.println("Main-menu");
				System.out.println("1. Registration");
				System.out.println("2. Sign-in / Login");
				System.out.println("3. Exit");
				System.out.print("Enter your choice: ");
				choice = scanner.nextInt();

				switch (choice) {
				case 1:
					registrationMenu(scanner, registerDao, adminDao, customerDao);
					break;
				case 2:
					login(scanner, loginDao, adminDao, customerDao); // Pass LoginDaoImpl instance to login method
					break;
				case 3:
					System.out.println("Exiting the system. Thank you for using our service!");
					break;
				default:
					System.out.println("Invalid choice. Please enter a valid option.");
				}
			} while (choice != 3);

			scanner.close();
			session.close();
			sf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void registrationMenu(Scanner scanner, Registerdaoimpl registerDao, Admindaoimpl adminDao, Customerdaoimpl customerDao) {
		int choice;
		do {
			System.out.println("1. Admin Registration");
			System.out.println("2. Customer Registration");
			System.out.println("3. Exit");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				adminRegistration(scanner, registerDao, adminDao);
				break;
			case 2:
				customerRegistration(scanner, registerDao, customerDao);
				break;
			case 3:
				System.out.println("Exiting registration menu.");
				break;
			default:
				System.out.println("Invalid choice. Please enter a valid option.");
			}
		} while (choice != 3);
	}

	public static void adminRegistration(Scanner scanner, Registerdaoimpl registerDao, Admindaoimpl adminDao) {
		Admin admin = new Admin();
		System.out.print("Enter admin username: ");
		admin.setAdminName(scanner.next());
		System.out.print("Enter admin email: ");
		admin.setEmail(scanner.next());
		System.out.print("Enter admin contact number: ");
		admin.setMobile(scanner.nextInt());
		System.out.print("Enter admin password: ");
		admin.setPassword(scanner.next());
		Register adminRegister = new Register();
		adminRegister.setDateOfRegister(java.time.LocalDate.now().toString());
		registerDao.insert(adminRegister);
		admin.setRegister(adminRegister);
		adminDao.insert(admin);
		System.out.println("Admin registered successfully.");
	}

	public static void customerRegistration(Scanner scanner, Registerdaoimpl registerDao, Customerdaoimpl customerDao) {
		Customer customer = new Customer();
		System.out.print("Enter customer name: ");
		customer.setCustName(scanner.next());
		System.out.print("Enter customer address: ");
		customer.setCustAdd(scanner.next());
		System.out.print("Enter customer email: ");
		customer.setCustEmail(scanner.next());
		System.out.print("Enter customer gender: ");
		customer.setGender(scanner.next());
		System.out.print("Enter customer mobile number: ");
		customer.setCustMobile(scanner.nextInt());
		System.out.print("Enter customer password: ");
		customer.setPassword(scanner.next());
		Register customerRegister = new Register();
		customerRegister.setDateOfRegister(java.time.LocalDate.now().toString());
		registerDao.insert(customerRegister);
		customer.setRegister(customerRegister);
		customerDao.insert(customer);
		System.out.println("Customer registered successfully.");
	}

	private static void login(Scanner scanner, Logindaoimpl loginDao, Admindaoimpl adminDao, Customerdaoimpl customerDao) {
	    Login login = new Login();
	    System.out.println("**************  LOGIN ************");
	    System.out.println("Enter Email:");
	    String email = scanner.next();
	    login.setEmail(email);
	    System.out.println("Enter Password:");
	    String password = scanner.next();
	    login.setPassword(password);
	    Register adminRegister = new Register();
	    registerDao.insert(adminRegister);
		Register customerRegister = new Register();
		registerDao.insert(customerRegister);
	    login.setRegistration(adminRegister);
	    login.setRegistration(customerRegister);
	    // Attempt to log in as an admin or customer
	    Admin loggedInAdmin = adminDao.getAdminByEmailAndPassword(email, password);
	    if (loggedInAdmin != null) {
	       adminRegister.setDateOfRegister(java.time.LocalDate.now().toString());
	        loginDao.insertLogin(login);
	        System.out.println("Admin login successful!");
	        adminMenu(loggedInAdmin);
	    } else {
	        Customer loggedInCustomer = customerDao.getCustomerByEmailAndPassword(email, password);
	        if (loggedInCustomer != null) {
	        	customerRegister.setDateOfRegister(java.time.LocalDate.now().toString());
	            loginDao.insertLogin(login);
	            System.out.println("Customer login successful!");
	            customerMenu(loggedInCustomer);
	        } else {
	            System.out.println("Login failed. Invalid email or password.");
	        }
	    }
	}

	private static void adminMenu(Admin loggedInAdmin) {
	    Scanner scanner = new Scanner(System.in);
	    int choice;
	    do {
	        System.out.println("Admin Menu");
	        System.out.println("1. Add Product");
	        System.out.println("2. View All Products");
	        System.out.println("3. Update Product");
	        System.out.println("4. Delete Record");
	        System.out.println("5. View All Orders");
	        System.out.println("6. View All Payments");
	        System.out.println("7. Back to Main Menu");
	        System.out.print("Enter your choice: ");
	        choice = scanner.nextInt();
	        switch (choice) {
	            case 1:
	                addProduct(scanner);
	                break;
	            case 2:
	                viewAllProducts(session);
	                break;
	            case 3:
	                updateProduct(scanner);
	                break;
	            case 4:
	                deleteRecord(scanner);
	                break;
	            case 5:
	                viewAllOrders(session);
	                break;
	            case 6:
	                viewAllPayments(session);
	                break;
	            case 7:
	                System.out.println("Returning to Main Menu...");
	                return;
	            default:
	                System.out.println("Invalid choice. Please enter a valid option.");
	        }
	    } while (true);
	}


	private static void deleteRecord(Scanner scanner) {
		System.out.print("Enter product id : ");
		int productid = scanner.nextInt();
		productdao.deleteProduct(productid);
		
	}

	

	private static void updateProduct(Scanner scanner) {
		System.out.print("Enter product id : ");
	 int productid = scanner.nextInt();
		System.out.print("Enter productname to update : ");
		scanner.nextLine();
		 String productname = scanner.nextLine();
		

		productdao.update(productid,productname);


	}

	private static void viewAllPayments(Session session) {
		 Payment payment1=new Payment();
		   payment1.setCashOnDelivery(null);
		   Cashondeliverydao cashOnDeliveryDaO = new Cashondeliverydaoimpl(session); 
		    int codId = 1; 
		    int mobNo = 2356; 
		    String status = "yes"; 
		    String address="ravidmm5-120-1";
		    
		  System.out.println("All payments ADD");
	}

	

	private static void viewAllOrders(Session session) {
		List<Products> productList = session.createQuery("FROM Products", Products.class).getResultList();
		if (productList.isEmpty()) {
			System.out.println("No products available.");
		} else {
			System.out.println("all orders available.");
			for (Products product : productList) {
				System.out.println("Name: " + product.getProductName());
				System.out.println("Price: " + product.getPrice());
				System.out.println("Color: " + product.getColor());
				System.out.println("Description: " + product.getDescription());
				System.out.println("Quantity: " + product.getQuantity());
				System.out.println("*******************************************");
			}
		}
		
	}

	private static void viewAllProducts( Session session) {
		try {
			
			if (session == null) {
				System.out.println("Session is not initialized.");
				return;
			}

			List<Products> productList = session.createQuery("FROM Products", Products.class).getResultList();

			if (productList.isEmpty()) {
				System.out.println("No products available.");
			} else {
				System.out.println("All Products:");
				for (Products product : productList) {
					System.out.println("Name: " + product.getProductName());
					System.out.println("Price: " + product.getPrice());
					System.out.println("Color: " + product.getColor());
					System.out.println("Description: " + product.getDescription());
					System.out.println("Quantity: " + product.getQuantity());
					System.out.println("-----------------------------------");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void addProduct(Scanner scanner) {
		try {
			System.out.println("Adding Product:");
			System.out.print("Enter product name: ");
			String productName = scanner.next();
			System.out.print("Enter description: ");
			String description = scanner.next();
			System.out.print("Enter quantity: ");
			int quantity = scanner.nextInt();
			System.out.print("Enter color: ");
			String color = scanner.next();
			System.out.print("Enter product price: ");
			double price = scanner.nextDouble();

			Products product = new Products();
			product.setProductName(productName);
			product.setDescription(description);
			product.setQuantity(quantity);
			product.setColor(color);
			product.setPrice(price);
			Register adminRegister = new Register();
			adminRegister.setDateOfRegister(java.time.LocalDate.now().toString());
			registerDao.insert(adminRegister);
			product.setRegister(adminRegister);
			productdao.insert(product);
			System.out.println("Product added successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void customerMenu(Customer loggedInCustomer) {
	    Scanner scanner = new Scanner(System.in);
	    int choice;

	    do {
	        System.out.println("Customer Menu");
	        System.out.println("1. View Products");
	        System.out.println("2. Display Single Record");
	        System.out.println("3. Order Products");
	        System.out.println("4. Place Order");
	        System.out.println("5. Payment");
	        System.out.println("6. Logout");
	        System.out.print("Enter your choice: ");
	        choice = scanner.nextInt();
	        switch (choice) {
	            case 1:
	                viewProducts();
	                break;
	            case 2:
	                displaySingleRecord(scanner);
	                break;
	            case 3:
	                orderProducts();
	                break;
	            case 4:
	                placeOrder(null);
	                break;
	            case 5:
	                makePayment();
	                break;
	            case 6:
	                System.out.println("Logging out...");
	                return;
	            default:
	                System.out.println("Invalid choice. Please enter a valid option.");
	        }
	    } while (true);
	}

	private static void displaySingleRecord(Scanner sc) {
		System.out.print("Enter product id : ");
	int 	productid = sc.nextInt();
		productdao.search(productid);
	}

	private static void viewProducts() {
		try {
			// Ensure session is initialized before using it
			if (session == null) {
				System.out.println("Session is not initialized.");
				return;
			}

			List<Products> productList = session.createQuery("FROM Products", Products.class).getResultList();

			if (productList.isEmpty()) {
				System.out.println("No products available.");
			} else {
				System.out.println("All Products:");
				for (Products product : productList) {
					System.out.println("Name: " + product.getProductName());
					System.out.println("Price: " + product.getPrice());
					System.out.println("Color: " + product.getColor());
					System.out.println("Description: " + product.getDescription());
					System.out.println("Quantity: " + product.getQuantity());
					System.out.println("-----------------------------------");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "resource", "unused"})
	private static void orderProducts() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Order Products");

		System.out.print("Enter the ID of the product you want to order: ");
		int productId = scanner.nextInt();

		boolean continueOrdering = true;
		while (continueOrdering) {
			System.out.print("Do you want to order more products? (yes/no): ");
			String choice = scanner.next();
			if (choice.equalsIgnoreCase("yes")) {
				System.out.print("Enter the ID of the next product you want to order: ");
				List<Products> productList = session.createQuery("FROM Products", Products.class).getResultList();

				if (productList.isEmpty()) {
					System.out.println("No products available.");
				} else {
					System.out.println("All Products:");
					for (Products product : productList) {
						System.out.println("Name: " + product.getProductName());
						System.out.println("Price: " + product.getPrice());
						System.out.println("Color: " + product.getColor());
						System.out.println("Description: " + product.getDescription());
						System.out.println("Quantity: " + product.getQuantity());
						System.out.println("-----------------------------------");
					}
				}
				
			} else if (choice.equalsIgnoreCase("no")) {
				continueOrdering = false;
				
				List<Order> orders = Order.getAllOrders();
				if (orders != null && !orders.isEmpty()) {
					for (Order order : orders) {
						System.out.println(order); 
					}
				} else {
					System.out.println("would you like continue order:");
				}
				
				}
			}
		}
	

	@SuppressWarnings("resource")
	private static void placeOrder(Date date) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Place Order");
		System.out.println("Place your order:");
		System.out.print("Enter item number: ");
		int itemNumber = scanner.nextInt();
		scanner.nextLine(); 

		System.out.print("Enter quantity: ");
		int quantity = scanner.nextInt();
		scanner.nextLine();
		double itemPrice = getItemPrice(itemNumber); 
		double totalAmount = quantity*itemPrice;
		Order orderobj = new Order();
		orderobj.setItemNumber(itemNumber);
		orderobj.setQuantity(quantity);
		orderobj.setTotalamount(totalAmount); 
        orderobj.setOrderdate(date);
        orderobj.setProducts(productdao);

		Orderdaoimpl orderDAO = new Orderdaoimpl();
		orderDAO.save(orderobj);

		System.out.println("Order placed successfully!");
		System.out.println("Order Details:");
		System.out.println("Item Number: " + itemNumber);
		System.out.println("Quantity: " + quantity);
		System.out.println("Total Amount: " + totalAmount);
	}

	private static double getItemPrice(int itemNumber) {
	
			if (itemNumber == 1) {
				return 99.0; 
			} else if (itemNumber == 2) {
				return 198.0;
			}
		return 0.0; 
	}
	
	@SuppressWarnings("resource")
	private static void makePayment() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Payment");

		System.out.println("Select Payment Method:");
		System.out.println("1. Cash on Delivery");
		System.out.println("2. Online payment");
		System.out.print("Enter your choice: ");
		int paymentMethod = scanner.nextInt();

		switch (paymentMethod) {
		case 1:
			processCashOnDelivery();
			break;
		case 2:
			processOnlinePayment();
			break;
		default:
			System.out.println("Invalid payment method.");
			break;
		}
	}

	@SuppressWarnings("resource")
	private static void processOnlinePayment() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Select Online Payment Method:");
		System.out.println("1. UPI");
		System.out.println("2. Credit Card");

		System.out.print("Enter your choice: ");
		int onlinePaymentChoice = scanner.nextInt();
		scanner.nextLine(); 
		switch (onlinePaymentChoice) {
		case 1:
			processUPIPayment();
			break;
		case 2:
			processCreditCardPayment();
			break;
		default:
			System.out.println("Invalid choice. Please try again.");
		}
	}

	private static void processUPIPayment() {
		System.out.println("You have chosen UPI (Unified Payments Interface) for payment.");
		System.out.println("UPI payment processed successfully!");
	}

	private static void processCreditCardPayment() {
		System.out.println("You have chosen Credit Card for payment.");
		System.out.println("Credit card payment processed successfully!");
	}


	@SuppressWarnings("unused")
	private static void processCashOnDelivery() {
	   
	    Cashondeliverydao cashOnDeliveryDaO = new Cashondeliverydaoimpl(session); 
	    int codId = 1; 
	    int mobNo = 2356; 
	    String status = "yes"; 
	    String address="ravidmm5-120-1";
	   
	    Cashondelivery codobj = cashOnDeliveryDaO.getcashondelivery(codId, mobNo, status,address); 
	    Cashondelivery codobj1 = new Cashondelivery();
	    codobj1.setCodId(codId);
	    codobj1.setMobNo(mobNo);
	    codobj1.setStatus(status);
	    codobj1.setAddress(address);
	    codobj1.setPaymentId(mobNo);
	    // Insert the new Cashondelivery object
	    cashOnDeliveryDaO.insert(codobj1);
	    // Print some messages
	    System.out.println("You have chosen Cash on Delivery.");
	    System.out.println("Your order will be delivered, and you can pay the delivery person in cash.");
	    
	    System.out.println("**************************THANKYOU****************************************");
	}


}

