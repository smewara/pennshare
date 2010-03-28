package example;

import com.opensymphony.xwork2.ActionSupport;

public class RequestItem extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	public String requesteremail;
	public int itemid;
	
	public String message;
	
	public String execute() {
		
		if(ItemDAO.existUserRequestItem(requesteremail, itemid)) {
			message = "You already requested this item <img src=\"smile.png\" />";	
		}
		else {
			message = "Your request is successfully sent!";
			ItemDAO.insertUserRequestItem(requesteremail, itemid);
		}
		
		return SUCCESS;
		
	}
}
