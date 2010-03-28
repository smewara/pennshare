package example;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ChangeProfile extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String name;
	public String address;
	public String password;
	
	public String message;
	public boolean successful = false;
	
	public String execute() {
		
		if(name == null || name.trim().equals("")) {
			message = "Name field is required";
			return INPUT;
		}
		
		if(address == null || address.trim().equals("")) {
			message = "Address field is required";
			return INPUT;
		}
		
		if(!password.equals("") && password.trim().equals("")) {
			message = "Password can't be just spaces";
			return INPUT;
		}
		
		String email = (String)ActionContext.getContext().getSession().get("email");
		String query = "update users " +
						"set name = '" + name + "', " + 
						"address = '" + address + "' ";
		if(password != null && !password.equals("")) {
			query += ", password = '" + password + "' ";
		}
		query += "where email = '" + email + "'";
		
		/* update this user record */
		database_conn.update_table2(query);
		database_conn.close_connections();
		
		message = "Changed Saved";
		successful = true;

		return SUCCESS;
	}

}
