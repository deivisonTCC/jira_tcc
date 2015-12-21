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

public class BuscarTaskBugFixes extends Controller{
		
	
	public static void filtarTaskBugFixes() throws SQLException{
		
		List<Jiraissue> tarefas = new ArrayList<Jiraissue>();
		
		ResultSet rs = null;
		Connection con = null;
		Statement s = null;
		
		con = DB.getConnection();
		
		String sql = "select * from jiraissue j "
				+ " join issuelink i on j.id = i.source "
				+ " where j.issuetype = '1' AND j.issuestatus = '10001' ";
		
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
		
		render(tarefas);
	}

}
