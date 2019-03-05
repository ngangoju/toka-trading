package com.toka.trading;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
import toka.dao.impl.UserCategoryImpl;
import toka.dao.impl.UserImpl;
import toka.domain.Contact;
import toka.domain.UserCategory;
import toka.domain.Users;
import toka.trading.dto.ContactDto;
import toka.trading.dto.UserCategoryDto;
import toka.trading.dto.UserDto;

@ManagedBean
@ViewScoped
public class UserCategoryController implements Serializable, DbConstant {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
	private String CLASSNAME = "UserCategoryController :: ";
	private static final long serialVersionUID = 1L;
	/* to manage validation messages */
	private boolean isValid;
	/* end manage validation messages */
	private UserCategory userCategory;
	private Users usersSession;
	private List<UserCategory> categoryDetails = new ArrayList<UserCategory>();
	private List<Contact> contactDetails = new ArrayList<Contact>();
	private List<ContactDto> contactDtoDetails = new ArrayList<ContactDto>();
	private List<UserCategoryDto> categoryDtoDetails = new ArrayList<UserCategoryDto>();
	/* class injection */
	JSFBoundleProvider provider = new JSFBoundleProvider();
	UserImpl usersImpl = new UserImpl();
	ContactImpl contactImpl = new ContactImpl();
	private boolean rendered;
	UserCategoryImpl userCatImpl = new UserCategoryImpl();
	Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
	private String repEmail;
	private String option;
	private String range;
	private Contact cont = new Contact();

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		HttpSession session = SessionUtils.getSession();
		usersSession = (Users) session.getAttribute("userSession");

		if (userCategory == null) {
			userCategory = new UserCategory();
		}
		if (cont == null) {
			cont = new Contact();
		}

		try {
			//categoryDetails = userCatImpl.getGenericListWithHQLParameter(new String[] { "genericStatus" },new Object[] { ACTIVE }, "UserCategory", "userCatid desc");
			categoryDetails=userCatImpl.getListWithHQL("from UserCategory", 0, endCateRecord);

			categoryDtoDetails = listCategory(categoryDetails);
			
				
		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}

	}
	@SuppressWarnings("unchecked")
	public void showCategory() throws Exception {
	if (range.equals("5") || (range.equals("10")) || (range.equals("15"))) {
			int endRecords = Integer.parseInt(range);
			categoryDetails=userCatImpl.getListWithHQL("from UserCategory", 0, endRecords);
			categoryDtoDetails = listCategory(categoryDetails);
			//this.renderFooter = true;
		} else {
			categoryDetails = userCatImpl.getGenericListWithHQLParameter(new String[] { "genericStatus" },new Object[] { ACTIVE }, "UserCategory", "userCatid desc");
			categoryDtoDetails = listCategory(categoryDetails);
		}
	}

	@SuppressWarnings("rawtypes")
	public List listCategory(List<UserCategory> userCatDetails) {
		List<UserCategoryDto> categoryDtoDetails = new ArrayList<UserCategoryDto>();
		for (UserCategory userCat : userCatDetails) {
			UserCategoryDto catDto = new UserCategoryDto();
			catDto.setEditable(false);
			catDto.setNotify(false);
			catDto.setUserCatid(userCat.getUserCatid());
			catDto.setUsercategoryName(userCat.getUsercategoryName());
			catDto.setStatus(userCat.getStatus());
			if (userCat.getStatus().equals(ACTIVE)) {
				catDto.setAction(DESACTIVE);
			} else {
				catDto.setAction(ACTIVE);
			}
			categoryDtoDetails.add(catDto);
		}
		return (categoryDtoDetails);
	}

	@SuppressWarnings("unchecked")
	public String updateStatus(UserCategoryDto categ) throws Exception {
		LOGGER.info("update  saveAction method");
		// get all existing value but set "editable" to false
		UserCategory cat = new UserCategory();

		if (categ != null)
			cat = userCatImpl.getUserCategoryById(categ.getUserCatid(), "userCatid");
		if (cat != null)
			LOGGER.info("here update sart for " + cat + " useriD " + cat.getStatus());
		if (categ.getStatus().equals(ACTIVE)) {
			cat.setUpdatedBy(usersSession.getViewId());
			cat.setUpDtTime(timestamp);
			cat.setStatus(DESACTIVE);
			categ.setNotify(false);
		} else {
			cat.setUpdatedBy(usersSession.getViewId());
			cat.setUpDtTime(timestamp);
			cat.setStatus(ACTIVE);
			categ.setNotify(false);
		}
		Contact ct = new Contact();

		if (null != repEmail) {
			ct = contactImpl.getModelWithMyHQL(new String[] { "email" }, new Object[] { repEmail }, "from Contact");
			if (null != ct) {
				userCatImpl.UpdateUsercategory(cat);
				categoryDetails = userCatImpl.getGenericListWithHQLParameter(new String[] { "genericStatus" },
						new Object[] { ACTIVE }, "UserCategory", "userCatid desc");
				categoryDtoDetails = listCategory(categoryDetails);
				LOGGER.info("EMAIL TO NOTIFY::::::::::::::::::::::::::::::::::::::" + repEmail);
				boolean valid = notifyRepresentativeChange(repEmail);
				if (valid) {
					JSFMessagers.resetMessages();
					setValid(true);
					JSFMessagers.addErrorMessage(getProvider().getValue("com.notify.representative.cat"));
					LOGGER.info(CLASSNAME + "::Email sent successuful!!");
					this.repEmail = null;
					// return to current page
				} else {
					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("com.notifyError.representative.cat"));
					LOGGER.info(CLASSNAME + "::Fail to send Email!!");
					this.repEmail = null;
				}
			} else {
				this.repEmail = null;
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

	public String SaveUserCategory() {

		try {
			try {
				UserCategory userCat = new UserCategory();
				userCat = userCatImpl.getModelWithMyHQL(new String[] { "usercategoryName" },
						new Object[] { userCategory.getUsercategoryName() }, "from UserCategory");
				if (null != userCat) {
					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("error.server.side.dupicate.categoryname"));
					LOGGER.info(
							CLASSNAME + "sivaserside validation ::  Category Name already  recorded in the system! ");
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
			userCategory.setCreatedBy(usersSession.getViewId());
			userCategory.setCrtdDtTime(timestamp);
			userCategory.setGenericStatus(ACTIVE);
			userCategory.setStatus(ACTIVE);
			userCategory.setUpdatedBy(usersSession.getViewId());
			userCategory.setCrtdDtTime(timestamp);
			userCatImpl.saveUsercategory(userCategory);
			JSFMessagers.resetMessages();
			setValid(true);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.usercategory"));
			LOGGER.info(CLASSNAME + "::UserCategory Details is saved");
			clearCategoryFuileds();
			return null;
		} catch (HibernateException e) {
			// TODO: handle exception
			LOGGER.info(CLASSNAME + ":::Contact Details is fail with HibernateException  error");
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(CLASSNAME + "" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public void showCatTable() {
		/* if (categoryDtoDetails.size() != 0) */
		rendered = true;
	}

	private void clearCategoryFuileds() {
		userCategory = new UserCategory();
		categoryDetails = null;
	}

	public String saveAction(UserCategoryDto cat) {
		LOGGER.info("update  saveAction method");
		// get all existing value but set "editable" to false
		try {
			if (null != cat) {
				LOGGER.info("UserCat:++++++++++++++++++++++++++" + cat.getUserCatid());
				UserCategory usercat = new UserCategory();
				usercat = new UserCategory();
				usercat = userCatImpl.getUserCategoryById(cat.getUserCatid(), "userCatid");

				LOGGER.info("here update sart for " + usercat + " useriD " + usercat.getUserCatid());

				cat.setEditable(false);
				usercat.setUpdatedBy(usersSession.getViewId());
				usercat.setUpDtTime(timestamp);
				usercat.setUsercategoryName(cat.getUsercategoryName());
				userCatImpl.UpdateUsercategory(usercat);
				JSFMessagers.resetMessages();
				setValid(true);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.categoryupdate"));
			} else {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.errorupdate"));
			}
		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.updateError"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}

		// return to current page
		return null;

	}

	public String addNewCategory() {

		return "/menu/UserCategory.xhtml?faces-redirect=true";
	}

	public String cancel(UserCategoryDto cat) {
		cat.setEditable(false);
		return null;
	}

	public String cancelChange(UserCategoryDto cat) {
		cat.setNotify(false);
		this.repEmail = null;
		return null;
	}

	public String otherUserCategory() {
		return "/menu/UserCategory.xhtml?faces-redirect=true";

	}

	public String editAction(UserCategoryDto cat) {

		cat.setEditable(true);
		return null;
	}

	@SuppressWarnings("unchecked")
	public String renderAction(UserCategoryDto cat) throws Exception {
		cat.setNotify(true);
		/*List<Contact>contactList= new ArrayList<Contact>();
		contactList=contactImpl.getGenericListWithHQLParameter(new String[] { "genericStatus" },
				new Object[] { ACTIVE}, "Contact", "contactId asc");*/
		contactDetails= new ArrayList<Contact>();
		for (Object[] data : usersImpl.reportList(
				"select us.fname,us.lname, us.viewId,us.address, us.userId,co.email,co.phone from Contact co right  join  co.user us join us.userCategory cat where co.user is not null and cat.usercategoryName='"
						+ INSTITUTE_REP + "'")) {
			LOGGER.info("users>>" + data[0] + ":: " + data[1] + "");			
			Contact cont= new Contact();
			cont.setEmail(data[5]+"");
			cont.setPhone(data[6]+"");
			contactDetails.add(cont);
		}	
		return null;
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

	public UserCategory getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(UserCategory userCategory) {
		this.userCategory = userCategory;
	}

	public Users getUsersSession() {
		return usersSession;
	}

	public void setUsersSession(Users usersSession) {
		this.usersSession = usersSession;
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

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public List<UserCategory> getCategoryDetails() {
		return categoryDetails;
	}

	public void setCategoryDetails(List<UserCategory> categoryDetails) {
		this.categoryDetails = categoryDetails;
	}

	public List<UserCategoryDto> getCategoryDtoDetails() {
		return categoryDtoDetails;
	}

	public void setCategoryDtoDetails(List<UserCategoryDto> categoryDtoDetails) {
		this.categoryDtoDetails = categoryDtoDetails;
	}

	public UserCategoryImpl getUserCatImpl() {
		return userCatImpl;
	}

	public void setUserCatImpl(UserCategoryImpl userCatImpl) {
		this.userCatImpl = userCatImpl;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}

	public String getRepEmail() {
		return repEmail;
	}

	public void setRepEmail(String repEmail) {
		this.repEmail = repEmail;
	}

	public List<Contact> getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(List<Contact> contactDetails) {
		this.contactDetails = contactDetails;
	}

	public ContactImpl getContactImpl() {
		return contactImpl;
	}

	public void setContactImpl(ContactImpl contactImpl) {
		this.contactImpl = contactImpl;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public Contact getCont() {
		return cont;
	}

	public void setCont(Contact cont) {
		this.cont = cont;
	}

	public List<ContactDto> getContactDtoDetails() {
		return contactDtoDetails;
	}

	public void setContactDtoDetails(List<ContactDto> contactDtoDetails) {
		this.contactDtoDetails = contactDtoDetails;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	
}
