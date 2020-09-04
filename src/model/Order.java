package model;

public class Order {

	private int id;
	private int pid;
	private String Custname;
	private String address;
	private int price;
	private int quantity;
	
	public int getId() {
		return id;
	}
	public void setId(int orderId) {
		this.id = orderId;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getCustname() {
		return Custname;
	}
	public void setCustname(String Custname) {
		this.Custname = Custname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int pPrice) {
		this.price = pPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
