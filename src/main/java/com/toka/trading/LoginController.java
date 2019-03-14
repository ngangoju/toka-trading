package com.toka.trading;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import toka.common.DbConstant;
import toka.common.JSFBoundleProvider;
import toka.common.JSFMessagers;
import toka.common.SessionUtils;
import toka.dao.impl.LoginHistoricImpl;
import toka.dao.impl.LoginImpl;
import toka.dao.impl.UserImpl;
import toka.domain.Users;


@ManagedBean
@SessionScoped
public class LoginController implements Serializable, DbConstant {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
	private String CLASSNAME = "LoginController :: ";
	private static final long serialVersionUID = 1L;
	/* to manage validation messages */
	private boolean isValid;
	/* end manage validation messages */
	private Users users;
	private Users logedusers;
	/* class injection */
	JSFBoundleProvider provider = new JSFBoundleProvider();
	LoginImpl loginDao1 = new LoginImpl();
	UserImpl usersImpl = new UserImpl();
	LoginHistoricImpl historic = new LoginHistoricImpl();
	/* end class injection */
	Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
	boolean isValidCredential;

	@PostConstruct
	public void init() {

		if (users == null) {
			users = new Users();
		}
		if(logedusers==null) {
			
			logedusers= new Users();
		}

	}

	public String validateUsernamePassword() {
		LOGGER.info(CLASSNAME + ":::step one");
		HttpSession session = SessionUtils.getSession();

		Users user = new Users();
		try {

		/*	user = usersImpl.getModelWithMyHQL(new String[] { EMAIL, PASSWORD },
					new Object[] { users.getEmail(), loginDao1.criptPassword(users.getPassword()) }, SELECT_USERS);
			LOGGER.info("check username and password done :::" + users.getUsername());
			if (user != null) {
				isValidCredential = true;

				LOGGER.info("check username and password is correct:::");
			} else {
				isValidCredential = false;
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.validat.user.password"));
				LOGGER.info("check username and password is incorrect:::");
			}

			if (user != null && (user.getStatus()).equals(ACTIVE)) {
			} else {
				isValidCredential = false;
			}*/
			return "dashboard.xhtml?faces-redirect=true";
		} catch (Exception jj) {
			LOGGER.info("errrr:::::::::");
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(jj.getMessage());
			jj.printStackTrace();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
		}
		// Check if the account is locked
		/*if (isValidCredential) {

			try {

				LOGGER.info(CLASSNAME + "LoginHistoric saving start for machine ip" + historic.getMachineIp());

				LoginHistoric his = new LoginHistoric();

				his.setHistoricId(0);
				his.setIpAddress(historic.getMachineIp());
				his.setLoginTimeIn(new Date());
				his.setCreatedBy(user.getFname() + " " + user.getLname());
				his.setUpDtTime(timestamp);

				his.setUsers(user);
				session.setAttribute("userSession", user);

				Object a = historic.saveLoginHistoric(his);
				// session.setAttribute("loginID", a);
				LOGGER.info(CLASSNAME + "Loging Save Login Historic");
				LOGGER.info("step 111");
				user.setLoginStatus(ONLINE);
				usersImpl.UpdateUsers(user);
				LOGGER.info(CLASSNAME + "Creating Sessions for users::" + user.getFname());
			} catch (Exception ex) {
				LOGGER.info(CLASSNAME + "Loging Fail whene login" + ex.getMessage());
				setValid(false);
				JSFMessagers.addInfoMessage("com.server.side.internal.error");
				ex.printStackTrace();
			}

			if (user != null && user.getUserCategory().getUserCatid() == 1) {// ADMIN

				LOGGER.info(CLASSNAME + ":::ADMIN ");
				//clearCredentials();
				logedusers=(Users) session.getAttribute("userSession");
				return "index.xhtml?faces-redirect=true";
				// return "";
			} else 	if (user != null && user.getUserCategory().getUserCatid() == 2) {//Club Representative

				LOGGER.info("login on 1 page for Club Representative ");
				//clearCredentials();
				logedusers=(Users) session.getAttribute("userSession");
				return "index_v1.xhtml?faces-redirect=true";
			}else {
				LOGGER.info("login on 1 page for registered Guest ");
				//clearCredentials();
				logedusers=(Users) session.getAttribute("userSession");
				return "index_v2.xhtml?faces-redirect=true";
			}

		} else {
			LOGGER.info(CLASSNAME + "credential are invalid ");
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.validat.user.password"));
			clearCredentials();
			return "";
		}
*/
		return "dashboard.xhtml?faces-redirect=true";
	}

	// logout event, invalidate session
	public void logout() {
		HttpSession session = SessionUtils.getSession();

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		String url = request.getContextPath() + "/home.xhtml";
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} catch (IOException e) {

			e.printStackTrace();
		}
		session.invalidate();
	}
public void clearCredentials() {
	
	users= new Users();
}
	public JSFBoundleProvider getProvider() {
		return provider;
	}

	public void setProvider(JSFBoundleProvider provider) {
		this.provider = provider;
	}

	public LoginImpl getLoginDao1() {
		return loginDao1;
	}

	public void setLoginDao1(LoginImpl loginDao1) {
		this.loginDao1 = loginDao1;
	}

	public LoginHistoricImpl getHistoric() {
		return historic;
	}

	public void setHistoric(LoginHistoricImpl historic) {
		this.historic = historic;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public Users getLogedusers() {
		return logedusers;
	}

	public void setLogedusers(Users logedusers) {
		this.logedusers = logedusers;
	}

	public UserImpl getUsersImpl() {
		return usersImpl;
	}

	public void setUsersImpl(UserImpl usersImpl) {
		this.usersImpl = usersImpl;
	}

}
