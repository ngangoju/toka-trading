package toka.dao.interfc;

import java.util.List;

import toka.domain.UserCategory;

public interface IUserCategory {
	public UserCategory saveUsercategory(UserCategory usercategory);

	public List<UserCategory> getListUsercategory();

	public UserCategory UpdateUsercategory(UserCategory usercategory);

}
