package example;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;

public class ShowItemProfile extends ActionSupport {
	
	/**
	 * 
	 */
	
	public List<Item> bookname = new ArrayList<Item>();
	public int itemid;

	public void setItemid(int itemid)
	{
		this.itemid = itemid;
	} 
	
	public String execute() {
	
		String query2 = "select * " +
				"from items where items.itemid = "+ itemid + "";
	
		System.out.println(query2);
		
		ResultSet rs = database_conn.executeQuery(query2);

		try {
			while(rs.next()){
				String nametmp = rs.getString("items.title");
				String contentmp = rs.getString("items.content");
				int id = rs.getInt("items.itemid");
				Item tm = new Item(nametmp,contentmp,id);
				bookname.add(tm);
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
