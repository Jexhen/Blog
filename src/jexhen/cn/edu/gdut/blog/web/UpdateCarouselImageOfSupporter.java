package jexhen.cn.edu.gdut.blog.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import jexhen.cn.edu.gdut.blog.entity.Carousel;
import jexhen.cn.edu.gdut.blog.service.BlogService;

/**
 * @author Jexhen  E-mail: liaozhihang95@163.com
 * @version ����ʱ�䣺2017��7��31��  ����10:38:26
 * tags
 */
public class UpdateCarouselImageOfSupporter extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024*1024);
		String tempPath = this.getServletContext().getRealPath("img/carousel/temp");
		File repository = new File(tempPath);
		factory.setRepository(repository);
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		Map<String,String> jsonMap = new HashMap<String, String>();
		try {
			List<FileItem> parseRequest = upload.parseRequest(request);
			for (FileItem item : parseRequest) {
				if (item.isFormField()) {
					String fieldName = item.getFieldName();
					String value = item.getString("UTF-8");
					jsonMap.put(fieldName, value);
				} else {
					String filename = item.getName();
					Pattern pattern = Pattern.compile("\\.[a-zA-Z]+");
					Matcher matcher = pattern.matcher(filename);
					String postfix = null;
					if (matcher.find()) {
						postfix = matcher.group();
					}
					if (!"".equals(postfix)&&postfix!=null) {
						postfix = postfix.toLowerCase();
						if (postfix.contains("jpg")||postfix.contains("jpeg")||postfix.contains("png")||postfix.contains("bmp")) {
							//�������ݿ��ֲ�ͼԴ
							String uuid = UUID.randomUUID().toString();
							String csrc = "img/carousel/carousel_" + uuid + postfix;
							jsonMap.put("csrc", csrc);
							//�����ֲ�ͼ���������
							String regFileName = "carousel_" + uuid + postfix;
							String realPath = this.getServletContext().getRealPath("img/carousel");
							File file = new File(realPath + ("/") + regFileName);
							InputStream in = item.getInputStream();
							OutputStream out = new FileOutputStream(file);
							IOUtils.copy(in, out);
							out.close();
							in.close();
						} else {
							
							return;
						}
					}
				}
			}
			if (jsonMap.size()>0) {
				Gson gson = new Gson();
				String json = gson.toJson(jsonMap);
				Carousel carousel = gson.fromJson(json, Carousel.class);
				int caid = carousel.getCaid();
				BlogService service = new BlogService();
				//��ס����֮ǰ��carousel,������³ɹ�֮��ɾ���洢�ڷ�������ԭ��ͼƬ
				Carousel oldCar = service.getCarouselByCaid(carousel.getCaid());
				boolean isOk = false;
				isOk = service.updateCarousel(carousel);
				if (isOk) {
					//������³ɹ�,ȡ��ԭ�е�ͼƬ��ַ����ɾ������
					String csrc = oldCar.getCsrc();
					File oldImg = null;
					//ԭ��ͼƬ��ַ���ǿյĲŽ���ɾ����������Ϊ������ʱ��ͼƬ��ԭ�е�ַ������Ϊ�յ�
					if (!"".equals(csrc)&&csrc!=null) {
						String oldImgPath = request.getServletContext().getRealPath(csrc);
						oldImg = new File(oldImgPath);
					}
					if (oldImg!=null)	
						oldImg.delete();
					response.getWriter().write(Boolean.toString(isOk));
				}
			}
		} catch (FileUploadException e) {
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