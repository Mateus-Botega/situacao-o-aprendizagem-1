package br.com.senai.manutencaosenaiapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "Peca")
@Table(name = "pecas")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Peca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Column(name = "descricao")
	@NotEmpty(message = "A descrição é obrigatória")
	@Size(max = 150, message = "A descrição não deve conter mais de 150 caracteres")
	private String descricao;
	
	@Column(name = "especificacoes")
	@Size(max = 400, message = "A especificação não deve conter mais de 400 caracteres")
	private String especificacoes;
	
	@Column(name = "qtde_estoque")
	@NotNull(message = "A quantidade é obrigatória")
	@Min(value = 0, message = "A quantidade em estoque não pode ser menor que zero")
	private Integer qtdeEmEstoque;
	
}
