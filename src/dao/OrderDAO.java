package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.Product;
import service.OrderInterface;
import utility.ConnectionManager;

public class OrderDAO implements OrderInterface{
	static int status;
	static String sql;
	static ConnectionManager cm = new ConnectionManager();
	static List<Order> list1 = new ArrayList<Order>();
	int price;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
// inserting order in the table
	@SuppressWarnings("unused")
	public void purchase(Order order) throws Exception {
		ConnectionManager cm = new ConnectionManager();
		Connection con = cm.getConnection();
		System.out.println(order.getPid());
		String sql1 = "select * from PRODUCT where PID = ?";
		PreparedStatement stt = con.prepareStatement(sql1);
		stt.setInt(1,order.getPid());
		ResultSet rs = stt.executeQuery();
		if(rs.next()) { 
		price =order.getQuantity()*Integer.parseInt(rs.getString(5));
		String sql = "INSERT INTO SALES(ID,CUSTNAME,ADDRESS,PID,PRICE,QUANTITY)VALUES(id_sequence.nextval,?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, order.getCustname());
		st.setString(2, order.getAddress());
		st.setInt(3, order.getPid()); 
		st.setInt(4, price);
		st.setInt(5, order.getQuantity());
		int status = st.executeUpdate();
		if(status > 0) {
			System.out.println("");
			System.out.println("------ Order purchased successfully --------");
		}else {
			System.out.println("----- Failure -----");
		}
		con.close();
		}else {
			System.out.println("");
			System.out.println("--Product Id Does Not Exist!! Try Again--");
		}
	}
			
		
	//view respective order by typing name.
	public List<Order> MyOrders(String Custname) 
	{
		List<Order> list1 = new ArrayList<Order>();
		try 
		{
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			String sql = "Select * from SALES where CUSTNAME =?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, Custname);
			ResultSet rs = st.executeQuery();
			if(rs.next()==false)
			{
				System.out.println("Sorry!! YOUR CART IS EMPTY...!");
			}
			else
			{
				do{
					Order o = new Order();
					
					int Id = rs.getInt(1);
					String username = rs.getString(2);
					String address = rs.getString(3);
					int pid = rs.getInt(4);
					int price = rs.getInt(5);
					int quantity = rs.getInt(6);
					
					o.setId(Id);
					o.setCustname(username);
					o.setPid(pid);
					o.setAddress(address);
					o.setPrice(price);
					o.setQuantity(quantity);
					list1.add(o);
					
					}while(rs.next()); 
			}
			con.close();
		}
		catch (Exception e) {
			e.getMessage();
		}
		return list1;
	}

	
	//cancel order using the order id.
	public void deleteOrder(String Id)  {
		try {
			//System.out.println(Id);
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			String sql = "DELETE FROM SALES WHERE id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,Id);
			int stat = st.executeUpdate();
			if(stat > 0) {
				System.out.println("---- ORDER CANCELLED SUCCESSFULLY ----");
				con.close();
			}else {
				System.out.println("---- INVALID ORDER ID ----");
				con.close();
			}
		}catch (Exception e) 
		{
			e.getMessage();
		}	
	}
}