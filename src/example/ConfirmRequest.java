package example;

import com.opensymphony.xwork2.ActionSupport;

public class ConfirmRequest extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int itemid;
	public String requesteremail;
	public String message;
	
	public String execute() {
		
		ItemDAO.confirmRequest(itemid, requesteremail);
		
		message = "Confirm Success!";
		
		return SUCCESS;
	}
	

}
