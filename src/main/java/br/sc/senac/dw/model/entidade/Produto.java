package br.sc.senac.dw.model.entidade;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Anotação do lombok para gerar getters, setters e construtor vazio (não descobri ainda porque não gerou o construtor com todos os argumentos)
//TODO ver esse artigo ("BUGFIX"): 
//https://stackoverflow.com/questions/62168522/spring-boot-rest-api-returns-empty-json-used-with-lombok
//@Data

//Anotação do JPA para determinar que esta classe é uma entidade (objeto será gerenciado pelo container)
@Entity

//Anotação do JPA para associar a entidade a uma tabela do banco
//Nome da tabela no banco, caso nada seja informado é considerado o nome da classe da entidade
@Table(name = "produtos") 
@NoArgsConstructor
public class Produto {
	
	@Id
	private Integer id;
	private String nome;
	private String fabricante;
	private Double valor;
	private Double peso;
	private LocalDate dataCadastro;
	
	public Produto(Integer id, String nome, String fabricante, Double valor, Double peso, LocalDate dataCadastro) {
		super();
		this.id = id;
		this.nome = nome;
		this.fabricante = fabricante;
		this.valor = valor;
		this.peso = peso;
		this.dataCadastro = dataCadastro;
	}
	
	public Produto() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}