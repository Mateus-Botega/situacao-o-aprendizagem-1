package br.com.senai.manutencaosenaiapi.view.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.senai.manutencaosenaiapi.entity.TipoDePeca;

public class TipoDePecaTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private List<TipoDePeca> tipos;
	private final int QTDE_COLUNAS = 2;
	
	public TipoDePecaTableModel(List<TipoDePeca> tipos) {
		this.tipos = tipos;
	}

	@Override
	public int getRowCount() {
		return tipos.size();
	}

	@Override
	public int getColumnCount() {
		return QTDE_COLUNAS;
	}
	
	@Override
	public String getColumnName(int column) {
		if (column == 0) {
			return "ID";
		}
		else if (column == 1) {
			return "Descrição";
		}		
		throw new IllegalArgumentException("A coluna informada é inválida");
	}
	
	public TipoDePeca getPor(int rowIndex) {
		return tipos.get(rowIndex);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return tipos.get(rowIndex).getId();
		}
		else if (columnIndex == 1) {
			return tipos.get(rowIndex).getDescricao();
		}
		
		throw new IllegalArgumentException("Indice informado inválido");
	}
	
}
