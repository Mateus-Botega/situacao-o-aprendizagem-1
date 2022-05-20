package br.com.senai.manutencaosenaiapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.senai.manutencaosenaiapi.entity.Tecnico;

@Repository
public interface TecnicosRepository 
		extends JpaRepository<Tecnico, Integer>{
	
	@Modifying
	@Query(value = 
			"DELETE FROM Tecnico t WHERE t.id = :id")
	void deletarPor(Integer id);
	
	@Query(value = 
			"SELECT t "
			+ "FROM Tecnico t "
			+ "WHERE Upper(t.nomeCompleto) LIKE Upper(:nome)")
	List<Tecnico> listarPor(@Param("nome") String nome);

}
