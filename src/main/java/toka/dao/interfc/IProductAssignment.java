package toka.dao.interfc;

import java.util.List;

import toka.domain.Product;
import toka.domain.ProductAssignment;
import toka.domain.UserCategory;

public interface IProductAssignment {
	public ProductAssignment saveProductAssignment(ProductAssignment assignement);

	public List<ProductAssignment> getListProductAssignment();

	public ProductAssignment UpdateProductAssignment(ProductAssignment assignement);
	public ProductAssignment getProductAssignmentById(int assignementId, String primaryKeyclomunName);

}
