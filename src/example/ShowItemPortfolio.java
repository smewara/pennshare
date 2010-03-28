package example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class ShowItemPortfolio extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String email;
	public List<Item> owned_items;
	public List<Item> items_requested;
	public List<Item> requesting_items;
	
	public String execute() {
		
		System.out.println("\n" + email + "\n");
		
		/* owned items */
		owned_items = ItemDAO.getItemByOwner(email);
		
		/* items to lend */
		items_requested = new ArrayList<Item>();
		ResultSet rs = database_conn.executeQuery("select i.itemid, i.name itemname, uri.status, requester.name requestername, requester.email requesteremail " + 
									"from userrequestsitem uri " +
									"inner join items i on i.itemid = uri.itemid " +
									"inner join users owner on owner.userid = i.ownerid " +
									"inner join users requester on requester.userid = uri.userid  " +
									"where owner.email='" + email + "'");
		try {
			while(rs.next()) {
				//List<User> requester = new ArrayList<User>();
				int itemid = rs.getInt("itemid");
				String itemstatus = rs.getString("status");
				String requester_name = rs.getString("requestername");
				String requester_email = rs.getString("requesteremail");
				System.out.println(rs.getString("requestername")+"!!!!!!!!!");
				Item tempitem = new Item(itemid, rs.getString("itemname"), itemstatus, requester_name, requester_email, 0);
				items_requested.add(tempitem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		database_conn.close_connections();
		
		/* requesting items */
		requesting_items = ItemDAO.getRequestingItems(email);
		
		return SUCCESS;
		
	}
}
