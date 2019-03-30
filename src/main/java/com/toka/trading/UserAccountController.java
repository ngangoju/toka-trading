package com.toka.trading;

import java.awt.image.renderable.RenderedImageFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.primefaces.model.UploadedFile;

import toka.common.DbConstant;
import toka.common.Formating;
import toka.common.JSFBoundleProvider;
import toka.common.JSFMessagers;
import toka.common.SessionUtils;
import toka.dao.impl.ContactImpl;
import toka.dao.impl.DistrictImpl;
import toka.dao.impl.LoginImpl;
import toka.dao.impl.ProvinceImpl;
import toka.dao.impl.UserCategoryImpl;
import toka.dao.impl.UserImpl;
import toka.domain.Contact;
import toka.domain.District;
import toka.domain.Province;
import toka.domain.UserCategory;
import toka.domain.Users;
import toka.trading.dto.ContactDto;
import toka.trading.dto.UserDto;

@SuppressWarnings("unused")
@ManagedBean
@ViewScoped
public class UserAccountController implements Serializable, DbConstant {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
	private String CLASSNAME = "UserAccountController :: ";
	private static final long serialVersionUID = 1L;
	/* to manage validation messages */
	private boolean isValid;
	/* end manage validation messages */
	private UploadedFile imageUpload;
	private String imageName;
	private Users users;
	private Province province;
	private District district;
	private UserCategory usercat;
	private Users usersSession;
	private int userIdNumber;
	private int provinceId;
	private int districtId;
	private int sectorId;
	private int cellId;
	private int villageId;
	private int categoryId;
	private String password;
	private String confirmPswd;
	private String useremail;
	private UserDto userDto;
	private List<Users> usersDetails = new ArrayList<Users>();
	private List<Users> useravail = new ArrayList<Users>();
	private List<Users> staffList = new ArrayList<Users>();
	private List<UserCategory> catDetails = new ArrayList<UserCategory>();
	private List<Province> provinceList = new ArrayList<Province>();
	private List<District> districtList = new ArrayList<District>();
	private List<District> districtByProv = new ArrayList<District>();
	private List<UserDto> userDtoDetails = new ArrayList<UserDto>();
	private List<UserDto> userDtosDetails = new ArrayList<UserDto>();
	List<ContactDto> contactDtoDetails = new ArrayList<ContactDto>();
	private List<UserDto> repDtosDetails = new ArrayList<UserDto>();
	private List<UserCategory> userCatDetails = new ArrayList<UserCategory>();
	private List<UserCategory> staffPosition = new ArrayList<UserCategory>();
	private List<Contact> contactDetails = new ArrayList<Contact>();
	/* class injection */
	JSFBoundleProvider provider = new JSFBoundleProvider();
	UserImpl usersImpl = new UserImpl();
	ProvinceImpl provImpl = new ProvinceImpl();
	DistrictImpl districtImpl = new DistrictImpl();
	UserCategoryImpl catImpl = new UserCategoryImpl();
	Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
	LoginImpl loginImpl = new LoginImpl();
	private String choice;
	private Contact contact;
	private ContactDto contactDto;
	private boolean rendered;
	private boolean renderForeignCountry;
	private boolean rendersaveButton;
	private boolean renderprofile;
	private String option;
	private String selection;
	private Date dateofBirth;
	private int age;
	private int days;
	private int count;
	private int contactSize;
	private int repavail;
	private int userCatid;
	private Date to;
	private Date from;
	private boolean renderDataTable;
	private boolean nextButoon;
	private String redirect;
	private String range;
	private String searchKey;
	private boolean renderBoard;
	private boolean renderDatePanel;
	private boolean renderEditedTableByDate;
	private boolean renderEditedTableByBoard;
	private boolean renderBoardOption;
	private boolean renderHeading;
	private boolean renderRepTable;
	private boolean renderRepContactDash;
	private boolean availrepSize;
	private boolean renderContactForm;
	private boolean renderRepContactAvailTable;
	private boolean renderOtherContForm;
	ContactImpl contactImpl = new ContactImpl();

	@SuppressWarnings({ "unchecked" })
	@PostConstruct
	public void init() {
		HttpSession session = SessionUtils.getSession();
		usersSession = (Users) session.getAttribute("userSession");

		if (users == null) {
			users = new Users();
		}
		if (province == null) {

			province = new Province();
		}

		if (district == null) {
			district = new District();
		}
		if (usercat == null) {
			usercat = new UserCategory();

		}
		if (userDto == null) {
			userDto = new UserDto();
		}
		if (contact == null) {
			contact = new Contact();
		}
		if (contactDto == null) {
			contactDto = new ContactDto();
		}

		try {
			provinceList = provImpl.getListWithHQL(SELECT_PROVINCE);
			// Profile Details for user logged in
			userDtoDetails = showProfileDetails();
			// Staff Position
			staffPosition = staffPosition();
			this.renderRepContactDash = true;
			for (Object[] data : usersImpl.reportList(
					"select us.fname,us.lname, us.viewId,us.address, us.userId from Contact co right  join  co.user us join us.userCategory cat where co.user is null and cat.usercategoryName='"
							+ INSTITUTE_REP + "'")) {

				LOGGER.info("users>>" + data[0] + ":: " + data[1] + "");
				Users users = new Users();
				users.setFname(data[0] + "");
				users.setLname(data[1] + "");
				users.setViewId(data[2] + "");
				users.setAddress(data[3] + "");
				users.setUserId(Integer.parseInt(data[4] + ""));
				usersDetails.add(users);
			}
			contactSize = usersDetails.size();
			contactDtoDetails = displayRepresentContact();
			repavail = contactDtoDetails.size();

			for (Object[] data : catImpl.reportList(
					"select cat.userCatid,cat.status,cat.usercategoryName from UserCategory cat where cat.usercategoryName<>'"
							+ INSTITUTE_REP + "' and cat.usercategoryName<>'" + SUPER_ADMIN + "'")) {
				UserCategory cat = new UserCategory();
				cat.setUserCatid(Integer.parseInt(data[0] + ""));
				cat.setStatus(data[1] + "");
				cat.setUsercategoryName(data[2] + "");
				userCatDetails.add(cat);
			}
			catDetails = catImpl.getGenericListWithHQLParameter(new String[] { "status", "usercategoryName" },
					new Object[] { ACTIVE, INSTITUTE_REP }, "UserCategory", " userCatid desc");
			useravail = usersImpl.getListWithHQL("from Users", 0, endrecord);
			userDtosDetails = showUsersByPageRecords(useravail);
			this.renderBoard = true;
			this.renderDatePanel = true;
			staffList = usersImpl.getGenericListWithHQLParameter(new String[] { "genericStatus" },
					new Object[] { ACTIVE }, "Users", "userId desc");

		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}

	}

	public List<UserCategory> staffPosition() {
		for (Object[] data : catImpl.reportList(
				"select cat.userCatid,cat.status,cat.usercategoryName from UserCategory cat where cat.usercategoryName<>'"
						+ INSTITUTE_REP + "'")) {
			UserCategory cat = new UserCategory();
			cat.setUserCatid(Integer.parseInt(data[0] + ""));
			cat.setStatus(data[1] + "");
			cat.setUsercategoryName(data[2] + "");
			staffPosition.add(cat);
		}
		return (staffPosition);
	}

	public List<UserDto> showProfileDetails() {
		Users user = usersImpl.gettUserById(usersSession.getUserId(), "userId");
		List<UserDto> staffDto = new ArrayList<UserDto>();
		if (null != user) {
			UserDto userDto = new UserDto();
			if (user.getUserCategory().getUserCatid() == 3) {
				userDto.setEditable(false);
				userDto.setRenderBoard(false);
				userDto.setFname(user.getFname());
				userDto.setLname(user.getLname());
				userDto.setViewId(user.getViewId());
				userDto.setAddress(user.getAddress());
				userDto.setUserId(user.getUserId());
				userDto.setUserCategory(user.getUserCategory());
				userDto.setLoginStatus(user.getLoginStatus());
				userDto.setGender(user.getGender());
				staffDto.add(userDto);
			} else {
				userDto.setEditable(false);
				userDto.setFname(user.getFname());
				userDto.setLname(user.getLname());
				userDto.setViewId(user.getViewId());
				userDto.setAddress(user.getAddress());
				userDto.setUserId(user.getUserId());
				userDto.setUserCategory(user.getUserCategory());
				userDto.setLoginStatus(user.getLoginStatus());
				userDto.setGender(user.getGender());
				staffDto.add(userDto);
			}

		}
		return (staffDto);
	}

	public void showUsers() throws Exception {
		if (range.equals("6") || (range.equals("11")) || (range.equals("16"))) {
			int endRecords = Integer.parseInt(range);
			useravail = usersImpl.getListWithHQL("from Users", 0, endRecords);
			LOGGER.info("RANGE VALUE:" + range + "::::::::::BOARD LIST RENDERED:::::::::::::::::");
		} else {
			this.renderBoardOption = true;
			this.renderDatePanel = false;

			LOGGER.info("RANGE VALUE:" + range + "::::::::::BOARD LIST RENDERED:::::::::::::::::");
		}
	}
	public void searchStaff() {
		LOGGER.info("Search Key value::::::"+searchKey);
		if (null!=searchKey) {
			userDtosDetails = new ArrayList<UserDto>();
			for (Users users : staffList) {
				LOGGER.info("users::::::::::::::::::::::::::::::::::::::::::::::::>>" + users.getUserId() + ":: "
						+ users.getFname() + "");
				if (users.getUserCategory().getUserCatid() != 1) {
					UserDto userDtos = new UserDto();
					userDtos.setEditable(false);
					userDtos.setNotify(false);
					if (users.getBranch() != null) {
						if (users.getFname().contains(searchKey) || users.getLname().contains(searchKey)
								|| users.getStatus().contains(searchKey)||users.getLoginStatus().contains(searchKey)
								|| users.getBranch().getBranchName().contains(searchKey)
								|| users.getUserCategory().getUsercategoryName().contains(searchKey)) {
							userDtos.setUserId(users.getUserId());
							userDtos.setFname(users.getFname());
							userDtos.setLname(users.getLname());
							userDtos.setViewId(users.getViewId());
							userDtos.setLoginStatus(users.getLoginStatus());
							userDtos.setUserCategory(users.getUserCategory());
							userDtos.setStatus(users.getStatus());
							userDtos.setBranch(users.getBranch());
							if (users.getStatus().equals(ACTIVE)) {
								userDtos.setAction(DESACTIVE);
							} else {
								userDtos.setAction(ACTIVE);
							}
							userDtosDetails.add(userDtos);
						}
					}
				}
			}
		} else {
			userDtosDetails = showUsersByPageRecords(useravail);
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.searcherror"));
		}
	}


	public List<UserDto> showUsersByPageRecords(List<Users> userslist) {
		try {

			userDtosDetails = new ArrayList<UserDto>();
			for (Users users : userslist) {
				LOGGER.info("users::::::::::::::::::::::::::::::::::::::::::::::::>>" + users.getUserId() + ":: "
						+ users.getFname() + "");
				if (users.getUserCategory().getUserCatid() != 1) {
					UserDto userDtos = new UserDto();
					userDtos.setEditable(false);
					userDtos.setNotify(false);
					if (users.getBranch() != null) {
						userDtos.setUserId(users.getUserId());
						userDtos.setFname(users.getFname());
						userDtos.setLname(users.getLname());
						userDtos.setViewId(users.getViewId());
						userDtos.setLoginStatus(users.getLoginStatus());
						userDtos.setUserCategory(users.getUserCategory());
						userDtos.setStatus(users.getStatus());
						userDtos.setBranch(users.getBranch());
						if (users.getStatus().equals(ACTIVE)) {
							userDtos.setAction(DESACTIVE);
						} else {
							userDtos.setAction(ACTIVE);
						}
						userDtosDetails.add(userDtos);
					}
				}
			}
			return (userDtosDetails);

		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}
		return (userDtosDetails);

	}

	public List<ContactDto> displayRepresentContact() {

		List<ContactDto> contactDtoDetails = new ArrayList<ContactDto>();
		for (Object[] data : contactImpl.reportList(
				"select co.contactDetails,co.email,co.phone,co.contactId,co.user from Contact co right  join  co.user us join us.userCategory cat where co.user is not null and cat.usercategoryName='"
						+ INSTITUTE_REP + "'")) {
			ContactDto contDto = new ContactDto();
			contDto.setEditable(false);
			contDto.setContactDetails(data[0] + "");
			contDto.setEmail(data[1] + "");
			contDto.setPhone(data[2] + "");
			contDto.setContactId(Integer.parseInt(data[3] + ""));
			contDto.setUser((Users) data[4]);
			contactDtoDetails.add(contDto);
		}
		return (contactDtoDetails);
	}

	public void renderContactTable() {
		this.rendered = true;
		this.renderRepContactDash = false;
	}

	
	public void showRepresent() {

		this.renderRepTable = true;
		this.renderHeading = true;
		this.renderRepContactDash = false;
	}

	// Method to display all district by province
	@SuppressWarnings("unchecked")
	public void changeDistrict() {
		try {
			province = provImpl.getProvinceById(provinceId, "provenceId");
			districtByProv = districtImpl.getGenericListWithHQLParameter(new String[] { "province" },
					new Object[] { province }, "District", "districtId asc");
		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}
	}


	public void clearUserFuileds() {
		users = new Users();
		usersDetails = null;
	}

	public String changeOption() {
		if (option.equals(Yes_Option)) {
			rendered = true;
			renderprofile = false;
			/* renderForeignCountry=true; */
			rendersaveButton = true;
			return (option);
		} else {
			rendered = false;
			renderprofile = false;
			rendersaveButton = true;
			return (option);
		}
	}

	public String getMyFormattedDate() {
		HttpSession session = SessionUtils.getSession();
		Users usersSes = new Users();
		usersSes = (Users) session.getAttribute("userSession");
		LOGGER.info("USERNAME::::"+usersSes.getDateOfBirth());
		return new SimpleDateFormat("dd-MM-yyyy").format(usersSes.getDateOfBirth());
	}

	public void profilePage(UserDto user) {
		if (redirect.equals(Next_Option)) {
			if (null != user) {
				int userId = user.getUserId();
				HttpSession sessionuser = SessionUtils.getSession();
				sessionuser.setAttribute("userProfile", userId);
				nextButoon = true;
				renderRepContactDash = false;
			}
		} else {
			renderprofile = false;
			nextButoon = false;
		}

	}

	public String nextPage() {
		return "/menu/EditProfile.xhtml?faces-redirect=true";
	}

	public String getContextPath() {

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		return request.getContextPath();
	}

	public void optionCombine() {
		rendered = false;
		renderForeignCountry = false;
		rendersaveButton = false;
		renderprofile = false;
		nextButoon = false;
	}

	public String cancel(UserDto user) {
		user.setEditable(false);
		user.setRenderBoard(false);
		optionCombine();

		// usersImpl.UpdateUsers(user);
		return null;

	}

	public void manageOption() {

		if (selection.equals(Date_opt)) {
			this.renderDatePanel = true;
			this.renderBoardOption = false;
			this.renderEditedTableByBoard = false;
			LOGGER.info("Option founded:::::::::::" + selection);
		} else {
			this.renderDatePanel = false;
			this.renderBoardOption = true;
			this.renderEditedTableByDate = false;
		}

	}

	@SuppressWarnings("unchecked")
	public String renderAction(UserDto user) throws Exception {
		user.setNotify(true);
		Users users = new Users();
		users = usersImpl.getModelWithMyHQL(new String[] { "userId" }, new Object[] { user.getUserId() }, "from Users");
		if (null != users) {
			contactDetails = contactImpl.getGenericListWithHQLParameter(new String[] { "genericStatus", "user" },
					new Object[] { ACTIVE, users }, "Contact", "contactId asc");
		}
		return null;
	}

	public String cancelChange(UserDto user) {
		user.setNotify(false);
		this.useremail = null;
		return null;
	}

	public String editAction(UserDto user) {

		user.setEditable(true);
		if (user.getUserCategory().getUserCatid() == 3) {
			user.setRenderBoard(true);
		}

		renderForeignCountry = true;
		renderprofile = true;
		rendersaveButton = true;
		// showCategory();
		// usersImpl.UpdateUsers(user);
		return null;
	}

	public boolean confirmPswd() {
		boolean valid = false;
		if (password.equalsIgnoreCase(confirmPswd)) {
			valid = true;
			return (valid);
		}
		return (valid);
	}

	public String backPage() {
		if (usersSession.getUserCategory().getUsercategoryName().equals(INSTITUTE_REP)) {
			return "/menu/ViewUsersDetails.xhtml?faces-redirect=true";
		} else {
			return "/menu/ListOfUsers.xhtml?faces-redirect=true";
		}
	}

	public String boardUpdateStatus(UserDto user) {
		LOGGER.info("update  saveAction method");
		try {
			Users us = new Users();
			us = new Users();
			if (user != null)
				us = usersImpl.gettUserById(user.getUserId(), "userId");
			if (us != null)
				LOGGER.info("here update sart for " + us + " useriD " + us.getStatus());

			if (user.getStatus().equals(ACTIVE)) {
				us.setUpdatedBy(usersSession.getViewId());
				us.setUpDtTime(timestamp);
				us.setStatus(DESACTIVE);
				user.setNotify(false);
			} else {
				us.setUpdatedBy(usersSession.getViewId());
				us.setUpDtTime(timestamp);
				us.setStatus(ACTIVE);
				user.setNotify(false);
			}
			Contact ct = new Contact();

			if (null != useremail) {
				ct = contactImpl.getModelWithMyHQL(new String[] { "email" }, new Object[] { useremail },
						"from Contact");
				if (null != ct) {
					usersImpl.UpdateUsers(us);
					JSFMessagers.resetMessages();
					setValid(true);
					JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.userupdate"));
					LOGGER.info("EMAIL TO NOTIFY::::::::::::::::::::::::::::::::::::::" + useremail);
					boolean valid = notifyRepresentativeChange(useremail);
					if (valid) {
						JSFMessagers.resetMessages();
						setValid(true);
						JSFMessagers
								.addErrorMessage(getProvider().getValue("com.server.side.email.notification.status"));
						LOGGER.info(CLASSNAME + "::Email sent successuful!!");
						this.useremail = null;
						// return to current page
					} else {
						JSFMessagers.resetMessages();
						setValid(false);
						JSFMessagers.addErrorMessage(getProvider().getValue("com.notifyError.representative.user"));
						LOGGER.info(CLASSNAME + "::Fail to send Email!!");
						this.useremail = null;
					}
				} else {
					this.useremail = null;
					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("error.server.side.notfound.email"));
					LOGGER.info(CLASSNAME + "sivaserside validation :: email not recorded in the system! ");
					return null;
				}

			} else {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("error.selected.invalid.email"));
			}
			return "";
		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.updateuserError"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}
		return null;

	}

	public String updateStatus(UserDto user) {
		LOGGER.info("update  saveAction method");
		// get all existing value but set "editable" to false
		try {
			Users us = new Users();
			us = new Users();
			Contact ct = new Contact();
			if (user != null)
				us = usersImpl.gettUserById(user.getUserId(), "userId");
			if (us != null)
				LOGGER.info("here update sart for " + us + " useriD " + us.getStatus());

			if (user.getStatus().equals(ACTIVE)) {
				us.setUpdatedBy(usersSession.getViewId());
				us.setUpDtTime(timestamp);
				us.setStatus(DESACTIVE);
				user.setNotify(false);
			} else {
				us.setUpdatedBy(usersSession.getViewId());
				us.setUpDtTime(timestamp);
				us.setStatus(ACTIVE);
				user.setNotify(false);
			}

			if (null != useremail) {
				ct = contactImpl.getModelWithMyHQL(new String[] { "email" }, new Object[] { useremail },
						"from Contact");
				if (null != ct) {
					usersImpl.UpdateUsers(us);
					useravail = usersImpl.getListWithHQL("from Users", 0, endrecord);
					JSFMessagers.resetMessages();
					setValid(true);
					JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.userupdate"));
					LOGGER.info(CLASSNAME + "::Email sent successuful!!");
					this.useremail = null;
					LOGGER.info("EMAIL TO NOTIFY::::::::::::::::::::::::::::::::::::::" + useremail);
					boolean valid = notifyRepresentativeChange(useremail);
					if (valid) {
						LOGGER.info("returing values controller" + valid);
						setValid(true);
						JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.email.notifications"));
						LOGGER.info(CLASSNAME + ":::Contact Details is saved");
					} else {
						JSFMessagers.resetMessages();
						setValid(false);
						JSFMessagers.addErrorMessage(getProvider().getValue("com.notifyError.representative.user"));
						LOGGER.info(CLASSNAME + "::Fail to send Email!!");
						this.useremail = null;
					}
				} else {
					this.useremail = null;
					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("error.server.side.notfound.email"));
					LOGGER.info(CLASSNAME + "sivaserside validation :: email not recorded in the system! ");
					return null;
				}
			} else {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("error.selected.invalid.email"));
			}
			return null;
		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.updateuserError"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public Boolean notifyRepresentativeChange(String email) throws Exception {
		boolean valid = false;
		try {
			SendSupportEmail support = new SendSupportEmail();
			Contact ct = new Contact();

			if (null != email) {
				ct = contactImpl.getModelWithMyHQL(new String[] { "email" }, new Object[] { email }, "from Contact");
				if (null != ct) {
					boolean verifyemail = support.sendMailTestVersion(ct.getUser().getFname(), ct.getUser().getLname(),
							ct.getEmail());
					if (verifyemail) {
						valid = true;
					}
				} else {
					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("error.server.side.notfound.email"));
					LOGGER.info(CLASSNAME + "sivaserside validation :: email not recorded in the system! ");
					return null;
				}

			} else {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("error.selected.invalid.email"));
			}
		} catch (HibernateException e) {
			LOGGER.info(CLASSNAME + ":::Contact Details is fail with HibernateException  error");
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.errorsession"));
			LOGGER.info(CLASSNAME + "" + e.getMessage());
			e.printStackTrace();
		}
		return (valid);
	}

	public String updateRepStatus(UserDto user) {
		LOGGER.info("update  saveAction method");
		// get all existing value but set "editable" to false
		try {
			Users us = new Users();
			us = new Users();
			if (user != null)
				us = usersImpl.gettUserById(user.getUserId(), "userId");
			if (us != null)
				LOGGER.info("here update sart for " + us + " useriD " + us.getStatus());

			if (user.getStatus().equals(ACTIVE)) {
				us.setUpdatedBy(usersSession.getViewId());
				us.setUpDtTime(timestamp);
				us.setStatus(DESACTIVE);

			} else {
				us.setUpdatedBy(usersSession.getViewId());
				us.setUpDtTime(timestamp);
				us.setStatus(ACTIVE);
			}
			Contact ct = new Contact();

			if (null != useremail) {
				ct = contactImpl.getModelWithMyHQL(new String[] { "email" }, new Object[] { useremail },
						"from Contact");
				if (null != ct) {
					usersImpl.UpdateUsers(us);
					JSFMessagers.resetMessages();
					setValid(true);
					JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.userupdate"));
					LOGGER.info("EMAIL TO NOTIFY::::::::::::::::::::::::::::::::::::::" + useremail);
					boolean valid = notifyRepresentativeChange(useremail);
					if (valid) {
						JSFMessagers.resetMessages();
						setValid(true);
						JSFMessagers
								.addErrorMessage(getProvider().getValue("com.server.side.email.notification.status"));
						LOGGER.info(CLASSNAME + "::Email sent successuful!!");
						this.useremail = null;

					} else {
						JSFMessagers.resetMessages();
						setValid(false);
						JSFMessagers.addErrorMessage(getProvider().getValue("com.notifyError.representative.user"));
						LOGGER.info(CLASSNAME + "::Fail to send Email!!");
						this.useremail = null;
					}
				} else {
					this.useremail = null;
					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("error.server.side.notfound.email"));
					LOGGER.info(CLASSNAME + "sivaserside validation :: email not recorded in the system! ");
					return null;
				}

			} else {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("error.selected.invalid.email"));
			}
			return "";
			/* return "/menu/ViewUsersList.xhtml?faces-redirect=true"; */

		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.updateuserError"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	public void updateTable() throws Exception {
		try {

			if (choice.equals(country_rw)
					&& usersSession.getUserCategory().getUsercategoryName().equals(INSTITUTE_REP)) {
				rendered = true;
				renderForeignCountry = true;
				renderBoard = true;
				nextButoon = false;
				LOGGER.info(" REP LIST SIze:::::::" + userCatDetails.size());
			} else if (usersSession.getUserCategory().getUsercategoryName().equals(INSTITUTE_REP)) {
				rendered = false;
				renderForeignCountry = true;
				nextButoon = false;
				renderBoard = true;
				LOGGER.info(" REP LIST SIze:::::::" + userCatDetails.size());
			}

			// CATEGORY ON ADMIN PANEL
			if (choice.equals(country_rw) && usersSession.getUserCategory().getUsercategoryName().equals(SUPER_ADMIN)) {
				rendered = true;
				renderForeignCountry = true;
				renderBoard = false;
				nextButoon = true;
				LOGGER.info("ADMIN LIST SIze:::::::" + catDetails.size());
			} else if (usersSession.getUserCategory().getUsercategoryName().equals(SUPER_ADMIN)) {
				rendered = false;
				renderForeignCountry = true;
				nextButoon = true;
				renderBoard = false;
				LOGGER.info(" ADMIN LIST SIze:::::::" + catDetails.size());
			}
		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}
	}

	public void showDataTable() {

		if ((null != to) && (null != from)) {

			renderDataTable = true;
		}
	}

	public void addcontacts() {
		HttpSession session = SessionUtils.getSession();
		// Get the values from the session
		users = (Users) session.getAttribute("userinfo");
	}

	public void showDatePanel() {

		if (selection.equals(Date_opt)) {
			renderDatePanel = true;
		}
	}

	public String saveUserNewContact() {

		try {

			try {

				Contact ct = new Contact();
				LOGGER.info("USER DETAILS:::::::::::" + contact.getEmail() + ":::::::::" + users.getFname() + "::::"
						+ users.getLname());
				if (null != contact.getEmail())
					ct = contactImpl.getModelWithMyHQL(new String[] { "email" }, new Object[] { contact.getEmail() },
							"from Contact");
				if (null != ct) {

					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("error.server.side.dupicate.email"));
					LOGGER.info(CLASSNAME + "sivaserside validation :: email already  recorded in the system! ");
					return null;
				}
				ct = contactImpl.getModelWithMyHQL(new String[] { "phone" }, new Object[] { contact.getPhone() },
						"from Contact");
				if (null != ct) {

					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("error.server.side.dupicate.phone.number"));
					LOGGER.info(CLASSNAME + "sivaserside validation :: phone number already  recorded in the system! ");
					return null;
				}

			} catch (Exception e) {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
				LOGGER.info(CLASSNAME + "" + e.getMessage());
				e.printStackTrace();
				return null;
			}
			// :::Method to get user's info through session:::::::::://
			addcontacts();
			// :::End of Method :::::::::://

			/* FormSampleController sample = new FormSampleController(); */
			SendSupportEmail support = new SendSupportEmail();
			if (null != users) {
				contact.setCreatedBy(usersSession.getViewId());
				contact.setCrtdDtTime(timestamp);
				contact.setGenericStatus(ACTIVE);
				contact.setUpDtTime(timestamp);
				contact.setUpdatedBy(usersSession.getViewId());
				LOGGER.info(users.getUserId() + "" + users.getFname() + ":::------->>>>>>User searched founded");
				contact.setUser(usersImpl.gettUserById(users.getUserId(), "userId"));

				contact.setUpdatedBy(usersSession.getViewId());
				// :::saving contact action:::::::::::://
				contactImpl.saveContact(contact);
				// :::::End of saving:::::::::::::// JSFMessagers.resetMessages();
				// JSFMessagers.resetMessages();
				JSFMessagers.resetMessages();
				setValid(true);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.contact"));
				LOGGER.info(CLASSNAME + ":::Contact Details is saved" + contact.getEmail() + ":::::::"
						+ users.getFname() + ":::" + users.getLname());
				boolean verifyemail = support.sendMailTestVersion(users.getFname(), users.getLname(),
						contact.getEmail());
				// ::: end sending email action:::::::::::://
				if (verifyemail) {
					LOGGER.info("returing values controller" + verifyemail);
					setValid(true);
					JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.email.notifications"));
					LOGGER.info(CLASSNAME + ":::Contact Details is saved");
				} else {
					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("com.notifyError.representative.user"));
				}
			} else {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.errorsession"));
			}
			clearContactFuileds();
			return null;
		} catch (HibernateException e) {
			LOGGER.info(CLASSNAME + ":::Contact Details is fail with HibernateException  error");
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.errorsession"));
			LOGGER.info(CLASSNAME + "" + e.getMessage());
			e.printStackTrace();
			return "";
		}

	}

	private void clearContactFuileds() {
		this.useremail = null;
		contact = new Contact();
		userIdNumber = 0;
		// usersDetails=null;
	}

	public String cancelContact(ContactDto contact) {
		contact.setEditable(false);
		// usersImpl.UpdateUsers(user);
		return null;

	}

	public void showRepresentContAvail() {
		this.renderRepContactDash = false;
		this.renderRepContactAvailTable = true;
	}

	public String changePage() {

		if (usersSession.getUserCategory().getUsercategoryName().equals(INSTITUTE_REP)) {
			return "/menu/ListOfUsers.xhtml?faces-redirect=true";
		}
		return null;
	}

	public void addOthercontacts() {
		HttpSession session = SessionUtils.getSession();
		// Get the values from the session
		contactDto = (ContactDto) session.getAttribute("continfo");
	}

	public void otherUserContact(ContactDto cont) {
		HttpSession sessionuser = SessionUtils.getSession();

		if (null != cont) {
			/*
			 * renderContactForm = true;
			 * 
			 * rendered = false; renderForeignCountry = false;
			 */

			// Session creation to get user info from dataTable row
			sessionuser.setAttribute("continfo", cont);
			addOthercontacts();
			this.renderOtherContForm = true;
			this.renderRepContactAvailTable = false;

		}

	}

	public String saveContactAction(ContactDto contact) {
		LOGGER.info("update  saveAction method");
		// get all existing value but set "editable" to false
		Contact cont = new Contact();
		cont = new Contact();
		cont = contactImpl.getContactById(contact.getContactId(), "contactId");

		LOGGER.info("here update sart for " + cont + " useriD " + cont.getContactId());

		contact.setEditable(false);
		cont.setContactDetails(contact.getContactDetails());
		cont.setEmail(contact.getEmail());
		cont.setPhone(contact.getPhone());
		cont.setUpdatedBy(usersSession.getViewId());
		cont.setUpDtTime(timestamp);
		contactImpl.UpdateContact(cont);
		JSFMessagers.resetMessages();
		setValid(true);
		JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.userupdate"));
		// return to current page
		return null;

	}

	public void editUserContact(Users user) {
		HttpSession sessionuser = SessionUtils.getSession();

		if (null != user)
			renderContactForm = true;
		rendered = false;
		// Session creation to get user info from dataTable row
		sessionuser.setAttribute("userinfo", user);
		LOGGER.info("Info Founded are userid:>>>>>>>>>>>>>>>>>>>>>>fname:" + user.getFname());
		addcontacts();
	}

	public String editContactAction(ContactDto contact) {

		contact.setEditable(true);
		// usersImpl.UpdateUsers(user);
		return null;
	}

	public String saveContact() throws Exception {
		try {

			try {

				Contact ct = new Contact();
				if (null != contact.getEmail())
					ct = contactImpl.getModelWithMyHQL(new String[] { "email" }, new Object[] { contact.getEmail() },
							"from Contact");
				if (null != ct) {

					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("error.server.side.dupicate.email"));
					LOGGER.info(CLASSNAME + "sivaserside validation :: email already  recorded in the system! ");
					return null;
				}
				ct = contactImpl.getModelWithMyHQL(new String[] { "phone" }, new Object[] { contact.getPhone() },
						"from Contact");
				if (null != ct) {

					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("error.server.side.dupicate.phone.number"));
					LOGGER.info(CLASSNAME + "sivaserside validation :: phone number already  recorded in the system! ");
					return null;
				}

			} catch (Exception e) {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
				LOGGER.info(CLASSNAME + "" + e.getMessage());
				e.printStackTrace();
				return null;
			}
			// :::Method to get user's info through session:::::::::://
			addOthercontacts();
			// :::End of Method :::::::::://

			/* FormSampleController sample = new FormSampleController(); */
			SendSupportEmail support = new SendSupportEmail();

			if (null != contactDto) {
				users = contactDto.getUser();

				contact.setCreatedBy(usersSession.getViewId());
				contact.setCrtdDtTime(timestamp);
				contact.setGenericStatus(ACTIVE);
				contact.setUpDtTime(timestamp);
				contact.setCreatedBy(usersSession.getViewId());
				contact.setCrtdDtTime(timestamp);
				contact.setGenericStatus(ACTIVE);
				contact.setUpDtTime(timestamp);
				LOGGER.info(users.getUserId() + "" + users.getFname() + ":::------->>>>>>User searched founded");
				contact.setUser(usersImpl.gettUserById(users.getUserId(), "userId"));
				// :::sending email action:::::::::::://
				contact.setUpdatedBy(usersSession.getViewId());
				// :::saving contact action:::::::::::://
				contactImpl.saveContact(contact);
				// :::::End of saving::::::::::::://
				JSFMessagers.resetMessages();
				setValid(true); //
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.contact"));
				LOGGER.info(CLASSNAME + ":::Other Contact:::::" + users.getFname() + "::::" + users.getLname() + "::"
						+ contact.getEmail());

				boolean verifyemail = support.sendMailTestVersion(users.getFname(), users.getLname(),
						contact.getEmail());
				// ::: end sending email action:::::::::::://
				if (verifyemail) {
					LOGGER.info("returing values controller" + verifyemail);
					setValid(true);
					JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.email.notifications"));
					LOGGER.info(CLASSNAME + ":::Contact Details is saved");
				} else {
					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("com.notifyError.representative.user"));
				}
			} else {

				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.errorsession"));
			}
			clearContactFuileds();
			return null;

		} catch (HibernateException e) {
			LOGGER.info(CLASSNAME + ":::Contact Details is fail with HibernateException  error");
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.errorsession"));
			LOGGER.info(CLASSNAME + "" + e.getMessage());
			e.printStackTrace();
			return "";
		}

	}

	public String getCLASSNAME() {
		return CLASSNAME;
	}

	public void setCLASSNAME(String cLASSNAME) {
		CLASSNAME = cLASSNAME;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Users getUsersSession() {
		return usersSession;
	}

	public void setUsersSession(Users usersSession) {
		this.usersSession = usersSession;
	}

	public int getUserIdNumber() {
		return userIdNumber;
	}

	public void setUserIdNumber(int userIdNumber) {
		this.userIdNumber = userIdNumber;
	}

	public List<Users> getUsersDetails() {
		return usersDetails;
	}

	public void setUsersDetails(List<Users> usersDetails) {
		this.usersDetails = usersDetails;
	}

	public JSFBoundleProvider getProvider() {
		return provider;
	}

	public void setProvider(JSFBoundleProvider provider) {
		this.provider = provider;
	}

	public UserImpl getUsersImpl() {
		return usersImpl;
	}

	public void setUsersImpl(UserImpl usersImpl) {
		this.usersImpl = usersImpl;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public List<Province> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}

	public ProvinceImpl getProvImpl() {
		return provImpl;
	}

	public void setProvImpl(ProvinceImpl provImpl) {
		this.provImpl = provImpl;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public List<District> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<District> districtList) {
		this.districtList = districtList;
	}

	public DistrictImpl getDistrictImpl() {
		return districtImpl;
	}

	public void setDistrictImpl(DistrictImpl districtImpl) {
		this.districtImpl = districtImpl;
	}

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public int getSectorId() {
		return sectorId;
	}

	public void setSectorId(int sectorId) {
		this.sectorId = sectorId;
	}

	public int getCellId() {
		return cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	public int getVillageId() {
		return villageId;
	}

	public void setVillageId(int villageId) {
		this.villageId = villageId;
	}

	public List<District> getDistrictByProv() {
		return districtByProv;
	}

	public void setDistrictByProv(List<District> districtByProv) {
		this.districtByProv = districtByProv;
	}

	public UserCategory getUsercat() {
		return usercat;
	}

	public void setUsercat(UserCategory usercat) {
		this.usercat = usercat;
	}

	public List<UserCategory> getCatDetails() {
		return catDetails;
	}

	public void setCatDetails(List<UserCategory> catDetails) {
		this.catDetails = catDetails;
	}

	public UserCategoryImpl getCatImpl() {
		return catImpl;
	}

	public void setCatImpl(UserCategoryImpl catImpl) {
		this.catImpl = catImpl;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public UploadedFile getImageUpload() {
		return imageUpload;
	}

	public void setImageUpload(UploadedFile imageUpload) {
		this.imageUpload = imageUpload;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPswd() {
		return confirmPswd;
	}

	public void setConfirmPswd(String confirmPswd) {
		this.confirmPswd = confirmPswd;
	}

	public List<UserDto> getUserDtoDetails() {
		return userDtoDetails;
	}

	public void setUserDtoDetails(List<UserDto> userDtoDetails) {
		this.userDtoDetails = userDtoDetails;
	}

	public List<UserDto> getUserDtosDetails() {
		return userDtosDetails;
	}

	public void setUserDtosDetails(List<UserDto> userDtosDetails) {
		this.userDtosDetails = userDtosDetails;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public LoginImpl getLoginImpl() {
		return loginImpl;
	}

	public void setLoginImpl(LoginImpl loginImpl) {
		this.loginImpl = loginImpl;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}

	public boolean isRenderForeignCountry() {
		return renderForeignCountry;
	}

	public void setRenderForeignCountry(boolean renderForeignCountry) {
		this.renderForeignCountry = renderForeignCountry;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDateofBirth() {
		return dateofBirth;
	}

	public void setDateofBirth(Date dateofBirth) {
		this.dateofBirth = dateofBirth;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public boolean isRenderDataTable() {
		return renderDataTable;
	}

	public void setRenderDataTable(boolean renderDataTable) {
		this.renderDataTable = renderDataTable;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public boolean isRendersaveButton() {
		return rendersaveButton;
	}

	public void setRendersaveButton(boolean rendersaveButton) {
		this.rendersaveButton = rendersaveButton;
	}

	public boolean isRenderprofile() {
		return renderprofile;
	}

	public void setRenderprofile(boolean renderprofile) {
		this.renderprofile = renderprofile;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public boolean isNextButoon() {
		return nextButoon;
	}

	public void setNextButoon(boolean nextButoon) {
		this.nextButoon = nextButoon;
	}

	public boolean isRenderBoard() {
		return renderBoard;
	}

	public void setRenderBoard(boolean renderBoard) {
		this.renderBoard = renderBoard;
	}

	public List<UserDto> getRepDtosDetails() {
		return repDtosDetails;
	}

	public void setRepDtosDetails(List<UserDto> repDtosDetails) {
		this.repDtosDetails = repDtosDetails;
	}

	public boolean isRenderEditedTableByDate() {
		return renderEditedTableByDate;
	}

	public void setRenderEditedTableByDate(boolean renderEditedTableByDate) {
		this.renderEditedTableByDate = renderEditedTableByDate;
	}

	public boolean isRenderEditedTableByBoard() {
		return renderEditedTableByBoard;
	}

	public void setRenderEditedTableByBoard(boolean renderEditedTableByBoard) {
		this.renderEditedTableByBoard = renderEditedTableByBoard;
	}

	public boolean isRenderDatePanel() {
		return renderDatePanel;
	}

	public void setRenderDatePanel(boolean renderDatePanel) {
		this.renderDatePanel = renderDatePanel;
	}

	public boolean isRenderBoardOption() {
		return renderBoardOption;
	}

	public void setRenderBoardOption(boolean renderBoardOption) {
		this.renderBoardOption = renderBoardOption;
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public boolean isRenderHeading() {
		return renderHeading;
	}

	public void setRenderHeading(boolean renderHeading) {
		this.renderHeading = renderHeading;
	}

	public boolean isRenderRepTable() {
		return renderRepTable;
	}

	public void setRenderRepTable(boolean renderRepTable) {
		this.renderRepTable = renderRepTable;
	}

	public boolean isRenderRepContactDash() {
		return renderRepContactDash;
	}

	public void setRenderRepContactDash(boolean renderRepContactDash) {
		this.renderRepContactDash = renderRepContactDash;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isAvailrepSize() {
		return availrepSize;
	}

	public void setAvailrepSize(boolean availrepSize) {
		this.availrepSize = availrepSize;
	}

	public int getContactSize() {
		return contactSize;
	}

	public void setContactSize(int contactSize) {
		this.contactSize = contactSize;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public boolean isRenderContactForm() {
		return renderContactForm;
	}

	public void setRenderContactForm(boolean renderContactForm) {
		this.renderContactForm = renderContactForm;
	}

	public ContactImpl getContactImpl() {
		return contactImpl;
	}

	public void setContactImpl(ContactImpl contactImpl) {
		this.contactImpl = contactImpl;
	}

	public List<ContactDto> getContactDtoDetails() {
		return contactDtoDetails;
	}

	public void setContactDtoDetails(List<ContactDto> contactDtoDetails) {
		this.contactDtoDetails = contactDtoDetails;
	}

	public int getRepavail() {
		return repavail;
	}

	public void setRepavail(int repavail) {
		this.repavail = repavail;
	}

	public boolean isRenderRepContactAvailTable() {
		return renderRepContactAvailTable;
	}

	public void setRenderRepContactAvailTable(boolean renderRepContactAvailTable) {
		this.renderRepContactAvailTable = renderRepContactAvailTable;
	}

	public ContactDto getContactDto() {
		return contactDto;
	}

	public void setContactDto(ContactDto contactDto) {
		this.contactDto = contactDto;
	}

	public boolean isRenderOtherContForm() {
		return renderOtherContForm;
	}

	public void setRenderOtherContForm(boolean renderOtherContForm) {
		this.renderOtherContForm = renderOtherContForm;
	}

	public List<UserCategory> getUserCatDetails() {
		return userCatDetails;
	}

	public void setUserCatDetails(List<UserCategory> userCatDetails) {
		this.userCatDetails = userCatDetails;
	}

	public List<Users> getUseravail() {
		return useravail;
	}

	public void setUseravail(List<Users> useravail) {
		this.useravail = useravail;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public int getUserCatid() {
		return userCatid;
	}

	public void setUserCatid(int userCatid) {
		this.userCatid = userCatid;
	}

	public List<Contact> getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(List<Contact> contactDetails) {
		this.contactDetails = contactDetails;
	}

	public List<UserCategory> getStaffPosition() {
		return staffPosition;
	}

	public void setStaffPosition(List<UserCategory> staffPosition) {
		this.staffPosition = staffPosition;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public List<Users> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<Users> staffList) {
		this.staffList = staffList;
	}

}
