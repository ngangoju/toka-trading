package toka.dao.impl;

import java.util.List;

import toka.dao.generic.AbstractDao;
import toka.dao.interfc.IMenuGroup;
import toka.domain.MenuGroup;

public class MenuGroupImpl extends AbstractDao<Long, MenuGroup> implements IMenuGroup {

	public MenuGroup saveMenuGroupt(MenuGroup menuGroup) {
		return saveIntable(menuGroup);
	}

	public List<MenuGroup> getListMenuGroups() {
		return (List<MenuGroup>) (Object) getModelList();
	}

	public MenuGroup getMenuGroupById(int menuGroupId, String primaryKeyclomunName) {
		return (MenuGroup) getModelById(menuGroupId, primaryKeyclomunName);
	}

	public MenuGroup updateMenuGroup(MenuGroup menuGroup) {
		return updateIntable(menuGroup);
	}

}
