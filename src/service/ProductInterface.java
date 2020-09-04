package service;

import java.sql.SQLException;
import java.util.List;
import model.Order;
import model.Product;

public interface ProductInterface {
	public List<Product> displayProducts() ;
	public void insert(Product product);
	public void delete(int pid);
	public void searchProduct(String productId) throws SQLException, Exception;
	public void updateProduct(Product p);

}
