/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toka.domain;

import java.util.Date;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "LoginHistoric")
@NamedQuery(name = "LoginHistoric.findAll", query = "SELECT r FROM LoginHistoric r order by historicId desc")
public class LoginHistoric extends CommonDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "historicId")
	private int historicId;

	@Column(name = "loginTimeIn", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginTimeIn;

	@Column(name = "logOutTime", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logOutTime;

	@Column(name = "IpAddress")
	private String IpAddress;

	@ManyToOne
	@JoinColumn(name = "users")
	private Users users;

	public int getHistoricId() {
		return historicId;
	}

	public void setHistoricId(int historicId) {
		this.historicId = historicId;
	}

	public Date getLoginTimeIn() {
		return loginTimeIn;
	}

	public void setLoginTimeIn(Date loginTimeIn) {
		this.loginTimeIn = loginTimeIn;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Date getLogOutTime() {
		return logOutTime;
	}

	public void setLogOutTime(Date logOutTime) {
		this.logOutTime = logOutTime;
	}

	public String getIpAddress() {
		return IpAddress;
	}

	public void setIpAddress(String IpAddress) {
		this.IpAddress = IpAddress;
	}

}
