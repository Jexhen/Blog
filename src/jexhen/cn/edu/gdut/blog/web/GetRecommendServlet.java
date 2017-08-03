package jexhen.cn.edu.gdut.blog.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jexhen.cn.edu.gdut.blog.service.BlogService;
import jexhen.cn.edu.gdut.blog.entity.*;

/**
 * @author Jexhen E-mail: liaozhihang95@163.com
 * @version 创建时间：2017年7月28日 上午8:38:31 tags
 */
public class GetRecommendServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String type = request.getParameter("type");
		String cid = request.getParameter("cid");
		BlogService service = new BlogService();
		List<Article> recommends = null;
		try {
			if (cid.equals("index")) {
				if ("popular".equals(type))
					recommends = service.getPopularArticles();
				else
					recommends = service.getRecentArticles();
			} else {
				if ("popular".equals(type))
					recommends = service.getPopularArticlesByCid(cid);
				else
					recommends = service.getRecentArticlesByCid(cid);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = gson.toJson(recommends);
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}