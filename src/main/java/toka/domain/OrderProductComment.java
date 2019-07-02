package toka.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OrderProductComment")
@NamedQuery(name = "OrderProductComment.findAll", query = "select r from OrderProductComment r order by v desc")
public class OrderProductComment extends CommonDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "commentOrdId")
	private long commentOrdId;
	@OneToOne
	@JoinColumn(name = "comment")
	private Comment comment;
	@ManyToOne
	@JoinColumn(name = "orderProduct")
	private OrderProduct orderProduct;

	public long getCommentOrdId() {
		return commentOrdId;
	}

	public void setCommentOrdId(long commentOrdId) {
		this.commentOrdId = commentOrdId;
	}

	public OrderProduct getOrderProduct() {
		return orderProduct;
	}

	public void setOrderProduct(OrderProduct orderProduct) {
		this.orderProduct = orderProduct;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

}
