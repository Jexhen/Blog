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
 * @version ����ʱ�䣺2017��7��30��  ����9:19:54
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
		//���aid�ǿմ�˵��������ȫ�·���,��did��Ϊaidȫ�·�����ӵ����ݿ��article��
		if ("".equals(aid)||aid==null) {
			try {
				article.setAid(request.getParameter("did"));
				isOk = service.addArticle(article);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
		//aid��Ϊ����˵�����ݸ��Ǹ���ԭ�����µĲ���,��article�����aid����Ϊdraft�����aid�����ݿ���и��²���
			try {
				article.setAid(aid);
				isOk = service.updateArticle(article);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//�������ɹ��ˣ�ͬʱ�Ѳݸ���Ķ�Ӧ����ɾ��
		if (isOk) {
			String[] dids = {request.getParameter("did")};
			try {
				service.removeDraft(dids);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//��������ظ��ͻ���
		String json;
		//�������ɹ��������µ�aidҲ���ظ��ͻ��ˣ�����ͻ��˲鿴
		if (isOk) {
			json = "{\"isOk\":"+ isOk +",\"aid\":\""+ article.getAid() +"\"}";
		} else {
		//�����ɹ�aid��Ϊ�մ�
			json = "{\"isOk\":"+ isOk +",\"aid\":"+ "" +"}";
		}
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}