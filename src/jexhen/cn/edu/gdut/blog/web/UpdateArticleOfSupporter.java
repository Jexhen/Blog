package jexhen.cn.edu.gdut.blog.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jexhen.cn.edu.gdut.blog.entity.Article;
import jexhen.cn.edu.gdut.blog.service.BlogService;

/**
 * @author Jexhen  E-mail: liaozhihang95@163.com
 * @version 创建时间：2017年7月29日  下午6:05:03
 * tags
 */
public class UpdateArticleOfSupporter extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Map<String, String[]> parameterMap = request.getParameterMap();
		Map<String, String> jsonMap = new HashMap<String, String>();
		for (Map.Entry<String, String[]> entry:parameterMap.entrySet()) {
			String key = entry.getKey();
			for (String s : entry.getValue()) {
				String value = URLDecoder.decode(s, "UTF-8");
				value = URLDecoder.decode(value, "UTF-8");
				jsonMap.put(key, value);
			}
		}
		Gson gson = new Gson();
		String json = gson.toJson(jsonMap);
		Article article = gson.fromJson(json, Article.class);
		BlogService service = new BlogService();
		boolean isOk = false;
		try {
			isOk = service.updateArticle(article);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.getWriter().write(Boolean.toString(isOk));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}