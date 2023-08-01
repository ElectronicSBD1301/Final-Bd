package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import SQL.ConexionSQL;
import lógico.Cliente;
import lógico.Componente;
import lógico.MicroProcesador;
import lógico.Persona;
import lógico.TiendaComp;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class ListadoCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private Object rows[];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoCliente dialog = new ListadoCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoCliente() {
		ClienteDesdeBD();
		setTitle("Listado de Clientes");
		setBackground(new Color(173, 216, 230));
		setForeground(new Color(173, 216, 230));;
		setBounds(100, 100, 668, 383);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					
					String[] cabecera = {"Cédula", "Nombre","teléfono", "Usuario"};
					table = new JTable();
					scrollPane.setViewportView(table);
					model= new DefaultTableModel();
					model.setColumnIdentifiers(cabecera);
					table.setModel(model);
					loadCliente();
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadCliente();
		TiendaComp.getInstance().setMisPersonas(new ArrayList <Persona>());
	}
	private void loadCliente() {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		
		for (Persona aux : TiendaComp.getInstance().getMisPersonas()) {
			
			if(aux instanceof Cliente) {
				rows[0]= aux.getCedula();
				rows[1]= aux.getNombre();
				rows[2]= aux.getTelefono();
				rows[3]= aux.getUsuarioString();				
				model.addRow(rows);
			}
			
			
		}
		

	}
	
	public void ClienteDesdeBD(){
		
		String b1="";
		String b2 ="";
		String b3 ="";
		String b4 = "";
		String b5 = "";
		try {
			
			Statement sql = ConexionSQL.getConexion().createStatement();
			String consulta = "select id_usuario, nombre, telefono, email, passwordU from usuario \n" + 
					"where id_tipoU = 0";
	
			ResultSet resultado = sql.executeQuery (consulta);

			while (resultado.next()) {
				b1 = resultado.getString(1);
				b2 = resultado.getString(2);
				b3 = resultado.getString(3);
				b4 = resultado.getString(4);
				b5 = resultado.getString(5);
				Cliente aux = new Cliente (b1, b2, null, b3, false, b4, b5, "Cliente", 0);
				TiendaComp.getInstance().InsertarPersona(aux);
			}
			
			sql.close();
		}	catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.toString());
		}
		
	}
	

}
