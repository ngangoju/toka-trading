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
@Table(name = "Product")
@NamedQuery(name = "Product.findAll", query = "SELECT r FROM Product r order by productId desc")
public class Product extends CommonDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "productId")
	private int productId;
	
	@Column(name = "productName", unique = true)
	private String productName;

	@ManyToOne
	@JoinColumn(name = "productCategory")
	private ProductCategory productCategory;

	@ManyToOne
	@JoinColumn(name = "branch")
	private Branch branch;

	@Column(name = "purchaseUnitPrice")
	private String purchaseUnitPrice;

	@Column(name = "sellingUnitPrice")
	private String sellingUnitPrice;

	@Column(name = "ManufactDate", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ManufactDate;

	@Column(name = "ExpireDate", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ExpireDate;

	@Column(name = "quantity")
	private double quantity;

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public String getPurchaseUnitPrice() {
		return purchaseUnitPrice;
	}
	public void setPurchaseUnitPrice(String purchaseUnitPrice) {
		this.purchaseUnitPrice = purchaseUnitPrice;
	}
	public String getSellingUnitPrice() {
		return sellingUnitPrice;
	}
	public void setSellingUnitPrice(String sellingUnitPrice) {
		this.sellingUnitPrice = sellingUnitPrice;
	}
	public Date getManufactDate() {
		return ManufactDate;
	}
	public void setManufactDate(Date manufactDate) {
		ManufactDate = manufactDate;
	}
	public Date getExpireDate() {
		return ExpireDate;
	}
	public void setExpireDate(Date expireDate) {
		ExpireDate = expireDate;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
}
