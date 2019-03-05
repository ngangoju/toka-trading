/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package toka.dao.impl;

import toka.dao.generic.AbstractDao;
import toka.dao.interfc.IUserCategory;
import toka.domain.UserCategory;
import java.util.List;

/**
 *
 * @author Ngango
 */
public class UserCategoryImpl extends AbstractDao<Long, UserCategory> implements IUserCategory {

	public UserCategory saveUsercategory(UserCategory usercategory) {

		return saveIntable(usercategory);
	}

	public List<UserCategory> getListUsercategory() {

		return (List<UserCategory>) (Object) getModelList();
	}

	public UserCategory getUserCategoryById(int usercatId, String primaryKeyColumn) {
		return (UserCategory) getModelById(usercatId, primaryKeyColumn);

	}

	public UserCategory UpdateUsercategory(UserCategory usercategory) {

		return updateIntable(usercategory);
	}

}
