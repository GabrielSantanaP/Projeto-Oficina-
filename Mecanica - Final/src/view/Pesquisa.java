package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JDialog;
import javax.swing.JTextField;

import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Pesquisa extends JDialog {
	private JTextField txtPesquisa;
	private JTable tableCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pesquisa dialog = new Pesquisa();
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
	public Pesquisa() {
		setModal(true);
		setBounds(150, 150, 699, 373);
		getContentPane().setLayout(null);
		
		txtPesquisa = new JTextField();
		txtPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});
		txtPesquisa.setBounds(24, 24, 204, 20);
		getContentPane().add(txtPesquisa);
		txtPesquisa.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Pesquisa.class.getResource("/icones/pesquisar.png")));
		lblNewLabel.setBounds(238, 11, 46, 48);
		getContentPane().add(lblNewLabel);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(24, 87, 649, 236);
		getContentPane().add(desktopPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 629, 214);
		desktopPane.add(scrollPane);
		
		tableCliente = new JTable();
		scrollPane.setViewportView(tableCliente);
		
		JLabel lblNewLabel_1 = new JLabel("Pesquisa Avan\u00E7ada de Clientes");
		lblNewLabel_1.setBounds(356, 27, 243, 14);
		getContentPane().add(lblNewLabel_1);

	}
	
	private void pesquisarCliente() {
		String read = "select idcli as Id, nome as Cliente, cep, endereco as Endereço, numero as Numero, complemento as Complemento, bairro as Bairro, cidade as Cidade, uf , telefone as Telefone, cpf as Cpf, telefone2 as Telefone2, email as Email from clientes where nome like?";
		try {
			DAO dao = new DAO();
			// abrir a conexao com o banco
			Connection con = dao.conectar();
			// preparar a query(instrucao sql) para pesquisar no banco
			PreparedStatement pst = con.prepareStatement(read);
			// substituir o parametro(?) Atencao ao % para completar a query
			pst.setString(1, txtPesquisa.getText() + "%");
			// obter os dados do banco (resultado)
			ResultSet rs = pst.executeQuery();
			// popular(preencher) a tabela com os dados do banco
			tableCliente.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
