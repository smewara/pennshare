package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;
import java.io.*;

public class TextbookDAO {
	static public int start = 0;
	
	private static Connection con;;
	private static Statement stmt;
	private static ResultSet rs;
	
	public static int insert(Textbook textbook) {
		String query = "insert into textbooks (isbn, coverimage, title, titleLong, author, publisher) VALUES ('"
				+ textbook.isbn + "','" + textbook.coverimage+ "','" + textbook.title + "','" 
				+ textbook.titleLong + "','" + textbook.author + "', '" + textbook.publisher + "')";
		System.out.print(query);
		int textbookid = database_conn.update_table(query);
		database_conn.close_connections();
		return textbookid;
	}
		
	static Textbook searchBookbyISBN(String isbn)
	{
		
		Textbook textbook = null;
		String query = "select * from textbooks where textbooks.isbn = '" + isbn + "'";	
		
		database_setup(query);
		
		try {
			System.out.println(rs.getFetchSize());
			while(rs.next()) {
				textbook = new Textbook();
				textbook.textbookid = rs.getInt("textbookid");
				textbook.isbn = rs.getString("isbn");
				textbook.coverimage = rs.getString("coverimage");
				textbook.title = rs.getString("title");
				textbook.titleLong = rs.getString("titleLong");
				textbook.author = rs.getString("author");
				textbook.publisher = rs.getString("publisher");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		database_close();
		return textbook;
	}
	
	
	static Textbook returnBookInfo(String isbn) throws Exception
	{
		Textbook book = searchBookbyISBN(isbn);
		//if textbook with the isbn exists in the database, then return it.  
		if(book != null)
		{
			return book;
			
		}
		/*
		  if textbook with the isbn does not exist in the database, then ask isbnDB for the book information
		  1) Parse the XML file containing book informations, 
		  2) save the information into textbook instance and return it 
		*/
		else
		{
			String isbnDB = "http://isbndb.com/api/books.xml?access_key=XZ31F4RL&index1=isbn&value1=" + isbn;
			URL yahoo = new URL(isbnDB);
	        URLConnection yc = yahoo.openConnection();
	        BufferedReader in = new BufferedReader(
	                                new InputStreamReader(
	                                yc.getInputStream()));
	        String inputLine;
	        Textbook textbook = new Textbook(); 
	        textbook.isbn = isbn;
	        textbook.coverimage = "http://images.amazon.com/images/P/"+ isbn + ".01._SCLZZZZZZZ_.jpg";
	        while ((inputLine = in.readLine()) != null){
	            System.out.println(inputLine);
	            inputLine = inputLine.replace("'", "#");
	            
	            if(inputLine.contains("TitleLong")){
	            	Pattern p = Pattern.compile("(?i)(<TitleLong.*?>)(.+?)(</TitleLong>)");
	        	    Matcher m = p.matcher(inputLine);
	        	    System.out.print(m.replaceAll("$0"));
	        	    System.out.print(m.replaceAll("$1"));
	        	    System.out.print(m.replaceAll("$2"));
	        	    if(m.matches() == true & m.replaceAll("$2")!=null){ textbook.titleLong = m.replaceAll("$2"); }
	            }else if(inputLine.contains("Title")){
	            	Pattern p = Pattern.compile("(?i)(<Title.*?>)(.+)(</Title>)");
	        	    Matcher m = p.matcher(inputLine);
	        	    if(m.matches() == true & m.replaceAll("$2")!=null){ textbook.title = m.replaceAll("$2"); }
	        	
	            }else if(inputLine.contains("AuthorsText")){
	            	Pattern p = Pattern.compile("(?i)(<AuthorsText.*?>)(.+)(</AuthorsText>)");
	        	    Matcher m = p.matcher(inputLine);
	        	    if(m.matches() == true & m.replaceAll("$2")!=null){ textbook.author = m.replaceAll("$2"); }
	        	    
	        	}else if(inputLine.contains("PublisherText")){
	            	Pattern p = Pattern.compile("(?i)(<PublisherText.*?>)(.+)</PublisherText>");
	        	    Matcher m = p.matcher(inputLine);
	        	    if(m.matches() == true & m.replaceAll("$2")!=null){ textbook.publisher= m.replaceAll("$2"); }
	            }else {
	            	start = 0;
	            }
	        }
	        in.close();
	        System.out.print(textbook.titleLong);
	        System.out.println(textbook.coverimage);
	        System.out.print(textbook.publisher);
	        System.out.print(textbook.author);
	        System.out.print(textbook.title);
	        
			return textbook;
		}
	}
	
	public static Textbook getTextbookByItemID(int itemid) {
		
		Textbook textbook = null;
		String query = "select * from textbooks, items where textbooks.textbookid = items.textbookid and items.itemid = " + itemid;	
		
		database_setup(query);
		
		try {
			System.out.println(rs.getFetchSize());
			while(rs.next()) {
				textbook = new Textbook();
				textbook.textbookid = rs.getInt("textbookid");
				textbook.isbn = rs.getString("isbn");
				textbook.coverimage = rs.getString("coverimage");
				textbook.title = rs.getString("title");
				textbook.titleLong = rs.getString("titleLong");
				textbook.author = rs.getString("author");
				textbook.publisher = rs.getString("publisher");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		database_close();
		return textbook;
	}
	
	public static boolean existISBN(String ISBN) {
		
		String query = "select * " + 
						"from textbooks t " + 
						"where t.isbn = " + ISBN;
		
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
