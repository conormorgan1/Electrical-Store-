package model;

public class Customer {
	int custID;
	String firstName;
	String lastName;
	String address;
	long telNum;
	
	public Customer(int custID,String firstName, String lastName, String address, long telNum) {
		this.custID = custID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.telNum = telNum;
		
	}
	public int getCustID() {
		return custID;
	}
	public void setCustID(int custID) {
		this.custID = custID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getTelNum() {
		return telNum;
	}
	public void setTelNum(long telNum) {
		this.telNum = telNum;
	}
}
