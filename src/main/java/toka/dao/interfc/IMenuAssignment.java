package toka.dao.interfc;

import java.util.List;

import toka.domain.MenuAssignment;

public interface IMenuAssignment {
	public MenuAssignment saveMenuAssignment(MenuAssignment menuAssignment);

	public List<MenuAssignment> getListMenuAssignments();

	public MenuAssignment getMenuAssignmentById(int menuAssignmentId, String primaryKeyclomunName);

	public MenuAssignment updateMenuAssignment(MenuAssignment menuAssignment);
}
