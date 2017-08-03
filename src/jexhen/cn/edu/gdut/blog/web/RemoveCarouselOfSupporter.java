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
 * @version ����ʱ�䣺2017��8��1��  ����9:14:25
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
			//��ס����Ҫɾ�����ֲ�ͼ,�������ݿ�����Ժ�ɾ����Ӧ�洢�ڷ�������ͼƬ
			Carousel[] carousel = new Carousel[caids.length];
			for (int i=0;i<caids.length;i++) {
				Carousel image = service.getCarouselByCaid(Integer.parseInt(caids[i]));
				carousel[i] = image;
			}
			isOk = service.removeCarousel(caids);
			//���ɾ���ɹ���,�����д洢�ڷ�������ͼƬͬʱɾ��
			if (isOk) {
				for (Carousel c:carousel) {
					String csrc = c.getCsrc();//���ԭ��ͼƬ�洢�����·��
					//ͼƬ��ַ���ǿյĲŽ���ɾ��
					if (!"".equals(csrc)&&csrc!=null) {
						String realPath = this.getServletContext().getRealPath(csrc);//��þ���·��
						File file = new File(realPath);
						file.delete();//����ɾ������
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