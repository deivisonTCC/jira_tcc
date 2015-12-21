package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Issuestatus;
import models.Jiraissue;
import models.Sprint;
import play.db.DB;
import play.mvc.Controller;

public class BuscarTaskBugSprint extends Controller{
		
	private static List<Sprint> buscaTodasAsSprints() throws SQLException{
		
		ResultSet rs = null;
		Connection con = null;
		Statement s = null;
		
		List<Sprint> sprints = new ArrayList<Sprint>();
		
		con = DB.getConnection();
		
		String sql = "select * from \"AO_60DB71_SPRINT\" ";
		
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			
			while (rs.next()) {
				
				Sprint sprint = new Sprint();
				sprint.setId(rs.getLong("ID"));
				sprint.setName(rs.getString("NAME"));
				
				sprints.add(sprint);
				
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
		
		return sprints;

	}
	
	
	public static void filtarTaskBugSprint(boolean checkResponsavel, String responsavel, boolean checkSprint, String sprint) throws SQLException{
		List<Jiraissue> tarefas = new ArrayList<Jiraissue>();
		
		ResultSet rs = null;
		Connection con = null;
		Statement s = null;
		
		con = DB.getConnection();
		
		String sql = "select * from customfieldvalue c "
					+ "join jiraissue j on j.id = c.issue "
					+ "where j.issuetype = '1'";
		
		if(checkSprint){
			sql += " AND c.stringvalue = '" + sprint + "'";
		}
		
		if(checkResponsavel){
			sql += " AND j.assignee ilike '%" + responsavel + "%'";
		}
		
		System.out.println(checkResponsavel + responsavel + checkSprint + sprint +"  "+ sql);
		
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
		
		List<Sprint> sprints = new ArrayList<Sprint>();
		sprints.addAll(buscaTodasAsSprints());
		
		render(sprints,tarefas);
	}
	
	
	

}
