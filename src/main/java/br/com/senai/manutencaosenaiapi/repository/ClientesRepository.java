package br.com.senai.manutencaosenaiapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.senai.manutencaosenaiapi.entity.Cliente;

@Repository
public interface ClientesRepository 
		extends JpaRepository<Cliente, Integer>{
	
	@Query(value = 
			"SELECT c "
			+ "FROM Cliente c "
			+ "WHERE Upper(c.nome) LIKE Upper(:nome)")
	List<Cliente> listarPor(@Param("nome") String nome);
	
}
