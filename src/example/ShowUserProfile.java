
package example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class ShowUserProfile extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	public String email;
	/* TODO! only for struts tag purposes */
	public List<User> user;
	
	public String execute() {
		
		user = new ArrayList<User>();
		user.add(UserDAO.getUserByEmail(email));
		
		//System.out.println(items_requested.get(0).requester.get(0).name+"!!!!!!!!!");
		
		return SUCCESS;
	}
	

}
