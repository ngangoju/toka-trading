package com.toka.trading;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;

import toka.common.DbConstant;
import toka.common.Formating;
import toka.common.JSFBoundleProvider;
import toka.common.JSFMessagers;
import toka.common.SessionUtils;
import toka.dao.impl.ContactImpl;
import toka.dao.impl.UserImpl;
import toka.domain.Contact;
import toka.domain.UserCategory;
import toka.domain.Users;
import toka.trading.dto.ContactDto;
import toka.trading.dto.UserDto;

@ManagedBean
@ViewScoped
public class UserContactController implements Serializable, DbConstant {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
	private String CLASSNAME = "UserContactController :: ";
	private static final long serialVersionUID = 1L;
	/* to manage validation messages */
	private boolean isValid;

	/* end manage validation messages */
	private Contact contact;
	private Users users;
	private UserDto userDto;
	private ContactDto contactDto;
	private UserCategory categ;
	private Users usersSession;
	private int userIdNumber;
	private List<Users> usersDetails = new ArrayList<Users>();
	private List<UserDto> userDtoDetails = new ArrayList<UserDto>();
	private List<UserDto> userDtofiltered = new ArrayList<UserDto>();
	private List<ContactDto> contactDtoDetails = new ArrayList<ContactDto>();
	private List<Contact> contactDetails = new ArrayList<Contact>();
	private List<UserDto> userDtosDetails = new ArrayList<UserDto>();
	private List<Users> countUsersList = new ArrayList<Users>();
	private List<Users> holdCountedList = new ArrayList<Users>();
	private String choice;
	private boolean rendered;
	private boolean planRender;
	private boolean renderForeignCountry = true;
	private boolean renderContactForm;
	private boolean renderTable;
	private boolean renderLoginTable;
	private boolean renderContactDash;
	private boolean renderEditedTableByBoard;
	private boolean renderDatePanel;
	private boolean renderDataTable;
	private String useremail;
	private int listSize;
	private int contactSize;
	private int userId;
	private int countUserSize;
	private int userLoginSize;
	private Date from, to;
	private int days;
	private int staffSize;
	private int staffDetails;
	private String boardName;
	private String range;
	private String selection;
	private List<Contact> contactDetail = new ArrayList<Contact>();
	private List<ContactDto> contactDtosInfo = new ArrayList<ContactDto>();

	/*
	 * private String viewId; private String fname; private String lname;
	 */
	/* class injection */
	JSFBoundleProvider provider = new JSFBoundleProvider();
	UserImpl usersImpl = new UserImpl();
	ContactImpl contactImpl = new ContactImpl();
	Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		addOthercontacts();
		HttpSession session = SessionUtils.getSession();
		usersSession = (Users) session.getAttribute("userSession");

		if (contact == null) {
			contact = new Contact();
		}

		if (users == null) {
			users = new Users();
		}
		if (userDto == null) {
			userDto = new UserDto();
		}
		if (contactDto == null) {
			contactDto = new ContactDto();
		}
		if (categ == null) {
			categ = new UserCategory();
		}
		try {
			contactDetails = contactImpl.getGenericListWithHQLParameter(new String[] { "genericStatus" },
					new Object[] { ACTIVE }, "Contact", "contactId asc");

			contactDtoDetails = displayContactByDateBetween(contactDetails);

			contactSize = contactDtoDetails.size();
			for (Object[] data : usersImpl.reportList(
					"select us.fname,us.lname, us.viewId,us.address, us.userId,us.userCategory from Contact co right  join  co.user us where co.user is null")) {

				LOGGER.info("users>>" + data[0] + ":: " + data[1] + "");
				Users users = new Users();
				
				if(((UserCategory)data[5]).getUserCatid()!=1) {
				users.setFname(data[0] + "");
				users.setLname(data[1] + "");
				users.setViewId(data[2] + "");
				users.setAddress(data[3] + "");
				users.setUserId(Integer.parseInt(data[4] + ""));
				usersDetails.add(users);
				}
			}
			listSize = usersDetails.size();
			// count total number of registered users

			/*countUsersList = usersImpl.getGenericListWithHQLParameter(new String[] { "genericStatus" },
					new Object[] { ACTIVE }, "Users", "userId desc");*/

			for (Object[] data : usersImpl.reportList(
					"select us.fname,us.lname,us.userCategory, cat.userCatid,cat.usercategoryName from UserCategory cat,Users us where us.userCategory=cat.userCatid and us.board is not null and cat.usercategoryName<>'" + SUPER_ADMIN + "'")) {
				Users us = new Users();
				us.setFname(data[0] + "");
				us.setLname(data[1] + "");
				us.setUserCategory((UserCategory)data[2]);
				countUsersList.add(us);
			}

			countUserSize = showAllUsersSize(countUsersList);
			// End of the registers users

			// count total number of user and showing users able to login
			countUsersList = usersImpl.getGenericListWithHQLParameter(new String[] { "loginStatus", "genericStatus" },
					new Object[] { ONLINE, ACTIVE }, "Users", "userId desc");

			userLoginSize = showAllLoginUsersSize(countUsersList);

			// End of login users size

			contactDetail = contactImpl.getListWithHQL("from Contact", 0, endrecord);
			
			
			contactDtosInfo = showUserDetails(contactDetail);
			//Staff Details size
			staffDetails=contactDtosInfo.size();
			contactDtoDetails = showUserDetails(contactDetail);
		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}

	}

	public void showUsersContact() throws Exception {

		if (range.equals("5") || (range.equals("10")) || (range.equals("15"))) {
			int endRecords = Integer.parseInt(range);
			contactDetail = contactImpl.getListWithHQL("from Contact", 0, endRecords);
			contactDtosInfo = showUserDetails(contactDetail);
			this.renderDatePanel = false;

		} else {
			this.renderDatePanel = true;

		}
	}

	public void showUsersEditedContact() throws Exception {

		if (range.equals("5") || (range.equals("10")) || (range.equals("15"))) {
			int endRecords = Integer.parseInt(range);
			contactDetail = contactImpl.getListWithHQL("from Contact", 0, endRecords);
			contactDtoDetails = showUserDetails(contactDetail);
			this.renderDatePanel = false;

		} else {
			this.renderDatePanel = true;

		}
	}

	
	@SuppressWarnings({ "static-access", "unchecked" })
	public void showContactFiltered() {
		try {
			if (null != to && null != from) {
				if (to.after(from)) {
					Formating fmt = new Formating();
					LOGGER.info("Start Date found:-----------:" + fmt.getMysqlFormatV2(from) + "End Date:::::::::"
							+ fmt.getMysqlFormatV2(to));
					days = fmt.daysBetween(from, to);
					if (days <= 30) {
						renderTable = true;
						contactDtoDetails = filterContactByDate(from, to);

					} else {
						setValid(false);
						JSFMessagers
								.addErrorMessage(getProvider().getValue("com.server.side.internal.invalidDaysRange"));
					}
				} else {

					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.invalidRange"));
				}
			} else {
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.invalidDate"));
			}
		} catch (Exception e) {
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.errorDate"));
			LOGGER.info(CLASSNAME + "" + e.getMessage());
			e.printStackTrace();
		}
	}

	public List<ContactDto> showUserDetails(List<Contact> contactList) {

		try {
			contactDtosInfo = new ArrayList<ContactDto>();
			for (Contact contact : contactList) {
				ContactDto contactDto = new ContactDto();
				if(contact.getUser().getUserCategory()!=null) {
					contactDto.setContactId(contact.getContactId());
					contactDto.setViewId(contact.getUser().getViewId());
					contactDto.setPhone(contact.getPhone());
					contactDto.setEmail(contact.getEmail());
					contactDto.setStaffNames(contact.getUser().getFname() + " " + contact.getUser().getLname());
					contactDto.setCategoryname(contact.getUser().getUserCategory().getUsercategoryName());
					contactDto.setUser(contact.getUser());
					contactDtosInfo.add(contactDto);
				}	
			}
		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}
		return (contactDtosInfo);
	}

	@SuppressWarnings({ "static-access" })
	public List<ContactDto> filterContactByDate(Date from, Date to) {

		Formating fmt = new Formating();
		contactDtoDetails = new ArrayList<ContactDto>();

		try {
			for (Object[] data : contactImpl.reportList(
					"select co.contactId,co.contactDetails,co.email,co.phone,co.user from Users us,Contact co where co.user=us.userId and co.crtdDtTime between '"
							+ fmt.getMysqlFormatV2(from) + "' and  '" + Formating.getMysqlFormatV2(to) + "'")) {

				ContactDto contDto = new ContactDto();
				contDto.setEditable(false);
				contDto.setContactDetails(data[1] + "");
				contDto.setEmail(data[2] + "");
				contDto.setPhone(data[3] + "");
				contDto.setContactId(Integer.parseInt(data[0] + ""));
				contDto.setStaffNames(data[4] + "");
				contDto.setUser((Users) data[4]);
				contactDtoDetails.add(contDto);
			}
		} catch (ParseException e) {
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.errorfilter"));
			LOGGER.info(CLASSNAME + "" + e.getMessage());
			e.printStackTrace();
		}

		return contactDtoDetails;

	}

	public int showAllUsersSize(List<Users> Usersize) {
		List<Users> addusers = new ArrayList<Users>();
		for (Users user : Usersize) {
			addusers.add(user);
		}
		return (addusers.size());
	}

	public int showAllLoginUsersSize(List<Users> Usersize) {
		List<Users> addusers = new ArrayList<Users>();
		for (Users user : Usersize) {
			addusers.add(user);
		}
		return (addusers.size());
	}

	public void addcontacts() {
		HttpSession session = SessionUtils.getSession();
		// Get the values from the session
		users = (Users) session.getAttribute("userinfo");
	}

	public void showAllusers() {
			rendered = false;
		renderContactForm = false;
		renderTable = true;
		renderLoginTable = false;
		planRender = false;
	}

	public void showContacts() {
			this.rendered = true;
		this.renderContactDash = false;
		/*
		 * renderTable = false; renderLoginTable = false; planRender= false;
		 */
	}

	public void showContactDashBoard() {
		this.renderContactDash = true;
		this.renderForeignCountry = false;
		this.rendered = false;
		this.renderLoginTable = false;
		this.renderDatePanel = false;
		this.renderEditedTableByBoard = false;
		this.renderDataTable = false;

	}

	@SuppressWarnings("static-access")
	public void displayUsersDetailsByDateBetween() {

		Formating fmt = new Formating();
		contactDtosInfo = new ArrayList<ContactDto>();

		try {
			if (null != to && null != from) {
				if (to.after(from)) {

					LOGGER.info("tart Date form::" + from + "::end form::" + to);
					LOGGER.info("Here We are :--------------->>" + "Start Date:" + fmt.getMysqlFormatV3(from)
							+ "End Date:-------->>>" + fmt.getMysqlFormatV3(to));
					days = fmt.daysBetween(from, to);

					LOGGER.info("Days founded:......................" + days);
					if (days <= 30) {

						for (Object[] data : contactImpl.reportList(
								"select co.contactId,bo.boardName,co.email,co.phone,us.fname,us.lname,cat.usercategoryName from Users us,Contact co,Board bo,UserCategory cat where co.user=us.userId and us.board=bo.boardId and us.userCategory=cat.userCatid and co.crtdDtTime between '"
										+ fmt.getMysqlFormatV3(from) + "' and  '" + Formating.getMysqlFormatV3(to)
										+ "'")) {

							ContactDto contDto = new ContactDto();
							contDto.setEditable(false);
							contDto.setBoardname(data[1] + "");
							contDto.setEmail(data[2] + "");
							contDto.setPhone(data[3] + "");
							contDto.setContactId(Integer.parseInt(data[0] + ""));
							contDto.setStaffNames(data[4] + "  " + data[5] + " ");
							contDto.setCategoryname(data[6] + " ");
							contactDtosInfo.add(contDto);
						}

					} else {
						setValid(false);
						JSFMessagers
								.addErrorMessage(getProvider().getValue("com.server.side.internal.invalidDaysRange"));
					}
				} else {
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.invalidRange"));
				}
			} else {
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.invalidDate"));
			}

		} catch (ParseException e) {
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.errorfilter"));
			LOGGER.info(CLASSNAME + "" + e.getMessage());
			e.printStackTrace();
		}

	}

	public void showLoginUsers() {
		if (userLoginSize != 0)
			renderTable = false;
		renderLoginTable = true;
		rendered = false;
		planRender = false;
		this.renderContactDash = false;
		this.renderForeignCountry = false;

	}

	public void showPlan() {
		planRender = true;
		renderTable = false;
		rendered = false;
		renderLoginTable = false;

	}

	public List<ContactDto> displayContactByDateBetween(List<Contact> contactDto) {

		List<ContactDto> contactDtoDetails = new ArrayList<ContactDto>();
		for (Contact contact : contactDetails) {
			ContactDto contDto = new ContactDto();
			contDto.setEditable(false);
			if(contact.getUser().getUserCategory().getUserCatid()!=1) {
			contDto.setContactDetails(contact.getContactDetails());
			contDto.setEmail(contact.getEmail());
			contDto.setPhone(contact.getPhone());
			contDto.setContactId(contact.getContactId());
			contDto.setUser(contact.getUser());
			contactDtoDetails.add(contDto);
			}
		}
		return (contactDtoDetails);
	}

	public String saveUserNewContact() {

		try {

			try {

				Contact ct = new Contact();

				LOGGER.info("EMAIL AND PHONE DETAILS::::" + contact.getEmail() + ":::::::" + contact.getPhone());
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
				// :::sending email action:::::::::::://
				// contact.setEmail(useremail);
				contact.setUpdatedBy(usersSession.getViewId());
				// :::saving contact action:::::::::::://
				contactImpl.saveContact(contact);
				// :::::End of saving:::::::::::::// JSFMessagers.resetMessages();
				JSFMessagers.resetMessages();
				setValid(true);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.contact"));
				LOGGER.info("OBTAINED DETAILS:" + users.getFname() + "::::::::" + users.getLname() + ":::::::::"
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
					JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.notificationError"));

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

	public String saveContact() throws Exception {
		try {

			try {

				Contact ct = new Contact();
				LOGGER.info("EMAIL AND PHONE DETAILS::::" + contact.getEmail() + ":::::::" + contact.getPhone());
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
				setValid(true);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.contact"));
				LOGGER.info("OBTAINED DETAILS:" + users.getFname() + "::::::::" + users.getLname() + ":::::::::"
						+ contact.getEmail());
				boolean verifyemail = support.sendMailTestVersion(users.getFname(), users.getLname(),
						contact.getEmail());
				// ::: end sending email action:::::::::::://
				if (verifyemail) {
					setValid(true);
					JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.email.notifications"));
					LOGGER.info(CLASSNAME + ":::Contact Details is saved");
				} else {
					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.email.notifail"));
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

	public String saveAction(UserDto user) {
		LOGGER.info("update  saveAction method");
		// get all existing value but set "editable" to false
		try {
			if (null != user) {
				Users us = new Users();
				us = new Users();
				us = usersImpl.gettUserById(user.getUserId(), "userId");

				LOGGER.info("here update sart for " + us + " useriD " + us.getUserId());

				user.setEditable(false);
				us.setFname(user.getFname());
				us.setLname(user.getLname());

				usersImpl.UpdateUsers(us);
				// return to current page
				JSFMessagers.resetMessages();
				setValid(true);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.contactupdate"));
				return "/menu/ListOfUsers.xhtml?faces-redirect=true";
			} else {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.errorupdatecontact"));
			}
		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.updateErrorcontact"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public String cancel(UserDto user) {
		user.setEditable(false);
		// usersImpl.UpdateUsers(user);
		return null;

	}

	public String editAction(UserDto user) {

		user.setEditable(true);
		// usersImpl.UpdateUsers(user);
		return null;
	}

	public void editUserContact(Users user) {
		HttpSession sessionuser = SessionUtils.getSession();

		if (null != user)
			renderContactForm = true;
		rendered = false;
		renderForeignCountry = false;
		// Session creation to get user info from dataTable row
		sessionuser.setAttribute("userinfo", user);
		LOGGER.info("Info Founded are userid:>>>>>>>>>>>>>>>>>>>>>>>:" + userId + "fname:" + user.getFname());
		addcontacts();
	}

	public String otherUserContact(ContactDto cont) {
		HttpSession sessionuser = SessionUtils.getSession();

		if (null != cont) {
			/*
			 * renderContactForm = true;
			 * 
			 * rendered = false; renderForeignCountry = false;
			 */

			// Session creation to get user info from dataTable row
			sessionuser.setAttribute("continfo", cont);
			LOGGER.info("Info Founded are userid:>>>>>>>>>>>>>>>>>>>>>>>:" + userId + "fname:"
					+ cont.getUser().getUserId());
			addOthercontacts();
			return "/menu/UserContacts.xhtml?faces-redirect=true";
		}
		return null;
	}

	public void addOthercontacts() {
		HttpSession session = SessionUtils.getSession();
		// Get the values from the session
		contactDto = (ContactDto) session.getAttribute("continfo");
	}

	public String saveContactAction(ContactDto contact) {
		LOGGER.info("update  saveAction method");
		// get all existing value but set "editable" to false
		try {
			if (null != contact) {
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
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.contactupdate"));
				return null;
			} else {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.errorupdatecontact"));
			}
		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.updateErrorcontact"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public String cancelContact(ContactDto contact) {
		contact.setEditable(false);
		// usersImpl.UpdateUsers(user);
		return null;

	}

	public String editContactAction(ContactDto contact) {

		contact.setEditable(true);
		// usersImpl.UpdateUsers(user);
		return null;
	}

	public String addNewContact() {

		return "/menu/UserContacts.xhtml?faces-redirect=true";
	}

	public void updateTable() throws Exception {

		Users user = new Users();
		user = usersImpl.getModelWithMyHQL(new String[] { "viewId", "genericstatus" }, new Object[] { choice, ACTIVE },
				"from Users");
		if (null != user) {
			rendered = true;
			renderForeignCountry = false;
			JSFMessagers.resetMessages();
			setValid(true);
			// JSFMessagers.addErrorMessage(getProvider().getValue("error.server.side.message.viewid"));
			LOGGER.info(CLASSNAME + "sivaserside validation :: username found in the system! ");
		} else {
			rendered = false;
			renderForeignCountry = true;
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("error.server.side.message.viewid"));
		}

	}

	public void renderUserByDate() {
		renderEditedTableByBoard = true;
		renderContactDash = false;
	}

	public void showDatePanel() {

		if (selection.equals(Date_opt)) {
			renderDatePanel = true;
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

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
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

	public ContactImpl getContactImpl() {
		return contactImpl;
	}

	public void setContactImpl(ContactImpl contactImpl) {
		this.contactImpl = contactImpl;
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

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public List<Users> getUsersDetails() {
		return usersDetails;
	}

	public void setUsersDetails(List<Users> usersDetails) {
		this.usersDetails = usersDetails;
	}

	public List<UserDto> getUserDtoDetails() {
		return userDtoDetails;
	}

	public void setUserDtoDetails(List<UserDto> userDtoDetails) {
		this.userDtoDetails = userDtoDetails;
	}

	public void setUserIdNumber(int userIdNumber) {
		this.userIdNumber = userIdNumber;
	}

	public List<UserDto> getUserDtofiltered() {
		return userDtofiltered;
	}

	public void setUserDtofiltered(List<UserDto> userDtofiltered) {
		this.userDtofiltered = userDtofiltered;
	}

	public List<ContactDto> getContactDtoDetails() {
		return contactDtoDetails;
	}

	public void setContactDtoDetails(List<ContactDto> contactDtoDetails) {
		this.contactDtoDetails = contactDtoDetails;
	}

	public List<Contact> getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(List<Contact> contactDetails) {
		this.contactDetails = contactDetails;
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

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean isPlanRender() {
		return planRender;
	}

	public void setPlanRender(boolean planRender) {
		this.planRender = planRender;
	}

	public boolean isRenderContactForm() {
		return renderContactForm;
	}

	public void setRenderContactForm(boolean renderContactForm) {
		this.renderContactForm = renderContactForm;
	}

	public boolean isRenderTable() {
		return renderTable;
	}

	public void setRenderTable(boolean renderTable) {
		this.renderTable = renderTable;
	}

	public int getContactSize() {
		return contactSize;
	}

	public void setContactSize(int contactSize) {
		this.contactSize = contactSize;
	}

	public List<Users> getCountUsersList() {
		return countUsersList;
	}

	public void setCountUsersList(List<Users> countUsersList) {
		this.countUsersList = countUsersList;
	}

	public int getCountUserSize() {
		return countUserSize;
	}

	public void setCountUserSize(int countUserSize) {
		this.countUserSize = countUserSize;
	}

	public List<Users> getHoldCountedList() {
		return holdCountedList;
	}

	public void setHoldCountedList(List<Users> holdCountedList) {
		this.holdCountedList = holdCountedList;
	}

	public int getUserLoginSize() {
		return userLoginSize;
	}

	public void setUserLoginSize(int userLoginSize) {
		this.userLoginSize = userLoginSize;
	}

	public boolean isRenderLoginTable() {
		return renderLoginTable;
	}

	public void setRenderLoginTable(boolean renderLoginTable) {
		this.renderLoginTable = renderLoginTable;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public ContactDto getContactDto() {
		return contactDto;
	}

	public void setContactDto(ContactDto contactDto) {
		this.contactDto = contactDto;
	}

	public int getStaffSize() {
		return staffSize;
	}

	public void setStaffSize(int staffSize) {
		this.staffSize = staffSize;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public boolean isRenderContactDash() {
		return renderContactDash;
	}

	public void setRenderContactDash(boolean renderContactDash) {
		this.renderContactDash = renderContactDash;
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

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public List<UserDto> getUserDtosDetails() {
		return userDtosDetails;
	}

	public void setUserDtosDetails(List<UserDto> userDtosDetails) {
		this.userDtosDetails = userDtosDetails;
	}

	public boolean isRenderDataTable() {
		return renderDataTable;
	}

	public void setRenderDataTable(boolean renderDataTable) {
		this.renderDataTable = renderDataTable;
	}

	public List<Contact> getContactDetail() {
		return contactDetail;
	}

	public void setContactDetail(List<Contact> contactDetail) {
		this.contactDetail = contactDetail;
	}

	public List<ContactDto> getContactDtosInfo() {
		return contactDtosInfo;
	}

	public void setContactDtosInfo(List<ContactDto> contactDtosInfo) {
		this.contactDtosInfo = contactDtosInfo;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public UserCategory getCateg() {
		return categ;
	}

	public void setCateg(UserCategory categ) {
		this.categ = categ;
	}

	public int getStaffDetails() {
		return staffDetails;
	}

	public void setStaffDetails(int staffDetails) {
		this.staffDetails = staffDetails;
	}

}
