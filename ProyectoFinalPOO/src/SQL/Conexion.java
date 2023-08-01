package SQL;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.org.glassfish.external.statistics.annotations.Reset;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Conexion extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conexion frame = new Conexion();
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
	public Conexion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Conectar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bases="";
				String base ="";
				try {
					Statement sql = ConexionSQL.getConexion().createStatement();
					String consulta = "SELECT * FROM dbo.componente";
					ResultSet resultado = sql.executeQuery (consulta);
					while (resultado.next()) {
						bases += resultado.getString(1) + "\n";
					}

					JOptionPane.showMessageDialog(null, bases);
				}	catch(SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.toString());
				}
			}
		});
		contentPane.add(btnNewButton, BorderLayout.CENTER);
	}

}
