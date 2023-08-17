package br.sc.senac.dw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.sc.senac.dw.model.entidade.Produto;
import br.sc.senac.dw.service.ProdutoService;

@RestController
@RequestMapping(path = "/api/produtos") // shorthand for @Controller and @ResponseBody rolled together
public class ProdutoController {
	
	//Anteriormente fazíamos:
	//private ProdutoBO bo = new ProdutoBO();
	
	@Autowired //injeção de dependência (tomcat vai instanciar o objeto produtoService sob demanda)
	private ProdutoService produtoService;
	
	@GetMapping(path = "/todos")
	public List<Produto> listarTodos() {
		return produtoService.listarTodos();
	}
	
	@GetMapping(path = "/{id}")
	public Produto consultarPorId(@PathVariable Integer id) {
		return produtoService.consultarPorId(id.longValue());
	}
}