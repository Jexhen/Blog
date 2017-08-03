package jexhen.cn.edu.gdut.blog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jexhen.cn.edu.gdut.blog.entity.Administrator;

public class AdminFilter implements Filter{
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		Administrator admin = (Administrator)request.getSession().getAttribute("admin");
		if (request.getServletPath().contains("login")||request.getServletPath().contains("admin")) {
			chain.doFilter(request, response);
			return;
		}
		if (admin==null) {
			response.sendRedirect(request.getContextPath() + "/admin");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		
	}


	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
