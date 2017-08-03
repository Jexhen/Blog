package jexhen.cn.edu.gdut.blog.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jexhen.cn.edu.gdut.blog.entity.PageBean;
import jexhen.cn.edu.gdut.blog.service.BlogService;

/**
 * @author Jexhen E-mail: liaozhhang95@163.com
 * @version 创建时间：2017年7月28日 上午11:58:00 tags
 */
public class GetArticlesByTagNameServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String tname = request.getParameter("tname");
		String currentPageStr = request.getParameter("currentPage");
		int currentPage = 1;
		if (currentPageStr != null) {
			currentPage = Integer.parseInt(currentPageStr);
		}
		int currentCount = 5;
		PageBean page = null;
		BlogService service = new BlogService();
		try {
			page = service.getPageByTagName(tname, currentPage, currentCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (page != null) {
			request.setAttribute("page", page);
		}
		request.getRequestDispatcher("article_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}