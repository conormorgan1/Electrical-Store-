package model;

public class Phone {
	int phoneId;
	String make;
	String model;
	double price;
	
	public Phone(int phoneId, String make, String model, double price, int storage ) {
		this.phoneId = phoneId;
		this.make = make;
		this.model = model;
		this.price = price;
		this.storage = storage;
	
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	int storage;
	public int getPhoneId() {
		return phoneId;
	}
	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getStorage() {
		return storage;
	}
	public void setStorage(int storage) {
		this.storage = storage;
	}
	
	
}
