package models;

import java.io.Serializable;

import play.db.jpa.Model;

public class Usuario extends Model implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;	
	
	private String email;
	
	private String login;
	
	private String senha;
	
	/*private Pessoa pessoa;
	
	private Membro membro;*/

	/**Verifica se o usuario ainda tem acesso ao sistema*/
	private boolean ativo;
	
	public Usuario() {
	}

	/* Getters and Setters */
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}	

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	/*public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}*/

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	/*public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}*/

}
