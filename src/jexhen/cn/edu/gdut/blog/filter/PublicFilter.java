package jexhen.cn.edu.gdut.blog.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import jexhen.cn.edu.gdut.blog.service.BlogService;
import jexhen.cn.edu.gdut.blog.entity.*;

public class PublicFilter implements Filter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		BlogService service = new BlogService();
		List<Category> category = null;
		List<Article> popular = null;
		Set<Tag> tags = null;
		String cid = req.getParameter("cid");
		try {
			// 获取目录
			category = service.getCategory();
			if (cid!=null && !cid.equals("index")) {
				popular = service.getPopularArticlesByCid(cid);
				tags = service.getTagsByCid(cid);
			}
			else {
				popular = service.getPopularArticles();
				tags = service.getTags();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (category != null && popular != null && tags != null) {
			request.setAttribute("category", category);
			request.setAttribute("popular", popular);
			request.setAttribute("tags", tags);
			if (cid!=null)
				request.setAttribute("cid", cid);
			else 
				request.setAttribute("cid", "index");
		}
		// 将request携带的参数字符进行编码
		MyRequest myReq = new MyRequest(request);
		chain.doFilter(myReq, response);
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}

// 装饰者模式
class MyRequest extends HttpServletRequestWrapper {
	HttpServletRequest request;

	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	public String getParameter(String key) {
		String value = request.getParameter(key);
		String decValue = null;
		try {
			if (value != null)
				decValue = new String(value.getBytes("ISO8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decValue;
	}

	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Map<String, String[]> decMap = new HashMap<String, String[]>();
		for (Map.Entry<String, String[]> e : parameterMap.entrySet()) {
			String key = e.getKey();
			String[] value = e.getValue();
			String[] decValue = new String[value.length];
			for (int i = 0; i < value.length; i++) {
				String s = value[i];
				String decs = null;
				try {
					decs = new String(s.getBytes("ISO8859-1"), "utf-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				if (decs != null) {
					decValue[i] = decs;
				}
			}
			decMap.put(key, decValue);
		}
		return decMap;
	}
}
