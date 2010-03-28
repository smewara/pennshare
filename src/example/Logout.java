package example;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Logout extends ActionSupport {

	public String execute() {
		System.out.println("==================: \n"+ActionContext.getContext().getSession());
		((org.apache.struts2.dispatcher.SessionMap)
				ActionContext.getContext().getSession()).invalidate(); 
		System.out.println("ggggggggggggggggggg: \n"+ActionContext.getContext().getSession());
		return SUCCESS;
	}
}
