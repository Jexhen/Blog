package jexhen.cn.edu.gdut.blog.entity;

public class Article {
	private String aid;
	private String aname;
	private String acontent;
	private String adate;
	private int aview;
	private int acomment;
	private String atags;
	private String summary;
	private int cid;

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getAcontent() {
		return acontent;
	}

	public void setAcontent(String acontent) {
		this.acontent = acontent;
	}

	public String getAdate() {
		return adate;
	}

	public void setAdate(String adate) {
		this.adate = adate;
	}

	public int getAview() {
		return aview;
	}

	public void setAview(int aview) {
		this.aview = aview;
	}

	public int getAcomment() {
		return acomment;
	}

	public void setAcomment(int acomment) {
		this.acomment = acomment;
	}

	public String getAtags() {
		return atags;
	}

	public void setAtags(String atags) {
		this.atags = atags;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

}
