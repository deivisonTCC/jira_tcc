package models;

import java.io.Serializable;
import java.util.Date;

import play.db.jpa.Model;

public class Jiraissue extends Model implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String pkey;
	
	private Integer project;
	
	private String reporter;
	
	private String assignee;
	
	private String creator;
	
	private String issuetype;
	
	private String summary;
	
	private String description;
	
	private String environment;
	
	private String priority;
	
	private String resolution;
	
	private String issuestatus;
	
	private Date created;
	
	private Date updated;
	
	private Date duedate;
	
	private Date resolutiondate;
	
	private Integer votes;
	
	private Integer watches;
	
	private Integer timeoriginalestimate;
	
	private Integer timeestimate;
	
	private Integer timespent;
	
	private Integer workflow_id;
	
	private Integer security;
	
	private Integer issuenum;
	
	///////////////////////////////////////////////////////////////
	
	private String nomeDoProjeto;
	private String situacaoDoStatus;
	private String prioridade;
	private String tipo;
	
	///////////////////////////////////////////////////////////////

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPkey() {
		return pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public Integer getProject() {
		return project;
	}

	public void setProject(Integer project) {
		this.project = project;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getIssuetype() {
		return issuetype;
	}

	public void setIssuetype(String issuetype) {
		this.issuetype = issuetype;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getIssuestatus() {
		return issuestatus;
	}

	public void setIssuestatus(String issuestatus) {
		this.issuestatus = issuestatus;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public Date getResolutiondate() {
		return resolutiondate;
	}

	public void setResolutiondate(Date resolutiondate) {
		this.resolutiondate = resolutiondate;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public Integer getWatches() {
		return watches;
	}

	public void setWatches(Integer watches) {
		this.watches = watches;
	}

	public Integer getTimeoriginalestimate() {
		return timeoriginalestimate;
	}

	public void setTimeoriginalestimate(Integer timeoriginalestimate) {
		this.timeoriginalestimate = timeoriginalestimate;
	}

	public Integer getTimeestimate() {
		return timeestimate;
	}

	public void setTimeestimate(Integer timeestimate) {
		this.timeestimate = timeestimate;
	}

	public Integer getTimespent() {
		return timespent;
	}

	public void setTimespent(Integer timespent) {
		this.timespent = timespent;
	}

	public Integer getWorkflow_id() {
		return workflow_id;
	}

	public void setWorkflow_id(Integer workflow_id) {
		this.workflow_id = workflow_id;
	}

	public Integer getSecurity() {
		return security;
	}

	public void setSecurity(Integer security) {
		this.security = security;
	}

	public Integer getIssuenum() {
		return issuenum;
	}

	public void setIssuenum(Integer issuenum) {
		this.issuenum = issuenum;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getNomeDoProjeto() {
		return nomeDoProjeto;
	}

	public void setNomeDoProjeto(String nomeDoProjeto) {
		this.nomeDoProjeto = nomeDoProjeto;
	}

	public String getSituacaoDoStatus() {
		return situacaoDoStatus;
	}

	public void setSituacaoDoStatus(String situacaoDoStatus) {
		this.situacaoDoStatus = situacaoDoStatus;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

}
