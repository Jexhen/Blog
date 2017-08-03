package jexhen.cn.edu.gdut.blog.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jexhen.cn.edu.gdut.blog.entity.Draft;
import jexhen.cn.edu.gdut.blog.service.BlogService;

/**
 * @author Jexhen  E-mail: liaozhihang95@163.com
 * @version 创建时间：2017年7月30日  下午4:53:38
 * tags
 */
public class NewDraftOfSupporter extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String type = request.getParameter("type");
		Map<String, String> jsonMap = new HashMap<String, String>();
		String aid = null;
		//如果是旧的文章存草稿就保存aid，新的文章草稿无需保存aid以示区别
		if (type!=null&&"old".equals(type)) {
			aid = request.getParameter("aid");
		}
		jsonMap.put("aid", aid);
		String did = UUID.randomUUID().toString();
		jsonMap.put("did", did);
		String dname = request.getParameter("dname");
		dname = URLDecoder.decode(URLDecoder.decode(dname, "UTF-8"), "UTF-8");
		jsonMap.put("dname", dname);
		String dcontent = request.getParameter("dcontent");
		dcontent = URLDecoder.decode(URLDecoder.decode(dcontent, "UTF-8"), "UTF-8");
		jsonMap.put("dcontent", dcontent);
		String dtags = request.getParameter("dtags");
		dtags = URLDecoder.decode(URLDecoder.decode(dtags, "UTF-8"), "UTF-8");
		jsonMap.put("dtags", dtags);
		String cid = request.getParameter("cid");
		jsonMap.put("cid", cid);
		Gson gson = new Gson();
		String json = gson.toJson(jsonMap);
		Draft draft = gson.fromJson(json, Draft.class);
		BlogService service = new BlogService();
		boolean isOk = false;
		try {
			isOk = service.addDraft(draft);
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