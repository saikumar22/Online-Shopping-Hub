package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.User;
import service.UserInterface;
import utility.ConnectionManager;

public class UserDAO implements UserInterface{
	static List<User>list = new ArrayList<User>();
	static List<Order>orderlist = new ArrayList<Order>();
	int pid;
	
// display all the customers who have registered.
	public List<User> displayUsers(){
	
		 List<User> userlist = new ArrayList<User>();
			try 
			{
				ConnectionManager cm = new ConnectionManager();
				Connection con = cm.getConnection();
				String sql = "Select * from NEWUSER";
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				
				while(rs.next()) {
					User user = new User();
					int id = rs.getInt(1);
					String userName = rs.getString(2);
					String email = rs.getString(3);
					String mobile = rs.getString(4);
					String password = rs.getString(5);
					
					user.setid(id);
					user.setName(userName);
					user.setEmail(email);
					user.setMobile(mobile);
					user.setPassword(password);
					userlist.add(user);
					
				}
				con.close();
			}
			catch(Exception e)
			{
				e.getMessage();
			}
			
			return userlist;
	}

// deletes the customer using their cutomer id.
	public void delete(int pid) {
		try {
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			String sql = "DELETE FROM NEWUSER WHERE userid =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,pid);
			int status = ps.executeUpdate();
			if(status > 0) {
				System.out.println("<<--------Customer Details Deleted Successfully!!----->>");
				con.close();
			}else {
				System.out.println("<<--------Customer does not exist---------->>");
				con.close();
			}
		}catch (Exception e) 
		{
			e.getMessage();
		}
		
	}

	
//displays all the orders by respective customers.
	public List<Order> getAllOrders() {
		List<Order> orderList = new ArrayList<Order>();
		try {
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			String sql = "select * from SALES";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Order o = new Order();
				int id = rs.getInt(1);
				String custname = rs.getString(2);
				String address = rs.getString(3);
				int pid = rs.getInt(4);
				int price = rs.getInt(5);
				int quantity = rs.getInt(6);
			
				o.setId(id);
				o.setCustname(custname);
				o.setPid(pid);
				o.setAddress(address);
				o.setPrice(price);
				o.setQuantity(quantity);
				orderList.add(o);
			}con.close();
			
		}catch (Exception e) 
		{
			e.getMessage();
		}
		return orderList;
	}	
}