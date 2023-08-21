package br.sc.senac.dw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.sc.senac.dw.exception.CampoInvalidoException;
import br.sc.senac.dw.model.entidade.Produto;
import br.sc.senac.dw.model.repository.ProdutoRepository;

@Service
public class ProdutoService {

	//Anteriormente fazíamos:
	//private ProdutoDAO dao = new ProdutoDAO();
	@Autowired
    private ProdutoRepository produtoRepository;

	@Transactional
	public List<Produto> listarTodos() {
		return produtoRepository.findAll();
	}
	
	public Produto consultarPorId(Long id) {
		//fonte: https://stackoverflow.com/questions/52656517/no-serializer-found-for-class-org-hibernate-proxy-pojo-bytebuddy-bytebuddyinterc
		return produtoRepository.findById(id.longValue()).get();
	}

	public Produto inserir(Produto novoProduto) throws CampoInvalidoException {
		validarCamposObrigatorios(novoProduto);
		return produtoRepository.save(novoProduto);
	}
	
	public Produto atualizar(Produto produtoParaAtualizar) throws CampoInvalidoException {
		validarCamposObrigatorios(produtoParaAtualizar);
		return produtoRepository.save(produtoParaAtualizar);
	}
	
	public boolean excluir(Integer id) {
		produtoRepository.deleteById(id.longValue());
		return true;
	}
	
	//Métodos auxiliares
	private void validarCamposObrigatorios(Produto produto) throws CampoInvalidoException {
		String mensagemValidacao = "";
		mensagemValidacao += validarCampoString(produto.getNome(), "nome");
		mensagemValidacao += validarCampoString(produto.getFabricante(), "fabricante");
		mensagemValidacao += validarCampoDouble(produto.getValor(), "valor");
		mensagemValidacao += validarCampoDouble(produto.getPeso(), "peso");
		
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

	private String validarCampoDouble(Double valorCampo, String nomeCampo) {
		if(valorCampo == null) {
			return "Informe o " + nomeCampo + " \n";
		}
		return "";
	}
}
