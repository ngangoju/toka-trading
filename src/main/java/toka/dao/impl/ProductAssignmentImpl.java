/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package toka.dao.impl;

import toka.dao.generic.AbstractDao;
import toka.dao.interfc.IProductAssignment;
import toka.dao.interfc.IUserCategory;
import toka.domain.Product;
import toka.domain.ProductAssignment;
import toka.domain.UserCategory;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Ngango
 */
public class ProductAssignmentImpl extends AbstractDao<Long, ProductAssignment> implements IProductAssignment {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

	@Override
	public ProductAssignment saveProductAssignment(ProductAssignment assignement) {
		
		return saveIntable(assignement);
	}

	@Override
	public List<ProductAssignment> getListProductAssignment() {
		return (List<ProductAssignment>) (Object) getModelList();
	}

	@Override
	public ProductAssignment UpdateProductAssignment(ProductAssignment assignement) {
		return updateIntable(assignement);
	}

	@Override
	public ProductAssignment getProductAssignmentById(int assignementId, String primaryKeyclomunName) {
		return (ProductAssignment) getModelById(assignementId, primaryKeyclomunName);
	}
	public ProductAssignment getProductWithQuery(String[] propertyName, Object[] value, String hqlStatement) {
		try {
			return (ProductAssignment) getModelWithMyHQL(propertyName, value, hqlStatement);
		} catch (Exception ex) {
			LOGGER.info("getProductWithQuery  Query error ::::" + ex.getMessage());
		}
		return null;
	}
}
