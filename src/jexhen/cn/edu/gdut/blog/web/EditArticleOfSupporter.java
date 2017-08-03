package jexhen.cn.edu.gdut.blog.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jexhen.cn.edu.gdut.blog.entity.Article;
import jexhen.cn.edu.gdut.blog.service.BlogService;

/**
 * @author Jexhen  E-mail: liaozhihang95@163.com
 * @version 创建时间：2017年7月29日  上午9:55:18
 * tags
 */
public class EditArticleOfSupporter extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String aid = request.getParameter("aid");
		BlogService service = new BlogService();
		Article article = null;
		if (aid!=null) {
			try {
				article = service.getArticleByAid(aid);
				request.setAttribute("type", "old");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			article = new Article();
			String newAid = UUID.randomUUID().toString();
			article.setAid(newAid);
//			article.setAname("");
//			article.setAdate("");
//			article.setAtags("");
			request.setAttribute("type", "new");
		}
		request.setAttribute("article", article);
		request.getRequestDispatcher("/supporter/edit_article.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}