package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class UserDAO {

	private static Connection con;;
	private static Statement stmt;
	private static ResultSet rs;
	
	public static User getUserByEmail(String email) {
		
		User user = null;
		String query = "select * from users where users.email = '" + email + "'";	
		
		database_setup(query);
		
		try {
			System.out.println(rs.getFetchSize());
			while(rs.next()) {
				user = new User();
				user.userid = rs.getInt("userid");
				user.name = rs.getString("name");
				user.password = rs.getString("password");
				user.email = rs.getString("email");
				user.address = rs.getString("address");
				user.phone = rs.getString("phone");
				user.dollar = rs.getFloat("dollar");
				user.trust = rs.getFloat("trust");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		database_close();
		
		return user;
	}
	
	public static void insert(User user) {
		String query = "insert into users (name, password, email, address, phone, dollar, trust) VALUES ('" +
				user.name + "','" + user.password + "','" + user.email + "','" + user.address + "','" 
				+ user.phone + "', " + user.dollar + ", " + user.trust + ")";
		database_conn.update_table(query);
		database_conn.close_connections();
	}
	
	public static ArrayList<ArrayList<String>> SearchOwnerByTextbookname(String textbookname) {
		
		//User user = null;
		ArrayList<String> child;// = new ArrayList<String>();
		ArrayList<ArrayList<String>> parent = new ArrayList<ArrayList<String>>();
		String query = "select users.name, users.email, users.address, users.phone, users.dollar, users.trust from users, items, userownsitem where userownsitem.itemid=items.itemid and userownsitem.userid=users.userid and items.isactive = 1 and items.name= '" + textbookname + "'";
		
		System.out.println("\n" + query + "\n");
		
		database_setup(query);
		try {
			while(rs.next()) {
//				user = new User();
				child = new ArrayList<String>();
				child.add(rs.getString("email"));
				child.add(rs.getString("address"));
				child.add(rs.getString("phone"));
				child.add(Float.toString(rs.getFloat("dollar")));
				child.add(Float.toString(rs.getFloat("trust")));
				parent.add(child);
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
		
		database_close();
		
		return parent;
	
	}
	
/*	public static void main(String[] args) {
		ResultSet rs = database_conn.executeQuery("select * from users where users.email = 'gsid@seas.upenn.edu'");
		try {
			while(rs.next()) {
				System.out.println(rs.getString("name"));
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
	}*/
	
	public static void database_setup(String query) {

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
	
	public static void database_close() {
		try {
			rs.close();
			stmt.close();
			con.close();			
		}
		catch(Exception e) {
		}
	}
}
