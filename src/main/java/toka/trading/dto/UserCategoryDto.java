package toka.trading.dto;

import java.io.Serializable;

public class UserCategoryDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private int userCatid;
	private String usercategoryName;
	private boolean editable;
	private boolean notify;
	private String action;
	private String status;

	public int getUserCatid() {
		return userCatid;
	}

	public void setUserCatid(int userCatid) {
		this.userCatid = userCatid;
	}

	public String getUsercategoryName() {
		return usercategoryName;
	}

	public void setUsercategoryName(String usercategoryName) {
		this.usercategoryName = usercategoryName;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isNotify() {
		return notify;
	}

	public void setNotify(boolean notify) {
		this.notify = notify;
	}

}
