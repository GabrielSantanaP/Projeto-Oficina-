package view;

import java.awt.EventQueue;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import model.DAO;
import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Clientes extends JDialog {
	private JTextField txtPesquisar;
	private JTextField txtIdCli;
	private JTextField txtNomeCli;
	private JTextField txtFoneCli;
	private JTextField txtFone2;
	private JTextField txtEmail;
	private JTextField txtCpf;
	private JTextField txtEndereco;
	private JTextField txtCep;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes dialog = new Clientes();
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
	public Clientes() {
		setModal(true);
		setTitle("Clientes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/icones/Logo.png")));
		setModal(true);
		setBounds(150, 150, 657, 430);
		getContentPane().setLayout(null);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});
		txtPesquisar.setBounds(21, 11, 161, 25);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JLabel lblLupa = new JLabel("");
		lblLupa.setIcon(new ImageIcon(Clientes.class.getResource("/icones/pesquisar.png")));
		lblLupa.setBounds(201, 14, 46, 37);
		getContentPane().add(lblLupa);

		JLabel lblNewLabel = new JLabel("*Campos obrigat\u00F3rios");
		lblNewLabel.setBounds(355, 22, 171, 14);
		getContentPane().add(lblNewLabel);

		JLabel lbl = new JLabel("Id");
		lbl.setBounds(10, 157, 46, 14);
		getContentPane().add(lbl);

		txtIdCli = new JTextField();
		txtIdCli.setEditable(false);
		txtIdCli.setColumns(10);
		txtIdCli.setBounds(34, 154, 161, 19);
		getContentPane().add(txtIdCli);

		JLabel lblNewLabel_1 = new JLabel("*Nome");
		lblNewLabel_1.setBounds(205, 157, 46, 14);
		getContentPane().add(lblNewLabel_1);

		txtNomeCli = new JTextField();
		txtNomeCli.setColumns(10);
		txtNomeCli.setBounds(243, 154, 161, 19);
		getContentPane().add(txtNomeCli);

		JLabel lblNewLabel_2 = new JLabel("*Fone ");
		lblNewLabel_2.setBounds(414, 157, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtFoneCli = new JTextField();
		txtFoneCli.setBounds(470, 154, 112, 19);
		getContentPane().add(txtFoneCli);
		txtFoneCli.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Fone 2");
		lblNewLabel_3.setBounds(414, 199, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtFone2 = new JTextField();
		txtFone2.setColumns(10);
		txtFone2.setBounds(470, 196, 112, 19);
		getContentPane().add(txtFone2);

		JLabel lblNewLabel_4 = new JLabel("E-mail");
		lblNewLabel_4.setBounds(10, 199, 46, 14);
		getContentPane().add(lblNewLabel_4);

		txtEmail = new JTextField();
		txtEmail.setBounds(52, 198, 161, 15);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("*CPF");
		lblNewLabel_1_1.setBounds(220, 199, 46, 14);
		getContentPane().add(lblNewLabel_1_1);

		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBounds(264, 196, 112, 19);
		getContentPane().add(txtCpf);

		JLabel lblNewLabel_1_1_1 = new JLabel("*Endere\u00E7o");
		lblNewLabel_1_1_1.setBounds(10, 235, 60, 14);
		getContentPane().add(lblNewLabel_1_1_1);

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(80, 234, 203, 15);
		getContentPane().add(txtEndereco);

		JLabel lblNewLabel_1_1_2 = new JLabel("*CEP");
		lblNewLabel_1_1_2.setBounds(425, 235, 46, 14);
		getContentPane().add(lblNewLabel_1_1_2);

		txtCep = new JTextField();
		txtCep.setBounds(453, 232, 86, 20);
		getContentPane().add(txtCep);
		txtCep.setColumns(10);

		JButton btnCep = new JButton("Buscar");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnCep.setBounds(549, 231, 82, 23);
		getContentPane().add(btnCep);

		JLabel lblNewLabel_5 = new JLabel("*Numero");
		lblNewLabel_5.setBounds(293, 235, 60, 14);
		getContentPane().add(lblNewLabel_5);

		txtNumero = new JTextField();
		txtNumero.setBounds(355, 232, 60, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Complemento");
		lblNewLabel_6.setBounds(174, 271, 92, 14);
		getContentPane().add(lblNewLabel_6);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(264, 268, 164, 20);
		getContentPane().add(txtComplemento);
		txtComplemento.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("*Bairro");
		lblNewLabel_7.setBounds(21, 271, 46, 14);
		getContentPane().add(lblNewLabel_7);

		txtBairro = new JTextField();
		txtBairro.setBounds(62, 268, 105, 20);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("*UF");
		lblNewLabel_8.setBounds(443, 271, 46, 14);
		getContentPane().add(lblNewLabel_8);

		JLabel Cidade = new JLabel("*Cidade");
		Cidade.setHorizontalAlignment(SwingConstants.TRAILING);
		Cidade.setBounds(24, 311, 46, 14);
		getContentPane().add(Cidade);

		txtCidade = new JTextField();
		txtCidade.setBounds(90, 308, 86, 20);
		getContentPane().add(txtCidade);
		txtCidade.setColumns(10);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarCliente();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/icones/create.png")));
		btnAdicionar.setBounds(315, 311, 89, 69);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.setToolTipText("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarClientes();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/icones/update.png")));
		btnEditar.setBounds(419, 311, 89, 69);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirClientes();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(Clientes.class.getResource("/icones/delete.png")));
		btnExcluir.setBounds(531, 311, 89, 69);
		getContentPane().add(btnExcluir);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(21, 59, 610, 82);
		getContentPane().add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 590, 60);
		desktopPane.add(scrollPane);

		tableClientes = new JTable();
		tableClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
			}
		});
		scrollPane.setViewportView(tableClientes);

		cboUFF = new JComboBox();
		cboUFF.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUFF.setBounds(470, 267, 69, 22);
		getContentPane().add(cboUFF);

	}// fim do construtor
	// -----------------------------------------------------------------------------------------
	/**
	 * buscarCep
	 */
	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUFF.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {
						// lblStatus.setIcon(new
						// javax.swing.ImageIcon(getClass().getResource("/img/check.png")));
					} else {
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
					}
				}
			}
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	// -----------------------------------------------------------------------------------------
	private void pesquisarCliente() {
		String read = "select idcli as Id, nome as Cliente, cep, endereco as Endereço, numero as Numero, complemento as Complemento, bairro as Bairro, cidade as Cidade, uf , telefone as Telefone, cpf as Cpf, telefone2 as Telefone2, email as Email from clientes where nome like?";
		try {
			DAO dao = new DAO();
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
	// -----------------------------------------------------------------------------------------
	DAO dao = new DAO();
	private JTable tableClientes;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JComboBox cboEstado;
	private JComboBox cboUFF;
	// -----------------------------------------------------------------------------------------
	private void adicionarCliente() {
		if (txtNomeCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo nome", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtNomeCli.requestFocus();
		} else if (txtFoneCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Fone", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtFoneCli.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Bairro", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Cidade", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtCidade.requestFocus();
		}else if (cboUFF.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo UF", "Atenção", JOptionPane.WARNING_MESSAGE);
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo endereço", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Numero", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtNumero.requestFocus();
		} else {
			String create = "insert into clientes(nome,email,telefone,telefone2,cpf,cep,endereco,numero,complemento,bairro,cidade,uf) values (?,?,?,?,?,?,?,?,?,?,?,?)";
			try {

				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);

				pst.setString(1, txtNomeCli.getText());
				pst.setString(2, txtEmail.getText());
				pst.setString(3, txtFoneCli.getText());
				pst.setString(4, txtFone2.getText());
				pst.setString(5, txtCpf.getText());
				pst.setString(6, txtCep.getText());
				pst.setString(7, txtEndereco.getText());				
				pst.setString(8, txtNumero.getText());
				pst.setString(9, txtComplemento.getText());
				pst.setString(10, txtBairro.getText());
				pst.setString(11, txtCidade.getText());
				pst.setString(12, cboUFF.getSelectedItem().toString());

				int confirma = pst.executeUpdate();
				if (confirma == 1)
					;
				{
					JOptionPane.showMessageDialog(null, "Cliente Adicionado com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
				limpar();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	// -----------------------------------------------------------------------------------------
	private void setarCampos() {
		int setar = tableClientes.getSelectedRow();

		
		
		txtIdCli.setText(tableClientes.getModel().getValueAt(setar, 0).toString());
		txtNomeCli.setText(tableClientes.getModel().getValueAt(setar, 1).toString());
		txtCep.setText(tableClientes.getModel().getValueAt(setar, 2).toString());
		txtEndereco.setText(tableClientes.getModel().getValueAt(setar, 3).toString());
		txtNumero.setText(tableClientes.getModel().getValueAt(setar, 4).toString());
		txtComplemento.setText(tableClientes.getModel().getValueAt(setar, 5).toString());
		txtBairro.setText(tableClientes.getModel().getValueAt(setar, 6).toString());
		txtCidade.setText(tableClientes.getModel().getValueAt(setar, 7).toString());
		cboUFF.setSelectedItem(tableClientes.getModel().getValueAt(setar, 8).toString());
		txtFoneCli.setText(tableClientes.getModel().getValueAt(setar, 9).toString());
		txtCpf.setText(tableClientes.getModel().getValueAt(setar, 10).toString());
		txtFone2.setText(tableClientes.getModel().getValueAt(setar, 11).toString());
		txtEmail.setText(tableClientes.getModel().getValueAt(setar, 12).toString());

		
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(true);
	    btnExcluir.setEnabled(true);

	}
	// -----------------------------------------------------------------------------------------
	
	private void editarClientes() {
		if (txtNomeCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo nome", "Atenção!", JOptionPane.ERROR_MESSAGE);
			txtNomeCli.requestFocus();
		} else if (txtFoneCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Fone", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtFoneCli.requestFocus();
		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo CPF", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtCpf.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Endereço", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Numero", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtNumero.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo CEP", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtCep.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Bairro", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtBairro.requestFocus();
		} else if (cboUFF.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo UF", "Atenção", JOptionPane.ERROR_MESSAGE);
			cboUFF.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Cidade", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtCidade.requestFocus();
		} else {
			String update = "update clientes set nome = ?, email = ?, telefone = ?, telefone2 = ?, cpf = ?, cep = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, uf = ? where idcli = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				
				pst.setString(1, txtNomeCli.getText());
				pst.setString(2, txtEmail.getText());
				pst.setString(3, txtFoneCli.getText());
				pst.setString(4, txtFone2.getText());
				pst.setString(5, txtCpf.getText());
				pst.setString(6, txtCep.getText());
				pst.setString(7, txtEndereco.getText());
				pst.setString(8, txtNumero.getText());
				pst.setString(9, txtComplemento.getText());
				pst.setString(10, txtBairro.getText());
				pst.setString(11, txtCidade.getText());
				pst.setString(12, cboUFF.getSelectedItem().toString());
				pst.setString(13, txtIdCli.getText());
				
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente editado com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
				con.close();
				limpar();
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	// -----------------------------------------------------------------------------------------
	
	private void excluirClientes() {
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão desse cliente ?", "Atenção!", JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from clientes where idCli = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtIdCli.getText());
				int excluir = pst.executeUpdate();
				if (excluir == 1) {
					JOptionPane.showMessageDialog(null, "Cliente Excluido com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				pst.executeUpdate();
				con.close();
				limpar();
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "O cliente não pode ser excluido /n O cliente possui pedido em aberto");
			}
			
			catch (Exception e) {
				System.out.println(e);
				}
		}
	}
	
	// -----------------------------------------------------------------------------------------

	private void limpar() {
		txtIdCli.setText(null);
		txtNomeCli.setText(null);
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtNumero.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		txtCpf.setText(null);
		txtFoneCli.setText(null);
		txtEmail.setText(null);
		txtFone2.setText(null);
		((DefaultTableModel) tableClientes.getModel()).setRowCount(0);
		
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
	    btnExcluir.setEnabled(false);

		
	}
}