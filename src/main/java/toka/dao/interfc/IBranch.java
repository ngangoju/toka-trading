package toka.dao.interfc;

import java.util.List;

import toka.domain.Branch;

public interface IBranch {

	public Branch saveBranch(Branch branch);

	public List<Branch> getListBranch();

	public Branch UpdateBranch(Branch branch);
	
	public Branch getBranchById(int branchId, String primaryKeyclomunName);

}
