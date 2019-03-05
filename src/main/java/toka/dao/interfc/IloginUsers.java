/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package toka.dao.interfc;

import toka.domain.Users;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author RTAP4
 */
public interface IloginUsers {
	public boolean checkUserNameAndPasswod(String userName, String Password);

	public Users userDetail(String userName);

	public String criptPassword(String password) throws NoSuchAlgorithmException;

	public String getIpAddress() throws Exception;
}
