package com.toka.trading;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;

import com.google.gson.Gson;

import toka.common.DbConstant;
import toka.common.Formating;
import toka.common.JSFBoundleProvider;
import toka.common.JSFMessagers;
import toka.common.SessionUtils;
import toka.dao.impl.BranchImpl;
import toka.dao.impl.ContactImpl;
import toka.dao.impl.UserCategoryImpl;
import toka.dao.impl.UserImpl;
import toka.domain.Branch;
import toka.domain.Contact;
import toka.domain.UserCategory;
import toka.domain.Users;
import toka.trading.dto.ContactDto;
import toka.trading.dto.OrderProductDto;
import toka.trading.dto.ProductCategoryDtos;
import toka.trading.dto.SoldProductDto;
import toka.trading.dto.UserDto;
import toka.dao.impl.CountryImpl;
import toka.dao.impl.DistrictImpl;
import toka.dao.impl.OrderProductImpl;
import toka.dao.impl.ProductAssignmentImpl;
import toka.dao.impl.ProductImpl;
import toka.dao.impl.ProvinceImpl;
import toka.dao.impl.SoldProductImpl;
import toka.domain.Country;
import toka.domain.District;
import toka.domain.Product;
import toka.domain.ProductAssignment;
import toka.domain.ProductCategory;
import toka.domain.Province;
import toka.domain.SoldProduct;

@ManagedBean
@ViewScoped
public class BranchController implements Serializable, DbConstant {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
	private String CLASSNAME = "BranchController :: ";
	private static final long serialVersionUID = 1L;
	/* to manage validation messages */
	private boolean isValid, chngDiv, skip, nextpage, frstDiv, cmpltDiv, bnchDiv, div1, div4, div2, div3, div3_1 = true,
			hasContact, profileEditable;
	private int cntryId, vid, pid, cid, did, sid,totalorderqty,remainedqty,assignqty;
	/* end manage validation messages */
	private Branch branch;
	private Users usersSession;
	private List<Branch> branchDetails = new ArrayList<Branch>();
	private List<Contact> contactDetails = new ArrayList<Contact>();
	private List<ContactDto> contactDtoDetails = new ArrayList<ContactDto>();
	private List<ProductCategoryDtos> categoryDtoDetails = new ArrayList<ProductCategoryDtos>();
	/* class injection */
	JSFBoundleProvider provider = new JSFBoundleProvider();
	UserImpl usersImpl = new UserImpl();
	ContactImpl contactImpl = new ContactImpl();
	BranchImpl branchImpl = new BranchImpl();
	UserCategoryImpl uCategoryImpl = new UserCategoryImpl();
	private boolean rendered, renderProductForm, renderproduct, renderassform,renderheader;
	UserCategoryImpl userCatImpl = new UserCategoryImpl();
	Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
	private String repEmail;
	private String option;
	private String range;
	private Contact cont = new Contact();
	private UserCategory userCategory;
	private Country country;
	private Province province;
	private District district;
	private List<UserCategory> uCategoryDetails = new ArrayList<UserCategory>();
	private List<Country> countries = new ArrayList<Country>();
	private List<Province> provinces = new ArrayList<Province>();
	private List<District> districts = new ArrayList<District>();
	CountryImpl countryImpl = new CountryImpl();
	ProvinceImpl provImpl = new ProvinceImpl();
	DistrictImpl districtImpl = new DistrictImpl();
	private List<Product> productfulldetails = new ArrayList<Product>();
	private Product product;
	ProductImpl productImpl = new ProductImpl();
	private ProductAssignment prodAssign = new ProductAssignment();
	ProductAssignmentImpl prodAssignImpl = new ProductAssignmentImpl();
	private SoldProduct soldproduct= new SoldProduct();
	private List<SoldProductDto>branchSoldProduct= new ArrayList<SoldProductDto>();
	SoldProductImpl soldImpl= new SoldProductImpl();
	private List<OrderProductDto> orderDetails,dailyOrder,branchStatistics = new ArrayList<OrderProductDto>();
	private OrderProductDto orderDto = new OrderProductDto();
	OrderProductImpl orderProdImpl = new OrderProductImpl();
	private List<ProductAssignment> productAssignedList = new ArrayList<ProductAssignment>();
	private List<ProductAssignment> prodAssigDetails = new ArrayList<ProductAssignment>();
	private List<ProductAssignment> productassdetails = new ArrayList<ProductAssignment>();
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		HttpSession session = SessionUtils.getSession();
		usersSession = (Users) session.getAttribute("userSession");

		if (branch == null) {
			branch = new Branch();
		}
		if (cont == null) {
			cont = new Contact();
		}
		if (null == district) {
			district = new District();
		}
		if (null == product) {
			product = new Product();
		}
		if (null == prodAssign) {
			prodAssign = new ProductAssignment();
		}
		try {
			branchDetails = branchImpl.getGenericListWithHQLParameter(new String[] { "genericStatus" },
					new Object[] { ACTIVE }, "Branch", "branchId asc");
			if (branchDetails.size() > 0) {
				this.rendered = true;
				renderheader=true;
			} else {
				renderProductForm = true;
			}
			productfulldetails = productFullDetails();
		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}

	}

	
	@SuppressWarnings("unchecked")
	public List<Product> productFullDetails() throws Exception {
		List<Product> details = new ArrayList<Product>();
		details = productImpl.getGenericListWithHQLParameter(new String[] { "genericStatus" }, new Object[] { ACTIVE, },
				"Product", " upDtTime desc");

		for (Product p : details) {
			Product pro = new Product();
			if (p.getSellingUnitPrice() > 0) {
				pro.setProductId(p.getProductId());
				pro.setProductName(p.getProductName());
				pro.setQuantity(p.getQuantity());
				pro.setManufactDate(p.getManufactDate());
				pro.setExpireDate(p.getExpireDate());
				pro.setPurchaseUnitPrice(p.getPurchaseUnitPrice());
				pro.setSellingUnitPrice(p.getSellingUnitPrice());
				pro.setGenericStatus(p.getGenericStatus());
				pro.setProductImage(p.getProductImage());
				pro.setProductCategory(p.getProductCategory());
				pro.setTotalPrice(p.getQuantity() * p.getPurchaseUnitPrice());
				pro.setTotalSales(p.getSellingUnitPrice() * p.getQuantity());
				productfulldetails.add(pro);
			}
		}
		return (productfulldetails);
	}

	public void showBranchForm() {
		this.rendered = false;
		this.renderProductForm = true;
	}

	public void backBranchList() {
		this.rendered = true;
		this.renderproduct = false;
	}
	
	public void backProductBranchList() {
		this.renderproduct=true;
		this.renderassform=false;
	}
	public void backProductList() {
		this.rendered = false;
		this.renderproduct = true;
		this.renderassform = false;
	}

	public String saveAction(ProductCategoryDtos cat) {
		LOGGER.info("update  saveAction method");
		// get all existing value but set "editable" to false
		try {
			if (null != cat) {
				LOGGER.info("UserCat:++++++++++++++++++++++++++" + cat.getCatid());
				UserCategory usercat = new UserCategory();
				usercat = new UserCategory();
				usercat = userCatImpl.getUserCategoryById(cat.getCatid(), "userCatid");

				LOGGER.info("here update sart for " + usercat + " useriD " + usercat.getUserCatid());

				cat.setEditable(false);
				usercat.setUpdatedBy(usersSession.getViewId());
				usercat.setUpDtTime(timestamp);
				// usercat.setUsercategoryName(cat.getUsercategoryName());
				userCatImpl.UpdateUsercategory(usercat);
				JSFMessagers.resetMessages();
				setValid(true);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.categoryupdate"));
			} else {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.errorupdate"));
			}
		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.updateError"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}

		// return to current page
		return null;

	}

	public String saveBranch() {
		try {

			try {

				if (null == district.getDistrictName_en() && null == branch.getBranchName()) {
					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("com.frontend.side.error"));
				}
			} catch (Exception e) {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
				LOGGER.info(CLASSNAME + "" + e.getMessage());
				e.printStackTrace();
				return null;
			}

			districtImpl.saveDistrict(district);
			branch.setGenericStatus(ACTIVE);
			branch.setCreatedBy(usersSession.getFname() + "  " + usersSession.getLname());
			branch.setCrtdDtTime(timestamp);
			branch.setLocation(district);
			branchImpl.saveBranch(branch);
			JSFMessagers.resetMessages();
			setValid(true);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.branch"));

			clearContactFuileds();
			return null;

		} catch (HibernateException e) {
			LOGGER.info(CLASSNAME + ":::Product Details is fail with HibernateException  error");
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.product"));
			LOGGER.info(CLASSNAME + "" + e.getMessage());
			e.printStackTrace();
			return "";
		}
	}

	public String assignProduct() {
		try {

			try {
				branch = showAssignBranch();
				product = showAssignProduct();
				if (null == branch && null == product) {
					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("com.frontendbranchproductinfo.side.error"));
				}
			} catch (Exception e) {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
				LOGGER.info(CLASSNAME + "" + e.getMessage());
				e.printStackTrace();
				return null;
			}

			if (Integer.parseInt(range) <= product.getQuantity()) {
				prodAssign.setGenericStatus(ACTIVE);
				prodAssign.setCreatedBy(usersSession.getFname() + " " + usersSession.getLname());
				prodAssign.setCrtdDtTime(timestamp);
				prodAssign.setProduct(product);
				prodAssign.setBranch(branch);
				prodAssign.setAssignDate(timestamp);
				prodAssign.setQuantity(Integer.parseInt(range));
				Product p = new Product();
				p = productImpl.getProductById(product.getProductId(), "productId");
				if (null != p) {
					p.setQuantity(p.getQuantity() - Integer.parseInt(range));
					prodAssignImpl.saveProductAssignment(prodAssign);
					productImpl.UpdateProduct(p);
					JSFMessagers.resetMessages();
					setValid(true);
					JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.productassignment"));
				p= new Product();	
				p=productImpl.getProductById(product.getProductId(), "productId");
				if(p.getQuantity()==0) {
					p.setGenericStatus(DESACTIVE);
					productImpl.UpdateProduct(p);
				}
				}

			} else {

			}

			clearContactFuileds();
			return null;

		} catch (HibernateException e) {
			LOGGER.info(CLASSNAME + ":::Product Details is fail with HibernateException  error");
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.product"));
			LOGGER.info(CLASSNAME + "" + e.getMessage());
			e.printStackTrace();
			return "";
		}
	}

	public void clearContactFuileds() {
		district = new District();
		branch = new Branch();
	}

	
	public String addBranch() {

		return "/menu/Branch.xhtml?faces-redirect=true";
	}

	public String cancel(ProductCategoryDtos cat) {
		cat.setEditable(false);
		return null;
	}

	public String cancelChange(ProductCategoryDtos cat) {
		cat.setNotify(false);
		this.repEmail = null;
		return null;
	}

	public String otherUserCategory() {
		return "/menu/UserCategory.xhtml?faces-redirect=true";

	}

	public String editAction(ProductCategoryDtos cat) {

		cat.setEditable(true);
		return null;
	}

	@SuppressWarnings("unchecked")
	public String renderAction(ProductCategoryDtos cat) throws Exception {
		cat.setNotify(true);
		/*
		 * List<Contact>contactList= new ArrayList<Contact>();
		 * contactList=contactImpl.getGenericListWithHQLParameter(new String[] {
		 * "genericStatus" }, new Object[] { ACTIVE}, "Contact", "contactId asc");
		 */
		contactDetails = new ArrayList<Contact>();
		for (Object[] data : usersImpl.reportList(
				"select us.fname,us.lname, us.viewId,us.address, us.userId,co.email,co.phone from Contact co right  join  co.user us join us.userCategory cat where co.user is not null and cat.usercategoryName='"
						+ INSTITUTE_REP + "'")) {
			LOGGER.info("users>>" + data[0] + ":: " + data[1] + "");
			Contact cont = new Contact();
			cont.setEmail(data[5] + "");
			cont.setPhone(data[6] + "");
			contactDetails.add(cont);
		}
		return null;
	}

	/* method for render province panel starts */
	public void renderProvMethod() {
		try {
			country = countryImpl.getCountryById(cntryId, "taskId");
			if (country != null) {
				if (country.getCountryName_en().equals("RWANDA")) {
					skip = true;
				} else {
					skip = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String assignBranchProduct(Branch bList) {
		HttpSession sessionuser = SessionUtils.getSession();
		if (null != bList) {
			sessionuser.setAttribute("branchassign", bList);
			this.rendered = false;
			this.renderproduct = true;
		}
		return null;
	}

	
	
	
public List<SoldProductDto> branchSoldProduct(Branch b) {
		
		if (null != b) {
			this.rendered = false;
			this.renderproduct = true;
			branchSoldProduct= new ArrayList<SoldProductDto>();
			for(Object[]data:soldImpl.reportList("select s.soldId,s.soldDate,s.quantity,s.customer,s.product,a.quantity,p.sellingUnitPrice,o.orderDate,p.purchaseUnitPrice,o.quantity from SoldProduct s,ProductAssignment a,Product p ,OrderProduct o where s.product=a.product and p.productId=a.product and p.productId=s.product and o.product=s.product and a.branch="+b.getBranchId()+"")) {
				SoldProductDto sold= new SoldProductDto();
				sold.setSoldProductId(Integer.parseInt(data[0]+""));
				sold.setSoldDate((Date)data[1]);
				sold.setQuantity(Integer.parseInt(data[5]+""));
				sold.setCustomer((Users)data[3]);
				sold.setProduct((Product)data[4]);
				sold.setSoldquantity(Integer.parseInt(data[2]+""));
				sold.setSellingUnitPrice(data[6]+"");
				sold.setOrderDate((Date)data[7]);
				sold.setTotalSales(Double.parseDouble(data[6]+"") * Integer.parseInt(data[2]+""));
				sold.setTotalPurchase(Integer.parseInt(data[2]+"") * Double.parseDouble(data[8]+""));
				sold.setPurchaseUnitPrice(Double.parseDouble(data[8]+""));
				sold.setOrderquantity(Integer.parseInt(data[9]+""));
				branchSoldProduct.add(sold);
			}
		}
		return branchSoldProduct;
	}
	


@SuppressWarnings("unchecked")
public void soldProductList(Branch b) throws Exception {
	if(null!=b) {	
		productassdetails=prodAssignImpl.getGenericListWithHQLParameter(new String[] { "genericStatus","branch" },
				new Object[] { ACTIVE,b}, "ProductAssignment", " assignDate desc");
		productAssignedList= new ArrayList<ProductAssignment>();
		productAssignedList=productAssignedList(productassdetails);
		/*branchSoldProduct= new ArrayList<SoldProductDto>();
		branchSoldProduct=branchSoldProduct(b);*/
		HttpSession sessionuser = SessionUtils.getSession();
		sessionuser.setAttribute("branchId", b);
		this.rendered = false;
		this.renderproduct = true;
	}	
}


public List<ProductAssignment>productAssignedList(List<ProductAssignment>list){
	productAssignedList = new ArrayList<ProductAssignment>();
	for(ProductAssignment ass:list) {
		ProductAssignment assign= new ProductAssignment();
		assign.setTotalprice(ass.getQuantity()*ass.getProduct().getPurchaseUnitPrice());
		assign.setTotalsales(ass.getQuantity()*ass.getProduct().getSellingUnitPrice());
		assign.setProdAssId(ass.getProdAssId());
		assign.setQuantity(ass.getQuantity());
		assign.setAssignDate(ass.getAssignDate());
		assign.setProduct(ass.getProduct());
		assign.setBranch(ass.getBranch());
		assign.setUnitprice(ass.getProduct().getPurchaseUnitPrice());
		assign.setSalesunit(ass.getProduct().getSellingUnitPrice());
		productAssignedList.add(assign);
	
	}
	return(productAssignedList);
}
	public void checkBranchStock(ProductAssignment p) {
		if(null!=p) {
			
			LOGGER.info(":::PRODUCTASSIGNMENT ::::"+p.getProduct().getProductId());
			totalorderqty=totalOrderQuantity(p);
			assignqty=totalAssignedBranchQtyProduct(p);
			LOGGER.info("::TOTAL ORDERED QUANTITY::"+totalorderqty+":::TOTAL ASSIGNED QTY:::"+assignqty);
			if(assignqty>totalorderqty) {
				remainedqty=assignqty-totalorderqty;
				LOGGER.info("::REMAINING QTY FOR PRODUCT ID:"+p.getProduct().getProductId()+"::##::"+remainedqty);
				this.renderproduct = false;
				renderassform=true;
			}
		}	
	}
	public void backBranch() {
		this.rendered = true;
		this.renderproduct = false;
	}
	public int totalOrderQuantity(ProductAssignment b) {
		int totalOrderQty=0;
		HttpSession session = SessionUtils.getSession();
		branch = (Branch) session.getAttribute("branchId");
		if (null != b && null !=branch) {
			/*this.rendered = false;
			this.renderproduct = true;*/
			
			LOGGER.info("::Product ID:"+b.getProduct().getProductId());
			branchStatistics= new ArrayList<OrderProductDto>();
			for (Object[] data : orderProdImpl.reportList("select sum(o.quantity)as totalOrderQty,ass.branch,ass.product from OrderProduct o,ProductAssignment ass where o.productInfo=ass.prodAssId and ass.branch="+branch.getBranchId()+" and ass.product="+b.getProduct().getProductId()+"")) {
				OrderProductDto odto= new OrderProductDto();
				odto.setQuantity(Integer.parseInt(data[0]+""));
				totalOrderQty=Integer.parseInt(data[0]+"");
				LOGGER.info(":::PRODUCT QTY ORDERED::::"+totalOrderQty);
				branchStatistics.add(odto);
			}
			if(branchStatistics.size()>0) {
				return (totalOrderQty);
			}else {
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("order.branch.not.yet"));	
			}	
	}
		return totalOrderQty;
	}
	
	
	
	public int totalAssignedBranchQtyProduct(ProductAssignment b) {
		int totalAssQty=0;
		HttpSession session = SessionUtils.getSession();
		branch = (Branch) session.getAttribute("branchId");
		if (null != b && null!=branch) {
			/*this.rendered = false;
			this.renderproduct = true;*/
			LOGGER.info("::PRODUCT ID:"+b.getProduct().getProductId()+"::BRANCH INFO :"+branch.getBranchId());
			branchStatistics= new ArrayList<OrderProductDto>();
			for (Object[] data : orderProdImpl.reportList(" select sum(ass.quantity)as totalAssignedBranchQty,ass.branch,ass.product from ProductAssignment ass where ass.branch="+branch.getBranchId()+" and ass.product="+b.getProduct().getProductId()+"")) {
				OrderProductDto odto= new OrderProductDto();
				odto.setQuantity(Integer.parseInt(data[0]+""));
				totalAssQty=Integer.parseInt(data[0]+"");
				LOGGER.info(":::PRODUCT QTY ASSIGNED TO BRANCH"+branch.getBranchId()+"::::"+totalAssQty);
				branchStatistics.add(odto);
			}
			if(branchStatistics.size()>0) {
				return (totalAssQty);
			}else {
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("order.branch.not.yet"));	
			}	
	}
		return totalAssQty;
	}
	
	public void assignProductForm(Product details) {
		HttpSession sessionuser = SessionUtils.getSession();
		if (null != details) {
			sessionuser.setAttribute("productassign", details);
			this.rendered = false;
			this.renderproduct = false;
			this.renderassform = true;
			branch = showAssignBranch();
			product = showAssignProduct();
		}
	}

	public Branch showAssignBranch() {
		HttpSession session = SessionUtils.getSession();
		branch = (Branch) session.getAttribute("branchassign");
		return branch;
	}

	public Product showAssignProduct() {
		HttpSession session = SessionUtils.getSession();
		product = (Product) session.getAttribute("productassign");
		return product;
	}

	@SuppressWarnings("unchecked")
	public void changeDistrict() {
		try {
			province = provImpl.getProvinceById(pid, "provenceId");
			districts = districtImpl.getGenericListWithHQLParameter(new String[] { "province" },
					new Object[] { province }, "District", "code asc");
		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}
	}

	public String getCLASSNAME() {
		return CLASSNAME;
	}

	public void setCLASSNAME(String cLASSNAME) {
		CLASSNAME = cLASSNAME;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isChngDiv() {
		return chngDiv;
	}

	public void setChngDiv(boolean chngDiv) {
		this.chngDiv = chngDiv;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public boolean isNextpage() {
		return nextpage;
	}

	public void setNextpage(boolean nextpage) {
		this.nextpage = nextpage;
	}

	public boolean isFrstDiv() {
		return frstDiv;
	}

	public void setFrstDiv(boolean frstDiv) {
		this.frstDiv = frstDiv;
	}

	public boolean isCmpltDiv() {
		return cmpltDiv;
	}

	public void setCmpltDiv(boolean cmpltDiv) {
		this.cmpltDiv = cmpltDiv;
	}

	public boolean isBnchDiv() {
		return bnchDiv;
	}

	public void setBnchDiv(boolean bnchDiv) {
		this.bnchDiv = bnchDiv;
	}

	public boolean isDiv1() {
		return div1;
	}

	public void setDiv1(boolean div1) {
		this.div1 = div1;
	}

	public boolean isDiv4() {
		return div4;
	}

	public void setDiv4(boolean div4) {
		this.div4 = div4;
	}

	public boolean isDiv2() {
		return div2;
	}

	public void setDiv2(boolean div2) {
		this.div2 = div2;
	}

	public boolean isDiv3() {
		return div3;
	}

	public void setDiv3(boolean div3) {
		this.div3 = div3;
	}

	public boolean isDiv3_1() {
		return div3_1;
	}

	public void setDiv3_1(boolean div3_1) {
		this.div3_1 = div3_1;
	}

	public boolean isHasContact() {
		return hasContact;
	}

	public void setHasContact(boolean hasContact) {
		this.hasContact = hasContact;
	}

	public boolean isProfileEditable() {
		return profileEditable;
	}

	public void setProfileEditable(boolean profileEditable) {
		this.profileEditable = profileEditable;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Users getUsersSession() {
		return usersSession;
	}

	public void setUsersSession(Users usersSession) {
		this.usersSession = usersSession;
	}

	public JSFBoundleProvider getProvider() {
		return provider;
	}

	public void setProvider(JSFBoundleProvider provider) {
		this.provider = provider;
	}

	public UserImpl getUsersImpl() {
		return usersImpl;
	}

	public void setUsersImpl(UserImpl usersImpl) {
		this.usersImpl = usersImpl;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public List<Branch> getBranchDetails() {
		return branchDetails;
	}

	public void setBranchDetails(List<Branch> branchDetails) {
		this.branchDetails = branchDetails;
	}

	public List<ProductCategoryDtos> getCategoryDtoDetails() {
		return categoryDtoDetails;
	}

	public void setCategoryDtoDetails(List<ProductCategoryDtos> categoryDtoDetails) {
		this.categoryDtoDetails = categoryDtoDetails;
	}

	public UserCategoryImpl getUserCatImpl() {
		return userCatImpl;
	}

	public void setUserCatImpl(UserCategoryImpl userCatImpl) {
		this.userCatImpl = userCatImpl;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}

	public int getCntryId() {
		return cntryId;
	}

	public void setCntryId(int cntryId) {
		this.cntryId = cntryId;
	}

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getRepEmail() {
		return repEmail;
	}

	public void setRepEmail(String repEmail) {
		this.repEmail = repEmail;
	}

	public List<Contact> getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(List<Contact> contactDetails) {
		this.contactDetails = contactDetails;
	}

	public ContactImpl getContactImpl() {
		return contactImpl;
	}

	public void setContactImpl(ContactImpl contactImpl) {
		this.contactImpl = contactImpl;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public Contact getCont() {
		return cont;
	}

	public void setCont(Contact cont) {
		this.cont = cont;
	}

	public List<ContactDto> getContactDtoDetails() {
		return contactDtoDetails;
	}

	public void setContactDtoDetails(List<ContactDto> contactDtoDetails) {
		this.contactDtoDetails = contactDtoDetails;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public CountryImpl getCountryImpl() {
		return countryImpl;
	}

	public void setCountryImpl(CountryImpl countryImpl) {
		this.countryImpl = countryImpl;
	}

	public ProvinceImpl getProvImpl() {
		return provImpl;
	}

	public void setProvImpl(ProvinceImpl provImpl) {
		this.provImpl = provImpl;
	}

	public DistrictImpl getDistrictImpl() {
		return districtImpl;
	}

	public void setDistrictImpl(DistrictImpl districtImpl) {
		this.districtImpl = districtImpl;
	}

	public List<Province> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<Province> provinces) {
		this.provinces = provinces;
	}

	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public BranchImpl getBranchImpl() {
		return branchImpl;
	}

	public void setBranchImpl(BranchImpl branchImpl) {
		this.branchImpl = branchImpl;
	}

	public UserCategoryImpl getuCategoryImpl() {
		return uCategoryImpl;
	}

	public void setuCategoryImpl(UserCategoryImpl uCategoryImpl) {
		this.uCategoryImpl = uCategoryImpl;
	}

	public UserCategory getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(UserCategory userCategory) {
		this.userCategory = userCategory;
	}

	public List<UserCategory> getuCategoryDetails() {
		return uCategoryDetails;
	}

	public void setuCategoryDetails(List<UserCategory> uCategoryDetails) {
		this.uCategoryDetails = uCategoryDetails;
	}

	public boolean isRenderProductForm() {
		return renderProductForm;
	}

	public void setRenderProductForm(boolean renderProductForm) {
		this.renderProductForm = renderProductForm;
	}

	public List<Product> getProductfulldetails() {
		return productfulldetails;
	}

	public void setProductfulldetails(List<Product> productfulldetails) {
		this.productfulldetails = productfulldetails;
	}

	public ProductImpl getProductImpl() {
		return productImpl;
	}

	public void setProductImpl(ProductImpl productImpl) {
		this.productImpl = productImpl;
	}

	public boolean isRenderproduct() {
		return renderproduct;
	}

	public void setRenderproduct(boolean renderproduct) {
		this.renderproduct = renderproduct;
	}

	public boolean isRenderassform() {
		return renderassform;
	}

	public void setRenderassform(boolean renderassform) {
		this.renderassform = renderassform;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductAssignment getProdAssign() {
		return prodAssign;
	}

	public void setProdAssign(ProductAssignment prodAssign) {
		this.prodAssign = prodAssign;
	}

	public ProductAssignmentImpl getProdAssignImpl() {
		return prodAssignImpl;
	}

	public void setProdAssignImpl(ProductAssignmentImpl prodAssignImpl) {
		this.prodAssignImpl = prodAssignImpl;
	}

	public boolean isRenderheader() {
		return renderheader;
	}

	public void setRenderheader(boolean renderheader) {
		this.renderheader = renderheader;
	}


	public SoldProduct getSoldproduct() {
		return soldproduct;
	}


	public void setSoldproduct(SoldProduct soldproduct) {
		this.soldproduct = soldproduct;
	}


	public List<SoldProductDto> getBranchSoldProduct() {
		return branchSoldProduct;
	}


	public void setBranchSoldProduct(List<SoldProductDto> branchSoldProduct) {
		this.branchSoldProduct = branchSoldProduct;
	}


	public SoldProductImpl getSoldImpl() {
		return soldImpl;
	}


	public void setSoldImpl(SoldProductImpl soldImpl) {
		this.soldImpl = soldImpl;
	}


	public List<OrderProductDto> getOrderDetails() {
		return orderDetails;
	}


	public void setOrderDetails(List<OrderProductDto> orderDetails) {
		this.orderDetails = orderDetails;
	}


	public List<OrderProductDto> getDailyOrder() {
		return dailyOrder;
	}


	public void setDailyOrder(List<OrderProductDto> dailyOrder) {
		this.dailyOrder = dailyOrder;
	}


	public List<OrderProductDto> getBranchStatistics() {
		return branchStatistics;
	}


	public void setBranchStatistics(List<OrderProductDto> branchStatistics) {
		this.branchStatistics = branchStatistics;
	}


	public OrderProductDto getOrderDto() {
		return orderDto;
	}


	public void setOrderDto(OrderProductDto orderDto) {
		this.orderDto = orderDto;
	}


	public OrderProductImpl getOrderProdImpl() {
		return orderProdImpl;
	}


	public void setOrderProdImpl(OrderProductImpl orderProdImpl) {
		this.orderProdImpl = orderProdImpl;
	}


	public int getTotalorderqty() {
		return totalorderqty;
	}


	public void setTotalorderqty(int totalorderqty) {
		this.totalorderqty = totalorderqty;
	}


	public int getRemainedqty() {
		return remainedqty;
	}


	public void setRemainedqty(int remainedqty) {
		this.remainedqty = remainedqty;
	}


	public int getAssignqty() {
		return assignqty;
	}

	public void setAssignqty(int assignqty) {
		this.assignqty = assignqty;
	}


	public List<ProductAssignment> getProductAssignedList() {
		return productAssignedList;
	}


	public void setProductAssignedList(List<ProductAssignment> productAssignedList) {
		this.productAssignedList = productAssignedList;
	}


	public List<ProductAssignment> getProdAssigDetails() {
		return prodAssigDetails;
	}


	public void setProdAssigDetails(List<ProductAssignment> prodAssigDetails) {
		this.prodAssigDetails = prodAssigDetails;
	}


	public List<ProductAssignment> getProductassdetails() {
		return productassdetails;
	}


	public void setProductassdetails(List<ProductAssignment> productassdetails) {
		this.productassdetails = productassdetails;
	}	
	
}
