package toka.dao.interfc;

import java.util.List;

import toka.domain.SoldProduct;

public interface ISoldProduct {

	public SoldProduct saveSoldProduct(SoldProduct soldProduct);

	public List<SoldProduct> getListSoldProduct();

	public SoldProduct UpdateSoldProduct(SoldProduct soldProduct);
	
	public SoldProduct getSoldProductById(int SoldProductId, String primaryKeyclomunName);

}
