package jexhen.cn.edu.gdut.blog.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jexhen.cn.edu.gdut.blog.entity.Article;
import jexhen.cn.edu.gdut.blog.service.BlogService;

/**
 * @author Jexhen  E-mail: liaozhihang95@163.com
 * @version 创建时间：2017年7月30日  下午9:19:54
 * tags
 */
public class DraftToPublicOfSupporter extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String aid = request.getParameter("aid");
		BlogService service = new BlogService();
		Boolean isOk = false;
		Article article = new Article();
		String dname = URLDecoder.decode(URLDecoder.decode(request.getParameter("dname"), "UTF-8"),"UTF-8");
		article.setAname(dname);
		String dcontent = URLDecoder.decode(URLDecoder.decode(request.getParameter("dcontent"), "UTF-8"),"UTF-8");
		article.setAcontent(dcontent);
		String adate = URLDecoder.decode(URLDecoder.decode(request.getParameter("adate"), "UTF-8"),"UTF-8");
		article.setAdate(adate);
		String dtags = URLDecoder.decode(URLDecoder.decode(request.getParameter("dtags"), "UTF-8"),"UTF-8");
		article.setAtags(dtags);
		String summary = URLDecoder.decode(URLDecoder.decode(request.getParameter("summary"), "UTF-8"),"UTF-8");
		article.setSummary(summary);
		article.setCid(Integer.parseInt(request.getParameter("cid")));
		//如果aid是空串说明文章是全新发表,将did改为aid全新发表添加到数据库的article表
		if ("".equals(aid)||aid==null) {
			try {
				article.setAid(request.getParameter("did"));
				isOk = service.addArticle(article);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
		//aid不为空则说明本草稿是更新原有文章的操作,将article对象的aid设置为draft保存的aid对数据库进行更新操作
			try {
				article.setAid(aid);
				isOk = service.updateArticle(article);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//如果发表成功了，同时把草稿箱的对应文章删除
		if (isOk) {
			String[] dids = {request.getParameter("did")};
			try {
				service.removeDraft(dids);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//将结果返回给客户端
		String json;
		//如果发表成功，将文章的aid也返回给客户端，方便客户端查看
		if (isOk) {
			json = "{\"isOk\":"+ isOk +",\"aid\":\""+ article.getAid() +"\"}";
		} else {
		//发表不成功aid则为空串
			json = "{\"isOk\":"+ isOk +",\"aid\":"+ "" +"}";
		}
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}