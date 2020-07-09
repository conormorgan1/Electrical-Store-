package model;

public class Order {
	int orderID;
	String productName;
	double cost;
	
	public Order(int orderID, String productName, double cost) {
		this.orderID = orderID;
		this.productName = productName;
		this.cost = cost;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
}
