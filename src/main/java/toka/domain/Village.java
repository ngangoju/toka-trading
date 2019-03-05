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
@Table(name = "Village")
@NamedQuery(name = "Village.findAll", query = "SELECT r FROM Village r order by v desc")
public class Village {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "villageId")
	private int villageId;

	@Column(name = "Code")
	private String Code;

	@Column(name = "villageName_rw")
	private String villageName_rw;

	@Column(name = "villageName_fr")
	private String villageName_fr;

	@Column(name = "villageName_en")
	private String villageName_en;

	@ManyToOne
	@JoinColumn(name = "cell")
	private Cell cell;

	public int getVillageId() {
		return villageId;
	}

	public void setVillageId(int villageId) {
		this.villageId = villageId;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getVillageName_rw() {
		return villageName_rw;
	}

	public void setVillageName_rw(String villageName_rw) {
		this.villageName_rw = villageName_rw;
	}

	public String getVillageName_fr() {
		return villageName_fr;
	}

	public void setVillageName_fr(String villageName_fr) {
		this.villageName_fr = villageName_fr;
	}

	public String getVillageName_en() {
		return villageName_en;
	}

	public void setVillageName_en(String villageName_en) {
		this.villageName_en = villageName_en;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

}
