package jexhen.cn.edu.gdut.blog.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import jexhen.cn.edu.gdut.blog.entity.Administrator;
import jexhen.cn.edu.gdut.blog.entity.Article;
import jexhen.cn.edu.gdut.blog.entity.Carousel;
import jexhen.cn.edu.gdut.blog.entity.Category;
import jexhen.cn.edu.gdut.blog.entity.Draft;
import jexhen.cn.edu.gdut.blog.entity.PageBean;
import jexhen.cn.edu.gdut.blog.utils.C3P0Utils;

public class BlogDao {

	public List<Category> getCategory() throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select c.cid cid,c.cname cname,count(aid) count from category c left join article a on c.cid=a.cid group by c.cid;";
		List<Category> category = runner.query(sql, new BeanListHandler<Category>(Category.class));
		return category;
	}

	public List<Article> getAllArticle() throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from article order by adate desc";
		List<Article> articles = runner.query(sql, new BeanListHandler<Article>(Article.class));
		return articles;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageBean getPage(int currentPage, int currentCount) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		PageBean page = new PageBean();
		page.setCurrentPage(currentPage);
		page.setCurrentCount(currentCount);
		String sql = "select count(*) from article;";
		Long totalCnt = (Long) runner.query(sql, new ScalarHandler());
		int totalCount = totalCnt.intValue();
		page.setTotalCount(totalCount);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
		page.setTotalPage(totalPage);
		sql = "select * from article order by adate desc limit ?,?;";
		List<Article> articles = runner.query(sql, new BeanListHandler<Article>(Article.class),
				(currentPage - 1) * currentCount, currentCount);
		page.setArticles(articles);
		return page;
	}

	public List<Article> getArticlesByCid(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from article where cid=?";
		List<Article> articles = runner.query(sql, new BeanListHandler<Article>(Article.class), cid);
		return articles;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageBean getPageByCid(String cid, int currentPage, int currentCount) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		PageBean page = new PageBean();
		page.setCurrentPage(currentPage);
		page.setCurrentCount(currentCount);
		String sql = "select count(*) from article where cid=?;";
		Long totalCnt = (Long) runner.query(sql, new ScalarHandler(), cid);
		int totalCount = totalCnt.intValue();
		page.setTotalCount(totalCount);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
		page.setTotalPage(totalPage);
		sql = "select * from article where cid=? order by adate desc limit ?,?;";
		List<Article> articles = runner.query(sql, new BeanListHandler<Article>(Article.class), cid,
				(currentPage - 1) * currentCount, currentCount);
		page.setArticles(articles);
		return page;
	}

	public List<Article> getPopularArticles() throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from article order by aview desc limit 0, 5";
		List<Article> articles = runner.query(sql, new BeanListHandler<Article>(Article.class));
		return articles;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int getCountOfTag(String tagName) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select count(*) from article where atags like ?";
		String param = "%" + tagName + "%";
		Long tcount = (Long) runner.query(sql, new ScalarHandler(), param);
		return tcount.intValue();
	}

	public List<Article> getRecentArticles() throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from article order by adate desc limit 0, 5";
		List<Article> articles = runner.query(sql, new BeanListHandler<Article>(Article.class));
		return articles;
	}

	public Article getArticleByAid(String aid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from article where aid=?";
		Article article = runner.query(sql, new BeanHandler<Article>(Article.class), aid);
		return article;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageBean getPageByTagName(String tname, int currentPage, int currentCount) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		PageBean page = new PageBean();
		page.setCurrentPage(currentPage);
		page.setCurrentCount(currentCount);
		String sql = "select count(*) from article where atags like ?;";
		Long totalCnt = (Long) runner.query(sql, new ScalarHandler(), "%" + tname + "%");
		int totalCount = totalCnt.intValue();
		page.setTotalCount(totalCount);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
		page.setTotalPage(totalPage);
		sql = "select * from article where atags like ? order by adate desc limit ?,?;";
		Object[] params = { "%" + tname + "%", (currentPage - 1) * currentCount, currentCount };
		List<Article> articles = runner.query(sql, new BeanListHandler<Article>(Article.class), params);
		page.setArticles(articles);
		return page;
	}

	public List<Article> getPopularArticlesByCid(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from article where cid=? order by aview desc limit 0, 5";
		List<Article> articles = runner.query(sql, new BeanListHandler<Article>(Article.class), cid);
		return articles;
	}

	public List<Article> getRecentArticlesByCid(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from article where cid=? order by adate desc limit 0, 5";
		List<Article> articles = runner.query(sql, new BeanListHandler<Article>(Article.class), cid);
		return articles;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean removeArticle(String[] aids) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		if (aids != null) {
			StringBuffer sb = new StringBuffer("delete from article where aid=?");
			for (int i = 1; i < aids.length; i++) {
				sb.append(" or aid=?");
			}
			String sql = sb.toString();
			int count = runner.update(sql, aids);
			if (count == aids.length)
				return true;
		}
		return false;
	}

	public boolean addArticle(Article article) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "insert into article values(?,?,?,?,?,?,?,?,?);";
		Object[] params = { article.getAid(), article.getAname(), article.getAcontent(), article.getAdate(),
				article.getAview(), article.getAcomment(), article.getAtags(), article.getSummary(), article.getCid() };
		int count = runner.update(sql, params);
		if (count > 0)
			return true;
		return false;
	}

	public boolean updateArticle(Article article) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "update article set aname=?,acontent=?,atags=?,summary=?,cid=? where aid=?";
		Object[] params = { article.getAname(), article.getAcontent(), article.getAtags(), article.getSummary(), article.getCid(),
				article.getAid() };
		int count = runner.update(sql, params);
		if (count > 0)
			return true;
		return false;
	}

	public List<Article> getArticlesByKeyWord(String keyWord) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from article where aname like ?";
		String param = "%" + keyWord + "%";
		List<Article> articles = runner.query(sql, new BeanListHandler<Article>(Article.class), param);
		return articles;
	}

	public List<Article> getArticlesByCondition(String cid, String keyWord) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from article where cid=? and aname like ?";
		Object[] params = { cid, "%" + keyWord + "%" };
		List<Article> articles = runner.query(sql, new BeanListHandler<Article>(Article.class), params);
		return articles;
	}

	public boolean addDraft(Draft draft) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "insert into articledraft values(?,?,?,?,?,?)";
		Object[] params = { draft.getDid(), draft.getDname(), draft.getDcontent(), draft.getDtags(), draft.getCid(),
				draft.getAid() };
		int count = runner.update(sql, params);
		if (count > 0)
			return true;
		return false;
	}

	public List<Draft> getAlldraft() throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from articledraft order by did";
		List<Draft> drafts = runner.query(sql, new BeanListHandler<Draft>(Draft.class));
		return drafts;
	}

	public boolean removeDraft(String[] dids) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		if (dids != null) {
			StringBuffer sb = new StringBuffer("delete from articledraft where did=?");
			for (int i = 1; i < dids.length; i++) {
				sb.append(" or did=?");
			}
			String sql = sb.toString();
			int count = runner.update(sql, dids);
			if (count == dids.length)
				return true;
		}
		return false;
	}

	public List<Draft> getDraftsByKeyWord(String keyWord) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from articledraft where dname like ?";
		String param = "%" + keyWord + "%";
		List<Draft> drafts = runner.query(sql, new BeanListHandler<Draft>(Draft.class), param);
		return drafts;
	}

	public List<Draft> getDraftsByCid(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from articledraft where cid=?";
		List<Draft> drafts = runner.query(sql, new BeanListHandler<Draft>(Draft.class), cid);
		return drafts;
	}

	public List<Draft> getDraftsByCondition(String cid, String keyWord) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from articledraft where cid=? and dname like ?";
		Object[] params = { cid, "%" + keyWord + "%" };
		List<Draft> drafts = runner.query(sql, new BeanListHandler<Draft>(Draft.class), params);
		return drafts;
	}

	public Draft getDraftByDid(String did) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from articledraft where did=?";
		return runner.query(sql, new BeanHandler<Draft>(Draft.class), did);
	}

	public boolean updateDraft(Draft draft) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "update articledraft set dname=?,dcontent=?,dtags=?,cid=? where did=?";
		Object[] params = { draft.getDname(), draft.getDcontent(), draft.getDtags(), draft.getCid(), draft.getDid() };
		int count = runner.update(sql, params);
		if (count > 0)
			return true;
		return false;
	}

	public Administrator login(String username, String password) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from administrator where username=? and password=?";
		Administrator admin = runner.query(sql, new BeanHandler<Administrator>(Administrator.class), username,
				password);
		return admin;
	}

	public boolean updateAdministrator(Administrator newAdmin) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		StringBuffer sb = new StringBuffer("update administrator set");
		List<Object> param = new ArrayList<Object>();
		if (!"".equals(newAdmin.getUsername())&&newAdmin.getUsername()!=null) {
			sb.append(" username=?,");
			param.add(newAdmin.getUsername());
		}
		if (!"".equals(newAdmin.getRealname())&&newAdmin.getUsername()!=null) {
			sb.append(" realname=?,");
			param.add(newAdmin.getRealname());
		}
		if (!"".equals(newAdmin.getGender())&&newAdmin.getGender()!=null) {
			sb.append(" gender=?,");
			param.add(newAdmin.getGender());
		}
		if (!"".equals(newAdmin.getBirthday())&&newAdmin.getGender()!=null) {
			sb.append(" birthday=?,");
			param.add(newAdmin.getBirthday());
		}
		if (!"".equals(newAdmin.getEmail())&&newAdmin.getEmail()!=null) {
			sb.append(" email=?,");
			param.add(newAdmin.getEmail());
		}
		if (!"".equals(newAdmin.getAvata())&&newAdmin.getAvata()!=null) {
			sb.append(" avata=?,");
			param.add(newAdmin.getAvata());
		}
		sb.append("id=? where id=?");
		param.add(newAdmin.getId());
		param.add(newAdmin.getId());
		Object[] params = param.toArray();
		String sql = sb.toString();
		int count = runner.update(sql, params);
		if (count == 1)
			return true;
		return false;
	}

	public Carousel getCarouselByCaid(int caid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from carousel where caid=?";
		Carousel carousel = runner.query(sql, new BeanHandler<Carousel>(Carousel.class), caid);
		return carousel;
	}

	public boolean updateCarousel(Carousel carousel) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "update carousel set csrc=?,cdesc=? where caid=?";
		Object[] params = { carousel.getCsrc(), carousel.getCdesc(), carousel.getCaid() };
		int update = runner.update(sql, params);
		if (update > 0)
			return true;
		return false;
	}

	public List<Carousel> getAllCarousel() throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from carousel order by caid asc";
		List<Carousel> carousel = runner.query(sql, new BeanListHandler<Carousel>(Carousel.class));
		return carousel;
	}

	public boolean removeCarousel(String[] caids) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		if (caids != null) {
			StringBuffer sb = new StringBuffer("delete from carousel where caid=?");
			for (int i = 1; i < caids.length; i++) {
				sb.append(" or caid=?");
			}
			String sql = sb.toString();
			int count = runner.update(sql, caids);
			if (count == caids.length)
				return true;
		}
		return false;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int getCarouselCount() throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select count(*) from carousel";
		Long count = (Long) runner.query(sql, new ScalarHandler());
		return count.intValue();
	}

	public boolean addCarousel() throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "insert into carousel values(null,null,null)";
		int count = runner.update(sql);
		if (count>0)
			return true;
		return false;
	}

	public boolean swapCarousel(Carousel nowCarousel, Carousel otherCarousel) {
		Connection conn = C3P0Utils.getConnection();
		QueryRunner runner = new QueryRunner();
		try {
			conn.setAutoCommit(false);
			String sql = "update carousel set csrc=?,cdesc=? where caid=?";
			Object[] params = {nowCarousel.getCsrc(), nowCarousel.getCdesc(), nowCarousel.getCaid()};
			runner.update(conn, sql, params);
			params[0] = otherCarousel.getCsrc();
			params[1] = otherCarousel.getCdesc();
			params[2] = otherCarousel.getCaid();
			runner.update(conn, sql, params);
			conn.commit();
			return true;
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} 
		return false;
	}

	public void updateArticleView(String aid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "update article set aview=aview+1 where aid=?";
		runner.update(sql, aid);
	}

}
