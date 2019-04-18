package toka.common;

import java.io.Serializable;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * This class contains JSF utility methods.
 * 
 * @author Ngango
 */
public class JSFMessagers implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Removes all current messages held by the JSF framework.
	 */
	public static void resetMessages() {
		FacesContext context = FacesContext.getCurrentInstance();
		Iterator iter = context.getMessages();
		while (iter.hasNext()) {
			iter.next();
			iter.remove();
		}
	}

	/**
	 * Adds a SEVERE message.
	 * 
	 * @param message The message.
	 */
	public static void addErrorMessage(String message) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, facesMsg);
	}

	/**
	 * Adds a SEVERE message.
	 * 
	 * @param context
	 * @param clientId
	 * @param message
	 */
	public static void addErrorMessage(FacesContext context, String clientId, String message) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
		context.addMessage(clientId, facesMsg);
	}

	/**
	 * Adds a SEVERE message.
	 * 
	 * @param context
	 * @param message
	 */
	public static void addErrorMessage(FacesContext context, String message) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
		context.addMessage(null, facesMsg);
	}

	/**
	 * Adds an INFO message.
	 * 
	 * @param message The message.
	 */
	public static void addInfoMessage(String message) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, message, message);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, facesMsg);
	}

	/**
	 * Adds an INFO message.
	 * 
	 * @param context
	 * @param message
	 */
	public static void addInfoMessage(FacesContext context, String message) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, message, message);
		context.addMessage(null, facesMsg);
	}

	/**
	 * 
	 * @param context
	 * @param message
	 * @param detailedMessage
	 */
	public static void addInfoMessage(FacesContext context, String message, String detailedMessage) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, message, detailedMessage);
		context.addMessage(null, facesMsg);
	}

	/**
	 * Adds a WARNING message.
	 * 
	 * @param message The message.
	 */
	public static void addWarningMessage(String message) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, message, message);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, facesMsg);
	}

}
