package model;

public class OrderDetails {
	int orderDetailsID;
	String name;
	int quantity;
	double total;
	
	public OrderDetails(int orderDetailsID, String name, int quantity, double total) {
		this.orderDetailsID = orderDetailsID;
		this.name = name;
		this.quantity = quantity;
		this.total = total;

	}
	public int getOrderDetailsID() {
		return orderDetailsID;
	}
	public void setOrderDetailsID(int orderDetailsID) {
		this.orderDetailsID = orderDetailsID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
}
