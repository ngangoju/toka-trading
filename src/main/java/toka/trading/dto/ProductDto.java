package toka.trading.dto;

import java.io.Serializable;
import java.util.Date;
import toka.domain.Branch;
import toka.domain.ProductCategory;
public class ProductDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean editable;
	private int productId;
	private String productName;

	private String productImage;
	
	private ProductCategory productCategory;

	private Branch branch;

	private String purchaseUnitPrice;

	private String sellingUnitPrice;

	private Date ManufactDate;

	private Date ExpireDate;

	private String quantity;
	private String status;
	private String createdBy;
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
	public void setQuantity(String quantity) {
		this.quantity = quantity;
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
	
	public String getQuantity() {
		return quantity;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}	
	
}
