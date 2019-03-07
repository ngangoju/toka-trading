package toka.dao.interfc;

import java.util.List;

import toka.domain.ProductCategory;

public interface IProductCategory {

	public ProductCategory saveProductCategory(ProductCategory productCategory);

	public List<ProductCategory> getListProductCategory();

	public ProductCategory UpdateProductCategory(ProductCategory productCategory);
	
	public ProductCategory getProductCategoryById(int productCategoryId, String primaryKeyclomunName);

}
