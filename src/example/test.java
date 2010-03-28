package example;

import com.opensymphony.xwork2.ActionSupport;



public class test extends ActionSupport {
	private static final long serialVersionUID = 1L;
	public String execute() {
		Textbook textbook = new Textbook();
		try {
			//test case 1 
			String isbn = "1880685221"; //textbook whose information does not exist in the database
			textbook = TextbookDAO.returnBookInfo(isbn);
			TextbookDAO.insert(textbook);
			
			/* test case 2 */ 
/*			String isbn2 = "1880685221"; //textbook whose information exist in the database
			textbook = TextbookDAO.returnBookInfo(isbn2);
			System.out.print(textbook.title);
			System.out.print(textbook.coverimage);
			System.out.print(textbook.author);
			System.out.print(textbook.publisher);*/
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
}
