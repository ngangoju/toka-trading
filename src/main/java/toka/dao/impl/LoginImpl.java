/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toka.dao.impl;

import toka.dao.generic.AbstractDao;

import toka.dao.interfc.IloginUsers;
import toka.domain.Users;

import java.io.Serializable;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import javax.inject.Named;

/**
 *
 * @author Ngabo
 */

@Named
public class LoginImpl extends AbstractDao<Long, Users> implements IloginUsers, Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

	public boolean checkUserNameAndPasswod(String userName, String Password) {
		Object log = null;
		log = getLongIn(userName, Password);
		if (log == null) {
			return false;
		} else {
			return true;
		}
	}

	public Users userDetail(String userName) {
		return (Users) super.getLongInUserDeails(userName);
	}

	public String criptPassword(String password) throws NoSuchAlgorithmException {
		LOGGER.info("start cript  :::" + password);
		String pas = password;
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(pas.getBytes());
		byte byteData[] = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		LOGGER.info("done cript :::" + password);
		return (sb.toString());

	}

	public String getIpAddress() throws Exception {
		String ip = null;
		try {
			InetAddress Ip = InetAddress.getLocalHost();
			ip = Ip.toString();
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			;
		}
		return ip;
	}

}
