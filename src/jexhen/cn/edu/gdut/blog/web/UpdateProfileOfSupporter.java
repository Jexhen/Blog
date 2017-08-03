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

import jexhen.cn.edu.gdut.blog.entity.Administrator;
import jexhen.cn.edu.gdut.blog.service.BlogService;

/**
 * @author Jexhen  E-mail: liaozhihang95@163.com
 * @version 创建时间：2017年7月31日  下午2:25:43
 * tags
 */
public class UpdateProfileOfSupporter extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Administrator admin = (Administrator)request.getSession().getAttribute("admin");
		int id = admin.getId();
		String tempPath = this.getServletContext().getRealPath("/img/admin/temp");
		DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024, new File(tempPath));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		boolean isOk = false;
		try {
			List<FileItem> parseRequest = upload.parseRequest(request);
			Map<String,String> jsonMap = new HashMap<String, String>();
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
							//更新数据库头像源
							String avata = "img/admin/" + id +"/profile_pic/logo" + postfix;
							jsonMap.put("avata", avata);
							//将新头像存入服务器
							String regFileName = "logo" + postfix;
							String realPath = this.getServletContext().getRealPath("img/admin/" + id + "/profile_pic");
							File file = new File(realPath + ("/") + regFileName);
							InputStream in = item.getInputStream();
							OutputStream out = new FileOutputStream(file);
							IOUtils.copy(in, out);
							out.close();
							in.close();
						} else {
							request.setAttribute("message", "不支持该种图片格式");
							request.getRequestDispatcher("/supporter/edit_profile.jsp").forward(request, response);;
							return;
						}
					}
				}
			}
			if (jsonMap.size()>0) {
				jsonMap.put("id", ""+id);
				Gson gson = new Gson();
				String json = gson.toJson(jsonMap);
				Administrator newAdmin = gson.fromJson(json, Administrator.class);
				System.out.print(newAdmin.getAvata());
				BlogService service = new BlogService();
				isOk = service.updateAdministrator(newAdmin);
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (isOk) {
			request.setAttribute("message", "修改成功");
			request.getRequestDispatcher("/supporter/edit_profile.jsp").forward(request, response);;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}