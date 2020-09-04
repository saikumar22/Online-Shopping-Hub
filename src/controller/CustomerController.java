package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.OrderDAO;
import dao.ProductDAO;
import model.Order;
import model.Product;

public class CustomerController {
	private static final boolean String = false;
	static int choice;
	boolean flag = true;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static ProductDAO productdao = new ProductDAO();
	static Product product = new Product();
	static List<Product> plist = new ArrayList<Product>();
	static List<Order> list1 = new ArrayList<Order>();
	static Order order = new Order();
	static OrderDAO orderdao = new OrderDAO();

@SuppressWarnings("static-access")
public void customer() throws Exception {
	Scanner sc = new Scanner(System.in);
	while(flag) {
		System.out.println("   ");
		System.out.println("   ");
		System.out.println("******************************* WELCOME CUSTOMER! ******************************");
		System.out.println("1.View Products");
		System.out.println("2.Purchase Products");
		System.out.println("3.My Orders");
		System.out.println("4.Cancel Orders");
		System.out.println("5.Search Product");
		System.out.println("6.Exit");
		System.out.println("   ");
		System.out.println("Choose any of these:");
		choice = sc.nextInt();
		int pid;
		switch (choice) {

		case 1:
			plist = productdao.displayProducts();
			System.out.println("<<****************************************************************************************>>");
			System.out.println("                                  Products In Store                                   ");
			System.out.println("<<****************************************************************************************>>");

			if (plist != null) {
				System.out.println("ProductID         ProductName            Category              Brand             Price ");
				for(Product p: plist) {
					System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - -");
					System.out.println(p.getPid()+"      	"+p.getName()+"     		"+p.getCategory()+"     		 "+ p.getBrand()+"        	"+p.getPrice());
				}
			}
			// message if list is empty
			else {
				System.out.println("----------------------------------- Store is Empty !! -----------------------------------");
			}
			break;
		case 2:
			  System.out.println("---------------FILL THE FOLLOWING----------------------");
			  System.out.println("Please Enter Following Details:");
			  System.out.println("Enter your name:"); 
			  String name = br.readLine();
			  System.out.println("Enter your adress:");
			  String adress = br.readLine();
			  System.out.println("Enter the Required Quantity:");
			  int prod_quantity = Integer.parseInt(br.readLine());
			  System.out.println("Enter Product Id");
			  pid = Integer.parseInt(br.readLine());
			  
			  order.setCustname(name); 
			  order.setPid(pid);
			  order.setAddress(adress);
			  order.setQuantity(prod_quantity);
			  orderdao.purchase(order); 
			break;
		case 3:
			// searching order based on customer name
			System.out.println("Enter your name to view your Orders");
			String Cname = br.readLine();
			// Order list for specific customer order
			list1 = orderdao.MyOrders(Cname);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			System.out.println("                                    YOUR ORDERS                       ");
			System.out.println(">>=========================================================================================<<");
			// For each loop to iterate over orders
			if (list1!= null) {
				System.out.println("OrderID    Name        Address      Product ID       Price     Quantity");
				for (Order o : list1) {
					System.out.println("===========================================================================================");
					System.out.println(o.getId() + "         " + o.getCustname() + "     " + o.getAddress()+"     " + o.getPid()
					+ "        " + o.getPrice() + "         "+ o.getQuantity());
				}
				System.out.println(">>========================================================================================<<");
			}
			// message if empty cart
			else {
				System.out.println("------NO ORDERS PLACED------");
			}
			break;

		case 4:
			System.out.println("Enter the order id if you want to cancel the order:");
			String Id = br.readLine();
			orderdao.deleteOrder(Id);
			break;

		case 5:
			System.out.println("Enter the Product Name to be search");
			String name1 = br.readLine();
			productdao.searchProduct(name1);
			break;
			
		case 6:flag=false;
			System.out.println("***********************THANK YOU FOR VISITING  THE STORE***************************************");
			break;
		default:System.out.println("***********Invalid Choice CUSTOMER !*******");

		}

	}

}


}
		