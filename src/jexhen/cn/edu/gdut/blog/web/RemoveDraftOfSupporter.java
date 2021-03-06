package jexhen.cn.edu.gdut.blog.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jexhen.cn.edu.gdut.blog.service.BlogService;

/**
 * @author Jexhen  E-mail: liaozhihang95@163.com
 * @version 创建时间：2017年7月30日  下午8:24:53
 * tags
 */
public class RemoveDraftOfSupporter extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Map<String, String[]> parameterMap = request.getParameterMap();
		String[] dids = null;
		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			dids = entry.getValue();
		}		
		BlogService service = new BlogService();
		boolean isOk = false;
		try {
			isOk = service.removeDraft(dids);
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