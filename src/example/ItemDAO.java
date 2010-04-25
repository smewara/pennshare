package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemDAO {
	
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static Connection con;;
	private static Statement stmt;
	private static ResultSet rs;
	
	public static int insertItem(Item item) {
		
	    String query = "insert into items (title, content, ownerid) values (" + database_conn.quoteString(item.title)  + "," + database_conn.quoteString(item.contents) + ", " + item.ownerid + ")";
		int itemid = database_conn.update_table(query);
		database_conn.close_connections();
		return itemid;
	}
	
	public static void insertUserOwnsItem(int itemid, String user_email) {
    
		String query = "insert into userownsitem (itemid, userid, createdate ) VALUES ('" +
			itemid + "', (select userid from users where email = '" + user_email + "'), '" + dateFormat.format(new Date()) + "')";
		database_conn.update_table(query);
		database_conn.close_connections();
	}
	
	public static boolean existUserRequestItem(String requesteremail, int itemid) {
		
		System.out.println("!!!!!!!" + requesteremail);
		System.out.println("!!!!!!!" + itemid);
		
		String query = "select * " + 
						"from userrequestsitem uri " + 
						"inner join users u on u.userid = uri.userid " +
						"where u.email = '" + requesteremail + "' and " +
						"uri.itemid = " + itemid;
		database_setup(query);
		
		boolean result = false;
		try {
			while(rs.next()) {
				System.out.println("!!!!!!!true????" );
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		database_close();
		return result;
	}
	
	public static void insertUserRequestItem(String requesteremail, int itemid) {
		
		String query = "insert into userrequestsitem (userid, itemid) values ((select userid from users where email = '" 
						+ requesteremail + "'), '" + itemid + "')";
		database_conn.update_table(query);
		database_conn.close_connections();
	}
	
	public static List<Item> getRequestingItems(String user_email) {
		
		List<Item> requestingitems = new ArrayList<Item>();
		
		String query = "select i.itemid, i.name, uri.status, owner.name ownername, owner.email owneremail " +
						"from userrequestsitem uri " +
						"inner join users u on u.userid = uri.userid " +
						"inner join items i on i.itemid = uri.itemid " +
						"inner join users owner on owner.userid = i.ownerid " +
						"where u.email = '" + user_email + "'";
		
		database_setup(query);
		
		try {
			while(rs.next()) {
				int itemid = rs.getInt("itemid");
				String itemname = rs.getString("name");
				String itemstatus = rs.getString("status");
				String ownername = rs.getString("ownername");
				String owneremail = rs.getString("owneremail");
				requestingitems.add(new Item(itemid, itemname, itemstatus, ownername, owneremail));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		database_close();
		
		return requestingitems;
	}
	
	public static List<Item> getItemByOwner(String email) {
		List<Item> owned_items = new ArrayList<Item>();
		ResultSet rs = database_conn.executeQuery("select i.title, i.itemid " + 
									"from items i " +
									"inner join users u on u.userid = i.ownerid " +
									"where u.email='" + email + "'");
		try {
			while(rs.next()) {
				Item temp = new Item(rs.getInt("itemid"), rs.getString("title"));
				owned_items.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		database_conn.close_connections();
		return owned_items;
	}
	
	public static void deleteItemByItemID(int itemid) {
		String query = "update items set isactive = 0 where itemid = " + itemid;
		database_conn.update_table(query);
		database_conn.close_connections();
	}
	
	public static void confirmRequest(int itemid, String requesteremail) {
		
		/* first change every item to "Obtained" */
		String query = "update userrequestsitem uri " +
						"set uri.status = 'Obtained By Others' " +
						"where uri.itemid = " + itemid;
		System.out.println(query);
		
		database_conn.update_table(query);
		database_conn.close_connections();
		
		/* then change the right one to "Confirmed" */
		query = "update userrequestsitem uri, users u " +
				"set uri.status = 'Confirmed' " +
				"where uri.itemid = " + itemid + " and " +
				"uri.userid = u.userid and u.email = '" + requesteremail + "'";

		database_conn.update_table(query);
		database_conn.close_connections();
		
		/* set item to inactive */
		query = "update items i " +
				"set i.isactive = 0 " +
				"where i.itemid = " + itemid;
		
		database_conn.update_table(query);
		database_conn.close_connections();
		System.out.println(query);
		
	}
	
	private static void database_setup(String query) {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url_connection = database_conn.getConnectionUrl();
			con = DriverManager.getConnection(url_connection);
			
			if(con != null){
				stmt = con.createStatement();	
				rs = stmt.executeQuery(query);
			}					
			else {
				System.out.println("Connection failed . . . !!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private static void database_close() {
		try {
			rs.close();
			stmt.close();
			con.close();			
		}
		catch(Exception e) {
		}
	}
	
}
