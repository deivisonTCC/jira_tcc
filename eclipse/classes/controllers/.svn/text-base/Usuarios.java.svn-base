package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Usuario;
import play.db.DB;
import play.mvc.Controller;
import util.ValidatorUtil;

public class Usuarios extends Controller{
	
	public static void index() {
		render();
	}
	
	public static void cadastroUsuario(){
		render();
	}
	
	public static void buscarUsuario() throws SQLException{
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = listarUsuarios(); 
		render(usuarios);
	}
	
	public static List<Usuario> listarUsuarios() throws SQLException{
		
		ResultSet rs = null;
		Connection con = null;
		Statement s = null;
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		con = DB.getConnection();
		String sql = "SELECT id, email, login, senha FROM usuario WHERE 1=1";
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getLong("id"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setEmail(rs.getString("email"));
				usuarios.add(usuario);
				usuario = null;
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
		
		return usuarios;
		
	}
	
	public static Usuario buscaUsuario(Long idUsuario) throws SQLException{
		ResultSet rs = null;
		Connection con = null;
		Statement s = null;
		
		Usuario usuario = new Usuario();
		con = DB.getConnection();
		
		String sql = "SELECT id, email, login, senha FROM usuario WHERE usuario.id = " + idUsuario + " ;" ;
		
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			
			while (rs.next()) {
				usuario.setId(rs.getLong("id"));
				usuario.setLogin(rs.getString("email"));
				usuario.setSenha(rs.getString("login"));
				usuario.setEmail(rs.getString("senha"));
			}
			
			
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}
		
		return usuario;
		
	}

/**
 * Metodo que verifica se o email do usuario j� foi cadastrada. 
 * 
 * @author Deivison Guarines
 */
public static boolean emailJaCadastrado(String email) throws SQLException {
	
	boolean jaExiste = false;
	
	ResultSet rs = null;
	Connection con = null;
	Statement s = null;
	
	List<String> emails = new ArrayList<String>();
	con = DB.getConnection();
	
	String sql = "SELECT email FROM usuario WHERE email = " + "'" + email.toUpperCase() + "'" ;
	
	try {
		s = con.createStatement();
		rs = s.executeQuery(sql);
		
		while (rs.next()) {
			String eMail = rs.getString("email");
			emails.add(eMail);
			eMail = "";	
		}
		
		if(emails.size() > 0){
			System.out.println("Este e-mail j� foi cadastrado.");
		}
		
		
	}catch(SQLException sqlex){
		sqlex.printStackTrace();
	} finally {
		if (rs != null)
			rs.close();
		if (s != null)
			s.close();
		if (con != null)
			con.close();
	}
	
	return jaExiste;
}

/**
 * Método que cadastra o usuario no banco.
 * 
 * @author Deivison Guarines
 */
public static void cadastrar(String email, String login, String senha) throws SQLException {
	
	//Validando os campos
	if(ValidatorUtil.isEmpty(email) && ValidatorUtil.isEmpty(senha)){
		flash.error("Os campos est�o vazios.");
		cadastroUsuario();
	}
	
	if(ValidatorUtil.isEmpty(email)){
		flash.error("Campo E-mail est� em branco.");
		cadastroUsuario();
	}
	
	if(ValidatorUtil.isEmpty(senha)){
		flash.error("Campo Senha est� em branco.");
		cadastroUsuario();
	}
	
	boolean jaCadastrado = emailJaCadastrado(email);
	
	if(jaCadastrado){
		flash.error("Erro no cadastro: Este usu�rio j� existe.");
		cadastroUsuario();
	}
	
	boolean rs = false;
	Connection con = null;
	Statement s = null;
	
	con = DB.getConnection();
	
	String sql = "INSERT INTO usuario (email, login, senha) VALUES (" 
			 + "'" + email.toUpperCase() + "'" + "," 
			 + "'" + login.toUpperCase() + "'" + ","
			 + "'" + senha.toUpperCase() + "'" + ")"; 

	
	try {
		s = con.createStatement();
		rs = s.execute(sql); // execute usado para insert
		
	} catch (SQLException sqlex) {
		sqlex.printStackTrace();
	} finally {
		if (rs == true)
			; // não faz nada, apenas para remover warning
		if (s != null)
			s.close();
		if (con != null)
			con.close();
	}
	
	flash.success("Usuario cadastrado com sucesso.");
	cadastroUsuario();
}

/**
 * Método lista o resultado da busca por usuarios.
  * 
 * @author Deivison Guarines
 */
public static void buscaUsuario(String nome, String sobrenome, String email) throws SQLException{
	ResultSet rs = null;
	Connection con = null;
	Statement s = null;
	
	System.out.println("ESTOU AQUI ===============");
	
	List<Usuario> usuarios = new ArrayList<Usuario>();
	con = DB.getConnection();
	
	String sql = "SELECT nome, sobrenome, email, senha FROM usuario WHERE 1=1 ";
	
	//Gambiarra MODE!!!!
	if(!ValidatorUtil.isEmpty(nome))
		sql+= " AND nome = " + " '" + nome.toUpperCase() + "' ";
	
	if(!ValidatorUtil.isEmpty(sobrenome))
		sql+= " AND sobrenome = " + " '" + sobrenome.toUpperCase() + "' ";
	
	if(!ValidatorUtil.isEmpty(email))
		sql+= " AND email = " + " '" + email.toUpperCase() + "' ";
	
	sql += " ;";
	
	try {
		
		s = con.createStatement();
		rs = s.executeQuery(sql); // executeQuery usado para selects
		
		while (rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setLogin(rs.getString("nome"));
			usuario.setEmail(rs.getString("email"));
			usuario.setSenha(rs.getString("senha"));
			usuarios.add(usuario);
		}
		
		if(usuarios.isEmpty()){
			flash.error("N�o foram encontrados usu�rios com estes par�metros.");
			usuarios = new ArrayList<Usuario>();
			buscarUsuario();
		}
		
		
	}catch (SQLException sqlex) {
		sqlex.printStackTrace();
	}finally {
		if (rs != null)
			rs.close();
		if (s != null)
			s.close();
		if (con != null)
			con.close();
	}
	
	render(usuarios);
}

/**
 * Método que exclui usuario
 * 
 * @author Deivison Guarines
 */
public static void excluirUsuario(Long idUsuario) throws SQLException{
	
	if(ValidatorUtil.isEmpty(idUsuario)){
		flash.error("Este usuario nao existe.");
		index();
	}
	
	boolean rs = false;
	Connection con = null;
	Statement s = null;
	
	con = DB.getConnection();
	
	String sql = "DELETE FROM usuario WHERE usuario.id = " + idUsuario + " ;";

	try {
		
		s = con.createStatement();
		rs = s.execute(sql); 
		
		flash.success("Usu�rio exclu�do com sucesso.");
		
	} catch (SQLException sqlex) {
		sqlex.printStackTrace();
	} finally {
		if (rs == true)
			; // não faz nada, apenas para remover warning
		if (s != null)
			s.close();
		if (con != null)
			con.close();
	}
	
	buscarUsuario();		
	
}

/**
 * Método que Edita usuario
 * 
 * @author Deivison Guarines
 */
public static void editarUsuario(Long idUsuario) throws SQLException{
	
	Usuario usuario = new Usuario();
	usuario = buscaUsuario(idUsuario);
	
	render(usuario);
}

public static void editar(int idUsuario, String email, String login, String senha) throws SQLException{
	
	boolean rs = false;
	Connection con = null;
	Statement s = null;
	
	con = DB.getConnection();
	
	String sql = "UPDATE usuario SET "
			+ " email= " + " '" + email.toUpperCase() + "'" + " , "
			+ " login= " + " '" + login.toUpperCase() + "'" + " , "
			+ " senha= " + " '" + senha.toUpperCase() + "'" 
			+ " WHERE usuario.id = " + idUsuario + " ;";
	
	try {
		
		s = con.createStatement();
		rs = s.execute(sql); 
		
		flash.success("Usuario alterado com sucesso.");
		
	} catch (SQLException sqlex) {
		sqlex.printStackTrace();
	}finally {
		if (rs == true)
			;
		if (s != null)
			s.close();
		if (con != null)
			con.close();
	
	
	buscarUsuario();
	}
}
}
