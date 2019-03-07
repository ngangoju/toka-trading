package toka.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import toka.dao.generic.AbstractDao;
import toka.dao.interfc.ISoldProduct;
import toka.domain.SoldProduct;

public class SoldProductImpl extends AbstractDao<Long, SoldProduct> implements ISoldProduct {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

	public SoldProduct saveSoldProduct(SoldProduct soldProduct) {
		return saveIntable(soldProduct);
	}

	public List<SoldProduct> getListSoldProduct() {
		return (List<SoldProduct>) (Object) getModelList();
	}

	public SoldProduct getSoldProductById(int soldProductId, String primaryKeyclomunName) {
		return (SoldProduct) getModelById(soldProductId, primaryKeyclomunName);
	}

	public SoldProduct UpdateSoldProduct(SoldProduct soldProduct) {
		return updateIntable(soldProduct);
	}

	public SoldProduct getSoldProductWithQuery(String[] propertyName, Object[] value, String hqlStatement) {
		try {
			return (SoldProduct) getModelWithMyHQL(propertyName, value, hqlStatement);
		} catch (Exception ex) {
			LOGGER.info("getSoldProductWithQuery  Query error ::::" + ex.getMessage());
		}
		return null;
	}

}
