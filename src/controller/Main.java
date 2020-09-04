package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import businesslogic.RegistrationProcess;
import businesslogic.Validation;
import model.LoginUser;
import model.User;

public class Main {
	static String name,email,mobile,password,id;
	static String custEmail,custPassword;
	static LoginUser loginuser = new LoginUser();
	static String username,pass;
	static boolean flag = true;
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		LocalDateTime mydateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");	
		System.out.println("******************************************************************************");
		System.out.println("-------------------   WELCOME TO ONLINE SHOPPING HUB   -----------------------");
		System.out.println("           Today's Date and Time is: "+ mydateObj.format(myFormatObj));
		System.out.println("*******************************************************************************");	
		int choice;
		 
		//display options to enter in the system
		while(flag) {
		System.out.println("---- WELCOME ----");
		System.out.println("Choose the following:");
		System.out.println("----------------------");
		System.out.println("1. SIGNUP");
		System.out.println("2. LOGIN");
		System.out.println("3. ADMIN");
		System.out.println("4. EXIT");
		System.out.println("Select the required option:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		 
		//creating objects for model and controller classes
		 RegistrationProcess reg = new RegistrationProcess();
		 User user = new User();
		 Validation valid = new Validation();
		 AdminController ac = new AdminController();
		 CustomerController customerFunction = new CustomerController();
		 
		 //fetching choice from user
		 choice = Integer.parseInt(br.readLine());
		
		switch(choice) {
		case 1:
			System.out.println("===============================NEW USER REGISTRATION===============================");
			 
			//new user registration
			System.out.println("Enter your name :");
			name = br.readLine();
			System.out.println("Enter your email id:");
			email = br.readLine();
			businesslogic.MobileValidation mv = new businesslogic.MobileValidation();
			String mobile = mv.mobile();
			System.out.println("Enter password:");
			password = br.readLine();
			 
			//new user registration
			user.setName(name);
			user.setEmail(email);
			user.setMobile(mobile);
			user.setPassword(password);		
			
			if(!reg.checksignUp(user)) {
				System.out.println();
				 System.out.println("=============================INVALID EMAIL or PASSWORD=============================");
				 System.out.println("Password must match the following constraints:");
				 System.out.println("1. Atleast one Uppercase letter");
				 System.out.println("2. Atleast one number");
				 System.out.println("3. Atleast one special character");
				 System.out.println("4. Length must be between 8 to 20 characters!");
				 System.out.println("===================================================================================");
			}
			
			//function call to register user in database
			else {
				reg.register(user);
				System.out.println("             ");
				System.out.println("-->>Registration Successfully>>");
			}
			break;
			
		case 2:
			 //login of existing users
			System.out.println("             ");
			System.out.println("**********************CUSTOMER LOGIN*******************************");
			
			//getting login credentials
			System.out.println( "Enter email id:");
			custEmail = br.readLine();
			System.out.println("Enter password:");
			custPassword = br.readLine();
			 
			//setters to assign values
			loginuser.setEmail(custEmail);
			loginuser.setPassword(custPassword);
			
			if(valid.checkUser(loginuser)==true) {
				System.out.println("             ");
				System.out.println("             ");
				System.out.println("-->>Login Successfully >>");
				  //function call to display customer operations
				customerFunction.customer();
				
			}else {
				System.out.println("             ");
				System.out.println("             ");
				 System.out.println("-------Invalid Credentials! Try Again----------");
			}
			break;
			
		case 3: 
			System.out.println("===============================  ADMIN LOGIN  ==================================");
			
			//getting admin credentials
			System.out.println("Enter username : ");
			username = br.readLine();
			System.out.println("Enter password:");
			password = br.readLine();
			 
			//setters to assign values
			loginuser.setEmail(username);
			loginuser.setPassword(password);
			if(valid.checkAdmin(loginuser)==true) {
				System.out.println("             ");
				System.out.println("->>> Admin Login Successfully!!! >>>");
				 
				//function call to display admin operations
				ac.admin();
			}else {
				System.out.println("<--Invalid Credentials! Try Again--<");
			}
			break;
		case 4:flag=false;
			System.out.println("******** Thank you for Visiting the Store **********");
			System.exit(0);
			break;
		default:
			System.out.println("------------Invalid Choice! Try Again-----------");			
		}
		}		
	}
}
