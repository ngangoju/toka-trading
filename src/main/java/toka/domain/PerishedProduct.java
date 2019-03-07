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
@Table(name = "PerishedProduct")
@NamedQuery(name = "PerishedProduct.findAll", query = "SELECT r FROM PerishedProduct r order by perishedId desc")
public class PerishedProduct extends CommonDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "perishedId")
	private int perishedId;

	@ManyToOne
	@JoinColumn(name = "product")
	private Product product;

	@Column(name = "quantity")
	private String quantity;

	@Column(name = "perichedDate", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date perichedDate;

	public int getPerishedId() {
		return perishedId;
	}

	public void setPerishedId(int perishedId) {
		this.perishedId = perishedId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Date getPerichedDate() {
		return perichedDate;
	}

	public void setPerichedDate(Date perichedDate) {
		this.perichedDate = perichedDate;
	}

}
