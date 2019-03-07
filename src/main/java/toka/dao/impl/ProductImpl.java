package toka.dao.impl;

import java.util.List;
import java.util.logging.Logger;
import toka.dao.generic.AbstractDao;
import toka.dao.interfc.IProduct;
import toka.domain.Product;

public class ProductImpl extends AbstractDao<Long, Product> implements IProduct {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

	public Product saveProduct(Product product) {
		return saveIntable(product);
	}

	public List<Product> getListProduct() {
		return (List<Product>) (Object) getModelList();
	}

	public Product getProductById(int productId, String primaryKeyclomunName) {
		return (Product) getModelById(productId, primaryKeyclomunName);
	}

	public Product UpdateProduct(Product product) {
		return updateIntable(product);
	}

	public Product getProductWithQuery(String[] propertyName, Object[] value, String hqlStatement) {
		try {
			return (Product) getModelWithMyHQL(propertyName, value, hqlStatement);
		} catch (Exception ex) {
			LOGGER.info("getProductWithQuery  Query error ::::" + ex.getMessage());
		}
		return null;
	}

}
