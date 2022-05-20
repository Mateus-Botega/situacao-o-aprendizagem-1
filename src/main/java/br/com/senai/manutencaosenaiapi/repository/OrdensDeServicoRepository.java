package br.com.senai.manutencaosenaiapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.senai.manutencaosenaiapi.entity.OrdemDeServico;

@Repository
public interface OrdensDeServicoRepository 
		extends JpaRepository<OrdemDeServico, Integer>{
	
	@Query(value = 
			"SELECT o "
			+ "FROM OrdemDeServico o "
			+ "JOIN FETCH o.cliente "
			+ "JOIN FETCH o.tecnico "
			+ "JOIN FETCH o.pecasDoReparo "
			+ "WHERE o.id = :id")
	OrdemDeServico buscarPor(@Param("id") Integer id);

}
