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
@Table(name = "Sector")
@NamedQuery(name = "Sector.findAll", query = "SELECT r FROM Sector r order by v desc")
public class Sector implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "sectorId")
	private int sectorId;

	@Column(name = "code")
	private String code;

	@Column(name = "sectorName_en")
	private String sectorName_en;

	@Column(name = "sectorName_fr")
	private String sectorName_fr;

	@Column(name = "sectorName_rw")
	private String sectorName_rw;

	@ManyToOne
	@JoinColumn(name = "distric")
	private District distric;

	public int getSectorId() {
		return sectorId;
	}

	public void setSectorId(int sectorId) {
		this.sectorId = sectorId;
	}

	public District getDistric() {
		return distric;
	}

	public void setDistric(District distric) {
		this.distric = distric;
	}

	public String getSectorName_en() {
		return sectorName_en;
	}

	public void setSectorName_en(String sectorName_en) {
		this.sectorName_en = sectorName_en;
	}

	public String getSectorName_fr() {
		return sectorName_fr;
	}

	public void setSectorName_fr(String sectorName_fr) {
		this.sectorName_fr = sectorName_fr;
	}

	public String getSectorName_rw() {
		return sectorName_rw;
	}

	public void setSectorName_rw(String sectorName_rw) {
		this.sectorName_rw = sectorName_rw;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
