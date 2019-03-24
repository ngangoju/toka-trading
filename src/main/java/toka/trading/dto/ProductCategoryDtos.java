package toka.trading.dto;

import java.io.Serializable;

public class ProductCategoryDtos   implements Serializable {

	private static final long serialVersionUID = 1L;
	private int catid;
	private String productcategoryName;
	private boolean editable;
	private boolean notify;
	private String action;
	private String status;
	private String createdBy;
	
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
}
