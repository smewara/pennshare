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

    public static byte[] getBytesFromFile(File file) throws IOException {
	InputStream is = new FileInputStream(file);
	
	// Get the size of the file
	long length = file.length();
	
	// You cannot create an array using a long type.
	// It needs to be an int type.
	// Before converting to an int type, check
	// to ensure that file is not larger than Integer.MAX_VALUE.
	if (length > Integer.MAX_VALUE) {
	    // File is too large
	}
	
	// Create the byte array to hold the data
	byte[] bytes = new byte[(int)length];
	
	// Read in the bytes
	int offset = 0;
	int numRead = 0;
	while (offset < bytes.length
	       && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	    offset += numRead;
	}
	
	// Ensure all the bytes have been read in
	if (offset < bytes.length) {
	    throw new IOException("Could not completely read file "+file.getName());
	}
	
	// Close the input stream and return bytes
	is.close();
	return bytes;
    }

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
		    contents = new String(getBytesFromFile(theFile), "ISO-8859-1");
		} catch (IOException e) {
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
