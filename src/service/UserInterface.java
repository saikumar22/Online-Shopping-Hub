package service;

import java.util.List;
import model.Order;
import model.User;

public interface UserInterface {
	public void delete(int Id);
	public List<User> displayUsers();
	public List<Order> getAllOrders() ;

}
