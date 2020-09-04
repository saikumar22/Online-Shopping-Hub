package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.OrderDAO;
import dao.ProductDAO;
import dao.UserDAO;
import model.Order;
import model.Product;
import model.User;
import controller.Main;

public class AdminController {
	static int choice ,quantity,price;
	int pid;
	boolean flag = true;
	static String pname,category,brand,name,email,password,mobile;
	int id;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Product p= new Product();
	static ProductDAO productdao = new ProductDAO();
	static UserDAO userdao = new UserDAO();
	static OrderDAO orderdao = new OrderDAO();
	static List<Product> plist = new ArrayList<Product>();
	static List<User> ulist = new ArrayList<User>();
	static User u = new User();
	static List<Order> orderList = new ArrayList<Order>();
	
	public void admin() throws Exception {
		Scanner sc = new Scanner(System.in);
		while(flag) {
		System.out.println("    ");
		System.out.println("    ");
		System.out.println(">>>-*-*-*-*-*-*-*-*-*-*-*-*-*-* THE ADMIN LIST *-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-<<<");
		System.out.println("    ");
		System.out.println("1. ADD PRODUCT");
		System.out.println("2. DELETE PRODUCT");
		System.out.println("3. DISPLAY ALL THE PRODUCTS");
		System.out.println("4. UPDATE PRODUCT DETAILS");
		System.out.println("5. SEARCH PRODUCT");
		System.out.println("6. DISPLAY CUSTOMER DETAILS");
		System.out.println("7. DISPLAY CUSTOMER ORDER DETAILS");
		System.out.println("8. DELETE CUSTOMER DETAILS");
		System.out.println("9. EXIT");
		System.out.println("    ");
		System.out.println("Choose the following:");
		choice = sc.nextInt();
		switch(choice) {
		case 1:
			System.out.println("Enter product name :");
			businesslogic.NameValidation nv = new businesslogic.NameValidation();
			String name = nv.name();
			System.out.println("Enter product category:");
			category = br.readLine();
			System.out.println("Enter product brand:");
			brand = br.readLine();
			System.out.println("Enter price :");
			price = Integer.parseInt(br.readLine());
			
			//setters
    		p.setName(name);
			p.setCategory(category);
			p.setBrand(brand);
			p.setPrice(price);
			productdao.insert(p);
			System.out.println("    ");
			System.out.println("-----Product Successfully Added to the Store!!------");
			break;
			
		case 2:
			System.out.println("Enter product ID to be deleted:");
			pid = Integer.parseInt(br.readLine());
			productdao.delete(pid);
			break;
			
		case 3:
			plist = productdao.displayProducts();
			System.out.println("*********************************************************************************************");
			System.out.println("                                  Products In Store                                   ");
			System.out.println("**********************************************************************************************");
			System.out.println("    ");
			
			if(plist!=null)
			{
				System.out.println("ProductID         ProductName            Category              Brand             Price ");
				for(Product p: plist) {
					System.out.println("**************************************************************************************************");
					System.out.println(p.getPid()+"      	"+p.getName()+"     		"+p.getCategory()+"     		 "+ p.getBrand()+"        	"+p.getPrice()  );
				}
			}
			//message if list is empty
			else {
				System.out.println("-----------Store is Empty!!------------");
			}
			break;
		
		case 4: 
			System.out.println("**************************************************************************************");
			System.out.println("                                  Update Products                                     ");
			System.out.println(">>==================================================================================<<");
			
			//getting product details to be updated
			System.out.println("Enter Product ID to update");
			pid = Integer.parseInt(br.readLine());
			System.out.println("Update Product name");
			name = br.readLine();
			System.out.println("Update Category");
			category = br.readLine();
			System.out.println("Update brand");
			brand = br.readLine();
			System.out.println("Update Price");
			price = Integer.parseInt(br.readLine());
			
			
			//setters to initialize product details
			p.setPid(pid);
			p.setName(name);
			p.setCategory(category);
			p.setBrand(brand);
			p.setPrice(price);

			//passing the product to be updated
			productdao.updateProduct(p);
			break;
			
		case 5:
			System.out.println("Enter the Product Name to be search:");
			String name1 = br.readLine();
			productdao.searchProduct(name1);
			break;
			
		case 6:
			ulist = userdao.displayUsers();
			System.out.println("***************************************************************************************************");
			System.out.println("                                 CUSTOMER DETAILS                                  ");
			System.out.println("***************************************************************************************************");
			System.out.println("    ");
			System.out.println("    ");
			if(ulist!=null)
			{
				System.out.println("ID        Name      	 Email      	Mobile      Password");
				for(User ul: ulist) {	
				System.out.println("**********************************************************************************************");
				System.out.println(ul.getid()+"     "+ul.getName()+"      "+ul.getEmail()+"     "+ul.getPassword()+"   "+ul.getMobile());
				}
			}
			//message if list is empty
			else {
				System.out.println("----------------NO CUSTOMERS!!------------------");
			}
			break;
	
		case 7:
			orderList = userdao.getAllOrders();		
			System.out.println("***********************************************************************************************");
			System.out.println("                                 Customer Order Details                        ");
			System.out.println(">>============================================================================================<<");
			
			if(orderList!=null)
			{
				System.out.println("OrderID   CustomerName  Product ID   Price  Quantity ");
				for(Order o: orderList) {
					System.out.println("=============================================================================================");
					System.out.println(o.getId()+"        "+o.getCustname()+"         "+o.getPid()+"        "+o.getPrice()+"      "+o.getQuantity());
				}
				System.out.println(">>============================================================================================<<");
			}
			//message if no orders placed
			else 
			{
				System.out.println("---------- No Orders Placed! ---------");
			}
			break;
		case 8:
			System.out.println("Enter the customer id to be deleted:");
			id = Integer.parseInt(br.readLine());
			userdao.delete(id);
			break;

		case 9:flag=false;
			System.out.println("*******Thank you for visiting the store*****************");
			break;
		default: System.out.println("***********Invalid Choice ADMIN !*******");
		}
		
		}
}

}
