package br.sc.senac.dw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.sc.senac.dw.exception.CampoInvalidoException;
import br.sc.senac.dw.model.entidade.Produto;
import br.sc.senac.dw.model.seletor.ProdutoSeletor;
import br.sc.senac.dw.service.ProdutoService;

/**
 * Classe de controller REST para o CRUD de produtos
 * FONTE: https://spring.io/guides/tutorials/rest/
 * 
 * @author Vilmar César Pereira Júnior
 */
@RestController
@RequestMapping(path = "/api/produtos") // shorthand for @Controller and @ResponseBody rolled together
public class ProdutoController {
	
	@Autowired //injeção de dependência (tomcat vai instanciar o objeto produtoService sob demanda)
	private ProdutoService produtoService;
	
	/**
	 * Método GET: geralmente utilizado em consultas
	 * Parâmetros podem ser enviados via URL
	 * 
	 * @return a lista de todas os produtos
	 */
	@GetMapping
	public List<Produto> listarTodos() {
		List<Produto> produtos = produtoService.listarTodos();
		return produtos;
	}
	
	@PostMapping("/filtro")
	public List<Produto> listarComSeletor(@RequestBody ProdutoSeletor seletor){
		return produtoService.listarComSeletor(seletor);
	}
	
	/**
	 * Método GET
	 * Parâmetro id foi enviado via URL
	 * @return um produto específico, dado o seu id
	 */
	@GetMapping("/{id}")
	public Produto pesquisarPorId(@PathVariable Integer id){
		return produtoService.consultarPorId(id.longValue());
	}
	
	/**
	 * Método POST: geralmente utilizado para inserir novos registros
	 * Parâmetros são enviados no corpo da requisição HTTP, 
	 * para isso usamos a anotação @RequestBody
	 * 
	 * @return o produto salvo, com id preenchido
	 * @throws CampoInvalidoException 
	 */
	@PostMapping
	public Produto salvar(@RequestBody Produto novoProduto) 
			throws CampoInvalidoException {
		return produtoService.inserir(novoProduto);
	}
	
	/**
	 * Método PUT: utilizado para atualizar registros. 
	 * Muitas vezes é usado o POST em seu lugar
	 * Parâmetros são enviados no corpo da requisição HTTP, 
	 * para isso usamos a anotação @RequestBody
	 * 
	 * @return um booleano indicando se o produto em questão foi atualizado
	 * @throws CampoInvalidoException 
	 */
	@PutMapping()
	public boolean atualizar(@RequestBody Produto produtoParaAtualizar) 
			throws CampoInvalidoException {
		return produtoService.atualizar(produtoParaAtualizar) != null;
	}
	
	/**
	 * Método DELETE
	 * Parâmetro id foi enviado via URL
	 */
	@DeleteMapping("/{id}")
	public boolean excluir(@PathVariable Integer id) {
		return produtoService.excluir(id);
	}

}