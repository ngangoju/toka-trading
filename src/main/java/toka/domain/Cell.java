package toka.domain;

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
@Table(name = "Cell")
@NamedQuery(name = "Cell.findAll", query = "SELECT r FROM Cell r order by v desc")
public class Cell {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "cellId")
	private int cellId;

	@Column(name = "Code")
	private String Code;

	@Column(name = "cellName_rw")
	private String cellName_rw;

	@Column(name = "cellName_fr")
	private String cellName_fr;

	@Column(name = "cellName_en")
	private String cellName_en;

	@ManyToOne
	@JoinColumn(name = "sector")
	private Sector sector;

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public int getCellId() {
		return cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getCellName_rw() {
		return cellName_rw;
	}

	public void setCellName_rw(String cellName_rw) {
		this.cellName_rw = cellName_rw;
	}

	public String getCellName_fr() {
		return cellName_fr;
	}

	public void setCellName_fr(String cellName_fr) {
		this.cellName_fr = cellName_fr;
	}

	public String getCellName_en() {
		return cellName_en;
	}

	public void setCellName_en(String cellName_en) {
		this.cellName_en = cellName_en;
	}

}
