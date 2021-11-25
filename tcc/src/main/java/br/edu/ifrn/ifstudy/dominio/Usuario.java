package br.edu.ifrn.ifstudy.dominio;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Usuario {
	
	//Usuario do IfStudy
	
	
	private int id;

	@NotBlank(message = "O campo nome é obrigatório.")
	@Size(min = 2, message = "O nome deve ter no mínimo 2 caracteres")
	private String nome;
	
	@NotBlank(message = "O campo senha é obrigatório.")
	private String email;
	
	@NotBlank(message = "O campo senha é obrigatório.")
	@Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
	private String senha;
	
	@NotBlank(message="O campo escolaridade é obrigatório. ")
	private String escolaridade;

		
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
	public String getEscolaridade() {
		return escolaridade;
	}
	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
