package example;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class EditProfile extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String email;
	private User user;
	public List<User> userlist;
	
	public String execute() {
		
		String email = (String)ActionContext.getContext().getSession().get("email");
		
		user = UserDAO.getUserByEmail(email);
		userlist = new ArrayList<User>();
		userlist.add(user);
		
		return SUCCESS;
	}
}
