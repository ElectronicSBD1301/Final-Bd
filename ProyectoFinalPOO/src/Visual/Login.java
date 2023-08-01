package Visual;

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import lógico.Cliente;
import lógico.Empleado;
import lógico.MicroProcesador;
import lógico.Persona;
import lógico.TiendaComp;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import SQL.ConexionSQL;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField usuariotxt;
	private JLabel lblContrasea;
	private JButton crearCuentabtn;
	private JPasswordField contrasenatxt;
	Connection con;
	PreparedStatement fact;
	
	/**
	 * Launch the application.
	 */
	
	
	
	public static void main(String[] args) {

		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * Create the frame.
	 */
	public Login() {
		setBackground(new Color(135, 206, 235));
		setTitle("Iniciar Sesión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 429, 367);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		lblNewLabel.setBounds(92, 35, 61, 16);
		panel.add(lblNewLabel);
		
		usuariotxt = new JTextField();
		usuariotxt.setBounds(92, 63, 225, 32);
		panel.add(usuariotxt);
		usuariotxt.setColumns(10);
		
		lblContrasea = new JLabel("Contraseña: ");
		lblContrasea.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		lblContrasea.setBounds(92, 107, 106, 16);
		panel.add(lblContrasea);
		
		JButton iniciarSesionBnt = new JButton("Iniciar Sesión");
		iniciarSesionBnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] pass = contrasenatxt.getPassword();
				String password = new String(pass);
				
				if(VerificarUsuario(usuariotxt.toString(),password)) {
	
					Principal jframe = new Principal();
					dispose();
					jframe.setVisible(true);
			
				}else {
					JOptionPane.showMessageDialog(null, "La cuenta no pertenece al sistema!", "Informacion", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		iniciarSesionBnt.setBackground(new Color(255, 255, 255));
		iniciarSesionBnt.setBounds(144, 179, 117, 29);
		panel.add(iniciarSesionBnt);
		
		JLabel lblNewLabel_1 = new JLabel("No tiene una cuenta?");
		lblNewLabel_1.setBackground(new Color(255, 255, 0));
		lblNewLabel_1.setBounds(136, 258, 144, 16);
		panel.add(lblNewLabel_1);
		
		crearCuentabtn = new JButton("Crear Cuenta");
		crearCuentabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarPersona persona = new RegistrarPersona();
				persona.setModal(true);
				persona.setVisible(true);
			}
		});
		crearCuentabtn.setBounds(144, 275, 117, 29);
		panel.add(crearCuentabtn);
		
		contrasenatxt = new JPasswordField();
		contrasenatxt.setBounds(92, 135, 225, 32);
		panel.add(contrasenatxt);
	}
	
	public boolean VerificarUsuario(String usuario, String pw) {
		
		String b1="";
		String b2="";
		String b3="";
		String b4="";
		String b5="";
		int b6=0;
		int b8 = 0;
		String b7="";
	
		try {
			con = ConexionSQL.getConexion();
			String consulta = "SELECT passwordU, id_usuario, nombre, telefono, email, id_tipoU FROM usuario where email = ? ";
			fact = con.prepareStatement(consulta);
			fact.setString(1, usuario);
		//	System.out.println("hola");
			ResultSet resultado = fact.executeQuery();
			//resulto.next();
			while (resultado.next()) {
				b7 = resultado.getString(1);
				b1 = resultado.getString(2);
				b2 = resultado.getString(3);
				b3 = resultado.getString(4);
				b4 = resultado.getString(5);
				b5 = resultado.getString(6);
				b6 = resultado.getInt(6);
			}
			System.out.println(b5);
			if(b8==0) {
			//	(String cedula, String nombre, String direccion, String telefono, boolean estado,
				//		String usuarioString, String password, String ocupacion, int cantCompras)
				Cliente aux = new Cliente (b1, b2, null, b3, true, b4, b7, "Cliente", 0);
				TiendaComp.getInstance().InsertarPersona(aux);
			//	System.out.println("jioefoiew");
			}else {
			//	String cedula, String nombre, String direccion, String telefono, boolean estado,
				//String usuarioString, String password, String cargo
				Empleado aux = new Empleado (b1, b2, null, b3, true, b4, b7, "Empleado");
				TiendaComp.getInstance().InsertarPersona(aux);
			}
				
		}	catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.toString());
			return false;
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.out.println(e.toString());
				return false;
			}
		}
		return true;
	}
}
