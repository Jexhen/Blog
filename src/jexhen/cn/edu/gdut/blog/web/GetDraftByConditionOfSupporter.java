package jexhen.cn.edu.gdut.blog.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jexhen.cn.edu.gdut.blog.entity.Article;
import jexhen.cn.edu.gdut.blog.entity.Draft;
import jexhen.cn.edu.gdut.blog.service.BlogService;

/**
 * @author Jexhen  E-mail: liaozhihang95@163.com
 * @version 创建时间：2017年7月30日  下午8:31:16
 * tags
 */
public class GetDraftByConditionOfSupporter extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String cid = request.getParameter("cid");
		String keyWord = request.getParameter("keyWord");
		BlogService service = new BlogService();
		List<Draft> drafts = null;
		try {
			drafts = service.getDraftsByCondition(cid, keyWord);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		if (drafts!=null) {
			String json = gson.toJson(drafts);
			response.getWriter().write(json);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}