/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.Transient;

/**
 *
 * @author Ngango
 */
@Entity
@Table(name = "ProductAssignment")
@NamedQuery(name = "ProductAssignment.findAll", query = "SELECT r FROM ProductAssignment r order by prodAssId desc")
public class ProductAssignment extends CommonDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "prodAssId")
	private int prodAssId;
	@Transient
	private String action;
	private Date assignDate;
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "branch")
	private Branch branch;
	@ManyToOne
	@JoinColumn(name = "product")
	private Product product;
	public int getProdAssId() {
		return prodAssId;
	}
	public void setProdAssId(int prodAssId) {
		this.prodAssId = prodAssId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Date getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	

}
