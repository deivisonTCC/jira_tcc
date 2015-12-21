package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Issuestatus;
import models.Jiraissue;
import play.db.DB;
import play.mvc.Controller;

public class Issues extends Controller{
	
	/*public static void filtarTasksResponsavelStatus() throws SQLException{
		List<Jiraissue> tarefas = new ArrayList<Jiraissue>();
		List<Issuestatus> todosOsStatus = new ArrayList<Issuestatus>();
		todosOsStatus.addAll(buscaTodosOsStatus());
		
		render(todosOsStatus,tarefas);
	}*/
	
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
		} finally {
			if (rs != null)
				rs.close(); // usado quando o rs for do tipo ResultSet
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}
		
		return todosOsStatus;
		
	}
	
	public static List<Jiraissue> buscaTodasAsTarefas() throws SQLException{
		
		ResultSet rs = null;
		Connection con = null;
		Statement s = null;
		
		List<Jiraissue> issues = new ArrayList<Jiraissue>();
		
		con = DB.getConnection();
		
		String sql = "select * from jiraissue";
		
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			
			while (rs.next()) {
				
				Jiraissue issue = new Jiraissue();
				issue.setId(rs.getLong("id"));
				issue.setReporter(rs.getString("reporter"));
				issue.setAssignee(rs.getString("assignee"));
				issues.add(issue);
				
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
		
		return issues;
	}

	
	public static void filtarTasksResponsavelStatus(boolean checkResponsavel, String responsavel, boolean checkSituacao, String status) throws SQLException{
		List<Jiraissue> tarefas = new ArrayList<Jiraissue>();
		
		ResultSet rs = null;
		Connection con = null;
		Statement s = null;
		
		con = DB.getConnection();
		
		String sql = "select j.summary as descricao ,p.pname as nome_projeto, i.pname as status, pr.pname as prioridade, iy.pname as tipo, iy.pname as tipo, * "
				+ " from jiraissue j "
				+ " join project p on p.id = j.project "
				+ " join issuestatus i on i.id = j.issuestatus "
				+ " join priority pr on pr.id = j.priority "
				+ " join issuetype iy on iy.id = j.issuetype "
				+ " where 1 = 1 ";
		
		if(checkResponsavel){
			sql += " AND j.assignee = '" +responsavel+ "'" ;
		}
		
		if(checkSituacao){
			sql += " AND j.issuestatus = '" +status+ "'";
		}
		
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			
			while (rs.next()) {
				
				Jiraissue issue = new Jiraissue();
				issue.setSummary(rs.getString("descricao"));
				issue.setNomeDoProjeto(rs.getString("nome_projeto"));
				issue.setSituacaoDoStatus(rs.getString("status"));
				issue.setPrioridade(rs.getString("prioridade"));
				issue.setTipo(rs.getString("tipo"));
				issue.setId(rs.getLong("id"));
				issue.setEnvironment(rs.getString("environment"));
				issue.setCreator(rs.getString("creator"));
				issue.setProject(rs.getInt("project"));
				issue.setIssuestatus(rs.getString("issuestatus"));
				issue.setPriority(rs.getString("priority"));
				issue.setIssuetype(rs.getString("issuetype"));
				issue.setReporter(rs.getString("reporter"));
				issue.setAssignee(rs.getString("assignee"));
				tarefas.add(issue);
				
			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		} 
		
		List<Issuestatus> todosOsStatus = new ArrayList<Issuestatus>();
		todosOsStatus.addAll(buscaTodosOsStatus());
		
		render(todosOsStatus,tarefas);
	}
	
}
