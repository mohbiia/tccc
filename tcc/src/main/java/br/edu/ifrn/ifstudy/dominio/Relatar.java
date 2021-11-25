package br.edu.ifrn.ifstudy.dominio;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Relatar {
	
	
	@NotBlank(message = "O campo Matéria é obrigatório")
	@Size(min = 2, message = "A matéria deve ter no mínimo dois caracteres.")
	private String materia;
	
	
	private int data;
	private int duracao;
	@NotBlank(message = "O campo Método é obrigatório")
	private String metodo;
	private String conteudo;
	private int numeroQuestao;
	private int numeroAcerto;
	private String radioFacil;
	private String radioMedio;
	private String radioDificil;
	private String documento;
	private int idRelatar;
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public int getNumeroQuestao() {
		return numeroQuestao;
	}
	public void setNumeroQuestao(int numeroQuestao) {
		this.numeroQuestao = numeroQuestao;
	}
	public int getNumeroAcerto() {
		return numeroAcerto;
	}
	public void setNumeroAcerto(int numeroAcerto) {
		this.numeroAcerto = numeroAcerto;
	}
	public String getRadioFacil() {
		return radioFacil;
	}
	public void setRadioFacil(String radioFacil) {
		this.radioFacil = radioFacil;
	}
	public String getRadioMedio() {
		return radioMedio;
	}
	public void setRadioMedio(String radioMedio) {
		this.radioMedio = radioMedio;
	}
	public String getRadioDificil() {
		return radioDificil;
	}
	public void setRadioDificil(String radioDificil) {
		this.radioDificil = radioDificil;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public int getIdRelatar() {
		return idRelatar;
	}
	public void setIdRelatar(int idRelatar) {
		this.idRelatar = idRelatar;
	}
	
	
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idRelatar;
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
		Relatar other = (Relatar) obj;
		if (idRelatar != other.idRelatar)
			return false;
		return true;
	}
	
	
	

}

