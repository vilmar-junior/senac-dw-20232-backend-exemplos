package br.sc.senac.dw.model.entidade;

public enum Papel {
	ADMIN("ADMIN"), 
	USUARIO("USUARIO");
	
	private String nome;
	
	Papel(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
