package example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class ShowTextbookInfo extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	public String textbookname;
	public List<Item> item = new ArrayList<Item>();
	
	public String execute() {
		
		
		System.out.println(textbookname+"hellotext");
		
		String query = "select items.name, textbooks.isbn, textbooks.picture, items.price, items.duration,items.notes from textbooks, " +
				"items where textbooks.textbookid = items.itemid and items.name='"+textbookname+"';";
		
		ResultSet rs = database_conn.executeQuery(query);
		
		try {
			while (rs.next()) {
				String name = rs.getString("name");
				int isbn = rs.getInt("isbn");
				String picture_url = rs.getString("picture");
				String duration = rs.getString("duration");
				Float price = rs.getFloat("price");
				String notes = rs.getString("notes");
				
				Item temp_item = new Item(name, isbn, picture_url, duration, price, notes);
				
				item.add(temp_item);
				
			}
			rs.close();
			database_conn.close_connections();
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
