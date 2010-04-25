package example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class ShowItemPortfolio extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String email;
	public List<Item> owned_items;
	public List<Item> items_requested;
	public List<Item> requesting_items;
	
	public String execute() {
		
		System.out.println("\n" + email + "\n");
		
		/* owned items */
		owned_items = ItemDAO.getItemByOwner(email);
		
		return SUCCESS;
		
	}
}
