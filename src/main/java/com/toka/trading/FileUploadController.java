package com.toka.trading;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;

import toka.common.DbConstant;
import toka.common.JSFBoundleProvider;
import toka.common.JSFMessagers;
import toka.common.SessionUtils;
import toka.dao.impl.UserImpl;
import toka.domain.Users;

@ManagedBean
@RequestScoped

public class FileUploadController implements Serializable, DbConstant {

	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
	JSFBoundleProvider provider = new JSFBoundleProvider();
	private boolean isValid;
	private static final long serialVersionUID = 1L;
	private String name;
	private Part file;
	private Users users;
	UserImpl usersImpl = new UserImpl();

	@PostConstruct
	public void init() {
		if (null == users) {
			users = new Users();
		}
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// return the context path of the project like /VfpProject_Dev
	public String getContextPath() {

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		return request.getContextPath();
	}

	// processing uploaded file
	public String processFileUpload() throws IOException {
		HttpSession session = SessionUtils.getSession();
		int userid = (Integer) session.getAttribute("userProfile");
		LOGGER.info("USER PROFILE ID::::::::::::::::::::::::::" + userid);
		boolean valid = validateImage();
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
	            .getExternalContext().getContext();
	String realPath = ctx.getRealPath("/");
		if (valid) {
			Part uploadedFile = getFile();
			final Path destination = Paths.get(realPath+FILELOCATION + "\\" + UUID.randomUUID().toString() + "."
					+ FilenameUtils.getName(getSubmittedFileName(uploadedFile)));
			LOGGER.info("Uploaded File name::------------>>>>>>"
					+ FilenameUtils.getName(getSubmittedFileName(uploadedFile)));
			InputStream bytes = null;

			if (null != uploadedFile) {

				bytes = uploadedFile.getInputStream(); //

				// Copies bytes to destination.
				Files.copy(bytes, destination);
				// :::UPDATING USER PROFILE IN DATABASE::::
				try {
					if (null != users) {
						users = usersImpl.gettUserById(userid, "userId");
						if (null != users) {
							users.setImage(FilenameUtils.getName(getSubmittedFileName(uploadedFile)));
							usersImpl.UpdateUsers(users);
							setValid(true);
							JSFMessagers.addInfoMessage(getProvider().getValue("com.server.side.profile.edit"));
						} else {
							setValid(false);
							JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.userprofile.error"));
						}

					}
				} catch (Exception e) {
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.profileErro.internal"));
				}
			}

			return FilenameUtils.getName(getSubmittedFileName(uploadedFile));
		} else {
			return null;
		}
	}
	// code to get the submitted file name from the file part header.

	public static String getSubmittedFileName(Part filePart) {
		String header = filePart.getHeader("content-disposition");
		if (header == null)
			return null;
		for (String headerPart : header.split(";")) {
			if (headerPart.trim().startsWith("filename")) {
				return headerPart.substring(headerPart.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

	public boolean validateImage() {

		boolean valid = true;
		try {
			if (file == null) {
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.validfile.error"));
				LOGGER.info("Select a valid file::::::::::::::::::");
				valid = false;
			} else if (file.getSize() <= 0) {
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.validfile.error"));
				LOGGER.info("Select a valid file::::::::::::::::::");
				valid = false;
			} else if (file.getContentType().isEmpty()) {
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.validfile.error"));
				LOGGER.info("Select a valid file::::::::::::::::::");
				valid = false;
			} else if ((!file.getContentType().startsWith("image"))) {
				setValid(false);
				LOGGER.info("File type is:::::" + file.getContentType());
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.filetype.error"));
				LOGGER.info("Select the jpe?g|gif|png image only::::::::::::::::::");
				valid = false;
			} else if ((file.getSize() > 500000)) {
				LOGGER.info("The File Size is:::::::::::" + file.getSize());
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.error.bigsize"));
				LOGGER.info(
						"File size too big. File size allowed  is less than or equal to 500 Kb.\"::::::::::::::::::");
				valid = false;
			}

			return (valid);
		} catch (Exception ex) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.error.uploaded"));
			LOGGER.info(ex.getMessage());
			throw new ValidatorException(new FacesMessage(ex.getMessage()));
		}
	}

	public JSFBoundleProvider getProvider() {
		return provider;
	}

	public void setProvider(JSFBoundleProvider provider) {
		this.provider = provider;
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

	public UserImpl getUsersImpl() {
		return usersImpl;
	}

	public void setUsersImpl(UserImpl usersImpl) {
		this.usersImpl = usersImpl;
	}

}