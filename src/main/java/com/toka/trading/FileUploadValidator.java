package com.toka.trading;

import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import toka.common.JSFBoundleProvider;
import toka.common.JSFMessagers;

@FacesValidator(value = "fileUploadValidator")
public class FileUploadValidator implements Validator {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
	JSFBoundleProvider provider = new JSFBoundleProvider();
	private boolean isValid;

	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Part file = (Part) value;

		FacesMessage message = null;

		try {

			if (file == null || file.getSize() <= 0 || file.getContentType().isEmpty()) {
				message = new FacesMessage("Select a valid file");
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.validfile.error"));
				LOGGER.info("Select a valid file::::::::::::::::::");
			} else if ((!file.getContentType().endsWith("jpe?g")) || (!file.getContentType().endsWith("gif"))
					|| (!file.getContentType().endsWith("png"))) {
				message = new FacesMessage("Select the jpe?g|gif|png image only");
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.filetype.error"));
				LOGGER.info("Select the jpe?g|gif|png image only::::::::::::::::::");
			} else if (file.getSize() > 500)
				message = new FacesMessage("File size too big. File size allowed  is less than or equal to 500 Kb.");
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.error.bigsize"));
			LOGGER.info("File size too big. File size allowed  is less than or equal to 500 Kb.\"::::::::::::::::::");
		} catch (Exception ex) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.error.uploaded"));
			LOGGER.info(ex.getMessage());
			throw new ValidatorException(new FacesMessage(ex.getMessage()));
		}

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