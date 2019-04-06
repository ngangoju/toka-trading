package toka.trading.dto;

import java.io.Serializable;

import toka.domain.Documents;
import toka.domain.ProductCategory;
import toka.domain.Users;

public class ProductCatDetailsDto   implements Serializable {

	private static final long serialVersionUID = 1L;
	private int catid;
	private String productcategoryName;
	private boolean editable;
	private boolean notify;
	private String action;
	private String status;
	private String createdBy;
	private String categoryImage;
	private Documents documents;
	private ProductCategory prodCategory;
	private Users user;
	private int productCount;
	
	public Documents getDocuments() {
		return documents;
	}

	public void setDocuments(Documents documents) {
		this.documents = documents;
	}

	public ProductCategory getProdCategory() {
		return prodCategory;
	}

	public void setProdCategory(ProductCategory prodCategory) {
		this.prodCategory = prodCategory;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getCatid() {
		return catid;
	}

	public void setCatid(int catid) {
		this.catid = catid;
	}

	public String getProductcategoryName() {
		return productcategoryName;
	}

	public void setProductcategoryName(String productcategoryName) {
		this.productcategoryName = productcategoryName;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isNotify() {
		return notify;
	}

	public void setNotify(boolean notify) {
		this.notify = notify;
	}

	public String getCategoryImage() {
		return categoryImage;
	}

	public void setCategoryImage(String categoryImage) {
		this.categoryImage = categoryImage;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	
}
