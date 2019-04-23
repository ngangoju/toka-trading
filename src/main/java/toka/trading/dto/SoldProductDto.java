package toka.trading.dto;

import java.io.Serializable;
import java.util.Date;
import toka.domain.Product;
import toka.domain.Users;

public class SoldProductDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private boolean editable;
	private int orderProductId;
	private int soldProductId;
	private Product product;
	private int quantity;
	private int soldquantity;
	private int orderquantity;
	private Date orderDate;
	private Date soldDate;
	private Users customer;
	private String sellingUnitPrice;
	private Double totalSales,totalPurchase;
	private String email;
	private String phone;
	private double purchaseUnitPrice;
	
	public int getOrderquantity() {
		return orderquantity;
	}
	public void setOrderquantity(int orderquantity) {
		this.orderquantity = orderquantity;
	}
	public Double getTotalPurchase() {
		return totalPurchase;
	}
	public void setTotalPurchase(Double totalPurchase) {
		this.totalPurchase = totalPurchase;
	}
	public double getPurchaseUnitPrice() {
		return purchaseUnitPrice;
	}
	public void setPurchaseUnitPrice(double purchaseUnitPrice) {
		this.purchaseUnitPrice = purchaseUnitPrice;
	}
	public int getSoldquantity() {
		return soldquantity;
	}
	public void setSoldquantity(int soldquantity) {
		this.soldquantity = soldquantity;
	}
	public String getSellingUnitPrice() {
		return sellingUnitPrice;
	}
	public void setSellingUnitPrice(String sellingUnitPrice) {
		this.sellingUnitPrice = sellingUnitPrice;
	}
	public Double getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(Double totalSales) {
		this.totalSales = totalSales;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public int getOrderProductId() {
		return orderProductId;
	}
	public void setOrderProductId(int orderProductId) {
		this.orderProductId = orderProductId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getSoldProductId() {
		return soldProductId;
	}
	public void setSoldProductId(int soldProductId) {
		this.soldProductId = soldProductId;
	}
	public Date getSoldDate() {
		return soldDate;
	}
	public void setSoldDate(Date soldDate) {
		this.soldDate = soldDate;
	}
	
	
}
