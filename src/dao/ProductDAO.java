package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import service.ProductInterface;
import utility.ConnectionManager;

public class ProductDAO implements ProductInterface {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public void  insert(Product product) {
			ConnectionManager cm = new ConnectionManager();
			Connection con;
			try {
				con = cm.getConnection();
				String sql = "INSERT INTO PRODUCT(pid, name, category, brand, price) VALUES(PId_sequence.nextval, ?, ?, ?, ?)";
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, product.getName());
				st.setString(2, product.getCategory());
				st.setString(3, product.getBrand());
				st.setInt(4, product.getPrice());
				int status = st.executeUpdate();
				if(status > 0) 
				{
					System.out.println(" Success ");
				}
				else 
				{
					System.out.println(" Fail ");
					con.close();
				}
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
//display all product details.
public List<Product> displayProducts() 
{	
	List<Product> list = new ArrayList<Product>();
	try 
	{
		ConnectionManager cm = new ConnectionManager();
		Connection con = cm.getConnection();
		String sql = "Select * from PRODUCT";
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		while(rs.next()) 
		{
			Product p = new Product();
			int Pid = rs.getInt(1);
			String productName = rs.getString(2);
			String category = rs.getString(3);
			String brand = rs.getString(4);
			int price = rs.getInt(5);
			
			p.setPid(Pid);
			p.setName(productName);
			p.setCategory(category);
			p.setBrand(brand);
			p.setPrice(price);
		
			list.add(p);
		}
		con.close();
	}
	catch (Exception e) 
	{
		e.getMessage();
	}
	return list;
}
// delete the product details using product id.
public void delete(int Pid) {
	try {
		ConnectionManager cm = new ConnectionManager();
		Connection con = cm.getConnection();
		String sql = "DELETE FROM PRODUCT WHERE pid=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, Pid);
		int status = ps.executeUpdate();	
	if(status > 0) {
			System.out.println("-----Product Deleted Successfully!!------");
			con.close();
		}else {
			System.out.println("------Product does not exist-------");
			con.close();
		}	
	}catch (Exception e) 
	{
		e.printStackTrace();
	}
}


// Update product details
public void updateProduct(Product product) {
	try 
	{
		ConnectionManager cm = new ConnectionManager();
		Connection con = cm.getConnection();
		String sql = "UPDATE PRODUCT SET name= ?, category= ?, brand= ? ,price= ? where pid = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, product.getName());
		st.setString(2, product.getCategory());
		st.setString(3, product.getBrand());
		st.setInt(4, product.getPrice());
		boolean rowUpdated = st.executeUpdate() > 0;
		if(rowUpdated)
		{
			System.out.println("---- Product Details Updated Successfully!! ----");
			con.close();
		}
		else
		{
			System.out.println("---- Could Not Update Product! Try Again! -----");
			con.close();
		}
	}
	catch (Exception e) 
	{
		e.getMessage();
	}
}

//Search products using product name.
public void searchProduct(String name) throws Exception{
	try {
	ConnectionManager cm = new ConnectionManager();
	Connection con = cm.getConnection();
	String sql = "select pid,name,category,brand,price from PRODUCT where name = '"+name+"'";
	PreparedStatement st = con.prepareStatement(sql);
	ResultSet rs = st.executeQuery();
	while(rs.next()) {
		if(rs.getString("name").equals (name)) {
			System.out.println("    ");
			System.out.println("***********************************************************************************");
			System.out.println("ProductID  ProductName   Category      Brand       Price ");
			System.out.println("************************************************************************************"); 
			System.out.println(rs.getInt("Pid")+"        "+rs.getString("name")+"         "+rs.getString("category")+"    "+ rs.getString("brand")+"     "+rs.getInt("price"));
			System.out.println("    ");
			}
		else {
			System.out.println("------ PRODUCT ID DOES NOT EXSIST ------  ");
			System.out.println("    ");					
			}
		}
	}catch (Exception e) 
	{
		e.getMessage();
	}
	}

}



	

