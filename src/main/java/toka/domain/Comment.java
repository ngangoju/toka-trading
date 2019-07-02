package toka.domain;
/**
 * author Ngango
 **/

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Comment")
@NamedQuery(name = "Comment.findAll", query = "SELECT r FROM Comment r order by v desc")
public class Comment extends CommonDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "commentId")
	private int commentId;

	@Column(name = "description")
	private String description;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
