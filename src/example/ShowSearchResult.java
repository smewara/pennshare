/**
 * 
 */
package example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
 * @author Siddharth
 *
 */
public class ShowSearchResult extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	public String textbookname;
	public String requesteremail;
	
	public String[] textbooknameArray;
	public String namestring;
	public String itemname;
	public String keywordString = "";
	public int keywordIndex = 0;
	
	public List<Category> categorylist = CategoryDAO.getAllCategories();
	public List<Item> bookname = new ArrayList<Item>();
	public int categoryid;
	public int num=0;
	public String message;
	
	public int getNum() {
		return num;
	}
	public List<Item> getBookname()
	{
		return bookname;
	}
	public String execute() {
		
		//if(textbookname == null || textbookname.equals("")) {
		//	message = "This field is required";
		//	return INPUT;
		//}
		
		System.out.println("**************" + categoryid);
		
		/* put textbookname into session */
		ActionContext.getContext().getSession().put("search_keywords", textbookname);
		//ActionContext.getContext().getSession().put("categoryid", categoryid);
		
		/* requester email is current user */
		requesteremail = (String)ActionContext.getContext().getSession().get("email");
		
		textbookname = textbookname.trim();
		textbooknameArray = textbookname.split(" ");
		
		namestring = "(";
		for(int i =0; i < textbooknameArray.length; i++)
		{
			String word = textbooknameArray[i];
			if(!word.equalsIgnoreCase("of") & !word.equalsIgnoreCase("a") & !word.equalsIgnoreCase("the") & !word.equalsIgnoreCase("to") & !word.equalsIgnoreCase("and") & !word.equalsIgnoreCase("in") & !word.equalsIgnoreCase("its") & !word.equalsIgnoreCase("for") & !word.equalsIgnoreCase("with"))
			{
				if(i == textbooknameArray.length -1 ){
					namestring += word;
				} else {
					namestring += word + "|";
				}
				keywordIndex++;
			}
		}
		namestring += ")+";
		for(int i = 0; i< keywordIndex; i++)
		{
			keywordString += namestring + ".*";
		}
		System.out.println(namestring);
		System.out.println(keywordString);
		
		String query2 = "select title, createdate, content, ownerid " +
						"from items where items.title REGEXP '"+ keywordString +"' ";
		System.out.println(query2);
		
		ResultSet rs = database_conn.executeQuery(query2);
		try {
			while(rs.next()){
				String nametmp = rs.getString("items.title");
				String contentmp = rs.getString("items.content");
				Item tm = new Item(nametmp,contentmp);
				bookname.add(tm);
				num++;
			}
			
			database_conn.close_connections();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

}
