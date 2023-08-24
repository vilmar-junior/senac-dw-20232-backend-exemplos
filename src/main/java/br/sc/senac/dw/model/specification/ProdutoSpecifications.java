package br.sc.senac.dw.model.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.sc.senac.dw.model.entidade.Produto;
import br.sc.senac.dw.model.seletor.ProdutoSeletor;
import jakarta.persistence.criteria.Predicate;

public class ProdutoSpecifications {
	/*
	 * 
	 * root: é uma referência à entidade raiz que você está consultando. No contexto do JPA, isso representa a tabela do banco de dados correspondente à entidade Produto.
     * query: O parâmetro query representa a consulta JPA que está sendo construída. Ele é usado para adicionar cláusulas WHERE, JOIN, ORDER BY, entre outras, à consulta.
     * cb (CriteriaBuilder): é uma interface que oferece métodos para construção de cláusulas de consulta de forma programática. Você usa métodos deste objeto para criar expressões de predicado, funções agregadas e outras partes da consulta.
     * 
	 * */
    public static Specification<Produto> comFiltros(ProdutoSeletor seletor) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (seletor.getNome() != null) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + seletor.getNome().toLowerCase() + "%"));
            }
            
            // TODO Adicionar outros filtros aqui

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}