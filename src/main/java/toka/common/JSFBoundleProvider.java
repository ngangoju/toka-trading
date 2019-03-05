package toka.common;

import java.io.Serializable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * @author Ngabo
 * 
 */
@ManagedBean
@RequestScoped
public class JSFBoundleProvider implements Serializable {

	private static final long serialVersionUID = 1L;

	private transient ResourceBundle bundle;
	private transient ResourceBundle commonModuleErrorBundle;

	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

	@Produces
	@MessageBundle
	public ResourceBundle getBundle() {
		if (bundle == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			bundle = context.getApplication().getResourceBundle(context, "msg");
		}
		return bundle;
	}

	public String getValue(String key) {
		String result = null;
		try {
			result = verfiyBundleOne(key);
			if (null != result) {
				return result;
			}

		} catch (MissingResourceException ex) {
			LOGGER.info(ex.getMessage() + ex);
			result = key + " not found";
		}
		return result;
	}

	@Produces
	@MessageBundle
	public ResourceBundle getCommonModuleErrorBundle() {
		if (commonModuleErrorBundle == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			commonModuleErrorBundle = context.getApplication().getResourceBundle(context, "err");
		}
		return commonModuleErrorBundle;
	}

	public String verfiyBundleOne(String key) {
		String value = null;
		if (null != getBundle() && getBundle().containsKey(key)) {
			value = getBundle().getString(key);
		} else if (null != getCommonModuleErrorBundle() && getCommonModuleErrorBundle().containsKey(key)) {
			value = getCommonModuleErrorBundle().getString(key);
		}
		return value;
	}
}
