package jexhen.cn.edu.gdut.blog.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jexhen.cn.edu.gdut.blog.entity.Administrator;
import jexhen.cn.edu.gdut.blog.service.BlogService;

/**
 * @author Jexhen  E-mail: liaozhihang95@163.com
 * @version ����ʱ�䣺2017��7��30��  ����11:55:13
 * tags
 */
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		BlogService service = new BlogService();
		try {
			Administrator admin = service.login(username, password);
			if (admin!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("admin", admin);
				response.sendRedirect(request.getContextPath()+"/admin/getArticleToSupporter");
			} else {
				request.setAttribute("message", "�û��������������");
				request.getRequestDispatcher("/supporter/login.jsp").forward(request, response);;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}