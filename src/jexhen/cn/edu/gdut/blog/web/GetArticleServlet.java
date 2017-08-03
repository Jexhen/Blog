package jexhen.cn.edu.gdut.blog.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jexhen.cn.edu.gdut.blog.service.BlogService;
import jexhen.cn.edu.gdut.blog.entity.*;

/**
 * @author Jexhen E-mail: liaozhihang95@163.com
 * @version 创建时间：2017年7月27日 下午5:54:00 tags
 */
public class GetArticleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String cid = (String) request.getAttribute("cid");
		String currentPageStr = request.getParameter("currentPage");
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}
		int currentCount = 5;
		BlogService service = new BlogService();
		PageBean page = null;
		try {
			if (cid != null && !cid.equals("index")) {
				page = service.getPageByCid(cid, currentPage, currentCount);
			} else {
				page = service.getPage(currentPage, currentCount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (page != null) {
			request.setAttribute("page", page);
		}
		if (cid.equals("index"))
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		else
			request.getRequestDispatcher("/article_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}