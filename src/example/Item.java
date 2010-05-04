package example;



public class Item {

	public int itemid;
    public String contents;
    public String title;
    public int ownerid;

    @Deprecated
	public String name;
    @Deprecated public float price;
    @Deprecated public String picture;
	@Deprecated public String duration;
    @Deprecated public String description;
    @Deprecated public String category;
	
    @Deprecated public int categoryid;
    @Deprecated public String ownername;
    @Deprecated public String owneremail;
    @Deprecated public int createdate;
	
	//public List<User> requester;
    @Deprecated public String requester_name;
    @Deprecated public String requester_email;
	
    @Deprecated public String status;
    @Deprecated public int isbn;
    @Deprecated public int textbookid;
	
    public Item(String title, String contents, User user) {
	this.title = title;
	this.contents = contents;
	this.ownerid = user.userid;
    }

	public Item(String title, String contents, int itemid) {
		this.title = title;
		this.contents = contents;
		this.itemid = itemid;
	}

    public Item(int itemid, String title) {
	this.itemid = itemid;
	this.title = title;
    }

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
