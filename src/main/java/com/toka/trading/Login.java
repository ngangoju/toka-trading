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
import toka.domain.LoginHistoric;
import toka.domain.Users;

@ManagedBean
@SessionScoped
public class Login implements Serializable, DbConstant {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
	private String CLASSNAME = "LoginController :: ";
	private static final long serialVersionUID = 1L;
	/* to manage validation messages */
	private boolean isValid;
	/* end manage validation messages */
	private Users users;
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

	}

	public String validateUsernamePassword() {
		LOGGER.info(CLASSNAME + ":::step one");
		
		HttpSession session = SessionUtils.getSession();
	if(session.getAttribute("userSession")!=null) {
		
		session.invalidate();	
	}
		Users user = new Users();
		try {

			user = usersImpl.getModelWithMyHQL(new String[] { USERNAME, PASSWORD },
					new Object[] { users.getViewId(), loginDao1.criptPassword(users.getViewName()) }, SELECT_USERS);
			LOGGER.info("check username and password done :::" + users.getViewId());
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
			}

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
		if (isValidCredential) {

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

				LOGGER.info(CLASSNAME + "Creating Sessions for users::" + user.getViewId());
			} catch (Exception ex) {
				LOGGER.info(CLASSNAME + "Loging Fail whene login" + ex.getMessage());
				setValid(false);
				JSFMessagers.addInfoMessage("com.server.side.internal.error");
				ex.printStackTrace();
			}

			if (user != null && user.getUserCategory().getUserCatid() == 1) {// ADMIN

				LOGGER.info(CLASSNAME + ":::ADMIN ");

				return "page1.xhtml?faces-redirect=true";
				// return "";
			} else {

				LOGGER.info("login on 1 page for all users ");
				return "page1.xhtml?faces-redirect=true";
			}

		} else {
			LOGGER.info(CLASSNAME + "credential are invalid ");
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.validat.user.password"));
			return "";
		}

	}

	// logout event, invalidate session
	public void logout() {
		HttpSession session = SessionUtils.getSession();

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		String url = request.getContextPath() + "/home.xhtml";
		try {
			
			Users user= new Users();
			user=(Users)session.getAttribute("userSession");
			LoginHistoric his = new LoginHistoric();

			his.setHistoricId(0);
			his.setIpAddress(historic.getMachineIp());
			his.setLogOutTime(new Date());
			his.setCreatedBy(user.getFname() + " " + user.getLname());
			his.setUpDtTime(timestamp);
			his.setUsers(user);
			historic.saveLoginHistoric(his);
			user.setLoginStatus(OFFLINE);
			usersImpl.UpdateUsers(user);
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} catch (IOException e) {

			e.printStackTrace();
		}
		session.invalidate();
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

}
