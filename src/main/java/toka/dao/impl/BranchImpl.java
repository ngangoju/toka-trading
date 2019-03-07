package toka.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import toka.dao.generic.AbstractDao;
import toka.dao.interfc.IBranch;
import toka.domain.Branch;

public class BranchImpl extends AbstractDao<Long, Branch> implements IBranch {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

	public Branch saveBranch(Branch Branch) {
		return saveIntable(Branch);
	}

	public List<Branch> getListBranch() {
		return (List<Branch>) (Object) getModelList();
	}

	public Branch getBranchById(int branchId, String primaryKeyclomunName) {
		return (Branch) getModelById(branchId, primaryKeyclomunName);
	}

	public Branch UpdateBranch(Branch branch) {
		return updateIntable(branch);
	}

	public Branch getBranchWithQuery(String[] propertyName, Object[] value, String hqlStatement) {
		try {
			return (Branch) getModelWithMyHQL(propertyName, value, hqlStatement);
		} catch (Exception ex) {
			LOGGER.info("getBranchWithQuery  Query error ::::" + ex.getMessage());
		}
		return null;
	}

}
