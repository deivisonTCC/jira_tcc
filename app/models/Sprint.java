package models;

import java.io.Serializable;

import play.db.jpa.Model;

public class Sprint extends Model implements Serializable{
	
	private boolean closed;
	private Long completeDate;
	private Long endDate;
	private Long id;
	private String name;
	private Long rapidViewId;
	private Long sequence;
	private boolean started;
	private Long startDate;
	
	
	public boolean isClosed() {
		return closed;
	}
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	public Long getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(Long completeDate) {
		this.completeDate = completeDate;
	}
	public Long getEndDate() {
		return endDate;
	}
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getRapidViewId() {
		return rapidViewId;
	}
	public void setRapidViewId(Long rapidViewId) {
		this.rapidViewId = rapidViewId;
	}
	public Long getSequence() {
		return sequence;
	}
	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}
	public boolean isStarted() {
		return started;
	}
	public void setStarted(boolean started) {
		this.started = started;
	}
	public Long getStartDate() {
		return startDate;
	}
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}
	
	

}
