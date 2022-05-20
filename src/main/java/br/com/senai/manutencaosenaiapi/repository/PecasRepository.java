package br.com.senai.manutencaosenaiapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.senai.manutencaosenaiapi.entity.Peca;

@Repository
public interface PecasRepository extends 
		JpaRepository<Peca, Integer>{
	
	@Query(value = 
			"SELECT p "			
			+ "FROM Peca p "
			+ "WHERE Upper(p.descricao) LIKE Upper(:desc)")
	List<Peca> listarPor(@Param("desc") String descricao);
	
}
