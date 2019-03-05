package toka.trading.dto;

import java.io.Serializable;
import toka.domain.UserCategory;

public class MenuGroupDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int menuGroupId;

	private String genericStatus;
	private String menuGroupName;
	private UserCategory userCategory;
	private String defaultGroupMenu;
	private String groupMenuCode;
	private String menuColor;
	private String iconImage;

	private boolean editable;
	private String action;

	public int getMenuGroupId() {
		return menuGroupId;
	}

	public void setMenuGroupId(int menuGroupId) {
		this.menuGroupId = menuGroupId;
	}

	public String getGenericStatus() {
		return genericStatus;
	}

	public void setGenericStatus(String genericStatus) {
		this.genericStatus = genericStatus;
	}

	public String getMenuGroupName() {
		return menuGroupName;
	}

	public void setMenuGroupName(String menuGroupName) {
		this.menuGroupName = menuGroupName;
	}

	public UserCategory getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(UserCategory userCategory) {
		this.userCategory = userCategory;
	}

	public String getDefaultGroupMenu() {
		return defaultGroupMenu;
	}

	public void setDefaultGroupMenu(String defaultGroupMenu) {
		this.defaultGroupMenu = defaultGroupMenu;
	}

	public String getGroupMenuCode() {
		return groupMenuCode;
	}

	public void setGroupMenuCode(String groupMenuCode) {
		this.groupMenuCode = groupMenuCode;
	}

	public String getMenuColor() {
		return menuColor;
	}

	public void setMenuColor(String menuColor) {
		this.menuColor = menuColor;
	}

	public String getIconImage() {
		return iconImage;
	}

	public void setIconImage(String iconImage) {
		this.iconImage = iconImage;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
