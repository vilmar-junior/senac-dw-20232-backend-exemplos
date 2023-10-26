package br.sc.senac.dw.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.sc.senac.dw.model.entidade.Produto;
import br.sc.senac.dw.model.entidade.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	//UserDetails é o tipo de retorno esperado pelo SpringSecurity
	UserDetails findByLogin(String Login);
}

