package com.generation.lojagames.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table (name = "tb_usuarios")
public class Usuario {

	//ID
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	

	//Nome
	
	@NotBlank (message = "O atributo nome é obrigatório e não pode utilizar espaços em branco.")
	@Size (min = 3, max = 100, message = "O atributo nome deve conter no mínino 3 e no máximo 100 caracteres.")
	private String nome;
	
	//Usuário
	
	@NotBlank (message = "O atributo nome é obrigatório e não pode utilizar espaços em branco.")
	@Size (min = 5, max = 100, message = "O atributo usuário deve conter no mínino 5 e no máximo 100 caracteres.")
	private String usuario;
	
	//Senha
	
	@NotBlank (message = "O atributo nome é obrigatório e não pode utilizar espaços em branco.")
	@Size (min = 8, message = "O atributo senha deve conter no mínimo 8 caracteres.")
	private String senha;
	
	//Foto
	
	private String foto;
	
	//Data de Nascimento
	
	@Column (name = "data_nascimento")
	@JsonFormat (pattern = "yyyy-MM-dd")
	@NotNull (message = "O atributo data de Nascimento é obrigatório!")
	private LocalDate dataNascimento;
 
	
	// Getter and Setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}	
	
}
