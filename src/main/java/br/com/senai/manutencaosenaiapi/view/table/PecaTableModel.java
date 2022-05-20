package br.com.senai.manutencaosenaiapi.view.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.senai.manutencaosenaiapi.entity.Peca;

public class PecaTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;

	private final int QTDE_COLUNAS = 3;
	
	private List<Peca> pecas;	
	
	public PecaTableModel(List<Peca> pecas) {	
		this.pecas = pecas;
	}

	@Override
	public int getRowCount() {
		return pecas.size();
	}

	@Override
	public int getColumnCount() {
		return QTDE_COLUNAS;
	}
	
	@Override
	public String getColumnName(int column) {
		if (column == 0) {
			return "ID";
		}else if (column == 1) {
			return "Descrição";
		}else if (column == 2) {
			return "QTDE";
		}
		throw new IllegalArgumentException("Indice inválido");
	}
	
	public Peca getPor(int rowIndex) {
		return pecas.get(rowIndex);
	}
	
	public void removerPor(int rowIndex) {
		pecas.remove(rowIndex);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (columnIndex == 0) {
			return pecas.get(rowIndex).getId();
		}else if (columnIndex == 1) {
			return pecas.get(rowIndex).getDescricao();
		}else if (columnIndex == 2) {
			return pecas.get(rowIndex).getQtdeEmEstoque();
		}
		
		throw new IllegalArgumentException("Indice informado inválido");
	}

}
