package br.sc.senac.dw.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.sc.senac.dw.exception.CampoInvalidoException;
import br.sc.senac.dw.model.entidade.Fabricante;
import br.sc.senac.dw.model.entidade.TipoFabricante;
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
	
	@GetMapping
	public List<Fabricante> listarTodos() {
		List<Fabricante> fabricantes = service.listarTodos();
		return fabricantes;
	}
	
	@PostMapping
	public Fabricante salvar(@RequestBody Fabricante novo) 
			throws CampoInvalidoException {
		
		return service.inserir(novo);
	}
	
	@GetMapping("/{id}")
	public Fabricante pesquisarPorId(@PathVariable Integer id){
		return service.consultarPorId(id.longValue());
	}
	
	@PutMapping
	public boolean atualizar(@RequestBody Fabricante fabricanteParaAtualizar) 
			throws CampoInvalidoException {
		return service.atualizar(fabricanteParaAtualizar) != null;
	}
	
	@DeleteMapping("/{id}")
	public boolean excluir(@PathVariable Integer id) {
		return service.excluir(id);
	}
	
	@GetMapping("/tipos")
	public List<String> getTipos(){
		return Arrays.asList(TipoFabricante.FUNDO_QUINTAL.toString(), TipoFabricante.MARROMENO.toString(), TipoFabricante.GRANDE.toString());
	}
	
}