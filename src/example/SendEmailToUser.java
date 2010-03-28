

package example;

/**
 * @author Siddharth
 * gsid at seas dot upenn dot edu
 * 
 */

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;




public class SendEmailToUser {
		
	String d_email = "cisrs.fall.2008@gmail.com";
	private final String d_password = "CISRSfall2008";
	private final String d_host = "smtp.gmail.com"; 
	private final String d_port = "465";	

		
	/*public static void main(String args[]){
		SendEmailToUser se = new SendEmailToUser();
		se.sendEamil_from_user("sid0404@gmail.com","Welcome to CIS Resource Sharing System","hey - this is second email");
	}*/
	
		
	/**
	  * 
	  * Sends email
	  * 
	  * @param String :email of the recipient
	  * @param String :subject of email
	  * @param String :message
	  * 
	  * @author Siddharth
	  * 
	  * */
	public boolean sendEamil_from_user(String user_email,String msg_subject, String m_text){
			
		Properties props = new Properties();			
			
		props.put("mail.smtp.user", d_email);			
		props.put("mail.smtp.host", d_host);		
		props.put("mail.smtp.port", d_port);		
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", d_port);		
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		try	{
		
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);
			MimeMessage msg = new MimeMessage(session);
			msg.setText(m_text);
			msg.setSubject(msg_subject);
			msg.setFrom(new InternetAddress(d_email));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(user_email));
			Transport.send(msg);
			
		}catch (Exception mex){
		
			return false;
			
		}
		
		return true;
	}
		
	
	/**
	  * this verifies the user name and the 
	  * password of the sender's email
	  * 
	  * @author Siddharth
	  * 
	  * @return verification
	  * */

	private class SMTPAuthenticator extends javax.mail.Authenticator {

		public PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(d_email, d_password);
		}	
	}	
}
