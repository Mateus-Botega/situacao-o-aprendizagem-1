package br.com.senai.manutencaosenaiapi.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.senai.manutencaosenaiapi.entity.TipoDePeca;
import br.com.senai.manutencaosenaiapi.service.TiposDePecaService;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class TelaCadastroDeTipoDePeca extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtDescricao;
	
	@Autowired
	private TiposDePecaService service;

	public TelaCadastroDeTipoDePeca() {
		setTitle("Cadastro de tipos de peças");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblId = new JLabel("Id");
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descrição");
		
		txtDescricao = new JTextField();
		txtDescricao.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					if(txtId.getText() != null && txtId.getText().length() > 0) {
						TipoDePeca tipoDePeca = new TipoDePeca();
						tipoDePeca.setDescricao(txtDescricao.getText());
						tipoDePeca.setId(Integer.parseInt(txtId.getText()));
						service.alterar(tipoDePeca);
						JOptionPane.showMessageDialog(btnSalvar, "Tipo de peça alterada com sucesso!");
					}else {
						TipoDePeca tipoDePeca = new TipoDePeca();
						tipoDePeca.setDescricao(txtDescricao.getText());
						TipoDePeca tipoSalvo = service.inserir(tipoDePeca);
						JOptionPane.showMessageDialog(btnSalvar, "Tipo de peça salva com sucesso!");
						txtId.setText(tipoSalvo.getId().toString());
					}
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(btnSalvar, ex.getMessage());
				}
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				limpar();
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
							.addGap(25)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDescricao, GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton_1)
							.addPreferredGap(ComponentPlacement.RELATED, 505, Short.MAX_VALUE)
							.addComponent(btnSalvar)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(lblDescricao))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnNewButton_1))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	public void colocarEmEdicao(TipoDePeca tipoSelecionado) {
		txtId.setText(tipoSelecionado.getId().toString());
		txtDescricao.setText(tipoSelecionado.getDescricao());		
	}
	
	public void limpar() {
		txtId.setText(null);
		txtDescricao.setText(null);		
	}
}
