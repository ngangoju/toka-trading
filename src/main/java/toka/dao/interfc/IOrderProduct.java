package toka.dao.interfc;

import java.util.List;

import toka.domain.OrderProduct;

public interface IOrderProduct {

	public OrderProduct saveOrderProduct(OrderProduct orderProduct);

	public List<OrderProduct> getListOrderProduct();

	public OrderProduct UpdateOrderProduct(OrderProduct orderProduct);
	
	public OrderProduct getOrderProductById(int orderProductId, String primaryKeyclomunName);

}
