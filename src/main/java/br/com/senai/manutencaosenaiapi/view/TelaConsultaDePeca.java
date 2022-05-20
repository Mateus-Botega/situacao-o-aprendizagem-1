package br.com.senai.manutencaosenaiapi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumnModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.senai.manutencaosenaiapi.entity.Peca;
import br.com.senai.manutencaosenaiapi.service.PecaService;
import br.com.senai.manutencaosenaiapi.view.table.PecaTableModel;

@Component
public class TelaConsultaDePeca extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField edtFiltro;
	private JTable table;
	
	@Autowired
	private PecaService service;
	
	@Autowired
	private TelaCadastroDePeca telaDeCadastro;

	public TelaConsultaDePeca() {
		setTitle("Tela de Consulta de Peça");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblFiltro = new JLabel("Filtro");
		
		edtFiltro = new JTextField();
		edtFiltro.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Peca> pecas = service.listarPor(edtFiltro.getText());
				PecaTableModel model = new PecaTableModel(pecas);
				table.setModel(model);
				TableColumnModel cm = table.getColumnModel();
				cm.getColumn(0).setPreferredWidth(50);
				cm.getColumn(1).setPreferredWidth(500);
				cm.getColumn(2).setPreferredWidth(50);
				table.updateUI();
			}
		});
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaDeCadastro.colocarEmModoInclusao();
				setVisible(false);
			}
		});
		table = new JTable();
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "A\u00E7\u00F5es para a linha selecionada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 482, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(edtFiltro, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnPesquisar)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnAdicionar))
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblFiltro))
					.addContainerGap(6, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblFiltro)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(edtFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar)
						.addComponent(btnAdicionar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(40, Short.MAX_VALUE))
		);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = table.getSelectedRow();
				PecaTableModel model = (PecaTableModel)table.getModel();
				Peca pecaSelecionada = model.getPor(linhaSelecionada);
				telaDeCadastro.colocarEmEdicao(pecaSelecionada);
				telaDeCadastro.setVisible(true);
				setVisible(false);
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = table.getSelectedRow();
				PecaTableModel model = (PecaTableModel)table.getModel();
				Peca pecaSalva = model.getPor(linhaSelecionada);
				service.removerPor(pecaSalva.getId());
				model.removerPor(linhaSelecionada);
				table.updateUI();
				JOptionPane.showMessageDialog(contentPane, "A Peça removida com sucesso");
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnEditar)
					.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
					.addComponent(btnExcluir)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExcluir)
						.addComponent(btnEditar))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
