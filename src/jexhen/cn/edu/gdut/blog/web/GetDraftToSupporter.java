package jexhen.cn.edu.gdut.blog.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jexhen.cn.edu.gdut.blog.entity.Article;
import jexhen.cn.edu.gdut.blog.entity.Draft;
import jexhen.cn.edu.gdut.blog.service.BlogService;

/**
 * @author Jexhen  E-mail: liaozhihang95@163.com
 * @version 创建时间：2017年7月30日  下午5:58:08
 * tags
 */
public class GetDraftToSupporter extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		BlogService service = new BlogService();
		List<Draft> drafts = null;
		try {
			drafts = service.getAlldraft();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (drafts!=null) {
			request.setAttribute("drafts", drafts);
		}
		request.getRequestDispatcher("/supporter/draft_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}