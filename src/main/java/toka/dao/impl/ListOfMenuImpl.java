package toka.dao.impl;

import java.io.Serializable;
import java.util.List;

import toka.dao.generic.AbstractDao;
import toka.dao.interfc.IListOfMenu;
import toka.domain.ListOfMenu;

public class ListOfMenuImpl extends AbstractDao<Long, ListOfMenu> implements IListOfMenu, Serializable {
	private static final long serialVersionUID = 1L;

	public ListOfMenu saveListOfMenu(ListOfMenu listOfMenu) {
		return saveIntable(listOfMenu);
	}

	public ListOfMenu getListOfMenuById(int listOfMenuId, String primaryKeyclomunName) {
		return (ListOfMenu) getModelById(listOfMenuId, primaryKeyclomunName);
	}

	public List<ListOfMenu> getListListOfMenus() {
		return (List<ListOfMenu>) (Object) getModelList();
	}

	public ListOfMenu updateListOfMenu(ListOfMenu listOfMenu) {
		return updateIntable(listOfMenu);
	}

}
