package toka.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "OrderProduct")
@NamedQuery(name = "OrderProduct.findAll", query = "SELECT r FROM OrderProduct r order by orderProductId desc")
public class OrderProduct extends CommonDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "orderProductId")
	private int orderProductId;

	@ManyToOne
	@JoinColumn(name = "productInfo")
	private ProductAssignment productInfo;

	@Column(name = "quantity")
	private String quantity;

	@Column(name = "status")
	private String status;
	
	@Column(name = "orderDate", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;

	@ManyToOne
	@JoinColumn(name = "customer")
	private Users customer;

	public int getOrderProductId() {
		return orderProductId;
	}

	public void setOrderProductId(int orderProductId) {
		this.orderProductId = orderProductId;
	}

	public ProductAssignment getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductAssignment productInfo) {
		this.productInfo = productInfo;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Users getCustomer() {
		return customer;
	}

	public void setCustomer(Users customer) {
		this.customer = customer;
	}

}
