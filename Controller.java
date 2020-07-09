package controller;
import java.sql.SQLException;

import javafx.application.Platform;
import model.Customer;
import model.Order;
import model.OrderDetails;
import model.Phone;
import model.TV;

public class Controller {
	static DerbyDB db = new DerbyDB();
	public static void createCustomerButton(Customer customer ) throws SQLException {
		int custNo = customer.getCustID();
		String firstName = customer.getFirstName();
		String lastName = customer.getLastName();
		String address = customer.getAddress();
		long telNum = customer.getTelNum();
		
		db.addCustomer(custNo, firstName, lastName, address, telNum);

	   }	
	public static void updateCustomerButton(Customer customer ) throws SQLException 
	   {
		int custNo = customer.getCustID();
		String firstName = customer.getFirstName();
		String lastName = customer.getLastName();
		String address = customer.getAddress();
		long telNum = customer.getTelNum();
		
		db.updateCustomer(custNo, firstName, lastName, address, telNum);
    
	   }
	public static void removeCustomerButton(int custNo) throws SQLException {
		
		db.deleteCustomer(custNo);

           }	
	public static void createOrderButton(Order obj ) throws SQLException 
	   {
		int orderId = obj.getOrderID();
		String prod = obj.getProductName();
		double cost = obj.getCost();
	
		db.addOrder(orderId, prod, cost);
	      }
	public static void updateOrderButton(Order obj) throws SQLException 
	   {
		int orderId = obj.getOrderID();
		String prod = obj.getProductName();
		double cost = obj.getCost();
		db.addOrder(orderId, prod, cost);
	   }
	public static void removeOrderButton(int orderNo) throws SQLException {
		db.deleteOrder(orderNo);
		      }
	public static void createOrderDetailsButton(OrderDetails obj) throws SQLException 
	   {
		int orderDetailsNo = obj.getOrderDetailsID();
		String productName = obj.getName();
		int quantity = obj.getQuantity();
		double total = obj.getTotal();
		db.addOrderDetails(orderDetailsNo, productName, quantity, total);
					}
	public static void updateOrderDetailsButton(OrderDetails obj) throws SQLException 
	   {
		int orderDetailsNo = obj.getOrderDetailsID();
		String productName = obj.getName();
		int quantity = obj.getQuantity();
		double total = obj.getTotal();
		db.addOrderDetails(orderDetailsNo, productName, quantity, total);	   }
	public static void removeOrderDetailsButton(int orderDetailsNo) throws SQLException { 	
		db.deleteOrderDetails(orderDetailsNo);
		      }
	public static void createPhoneButton(Phone phone) throws SQLException 
	   {
		int phoneID = phone.getPhoneId();
		String make = phone.getMake();
		String model = phone.getModel();
		double price = phone.getPrice();
		int storage = phone.getStorage();
		
		db.addPhone(phoneID, make, model, price, storage);
	   }	
	public static void updatePhoneButton(Phone phone) throws SQLException 
	   {
		int phoneID = phone.getPhoneId();
		String make = phone.getMake();
		String model = phone.getModel();
		double price = phone.getPrice();
		int storage = phone.getStorage();
		
		db.addPhone(phoneID, make, model, price, storage);

	   }
	public static void removePhoneButton(int phoneID) throws SQLException {
		db.deletePhone(phoneID);
	}
	public static void createTvButton(TV tv) throws SQLException 
	   {
		int tvID = tv.getTVId();
		String make = tv.getMake();
		String model = tv.getModel();
		double price = tv.getPrice();
		int screenSize = tv.getScreensize();

		db.addTv(tvID, make, model, price, screenSize);
	      }	
	public static void updateTvButton(TV tv) throws SQLException 
	   {
		int tvID = tv.getTVId();
		String make = tv.getMake();
		String model = tv.getModel();
		double price = tv.getPrice();
		int screenSize = tv.getScreensize();

		db.addTv(tvID, make, model, price, screenSize);
	    
	   }
	public static void removeTvButton(int tvID) throws SQLException {
		db.deleteTv(tvID);
 	}
 
	/*public static void showCustomerTable() throws SQLException {
		DerbyDB.showTable("customer");
	}
	public static void showOrdersTable() throws SQLException {
		DerbyDB.showTable("orders");
	}
	public static void showOrderDetailsTable() throws SQLException {
		DerbyDB.showTable("orderDetails");
	}
	public static void showPhoneTable() throws SQLException {
		DerbyDB.showTable("phone");
	}
	public static void showTvTable() throws SQLException {
		DerbyDB.showTable("tv");
	}
	*/
	public static void close(){
			Platform.exit();
	}

}
