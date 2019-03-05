package toka.dao.impl;

import java.util.List;
import toka.dao.generic.AbstractDao;
import toka.dao.interfc.IMenuAssignment;
import toka.domain.MenuAssignment;

public class MenuAssignmentImpl extends AbstractDao<Long, MenuAssignment> implements IMenuAssignment {

	public MenuAssignment saveMenuAssignment(MenuAssignment menuAssignment) {
		return saveIntable(menuAssignment);
	}

	public List<MenuAssignment> getListMenuAssignments() {
		return (List<MenuAssignment>) (Object) getModelList();
	}

	public MenuAssignment getMenuAssignmentById(int menuAssignmentId, String primaryKeyclomunName) {
		return (MenuAssignment) getModelById(menuAssignmentId, primaryKeyclomunName);
	}

	public MenuAssignment updateMenuAssignment(MenuAssignment menuAssignment) {
		return updateIntable(menuAssignment);
	}

}
