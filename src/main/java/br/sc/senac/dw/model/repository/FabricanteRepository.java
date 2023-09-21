package br.sc.senac.dw.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.sc.senac.dw.model.entidade.Fabricante;

@Repository
public interface FabricanteRepository 
	extends JpaRepository<Fabricante, Long>, 
	JpaSpecificationExecutor<Fabricante> {
}

