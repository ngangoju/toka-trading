package toka.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Branch")
@NamedQuery(name = "Branch.findAll", query = "SELECT r FROM Branch r order by branchId desc")
public class Branch extends CommonDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "branchId")
	private int branchId;

	@Column(name = "branchName")
	private String branchName;
	
	@ManyToOne
	@JoinColumn(name = "districtLocation")
	private District  location;
	
	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public District getLocation() {
		return location;
	}

	public void setLocation(District location) {
		this.location = location;
	}

}
