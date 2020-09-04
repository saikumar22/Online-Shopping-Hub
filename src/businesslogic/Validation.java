package businesslogic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.LoginUser;
import utility.ConnectionManager;

public class Validation  {

	public boolean checkUser(LoginUser loginuser) throws SQLException, Exception
	{	
		String mailid = loginuser.getEmail();
		String password = loginuser.getPassword();
		
		ConnectionManager con = new ConnectionManager();
		Statement st = ConnectionManager.getConnection().createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM NEWUSER");
		while(rs.next()) {
			if(mailid.equals(rs.getString("EMAIL")) && password.equals(rs.getString("PASSWORD"))) {
				ConnectionManager.getConnection().close();
				return true;
			}	
		}
		return false;
	}
	
public boolean checkAdmin(LoginUser loginuser) throws SQLException, Exception {
	String username = loginuser.getEmail();
	String password = loginuser.getPassword();
	ConnectionManager con = new ConnectionManager();
	Statement st = ConnectionManager.getConnection().createStatement();
	ResultSet rs = st.executeQuery("SELECT * FROM ADMIN");
	boolean b = false;
	while(rs.next()) {
		if(username.equals(rs.getString("EMAIL")) && password.equals(rs.getString("PASSWORD"))){
			ConnectionManager.getConnection().close();
			return true;
		}		
		else {
	ConnectionManager.getConnection().close();
	return false;
		}
	}
	return b;
}
}

