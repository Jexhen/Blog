package jexhen.cn.edu.gdut.blog.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jexhen.cn.edu.gdut.blog.entity.Carousel;
import jexhen.cn.edu.gdut.blog.service.BlogService;

/**
 * @author Jexhen  E-mail: liaozhihang95@163.com
 * @version 创建时间：2017年8月1日  下午6:53:38
 * tags
 */
public class SwapCarouselOfSupporter extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String nowCaid = request.getParameter("nowCaid");
		String otherCaid = request.getParameter("otherCaid");
		BlogService service = new BlogService();
		boolean isOk = false;
		try {
			isOk = service.swapCarousel(nowCaid,otherCaid);
			if (isOk) {
				Carousel[] car = {service.getCarouselByCaid(Integer.parseInt(nowCaid)), service.getCarouselByCaid(Integer.parseInt(otherCaid))};
				Gson gson = new Gson();
				String json = gson.toJson(car);
				response.getWriter().write(json);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}