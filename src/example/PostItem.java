package example;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.*;
import java.util.*;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PostItem extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	public File theFile;
	public String title;
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

		if(title == null || title.trim().equals("") || theFile == null) {
			message = "This field is required";
			System.out.println ("Did not insert into db");
			return INPUT;
		}
		
		String user_email = (String)ActionContext.getContext().getSession().get("email");
		User user = UserDAO.getUserByEmail(user_email);
		

		String contents = "";

		try {
		    contents = (new Scanner (new FileInputStream (theFile))).useDelimiter("asfadfasfasfadsfasf-arbitrary-will-never-happen").next();
		} catch (FileNotFoundException e) {
		    System.out.println(""+ e);
		}

		Item item = new Item(title, contents, user);
		
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
