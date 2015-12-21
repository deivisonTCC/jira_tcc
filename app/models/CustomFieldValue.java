package models;

import java.io.Serializable;
import java.util.Date;

import play.db.jpa.Model;

public class CustomFieldValue extends Model implements Serializable {
	
	private Long id;
	private Long issue;
	private Long customfield;
	private String parentkey;
	private String stringvalue;
	private Double numbervalue;
	private String textvalue;
	private Date datevalue;
	private String valuetype;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIssue() {
		return issue;
	}
	public void setIssue(Long issue) {
		this.issue = issue;
	}
	public Long getCustomfield() {
		return customfield;
	}
	public void setCustomfield(Long customfield) {
		this.customfield = customfield;
	}
	public String getParentkey() {
		return parentkey;
	}
	public void setParentkey(String parentkey) {
		this.parentkey = parentkey;
	}
	public String getStringvalue() {
		return stringvalue;
	}
	public void setStringvalue(String stringvalue) {
		this.stringvalue = stringvalue;
	}
	public Double getNumbervalue() {
		return numbervalue;
	}
	public void setNumbervalue(Double numbervalue) {
		this.numbervalue = numbervalue;
	}
	public String getTextvalue() {
		return textvalue;
	}
	public void setTextvalue(String textvalue) {
		this.textvalue = textvalue;
	}
	public Date getDatevalue() {
		return datevalue;
	}
	public void setDatevalue(Date datevalue) {
		this.datevalue = datevalue;
	}
	public String getValuetype() {
		return valuetype;
	}
	public void setValuetype(String valuetype) {
		this.valuetype = valuetype;
	}

}
