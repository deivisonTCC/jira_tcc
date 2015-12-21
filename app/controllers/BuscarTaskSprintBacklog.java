package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Issuestatus;
import models.Jiraissue;
import models.Priority;
import play.db.DB;
import play.mvc.Controller;

public class BuscarTaskSprintBacklog extends Controller{

	public static List<Issuestatus> buscaTodosOsStatus() throws SQLException{
		
		ResultSet rs = null;
		Connection con = null;
		Statement s = null;
		
		List<Issuestatus> todosOsStatus = new ArrayList<Issuestatus>();
		
		con = DB.getConnection();
		
		String sql = "select id, pname from issuestatus";
		
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			
			while (rs.next()) {
				
				Issuestatus status = new Issuestatus();
				status.setId(rs.getLong("id"));
				status.setPname(rs.getString("pname"));
				todosOsStatus.add(status);
				
			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		} 
		
		return todosOsStatus;
		
	}
	
	public static List<Priority> buscaTodasAsPrioridades() throws SQLException{
		
		ResultSet rs = null;
		Connection con = null;
		Statement s = null;
		
		List<Priority> todasAsPrioridades = new ArrayList<Priority>();
		
		con = DB.getConnection();
		
		String sql = "select id, pname from priority";
		
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			
			while (rs.next()) {
				
				Priority prioridade = new Priority();
				prioridade.setId(rs.getLong("id"));
				prioridade.setPname(rs.getString("pname"));
				todasAsPrioridades.add(prioridade);
				
			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		} finally {
			if (rs != null)
				rs.close(); // usado quando o rs for do tipo ResultSet
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}
		
		return todasAsPrioridades;
		
	}

	public static void filtarTaskSprintBacklog(boolean checkstatus, Integer idStatus, boolean checkprioridade, Integer idPrioridade) throws SQLException{
		
		List<Jiraissue> issuesDoBacklog = new ArrayList<Jiraissue>();
		
		ResultSet rs = null;
		Connection con = null;
		Statement s = null;
		
		con = DB.getConnection();
		
		String sql = " SELECT * FROM jiraissue j WHERE j.id NOT IN (SELECT j.id FROM jiraissue j JOIN customfieldvalue c ON j.id = c.issue) ";
		
		if(checkstatus){
			sql += " AND j.issuestatus = '" +idStatus+ "'" ;
		}
		
		if(checkprioridade){
			sql += " AND j.priority = '" +idPrioridade+ "'";
		}	
		
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			
			while (rs.next()) {
				
				Jiraissue issue = new Jiraissue();
				issue.setId(rs.getLong("id"));
				issue.setEnvironment(rs.getString("environment"));
				issue.setCreator(rs.getString("creator"));
				issue.setIssuestatus(rs.getString("issuestatus"));
				issue.setPriority(rs.getString("priority"));
				issue.setIssuetype(rs.getString("issuetype"));
				issue.setReporter(rs.getString("reporter"));
				issue.setAssignee(rs.getString("assignee"));
				issuesDoBacklog.add(issue);
				
			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
		
		List<Issuestatus> todosOsStatus = new ArrayList<Issuestatus>();
		List<Priority> todasAsPrioridades = new ArrayList<Priority>();
		
		todosOsStatus.addAll(buscaTodosOsStatus());
		todasAsPrioridades.addAll(buscaTodasAsPrioridades());
		
		render(issuesDoBacklog,todosOsStatus,todasAsPrioridades);
	}
	
}
