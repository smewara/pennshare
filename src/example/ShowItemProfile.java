package example;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class ShowItemProfile extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int itemid;
	public String itemname;
	private Textbook textbook;
	public List<Textbook> textbooklist;
	
	public String execute() {
		
		textbook = TextbookDAO.getTextbookByItemID(itemid);
		if(textbook.textbookid != 0) {
			textbooklist = new ArrayList<Textbook>();
			textbooklist.add(textbook);
		}
		
		return SUCCESS;
	}
}
