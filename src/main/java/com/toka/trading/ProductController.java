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
import toka.dao.impl.ProductImpl;
import toka.domain.Contact;
import toka.domain.Product;
import toka.domain.Users;
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
	private boolean rendered, renderProductForm;
	private List<Product> productList = new ArrayList<Product>();
	ProductImpl productImpl = new ProductImpl();
	Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
	JSFBoundleProvider provider = new JSFBoundleProvider();

	private Date manufDate, expDate;

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

			showAvailProduct(productList);

		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}

	}

	public void showAvailProduct(List<Product> list) {
		if (list.size() > 0) {
			this.rendered = false;
		} else {
			this.renderProductForm = true;
		}
	}

	public String saveNewProduct() {
		try {

			try {

				Product pdt = new Product();
				pdt=product;
				if (null != pdt.getProductName()&& defaultCount!=pdt.getQuantity()
						&&defaultCount!=pdt.getPurchaseUnitPrice()&&null!=manufDate&&null!=expDate&&null!=pdt.getProductCategory()) {	
				}else {
					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("error.server.side.nullvalue"));
					LOGGER.info(CLASSNAME + "sivaserside validation :: phone number already  recorded in the system! ");
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
			String exp=smf.format(expDate);
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
				product.setManufactDate( new java.sql.Date(manufDate.getTime()));
				product.setExpireDate(new java.sql.Date(expDate.getTime()));
				product.setExpireDate(expDate);
				productImpl.saveProduct(product);
				JSFMessagers.resetMessages();
				setValid(true); 
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.product"));
				
			}
			clearContactFuileds();
			return null;

		}catch(HibernateException | ParseException e)
		{
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

}
