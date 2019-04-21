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
import javax.persistence.Transient;

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
	@Column(name = "productImage")
	private String productImage;
	@ManyToOne
	@JoinColumn(name = "productCategory")
	private ProductCategory productCategory;

	@Column(name = "purchaseUnitPrice")
	private double purchaseUnitPrice;

	@Column(name = "sellingUnitPrice")
	private double sellingUnitPrice;

	@Column(name = "ManufactDate", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ManufactDate;

	@Column(name = "ExpireDate", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ExpireDate;

	@Column(name = "quantity")
	private double quantity;
	@Transient
	private Double totalPrice;
	@Transient
	private Double totalSales;
	
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
	public double getPurchaseUnitPrice() {
		return purchaseUnitPrice;
	}
	public void setPurchaseUnitPrice(double purchaseUnitPrice) {
		this.purchaseUnitPrice = purchaseUnitPrice;
	}
	public double getSellingUnitPrice() {
		return sellingUnitPrice;
	}
	public void setSellingUnitPrice(double sellingUnitPrice) {
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
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Double getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(Double totalSales) {
		this.totalSales = totalSales;
	}	
	
	
}
