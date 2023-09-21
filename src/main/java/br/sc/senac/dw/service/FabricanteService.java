package br.sc.senac.dw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.sc.senac.dw.exception.CampoInvalidoException;
import br.sc.senac.dw.model.entidade.Fabricante;
import br.sc.senac.dw.model.entidade.Produto;
import br.sc.senac.dw.model.repository.FabricanteRepository;
import br.sc.senac.dw.model.repository.ProdutoRepository;
import br.sc.senac.dw.model.seletor.ProdutoSeletor;
import br.sc.senac.dw.model.specification.ProdutoSpecifications;

@Service
public class FabricanteService {

	@Autowired
	private FabricanteRepository fabricanteRepository;

	public List<Fabricante> listarTodos() {
		return fabricanteRepository.findAll();
	}

	public Fabricante inserir(Fabricante novoFabricante) throws CampoInvalidoException {
		validarCamposObrigatorios(novoFabricante);
		return fabricanteRepository.save(novoFabricante);
	}

	//Métodos auxiliares
	private void validarCamposObrigatorios(Fabricante fabricante) throws CampoInvalidoException {
		String mensagemValidacao = "";
		mensagemValidacao += validarCampoString(fabricante.getNome(), "nome");
		mensagemValidacao += validarCampoString(fabricante.getCnpj(), "cnpj");
		mensagemValidacao += validarCampoString(fabricante.getCep(), "cep");
		mensagemValidacao += validarCampoString(fabricante.getCidade(), "cidade");
		mensagemValidacao += validarCampoString(fabricante.getUf(), "uf");
		
		if(!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
	}

	private String validarCampoString(String valorCampo, String nomeCampo) {
		if(valorCampo == null || valorCampo.trim().isEmpty()) {
			return "Informe o " + nomeCampo + " \n";
		}
		return "";
	}
}
