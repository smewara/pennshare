package example;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class Search extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	public List<Category> categorylist = CategoryDAO.getAllCategories();
	
	public String execute() {
		return SUCCESS;
	}
}
