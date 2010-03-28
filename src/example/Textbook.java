package example;

public class Textbook {
	public int textbookid;
	public String isbn = "";
	public String coverimage= ""; 
	public String title = "";
	public String titleLong = "";
	public String author = "";
	public String publisher ="";
	
	public Textbook() {}
	
	public Textbook(String isbn, String coverimage, String title, String titleLong, String author, String publisher)
	{
		this.isbn = isbn;
		this.coverimage = coverimage;
		this.title = title;
		this.titleLong = titleLong;
		this.author = author;
		this.publisher = publisher;
	}
	

}
