package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Os extends JDialog {
	private JTextField txtPesquisar;
	private JTextField txtId;
	private JTable tableClientes;
	private String tipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Os dialog = new Os();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Os() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Os.class.getResource("/icones/Logo.png")));
		setTitle("Ordem de Servi\u00E7o");
		setBounds(150, 150, 731, 537);
		getContentPane().setLayout(null);
		
		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});
		txtPesquisar.setBounds(358, 11, 165, 20);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);
		
		btnCriar = new JButton("");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarOs();
			}
		});
		btnCriar.setIcon(new ImageIcon(Os.class.getResource("/icones/create.png")));
		btnCriar.setToolTipText("Criar");
		btnCriar.setBounds(248, 406, 119, 81);
		getContentPane().add(btnCriar);
		
		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarOs();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setIcon(new ImageIcon(Os.class.getResource("/icones/update.png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.setBounds(377, 406, 119, 81);
		getContentPane().add(btnEditar);
		
		btnCancelar = new JButton("");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirOs();
			}
		});
		btnCancelar.setEnabled(false);
		btnCancelar.setIcon(new ImageIcon(Os.class.getResource("/icones/delete.png")));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setBounds(506, 406, 119, 81);
		getContentPane().add(btnCancelar);
		
		btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarOs();
			}
		});
		btnPesquisar.setToolTipText("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon(Os.class.getResource("/icones/read.png")));
		btnPesquisar.setBounds(119, 406, 119, 81);
		getContentPane().add(btnPesquisar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Os.class.getResource("/icones/pesquisar.png")));
		lblNewLabel.setBounds(533, 11, 46, 33);
		getContentPane().add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(606, 11, 99, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Id");
		lblNewLabel_1.setBounds(579, 14, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(358, 55, 347, 110);
		getContentPane().add(desktopPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 327, 88);
		desktopPane.add(scrollPane);
		
		tableClientes = new JTable();
		tableClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pesquisarId();
			}
		});
		scrollPane.setViewportView(tableClientes);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Os", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 14, 341, 147);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		cboStatus = new JComboBox();
		cboStatus.setModel(new DefaultComboBoxModel(new String[] {"", "Aguardando aprova\u00E7\u00E3o", "Aguardando Pagamento", "Em andamento", "Retirado"}));
		cboStatus.setBounds(83, 98, 153, 22);
		panel.add(cboStatus);
		
		txtOs = new JTextField();
		txtOs.setEditable(false);
		txtOs.setBounds(10, 30, 106, 20);
		panel.add(txtOs);
		txtOs.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Data");
		lblNewLabel_2.setBounds(133, 33, 46, 14);
		panel.add(lblNewLabel_2);
		
		txtData = new JTextField();
		txtData.setBounds(173, 30, 113, 20);
		panel.add(txtData);
		txtData.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Status");
		lblNewLabel_3.setBounds(119, 69, 46, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Modelo do Carro ");
		lblNewLabel_5.setBounds(371, 220, 99, 14);
		getContentPane().add(lblNewLabel_5);
		
		txtModelo = new JTextField();
		txtModelo.setBounds(472, 217, 219, 20);
		getContentPane().add(txtModelo);
		txtModelo.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Placa");
		lblNewLabel_6.setBounds(404, 293, 46, 14);
		getContentPane().add(lblNewLabel_6);
		
		txtPlaca = new JTextField();
		txtPlaca.setBounds(472, 290, 219, 20);
		getContentPane().add(txtPlaca);
		txtPlaca.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Mecanico");
		lblNewLabel_7.setBounds(404, 381, 64, 14);
		getContentPane().add(lblNewLabel_7);
		
		txtMecanico = new JTextField();
		txtMecanico.setBounds(481, 375, 224, 20);
		getContentPane().add(txtMecanico);
		txtMecanico.setColumns(10);
		
		txtObs = new JTextField();
		txtObs.setBounds(221, 339, 155, 56);
		getContentPane().add(txtObs);
		txtObs.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Observa\u00E7\u00E3o");
		lblNewLabel_8.setBounds(254, 315, 157, 14);
		getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setBorder(null);
		lblNewLabel_9.setIcon(new ImageIcon(Os.class.getResource("/icones/png-transparent-car-automobile-repair-shop-maintenance-auto-mechanic-computer-icons-car.png")));
		lblNewLabel_9.setBounds(22, 248, 219, 147);
		getContentPane().add(lblNewLabel_9);
		
		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Os.class.getResource("/icones/print.png")));
		btnNewButton.setToolTipText("Imprimir");
		btnNewButton.setBounds(12, 406, 105, 81);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("Servi\u00E7os");
		lblNewLabel_4.setBounds(377, 176, 79, 14);
		getContentPane().add(lblNewLabel_4);
		
		txtServicos = new JTextField();
		txtServicos.setBounds(462, 173, 229, 20);
		getContentPane().add(txtServicos);
		txtServicos.setColumns(10);
		
		txtValor = new JTextField();
		txtValor.setBounds(472, 330, 219, 20);
		getContentPane().add(txtValor);
		txtValor.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Valor");
		lblNewLabel_10.setBounds(404, 330, 46, 14);
		getContentPane().add(lblNewLabel_10);
		
		txtAno = new JTextField();
		txtAno.setBounds(472, 259, 221, 20);
		getContentPane().add(txtAno);
		txtAno.setColumns(10);
		
		lblNewLabel_11 = new JLabel("Ano");
		lblNewLabel_11.setBounds(404, 262, 46, 14);
		getContentPane().add(lblNewLabel_11);
		
		txtDefeito = new JTextField();
		txtDefeito.setBounds(166, 172, 141, 20);
		getContentPane().add(txtDefeito);
		txtDefeito.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Defeito");
		lblNewLabel_12.setBounds(107, 172, 46, 14);
		getContentPane().add(lblNewLabel_12);

	}

	DAO dao = new DAO();
	private JTextField txtOs;
	private JTextField txtData;
	private JTextField txtModelo;
	private JTextField txtPlaca;
	private JTextField txtMecanico;
	private JTextField txtObs;
	private JButton btnPesquisar;
	private JButton btnNewButton;
	private JButton btnCriar;
	private JButton btnEditar;
	private JButton btnCancelar;
	private JTextField txtServicos;
	private JComboBox cboStatus;
	private JTextField txtValor;
	private JTextField txtAno;
	private JLabel lblNewLabel_11;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtDefeito;
	
	private void pesquisarCliente() {
		String read = "select idcli as ID, nome as clientes, telefone as Telefone, cidade as Cidade, uf from clientes where nome like ?";
		try {

			// abrir a conexao com o banco
			Connection con = dao.conectar();
			// preparar a query(instrucao sql) para pesquisar no banco
			PreparedStatement pst = con.prepareStatement(read);
			// substituir o parametro(?) Atencao ao % para completar a query
			pst.setString(1, txtPesquisar.getText() + "%");
			// obter os dados do banco (resultado)
			ResultSet rs = pst.executeQuery();
			// popular(preencher) a tabela com os dados do banco
			tableClientes.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	// --------------------------------------------------------------------------------------------
	
	private void pesquisarOs() {
		String numOs = JOptionPane.showInputDialog("Numero da OS");
		String read = "select * from servico where os = " + numOs;
		
		btnEditar.setEnabled(true);
		btnCancelar.setEnabled(true);
		btnCriar.setEnabled(false);
		
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				txtOs.setText(rs.getString(1));
				txtModelo.setText(rs.getString(2));
				txtServicos.setText(rs.getString(3));
				txtValor.setText(rs.getString(4));
				cboStatus.setSelectedItem(rs.getString(5));
				txtData.setText(rs.getString(6));
				txtDefeito.setText(rs.getString(7));
				txtAno.setText(rs.getString(8));
				txtPlaca.setText(rs.getString(9));
				txtMecanico.setText(rs.getString(10));
				txtObs.setText(rs.getString(11));
				//if (rs.getString(11).equals("Serviço")) {
					//chkOrcamento.setSelected(true);
				//} else {
					//chkServicos.setSelected(true);	
				txtId.setText(rs.getString(12));
				
				
			} else {
					JOptionPane.showMessageDialog(null, "Numero de OS não existe", "Atenção", JOptionPane.ERROR_MESSAGE);
				
			}
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	// --------------------------------------------------------------------------------------------
	private void adicionarOs() {
		if (cboStatus.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Status", "Atenção", JOptionPane.ERROR_MESSAGE);
			cboStatus.requestFocus();
	} else if (txtServicos.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Preencha o campo Serviços", "Atenção", JOptionPane.ERROR_MESSAGE);
		txtServicos.requestFocus();
	} else if (txtModelo.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Preencha o Modelo do Carro","Atenção", JOptionPane.ERROR_MESSAGE);
		txtModelo.requestFocus();
	} else if (txtAno.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Preencha o Ano do Carro", "Atenção", JOptionPane.ERROR_MESSAGE);
		txtAno.requestFocus();
	} else if (txtPlaca.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Preencha a Placa do Carro", "Atenção", JOptionPane.ERROR_MESSAGE);
		txtPlaca.requestFocus();
	} else if (txtValor.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null,"Preencha o Valor do Serviço","Atenção", JOptionPane.ERROR_MESSAGE);
		txtValor.requestFocus();
	} else {
		String create = "insert into servico (Modecar,servico,statusos,datsaid,defeito,valor,ano,placa,mecanico,obs,idcli) values (?,?,?,?,?,?,?,?,?,?,?)";
	try {
		
		Connection con = dao.conectar();
		PreparedStatement pst = con.prepareStatement(create);
		
		pst.setString(1, txtModelo.getText());
		pst.setString(2, txtServicos.getText());
		pst.setString(3, cboStatus.getSelectedItem().toString());
		pst.setString(4, txtData.getText());
		pst.setString(5, txtDefeito.getText());
		pst.setString(6, txtValor.getText());
		pst.setString(7, txtAno.getText());
		pst.setString(8, txtPlaca.getText());
		pst.setString(9, txtMecanico.getText());
		pst.setString(10, txtObs.getText());
		pst.setString(11, txtId.getText());
		
		
		int confirma = pst.executeUpdate();
		if (confirma == 1) {
			JOptionPane.showMessageDialog(null, "Os adicionado com sucesso", "Mensagem",
					JOptionPane.INFORMATION_MESSAGE);
		}
		con.close(); 
		limpar();
	} catch (Exception e) {
		System.out.println(e);
	}
	}
		
}
	private void pesquisarId() {
		int setar = tableClientes.getSelectedRow();
		
		txtId.setText(tableClientes.getModel().getValueAt(setar, 0).toString());
	}
	// --------------------------------------------------------------------------------------------
	private void editarOs() {
		if (cboStatus.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Status", "Atenção", JOptionPane.ERROR_MESSAGE);
			cboStatus.requestFocus();
	} else if (txtServicos.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Preencha o campo Serviços", "Atenção", JOptionPane.ERROR_MESSAGE);
		txtServicos.requestFocus();
	} else if (txtModelo.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Preencha o Modelo do Carro","Atenção", JOptionPane.ERROR_MESSAGE);
		txtModelo.requestFocus();
	} else if (txtAno.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Preencha o Ano do Carro", "Atenção", JOptionPane.ERROR_MESSAGE);
		txtAno.requestFocus();
	} else if (txtPlaca.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Preencha a Placa do Carro", "Atenção", JOptionPane.ERROR_MESSAGE);
		txtPlaca.requestFocus();
	} else if (txtValor.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null,"Preencha o Valor do Serviço","Atenção", JOptionPane.ERROR_MESSAGE);
		txtValor.requestFocus();
	} else {
		String update = "update servico set Modecar = ?, servico = ?, valor = ?, statusos = ?,defeito = ?, datsaid = ?, ano = ?, placa = ?, mecanico = ?, obs = ? where os = ?";
		
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(update);
			
			btnEditar.setEnabled(true);
			btnCancelar.setEnabled(true);
			btnCriar.setEnabled(false);
			
			pst.setString(1, txtModelo.getText());
			pst.setString(2, txtServicos.getText());
			pst.setString(3, txtValor.getText());
			pst.setString(4, cboStatus.getSelectedItem().toString());
			pst.setString(5, txtDefeito.getText());
			pst.setString(6, txtData.getText());
			pst.setString(7, txtAno.getText());
			pst.setString(8, txtPlaca.getText());
			pst.setString(9, txtMecanico.getText());
			pst.setString(10, txtObs.getText());
			pst.setString(11, txtOs.getText());
	
			
			int confirma = pst.executeUpdate();
			if (confirma == 1) {
				JOptionPane.showMessageDialog(null, "Os editado com sucesso", "Mensagem",
						JOptionPane.INFORMATION_MESSAGE);
			}
			con.close();
			limpar();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	}
	// ------------------------------------------------------------------------------------
	private void excluirOs() {
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão dessa Ordem de Serviço ?", "Atenção!", JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from servico where os = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				
				pst.setString(1, txtOs.getText());
				int excluir = pst.executeUpdate();
				if (excluir == 1) {
					JOptionPane.showMessageDialog(null, "Ordem de Serviço Excluido com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
				limpar();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	// ------------------------------------------------------------------------------------
	private void limpar() {
		txtId.setText(null);
		buttonGroup.clearSelection();
		txtServicos.setText(null);
		txtValor.setText(null);
		txtMecanico.setText(null);
		cboStatus.setSelectedItem(null);
		txtPlaca.setText(null);
		txtAno.setText(null);
		txtObs.setText(null);
		//txt.setText(null);
		txtOs.setText(null);
		txtData.setText(null);
		((DefaultTableModel) tableClientes.getModel()).setRowCount(0);
		
		btnCriar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnCancelar.setEnabled(false);
	}
}	
