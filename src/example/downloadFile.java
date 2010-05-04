
package example;

import java.util.List;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class downloadFile extends ActionSupport {

	public int itemId;
	InputStream inputStream;
	
	public void setItemId(int itemId)
	{
		this.itemId = itemId;
	}

	public InputStream getInputStream() throws Exception{
		return inputStream;
	}

	public String execute () throws SQLException {
		ResultSet rs = database_conn.executeQuery("select content from items where itemid=" + itemId);	
		if (!rs.next()) return "failure";	
		inputStream = new ByteArrayInputStream (rs.getBytes(1));	
		return "success";	
	}	
	
}
