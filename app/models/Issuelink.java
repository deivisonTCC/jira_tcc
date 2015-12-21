package models;

import java.io.Serializable;

import play.db.jpa.Model;

public class Issuelink extends Model implements Serializable{
	
	public static int BLOCKS = 10000;
	public static int CLONERS = 10001;
	public static int DUPLICATES = 10002;
	public static int RELATES = 10003;
	public static int JIRA_SUBTASK_LINK = 10100;
	
	private Long id;
	private Integer linktype;
	private Integer source;
	private Integer destination;
	private Integer sequence;
	
	private Jiraissue jiraSource;
	private Jiraissue jiraDestination;
	
	private String statusType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getLinktype() {
		return linktype;
	}
	public void setLinktype(Integer linktype) {
		this.linktype = linktype;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Integer getDestination() {
		return destination;
	}
	public void setDestination(Integer destination) {
		this.destination = destination;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public Jiraissue getJiraSource() {
		return jiraSource;
	}
	public void setJiraSource(Jiraissue jiraSource) {
		this.jiraSource = jiraSource;
	}
	public Jiraissue getJiraDestination() {
		return jiraDestination;
	}
	public void setJiraDestination(Jiraissue jiraDestination) {
		this.jiraDestination = jiraDestination;
	}
	public String getStatusType() {
		
		if(linktype == BLOCKS){
			statusType = "BLOCKS";
		}else if(linktype == CLONERS){
			statusType = "CLONERS";
		}else if(linktype == DUPLICATES){
			statusType = "DUPLICATES";
		}else if(linktype == RELATES){
			statusType = "RELATES";
		}else if(linktype == JIRA_SUBTASK_LINK){
			statusType = "JIRA SUBTASK LINK";
		}
		
		return statusType;
	}
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

}
