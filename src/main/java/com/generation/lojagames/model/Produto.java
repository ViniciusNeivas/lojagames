package com.generation.lojagames.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "tb_produtos")
public class Produto {

	// ID
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Nome
	
	@NotBlank (message = "O atributo nome é obrigatório e não deve conter espaços em brancos!")
	@Size (min = 3, max = 100, message = "O atributo nome deve ter no mínimo 3 e no máximo 100 caracteres" ) 
	private String nome;
	
	// Descrição
	
	@Size (max = 500, message = "O atributo descrição deve conter no máximo 500 caracteres.")
	private String descricao;
	
	// Console
	
	@NotBlank (message = "O atributo console é obrigatório e não deve conter espaços em branco!")
	@Size (max = 500, message = "O atributo console deve conter no máximo 500 caracteres.")
	private String console;
	
	// Quantidade
	
	private int quantidade;
	
	// Data de Lançamento

	private LocalDate dataLançamento;
	
	
	// Preço
	
	@NotBlank (message = "O atributo preço é obrigatório e não deve conter espaços em branco!")
	private double preco;
	
	// Foto
	
	private String foto;
	
	// Categoria
	
	@ManyToOne 
	@JsonIgnoreProperties ("produto")
	private Categoria categoria;

	
	// Getters And Setters
	
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDate getDataLançamento() {
		return dataLançamento;
	}

	public void setDataLançamento(LocalDate dataLançamento) {
		this.dataLançamento = dataLançamento;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
