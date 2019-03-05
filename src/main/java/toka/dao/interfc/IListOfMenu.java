package toka.dao.interfc;

import java.util.List;

import toka.domain.ListOfMenu;

public interface IListOfMenu {
	public ListOfMenu saveListOfMenu(ListOfMenu listOfMenu);

	public List<ListOfMenu> getListListOfMenus();

	public ListOfMenu getListOfMenuById(int listOfMenuId, String primaryKeyclomunName);

	public ListOfMenu updateListOfMenu(ListOfMenu listOfMenu);

}
