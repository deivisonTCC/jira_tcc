package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Scope.Session;

import java.util.*;

import models.*;
import play.cache.Cache;
import play.db.*;
import util.ValidatorUtil;

import java.sql.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponseWrapper;

public class Application extends Controller {
	
	public static List<Usuarios> lista = new ArrayList<Usuarios>();

	public static void index(String projeto, String abertapor, String responsavel, String situacao, int prioridade, int tipo,
			boolean checkprojeto, boolean checkabertapor, boolean checkresponsavel, boolean checksituacao, boolean checkprioridade,
			boolean checktipo) throws SQLException {
		
		List<Jiraissue> issues = new ArrayList<Jiraissue>();
		
		//System.out.println("INDEX: " + projeto + " " + abertapor + " " + responsavel + " " + situacao + " " + prioridade + " " + tipo + " " + checkprojeto + " " + checkabertapor + " " + checkresponsavel + " " + checksituacao + " " + checkprioridade + " " + checktipo);
		
		issues = filtraTarefa(checkprojeto ? projeto: null, checkabertapor ? abertapor : null, checkresponsavel ? responsavel : null, checksituacao ? situacao : null, checkprioridade ? prioridade : null, checktipo ? tipo : null); 
		render(issues);
	}
	
	public static void main(){
		render();
	}
	
	public static void detalharTarefa(Integer id) throws SQLException{
		Jiraissue issue = new Jiraissue();
		List<Jiraissue> subs = new ArrayList<Jiraissue>();
		
		issue = findByPrimaryKey(id);
		

		subs = findSubTasksByPrimaryKeyIssue(id);
	
		render(issue, subs);
	}
	
	public static void listaTodosLinks() throws SQLException {
		
		List<Issuelink> links = new ArrayList<Issuelink>();
		
		links = findAllIssueLink();
		
		HashMap<Jiraissue, List<Jiraissue>> map = new HashMap<Jiraissue, List<Jiraissue>>();
		
		List<Jiraissue> lista = new ArrayList<Jiraissue>();
			
		for(Issuelink l : links){
			
			lista.add(l.getJiraDestination());
			
			if(map.containsKey(l.getJiraSource())){
				
				map.get(l.getJiraSource()).add(l.getJiraDestination()) ;
				
			}else{
				List<Jiraissue> des = new ArrayList<Jiraissue>();
				des.add(l.getJiraDestination());
				
				map.put(l.getJiraSource(), des);
			}
			
		}

		Set set = map.entrySet();
		
		Iterator i = set.iterator();
		
		while(i.hasNext()) {
			Map.Entry me = (Map.Entry)i.next();
	        System.out.print(me.getKey() + ": ");
	        System.out.println(me.getValue());
		}
		
		render(links, map, lista);
		
	}
	
	/**
	 *
	 * 
	 * @author Deivison Guarines
	 */
	public static void login(String email, String senha) throws SQLException {
		
		if(email.equals("admin") && senha.equals("admin")){
			flash.success("Olá %s seja bem vindo!", "Admin");
			main();
		}
		
		ResultSet rs = null;
		Connection con = null;
		Statement s = null;
		
		if(ValidatorUtil.isEmpty(email) && ValidatorUtil.isEmpty(senha)){
			flash.error("Os campos estão em branco.");
			//index();
		}
		if(ValidatorUtil.isEmpty(email)){
			flash.error("Campo Email esta em branco.");
			//index();
		}
		if(ValidatorUtil.isEmpty(senha)){
			flash.error("Campo Senha esta em branco.");
			//index();
		}

		con = DB.getConnection();
		
		String sql = "SELECT * FROM usuario WHERE (email ='" + email + "' OR login ilike '" + email + "' ) "+ " AND senha ='" + senha + "'";

		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);

			Usuario usuario = new Usuario();
			
			while (rs.next()) {
				usuario.setId(rs.getLong("id"));
				usuario.setLogin(rs.getString("login"));				
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
			}

			if (!ValidatorUtil.isEmpty(usuario.getLogin())) {
				session.put("usuario", usuario.getLogin().toString());
				flash.success("Olá %s seja bem vindo!", usuario.getLogin());
				main();
			}else {
				flash.error("Email ou Senha incorretos.");
				//index();
			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			flash.error("Ocorreu um erro inesperado:" + sqlex.toString());
			//index();
		} finally {
			if (rs != null)
				rs.close();
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}

	}
	
	/**
	 * Retorna a "pessoa" cujo login est� registrado na sess�o
	 */
	public static Usuario getLogin() {
		String id = session.get("usuario");

		if (id != null) {
			Usuario usuario = Usuario.findById(Long.parseLong(id));
			return usuario;
		}
		return null;
	}
	
/*	*//**
	 * Remove os dados da sess�o, efetuando o logout
	 * @throws SQLException 
	 *//*
	public static void logout() throws SQLException {
		session.remove("usuario");
		index();
	}*/
	
	 /**
     * Verifica se o usuário esta logado
     * @return
     */
    public static boolean isLogged()
    {
    	return Session.current().get("usuario")!=null;
    }
    
public static List<Jiraissue> findAllIssues() throws SQLException{
		
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

public static Jiraissue findByPrimaryKey(Integer id) throws SQLException{
	
	ResultSet rs = null;
	Connection con = null;
	Statement s = null;

	con = DB.getConnection();
	
	Jiraissue issue = new Jiraissue();
	
	String sql = "select j.id, j.environment, j.creator, j.assignee, i.pname as status, p.pname as priority, t.pname as issuetype, j.description from jiraissue j "
			+ " inner join issuestatus i on i.id = j.issuestatus "
			+ " inner join priority p on p.id = j.priority "
			+ " inner join issuetype t on t.id = j.issuetype "
			+ " where j.id = " + id;
	
	try {
		s = con.createStatement();
		rs = s.executeQuery(sql);
		

		while(rs.next()){
			issue.setId(rs.getLong("id"));
			issue.setEnvironment(rs.getString("environment"));
			issue.setCreator((rs.getString("creator")));
			issue.setAssignee(rs.getString("assignee"));
			issue.setIssuestatus(rs.getString("status"));
			issue.setPriority(rs.getString("priority"));
			issue.setIssuetype(rs.getString("issuetype"));
			issue.setDescription(rs.getString("description"));
		}
		
	} catch (SQLException sqlex) {
		sqlex.printStackTrace();
	}
	
	
	return issue;
	
}

public static List<Jiraissue> findSubTasksByPrimaryKeyIssue(Integer id) throws SQLException{
	
	ResultSet rs = null;
	Connection con = DB.getConnection();
	Statement s = null;
	
	//con = DB.getConnection();
	
	List<Jiraissue> subs = new ArrayList<Jiraissue>();
	
	String sql = " select j.id, j.environment, j.creator, j.assignee, i.pname as status, p.pname as priority, t.pname as issuetype from jiraissue j "
			+ " inner join issuestatus i on i.id = j.issuestatus "
			+ " inner join priority p on p.id = j.priority "
			+ " inner join issuetype t on t.id = j.issuetype "
			+ " where j.id in ((select l.destination from issuelink l where l.source = " + id + " and l.linktype = " + Issuelink.JIRA_SUBTASK_LINK + ")) ";
	
	try {
		s = con.createStatement();
		rs = s.executeQuery(sql);
		

		while(rs.next()){
			Jiraissue sub = new Jiraissue();
			sub.setId(rs.getLong("id"));
			sub.setEnvironment(rs.getString("environment"));
			sub.setCreator((rs.getString("creator")));
			sub.setAssignee(rs.getString("assignee"));
			sub.setIssuestatus(rs.getString("status"));
			sub.setPriority(rs.getString("priority"));
			sub.setIssuetype(rs.getString("issuetype"));
			subs.add(sub);
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
	
	
	return subs;
}

public static List<Jiraissue> filtraTarefa(String projeto, String abertapor, String responsavel, String situacao, Integer prioridade, Integer tipo) throws SQLException{
	
	//System.out.println("FILTRAR: " + projeto + " " + abertapor + " " + responsavel + " " + situacao + " " + prioridade + " " + tipo );
	
	ResultSet rs = null;
	Connection con = null;
	Statement s = null;
	
	List<Jiraissue> issues = new ArrayList<Jiraissue>();
	
	
	con = DB.getConnection();
	
	String sql = "select j.id, j.environment, j.creator, j.assignee, i.pname as status, p.pname as priority, t.pname as issuetype from jiraissue j "
			+ " inner join issuestatus i on i.id = j.issuestatus "
			+ " inner join priority p on p.id = j.priority "
			+ " inner join issuetype t on t.id = j.issuetype "
			+ " where 1=1 ";
	
	if(!ValidatorUtil.isEmpty(projeto)){
		sql += " and j.environment ilike '%" + projeto + "%' "; 
	}
	
	if(!ValidatorUtil.isEmpty(abertapor)){
		sql += " and j.creator ilike '%" + abertapor + "%' "; 
	}
	
	if(!ValidatorUtil.isEmpty(responsavel)){
		sql += " and j.assignee ilike '%" + responsavel + "%' "; 
	}
	
	if(!ValidatorUtil.isEmpty(situacao)){
		sql += " and j.issuestatus = '" + situacao + "'"; 
	}
	
	if(!ValidatorUtil.isEmpty(prioridade)){
		sql += " and j.priority = '" + prioridade + "'"; 
	}
	
	if(!ValidatorUtil.isEmpty(tipo)){
		sql += " and j.issuetype = '" + tipo + "'"; 
	}
	
	sql += " order by j.id  ";
	
	
	try {
		s = con.createStatement();
		rs = s.executeQuery(sql);
		
		while (rs.next()) {
			
			Jiraissue issue = new Jiraissue();
			issue.setId(rs.getLong("id"));
			issue.setEnvironment(rs.getString("environment"));
			issue.setCreator((rs.getString("creator")));
			issue.setAssignee(rs.getString("assignee"));
			issue.setIssuestatus(rs.getString("status"));
			issue.setPriority(rs.getString("priority"));
			issue.setIssuetype(rs.getString("issuetype"));
			issues.add(issue);
			
		}
		
		//flash.success("Busca realizada com sucesso.");
		
		
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

public static List<Issuelink> findAllIssueLink () throws SQLException{
	
	ResultSet rs = null;
	Connection con = DB.getConnection();
	Statement s = null;
	
	con = DB.getConnection();
	
	List<Issuelink> issues = new ArrayList<Issuelink>();
	
	String source = " j.id as s_id, j.summary as s_summary, j.environment s_environment, j.creator as s_creator, j.assignee as s_assignee, si.pname as s_status, sp.pname as s_priority, st.pname as s_issuetype ";
	
	String destination = " e.id as d_id, e.summary as d_summary, e.environment d_environment, e.creator as d_creator, e.assignee as d_assignee, es.pname as d_status, ep.pname as d_priority, et.pname as d_issuetype ";
	
	String sql = " SELECT il.id, il.linktype, " + source + ",  " + destination + " FROM issuelink il "
			+ " JOIN jiraissue j ON j.id = il.source "
			+ " JOIN jiraissue e ON e.id = il.destination "
			+ " JOIN issuestatus si on si.id = j.issuestatus "
			+ " JOIN priority sp on sp.id = j.priority "
			+ " JOIN issuetype st on st.id = j.issuetype "
			+ " JOIN issuestatus es on es.id = e.issuestatus "
			+ " JOIN priority ep on ep.id = e.priority "
			+ " JOIN issuetype et on et.id = e.issuetype "
			+ " WHERE il.linktype = " + Issuelink.BLOCKS + " ORDER BY il.source ";
	
	try {
		s = con.createStatement();
		rs = s.executeQuery(sql);
		
		while (rs.next()) {
			
			Issuelink issuelink = new Issuelink();
			issuelink.setId(rs.getLong("id"));
			issuelink.setLinktype(rs.getInt("linktype"));
			
			issuelink.setJiraSource(new Jiraissue());
			issuelink.getJiraSource().setId(rs.getLong("s_id"));
			issuelink.getJiraSource().setSummary(rs.getString("s_summary"));
			issuelink.getJiraSource().setEnvironment(rs.getString("s_environment"));
			issuelink.getJiraSource().setCreator((rs.getString("s_creator")));
			issuelink.getJiraSource().setAssignee(rs.getString("s_assignee"));
			issuelink.getJiraSource().setIssuestatus(rs.getString("s_status"));
			issuelink.getJiraSource().setPriority(rs.getString("s_priority"));
			issuelink.getJiraSource().setIssuetype(rs.getString("s_issuetype"));
			
			issuelink.setJiraDestination(new Jiraissue());
			issuelink.getJiraDestination().setId(rs.getLong("d_id"));
			issuelink.getJiraDestination().setSummary(rs.getString("d_summary"));
			issuelink.getJiraDestination().setEnvironment(rs.getString("d_environment"));
			issuelink.getJiraDestination().setCreator((rs.getString("d_creator")));
			issuelink.getJiraDestination().setAssignee(rs.getString("d_assignee"));
			issuelink.getJiraDestination().setIssuestatus(rs.getString("d_status"));
			issuelink.getJiraDestination().setPriority(rs.getString("d_priority"));
			issuelink.getJiraDestination().setIssuetype(rs.getString("d_issuetype"));
			
			issues.add(issuelink);
		}
		
	/*	for(Issuelink l: issues){
			System.out.println(" Link: " + l.getId() + " " + l.getLinktype() + " " + l.getJiraSource().getId() + " " + l.getJiraDestination().getId());
		}*/
		
	}  catch (SQLException sqlex) {
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
	
}