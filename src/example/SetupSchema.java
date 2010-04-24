
package example;

import java.util.Scanner;
import java.io.*;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SetupSchema extends ActionSupport{

    /* set up the database schema */
    public String execute ()  throws Exception{
	/* read the file */
	Scanner scanner;
	scanner = new Scanner(getClass().getResourceAsStream("/db-schema.txt"));
	    
	scanner.useDelimiter(";");

	while (scanner.hasNext()){
	    String contents = scanner.next();
	    if (contents.trim().equals("")) continue;
	    System.out.println("running query: " + contents);
	    System.out.println("connection string: " + database_conn.getConnectionUrl());
	    database_conn.update_table(contents);
	}
	return "success";
    }

}