package jexhen.cn.edu.gdut.blog.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jexhen.cn.edu.gdut.blog.entity.Article;
import jexhen.cn.edu.gdut.blog.service.BlogService;

/**
 * @author Jexhen E-mail: liaozhihang95@163.com
 * @version 创建时间：2017年7月28日 上午10:53:30 tags
 */
public class ReadArticleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String aid = request.getParameter("aid");
		BlogService service = new BlogService();
		Article article = null;
		List<String> atags = null;
		try {
			if (!"".equals(aid)&&aid!=null) {
				service.updateArticleView(aid);
				article = service.getArticleByAid(aid);
				atags = service.getTagsByArticle(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (article != null) {
			request.setAttribute("article", article);
		}
		if (atags != null) {
			request.setAttribute("atags", atags);
		}
		request.getRequestDispatcher("article.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}