package com.toka.trading;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import toka.common.DbConstant;
import toka.common.GenerateNotificationTemplete;
import toka.common.JSFBoundleProvider;
import toka.common.JSFMessagers;
import toka.dao.impl.MenuAssignmentImpl;
import toka.dao.impl.MenuGroupImpl;
import toka.dao.impl.UserImpl;
import toka.domain.MenuAssignment;
import toka.domain.MenuGroup;
import toka.domain.Users;

/**
 * Servlet implementation class SendSupportEmail
 */
public class SendSupportEmail extends HttpServlet implements DbConstant {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
	private String CLASSNAME = "SendSupportEmail :: ";

	/* to manage validation messages */
	private boolean isValid;

	/* class injection */
	GenerateNotificationTemplete gen = new GenerateNotificationTemplete();
	JSFBoundleProvider provider = new JSFBoundleProvider();

	/* end class injection */
	Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fname = request.getParameter("name");
		String lname = request.getParameter("surname");
		String email = request.getParameter("email");
		String need = request.getParameter("need");
		String message = request.getParameter("message");

		sendMailTest(fname, lname, email, need, message);

		LOGGER.info(CLASSNAME + ":::notification sent to Support team");
		response.sendRedirect("default.xhtml?msg=Your request sent successfully..");

	}

	public void sendMailTest(String fname, String lname, String email, String need, String msgContent) {

		String msg = "<p>Please take look on the bellow request.</p>" + "<table width=\"50%\" border=\"5px\">\n"
				+ "  <tbody>\n" + "	<tr>" + "      <td class=\"labelbold\">Custome Names</td>\n" + "      <td>\n"
				+ "		  " + fname + " " + lname + "\n" + "	  </td>\n" + "    </tr>" + "	<tr>\n"
				+ "      <td class=\"labelbold\">Customer Email</td>\n" + "      <td>\n" + "		  " + email + "\n"
				+ "	  </td></tr>" + "	<tr>" + "      <td class=\"labelbold\">Customer Need</td>\n" + "      <td>\n"
				+ "		  " + need + "\n" + "	  </td></tr>"

				+ "	<tr>" + "      <td class=\"labelbold\">Customer Message</td>\n" + "      <td>\n" + "		  "
				+ msgContent + "\n" + "	  </td></tr>" + "<tr>" + "      <td class=\"labelbold\">Application URL</td>\n"
				+ "      <td> <a href='http://localhost:8080/vfpProject_v1/default.xhtml'>click here to acces the service</a>  </td></tr>"
				+ "  </tbody>\n" + "</table>\n";
		/* End send content in table sample */
		try {
			gen.sendEmailNotification("ngangoju@gmail.com", "Support Team ", need, msg);
		} catch (AddressException e) {
			setValid(false);
			e.printStackTrace();
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.emailerror"));
			e.printStackTrace();
		} catch (MessagingException e) {
			setValid(false);
			e.printStackTrace();
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.notificationError"));
			e.printStackTrace();
		}
		LOGGER.info("::: notidficatio sent   ");
	}

	public boolean sendMailStrategicPlan(String type, String fname, String senderName, String email) {

		boolean valid = false;
		if ((null != fname) && (null != email) && (null != senderName)) {
			String msg = null;
			if (type.equals("plan")) {
				msg = "<p>" + "I hope this email finds you well." + "<br/>"
						+ "This is to notify you the new strategic plan created, " + "<br/>"
						+ "you can now check it out and find the attached document with full details." + "<br/>"
						+ "</p>";
			} else if (type.equals("task")) {
				msg = "<p>" + "I hope this email finds you well." + "<br/>"
						+ "This is to notify you the new target created by your board superviser, " + "<br/>"
						+ "you can now check it out and start to create weekly activities." + "<br/>" + "</p>";
			}
			/* End send content in table sample */
			try {

				gen.sendEmailNotification(email, fname, "Strategic plan", msg);
				valid = true;
			} catch (AddressException e) {
				LOGGER.info("returing false1");
				valid = false;
				setValid(false);
				e.printStackTrace();
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.emailerror"));
				e.printStackTrace();
				LOGGER.info("returing false2");
				LOGGER.info("This content" + msg + " was not send to MY BE wrong address check email ::" + email
						+ " on " + timestamp);
			} catch (MessagingException e) {
				LOGGER.info("This content" + msg + " was not send to ::" + email + " on " + timestamp);
				valid = false;
				setValid(false);
				e.printStackTrace();
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.notificationError"));
				e.printStackTrace();
			}
			LOGGER.info("::: notidficatio sent   ");
		} else {
			valid = false;
		}
		LOGGER.info("returing values" + valid);
		return (valid);
	}

	public boolean sendMailTestVersion(String fname, String lname, String email) {

		boolean valid = false;
		if ((null != fname) && (null != lname) && (null != email)) {
			String msg = "<p>Please take look on the bellow request.</p>" + "<table width=\"50%\" border=\"5px\">\n"
					+ "  <tbody>\n" + "	<tr>" + "      <td class=\"labelbold\">Custome Names</td>\n" + "      <td>\n"
					+ "		  " + fname + " " + lname + "\n" + "	  </td>\n" + "    </tr>" + "	<tr>\n"
					+ "      <td class=\"labelbold\">Customer Email</td>\n" + "      <td>\n" + "		  " + email
					+ "\n" + "	  </td></tr>"

					+ "<tr>" + "      <td class=\"labelbold\">Application URL</td>\n" + "      <td> <a href=" + LINK
					+ "vfpProject_v1/default.xhtml>click here to acces the service</a>  </td></tr>" + "  </tbody>\n"
					+ "</table>\n";

			/* End send content in table sample */
			try {

				gen.sendEmailNotification(email, fname + " " + lname + "", "Support Team", msg);
				valid = true;
			} catch (AddressException e) {
				LOGGER.info("returing false1");
				valid = false;
				setValid(false);
				e.printStackTrace();
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.emailerror"));
				e.printStackTrace();
				LOGGER.info("returing false2");
				LOGGER.info("This content" + msg + " was not send to MY BE wrong address check email ::" + email
						+ " on " + timestamp);
			} catch (MessagingException e) {
				LOGGER.info("This content" + msg + " was not send to ::" + email + " on " + timestamp);
				valid = false;
				setValid(false);
				e.printStackTrace();
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.notificationError"));
				e.printStackTrace();
			}
			LOGGER.info("::: notidficatio sent   ");
		} else {
			valid = false;
		}
		LOGGER.info("returing values" + valid);
		return (valid);
	}

	public void sendMailForInstitution(String fname, String lname, String email, String need, String msgContent) {

		String msg = "<p>Please take look on the bellow request.</p>" + "<table width=\"50%\" border=\"5px\">\n"
				+ "  <tbody>\n" + "	<tr>" + "      <td class=\"labelbold\">Custome Names</td>\n" + "      <td>\n"
				+ "		  " + fname + " " + lname + "\n" + "	  </td>\n" + "    </tr>" + "	<tr>\n"
				+ "      <td class=\"labelbold\">Customer Email</td>\n" + "      <td>\n" + "		  " + email + "\n"
				+ "	  </td></tr>" + "	<tr>" + "      <td class=\"labelbold\">Customer Need</td>\n" + "      <td>\n"
				+ "		  " + need + "\n" + "	  </td></tr>"

				+ "	<tr>" + "      <td class=\"labelbold\">Customer Message</td>\n" + "      <td>\n" + "		  "
				+ msgContent + "\n" + "	  </td></tr>" + "<tr>" + "      <td class=\"labelbold\">Application URL</td>\n"
				+ "      <td> <a href='http://localhost:8080/vfpProject_v1/default.xhtml'>click here to acces the service</a>  </td></tr>"
				+ "  </tbody>\n" + "</table>\n";
		/* End send content in table sample */
		try {
			gen.sendEmailNotification(email, "Institution Registration Confirmation ", need, msg);
		} catch (AddressException e) {
			setValid(false);
			e.printStackTrace();
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.emailerror"));
			e.printStackTrace();
		} catch (MessagingException e) {
			setValid(false);
			e.printStackTrace();
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.notificationError"));
			e.printStackTrace();
		}
		LOGGER.info("::: notidficatio sent   ");
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public JSFBoundleProvider getProvider() {
		return provider;
	}

	public void setProvider(JSFBoundleProvider provider) {
		this.provider = provider;
	}

}
