package jexhen.cn.edu.gdut.blog.web;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jexhen.cn.edu.gdut.blog.entity.Carousel;
import jexhen.cn.edu.gdut.blog.service.BlogService;

/**
 * @author Jexhen  E-mail: liaozhihang95|@163.com
 * @version 创建时间：2017年8月1日  上午9:14:25
 * tags
 */
public class RemoveCarouselOfSupporter extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Map<String, String[]> parameterMap = request.getParameterMap();
		String[] caids = null;
		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			caids = entry.getValue();
		}
		BlogService service = new BlogService();
		boolean isOk = false;
		try {
			//记住所有要删除的轮播图,方便数据库更新以后删除对应存储在服务器的图片
			Carousel[] carousel = new Carousel[caids.length];
			for (int i=0;i<caids.length;i++) {
				Carousel image = service.getCarouselByCaid(Integer.parseInt(caids[i]));
				carousel[i] = image;
			}
			isOk = service.removeCarousel(caids);
			//如果删除成功了,将所有存储在服务器的图片同时删除
			if (isOk) {
				for (Carousel c:carousel) {
					String csrc = c.getCsrc();//获得原有图片存储的相对路径
					//图片地址不是空的才进行删除
					if (!"".equals(csrc)&&csrc!=null) {
						String realPath = this.getServletContext().getRealPath(csrc);//获得绝对路径
						File file = new File(realPath);
						file.delete();//进行删除操作
					}
				}
				response.getWriter().write(Boolean.toString(isOk));
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