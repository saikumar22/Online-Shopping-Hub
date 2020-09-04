package businesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.regex.Pattern;

import model.User;
import utility.ConnectionManager;

public class RegistrationProcess {
	
public boolean checksignUp(User user) {
	if(validEmail(user.getEmail()) && validPassword(user.getPassword())) {
		return true;
	}else {
		return false;
	}
}

//email validation
private boolean validEmail(String email) {
	String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
			+ "A-Z]{2,7}$";

	Pattern pat = Pattern.compile(emailRegex);
	if (email == null)
		return false;
	return pat.matcher(email).matches();
}
//password validation
private boolean validPassword(String password) {
	Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
	Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
	Pattern lowerCasePatten = Pattern.compile("[a-z ]");
	Pattern digitCasePatten = Pattern.compile("[0-9 ]");
	boolean flag = true;

	if (password.length() < 8 || password.length() > 20) {
		flag = false;
	}
	if (!specailCharPatten.matcher(password).find()) {
		flag = false;
	}
	if (!UpperCasePatten.matcher(password).find()) {
		flag = false;
	}
	if (!lowerCasePatten.matcher(password).find()) {
		flag = false;
	}
	if (!digitCasePatten.matcher(password).find()) {
		flag = false;
	}

	return flag;
}

//registration of new user
public void register(User user) throws Exception {
	ConnectionManager cm = new ConnectionManager();
	Connection con = cm.getConnection();
	
	String sql = "INSERT INTO NEWUSER (USERID,NAME,EMAIL,PASSWORD,MOBILE) VALUES (id_sequence.nextval,?,?,?,?)";
	PreparedStatement st = con.prepareStatement(sql);
	
	//setters
	st.setString(1, user.getName());
	st.setString(2, user.getEmail());
	st.setString(3, user.getPassword());
	st.setString(4, user.getMobile());
	
	//execute statement
	int status =st.executeUpdate();
	if(status > 0) {
		System.out.println("---------------------------------REGISTRATION SUCCESSFULL---------------------------------------------------");
	}else {
		System.out.println("---------------------------------ERROR OCCURED----------------------------------------------------------------");
	}
	con.close();
}



}

