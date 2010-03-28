package example;



public class Item {

	public int itemid;
	public String name;
	public float price;
	public String picture;
	public String duration;
	public String description;
	public String category;
	
	public int categoryid;
	public int ownerid;
	public String ownername;
	public String owneremail;
	public int createdate;
	
	//public List<User> requester;
	public String requester_name;
	public String requester_email;
	
	public String status;
	public int isbn;
	public int textbookid;
	
	public Item(String name, float price, String duration, String description, String category) {
		this.name = name;
		this.price = price;
		this.duration = duration;
		this.description = description;
		this.category = category;		
	}
	
	public Item(String name, int itemid) {
		this.name = name;
		this.itemid = itemid;
	}
	
	public Item(int itemid, String name, String itemstatus, String ownername, String owneremail) {
		this.itemid = itemid;
		this.name = name;
		this.status = itemstatus;
		this.ownername = ownername;
		this.owneremail = owneremail;
	}
	
	public Item(String name, int isbn, String picture, String duration, float price, String description) {
		this.name = name;
		this.isbn = isbn;
		this.picture = picture;
		this.duration = duration;
		this.price = price;
		this.description = description;

	}

	public Item(String name, int categoryid, int textbookid, int ownerid) {

		this.name = name;
		this.categoryid = categoryid;
		this.textbookid = textbookid;
		this.ownerid = ownerid;
	}
	
	public Item(int itemid, String name, String itemstatus, String requester_name, String requester_email, int dum) {
		this.itemid = itemid;
		this.name = name;
		this.status = itemstatus;
		this.requester_name = requester_name;
		this.requester_email = requester_email;
	}

}
