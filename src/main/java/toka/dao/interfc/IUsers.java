/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package toka.dao.interfc;

import toka.domain.Users;
import java.util.List;

/**
 *
 * @author Emmanuel
 */
public interface IUsers {
	public Users saveUsers(Users users);

	public List<Users> getListUsers();

	public Users gettUserById(int userId, String primaryKeyclomunName);

	public Users UpdateUsers(Users users);

	public String myNane();

	public Users getUsersWithQuery(final String[] propertyName, final Object[] value, final String hqlStatement);
}
