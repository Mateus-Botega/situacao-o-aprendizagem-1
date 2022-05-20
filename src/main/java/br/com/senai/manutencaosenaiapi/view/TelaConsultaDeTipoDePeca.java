package br.com.senai.manutencaosenaiapi.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumnModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.senai.manutencaosenaiapi.entity.TipoDePeca;
import br.com.senai.manutencaosenaiapi.service.TiposDePecaService;
import br.com.senai.manutencaosenaiapi.view.table.TipoDePecaTableModel;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

@Component
public class TelaConsultaDeTipoDePeca extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtParametro;
	private JTable table;
	
	@Autowired
	private TelaCadastroDeTipoDePeca telaDeCadastroDeTipoDePeca;
	
	@Autowired
	private TiposDePecaService service;

	public TelaConsultaDeTipoDePeca() {
		setTitle("Pequisa de Tipo de Peça");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblParametro = new JLabel("Parametro");
		
		txtParametro = new JTextField();
		txtParametro.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<TipoDePeca> tiposEncontrados = service.listarPor(txtParametro.getText());
					TipoDePecaTableModel model = new TipoDePecaTableModel(tiposEncontrados);
					table.setModel(model);
					TableColumnModel cm = table.getColumnModel();
					cm.getColumn(0).setPreferredWidth(50);
					cm.getColumn(1).setPreferredWidth(500);
					table.updateUI();
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(btnPesquisar, ex.getMessage());
				}
			}
		});
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaDeCadastroDeTipoDePeca.setVisible(true);
				telaDeCadastroDeTipoDePeca.setLocationRelativeTo(null);
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "A\u00E7\u00F5es para a linha selecionada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int linhaSelecionada = table.getSelectedRow();
					TipoDePecaTableModel model = (TipoDePecaTableModel) table.getModel();
					TipoDePeca tipoSelecionado = model.getPor(linhaSelecionada);
					telaDeCadastroDeTipoDePeca.colocarEmEdicao(tipoSelecionado);
					telaDeCadastroDeTipoDePeca.setVisible(true);
					setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(btnEditar, "Por favor, selecione um tipo para editar");
				}
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int linhaSelecionada = table.getSelectedRow();
					TipoDePecaTableModel model = (TipoDePecaTableModel) table.getModel();
					TipoDePeca tipoSelecionado = model.getPor(linhaSelecionada);
					service.removerPor(tipoSelecionado.getId());
					JOptionPane.showMessageDialog(btnExcluir, "Tipo de peça excluida com sucesso!");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(btnEditar, "Selecione um tipo para editar");
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnEditar)
					.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
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
					.addContainerGap(39, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblParametro, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(table, GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtParametro, GroupLayout.PREFERRED_SIZE, 541, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 440, Short.MAX_VALUE)
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)))
							.addGap(209)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblParametro)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtParametro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar)
						.addComponent(btnAdicionar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnVoltar)
							.addGap(34))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
