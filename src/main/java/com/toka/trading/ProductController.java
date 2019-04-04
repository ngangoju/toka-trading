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
import toka.dao.impl.ProductCategoryImpl;
import toka.dao.impl.ProductImpl;
import toka.dao.impl.UploadingFilesImpl;
import toka.domain.Contact;
import toka.domain.Documents;
import toka.domain.Product;
import toka.domain.ProductCategory;
import toka.domain.UploadingFiles;
import toka.domain.Users;
import toka.trading.dto.ProductCategoryDtos;
import toka.trading.dto.ProductDto;
import toka.common.JSFBoundleProvider;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

@ManagedBean
@ViewScoped
public class ProductController implements Serializable, DbConstant {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
	private String CLASSNAME = "ProductController :: ";
	private static final long serialVersionUID = 1L;
	/* to manage validation messages */
	private boolean isValid;
	private Users usersSession;
	private Product product;
	private ProductCategory category;
	private boolean rendered, renderProductForm, renderDetails;
	private List<Product> productList = new ArrayList<Product>();
	private List<Product> productBranchList = new ArrayList<Product>();
	private List<ProductCategory> categoryList = new ArrayList<ProductCategory>();
	private List<ProductDto> prodDto = new ArrayList<ProductDto>();
	private List<UploadingFiles> filesUploaded = new ArrayList<UploadingFiles>();
	ProductDto pdto = new ProductDto();
	ProductImpl productImpl = new ProductImpl();
	ProductCategoryImpl categoryImpl = new ProductCategoryImpl();
	Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
	JSFBoundleProvider provider = new JSFBoundleProvider();
	private Documents document;
	private DocumentsImpl docsImpl = new DocumentsImpl();
	private UploadingFiles uploadingFiles;
	private UploadingFilesImpl uplActImpl = new UploadingFilesImpl();

	private Date manufDate, expDate;
	private int productCatid;
	private String unitprice;
	private String quantity;
	private double totalprice;
	private double salesprice;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		HttpSession session = SessionUtils.getSession();
		usersSession = (Users) session.getAttribute("userSession");
		if (product == null) {
			product = new Product();
		}
		try {

			productList = productImpl.getGenericListWithHQLParameter(new String[] { "genericStatus" },
					new Object[] { ACTIVE, }, "Product", " upDtTime desc");

			categoryList = categoryImpl.getGenericListWithHQLParameter(new String[] { "genericStatus" },
					new Object[] { ACTIVE, }, "ProductCategory", " upDtTime desc");

			productBranchList = productImpl.getGenericListWithHQLParameter(new String[] { "genericStatus", "branch" },
					new Object[] { ACTIVE, usersSession.getBranch() }, "Product", " upDtTime desc");
			showAvailProduct(productBranchList);
			prodDto = listProduct(productBranchList);
		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}

	}

	@SuppressWarnings("rawtypes")
	public List listProduct(List<Product> prodDetails) {
		List<ProductDto> productDtoDetails = new ArrayList<ProductDto>();
		for (Product prod : prodDetails) {
			ProductDto prodDto = new ProductDto();
			prodDto.setEditable(false);
			prodDto.setProductId(prod.getProductId());
			prodDto.setProductName(prod.getProductName());
			prodDto.setQuantity(String.valueOf(prod.getQuantity()));
			prodDto.setProductCategory(prod.getProductCategory());
			prodDto.setPurchaseUnitPrice(String.valueOf(prod.getPurchaseUnitPrice()));
			prodDto.setSellingUnitPrice(String.valueOf(prod.getSellingUnitPrice()));
			prodDto.setManufactDate(prod.getManufactDate());
			prodDto.setExpireDate(prod.getExpireDate());
			prodDto.setBranch(prod.getBranch());
			prodDto.setCreatedBy(prod.getCreatedBy());
			prodDto.setStatus(prod.getGenericStatus());

			productDtoDetails.add(prodDto);
		}
		return (productDtoDetails);
	}

	public void showAvailProduct(List<Product> list) {
		if (list.size() > 0) {
			this.rendered = true;
			this.renderProductForm = false;
		} else {
			this.renderProductForm = true;
			this.rendered = false;
		}
	}

	public void showProductForm() {
		this.rendered = false;
		this.renderProductForm = true;
	}

	public String saveNewProduct() {
		try {

			try {

				category = categoryImpl.getProductCategoryById(productCatid, "productCatid");
				Product pdt = new Product();
				pdt = product;
				if (null != pdt.getProductName() && decimalpoint != Double.parseDouble(quantity) && null != category
						&& decimalpoint != Double.parseDouble(unitprice) && null != manufDate && null != expDate
						&& null != category) {
				} else {
					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("error.server.side.nullvalue"));
					LOGGER.info(CLASSNAME + "sivaserside validation ::product details must be supplied ");
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
			SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
			String manuf = smf.format(manufDate);
			String exp = smf.format(expDate);
			Date date1 = smf.parse(manuf);
			Date date2 = smf.parse(exp);

			if (date2.compareTo(date1) > 0) {
				LOGGER.info("Date2 is after Date1");
				product.setCreatedBy(usersSession.getViewId());
				product.setCrtdDtTime(timestamp);
				product.setGenericStatus(ACTIVE);
				product.setUpDtTime(timestamp);
				product.setCreatedBy(usersSession.getViewId());
				product.setCrtdDtTime(timestamp);
				product.setGenericStatus(ACTIVE);
				product.setUpDtTime(timestamp);
				product.setPurchaseUnitPrice(Double.parseDouble(unitprice));
				product.setQuantity(Double.parseDouble(quantity));
				product.setManufactDate(new java.sql.Date(manufDate.getTime()));
				product.setExpireDate(new java.sql.Date(expDate.getTime()));
				product.setExpireDate(expDate);
				product.setBranch(usersSession.getBranch());
				product.setProductCategory(category);
				productImpl.saveProduct(product);
				JSFMessagers.resetMessages();
				setValid(true);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.product"));

			} else {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.error.expaireddate.product"));
			}
			clearContactFuileds();
			return null;

		} catch (HibernateException | ParseException e) {
			LOGGER.info(CLASSNAME + ":::Product Details is fail with HibernateException  error");
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.product"));
			LOGGER.info(CLASSNAME + "" + e.getMessage());
			e.printStackTrace();
			return "";
		}
	}

	private void clearContactFuileds() {

		product = new Product();
		unitprice = null;
		quantity = null;
		manufDate = null;
		expDate = null;
		// usersDetails=null;
	}

	public String editAction(ProductDto prod) {
		prod.setEditable(true);
		return null;
	}

	public String cancel(ProductDto prod) {
		prod.setEditable(false);
		return null;
	}

	public String viewDetails(ProductDto prod) throws Exception {
		LOGGER.info(":::Product Info::" + prod.getProductId());
		Product product = new Product();
		product = productImpl.getProductById(prod.getProductId(), "productId");
		uploadingFiles = uplActImpl.getModelWithMyHQL(new String[] { "product" }, new Object[] { product },
				"from UploadingFiles");
//		LOGGER.info(
//				"::::Product Details:" + uploadingFiles.getProduct() + ":Documents::" + uploadingFiles.getDocuments());
		if(null!=uploadingFiles) {
		totalprice = totalPrice(prod);
		salesprice = sellingPrice(prod);
		this.rendered = false;
		this.renderDetails = true;
		}
		else {
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.error.failtoload.productdetails"));
		}
		return null;
	}

	public double totalPrice(ProductDto prod) {
		return (Double.parseDouble(prod.getQuantity()) * Double.parseDouble(prod.getPurchaseUnitPrice()));
	}

	public double sellingPrice(ProductDto prod) {
		if(defaultCount!=Double.parseDouble(prod.getSellingUnitPrice())){
			return (Double.parseDouble(prod.getQuantity()) * Double.parseDouble(prod.getSellingUnitPrice()));
		}else {
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.error.failtoload.productdetails"));
		}
		return defaultCount;
	}
		

	public String saveSalesPriceAction(ProductDto prod) {
		try {

			if (null != prod) {
				LOGGER.info("product:++++++++++++++++++++++++++" + prod.getProductId());
				Product procat = new Product();
				procat = new Product();
				procat = productImpl.getProductById(prod.getProductId(), "productId");

				prod.setEditable(false);
				procat.setUpdatedBy(usersSession.getViewId());
				procat.setUpDtTime(timestamp);
				procat.setSellingUnitPrice(Double.parseDouble(prod.getSellingUnitPrice()));
				productImpl.UpdateProduct(procat);
				JSFMessagers.resetMessages();
				setValid(true);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.productsalespriceupdate"));
			} else {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.error.failupdate.product"));
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

	public String saveAction(ProductDto prod) {
		LOGGER.info("update  saveAction method");
		// get all existing value but set "editable" to false
		try {
			boolean valid = compareDate(prod);
			if (null != prod && valid) {
				LOGGER.info("product:++++++++++++++++++++++++++" + prod.getProductId());
				Product procat = new Product();
				procat = new Product();
				procat = productImpl.getProductById(prod.getProductId(), "productId");

				prod.setEditable(false);
				procat.setUpdatedBy(usersSession.getViewId());
				procat.setUpDtTime(timestamp);
				procat.setManufactDate(prod.getManufactDate());
				procat.setExpireDate(prod.getExpireDate());
				procat.setQuantity(Double.parseDouble(prod.getQuantity()));
				procat.setPurchaseUnitPrice(Double.parseDouble(prod.getPurchaseUnitPrice()));
				productImpl.UpdateProduct(procat);
				JSFMessagers.resetMessages();
				setValid(true);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.productupdate"));
			} else {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.error.expaireddate.product"));
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

	public boolean compareDate(ProductDto prod) {
		boolean valid = false;
		try {
			SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
			String manuf = smf.format(prod.getManufactDate());
			String exp = smf.format(prod.getExpireDate());
			Date date1 = smf.parse(manuf);
			Date date2 = smf.parse(exp);

			if (date2.compareTo(date1) > 0) {
				valid = true;
			} else {
				valid = false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return valid;
	}

	public String uploadAction(ProductDto dtos) {
		HttpSession sessionuser = SessionUtils.getSession();

		if (null != dtos) {
			// Session creation to get user info from dataTable row
			sessionuser.setAttribute("Prxdtpic", dtos);
			LOGGER.info("Info Founded are product:>>>>>>>>>>>>>>>>>>>>>>>:" + dtos.getProductName() + "ID:"
					+ dtos.getProductId());
		}
		return "/menu/ProductImageUpload.xhtml?faces-redirect=true";
	}

	public ProductDto saveProductFiles() {
		HttpSession session = SessionUtils.getSession();
		// Get the values from the session
		pdto = (ProductDto) session.getAttribute("Prxdtpic");
		return (pdto);
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

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public ProductImpl getProductImpl() {
		return productImpl;
	}

	public void setProductImpl(ProductImpl productImpl) {
		this.productImpl = productImpl;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getManufDate() {
		return manufDate;
	}

	public void setManufDate(Date manufDate) {
		this.manufDate = manufDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
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

	public int getProductCatid() {
		return productCatid;
	}

	public void setProductCatid(int productCatid) {
		this.productCatid = productCatid;
	}

	public String getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public List<ProductDto> getProdDto() {
		return prodDto;
	}

	public void setProdDto(List<ProductDto> prodDto) {
		this.prodDto = prodDto;
	}

	public ProductDto getPdto() {
		return pdto;
	}

	public void setPdto(ProductDto pdto) {
		this.pdto = pdto;
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

	public UploadingFilesImpl getUplActImpl() {
		return uplActImpl;
	}

	public void setUplActImpl(UploadingFilesImpl uplActImpl) {
		this.uplActImpl = uplActImpl;
	}

	public List<Product> getProductBranchList() {
		return productBranchList;
	}

	public void setProductBranchList(List<Product> productBranchList) {
		this.productBranchList = productBranchList;
	}

	public boolean isRenderDetails() {
		return renderDetails;
	}

	public void setRenderDetails(boolean renderDetails) {
		this.renderDetails = renderDetails;
	}

	public List<UploadingFiles> getFilesUploaded() {
		return filesUploaded;
	}

	public void setFilesUploaded(List<UploadingFiles> filesUploaded) {
		this.filesUploaded = filesUploaded;
	}

	public UploadingFiles getUploadingFiles() {
		return uploadingFiles;
	}

	public void setUploadingFiles(UploadingFiles uploadingFiles) {
		this.uploadingFiles = uploadingFiles;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public double getSalesprice() {
		return salesprice;
	}

	public void setSalesprice(double salesprice) {
		this.salesprice = salesprice;
	}

}
