package br.com.senai.manutencaosenaiapi.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.base.Preconditions;

import br.com.senai.manutencaosenaiapi.entity.Cliente;
import br.com.senai.manutencaosenaiapi.repository.ClientesRepository;

@Service
@Validated
public class ClienteService {
	
	final int IDADE_MINIMA = 12;
	
	@Autowired
	private ClientesRepository repository;
	
	public Cliente inserir(
			@Valid
			@NotNull(message = "O cliente não pode ser nulo")
			Cliente novoCliente) {
		this.validarIdadeDo(novoCliente);
		Cliente clienteSalvo = repository.save(novoCliente);
		return clienteSalvo;
	}
	
	public Cliente alterar(
			@Valid
			@NotNull(message = "O cliente não pode ser nulo")
			Cliente clienteSalvo) {
		this.validarIdadeDo(clienteSalvo);
		Cliente clienteAtualizado = repository.save(clienteSalvo);
		return clienteAtualizado;
	}
	
	public List<Cliente> listarPor(
			@NotEmpty(message = "O nome para busca é obrigatório")
			@NotBlank(message = "O nome para busca não deve conter "
					+ "espaço em branco")
			String nome){
		return repository.listarPor("%" + nome + "%");
	}
	
	public void removerPor(
			@NotNull(message = "O id para remoção não pode ser nulo")
			@Min(value = 1, message = "O id deve ser maior que zero")
			Integer id) {
		this.repository.deleteById(id);
	}
	
	private void validarIdadeDo(Cliente cliente) {
		Preconditions.checkArgument(
				cliente.getIdade() > IDADE_MINIMA,
				"O cliente deve possuir mais de 12 anos");
	}
	
}
