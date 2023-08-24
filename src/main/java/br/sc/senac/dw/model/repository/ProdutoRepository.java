package br.sc.senac.dw.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.sc.senac.dw.model.entidade.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {
	//É possível declarar métodos do JpaRepository aqui, combinando atributos e operadores SQL
	
	//https://reflectoring.io/spring-data-specifications/
	//Exemplo
	List<Produto> findAllByNomeLike(String nomeInformado);
}

