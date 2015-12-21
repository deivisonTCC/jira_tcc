package models;

import java.io.Serializable;

import play.db.jpa.Model;

public class Priority extends Model {
	
	private Long id;
	private Long  sequence;
	private String pname;
	private String description;
	private String iconurl;
	private String status_color;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSequence() {
		return sequence;
	}
	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
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
	public String getStatus_color() {
		return status_color;
	}
	public void setStatus_color(String status_color) {
		this.status_color = status_color;
	}

}
