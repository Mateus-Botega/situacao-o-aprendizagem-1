package br.com.senai.manutencaosenaiapi.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.Data;

@Data
@Entity(name = "OrdemDeServico")
@Table(name = "ordens_servicos")
public class OrdemDeServico {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente")
	@NotNull(message = "O cliente da ordem não pode ser nulo")
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tecnico")
	@NotNull(message = "O técnico da ordem não pode ser nulo")
	private Tecnico tecnico;
	
	@Column(name = "dt_abertura")
	@NotNull(message = "A data de abertura é obrigatória")
	@PastOrPresent(message = "A data de abertura não pode "
			+ "ser posterior a data atual")
	private LocalDate dataDeAbertura;
	
	@Column(name = "dt_encerramento")
	@PastOrPresent(message = "A data de encerramento não "
			+ "pode ser posterior a data atual")
	private LocalDate dataDeEncerramento;
	
	@Column(name = "desc_problema")
	@NotEmpty(message = "A descrição do problema é obrigatória")
	@NotBlank(message = "A descrição do problema não foi informada")
	private String descricaoDoProblema;
	
	@Column(name = "desc_reparo")
	private String descricaoDoReparo;
	
	@ManyToMany
	@JoinTable(
		name = "pecas_reparos",
		joinColumns = @JoinColumn(name = "id_ordem"),
		inverseJoinColumns = @JoinColumn(name = "id_peca"))
	@NotEmpty(message = "Deve haver ao menos uma peça de reparo")
	private List<Peca> pecasDoReparo;
	
}
