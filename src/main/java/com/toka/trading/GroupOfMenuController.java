package com.toka.trading;

import java.io.Serializable;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Timestamp;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import toka.common.DbConstant;
import toka.common.JSFBoundleProvider;
import toka.common.JSFMessagers;
import toka.common.SessionUtils;
import toka.dao.impl.ListOfMenuImpl;
import toka.dao.impl.MenuAssignmentImpl;
import toka.dao.impl.MenuGroupImpl;
import toka.dao.impl.UserCategoryImpl;
import toka.domain.ListOfMenu;
import toka.domain.MenuAssignment;
import toka.domain.MenuGroup;
import toka.domain.UserCategory;
import toka.domain.Users;
import toka.trading.dto.MenuGroupDto;
import toka.trading.dto.UserDto;

@SuppressWarnings("unused")
@ManagedBean
@ViewScoped
public class GroupOfMenuController implements Serializable, DbConstant {

	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
	private String CLASSNAME = "GroupOfMenuController :: ";
	private static final long serialVersionUID = 1L;

	/* to manage validation messages */
	private boolean isValid;
	/* end manage validation messages */
	private String genericStatus;
	private String defaulGrouptMenu;
	private String menuGroupName;
	private String menuGroupCode;
	private UserCategory userCategory;
	private Users userSessions;
	private MenuGroup menuGroup;
	private MenuGroup menuGroupSession;
	private MenuGroupDto menuGroupDto;
	private int categoryId;
	private int menuGroupId;

	private String defaultMenuUrl;
	private MenuAssignment menuAssignment;
	private ListOfMenu listOfMenu;
	private int menuAssignId;
	private int listOfMenuId;

	Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());

	private List<MenuGroup> menuGroupDetails = new ArrayList<MenuGroup>();
	private List<UserCategory> userCategoryDetails = new ArrayList<UserCategory>();

	private List<MenuAssignment> menuAssignmentDetails = new ArrayList<MenuAssignment>();
	private List<ListOfMenu> listOfMenuDetails = new ArrayList<ListOfMenu>();

	private List<MenuGroupDto> menuGroupDtoDetails = new ArrayList<MenuGroupDto>();

	JSFBoundleProvider provider = new JSFBoundleProvider();
	UserCategoryImpl userCategoryImpl = new UserCategoryImpl();
	MenuGroupImpl menuGroupImpl = new MenuGroupImpl();

	ListOfMenuImpl listOfMenuImpl = new ListOfMenuImpl();
	MenuAssignmentImpl menuAssignmentImpl = new MenuAssignmentImpl();

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		HttpSession session = SessionUtils.getSession();
		userSessions = (Users) session.getAttribute("userSession");

		menuGroupSession = (MenuGroup) session.getAttribute("menuGroupSession");

		if (menuGroup == null) {
			menuGroup = new MenuGroup();
		}
		if (menuAssignment == null) {
			menuAssignment = new MenuAssignment();
		}
		if (userCategory == null) {
			userCategory = new UserCategory();

		}
		if (menuGroupDto == null) {
			menuGroupDto = new MenuGroupDto();
		}

		try {
			userCategoryDetails = userCategoryImpl.getListWithHQL(SELECT_USERCATEGORY);
			// MenuGroup menu = new MenuGroup();
			// menu = new MenuGroup();
//			MenuGroup menu = menuGroupImpl.getMenuGroupById(menuGroupSession.getMenuGroupId(), "menuGroupId");
//			
//			MenuGroupDto menuGroupDto = new MenuGroupDto();
//			menuGroupDto.setEditable(false);
//			menuGroupDto.setMenuGroupName(menu.getMenuGroupName());
//			menuGroupDto.setDefaultGroupMenu(menu.getDefaulGrouptMenu());
//			menuGroupDto.setUserCategory(menu.getUserCategory());
//			menuGroupDtoDetails.add(menuGroupDto);
//			// below list concern list of all users by changing their status
			menuGroupDetails = menuGroupImpl.getListWithHQL(SELECT_MENUGROUP);

			userCategoryDetails = userCategoryImpl.getListWithHQL(SELECT_USERCATEGORY);

			for (MenuGroup menuGroups : menuGroupDetails) {
				MenuGroupDto menuGroupDtos = new MenuGroupDto();
				menuGroupDtos.setMenuGroupId(menuGroups.getMenuGroupId());
				menuGroupDtos.setEditable(false);
				menuGroupDtos.setMenuGroupName(menuGroups.getMenuGroupName());
				menuGroupDtos.setDefaultGroupMenu(menuGroups.getDefaulGrouptMenu());
				menuGroupDtos.setUserCategory(menuGroups.getUserCategory());
				menuGroupDtos.setGenericStatus(menuGroups.getGenericStatus());
				if (menuGroups.getGenericStatus().equals(ACTIVE)) {
					menuGroupDtos.setAction(DESACTIVE);

				} else if (menuGroups.getGenericStatus().equals(DESACTIVE)) {

					menuGroupDtos.setAction(ACTIVE);
					menuGroups.setGenericStatus(DESACTIVE);

				} else {
					menuGroupDtos.setAction(DESACTIVE);
					menuGroups.setGenericStatus(ACTIVE);

				}
				menuGroupDtoDetails.add(menuGroupDtos);

			}
		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}

		try {

			// userCategoryDetails = userCategoryImpl.getGenericListWithHQLParameter(new
			// String[] { "genericStatus" },
			// new Object[] { ACTIVE }, "UserCategory", "userCatid desc");
			userCategoryDetails = userCategoryImpl.getListUsercategory();
			listOfMenuDetails = listOfMenuImpl.getListListOfMenus();

		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}

	}

	public String saveMenuGroupInfo() throws IOException {
		String url = getContextPath();
		System.out.print("+++++++++++++++++:" + url
				+ userCategoryImpl.getUserCategoryById(categoryId, "userCatid").getUsercategoryName());
		try {

			MenuGroup menuG = new MenuGroup();

			menuG = menuGroupImpl.getModelWithMyHQL(new String[] { "groupMenuCode" },
					new Object[] { menuGroup.getGroupMenuCode() }, "from MenuGroup ");

			menuGroup.setCreatedBy(userSessions.getViewId());
			menuGroup.setCrtdDtTime(timestamp);
			menuGroup.setCreationDate(timestamp);
			menuGroup.setGenericStatus(ACTIVE);
			menuGroup.setUpdatedBy(userSessions.getViewId());
			menuGroup.setCrtdDtTime(timestamp);
			menuGroup.setUpDtTime(timestamp);

			menuGroup.setUserCategory(userCategoryImpl.getUserCategoryById(categoryId, "userCatid"));

			menuGroupImpl.saveMenuGroupt(menuGroup);

			JSFMessagers.resetMessages();
			setValid(true);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.user"));
			LOGGER.info(CLASSNAME + ":::MenuGroup is saved");
			clearUserFuileds();
			return "";

		} catch (Exception ex) {
			LOGGER.info(CLASSNAME + ":::MenuGroup is fail with HibernateException  error");
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(CLASSNAME + "" + ex.getMessage());
			ex.printStackTrace();
		}
		return "";
	}

	public String saveMenuAssignmentInfo() throws IOException {
		String url = getContextPath();
		System.out.print("+++++++++++++++++:" + url + "/");
		try {

			menuAssignment.setCreatedBy(userSessions.getViewId());
			menuAssignment.setCrtdDtTime(timestamp);
			menuAssignment.setGenericStatus(ACTIVE);
			menuAssignment.setUpdatedBy(userSessions.getViewId());
			menuAssignment.setCrtdDtTime(timestamp);
			menuAssignment.setUpDtTime(timestamp);
			menuAssignment.setListOfMenu(listOfMenuImpl.getListOfMenuById(listOfMenuId, "menuId"));
			menuAssignment.setUserCategory(userCategoryImpl.getUserCategoryById(categoryId, "userCatid"));
			menuAssignmentImpl.saveMenuAssignment(menuAssignment);

			JSFMessagers.resetMessages();
			setValid(true);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.user"));
			LOGGER.info(CLASSNAME + ":::MenuAssignment is saved");
			clearUserFuileds();
			return "";

		} catch (HibernateException ex) {
			LOGGER.info(CLASSNAME + ":::MenuAssignment is fail with HibernateException  error");
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(CLASSNAME + "" + ex.getMessage());
			ex.printStackTrace();
		}
		return "";
	}

	public String cancel(MenuGroupDto menu) {
		menu.setEditable(false);
		// usersImpl.UpdateUsers(user);
		return null;

	}

	public String editAction(MenuGroupDto menu) {

		menu.setEditable(true);
		// usersImpl.UpdateUsers(user);
		return null;
	}

	public String saveAction(MenuGroupDto menu) {
		LOGGER.info("update  saveAction method");
		/* System.out.println("**************update  saveAction method"); */
		// get all existing value but set "editable" to false

		if (menu != null) {

			MenuGroup menus = new MenuGroup();
			menus = new MenuGroup();
			menus = menuGroupImpl.getMenuGroupById(menu.getMenuGroupId(), "menuGroupId");

			LOGGER.info("here update sart for " + menus + " menuGroupId " + menus.getMenuGroupId());
			System.out.println("++++++++++++++++++++++++++here update sart for " + menus + " menuGroupId "
					+ menus.getMenuGroupId());
			menu.setEditable(false);
			menus.setMenuGroupName(menu.getMenuGroupName());
			menus.setDefaulGrouptMenu(menu.getDefaultGroupMenu());
			menus.setUserCategory(menu.getUserCategory());
			menuGroupImpl.updateMenuGroup(menus);

			// return to current page
			return null;
		} else {
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.userprofile"));
			return null;
		}

	}

	public String newAction(MenuGroupDto menu) {
		LOGGER.info("update  saveAction method");
		// get all existing value but set "editable" to false
		MenuGroup menus = new MenuGroup();
		menus = new MenuGroup();
		menus = menuGroupImpl.getMenuGroupById(menu.getMenuGroupId(), "menuGroupId");

		LOGGER.info("here update sart for " + menus + " menuGroupId " + menus.getMenuGroupId());

		menu.setEditable(false);
		menus.setMenuGroupName(menu.getMenuGroupName());
		menus.setDefaulGrouptMenu(menu.getDefaultGroupMenu());
		menus.setUserCategory(menu.getUserCategory());
		menuGroupImpl.updateMenuGroup(menus);

		// return to current page
		return "/menu/ViewMenuGroup.xhtml?faces-redirect=true";

	}

	public String updateStatus(MenuGroupDto menu) {
		LOGGER.info("update  saveAction method");
		// get all existing value but set "editable" to false
		MenuGroup menus = new MenuGroup();
		menus = new MenuGroup();
		menus = menuGroupImpl.getMenuGroupById(menu.getMenuGroupId(), "menuGroupId");

		LOGGER.info("here update sart for " + menus + " menuGroupId " + menus.getMenuGroupId());

		if (menu.getGenericStatus().equals(ACTIVE)) {

			menus.setGenericStatus(DESACTIVE);
			menuGroupImpl.updateMenuGroup(menus);
		} else {

			menus.setGenericStatus(ACTIVE);
		}
		menuGroupImpl.updateMenuGroup(menus);

		// return to current page
		return "/menu/ViewMenuGroup.xhtml?faces-redirect=true";

	}

	public String addNewMenuGroup() {

		return "/menu/menuGroupForm.xhtml?faces-redirect=true";

	}

	public void clearUserFuileds() {

		menuGroup = new MenuGroup();
		menuGroupDetails = null;
	}

	public void clearUserFuiledss() {

		menuAssignment = new MenuAssignment();
		menuAssignmentDetails = null;
	}

	public String getContextPath() {

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

	public UserCategory getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(UserCategory userCategory) {
		this.userCategory = userCategory;
	}

	public List<UserCategory> getUserCategoryDetails() {
		return userCategoryDetails;
	}

	public void setUserCategoryDetails(List<UserCategory> userCategoryDetails) {
		this.userCategoryDetails = userCategoryDetails;
	}

	public JSFBoundleProvider getProvider() {
		return provider;
	}

	public void setProvider(JSFBoundleProvider provider) {
		this.provider = provider;
	}

	public UserCategoryImpl getUserCategoryImpl() {
		return userCategoryImpl;
	}

	public void setUserCategoryImpl(UserCategoryImpl userCategoryImpl) {
		this.userCategoryImpl = userCategoryImpl;
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

	public Users getuserSessions() {
		return userSessions;
	}

	public void setuserSessions(Users userSessions) {
		this.userSessions = userSessions;
	}

	public MenuGroup getMenuGroup() {
		return menuGroup;
	}

	public void setMenuGroup(MenuGroup menuGroup) {
		this.menuGroup = menuGroup;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public List<MenuGroup> getMenuGroupDetails() {
		return menuGroupDetails;
	}

	public void setMenuGroupDetails(List<MenuGroup> menuGroupDetails) {
		this.menuGroupDetails = menuGroupDetails;
	}

	public MenuGroupImpl getMenuGroupImpl() {
		return menuGroupImpl;
	}

	public void setMenuGroupImpl(MenuGroupImpl menuGroupImpl) {
		this.menuGroupImpl = menuGroupImpl;
	}

	public String getMenuGroupCode() {
		return menuGroupCode;
	}

	public void setMenuGroupCode(String menuGroupCode) {
		this.menuGroupCode = menuGroupCode;
	}

	public String getDefaultMenuUrl() {
		return defaultMenuUrl;
	}

	public void setDefaultMenuUrl(String defaultMenuUrl) {
		this.defaultMenuUrl = defaultMenuUrl;
	}

	public MenuAssignment getMenuAssignment() {
		return menuAssignment;
	}

	public void setMenuAssignment(MenuAssignment menuAssignment) {
		this.menuAssignment = menuAssignment;
	}

	public ListOfMenu getListOfMenu() {
		return listOfMenu;
	}

	public void setListOfMenu(ListOfMenu listOfMenu) {
		this.listOfMenu = listOfMenu;
	}

	public int getMenuAssignId() {
		return menuAssignId;
	}

	public void setMenuAssignId(int menuAssignId) {
		this.menuAssignId = menuAssignId;
	}

	public int getListOfMenuId() {
		return listOfMenuId;
	}

	public void setListOfMenuId(int listOfMenuId) {
		this.listOfMenuId = listOfMenuId;
	}

	public List<MenuAssignment> getMenuAssignmentDetails() {
		return menuAssignmentDetails;
	}

	public void setMenuAssignmentDetails(List<MenuAssignment> menuAssignmentDetails) {
		this.menuAssignmentDetails = menuAssignmentDetails;
	}

	public List<ListOfMenu> getListOfMenuDetails() {
		return listOfMenuDetails;
	}

	public void setListOfMenuDetails(List<ListOfMenu> listOfMenuDetails) {
		this.listOfMenuDetails = listOfMenuDetails;
	}

	public ListOfMenuImpl getListOfMenuImpl() {
		return listOfMenuImpl;
	}

	public void setListOfMenuImpl(ListOfMenuImpl listOfMenuImpl) {
		this.listOfMenuImpl = listOfMenuImpl;
	}

	public MenuAssignmentImpl getMenuAssignmentImpl() {
		return menuAssignmentImpl;
	}

	public void setMenuAssignmentImpl(MenuAssignmentImpl menuAssignmentImpl) {
		this.menuAssignmentImpl = menuAssignmentImpl;
	}

	public int getMenuGroupId() {
		return menuGroupId;
	}

	public void setMenuGroupId(int menuGroupId) {
		this.menuGroupId = menuGroupId;
	}

	public List<MenuGroupDto> getMenuGroupDtoDetails() {
		return menuGroupDtoDetails;
	}

	public void setMenuGroupDtoDetails(List<MenuGroupDto> menuGroupDtoDetails) {
		this.menuGroupDtoDetails = menuGroupDtoDetails;
	}

	public MenuGroup getMenuGroupSession() {
		return menuGroupSession;
	}

	public void setMenuGroupSession(MenuGroup menuGroupSession) {
		this.menuGroupSession = menuGroupSession;
	}

	public MenuGroupDto getMenuGroupDto() {
		return menuGroupDto;
	}

	public void setMenuGroupDto(MenuGroupDto menuGroupDto) {
		this.menuGroupDto = menuGroupDto;
	}

}
