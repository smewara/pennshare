package example;

public class User {

	public int userid;
	public String name;
	public String password;
	public String email;
	public String address;
	public String phone;
	public double dollar;
	public double trust;
	
	public int itemid;
	public String itemname;
	public String itemimage;
	public int categoryid;
	
	public User() {}
	
	public User(String email, String name, String address) {
		this.name = name;
		this.email = email;
		this.address = address;
	}
	
	public User(String name, String password, String email, String address, String phone ) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phone = phone;
	}
	
	
	public User(String name, String password, String email, String address, String phone, float dollar, float trust ) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.dollar = dollar;
		this.trust = trust;
	}
	
	public User(String name, String email, String address, String phone, float dollar, float trust, int itemid, String itemname, String itemimage, int categoryid ) {
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.dollar = dollar;
		this.trust = trust;
		this.itemid = itemid;
		this.itemname = itemname;
		this.itemimage = itemimage;
		this.categoryid = categoryid;
	}
}
