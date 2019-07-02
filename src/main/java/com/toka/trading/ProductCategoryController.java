package com.toka.trading;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;

import toka.common.DbConstant;
import toka.common.JSFMessagers;
import toka.common.SessionUtils;
import toka.dao.impl.DocumentsImpl;
import toka.dao.impl.OrderProductImpl;
import toka.dao.impl.ProductCategoryImpl;
import toka.dao.impl.ProductImpl;
import toka.dao.impl.UploadingFilesImpl;
import toka.domain.Branch;
import toka.domain.Contact;
import toka.domain.Documents;
import toka.domain.OrderProduct;
import toka.domain.Product;
import toka.domain.ProductCategory;
import toka.domain.UploadingFiles;
import toka.domain.Users;
import toka.trading.dto.OrderProductDto;
import toka.trading.dto.ProductCatDetailsDto;
import toka.trading.dto.ProductCategoryDtos;
import toka.trading.dto.ProductDto;
import toka.common.JSFBoundleProvider;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

@SuppressWarnings({ "unused" })
@ManagedBean
@ViewScoped
public class ProductCategoryController implements Serializable, DbConstant {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
	private String CLASSNAME = "ProductCategoryController :: ";
	private static final long serialVersionUID = 1L;
	/* to manage validation messages */
	private boolean isValid;
	private Users usersSession;
	private ProductCategory category;
	private OrderProduct orderproduct;
	private OrderProductDto orderproductDto;
	
	private boolean rendered, renderProductForm;
	private List<OrderProduct> orderList = new ArrayList<OrderProduct>();
	private List<ProductCategory> categoryList = new ArrayList<ProductCategory>();
	private List<ProductCategory> categoryDetails = new ArrayList<ProductCategory>();
	private List<ProductCatDetailsDto> branchCatDetails = new ArrayList<ProductCatDetailsDto>();
	private List<ProductCategoryDtos> categoryListDto = new ArrayList<ProductCategoryDtos>();
	ProductCategoryImpl categoryImpl = new ProductCategoryImpl();
	Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
	JSFBoundleProvider provider = new JSFBoundleProvider();
	private Documents document;
	OrderProductImpl orderProdImpl = new OrderProductImpl();
	private DocumentsImpl docsImpl = new DocumentsImpl();
	private UploadingFiles uploadingFiles;
	private UploadingFilesImpl uplActImpl = new UploadingFilesImpl();
	private int id=0;
private  Branch branch;
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		HttpSession session = SessionUtils.getSession();
		usersSession = (Users) session.getAttribute("userSession");
		if (category == null) {
			category = new ProductCategory();
		}
		if (branch == null) {
			branch = new Branch();
		}
		
		
		try {

			orderList=orderProdImpl.getGenericListWithHQLParameter(new String[] { "genericStatus","customer" },
				new Object[] { ACTIVE, usersSession }, "OrderProduct", " orderDate desc");
		
			categoryList = categoryImpl.getGenericListWithHQLParameter(new String[] { "genericStatus" },
					new Object[] { ACTIVE }, "ProductCategory", "upDtTime desc");
//			categoryDetails = categoryImpl.getGenericListWithHQLParameter(new String[] { "genericStatus", "branch" },
//					new Object[] { ACTIVE, id }, "ProductCategory", " upDtTime desc");
			

			this.rendered = true;
		showAvailProduct(categoryList);
		categoryListDto =listCategory(categoryList);
		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}

	}

	public List<ProductCatDetailsDto> showBranchProduct(Branch branch) {
		if(null!=branch) {
		LOGGER.info("BRANCH_ID IS:::::::"+branch.getBranchId());
		for (Object[] data : uplActImpl.reportList(
				"select f.productCategory,f.documents,f.user,count(prod.productCategory) from UploadingFiles f,ProductCategory cat,Users us,Documents d,Product prod  where f.documents=d.DocId and f.productCategory=cat.productCatid and f.user=us.userId and cat.branch="
						+ branch.getBranchId()
						+ " and prod.productCategory=cat.productCatid group by f.productCategory")) {
			ProductCatDetailsDto details = new ProductCatDetailsDto();
			details.setProdCategory((ProductCategory) data[0]);
			details.setDocuments((Documents) data[1]);
			details.setUser((Users) data[2]);
			details.setProductCount(Integer.parseInt(data[3] + ""));
			branchCatDetails.add(details);
		}
		
		}
		return (branchCatDetails);
	}
	public void showAvailProduct(List<ProductCategory> list) {
		if (list.size() > 0) {
			this.rendered = true;
			this.renderProductForm = false;
		} else {
			this.renderProductForm = true;
			this.rendered = false;
		}
	}

	public void showCategoryForm() {
		this.rendered=false;
		this.renderProductForm=true;
	}

	public String viewpCategory() {
		
		
		return "/menu/ViewProdCat.xhtml?faces-redirect=true";
	}
	
	@SuppressWarnings("rawtypes")
	public List listCategory(List<ProductCategory> CatDetails) {
		List<ProductCategoryDtos> categoryDtoDetails = new ArrayList<ProductCategoryDtos>();
		for (ProductCategory Cat : CatDetails) {
			ProductCategoryDtos catDto = new ProductCategoryDtos();
			catDto.setEditable(false);
			catDto.setNotify(false);
			catDto.setCatid(Cat.getProductCatid());
			catDto.setProductcategoryName(Cat.getProductCatName());
			catDto.setStatus(Cat.getGenericStatus());
			catDto.setCreatedBy(Cat.getCreatedBy());
			/*if (userCat.getStatus().equals(ACTIVE)) {
				catDto.setAction(DESACTIVE);
			} else {
				catDto.setAction(ACTIVE);
			}*/
			categoryDtoDetails.add(catDto);
		}
		return (categoryDtoDetails);
	}
	public String saveNewProductCategory() {
		try {
			HttpSession sessionuser = SessionUtils.getSession();

			try {
				if (null != category.getProductCatName()) {	
				}else {
					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("error.server.side.nullvalue"));
					return null;
				}

			} catch (Exception e) {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
				LOGGER.info(CLASSNAME + "" + e.getMessage());
				e.printStackTrace();
				return null;
			}
			
				category.setCreatedBy(usersSession.getViewId());
				category.setCrtdDtTime(timestamp);
				category.setGenericStatus(ACTIVE);
				category.setUpDtTime(timestamp);
				category.setCreatedBy(usersSession.getViewId());
				category.setCrtdDtTime(timestamp);
				category.setGenericStatus(ACTIVE);
				category.setUpDtTime(timestamp);
				category.setBranch(usersSession.getBranch());
				categoryImpl.saveProductCategory(category);
				JSFMessagers.resetMessages();
				setValid(true); 
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.productcategory"));
				
			if (null != category) {
				// Session creation to get user info from dataTable row
				sessionuser.setAttribute("CatImage", category);
				LOGGER.info("Info Founded are product:>>>>>>>>>>>>>>>>>>>>>>>:" + category.getProductCatName() + "ID:"
						+ category.getProductCatid());
			}
			//clearContactFuileds();
			return "/menu/CategoryImageUpload.xhtml?faces-redirect=true";
			

		}catch(HibernateException e)
		{
		LOGGER.info(CLASSNAME + ":::Product Category Details is fail with HibernateException  error");
		JSFMessagers.resetMessages();
		setValid(false);
		JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.productcat"));
		LOGGER.info(CLASSNAME + "" + e.getMessage());
		e.printStackTrace();
		return "";
	}
	}

	public String deleteFile(UploadingFiles info) {
		try {
			ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String realPath = ctx.getRealPath("/");
			LOGGER.info("Filse Reals Path::::" + realPath);
			Documents documents = new Documents();
			documents = docsImpl.getModelWithMyHQL(new String[] { "DocId" },
					new Object[] { info.getDocuments().getDocId() }, " from Documents");

			if (null != documents) {
				final Path destination = Paths.get(realPath + FILELOCATION + documents.getSysFilename());
				LOGGER.info("Path::" + destination);
				File file = new File(destination.toString());
				uplActImpl.deleteIntable(info);
				docsImpl.deleteIntable(documents);
				LOGGER.info("Delete in db operation done!!!:");
				if (file.delete()) {
					System.out.println(file.getName() + " is deleted!");
					JSFMessagers.resetMessages();
					setValid(true);
					JSFMessagers.addErrorMessage(getProvider().getValue("com.server.success.files.delete"));
				} else {
					System.out.println("Delete operation is failed.");
					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("com.server.error.files.delete"));
				}

			}

		} catch (Exception e) {
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
		}
		return null;
	}
	public ProductCategory saveProductCategoryFiles() {
		HttpSession session = SessionUtils.getSession();
		// Get the values from the session
		category = (ProductCategory) session.getAttribute("CatImage");
		return (category);
	}
	public String editAction(ProductCategoryDtos cat) {
		cat.setEditable(true);
		return null;
	}
	public String cancel(ProductCategoryDtos cat) {
		cat.setEditable(false);
		return null;
	}
	public String saveAction(ProductCategoryDtos cat) {
		LOGGER.info("update  saveAction method");
		// get all existing value but set "editable" to false
		try {
			if (null != cat) {
				LOGGER.info("UserCat:++++++++++++++++++++++++++" + cat.getCatid());
				ProductCategory procat = new ProductCategory();
				procat = new ProductCategory();
				procat = categoryImpl.getProductCategoryById(cat.getCatid(), "productCatid");

				LOGGER.info("here update sart for " + procat + " useriD " + procat.getProductCatid());

				cat.setEditable(false);
				procat.setUpdatedBy(usersSession.getViewId());
				procat.setUpDtTime(timestamp);
				procat.setProductCatName(cat.getProductcategoryName());
				categoryImpl.UpdateProductCategory(procat);
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
	
	@SuppressWarnings("unchecked")
	public void cancelOrder(OrderProduct order) {
		LOGGER.info("new Status::::"+order.getStatus());
		try {
			if(order.getStatus().equalsIgnoreCase("pending")) {
				orderproduct=order;
				orderproduct.setGenericStatus("desactive");
				orderProdImpl.updateOrderProduct(orderproduct);
				LOGGER.info("new Status::::"+orderproduct.getGenericStatus());
				orderList=orderProdImpl.getGenericListWithHQLParameter(new String[] { "genericStatus","customer" },
						new Object[] { ACTIVE, usersSession }, "OrderProduct", " orderDate desc");
				JSFMessagers.resetMessages();
				setValid(true);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.orderupdate"));
			}
			else {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.canceled.order"));
			}
//			else if(order.getStatus().equalsIgnoreCase("processed")) {
//				JSFMessagers.resetMessages();
//				setValid(false);
//				JSFMessagers.addErrorMessage(getProvider().getValue("com.not.update.order"));
//			}
//			else {
//				JSFMessagers.resetMessages();
//				setValid(false);
//				JSFMessagers.addErrorMessage(getProvider().getValue("com.sold.order"));
//			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage()+"::::::Can't update the status!!!!");
		}
}
	
	public void clearContactFuileds() {
		category = new ProductCategory();
		// usersDetails=null;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public Users getUsersSession() {
		return usersSession;
	}

	public void setUsersSession(Users usersSession) {
		this.usersSession = usersSession;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getCLASSNAME() {
		return CLASSNAME;
	}

	public void setCLASSNAME(String cLASSNAME) {
		CLASSNAME = cLASSNAME;
	}

	public JSFBoundleProvider getProvider() {
		return provider;
	}

	public void setProvider(JSFBoundleProvider provider) {
		this.provider = provider;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}

	public boolean isRenderProductForm() {
		return renderProductForm;
	}

	public void setRenderProductForm(boolean renderProductForm) {
		this.renderProductForm = renderProductForm;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public List<ProductCategory> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<ProductCategory> categoryList) {
		this.categoryList = categoryList;
	}

	public ProductCategoryImpl getCategoryImpl() {
		return categoryImpl;
	}

	public void setCategoryImpl(ProductCategoryImpl categoryImpl) {
		this.categoryImpl = categoryImpl;
	}

	public List<ProductCategoryDtos> getCategoryListDto() {
		return categoryListDto;
	}

	public void setCategoryListDto(List<ProductCategoryDtos> categoryListDto) {
		this.categoryListDto = categoryListDto;
	}

	public Documents getDocument() {
		return document;
	}

	public void setDocument(Documents document) {
		this.document = document;
	}

	public DocumentsImpl getDocsImpl() {
		return docsImpl;
	}

	public void setDocsImpl(DocumentsImpl docsImpl) {
		this.docsImpl = docsImpl;
	}

	public UploadingFiles getUploadingFiles() {
		return uploadingFiles;
	}

	public void setUploadingFiles(UploadingFiles uploadingFiles) {
		this.uploadingFiles = uploadingFiles;
	}

	public UploadingFilesImpl getUplActImpl() {
		return uplActImpl;
	}

	public void setUplActImpl(UploadingFilesImpl uplActImpl) {
		this.uplActImpl = uplActImpl;
	}

	public List<ProductCategory> getCategoryDetails() {
		return categoryDetails;
	}

	public void setCategoryDetails(List<ProductCategory> categoryDetails) {
		this.categoryDetails = categoryDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ProductCatDetailsDto> getBranchCatDetails() {
		return branchCatDetails;
	}

	public void setBranchCatDetails(List<ProductCatDetailsDto> branchCatDetails) {
		this.branchCatDetails = branchCatDetails;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public OrderProduct getOrderproduct() {
		return orderproduct;
	}

	public void setOrderproduct(OrderProduct orderproduct) {
		this.orderproduct = orderproduct;
	}

	public OrderProductDto getOrderproductDto() {
		return orderproductDto;
	}

	public void setOrderproductDto(OrderProductDto orderproductDto) {
		this.orderproductDto = orderproductDto;
	}

	public List<OrderProduct> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderProduct> orderList) {
		this.orderList = orderList;
	}
	
}
