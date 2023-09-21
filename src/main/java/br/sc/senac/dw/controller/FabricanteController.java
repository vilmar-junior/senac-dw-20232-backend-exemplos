package br.sc.senac.dw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.sc.senac.dw.exception.CampoInvalidoException;
import br.sc.senac.dw.model.entidade.Fabricante;
import br.sc.senac.dw.service.FabricanteService;

/**
 * Classe de controller REST para o CRUD de fabricantes
 * FONTE: https://spring.io/guides/tutorials/rest/
 * 
 * @author Vilmar César Pereira Júnior
 */
@RestController
@RequestMapping(path = "/api/fabricantes") 
@CrossOrigin(origins = {"http://localhost:4200","http://127.0.0.1:5500"}, maxAge = 3600)
public class FabricanteController {
	
	@Autowired
	private FabricanteService service;
	
	/**
	 * Método GET: geralmente utilizado em consultas
	 * Parâmetros podem ser enviados via URL
	 * 
	 * @return a lista de todos os fabricantes
	 */
	@GetMapping
	public List<Fabricante> listarTodos() {
		List<Fabricante> fabricantes = service.listarTodos();
		return fabricantes;
	}
	
	/**
	 * Método POST: geralmente utilizado para inserir novos registros
	 * Parâmetros são enviados no corpo da requisição HTTP, 
	 * para isso usamos a anotação @RequestBody
	 * 
	 * @return o fabricante salvo, com id preenchido
	 * @throws CampoInvalidoException 
	 */
	@PostMapping
	public Fabricante salvar(@RequestBody Fabricante novo) 
			throws CampoInvalidoException {
		return service.inserir(novo);
	}
}