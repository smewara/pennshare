package example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PreLogin extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	public String emailinput;
	public String message;
	
	public String execute() {
		
		System.out.println("Pre-Log-in start executing!!!");
		
		if ( emailinput == null || emailinput.equals( "" )) {
			message = "This field is required";
			return NONE;
		}
		else{
		      // check whether the email address is "@seas.upenn.edu"
		      Pattern p = Pattern.compile(".+@seas\\.upenn\\.edu");
		      Matcher m = p.matcher(emailinput);
		      boolean matchFound = m.matches();
		      
			if(!matchFound) {
				message = "This website is only for Penn SEAS students";
				return NONE;
			}
			/* for valid seas email address */
			else {
				User user = UserDAO.getUserByEmail(emailinput);
				/* store email in session */
				ActionContext.getContext().getSession().put("email", emailinput);
				
				/* new user */
				if(user == null){
					
					System.out.println("NO SUCH USER!!!");
					
					/* send temp password */
					sendTempPassword.sendPwdExecution(emailinput);
					return INPUT;
				}
				/* existing user */
				else 
				{
					/* get name by email address */
					String username = user.name;
					ActionContext.getContext().getSession().put("username", username);
					message = "Welcome, " + username + " !";
					return SUCCESS;
				}
			}
		}
	}	
}
