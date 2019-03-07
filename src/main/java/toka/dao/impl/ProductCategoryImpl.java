package toka.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import toka.dao.generic.AbstractDao;
import toka.dao.interfc.IProductCategory;
import toka.domain.ProductCategory;

public class ProductCategoryImpl extends AbstractDao<Long, ProductCategory> implements IProductCategory {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

	public ProductCategory saveProductCategory(ProductCategory productCategory) {
		return saveIntable(productCategory);
	}

	public List<ProductCategory> getListProductCategory() {
		return (List<ProductCategory>) (Object) getModelList();
	}

	public ProductCategory getProductCategoryById(int productCategoryId, String primaryKeyclomunName) {
		return (ProductCategory) getModelById(productCategoryId, primaryKeyclomunName);
	}

	public ProductCategory UpdateProductCategory(ProductCategory productCategory) {
		return updateIntable(productCategory);
	}

	public ProductCategory getProductCategoryWithQuery(String[] propertyName, Object[] value, String hqlStatement) {
		try {
			return (ProductCategory) getModelWithMyHQL(propertyName, value, hqlStatement);
		} catch (Exception ex) {
			LOGGER.info("getProductCategoryWithQuery  Query error ::::" + ex.getMessage());
		}
		return null;
	}

}
