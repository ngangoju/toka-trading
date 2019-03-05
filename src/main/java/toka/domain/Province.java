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
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Emmanuel
 */
@Entity
@Table(name = "Province")
@NamedQuery(name = "Province.findAll", query = "SELECT r FROM Province r order by v desc")
public class Province implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "provenceId")
	private int provenceId;

	@Column(name = "code")
	private String code;

	@Column(name = "provenceName_rw")
	private String provenceName_rw;

	@Column(name = "provenceName_en")
	private String provenceName_en;

	@Column(name = "provenceName_fr")
	private String provenceName_fr;

	public int getProvenceId() {
		return provenceId;
	}

	public void setProvenceId(int provenceId) {
		this.provenceId = provenceId;
	}

	public String getProvenceName_rw() {
		return provenceName_rw;
	}

	public void setProvenceName_rw(String provenceName_rw) {
		this.provenceName_rw = provenceName_rw;
	}

	public String getProvenceName_en() {
		return provenceName_en;
	}

	public void setProvenceName_en(String provenceName_en) {
		this.provenceName_en = provenceName_en;
	}

	public String getProvenceName_fr() {
		return provenceName_fr;
	}

	public void setProvenceName_fr(String provenceName_fr) {
		this.provenceName_fr = provenceName_fr;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
