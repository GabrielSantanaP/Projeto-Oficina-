package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.DAO;

public class Inicio extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private AbstractButton lblData;
	private JLabel lblData_1;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicio() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				setarData();
				status();
			}

		});

		setFont(new Font("Dialog", Font.BOLD, 12));
		setForeground(SystemColor.inactiveCaption);
		setBackground(SystemColor.window);
		setTitle("Senacar\u00B4s - Sistemas de gest\u00E3o");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/icones/Logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 150, 704, 417);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnClientes = new JButton("");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes clientes = new Clientes();
				clientes.setVisible(true);
			}
		});
		btnClientes.setIcon(new ImageIcon(Inicio.class.getResource("/icones/cadastro-clientes.png")));
		btnClientes.setToolTipText("Clientes");
		btnClientes.setBounds(22, 25, 128, 128);
		contentPane.add(btnClientes);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.windowBorder);
		panel.setBounds(0, 327, 698, 51);
		contentPane.add(panel);
		panel.setLayout(null);

		lblData_1 = new JLabel("");
		lblData_1.setBounds(404, 11, 239, 29);
		panel.add(lblData_1);
		
		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Inicio.class.getResource("/icones/dberror.png")));
		lblStatus.setBounds(10, 11, 32, 32);
		panel.add(lblStatus);

		JButton btnOS = new JButton("");
		btnOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Os os = new Os();
				os.setVisible(true);
			}
		});
		btnOS.setIcon(new ImageIcon(Inicio.class.getResource("/icones/OS.png")));
		btnOS.setToolTipText("Ordem de Servico");
		btnOS.setBounds(184, 25, 128, 128);
		contentPane.add(btnOS);

		JButton btnSobre = new JButton("");
		btnSobre.setBorder(null);
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setBackground(SystemColor.inactiveCaption);
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnSobre.setIcon(new ImageIcon(Inicio.class.getResource("/icones/Sobre.png")));
		btnSobre.setToolTipText("Sobre");
		btnSobre.setBounds(550, 188, 128, 128);
		contentPane.add(btnSobre);

		JButton btnRelatorio = new JButton("");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pesquisa pesquisa = new Pesquisa();
				pesquisa.setVisible(true);
			}
		});
		btnRelatorio.setIcon(new ImageIcon(Inicio.class.getResource("/icones/lista.png")));
		btnRelatorio.setToolTipText("Relatorios");
		btnRelatorio.setBounds(22, 188, 128, 128);
		contentPane.add(btnRelatorio);

		lblNewLabel = new JLabel("Senacar's");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setBounds(560, 25, 118, 36);
		contentPane.add(lblNewLabel);
	}

	private void setarData() {

		Date dataLabel = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);

		lblData_1.setText(formatador.format(dataLabel));
	}
	private void status() {
		DAO dao = new DAO(); // Criar um objeto de nome DAO para acessar a classe DAO
		try {
			// Abrir a conexão com o Banco
			Connection con = dao.conectar();
			// Exibe o retorno da conexão
			System.out.println("Banco Conectado");
			// mudando o icone caso a conexão seja bem sucedida
			if (con == null) {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dberror.png")));
			} else {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dbok.png")));
			}

			// IMPORTANTE! Sempre encerrar a conexão
			con.close();
		} catch (com.mysql.cj.jdbc.exceptions.CommunicationsException ex) {
			JOptionPane.showMessageDialog(null, "Servidor Indisponivel");
		}

		catch (Exception e) {
			System.out.println(e);
		}
	}
}
