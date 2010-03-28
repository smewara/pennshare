package example;


import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.MessagingException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import example.SendEmailToUser;
import example.database_conn;

/**
 * Servlet implementation class forgotPassword
 */
public class forgotPassword extends ActionSupport  {
	private static final long serialVersionUID = 1L;

    public String pwdmsg;
	
	  public String execute() throws Exception{

		  String emailinput = (String)ActionContext.getContext().getSession().get("email");
		  String username = (String)ActionContext.getContext().getSession().get("username");
		  
		  String sql_query = "select password from users where email = \""+emailinput+"\"";
		  Boolean flag = true;
		  String passwrd="###";
		  ResultSet rs311 = database_conn.executeQuery(sql_query);
			
			try {
				while (rs311.next()) 
					passwrd = rs311.getString("password");
				
				if (!passwrd.equals("###")) {
					SendEmailToUser sendEmail = new SendEmailToUser();
					flag = sendEmail.sendEamil_from_user(emailinput,
							"Web2Share Password Assistance Request",
							"  \n\nHello, " + username + "!\n\n" + "Forgot your password? No Problem!\n\nHere it is: " + passwrd
									+ "\n\nWeb2Share Password Assistance\n\n  ");
				}
				else if(passwrd.equals("###")){
					flag = false;
				}
				rs311.close();
				database_conn.close_connections();
				
			}catch(NullPointerException e){
				return NONE;
			}
			catch(SQLException e){	
				return NONE;
			}
			catch (Exception e) {
				return NONE;
			}
			if(flag == false)
				return NONE;
			else {
				pwdmsg = "Your password has been sent to your email box";
				return SUCCESS;
			}
	  }
	 

}
