
package example;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginCIS extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	public String passwordinput;
	public String pwdmsg;
	
	public List<Category> categorylist = CategoryDAO.getAllCategories();
	
	public String execute() {
		
		System.out.println("Log-in start executing!!!");
		
		/* session check */
		//if(ActionContext.getContext().getSession().get("email") == null) {
			//return INPUT;
		//}
		
		String email = (String)ActionContext.getContext().getSession().get("email");
		System.out.println("**********" + email);
		User user = UserDAO.getUserByEmail(email);
		System.out.println(user.password);
		System.out.println(passwordinput);
		
		if(user.password.equals(passwordinput)){
			return SUCCESS;
		}
		else {
			pwdmsg = "Incorrect password";
			System.out.print(pwdmsg);
			return INPUT;
		}		
	}
}
