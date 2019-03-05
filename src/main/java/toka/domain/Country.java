package toka.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Country")
@NamedQuery(name = "Country.findAll", query = "SELECT r FROM Country r order by boardId desc")
public class Country {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "countryId")
	private int taskId;

	@Column(name = "code")
	private String code;

	@Column(name = "countryName_en")
	private String countryName_en;

	@Column(name = "countryName_fr")
	private String countryName_fr;

	@Column(name = "countryName_rw")
	private String countryName_rw;

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCountryName_en() {
		return countryName_en;
	}

	public void setCountryName_en(String countryName_en) {
		this.countryName_en = countryName_en;
	}

	public String getCountryName_fr() {
		return countryName_fr;
	}

	public void setCountryName_fr(String countryName_fr) {
		this.countryName_fr = countryName_fr;
	}

	public String getCountryName_rw() {
		return countryName_rw;
	}

	public void setCountryName_rw(String countryName_rw) {
		this.countryName_rw = countryName_rw;
	}

	@Override
	public String toString() {
		return countryName_en;
	}
	

}
