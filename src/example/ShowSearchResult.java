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
	public List<User> owners = new ArrayList<User>();
	public int numofowners;
	public String requesteremail;
	
	public String[] textbooknameArray;
	public String namestring;
	public String itemname;
	public String keywordString = "";
	public int keywordIndex = 0;
	
	public List<Category> categorylist = CategoryDAO.getAllCategories();
	
	public int categoryid;
	
	public String message;
	
	public String execute() {
		
		//if(textbookname == null || textbookname.equals("")) {
		//	message = "This field is required";
		//	return INPUT;
		//}
		
		System.out.println("**************" + categoryid);
		
		/* put textbookname into session */
		ActionContext.getContext().getSession().put("search_keywords", textbookname);
		ActionContext.getContext().getSession().put("categoryid", categoryid);
		
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
		
		String query2 = "select users.name, users.email, users.address, users.phone, " +
						"users.dollar, users.trust, items.itemid, items.name, items.categoryid, textbooks.coverimage from users, items, textbooks where " +
						"textbooks.textbookid = items.textbookid and " + 
						"items.isactive = 1 and items.categoryid = " + categoryid + " and " +
						"items.ownerid =users.userid and " + 
						"users.email <> '" + requesteremail + "' and " + 
						"items.name REGEXP '"+keywordString+"' " +
						"order by users.name";

		System.out.println(query2);
		
		ResultSet rs = database_conn.executeQuery(query2);
		try {
			while(rs.next()){
				String email = rs.getString("email");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				float dollar = rs.getFloat("dollar");
				float trust = rs.getFloat("trust");
				int itemid = rs.getInt("itemid");
				String itemname = rs.getString("items.name"); 
				String itemimage = rs.getString("coverimage");
				int categoryid = rs.getInt("categoryid");
				User temp_user = new User(name, email, address, phone, dollar, trust, itemid, itemname, itemimage, categoryid);
				owners.add(temp_user);
			}
			
			// DEBUG
			if(owners.size() > 10) {
				owners = owners.subList(0, 10);
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
		
		numofowners = owners.size();
		
		return SUCCESS;
	}

}
