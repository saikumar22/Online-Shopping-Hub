package businesslogic;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidation {
	public String name() {
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		if(isValidName(name))
			return name;
		else {
			System.out.println("\t *Please enter valid name");
			return name();
		}
	}
	public static boolean isValidName(String name){
		String regex = "[a-zA-Z ]*$";
		Pattern p = Pattern.compile(regex);  // Compile the ReGex
		// If the password is empty
		// return false
		if (name == null) {
			return false;
		}
		// Pattern class contains matcher() method to find matching between given name and regular expression.
		Matcher m = p.matcher(name);
		return m.matches();
	}

}
