package models;

import java.io.Serializable;

import com.google.gson.annotations.Until;

import play.db.jpa.Model;

@SuppressWarnings("unused")
public class Issuetype extends Model implements Serializable{
	
	private static final int BUG = 1;
	private static final int NEW_FEATURE = 2;
	private static final int TASK = 3;
	private static final int IMPROVEMENT = 4;
	private static final int SUB_TASK = 5;
	
	private Long id;
	private String sequence;
	private String pname;
	private String pstyle;
	private String description;
	private String iconurl;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPstyle() {
		return pstyle;
	}
	public void setPstyle(String pstyle) {
		this.pstyle = pstyle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIconurl() {
		return iconurl;
	}
	public void setIconurl(String iconurl) {
		this.iconurl = iconurl;
	}

}
