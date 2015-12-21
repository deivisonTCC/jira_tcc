package models;

import play.db.jpa.Model;

public class Project extends Model {
	
	private Long id;
	private String pname;
	private String url;
	private String lead;
	private String description;
	private String pkey;
	private Long pcounter;
	private Long assigneetype;
	private Long  avatar;
	private String  originalkey;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLead() {
		return lead;
	}
	public void setLead(String lead) {
		this.lead = lead;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPkey() {
		return pkey;
	}
	public void setPkey(String pkey) {
		this.pkey = pkey;
	}
	public Long getPcounter() {
		return pcounter;
	}
	public void setPcounter(Long pcounter) {
		this.pcounter = pcounter;
	}
	public Long getAssigneetype() {
		return assigneetype;
	}
	public void setAssigneetype(Long assigneetype) {
		this.assigneetype = assigneetype;
	}
	public Long getAvatar() {
		return avatar;
	}
	public void setAvatar(Long avatar) {
		this.avatar = avatar;
	}
	public String getOriginalkey() {
		return originalkey;
	}
	public void setOriginalkey(String originalkey) {
		this.originalkey = originalkey;
	}

}
