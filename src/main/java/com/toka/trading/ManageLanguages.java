package com.toka.trading;

import java.util.Locale;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import toka.common.SessionUtils;

@ManagedBean
@SessionScoped
public class ManageLanguages {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

	private String locale = "en";
	private String lg = "en";

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String changeLanguage(String locale) {
		this.locale = locale;
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(this.locale));
		HttpSession session = SessionUtils.getSession();
		session.setAttribute("lg", locale);
		return locale;
	}

	public String getLang() {
		HttpSession session = SessionUtils.getSession();
		String lg = "en";
		lg = (String) session.getAttribute("lg");
		if (lg != null) {
			ManageLanguages m = new ManageLanguages();
			m.changeLanguage(lg);
			LOGGER.info("login languages:::" + lg);
			this.locale = lg;
		}

		return locale;
	}

	public String getLg() {
		return lg;
	}

	public void setLg(String lg) {
		this.lg = lg;
	}

}