package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
	
	private static Connection con;;
	private static Statement stmt;
	private static ResultSet rs;
	
	public static List<Category> getAllCategories() {
		
		String query = "select categoryid, name from categories";
		database_setup(query);
		
		List<Category> categorylist = new ArrayList<Category>();
		
		try {
			while(rs.next()) {
				categorylist.add(new Category(rs.getInt("categoryid"), rs.getString("name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		database_close();
		
		return categorylist;
	}
	
	private static void database_setup(String query) {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url_connection = "jdbc:mysql://127.0.0.1:3306/appdev2?user=" + "appdev2" + "&password=" + "appdev2";
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
	
	@SuppressWarnings("unused")
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
