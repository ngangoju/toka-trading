package toka.dao.interfc;

import java.util.List;

import toka.domain.PerishedProduct;

public interface IPerishedProduct {

	public PerishedProduct savePerishedProduct(PerishedProduct perishedProduct);

	public List<PerishedProduct> getListPerishedProduct();

	public PerishedProduct UpdatePerishedProduct(PerishedProduct perishedProduct);
	
	public PerishedProduct getPerishedProductById(int perishedProductId, String primaryKeyclomunName);

}
