package toka.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import toka.dao.generic.AbstractDao;
import toka.dao.interfc.IPerishedProduct;
import toka.domain.PerishedProduct;

public class PerishedProductImpl extends AbstractDao<Long, PerishedProduct> implements IPerishedProduct {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

	public PerishedProduct savePerishedProduct(PerishedProduct perishedProduct) {
		return saveIntable(perishedProduct);
	}

	public List<PerishedProduct> getListPerishedProduct() {
		return (List<PerishedProduct>) (Object) getModelList();
	}

	public PerishedProduct getPerishedProductById(int perishedProductId, String primaryKeyclomunName) {
		return (PerishedProduct) getModelById(perishedProductId, primaryKeyclomunName);
	}

	public PerishedProduct UpdatePerishedProduct(PerishedProduct perishedProduct) {
		return updateIntable(perishedProduct);
	}

	public PerishedProduct getPerishedProductWithQuery(String[] propertyName, Object[] value, String hqlStatement) {
		try {
			return (PerishedProduct) getModelWithMyHQL(propertyName, value, hqlStatement);
		} catch (Exception ex) {
			LOGGER.info("getPerishedProductWithQuery  Query error ::::" + ex.getMessage());
		}
		return null;
	}

}
