package example;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteItemStart extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String email;
	public List<Item> owned_items;
	
	public String execute() {
		
		System.out.println("*************HAHA");
		
		String email = (String)ActionContext.getContext().getSession().get("email");
		/* owned items */
		owned_items = ItemDAO.getItemByOwner(email);
		
		return SUCCESS;
	}
}
