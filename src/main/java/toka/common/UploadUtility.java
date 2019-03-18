package toka.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;

import toka.dao.impl.DocumentsImpl;
import toka.dao.impl.UserImpl;
import toka.domain.Documents;
import toka.domain.Users;

public class UploadUtility implements Serializable, DbConstant {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
	private String CLASSNAME = "FormSampleController :: ";
	private static final long serialVersionUID = 1L;
	DocumentsImpl documentsImpl = new DocumentsImpl();
	private boolean isValid;
	private Users usersSession;
	UserImpl usersImpl = new UserImpl();
	JSFBoundleProvider provider = new JSFBoundleProvider();
	/* end class injection */
	Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
	 Path destination =null;

	public Documents fileUploadUtil(FileUploadEvent event, String validationCode) throws Exception {
		LOGGER.info("FILE 22::::" + event.getFile().getFileName());
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
	            .getExternalContext().getContext();
		
		String OS = null;
		if (OS == null) {
			OS = System.getProperty("os.name");
		}

		
	String realPath = ctx.getRealPath("/");
	LOGGER.info("Filse Reals Path::::" + realPath);
		Documents documents = new Documents();
		String systemFileName = UUID.randomUUID().toString() + "."
				+ FilenameUtils.getName(event.getFile().getFileName());
		if (OS.startsWith("Windows")) {
		 destination = Paths.get(realPath+FILELOCATION + systemFileName);
		}else {
			destination= Paths.get(realPath+FILELOCATIONUNIX + systemFileName);
		}
		LOGGER.info("Path::" + destination);
		InputStream bytes = null;
		try {
			bytes = event.getFile().getInputstream();
			Files.copy(bytes, destination);

			documents.setCrtdDtTime(timestamp);
			documents.setFileSize(event.getFile().getSize());
			documents.setOriginalFileName(event.getFile().getFileName());
			documents.setSysFilename(systemFileName);
			documents.setValidDocCode(validationCode);
			if (OS.startsWith("Windows")) {
			documents.setDocumentLoc(realPath+FILELOCATION);
			}else {
				documents.setDocumentLoc(realPath+FILELOCATIONUNIX);	
			}
			documents = documentsImpl.saveIntable(documents);
		} catch (IOException e) {

			e.printStackTrace();
		} //

		return documents;
	}
	public Documents fileUploadUtilUsers(FileUploadEvent event, String validationCode) throws Exception {
		LOGGER.info("FILE ::::" + event.getFile().getFileName());
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
	            .getExternalContext().getContext();
		String OS=null;
	String realPath = ctx.getRealPath("/");
		LOGGER.info("FILE tt::::" + realPath);
	
		Documents documents = new Documents();
		String systemFileName = UUID.randomUUID().toString() + "."
				+ FilenameUtils.getName(event.getFile().getFileName());
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		if (OS.startsWith("Windows")) {
			 Path destination = Paths.get(realPath+FILELOCATION + systemFileName);
			}else {
				 Path destination = Paths.get(realPath+FILELOCATIONUNIX + systemFileName);	
			}
		
	 
		LOGGER.info("Path::" + destination);
		InputStream bytes = null;
		HttpSession session = SessionUtils.getSession();
		usersSession = (Users) session.getAttribute("userSession");
		
		try {
			bytes = event.getFile().getInputstream();
			Files.copy(bytes, destination);

			documents.setCrtdDtTime(timestamp);
			documents.setFileSize(event.getFile().getSize());
			documents.setOriginalFileName(event.getFile().getFileName());
			documents.setSysFilename(systemFileName);
			documents.setValidDocCode(validationCode);
			if (OS.startsWith("Windows")) {
				documents.setDocumentLoc(realPath+FILELOCATION);
				}else {
					documents.setDocumentLoc(realPath+FILELOCATIONUNIX);
				}
		
			documents = documentsImpl.saveIntable(documents);
			///Updating user image in db start here
			Users us = new Users();
			us.setUserId(usersSession.getUserId());
			us.setViewId(usersSession.getViewId());
			us.setViewName(usersSession.getViewName());
			us.setAddress(usersSession.getAddress());
			us.setFname(usersSession.getFname());
			us.setLname(usersSession.getLname());
			us.setUserCategory(usersSession.getUserCategory());
			us.setCreatedBy(usersSession.getCreatedBy());
			us.setGender(usersSession.getGender());
			us.setDateOfBirth(usersSession.getDateOfBirth());
			us.setUpdatedBy(usersSession.getUpdatedBy());
			us.setCreatedDate(usersSession.getCreatedDate());
			us.setCrtdDtTime(usersSession.getCrtdDtTime());
			us.setUpDtTime(usersSession.getUpDtTime());
			us.setLoginStatus(usersSession.getLoginStatus());
			us.setStatus(usersSession.getStatus());
			us.setDistrict(usersSession.getDistrict());
			us.setImage(systemFileName);
			usersImpl.UpdateUsers(us);
		} catch (IOException e) {

			e.printStackTrace();
		} //

		return documents;
	}
	
	

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

	public String downloadFileUtil() throws Exception {
		String docId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("docId");
		LOGGER.info("download start1:::" + docId);
		long id = Long.parseLong(docId);
		Documents doc = null;
		File file = null;
		try {

			doc = (Documents) documentsImpl.getModelById(Integer.parseInt(docId), "DocId");
			// LOGGER.info("doc location >>"+doc.getDocumentLoc());
			if (doc != null) {
				// loadProperties();
				LOGGER.info("download start:::");
				String filename = doc == null ? null : doc.getOriginalFileName();
				String orgfilename = doc == null ? null : doc.getSysFilename();

				file = new File(doc.getDocumentLoc() + "/" + orgfilename);
				// String l="C:\\Vfp_Document\\";
				// file = new File(l+ "/" +
				// "d664ee13-bd63-4625-a464-60fe90b31305.logo_welcome.png");

				ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
						.getContext();
				HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance()
						.getExternalContext().getResponse();
				String contentType = servletContext.getMimeType(file.getName());

				if (contentType == null) {
					contentType = "application/octet-stream";
				}
				writeOutContent(response, file, filename);
				FacesContext.getCurrentInstance().responseComplete();
				LOGGER.info("download done::: for " + filename + ":: file");
			} else {
				HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance()
						.getExternalContext().getResponse();
				response.setStatus(401);
				FacesContext.getCurrentInstance().responseComplete();
			}
		} catch (Exception ex) {
			LOGGER.info(ex.getMessage() + ex);
			LOGGER.info("download exception::" + ex);
			throw ex;
		}
		return null;
	}

	private void writeOutContent(final HttpServletResponse res, final File content, final String theFilename) {
		if (content == null) {
			return;
		}
		ServletOutputStream os = null;
		FileInputStream fis = null;
		try {
			res.setHeader("Pragma", "no-cache");
			res.setDateHeader("Expires", 0);
			res.setHeader("Content-disposition", "attachment; filename=" + theFilename);
			fis = new FileInputStream(content);
			os = res.getOutputStream();
			int bt = fis.read();
			while (bt != -1) {
				os.write(bt);
				bt = fis.read();
			}
			os.flush();
		} catch (final IOException ex) {
			LOGGER.info(ex.getMessage() + ex);
		} finally {

			try {
				if (null != fis) {
					fis.close();
				}
				if (null != os) {
					os.close();
				}
			} catch (IOException e) {
				LOGGER.info(e.getMessage() + e);
			}

		}
	}

	public DocumentsImpl getDocumentsImpl() {
		return documentsImpl;
	}

	public void setDocumentsImpl(DocumentsImpl documentsImpl) {
		this.documentsImpl = documentsImpl;
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
	public Users getUsersSession() {
		return usersSession;
	}
	public void setUsersSession(Users usersSession) {
		this.usersSession = usersSession;
	}
	public UserImpl getUsersImpl() {
		return usersImpl;
	}
	public void setUsersImpl(UserImpl usersImpl) {
		this.usersImpl = usersImpl;
	}
	
}
