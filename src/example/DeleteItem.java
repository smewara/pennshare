package example;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteItem extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean successful;
	public int itemid;
	
	public String execute() {
		successful = true;
		
		/* delete item */
		ItemDAO.deleteItemByItemID(itemid);
		
		return SUCCESS;
		
	}
}
