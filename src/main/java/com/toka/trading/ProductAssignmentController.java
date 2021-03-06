package com.toka.trading;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
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
import com.google.gson.Gson;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import toka.common.DbConstant;
import toka.common.JSFMessagers;
import toka.common.SessionUtils;
import toka.dao.impl.BranchImpl;
import toka.dao.impl.DocumentsImpl;
import toka.dao.impl.OrderProductImpl;
import toka.dao.impl.PerishedProductImpl;
import toka.dao.impl.ProductAssignmentImpl;
import toka.dao.impl.ProductCategoryImpl;
import toka.dao.impl.ProductImpl;
import toka.dao.impl.UploadingFilesImpl;
import toka.domain.Branch;
import toka.domain.Contact;
import toka.domain.Documents;
import toka.domain.OrderProduct;
import toka.domain.PerishedProduct;
import toka.domain.Product;
import toka.domain.ProductAssignment;
import toka.domain.ProductCategory;
import toka.domain.UploadingFiles;
import toka.domain.UserCategory;
import toka.domain.Users;
import toka.trading.dto.OrderProductDto;
import toka.trading.dto.ProductCatDetailsDto;
import toka.trading.dto.ProductCategoryDtos;
import toka.trading.dto.ProductDto;
import toka.common.JSFBoundleProvider;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

@ManagedBean
@ViewScoped
public class ProductAssignmentController implements Serializable, DbConstant {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
	private String CLASSNAME = "ProductAssignmentController :: ";
	private static final long serialVersionUID = 1L;
	/* to manage validation messages */
	private boolean isValid;
	private Users usersSession;
	private Product product;
	private PerishedProduct perished;
	private String perishedQuantity, overallDailOrderProcessed, overallDailBranchOrderProcessed;
	private ProductCategory category;
	private ProductAssignment productAssignment;
	private OrderProduct orderproduct;
	private OrderProductDto orderproductDto;
	private List<ProductCategory> catbranch = new ArrayList<ProductCategory>();
	private boolean rendered, renderProductForm, renderDetails, renderproduct, renderbilling, renderOrder, renderheader,
			renderForm;
	private List<Product> productList = new ArrayList<Product>();
	private List<Product> productBranchList = new ArrayList<Product>();
	private List<ProductCategory> categoryList = new ArrayList<ProductCategory>();
	private List<ProductDto> prodDto = new ArrayList<ProductDto>();
	private List<UploadingFiles> filesUploaded = new ArrayList<UploadingFiles>();
	private List<PerishedProduct> perishedList = new ArrayList<PerishedProduct>();
	private List<ProductCatDetailsDto> branchCatDetails = new ArrayList<ProductCatDetailsDto>();
	private List<ProductCatDetailsDto> branchCatList = new ArrayList<ProductCatDetailsDto>();
	private List<OrderProductDto> orderDetails, dailyOrder, branchStatistics = new ArrayList<OrderProductDto>();
	private List<Product> productfulldetails = new ArrayList<Product>();
	private ProductAssignment prodAssign = new ProductAssignment();
	ProductAssignmentImpl prodAssignImpl = new ProductAssignmentImpl();
	private List<ProductAssignment> productAssignedList = new ArrayList<ProductAssignment>();
	private List<ProductAssignment> prodAssigDetails = new ArrayList<ProductAssignment>();
	private List<ProductAssignment> productassdetails = new ArrayList<ProductAssignment>();
	ProductDto pdto = new ProductDto();
	ProductImpl productImpl = new ProductImpl();
	private List<Branch> branchDetails = new ArrayList<Branch>();
	BranchImpl branchImpl = new BranchImpl();
	OrderProductImpl orderProdImpl = new OrderProductImpl();
	PerishedProductImpl perishImpl = new PerishedProductImpl();
	ProductCategoryImpl categoryImpl = new ProductCategoryImpl();
	Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
	JSFBoundleProvider provider = new JSFBoundleProvider();
	private Documents document;
	private DocumentsImpl docsImpl = new DocumentsImpl();
	private UploadingFiles uploadingFiles;
	private UploadingFilesImpl uplActImpl = new UploadingFilesImpl();
	private OrderProductDto orderDto = new OrderProductDto();
	private Date manufDate, expDate, perishedDate, todayDate;
	private Branch branch;
	private int productCatid;
	private String unitprice;
	private String quantity;
	private String quantit;
	private double totalprice;
	private double salesprice;
	private int id;
	private int branchId;
	private Date startDate;
	private Date endDate;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		HttpSession session = SessionUtils.getSession();
		usersSession = (Users) session.getAttribute("userSession");
		if (product == null) {
			product = new Product();
		}
		if (perished == null) {
			perished = new PerishedProduct();
		}
		if (orderDto == null) {
			orderDto = new OrderProductDto();
		}
		if (orderproduct == null) {
			orderproduct = new OrderProduct();
		}
		if (productAssignment == null) {
			productAssignment = new ProductAssignment();
		}
		if (null == branch) {
			branch = new Branch();
		}
		try {

			if (usersSession.getUserCategory().getUsercategoryName().equalsIgnoreCase("ceo")) {

				productList = productImpl.getGenericListWithHQLParameter(new String[] { "genericStatus" },
						new Object[] { ACTIVE, }, "Product", " upDtTime desc");

				categoryList = categoryImpl.getGenericListWithHQLParameter(new String[] { "genericStatus" },
						new Object[] { ACTIVE, }, "ProductCategory", " upDtTime desc");

				for (Object[] data : uplActImpl.reportList(
						"select  f.productCategory,f.documents,f.user,count(p.productId) from Users us,Documents d,Product p ,ProductCategory cat,UploadingFiles f where \r\n"
								+ "f.documents=d.DocId and p.productCategory=cat.productCatid and p.productCategory=f.productCategory and f.productCategory=cat.productCatid \r\n"
								+ "and f.user=us.userId   group by p.productCategory")) {
					ProductCatDetailsDto details = new ProductCatDetailsDto();
					details.setProdCategory((ProductCategory) data[0]);
					details.setDocuments((Documents) data[1]);
					details.setUser((Users) data[2]);
					details.setProductCount(Integer.parseInt(data[3] + ""));
					// details.setCatid(Integer.parseInt(data[3]+""));
					// details.setProductcategoryName(data[4]+"");
					branchCatList.add(details);
				}
				/*
				 * for (Object[] data : uplActImpl.reportList(
				 * "select o.orderProductId,o.orderDate,o.quantity,o.customer,cat.branch,o.product,p.sellingUnitPrice,co.email,co.phone from OrderProduct o,Product p,ProductCategory cat,Users us,Contact co,Branch b where o.product=p.productId and o.customer=us.userId and cat.branch="
				 * + usersSession.getBranch().getBranchId() +
				 * " and co.user=us.userId  and o.genericStatus='" + ACTIVE +
				 * "' group by o.orderProductId")) { OrderProductDto order = new
				 * OrderProductDto(); order.setOrderProductId(Integer.parseInt(data[0] + ""));
				 * order.setOrderDate((Date) data[1]);
				 * order.setQuantity(Integer.parseInt(data[2] + "")); order.setCustomer((Users)
				 * data[3]); order.setProduct((Product) data[5]);
				 * order.setSellingUnitPrice(data[6] + "");
				 * order.setTotalSales(Double.parseDouble(data[2] + "") *
				 * Double.parseDouble(data[6] + "")); order.setEmail(data[7] + "");
				 * order.setPhone(data[8] + ""); orderDetails.add(order); }
				 */
				/*
				 * id = (int) session.getAttribute("branchId");
				 * LOGGER.info("BRANCH_ID IS:::::::"+id); for (Object[] data :
				 * uplActImpl.reportList(
				 * "select f.productCategory,f.documents,f.user,count(prod.productCategory) from UploadingFiles f,ProductCategory cat,Users us,Documents d,Product prod  where f.documents=d.DocId and f.productCategory=cat.productCatid and f.user=us.userId and cat.branch="
				 * + id +
				 * " and prod.productCategory=cat.productCatid group by f.productCategory")) {
				 * LOGGER.info("BRANCH_ID2 IS:::::::"+id); ProductCatDetailsDto details = new
				 * ProductCatDetailsDto(); details.setProdCategory((ProductCategory) data[0]);
				 * details.setDocuments((Documents) data[1]); details.setUser((Users) data[2]);
				 * details.setProductCount(Integer.parseInt(data[3] + "")); //
				 * details.setCatid(Integer.parseInt(data[3]+"")); //
				 * details.setProductcategoryName(data[4]+""); branchCatDetails.add(details);
				 * LOGGER.info("BRANCH_ID3 IS:::::::"+id); } }
				 */

				productfulldetails = productFullDetails();
				productassdetails = prodAssignImpl.getGenericListWithHQLParameter(new String[] { "genericStatus" },
						new Object[] { ACTIVE, }, "ProductAssignment", " assignDate desc");
				productAssignedList = productAssignedList(productassdetails);
				this.rendered = true;

				// daily successful served order and sort it buy branchs;
				/*
				 * overallDailOrderProcessed=overalldailyOrderStatistics(); LOGGER
				 * .info("JSON VALUE HERE:::"+overallDailOrderProcessed);
				 */

				branchDetails = branchImpl.getGenericListWithHQLParameter(new String[] { "genericStatus" },
						new Object[] { ACTIVE }, "Branch", "branchId asc");
				if (branchDetails.size() > 0) {
					this.rendered = true;
//				renderheader=true;
				}

			} else if (usersSession.getUserCategory().getUsercategoryName().equalsIgnoreCase("customer")) {
				this.renderDetails = true;
				id = (int) session.getAttribute("branchId");
				LOGGER.info("BRANCH_ID INGANA NA:::::::" + id);
				productassdetails = prodAssignImpl.getGenericListWithHQLParameter(
						new String[] { "genericStatus", "branch" },
						new Object[] { ACTIVE, branchImpl.getBranchById(id, "branchId") }, "ProductAssignment",
						" assignDate desc");
				productAssignedList = productAssignedList(productassdetails);
			} else {
				prodAssigDetails = prodAssignImpl.getGenericListWithHQLParameter(
						new String[] { "genericStatus", "branch" }, new Object[] { ACTIVE, usersSession.getBranch() },
						"ProductAssignment", " assignDate desc");
				productAssignedList = productAssignedList(prodAssigDetails);
				branchStatistics();
			}
		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}

	}

	public void branchProductStat(Branch bList) {
		if (null != bList) {
//			this.renderForm = false;
			this.rendered = false;
			this.renderheader = true;
			HttpSession sessionuser = SessionUtils.getSession();
			sessionuser.setAttribute("branchdetails", bList);
		}
	}

	public String overalldailyOrderStatistics() throws ParseException {

		HttpSession session = SessionUtils.getSession();
		branch = (Branch) session.getAttribute("branchdetails");

		LOGGER.info("::::BRANCH DETAILS:::" + branch.getBranchName());
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal1 = new GregorianCalendar();
		cal1.setTime(todayDate);
		LOGGER.info("Orginal date is ::" + smf.format(todayDate));
		cal1.add(Calendar.DATE, 1);
		java.util.Date dDate = cal1.getTime();
		// cal1.add(Calendar.DATE, policy.getVariation());
		String dt = smf.format(dDate);
		LOGGER.info("Modified date is ::" + dt);

		dailyOrder = new ArrayList<OrderProductDto>();
		for (Object[] data : orderProdImpl.reportList(
				"select ass.branch,ass.assignDate,o.orderDate,o.quantity,sum(o.quantity) as totalProccessedQty ,ass.product,o.customer, o.productInfo \r\n"
						+ "from OrderProduct o,ProductAssignment ass where ass. prodAssId=o.productInfo and o.orderDate between '"
						+ smf.format(todayDate) + "' and '" + dt + "' and ass.branch=" + branch.getBranchId()
						+ " group by ass.product")) {
			OrderProductDto odto = new OrderProductDto();
			odto.setQuantity(Integer.parseInt(data[4] + ""));
			odto.setBranch(((Product) data[5]).getProductName());
			LOGGER.info(":::QUANTITY::" + data[3] + "");
			LOGGER.info(":::BRANCH::" + ((Branch) data[0]).getBranchName());
			dailyOrder.add(odto);
		}
		if (dailyOrder.size() > 0) {
			this.overallDailOrderProcessed = new Gson().toJson(dailyOrder);
			this.renderOrder = true;
		} else {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.order.date.error"));
		}

		return overallDailOrderProcessed;
	}

	public void branchStatistics() throws ParseException {
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
		branchStatistics = new ArrayList<OrderProductDto>();
		for (Object[] data : orderProdImpl.reportList(
				"select ass.branch,ass.assignDate ,o.orderDate,o.quantity,sum(o.quantity) as totalProccessedQty ,o.customer, o.productInfo \r\n"
						+ "from OrderProduct o,ProductAssignment ass where ass.prodAssId=o.productInfo and ass.branch="
						+ usersSession.getBranch().getBranchId() + " group by DATE_FORMAT(orderDate,'%d/%m/%Y')")) {
			OrderProductDto odto = new OrderProductDto();
			odto.setBranchOrderDate(smf.format(((Timestamp) data[2])));
			odto.setQuantity(Integer.parseInt(data[4] + ""));

			LOGGER.info(":::QUANTITY::" + data[4] + "");
			LOGGER.info(":::Date order::" + smf.format((Timestamp) data[2]));
			branchStatistics.add(odto);
		}
		if (branchStatistics.size() > 0) {
			this.overallDailBranchOrderProcessed = new Gson().toJson(branchStatistics);
			/* this.renderOrder=true; */
		} else {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("order.branch.not.yet"));
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

	public List<ProductAssignment> productAssignedList(List<ProductAssignment> list) {
		productAssignedList = new ArrayList<ProductAssignment>();

		for (ProductAssignment ass : list) {
			ProductAssignment assign = new ProductAssignment();
			assign.setEditable(false);
			assign.setTotalprice(ass.getQuantity() * ass.getProduct().getPurchaseUnitPrice());
			assign.setTotalsales(ass.getQuantity() * ass.getProduct().getSellingUnitPrice());
			assign.setProdAssId(ass.getProdAssId());
			assign.setQuantity(ass.getQuantity());
			assign.setAssignDate(ass.getAssignDate());
			assign.setProduct(ass.getProduct());
			assign.setBranch(ass.getBranch());
			assign.setUnitprice(ass.getProduct().getPurchaseUnitPrice());
			assign.setSalesunit(ass.getProduct().getSellingUnitPrice());
			productAssignedList.add(assign);

		}
		return (productAssignedList);
	}

	public void createReport() {
		LOGGER.info("Arrived!!!!!");
	}

	public String processOrder(OrderProductDto processorder) {
		try {
			HttpSession sessionuser = SessionUtils.getSession();
			if (null != processorder) {
				orderproductDto = processorder;
				sessionuser.setAttribute("customerorder", orderproductDto);
				this.rendered = false;
				this.renderbilling = true;

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public String showproducts(ProductCatDetailsDto prod) {
		try {
			HttpSession sessionuser = SessionUtils.getSession();
			if (null != prod) {
				ProductCategory cat = new ProductCategory();
				cat = categoryImpl.getProductCategoryById(prod.getProdCategory().getProductCatid(), "productCatid");
				productBranchList = productImpl.getGenericListWithHQLParameter(
						new String[] { "genericStatus", "productCategory" }, new Object[] { ACTIVE, cat }, "Product",
						" upDtTime desc");
				prodDto = listProduct(productBranchList);
				this.rendered = false;
				this.renderDetails = true;
				sessionuser.setAttribute("productcategory", cat);
			}
		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();

		}
		return null;

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
			prodDto.setBranch(prod.getProductCategory().getBranch());
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
		this.renderDetails = false;
		this.renderProductForm = true;
	}

	public void showGraph() {
		this.rendered = false;
		this.renderDetails = false;
		this.renderProductForm = false;
		this.renderForm = true;
	}

	public void back() {
		this.rendered = false;
		this.renderDetails = true;
		this.renderProductForm = false;
	}
	
	public void backBtn() {
		this.rendered = true;
		this.renderDetails= false;
		this.renderForm = false;
	}

	public void orderProd(Product prod) {
		try {
			HttpSession sessionuser = SessionUtils.getSession();
			product = prod;
			sessionuser.setAttribute("productOrder", product);
			this.renderOrder = true;
			this.rendered = false;
			this.renderDetails = false;

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String saveNewProduct() {
		try {

			try {

				// category = categoryImpl.getProductCategoryById(productCatid, "productCatid");
				Product pdt = new Product();
				pdt = product;
				if (null != pdt.getProductName() && decimalpoint != Double.parseDouble(quantity)
						&& decimalpoint != Double.parseDouble(unitprice) && null != manufDate && null != expDate) {
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
			HttpSession session = SessionUtils.getSession();
			String manuf = smf.format(manufDate);
			String exp = smf.format(expDate);
			Date date1 = smf.parse(manuf);
			Date date2 = smf.parse(exp);
			ProductCategory cat = new ProductCategory();
			cat = (ProductCategory) session.getAttribute("productcategory");
			if (date2.compareTo(date1) > 0 && null != cat) {
				LOGGER.info("Date2 is after Date1");
				product.setCreatedBy(usersSession.getViewId());
				product.setCrtdDtTime(timestamp);
				product.setGenericStatus(ACTIVE);
				product.setUpDtTime(timestamp);
				product.setGenericStatus(ACTIVE);
				product.setUpdatedBy(usersSession.getViewId());
				product.setPurchaseUnitPrice(Double.parseDouble(unitprice));
				product.setQuantity(Double.parseDouble(quantity));
				product.setManufactDate(new java.sql.Date(manufDate.getTime()));
				product.setExpireDate(new java.sql.Date(expDate.getTime()));
				product.setExpireDate(expDate);
				// product.setBranch(usersSession.getBranch());
				product.setProductCategory(cat);
				// product.setProductCategory(category);
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

	public String viewpCategory(int pid) {
		LOGGER.info("NEW_ID:::" + pid);
		HttpSession sessionuser = SessionUtils.getSession();
		sessionuser.setAttribute("branchId", pid);
		return "/menu/ViewProdCat.xhtml?faces-redirect=true";
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

	public String edit(ProductAssignment prod) {
		prod.setEditable(true);
		return null;
	}

	public String cancell(ProductAssignment prod) {
		prod.setEditable(false);
		return null;
	}

	public String viewDetails(ProductDto prod) throws Exception {
		LOGGER.info(":::Product Info::" + prod.getProductId());
		Product product = new Product();
		product = productImpl.getProductById(prod.getProductId(), "productId");
		uploadingFiles = uplActImpl.getModelWithMyHQL(new String[] { "product" }, new Object[] { product },
				"from UploadingFiles");
		// LOGGER.info(
		// "::::Product Details:" + uploadingFiles.getProduct() + ":Documents::" +
		// uploadingFiles.getDocuments());
		if (null != uploadingFiles) {
			totalprice = totalPrice(prod);
			salesprice = sellingPrice(prod);
			this.renderDetails = false;
			this.renderproduct = true;
		} else {
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
		if (defaultCount != Double.parseDouble(prod.getSellingUnitPrice())) {
			return (Double.parseDouble(prod.getQuantity()) * Double.parseDouble(prod.getSellingUnitPrice()));
		} else {
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
				procat.setSellingUnitPrice(Double.parseDouble(prod.getSellingUnitPrice()));
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

	public String saveAct(ProductAssignment prod) {
		LOGGER.info("update  saveAction method");
		// get all existing value but set "editable" to false
		try {
			LOGGER.info("product:++++++++++++++++++++++++++" + prod.getProduct().getProductName());

			prod.setEditable(false);
			prod.setCreatedBy(usersSession.getViewId());
			prod.setCrtdDtTime(timestamp);
			prod.setGenericStatus(ACTIVE);
			prod.setUpdatedBy(usersSession.getViewId());
			prod.setUpDtTime(timestamp);
			prod.setQuantity(Integer.parseInt(quantit));
			prodAssignImpl.UpdateProductAssignment(prod);
			JSFMessagers.resetMessages();
			setValid(true);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.productupdate"));
			this.setQuantit(null);
		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.updateError"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}

		// return to current page
		return null;
	}

	public void finalOrder() {
		try {
//			try {
//
//				if (decimalpoint != Double.parseDouble(quantity)) {
//				} else {
//					JSFMessagers.resetMessages();
//					setValid(false);
//					JSFMessagers.addErrorMessage(getProvider().getValue("error.server.side.nullvalue"));
//					LOGGER.info(CLASSNAME + "");
//				}
//
//			} catch (Exception e) {
//				JSFMessagers.resetMessages();
//				setValid(false);
//				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
//				LOGGER.info(CLASSNAME + "" + e.getMessage());
//				e.printStackTrace();
//			}

			orderproduct.setCreatedBy(usersSession.getViewId());
			orderproduct.setCrtdDtTime(timestamp);
			orderproduct.setGenericStatus(ACTIVE);
			orderproduct.setUpDtTime(timestamp);
			orderproduct.setCreatedBy(usersSession.getViewId());
			orderproduct.setCrtdDtTime(timestamp);
			orderproduct.setOrderDate(timestamp);
			orderproduct.setQuantity(quantity);
			orderproduct.setProductInfo(productAssignment);
			orderproduct.setCustomer(usersSession);
			orderProdImpl.saveIntable(orderproduct);
			JSFMessagers.resetMessages();
			setValid(true);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.orderProduct"));
//				QrGenerator qr = new QrGenerator();
//				qr.generator(product);
			clearContactFuileds();

		} catch (HibernateException e) {
			LOGGER.info(CLASSNAME + ":::Product Details is fail with HibernateException  error");
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.product"));
			LOGGER.info(CLASSNAME + "" + e.getMessage());
			e.printStackTrace();
		}
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

	public String perishableProductAction(ProductDto dtos) {
		HttpSession sessionuser = SessionUtils.getSession();

		if (null != dtos) {
			// Session creation to get user info from dataTable row
			product = productImpl.getProductById(dtos.getProductId(), "productId");
			sessionuser.setAttribute("perished", product);
			LOGGER.info("Info Founded are product:>>>>>>>>>>>>>>>>>>>>>>>:" + dtos.getProductName() + "ID:"
					+ dtos.getProductId());
		}
		return "/menu/PerishableProduct.xhtml?faces-redirect=true";
	}

	public void managePerishableProduct() {
		try {

			HttpSession session = SessionUtils.getSession();
			// Get the values from the session
			product = (Product) session.getAttribute("perished");
			LOGGER.info("Perished Quantity:::" + perishedQuantity);
			if (product != null) {
				product.setQuantity(product.getQuantity() - Integer.parseInt(perishedQuantity));
				productImpl.UpdateProduct(product);
				perished.setCrtdDtTime(timestamp);
				perished.setGenericStatus(ACTIVE);
				perished.setUpDtTime(timestamp);
				perished.setCreatedBy(usersSession.getViewId());
				// perished.setProduct(product);
				perished.setQuantity(Integer.parseInt(perishedQuantity));
				perished.setPerichedDate(new java.sql.Date(perishedDate.getTime()));
				perishImpl.savePerishedProduct(perished);
				clearPerishedProduct();
				LOGGER.info("INFO SAVED!!!");
				JSFMessagers.resetMessages();
				setValid(true);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.productPerished"));
			} else {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.productfailPerished"));
			}

		} catch (HibernateException e) {
			LOGGER.info(CLASSNAME + ":::Product perishable Details is fail with HibernateException  error");
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.fail.form.productPerished"));
			LOGGER.info(CLASSNAME + "" + e.getMessage());
			e.printStackTrace();
		}

	}

	public void clearPerishedProduct() {
		perished = null;
		perishedQuantity = null;
		perishedDate = null;
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
	// CREATING FOOTER AND HEADER FOR PAGES

	/*
	 * class MyFooter extends PdfPageEventHelper {
	 * 
	 * Font ffont1 = new Font(Font.FontFamily.UNDEFINED, 12, Font.ITALIC);
	 * 
	 * Font ffont2 = new Font(Font.FontFamily.UNDEFINED, 16, Font.ITALIC);
	 * 
	 * public void onEndPage(PdfWriter writer, Document document) {
	 * 
	 * try { PdfContentByte cb = writer.getDirectContent(); Phrase header = new
	 * Phrase(""); document.add(new Paragraph("\n")); Phrase footer = new
	 * Phrase("@Copyright Toka trading...!\n", ffont2);
	 * ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, header,
	 * (document.right() - document.left()) / 2 + document.leftMargin(),
	 * document.top() + 10, 0); ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
	 * footer, (document.right() - document.left()) / 2 + document.leftMargin(),
	 * document.bottom() - 10, 0);
	 * 
	 * } catch (DocumentException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * } }
	 */

	public void generateBranchReview() throws IOException, DocumentException {
		LOGGER.info(":::::From " + startDate + " to " + endDate + " the branch with an id of ");
//		branch=branchImpl.getBranchById(branchId,"branchId");
//		if(branch!=null) {
		Font font0 = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
		Font font18 = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLDITALIC, BaseColor.BLUE);
		Font font8 = new Font(Font.FontFamily.TIMES_ROMAN, 11);
		Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
		Date date = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String xdate = dt.format(date);
		String sdate = dt.format(startDate);
		String edate = dt.format(endDate);
		FacesContext context = FacesContext.getCurrentInstance();
		Document document = new Document();
		Rectangle rect = new Rectangle(20, 20, 800, 600);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter writer = PdfWriter.getInstance(document, baos);
		MyFooter event = new MyFooter();
		writer.setPageEvent(event);
		writer.setBoxSize("art", rect);
		document.setPageSize(rect);
		if (!document.isOpen()) {
			document.open();
		}
//		Image img1 = Image.getInstance(
//				"C:\\Users\\TRES-SDA\\Documents\\EclipseProject\\Ferwafa\\RSIProject_Dev\\src\\main\\webapp\\resources\\image\\header.png");
		// LOGO IMAGE FOR TRESS
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String realPath = ctx.getRealPath("/");
		LOGGER.info("Filse Reals Path::::" + realPath);
		Path destination = null;
		String OS = null;
		if (OS == null) {
			OS = System.getProperty("os.name");
		}
		if (OS.startsWith("Windows")) {
			destination = Paths.get(realPath + FILELOCATION + "logo.jpg");
		} else {
			destination = Paths.get(realPath + FILELOCATIONUNIX + "logo.jpg");
			LOGGER.info("Path UNIX::" + destination);
		}
		Image img = Image.getInstance("" + destination);
		img.scaleAbsolute(50f, 50f);
		document.add(img);
		// document.add(img1);
		document.add(new Paragraph("\n"));
		PdfPTable table = new PdfPTable(9);

		table.setWidthPercentage(105);
		Paragraph header1 = new Paragraph("BRANCHES ORDER REPORT MADE FROM  " + sdate + " TO " + edate + "");
		// header.setAlignment(Element.ALIGN_RIGHT);
		header1.setAlignment(Element.ALIGN_CENTER);
		// header.add(header1);
		document.add(header1);
		document.add(new Paragraph("                                        "));
		// String myBoardName=t.getBoardName();

		PdfPCell pc = new PdfPCell(new Paragraph(""+branch.getBranchName(), font2));
		pc.setColspan(9);
		pc.setBackgroundColor(BaseColor.CYAN);
		pc.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(pc);

		PdfPCell pc0 = new PdfPCell(new Paragraph("No", font0));
		pc0.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(pc0);
		
		PdfPCell pc1 = new PdfPCell(new Paragraph("PRODUCT NAME", font0));
		pc1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(pc1);

		PdfPCell pc2 = new PdfPCell(new Paragraph("BRANCH", font0));
		pc2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(pc2);

		PdfPCell pc3 = new PdfPCell(new Paragraph("SELLING PRICE PER UNIT", font0));
		pc3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(pc3);

		PdfPCell pcS = new PdfPCell(new Paragraph("PURCHASE PRICE PER UNIT", font0));
		pcS.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(pcS);
		PdfPCell pc5 = new PdfPCell(new Paragraph("QUANTITY", font0));
		pc5.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(pc5);

		PdfPCell pc6 = new PdfPCell(new Paragraph("STATUS", font0));
		pc5.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(pc6);

		PdfPCell pc7 = new PdfPCell(new Paragraph("ORDER DATE", font0));
		pc5.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(pc7);
		PdfPCell pc8 = new PdfPCell(new Paragraph("CREATED BY", font0));
		pc5.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(pc8);
		table.setHeaderRows(1);

		LOGGER.info("PRODUCT Info:::::From " + startDate + " to " + endDate + "");
		
		int i = 1;

		String query;
		if(usersSession.getUserCategory().getUsercategoryName().equalsIgnoreCase("ceo")) {
			query = "SELECT pro.productName, b.branchName, pro.sellingUnitPrice, pro.purchaseUnitPrice, o.quantity, o.status, o.orderDate, o.createdBy from OrderProduct o, ProductAssignment p, Product pro, Branch b where p.prodAssId=o.productInfo and pro.productId=p.product and b.branchId=p.branch and p.branch="
					+ branchId + " and o.orderDate between '" + sdate + "' and '" + edate
					+ "' group by o.orderDate";}
		else {
			query = "SELECT pro.productName, b.branchName, pro.sellingUnitPrice, pro.purchaseUnitPrice, o.quantity, o.status, o.orderDate, o.createdBy from OrderProduct o, ProductAssignment p, Product pro, Branch b where p.prodAssId=o.productInfo and pro.productId=p.product and b.branchId=p.branch and p.branch="
					+ usersSession.getBranch().getBranchId() + " and o.orderDate between '" + sdate + "' and '" + edate
					+ "' group by o.orderDate";
		}
		for (Object[] data : orderProdImpl.reportList(query)) {
			LOGGER.info("PRODUCT Infos:::");
			PdfPCell pcel1 = new PdfPCell(new Paragraph(i + "", font8));
			pcel1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(pcel1);

			PdfPCell pcel2 = new PdfPCell(new Paragraph(data[0] + "", font8));
			pcel2.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(pcel2);

			PdfPCell pcel3 = new PdfPCell(new Paragraph(data[1] + "", font8));
			pcel3.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(pcel3);

			PdfPCell pcel4 = new PdfPCell(new Paragraph(data[2] + "", font8));
			pcel4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(pcel4);

			PdfPCell pcele = new PdfPCell(new Paragraph(data[3] + "", font8));
			pcele.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(pcele);

			PdfPCell pcel5 = new PdfPCell(new Paragraph(data[4] + "", font8));
			pcele.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(pcel5);

			PdfPCell pcel6 = new PdfPCell(new Paragraph(data[5] + "", font8));
			pcele.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(pcel6);

			PdfPCell pcel7 = new PdfPCell(new Paragraph(data[6] + "", font8));
			pcele.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(pcel7);

			PdfPCell pcel8 = new PdfPCell(new Paragraph(data[7] + "", font8));
			pcele.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(pcel8);
			i++;
		}
		LOGGER.info("PRODUCT DETAILS:::::From " + sdate + " to " + edate + "");
		
		document.add(table);
		document.add(new Paragraph("\n"));
		document.add(new Paragraph("\n"));
		document.add(new Paragraph(new Chunk("Printed By:  " + usersSession.getViewId() + "on "+xdate,
				FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.ORANGE))));
		document.add(new Paragraph("\n"));
//		Image img2 = Image.getInstance("C:\\Users\\dff\\Documents\\" + orderDto.getOrderProductId() + ".png");
//		img.scaleAbsolute(150f, 150f);
//		document.add(img2);
		document.close();

		writePDFToResponse(context.getExternalContext(), baos, usersSession.getUserCategory().getUsercategoryName()+"_report_"+date);

		context.responseComplete();
//		branch = new Branch();
//		}
//		else {
//
//			JSFMessagers.resetMessages();
//			setValid(false);
//			JSFMessagers.addErrorMessage(getProvider().getValue("com.branch.select.nullvalue"));
//			LOGGER.info(CLASSNAME + "sivaserside validation ::Branch must be selected ");
//		}

	}

	public void writePDFToResponse(ExternalContext externalContext, ByteArrayOutputStream baos, String fileName) {
		try {
			externalContext.responseReset();
			externalContext.setResponseContentType("application/pdf");
			externalContext.setResponseHeader("Expires", "0");
			externalContext.setResponseHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			externalContext.setResponseHeader("Pragma", "public");
			externalContext.setResponseHeader("Content-disposition", "attachment;filename=" + fileName + ".pdf");
			externalContext.setResponseContentLength(baos.size());
			OutputStream out = externalContext.getResponseOutputStream();
			baos.writeTo(out);
			externalContext.responseFlushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public PerishedProduct getPerished() {
		return perished;
	}

	public void setPerished(PerishedProduct perished) {
		this.perished = perished;
	}

	public List<PerishedProduct> getPerishedList() {
		return perishedList;
	}

	public void setPerishedList(List<PerishedProduct> perishedList) {
		this.perishedList = perishedList;
	}

	public String getPerishedQuantity() {
		return perishedQuantity;
	}

	public void setPerishedQuantity(String perishedQuantity) {
		this.perishedQuantity = perishedQuantity;
	}

	public Date getPerishedDate() {
		return perishedDate;
	}

	public void setPerishedDate(Date perishedDate) {
		this.perishedDate = perishedDate;
	}

	public List<ProductCategory> getCatbranch() {
		return catbranch;
	}

	public void setCatbranch(List<ProductCategory> catbranch) {
		this.catbranch = catbranch;
	}

	public List<ProductCatDetailsDto> getBranchCatDetails() {
		return branchCatDetails;
	}

	public void setBranchCatDetails(List<ProductCatDetailsDto> branchCatDetails) {
		this.branchCatDetails = branchCatDetails;
	}

	public boolean isRenderproduct() {
		return renderproduct;
	}

	public void setRenderproduct(boolean renderproduct) {
		this.renderproduct = renderproduct;
	}

	public OrderProduct getOrderproduct() {
		return orderproduct;
	}

	public void setOrderproduct(OrderProduct orderproduct) {
		this.orderproduct = orderproduct;
	}

	public PerishedProductImpl getPerishImpl() {
		return perishImpl;
	}

	public void setPerishImpl(PerishedProductImpl perishImpl) {
		this.perishImpl = perishImpl;
	}

	public List<OrderProductDto> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderProductDto> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public boolean isRenderbilling() {
		return renderbilling;
	}

	public void setRenderbilling(boolean renderbilling) {
		this.renderbilling = renderbilling;
	}

	public OrderProductDto getOrderproductDto() {
		return orderproductDto;
	}

	public void setOrderproductDto(OrderProductDto orderproductDto) {
		this.orderproductDto = orderproductDto;
	}

	public OrderProductDto getOrderDto() {
		return orderDto;
	}

	public void setOrderDto(OrderProductDto orderDto) {
		this.orderDto = orderDto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ProductCatDetailsDto> getBranchCatList() {
		return branchCatList;
	}

	public void setBranchCatList(List<ProductCatDetailsDto> branchCatList) {
		this.branchCatList = branchCatList;
	}

	public boolean isRenderOrder() {
		return renderOrder;
	}

	public void setRenderOrder(boolean renderOrder) {
		this.renderOrder = renderOrder;
	}

	public OrderProductImpl getOrderProdImpl() {
		return orderProdImpl;
	}

	public void setOrderProdImpl(OrderProductImpl orderProdImpl) {
		this.orderProdImpl = orderProdImpl;
	}

	public List<Product> getProductfulldetails() {
		return productfulldetails;
	}

	public void setProductfulldetails(List<Product> productfulldetails) {
		this.productfulldetails = productfulldetails;
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

	public List<ProductAssignment> getProductAssignedList() {
		return productAssignedList;
	}

	public void setProductAssignedList(List<ProductAssignment> productAssignedList) {
		this.productAssignedList = productAssignedList;
	}

	public List<ProductAssignment> getProductassdetails() {
		return productassdetails;
	}

	public void setProductassdetails(List<ProductAssignment> productassdetails) {
		this.productassdetails = productassdetails;
	}

	public List<ProductAssignment> getProdAssigDetails() {
		return prodAssigDetails;
	}

	public void setProdAssigDetails(List<ProductAssignment> prodAssigDetails) {
		this.prodAssigDetails = prodAssigDetails;
	}

	public BranchImpl getBranchImpl() {
		return branchImpl;
	}

	public void setBranchImpl(BranchImpl branchImpl) {
		this.branchImpl = branchImpl;
	}

	public ProductAssignment getProductAssignment() {
		return productAssignment;
	}

	public void setProductAssignment(ProductAssignment productAssignment) {
		this.productAssignment = productAssignment;
	}

	public String getOverallDailOrderProcessed() {
		return overallDailOrderProcessed;
	}

	public void setOverallDailOrderProcessed(String overallDailOrderProcessed) {
		this.overallDailOrderProcessed = overallDailOrderProcessed;
	}

	public List<OrderProductDto> getDailyOrder() {
		return dailyOrder;
	}

	public void setDailyOrder(List<OrderProductDto> dailyOrder) {
		this.dailyOrder = dailyOrder;
	}

	public Date getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(Date todayDate) {
		this.todayDate = todayDate;
	}

	public String getOverallDailBranchOrderProcessed() {
		return overallDailBranchOrderProcessed;
	}

	public void setOverallDailBranchOrderProcessed(String overallDailBranchOrderProcessed) {
		this.overallDailBranchOrderProcessed = overallDailBranchOrderProcessed;
	}

	public List<OrderProductDto> getBranchStatistics() {
		return branchStatistics;
	}

	public void setBranchStatistics(List<OrderProductDto> branchStatistics) {
		this.branchStatistics = branchStatistics;
	}

	public boolean isRenderheader() {
		return renderheader;
	}

	public void setRenderheader(boolean renderheader) {
		this.renderheader = renderheader;
	}

	public List<Branch> getBranchDetails() {
		return branchDetails;
	}

	public void setBranchDetails(List<Branch> branchDetails) {
		this.branchDetails = branchDetails;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getQuantit() {
		return quantit;
	}

	public void setQuantit(String quantit) {
		this.quantit = quantit;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isRenderForm() {
		return renderForm;
	}

	public void setRenderForm(boolean renderForm) {
		this.renderForm = renderForm;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

}
