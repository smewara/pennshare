/**
 * 
 */
package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;


/**
 * @author Siddharth
 * gsid at seas dot upenn dot edu
 */
public class database_conn {

	private static Connection con = null;
	private static Connection con1 = null;
	private static String username = "appdev2";
	private static String password = "appdev2";
	private static String url_connection = "jdbc:mysql://spam.seas.upenn.edu:3306/appdev2?user="+username+"&password="+password;
	private static Statement stmt, stmt1;
	private static ArrayList<ArrayList<String>> return_results = new ArrayList<ArrayList<String>>();
	private static ResultSet rs11 = null;
	
	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		establish_connection();

	}*/

	/*
	 * this function is just for the internal testing 
	 * of the class
	 * */
		/*public static void establish_connection() {
			// TODO Auto-generated method stub
			
			String str_query = "select itemid, name,price from items";
			String str1 = "insert into items (itemid, name, price, duration, notes, category, textbookid) values (41220, \"PayCheck\", 2, \"1 week\", \"HDTV format\", \"DVD\", 3213 )";
			ArrayList<ArrayList<String>> value = new ArrayList<ArrayList<String>>();

			update_table(str1);
			
			System.out.println(value);
			
		}*/
		
		/**
		 * Please do not give the Query as "Select * ---"
		 * Queries the DB
		 * 
		 * @param: SQL query which you want to execute
		 * 
		 * @return: ArrayList<ArrayList<String>>
		 * 
		 */
	//	@SuppressWarnings("unchecked")
		public static ArrayList<ArrayList<String>> query_database(String SQL_query){
			
			
			String s[] = SQL_query.split("from");
			String column_names[] = (s[0].substring(7)).split(",");
			String temp="";
			for(int i = 0; i < column_names.length ; i++) 
				column_names[i] = column_names[i].trim();
			
			
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = DriverManager.getConnection(url_connection);
				
				if(con!=null){
					System.out.println("Connection etablished to the mysql server");
					
					stmt = con.createStatement();
					
					ResultSet rs = stmt.executeQuery(SQL_query);
					while(rs.next()){
						ArrayList<String> child_list = new ArrayList<String>();
//						return_results.add(child_list);
//						child_list.clear();
						for(int ii = 0 ; ii < column_names.length; ii++){
							
							if (column_names[ii].contains("date")|| column_names[ii].contains("deadline")) {

								Date iii;
								try {
									iii = rs.getDate(column_names[ii]);
									temp = iii.toString();
								} catch (SQLException e) {
									// TODO: handle exception
									temp = "null";
								}
								
								child_list.add(temp);

							}
							else if (column_names[ii].equalsIgnoreCase("dollar")
									|| column_names[ii].equalsIgnoreCase("trust")
									|| column_names[ii].equalsIgnoreCase("price")) {

								try {
									temp = Float.toString(rs
											.getFloat(column_names[ii]));
								} catch (SQLException e) {
									// TODO: handle exception
									temp = "null";
								}
								child_list.add(temp);

							} else {
								try {
									temp = rs.getString(column_names[ii]);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									temp = "null";
								}
								child_list.add(temp);
							}
								
						}
						return_results.add(child_list);
					}
					rs.close();
					stmt.close();
				}					
				else
					System.out.println("Connection failed . . . !!");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return return_results;
		}
		
		/**
		 * inserts or updates MySQL table
		 * 
		 * @param SQL query
		 * 
		 * @return last_insert_id
		 * 
		 */
		public static int update_table(String query){
			
			int last_insert_id = -1;
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = DriverManager.getConnection(url_connection);
				
				if(con!=null){
					System.out.println("Connection etablished to the mysql server");
					
					stmt = con.createStatement();
					
					stmt.executeUpdate(query);
					
					ResultSet rs = stmt.executeQuery("select last_insert_id()");
					while(rs.next()) {
						last_insert_id = rs.getInt("LAST_INSERT_ID()");
					}
					
					stmt.close();
				}					
				else
					System.out.println("Connection failed . . . !!");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return last_insert_id;
			
		}
		/**
		 * inserts or updates MySQL table
		 * 
		 * @param SQL query
		 * 
		 * @return null
		 * 
		 */
		public static void update_table2(String query){
			
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = DriverManager.getConnection(url_connection);
				
				if(con!=null){
					System.out.println("Connection etablished to the mysql server");
					
					stmt = con.createStatement();
					
					stmt.executeUpdate(query);
					
					stmt.close();
				}					
				else
					System.out.println("Connection failed . . . !!");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

			
		}
		
		/**
		 * Returns the Result set itself.
		 * Use rs.next() method to access the values.
		 * 
		 * @param valid SQL query
		 * @return ResultSet
		 * 
		 * @warning: Please use method close_connections
		 * after the ResultSet is returned from this.
		 * 
		 */

		public static ResultSet executeQuery(String query) {
			
			
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con1 = DriverManager.getConnection(url_connection);
				
				if(con1 != null){

					stmt1 = con1.createStatement();	
					rs11 = stmt1.executeQuery(query);

				}					
				else
					System.out.println("Connection failed . . . !!");
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return rs11;
		}
		
		/**
		 * This closes the connection which
		 * was establsihed in the executeQuery class
		 * 
		 * @param null
		 * @return null
		 */
		public static void close_connections() {
			try {
				rs11.close();
				stmt1.close();
				con1.close();			
			}
			catch(Exception e) {
			}
		}
	}


