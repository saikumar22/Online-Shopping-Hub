package businesslogic;

import java.util.Scanner;
import java.util.regex.Pattern;

public class MobileValidation {
	public String mobile(){
		System.out.println("Enter your mobile number:");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String mobile = sc.nextLine();
		if(isvalidMobile(mobile)) {
			return mobile;
		}
		else {
			System.out.println("\t *Please Enter Valid Mobile number");
			return mobile();
		}
	}
	public static boolean isvalidMobile(String mobile) {
		String mobileRegex ="^[789]\\d{9}$";
	
		Pattern pattern = Pattern.compile(mobileRegex);
		// If the number is empty
		// return false
		if(mobile == null) 
			return false;
		return pattern.matcher(mobile).matches();
	}
}
