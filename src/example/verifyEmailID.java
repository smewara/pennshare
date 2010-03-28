package example;


import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;


/**
 * Servlet implementation class verifyEmailID
 */
public class verifyEmailID extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	private static String emailinput="";
       
	private HttpServletRequest request;  
	private HttpServletResponse response;
	  
	  public String execute() throws Exception{
		  emailinput = request.getParameter("emailinput");
//		  PrintWriter output = response.getWriter();

		  
		  boolean flag = false;
		  ResultSet rs2 = database_conn.executeQuery("select email from users where email=\""+emailinput+"\";");
		  
		  while(rs2.next()){
			  flag = true;
		  }
		  
		  rs2.close();
		  database_conn.close_connections();
		
		  
		  if (flag==true)  
			  return SUCCESS;
			
		  else
			  return NONE;  
	  }
	  public void setServletRequest(HttpServletRequest request){
	    this.request = request;
	  }

	  public HttpServletRequest getServletRequest(){
	    return request;
	  }

	  public void setServletResponse(HttpServletResponse response){
	    this.response = response;
	  }

	  public HttpServletResponse getServletResponse(){
	    return response;
	  }

}
