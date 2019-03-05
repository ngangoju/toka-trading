package toka.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MenuGroup")
@NamedQuery(name = "MenuGroup.findAll", query = "select r from MenuGroup r order by menuGroupId desc")
public class MenuGroup extends CommonDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "menuGroupId")
	private int menuGroupId;

	@Column(name = "groupMenuCode", unique = true)
	private String groupMenuCode;

	@Column(name = "defaulGrouptMenu")
	private String defaulGrouptMenu;

	@Column(name = "menuGroupName")
	private String menuGroupName;

	@Column(name = "iconImage")
	private String iconImage;

	@Column(name = "menuColor")
	private String menuColor;

	@Column(name = "creationDate", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@ManyToOne
	@JoinColumn(name = "userCategory")
	private UserCategory userCategory;

	public int getMenuGroupId() {
		return menuGroupId;
	}

	public void setMenuGroupId(int menuGroupId) {
		this.menuGroupId = menuGroupId;
	}

	public String getDefaulGrouptMenu() {
		return defaulGrouptMenu;
	}

	public void setDefaulGrouptMenu(String defaulGrouptMenu) {
		this.defaulGrouptMenu = defaulGrouptMenu;
	}

	public String getMenuGroupName() {
		return menuGroupName;
	}

	public void setMenuGroupName(String menuGroupName) {
		this.menuGroupName = menuGroupName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public UserCategory getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(UserCategory userCategory) {
		this.userCategory = userCategory;
	}

	public String getGroupMenuCode() {
		return groupMenuCode;
	}

	public void setGroupMenuCode(String groupMenuCode) {
		this.groupMenuCode = groupMenuCode;
	}

	public String getIconImage() {
		return iconImage;
	}

	public void setIconImage(String iconImage) {
		this.iconImage = iconImage;
	}

	public String getMenuColor() {
		return menuColor;
	}

	public void setMenuColor(String menuColor) {
		this.menuColor = menuColor;
	}

}
