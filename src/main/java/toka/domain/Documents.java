package toka.domain;
/**
 * author Emma
 * */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Documents")
@NamedQuery(name = "Documents.findAll", query = "SELECT r FROM Documents r order by v desc")
public class Documents extends CommonDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "DocId")
	private int DocId;

	@Column(name = "documentLoc")
	private String documentLoc;

	@Column(name = "originalFileName")
	private String originalFileName;

	@Column(name = "sysFilename")
	private String sysFilename;

	@Column(name = "validDocCode")
	private String validDocCode;

	@Column(name = "fileSize")
	private long fileSize;

	public String getDocumentLoc() {
		return documentLoc;
	}

	public void setDocumentLoc(String documentLoc) {
		this.documentLoc = documentLoc;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getSysFilename() {
		return sysFilename;
	}

	public void setSysFilename(String sysFilename) {
		this.sysFilename = sysFilename;
	}

	public String getValidDocCode() {
		return validDocCode;
	}

	public void setValidDocCode(String validDocCode) {
		this.validDocCode = validDocCode;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public int getDocId() {
		return DocId;
	}

	public void setDocId(int docId) {
		DocId = docId;
	}

}
