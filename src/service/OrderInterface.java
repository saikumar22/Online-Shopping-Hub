package service;

import java.util.List;

import model.Order;

public interface OrderInterface {
	public void purchase(Order order) throws Exception;
	public List<Order> MyOrders(String name) ;
	public void deleteOrder(String orderId) ;

}
