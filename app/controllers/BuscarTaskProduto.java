package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Jiraissue;
import models.Project;
import models.Sprint;
import play.db.DB;
import play.mvc.Controller;

public class BuscarTaskProduto extends Controller{
	
	
	private static List<Project> buscaTodosOsProdutos() throws SQLException{
		
		ResultSet rs = null;
		Connection con = null;
		Statement s = null;
		
		List<Project> projects = new ArrayList<Project>();
		
		con = DB.getConnection();
		
		String sql = "select * from project ";
		
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			
			while (rs.next()) {
				
				Project project = new Project();
				project.setId(rs.getLong("ID"));
				project.setPname(rs.getString("PNAME"));
				
				projects.add(project);
				
			}
			
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			flash.error("Ocorreu um erro inesperado:" + sqlex.toString());
		} finally {
			
			if (rs != null)
				rs.close();
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}
		
		return projects;

	}
	
	
	public static void filtarTaskProduto(boolean checkResponsavel, String responsavel,boolean checkProject ,int idProject) throws SQLException {
		
		List<Jiraissue> tarefas = new ArrayList<Jiraissue>();
		
		ResultSet rs = null;
		Connection con = null;
		Statement s = null;
		
		con = DB.getConnection();
		
		String sql = "select * from jiraissue j where 1=1 ";
		
		if(checkResponsavel){
			sql += " AND j.assignee ilike '%"+responsavel+"%'";
		}
		
		if(checkProject){
			sql += " AND j.project = " + idProject;
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
				tarefas.add(issue);
				
			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		} 
		
		List<Project> projects = new ArrayList<Project>();
		projects.addAll(buscaTodosOsProdutos());
		
		render(projects,tarefas);
	}
	

}
