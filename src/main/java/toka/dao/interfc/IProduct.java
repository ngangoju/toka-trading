package toka.dao.interfc;

import java.util.List;

import toka.domain.Product;

public interface IProduct {

	public Product saveProduct(Product product);

	public List<Product> getListProduct();

	public Product UpdateProduct(Product product);
	
	public Product getProductById(int productId, String primaryKeyclomunName);

}
