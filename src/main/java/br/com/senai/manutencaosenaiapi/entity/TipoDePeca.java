package br.com.senai.manutencaosenaiapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "TipoDePeca")
@Table(name = "tipos_pecas")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class TipoDePeca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Column(name = "descricao")
	@NotEmpty(message = "A descrição é obrigatória")
	@Size(max = 150, message = "A descrição não deve conter mais de 150 caracteres")
	private String descricao;
	
}
