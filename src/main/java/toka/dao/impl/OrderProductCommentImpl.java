package toka.dao.impl;

import java.util.List;

import toka.dao.generic.AbstractDao;
import toka.dao.interfc.IOrderProductComment;
import toka.domain.OrderProductComment;

/**
 *
 * @author NGANGO
 */
public class OrderProductCommentImpl extends AbstractDao<Long, OrderProductComment> implements IOrderProductComment {

	public OrderProductComment saveOrderProductComment(OrderProductComment orderProductComment) {
		return saveIntable(orderProductComment);
	}

	public OrderProductComment getOrderProductCommentById(int activityCommentId, String primaryKeyclomunName) {
		return (OrderProductComment) getModelById(activityCommentId, primaryKeyclomunName);
	}

	public OrderProductComment UpdateOrderProductComment(OrderProductComment orderProductComment) {
		return updateIntable(orderProductComment);
	}

	public List<OrderProductComment> getListOrderProductComments() {
		return (List<OrderProductComment>) (Object) getModelList();
	}

}
