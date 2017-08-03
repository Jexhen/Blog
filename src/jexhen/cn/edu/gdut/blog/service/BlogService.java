package jexhen.cn.edu.gdut.blog.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import jexhen.cn.edu.gdut.blog.dao.BlogDao;
import jexhen.cn.edu.gdut.blog.entity.Administrator;
import jexhen.cn.edu.gdut.blog.entity.Article;
import jexhen.cn.edu.gdut.blog.entity.Carousel;
import jexhen.cn.edu.gdut.blog.entity.Category;
import jexhen.cn.edu.gdut.blog.entity.Draft;
import jexhen.cn.edu.gdut.blog.entity.PageBean;
import jexhen.cn.edu.gdut.blog.entity.Tag;

public class BlogService {

	public List<Category> getCategory() throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.getCategory();
	}

	public List<Article> getAllArticle() throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.getAllArticle();
	}

	public PageBean getPage(int currentPage, int currentCount) throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.getPage(currentPage, currentCount);
	}

	public List<Article> getArticlesByCid(String cid) throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.getArticlesByCid(cid);
	}

	public PageBean getPageByCid(String cid, int currentPage, int currentCount) throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.getPageByCid(cid, currentPage, currentCount);
	}

	public List<Article> getPopularArticles() throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.getPopularArticles();
	}

	public Set<Tag> getTags() throws SQLException {
		BlogDao dao = new BlogDao();
		List<Article> articles = getAllArticle();
		List<String> tagNames = new ArrayList<String>();
		Set<Tag> tags = new TreeSet<Tag>();
		for (Article a : articles) {
			String[] tagArr = a.getAtags().trim().split("\\s+");
			tagNames.addAll(Arrays.asList(tagArr));
		}
		for (String tagName : tagNames) {
			Tag tag = new Tag();
			tag.setTagName(tagName);
			int tcount = dao.getCountOfTag(tagName);
			tag.setTcount(tcount);
			tags.add(tag);
		}
		return tags;
	}

	public List<Article> getRecentArticles() throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.getRecentArticles();
	}

	public Article getArticleByAid(String aid) throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.getArticleByAid(aid);
	}

	public List<String> getTagsByArticle(Article article) {
		if (article.getAtags() != null) {
			String[] tagArr = article.getAtags().trim().split("\\s+");
			return Arrays.asList(tagArr);
		}
		return null;
	}

	public PageBean getPageByTagName(String tname, int currentPage, int currentCount) throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.getPageByTagName(tname, currentPage, currentCount);
	}

	public List<Article> getPopularArticlesByCid(String cid) throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.getPopularArticlesByCid(cid);
	}

	public Set<Tag> getTagsByCid(String cid) throws SQLException {
		BlogDao dao = new BlogDao();
		List<Article> articles = getArticlesByCid(cid);
		List<String> tagNames = new ArrayList<String>();
		Set<Tag> tags = new TreeSet<Tag>();
		for (Article a : articles) {
			String[] tagArr = a.getAtags().trim().split("\\s+");
			tagNames.addAll(Arrays.asList(tagArr));
		}
		for (String tagName : tagNames) {
			Tag tag = new Tag();
			tag.setTagName(tagName);
			int tcount = dao.getCountOfTag(tagName);
			tag.setTcount(tcount);
			tags.add(tag);
		}
		return tags;
	}

	public List<Article> getRecentArticlesByCid(String cid) throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.getRecentArticlesByCid(cid);
	}

	public boolean removeArticle(String[] aids) throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.removeArticle(aids);
	}

	public boolean addArticle(Article article) throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.addArticle(article);
	}

	public boolean updateArticle(Article article) throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.updateArticle(article);
	}

	public List<Article> getArticlesByCondition(String cid, String keyWord) throws SQLException {
		BlogDao dao = new BlogDao();
		List<Article> articles = null;
		if ("".equals(cid)||cid==null) {
			//cid空keyWord不空
			if (!"".equals(keyWord)&&keyWord!=null) {
				articles = dao.getArticlesByKeyWord(keyWord);
			} else {
			//两者皆空
				articles = dao.getAllArticle();
			}
		} else if ("".equals(keyWord)||keyWord==null) {
			//cid不空但是keyword空
			articles = dao.getArticlesByCid(cid);
		} else {
			//cid和keywor都不空
			articles = dao.getArticlesByCondition(cid, keyWord);
		} 
		return articles;
	}

	public boolean addDraft(Draft draft) throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.addDraft(draft);
	}

	public List<Draft> getAlldraft() throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.getAlldraft();
	}

	public boolean removeDraft(String[] dids) throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.removeDraft(dids);
	}

	public List<Draft> getDraftsByCondition(String cid, String keyWord) throws SQLException {
		BlogDao dao = new BlogDao();
		List<Draft> drafts = null;
		if ("".equals(cid)||cid==null) {
			//cid空但是keyword不空
			if (!"".equals(keyWord)&&keyWord!=null) {
				drafts = dao.getDraftsByKeyWord(keyWord);
			} else {
			//两者皆空默认搜索全部
				drafts = dao.getAlldraft();
			}
		} else if ("".equals(keyWord)||keyWord==null) {
			//cid不空但是keyword空
			drafts = dao.getDraftsByCid(cid);
		} else {
			//两者皆不空
			drafts = dao.getDraftsByCondition(cid, keyWord);
		} 
		return drafts;
	}

	public Draft getDraftByDid(String did) throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.getDraftByDid(did);
	}

	public boolean updateDraft(Draft draft) throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.updateDraft(draft);
	}

	public Administrator login(String username, String password) throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.login(username, password);
	}

	public boolean updateAdministrator(Administrator newAdmin) throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.updateAdministrator(newAdmin);
	}
	
	public Carousel getCarouselByCaid(int caid) throws SQLException {
		BlogDao dao = new BlogDao();
		Carousel carousel = dao.getCarouselByCaid(caid);
		return carousel;
	}
	
	public boolean updateCarousel(Carousel carousel) throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.updateCarousel(carousel);
	}

	public List<Carousel> getAllCarousel() throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.getAllCarousel();
	}

	public boolean removeCarousel(String[] caids) throws SQLException {
		BlogDao dao = new BlogDao();
		return dao.removeCarousel(caids);
	}

	public boolean addCarousel() throws SQLException {
		BlogDao dao = new BlogDao();
		int count = dao.getCarouselCount();
		if (count<5)
			return dao.addCarousel();
		return false;
	}

	public boolean swapCarousel(String nowCaid, String otherCaid) throws NumberFormatException, SQLException {
		BlogDao dao = new BlogDao();
		Carousel nowCarousel = dao.getCarouselByCaid(Integer.parseInt(nowCaid));
		Carousel otherCarousel = dao.getCarouselByCaid(Integer.parseInt(otherCaid));
		nowCarousel.setCaid(Integer.parseInt(otherCaid));
		otherCarousel.setCaid(Integer.parseInt(nowCaid));			
		return dao.swapCarousel(nowCarousel, otherCarousel);
	}

	public void updateArticleView(String aid) throws SQLException {
		BlogDao dao = new BlogDao();
		dao.updateArticleView(aid);
	}

}
