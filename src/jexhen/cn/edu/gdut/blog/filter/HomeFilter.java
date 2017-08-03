package jexhen.cn.edu.gdut.blog.filter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jexhen.cn.edu.gdut.blog.entity.PageBean;
import jexhen.cn.edu.gdut.blog.service.BlogService;
import jexhen.cn.edu.gdut.blog.entity.*;

public class HomeFilter implements Filter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		BlogService service = new BlogService();
		PageBean page = null;
		List<Carousel> carousel = null;
		int currentPage = 1;
		int currentCount = 5;
		try {
			page = service.getPage(currentPage, currentCount);
			carousel = service.getAllCarousel();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (page != null) {
			request.setAttribute("page", page);
		}
		if (carousel.size()!=0) {
			request.setAttribute("carousel", carousel);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
