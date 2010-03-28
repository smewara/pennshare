
package example;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class join extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String PHILADELPHIA = "Philadelphia";
	private static final String PA = "PA";
	
	public String firstname;
	public String lastname;
	
	public String address_no;
	public String address_street;
	public String city = PHILADELPHIA;
	public String state = PA;
	public String zipcode;

	public String password;
	
	public String message;
	
	public List<Category> categorylist = CategoryDAO.getAllCategories();
	
	public String execute() {
		
		if(firstname == null || firstname.equals("") || 
			lastname == null || lastname.equals("") || 
			address_no == null || address_no.equals("") || 
			address_street == null || address_street.equals("") ||
			password == null || password.equals("")) {
			
			message = "* indicates required fields";
			return INPUT;
		}
		
		String email = (String)ActionContext.getContext().getSession().get("email");
		String username = firstname + " " + lastname;
		String address = address_no + " " + address_street + ", " + city + ", " + state + ", " + zipcode;
		
		/* update this user record */
		database_conn.update_table2("update users " +
									"set name = '" + username + "', " + 
									"address = '" + address + "'," +
									"password = '" + password + "' " + 
									"where email = '" + email + "'");
		/* put username in session */
		ActionContext.getContext().getSession().put("username", username);
		database_conn.close_connections();
		
		return SUCCESS;	
	}
}