package toka.dao.interfc;

import java.util.List;
import toka.domain.OrderProductComment;

/**
 *
 * @author NGANGO
 */
public interface IOrderProductComment {
	public OrderProductComment saveOrderProductComment(OrderProductComment orderProductComment);

	public List<OrderProductComment> getListOrderProductComments();

	public OrderProductComment getOrderProductCommentById(int orderProductCommentId, String primaryKeyclomunName);

	public OrderProductComment UpdateOrderProductComment(OrderProductComment orderProductComment);
}
