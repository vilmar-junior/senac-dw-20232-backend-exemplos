package br.sc.senac.dw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
