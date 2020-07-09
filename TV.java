package model;

public class TV {
	int TVId;
	String make;
	String model;
	double price;
	int screensize;
	

	public TV(int TVId, String make, String model, double price, int screensize ) {
		this.TVId = TVId;
		this.make = make;
		this.model = model;
		this.price = price;
		this.screensize = screensize;
	
	}
	public int getTVId() {
		return TVId;
	}
	public void setTVId(int tVId) {
		TVId = tVId;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getScreensize() {
		return screensize;
	}
	public void setScreensize(int screensize) {
		this.screensize = screensize;
	}


}
