/**
 * 
 */
package example;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Siddharth
 *
 */
public class sendTempPassword extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	public static String emailinput;
	
	public String execute(){
		
		Boolean flag = true;
		Pattern p = Pattern.compile(".+@seas\\.upenn\\.edu");
	    Matcher m = p.matcher(emailinput);
	    flag = m.matches();
	      
		if (flag) {
			
			flag = sendPwdExecution(emailinput);
			if(flag) {
				return SUCCESS;
			}
			else {
				return NONE;
			}
		}
		else 
			return NONE;
	}
	
	/* vince */
	public static boolean sendPwdExecution(String email) {
	
		SendEmailToUser setu = new SendEmailToUser();
		String temp_password = createTempPwd();
		setu.sendEamil_from_user(email,
				"Web2Share Temporary Password Assistance Request",
				"\n\nHello: \n\nWelcome to Web2Share!\n\nYour temporary password is " + temp_password + "\n\n\n");

		/* insert user into table */
		User user = new User(null, temp_password, email, null, null, 10, 0);
		UserDAO.insert(user);

		return true;
	}
	
	/**
	 * Creates the temporary password
	 * for the new user.
	 * 
	 * @param null
	 * @return null
	 * 
	 */
	private static String createTempPwd() {
		Random random =  new Random();
        long r = random.nextLong();
        String random_string = Long.toHexString(r);
        System.out.println(random_string);
		return random_string;
	}
	
}
