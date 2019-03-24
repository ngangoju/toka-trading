package com.toka.trading;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import toka.common.DbConstant;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Timestamp;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import toka.common.JSFBoundleProvider;
import toka.common.JSFMessagers;
import toka.common.SessionUtils;
import toka.dao.impl.ListOfMenuImpl;
import toka.dao.impl.MenuGroupImpl;
import toka.dao.impl.UserCategoryImpl;
import toka.domain.ListOfMenu;
import toka.domain.MenuGroup;
import toka.domain.UserCategory;
import toka.domain.Users;
import toka.trading.dto.ListOfMenuDto;
import toka.trading.dto.MenuGroupDto;;

@SuppressWarnings("unused")
@ManagedBean
@ViewScoped
public class ListOfMenuController implements Serializable, DbConstant {

	/**
	 * 
	 */
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
	private String CLASSNAME = "ListOfMenuController :: ";
	private static final long serialVersionUID = 1L;

	/* to manage validation messages */
	private boolean isValid;
	/* end manage validation messages */
	private String genericStatus;
	private String description;
	private String urlName;
	private String urlCode;
	private MenuGroup menuGroup;
	private ListOfMenu listOfMenu;
	private ListOfMenuDto listOfMenuDto;
	private Users userSessions;
	private Users users;
	private int groupId;

	Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());

	private List<ListOfMenu> listOfMenuDetails = new ArrayList<ListOfMenu>();
	private List<MenuGroup> menuGroupDetails = new ArrayList<MenuGroup>();

	private String[] menuColor = { DARKBLUE2, GREEN2, BLUE2, ORANGE2, RED2, PURPLE2, DARKGRAY2, GRAY2, LIGHTGRAY2,
			YELLOW2 };

	private String[] menuIcon = { GLYPHICONGLYPHICONASTERISK, GLYPHICONGLYPHICONPLUS, GLYPHICONGLYPHICONMINUS,
			GLYPHICONGLYPHICONEURO, GLYPHICONGLYPHICONCLOUD, GLYPHICONGLYPHICONENVELOPE, GLYPHICONGLYPHICONPENCIL,
			GLYPHICONGLYPHICONGLASS, GLYPHICONGLYPHICONMUSIC, GLYPHICONGLYPHICONSEARCH, GLYPHICONGLYPHICONHEART,
			GLYPHICONGLYPHICONSTAR, GLYPHICONGLYPHICONSTAREMPTY, GLYPHICONGLYPHICONUSER, GLYPHICONGLYPHICONFILM,
			GLYPHICONGLYPHICONTHLARGE, GLYPHICONGLYPHICONTH, GLYPHICONGLYPHICONTHLIST, GLYPHICONGLYPHICONOK,
			GLYPHICONGLYPHICONREMOVE, GLYPHICONGLYPHICONZOOMIN, GLYPHICONGLYPHICONZOOMOUT, GLYPHICONGLYPHICONOFF,
			GLYPHICONGLYPHICONSIGNAL, GLYPHICONGLYPHICONCOG, GLYPHICONGLYPHICONTRASH, GLYPHICONGLYPHICONHOME,
			GLYPHICONGLYPHICONFILE, GLYPHICONGLYPHICONTIME, GLYPHICONGLYPHICONROAD, GLYPHICONGLYPHICONDOWNLOADALT,
			GLYPHICONGLYPHICONDOWNLOAD, GLYPHICONGLYPHICONUPLOAD, GLYPHICONGLYPHICONINBOX };

	private List<ListOfMenuDto> listOfMenuDtoDetails = new ArrayList<ListOfMenuDto>();

	JSFBoundleProvider provider = new JSFBoundleProvider();
	ListOfMenuImpl listOfMenuImpl = new ListOfMenuImpl();
	MenuGroupImpl menuGroupImpl = new MenuGroupImpl();

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		HttpSession session = SessionUtils.getSession();
		userSessions = (Users) session.getAttribute("userSession");

		if (listOfMenu == null) {
			listOfMenu = new ListOfMenu();
		}
		if (listOfMenuDto == null) {
			listOfMenuDto = new ListOfMenuDto();
		}
		if (menuGroup == null) {
			menuGroup = new MenuGroup();
		}

		try {

			// userCategoryDetails = userCategoryImpl.getGenericListWithHQLParameter(new
			// String[] { "genericStatus" },
			// new Object[] { ACTIVE }, "UserCategory", "userCatid desc");

			menuGroupDetails = menuGroupImpl.getListWithHQL(SELECT_MENUGROUP);// This is for saving
			listOfMenuDetails = listOfMenuImpl.getListWithHQL(SELECT_LISTOFMENU);// This is for View

			for (ListOfMenu listOfMenus : listOfMenuDetails) {
				ListOfMenuDto listOfMenuDtos = new ListOfMenuDto();
				listOfMenuDtos.setMenuId(listOfMenus.getMenuId());
				listOfMenuDtos.setEditable(false);
				listOfMenuDtos.setDescription(listOfMenus.getDescription());
				listOfMenuDtos.setUrlName(listOfMenus.getUrlName());
				listOfMenuDtos.setMenuGroup(listOfMenus.getMenuGroup());
				listOfMenuDtos.setIconImage(listOfMenus.getIconImage());
				listOfMenuDtos.setMenuColor(listOfMenus.getMenuColor());
				listOfMenuDtos.setGenericStatus(listOfMenus.getGenericStatus());
				if (listOfMenus.getGenericStatus().equals(ACTIVE)) {
					listOfMenuDtos.setAction(DESACTIVE);

				} else if (listOfMenus.getGenericStatus().equals(DESACTIVE)) {

					listOfMenuDtos.setAction(ACTIVE);
					listOfMenus.setGenericStatus(DESACTIVE);

				} else {
					listOfMenuDtos.setAction(DESACTIVE);
					listOfMenus.setGenericStatus(ACTIVE);

				}
				listOfMenuDtoDetails.add(listOfMenuDtos);

			}

		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}

	}

	public String saveListMenuInfo() throws IOException {
		String url = getContextPath();
		System.out.print("+++++++++++++++++:" + url + "/");
		try {

			listOfMenu.setCreatedBy(userSessions.getViewId());
			listOfMenu.setCrtdDtTime(timestamp);
			listOfMenu.setCreationDate(timestamp);
			listOfMenu.setGenericStatus(ACTIVE);
			listOfMenu.setUpdatedBy(userSessions.getViewId());
			listOfMenu.setCrtdDtTime(timestamp);
			listOfMenu.setUpDtTime(timestamp);
			listOfMenu.setMenuGroup(menuGroupImpl.getMenuGroupById(groupId, "menuGroupId"));
			listOfMenuImpl.saveListOfMenu(listOfMenu);

			JSFMessagers.resetMessages();
			setValid(true);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.user"));
			LOGGER.info(CLASSNAME + ":::List of Menu is saved");
			clearUserFuileds();
			return "";

		} catch (HibernateException ex) {
			LOGGER.info(CLASSNAME + ":::List of Menu is fail to be sved with HibernateException  error");
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(CLASSNAME + "" + ex.getMessage());
			ex.printStackTrace();
		}
		return "";
	}

	public String cancel(ListOfMenuDto list) {
		list.setEditable(false);
		// usersImpl.UpdateUsers(user);
		return null;

	}

	public String editAction(ListOfMenuDto list) {

		list.setEditable(true);
		// usersImpl.UpdateUsers(user);
		return null;
	}

	public String saveAction(ListOfMenuDto list) {
		LOGGER.info("update  saveAction method");

		if (list != null) {

			ListOfMenu lists = new ListOfMenu();
			lists = new ListOfMenu();
			lists = listOfMenuImpl.getListOfMenuById(list.getMenuId(), "menuId");

			LOGGER.info("here update sart for " + lists + " menuId " + lists.getMenuId());
			System.out.println(
					"++++++++++++++++++++++++++here update sart for " + lists + " menuGroupId " + lists.getMenuId());
			list.setEditable(false);
			lists.setDescription(list.getDescription());
			lists.setUrlName(list.getUrlName());
			lists.setMenuGroup(list.getMenuGroup());
			listOfMenuImpl.updateListOfMenu(lists);

			// return to current page
			return null;
		} else {
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.userprofile"));
			return null;
		}

	}

	public String newAction(ListOfMenuDto list) {
		LOGGER.info("update  saveAction method");
		// get all existing value but set "editable" to false
		ListOfMenu lists = new ListOfMenu();
		lists = new ListOfMenu();
		lists = listOfMenuImpl.getListOfMenuById(list.getMenuId(), "menuId");

		LOGGER.info("here update sart for " + lists + " menuGroupId " + lists.getMenuId());

		list.setEditable(false);
		lists.setDescription(list.getDescription());
		lists.setUrlName(list.getUrlName());
		lists.setMenuGroup(list.getMenuGroup());
		lists.setIconImage(list.getIconImage());
		lists.setMenuColor(list.getMenuColor());
		listOfMenuImpl.updateListOfMenu(lists);

		// return to current page
		return "/menu/ViewListOfMenu.xhtml?faces-redirect=true";

	}

	public String updateStatus(ListOfMenuDto list) {
		LOGGER.info("update  saveAction method");
		// get all existing value but set "editable" to false
		ListOfMenu lists = new ListOfMenu();
		lists = new ListOfMenu();
		lists = listOfMenuImpl.getListOfMenuById(list.getMenuId(), "menuId");

		LOGGER.info("here update sart for " + lists + " menuGroupId " + lists.getMenuId());

		if (list.getGenericStatus().equals(ACTIVE)) {

			lists.setGenericStatus(DESACTIVE);
			listOfMenuImpl.updateListOfMenu(lists);
		} else {

			lists.setGenericStatus(ACTIVE);
		}
		listOfMenuImpl.updateListOfMenu(lists);

		// return to current page
		return "/menu/ViewListOfMenu.xhtml?faces-redirect=true";

	}

	public String addNewListOfMenu() {

		return "/menu/listOfMenuForm.xhtml?faces-redirect=true";

	}

	private void clearUserFuileds() {
		listOfMenu = new ListOfMenu();
		listOfMenuDetails = null;
	}

	private String getContextPath() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		return request.getContextPath();
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getGenericStatus() {
		return genericStatus;
	}

	public void setGenericStatus(String genericStatus) {
		this.genericStatus = genericStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public String getUrlCode() {
		return urlCode;
	}

	public void setUrlCode(String urlCode) {
		this.urlCode = urlCode;
	}

	public MenuGroup getMenuGroup() {
		return menuGroup;
	}

	public void setMenuGroup(MenuGroup menuGroup) {
		this.menuGroup = menuGroup;
	}

	public ListOfMenu getListOfMenu() {
		return listOfMenu;
	}

	public void setListOfMenu(ListOfMenu listOfMenu) {
		this.listOfMenu = listOfMenu;
	}

	public Users getuserSessions() {
		return userSessions;
	}

	public void setuserSessions(Users userSessions) {
		this.userSessions = userSessions;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public List<ListOfMenu> getListOfMenuDetails() {
		return listOfMenuDetails;
	}

	public void setListOfMenuDetails(List<ListOfMenu> listOfMenuDetails) {
		this.listOfMenuDetails = listOfMenuDetails;
	}

	public List<MenuGroup> getMenuGroupDetails() {
		return menuGroupDetails;
	}

	public void setMenuGroupDetails(List<MenuGroup> menuGroupDetails) {
		this.menuGroupDetails = menuGroupDetails;
	}

	public JSFBoundleProvider getProvider() {
		return provider;
	}

	public void setProvider(JSFBoundleProvider provider) {
		this.provider = provider;
	}

	public ListOfMenuImpl getListOfMenuImpl() {
		return listOfMenuImpl;
	}

	public void setListOfMenuImpl(ListOfMenuImpl listOfMenuImpl) {
		this.listOfMenuImpl = listOfMenuImpl;
	}

	public MenuGroupImpl getMenuGroupImpl() {
		return menuGroupImpl;
	}

	public void setMenuGroupImpl(MenuGroupImpl menuGroupImpl) {
		this.menuGroupImpl = menuGroupImpl;
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	public String getCLASSNAME() {
		return CLASSNAME;
	}

	public void setCLASSNAME(String cLASSNAME) {
		CLASSNAME = cLASSNAME;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public ListOfMenuDto getListOfMenuDto() {
		return listOfMenuDto;
	}

	public void setListOfMenuDto(ListOfMenuDto listOfMenuDto) {
		this.listOfMenuDto = listOfMenuDto;
	}

	public List<ListOfMenuDto> getListOfMenuDtoDetails() {
		return listOfMenuDtoDetails;
	}

	public void setListOfMenuDtoDetails(List<ListOfMenuDto> listOfMenuDtoDetails) {
		this.listOfMenuDtoDetails = listOfMenuDtoDetails;
	}

	public String[] getMenuColor() {
		return menuColor;
	}

	public void setMenuColor(String[] menuColor) {
		this.menuColor = menuColor;
	}

	public String[] getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String[] menuIcon) {
		this.menuIcon = menuIcon;
	}

}
