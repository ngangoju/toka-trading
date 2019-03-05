package toka.services.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import toka.dao.impl.UserImpl;
import toka.services.interfaces.ILoginControllerService;

@Stateless
public class LoginControllerServiceImpl implements ILoginControllerService {

	@Inject
	public transient UserImpl usersImpl;

	public String getMyNgaboName() {

		return usersImpl.myNane();
	}

}
