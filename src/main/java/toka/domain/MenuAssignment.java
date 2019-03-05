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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "MenuAssignment", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "defaultMenuUrl",
				"userCategory" }, name = "fk_of_defaultMenuUrl_and_userCategory"),
		@UniqueConstraint(columnNames = { "listOfMenu", "userCategory" }, name = "fk_of_listOfMenu_and_userCategory") }

)
@NamedQuery(name = "MenuAssignment.findAll", query = "select r from MenuAssignment r order by menuAssgnId desc")
public class MenuAssignment extends CommonDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "menuAssgnId")
	private int menuAssgnId;

	@ManyToOne
	@JoinColumn(name = "defaultMenuUrl")
	private ListOfMenu defaultMenuUrl;

	@ManyToOne
	@JoinColumn(name = "listOfMenu")
	private ListOfMenu listOfMenu;

	@ManyToOne
	@JoinColumn(name = "userCategory")
	private UserCategory userCategory;

	public int getMenuAssgnId() {
		return menuAssgnId;
	}

	public void setMenuAssgnId(int menuAssgnId) {
		this.menuAssgnId = menuAssgnId;
	}

	public ListOfMenu getListOfMenu() {
		return listOfMenu;
	}

	public void setListOfMenu(ListOfMenu listOfMenu) {
		this.listOfMenu = listOfMenu;
	}

	public ListOfMenu getDefaultMenuUrl() {
		return defaultMenuUrl;
	}

	public void setDefaultMenuUrl(ListOfMenu defaultMenuUrl) {
		this.defaultMenuUrl = defaultMenuUrl;
	}

	public UserCategory getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(UserCategory userCategory) {
		this.userCategory = userCategory;
	}

}
