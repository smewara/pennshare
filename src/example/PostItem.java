package example;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PostItem extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	public String textbookname;
	private Item item;
	private String user_email;
	public boolean successful = false;
	
	public String message;
	private Textbook textbook;
	public List<Textbook> textbooklist;
	
	public int categoryid;
	
	public List<Category> categorylist = CategoryDAO.getAllCategories();
	
	public String execute() {
		
		if(textbookname == null || textbookname.trim().equals("")) {
			message = "This field is required";
			return INPUT;
		}
		
		user_email = (String)ActionContext.getContext().getSession().get("email");
		User user = UserDAO.getUserByEmail(user_email);
		
		/* category: textbook */
		if(categoryid == 1) {
			if(!isISBN(textbookname)) {
				message = "Please input ISBN for textbooks";
				return INPUT;
			}
			
			textbook = TextbookDAO.searchBookbyISBN(textbookname);
			/* put into textbook table if not exists */
			if(textbook == null) {
				try {
					textbook = TextbookDAO.returnBookInfo(textbookname);
					if(textbook.title == null || textbook.title.equals("")) {
						message = "This textbook is not found. Please try another ISBN.";
						return INPUT;
					}
					int textbookid = TextbookDAO.insert(textbook);
					textbook.textbookid = textbookid;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			}
			
			/* manipulate textbook */
			textbooklist = new ArrayList<Textbook>();
			textbooklist.add(textbook);
			
			item = new Item(textbook.title, categoryid, textbook.textbookid, user.userid);
			
		}
		/* category: others */
		else {
			item = new Item(textbookname, categoryid, 0, user.userid);
		}
		
		/* insert into items table */	
		ItemDAO.insertItem(item);
		
		successful = true;

		return SUCCESS;
	}
	
	private boolean isISBN(String isbn) {
		
		if(isbn.length() < 10) {
			return false;
		}
		
		long isbn_long;
		try {
			isbn_long = Long.parseLong((isbn));
		}
		catch(NumberFormatException nfe) {
			return false;
		}
		if(isbn_long < 0) {
			return false;
		}
		
		return true;
	}
}
