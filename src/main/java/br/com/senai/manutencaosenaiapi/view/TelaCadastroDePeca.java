package br.com.senai.manutencaosenaiapi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import br.com.senai.manutencaosenaiapi.entity.Peca;
import br.com.senai.manutencaosenaiapi.service.PecaService;
import javax.swing.JTextArea;

@Component
public class TelaCadastroDePeca extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField edtId;
	private JTextField edtDescricao;
	private JLabel lblEspecificacoes;
	private JTextArea jtaEspecificacoes;
	
	@Autowired
	private PecaService service;
	
	@Autowired
	@Lazy
	private TelaConsultaDePeca telaDeConsulta;
	
	private JTextField edtQnt;

	public TelaCadastroDePeca() {
		setTitle("Cadastro de Peça");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblId = new JLabel("ID");
		
		edtId = new JTextField();
		edtId.setEnabled(false);
		edtId.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descrição");
		
		edtDescricao = new JTextField();
		edtDescricao.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if (edtId.getText() != null && edtId.getText().length() > 0) {
						Peca pecaSalva = new Peca();
						pecaSalva.setDescricao(edtDescricao.getText());
						pecaSalva.setQtdeEmEstoque(Integer
								.parseInt(edtQnt.getText()));
						pecaSalva.setEspecificacoes(jtaEspecificacoes.getText());
						pecaSalva.setId(Integer.parseInt(edtId.getText()));
						service.alterar(pecaSalva);
						JOptionPane.showMessageDialog(
								contentPane, "A peça foi alterada com sucesso");
					}else {
						Peca novaPeca = new Peca();
						novaPeca.setDescricao(edtDescricao.getText());
						novaPeca.setQtdeEmEstoque(Integer
								.parseInt(edtQnt.getText()));
						novaPeca.setEspecificacoes(jtaEspecificacoes.getText());
						Peca pecaSalva = service.inserir(novaPeca);
						edtId.setText(pecaSalva.getId().toString());
						JOptionPane.showMessageDialog(
								contentPane, "Peça inserida com sucesso");
					}					
					
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(
							contentPane, ex.getMessage());
				}
				
			}
		});
		
		JLabel lblQnt = new JLabel("Qnt");
		
		edtQnt = new JTextField();
		edtQnt.setColumns(10);
		
		lblEspecificacoes = new JLabel("Especificações");
		
		jtaEspecificacoes = new JTextArea();
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaDeConsulta.setVisible(true);
				setVisible(false);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblEspecificacoes)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(jtaEspecificacoes, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(edtId, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblId))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(edtDescricao, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDescricao))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(edtQnt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQnt))))
					.addGap(14))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnCancelar)
					.addPreferredGap(ComponentPlacement.RELATED, 333, Short.MAX_VALUE)
					.addComponent(btnSalvar)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(lblDescricao)
						.addComponent(lblQnt))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(edtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(edtDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(edtQnt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblEspecificacoes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jtaEspecificacoes, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnSalvar)))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void colocarEmEdicao(Peca pecaSalva) {
		edtId.setText(pecaSalva.getId().toString());
		edtDescricao.setText(pecaSalva.getDescricao());
		jtaEspecificacoes.setText(pecaSalva.getEspecificacoes());
		edtQnt.setText(pecaSalva.getQtdeEmEstoque().toString());
	}
	
	public void colocarEmModoInclusao() {
		edtId.setText("");
		edtDescricao.setText("");
		jtaEspecificacoes.setText("");
		edtQnt.setText("");
		setVisible(true);
	}
}
