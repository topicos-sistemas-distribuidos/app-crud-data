package com.myapp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "usuario")
public class Usuario{
private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	@NotNull
	@Size(min=4, max=255)
	String nome;
	@NotNull
	@Size(min=4, max=20)
	String login;
	@NotNull
	@Size(min=5, max=255)
	String email;
	@NotNull
	@Size(min=4, max=16)
	String senha;
	String imagemPath;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString(){
		return "Usuario [id: " + this.id + ", nome: " + this.nome + ", login: " + this.login + ", e-mail: " + this.email + ", senha: " + this.senha +  "]"; 
	}
	
	public String getImagemPath() {
		return imagemPath;
	}
	public void setImagemPath(String imagemPath) {
		this.imagemPath = imagemPath;
	}
			
}
