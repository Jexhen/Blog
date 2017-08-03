 package jexhen.cn.edu.gdut.blog.entity;

public class Tag implements Comparable<Tag> {
	private String tagName;
	private int tcount;

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public int getTcount() {
		return tcount;
	}

	public void setTcount(int tcount) {
		this.tcount = tcount;
	}

	@Override
	public int compareTo(Tag tag) {
		return tag.getTcount()-this.getTcount()==0?this.getTagName().compareTo(tag.getTagName()):tag.getTcount()-this.getTcount();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getTagName() == null) ? 0 : this.getTagName().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (this.getTagName() == null) {
			if (other.getTagName() != null)
				return false;
		} else if (!tagName.equals(other.getTagName()))
			return false;
		return true;
	}

	

}
