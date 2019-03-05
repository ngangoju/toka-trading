package toka.common;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter, DbConstant {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

	public AuthorizationFilter() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();
			if ((reqURI.indexOf("FormExampleV1.xhtml") >= 0 || reqURI.indexOf("503.xhtml") >= 0
					|| reqURI.indexOf("500.xhtml") >= 0 || reqURI.indexOf("404.xhtml") >= 0
					|| reqURI.indexOf("FormExample.xhtml") >= 0 || reqURI.indexOf("default.xhtml") >= 0
					|| reqURI.indexOf(HOMEURL) >= 0) || (ses != null && ses.getAttribute(USERSESSION) != null)
					|| reqURI.indexOf(PUBLICPATH) >= 0 || reqURI.contains(JAVAFACERESOURCE))
				chain.doFilter(request, response);
			else
				resp.sendRedirect(reqt.getContextPath() + HOMEURL);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

	public void destroy() {

	}
}