/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author Emmanuel
 */
@Entity
@Table(name = "District")
@NamedQuery(name = "District.findAll", query = "SELECT r FROM District r order by v desc")
public class District implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "districtId")
	private int districtId;

	@Column(name = "code")
	private String code;

	@Column(name = "districtName_en")
	private String districtName_en;

	@Column(name = "districtName_fr")
	private String districtName_fr;

	@Column(name = "districtName_rw")
	private String districtName_rw;

	@ManyToOne
	@JoinColumn(name = "province")
	private Province province;

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public String getDistrictName_en() {
		return districtName_en;
	}

	public void setDistrictName_en(String districtName_en) {
		this.districtName_en = districtName_en;
	}

	public String getDistrictName_fr() {
		return districtName_fr;
	}

	public void setDistrictName_fr(String districtName_fr) {
		this.districtName_fr = districtName_fr;
	}

	public String getDistrictName_rw() {
		return districtName_rw;
	}

	public void setDistrictName_rw(String districtName_rw) {
		this.districtName_rw = districtName_rw;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
