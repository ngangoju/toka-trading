package toka.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import toka.dao.generic.AbstractDao;
import toka.dao.interfc.IOrderProduct;
import toka.domain.OrderProduct;

public class OrderProductImpl extends AbstractDao<Long, OrderProduct> implements IOrderProduct {
			private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

			public OrderProduct saveOrderProduct(OrderProduct orderProduct) {
				return saveIntable(orderProduct);
			}

			public List<OrderProduct> getListOrderProduct() {
				return (List<OrderProduct>) (Object) getModelList();
			}

			public OrderProduct getOrderProductById(int orderProductId, String primaryKeyclomunName) {
				return (OrderProduct) getModelById(orderProductId, primaryKeyclomunName);
			}

			public OrderProduct updateOrderProduct(OrderProduct orderProduct) {
				return updateIntable(orderProduct);
			}

			public OrderProduct getOrderProductWithQuery(String[] propertyName, Object[] value, String hqlStatement) {
				try {
					return (OrderProduct) getModelWithMyHQL(propertyName, value, hqlStatement);
				} catch (Exception ex) {
					LOGGER.info("getOrderProductWithQuery  Query error ::::" + ex.getMessage());
				}
				return null;
			}

}
