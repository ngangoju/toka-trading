package toka.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ProductCategory")
@NamedQuery(name = "ProductCategory.findAll", query = "SELECT r FROM ProductCategory r order by productCatid desc")
public class ProductCategory extends CommonDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "productCatid")
	private int productCatid;
	@Column(name = "productCatName", unique = true)
	private String productCatName;
	@Transient
	private String action;
	public int getProductCatid() {
		return productCatid;
	}
	public void setProductCatid(int productCatid) {
		this.productCatid = productCatid;
	}
	public String getProductCatName() {
		return productCatName;
	}
	public void setProductCatName(String productCatName) {
		this.productCatName = productCatName;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
