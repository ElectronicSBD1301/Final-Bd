package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import SQL.ConexionSQL;
import lógico.Cliente;
import lógico.Combo;
import lógico.Componente;
import lógico.DiscoDuro;
import lógico.Factura;
import lógico.MicroProcesador;
import lógico.Persona;
import lógico.Ram;
import lógico.TMadre;
import lógico.TiendaComp;
import javax.swing.border.EtchedBorder;

public class ListaFacturas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private Object rows[];
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaFacturas dialog = new ListaFacturas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaFacturas() {
		Persona persona = new Persona("1", "Jose", null, null, true,
				null, null);
		TiendaComp.getInstance().getMisPersonas().add(persona);
		ClienteDesdeBDFact();
		ComboDesdeBDFact();
		FacturaDesdeBD();
		setBackground(new Color(135, 206, 235));
		setTitle("Listado de Facturas");
		setBounds(100, 100, 554, 379);
		setSize(800, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(240, 248, 255));
			panel.setBounds(0, 0, 892, 310);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBounds(10, 11, 765, 40);
				panel.add(panel_1);
				panel_1.setLayout(null);
				{
					JPanel panel_2 = new JPanel();
					panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
					panel_2.setBackground(Color.LIGHT_GRAY);
					panel_2.setBounds(-12, 11, 780, 26);
					panel_1.add(panel_2);
					panel_2.setLayout(null);
					{
						JLabel lblFacturas = new JLabel("Facturas emitidas");
						lblFacturas.setBounds(342, 7, 130, 14);
						panel_2.add(lblFacturas);
					}
				}
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 54, 765, 245);
				panel.add(scrollPane);
				{
					String[] headers = {"Factura","Comprador","Disco Duros", "Ram", "Microprocesador","Tarjetas Madre","Combos"};

					table = new JTable();
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane.setViewportView(table);

					model = new DefaultTableModel();
					model.setColumnIdentifiers(headers);
					table.setModel(model);
					{
						JPanel buttonPane = new JPanel();
						buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
						buttonPane.setBounds(10, 313, 764, 37);
						contentPanel.add(buttonPane);
						{
							JButton btnNewButton = new JButton("Datos de factura");
							btnNewButton.setBounds(516, 7, 141, 23);
							btnNewButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									DatosFactura list = new DatosFactura(textField.getText().toString());
									list.setModal(true);
									list.setVisible(true);
								}
							});
							buttonPane.setLayout(null);
							buttonPane.add(btnNewButton);
						}

						JButton btnSalir = new JButton("Salir");
						btnSalir.setBounds(689, 7, 68, 23);
						buttonPane.add(btnSalir);
						btnSalir.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								dispose();
							}
						});
						btnSalir.setEnabled(true);

						JLabel lblNewLabel = new JLabel("Codigo de factura a revisar:");
						lblNewLabel.setBounds(97, 11, 203, 14);
						buttonPane.add(lblNewLabel);

						textField = new JTextField();
						textField.setBounds(275, 8, 86, 20);
						buttonPane.add(textField);
						textField.setColumns(10);
					}

					ListaFactura();
				}
			}
		}
	}
	private void ListaFactura() {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		int ind=0;
		for (Factura factura : TiendaComp.getInstance().getMisFacturas()) {
			//if(factura!=null) {
				rows[0] = factura.getCodigo();
				rows[1] = factura.getPersona().getNombre();

				int cant1=0, cant2=0, cant3=0, cant4=0;
				table.getSelectedRowCount();
				for(Componente componente : factura.getMisComponentes()) {

					if(componente instanceof MicroProcesador) {
						cant1++;
					}
					else if(componente instanceof Ram) {
						cant2++;
					}
					else if (componente instanceof TMadre){
						cant3++;
					}
					else if (componente instanceof DiscoDuro){
						cant4++;
					}
				}
				if(!(factura.getcVendidos() == null || factura.getcVendidos().isEmpty())) {
					
					rows[2] = cant4;
					rows[3] = cant2;
					rows[4] = cant1;
					rows[5] = cant3;
					rows[6] = 1;
					//ind=0;
					
					model.addRow(rows);
				}
				else {
					rows[2] = cant4;
					rows[3] = cant2;
					rows[4] = cant1;
					rows[5] = cant3;
					rows[6] = ind;
					ind=0;
					model.addRow(rows);
				}
			
			}

		}
	

	public void FacturaDesdeBD() {
		String b1="";
		String b2 ="";
		String b3 ="";
		String b4 = "";
		
		try {
			  Connection con = null, con2=null, con3 = null, con4=null, con5=null, con6=null;
			  PreparedStatement fact, fact2, fact3, fact4, fact5, fact6;
			Statement sql = ConexionSQL.getConexion().createStatement();
			String consulta = "select id_factura, costoTotal, id_usuario, id_combo from factura";
			ResultSet resultado = sql.executeQuery (consulta);

			while (resultado.next()) {
				b1 = resultado.getString(1);
				b2 = resultado.getString(2);
				b3 = resultado.getString(3);
				b4 = resultado.getString(4);
				ArrayList<Componente> componentes = new ArrayList<Componente>();
				ArrayList<Combo> combo = new ArrayList<Combo>();
				combo.add(BuscarCombo(Integer.parseInt(b4)));
				//String codigo, Persona persona, ArrayList<Combo> cVendidos, ArrayList<Componente> misComponentes
				Factura factura = new Factura(b1, BuscarPersona(Integer.parseInt(b3)), combo, componentes);
				TiendaComp.getInstance().InsertarFact(factura);
				
				try {
					String c1 ="";
					String consulta2 = "select numSerie from detalle "+
							 "where id_factura = ?";
			
					con = ConexionSQL.getConexion();
					fact = con.prepareStatement(consulta2);
					fact.setString(1, b1);
					
					ResultSet resultado2 = fact.executeQuery();
					
					while (resultado2.next()) {
						c1 = resultado2.getString(1);

						try {
							String d1 ="";
							String d2 ="";
							String d3 ="";
							
							String consulta3 = "select M.nombreMarca, descripcion, precio from componente C\r\n" + 
									"inner join tipo T on T.id_tipo = C.id_tipo\r\n" + 
									"inner join marca M on M.id_marca = C.id_marca\r\n" + 
									"where T.NombreComp = 'Microprocesador' and C.numSerie = ?";
							con2 = ConexionSQL.getConexion();
							fact2 = con2.prepareStatement(consulta3);
							fact2.setString(1, c1);
							ResultSet resultado3 = fact2.executeQuery();

							while (resultado3.next()) {
								d1 = resultado3.getString(1);
								d2 = resultado3.getString(2);
								d3 = resultado3.getString(3);
								MicroProcesador aux= new MicroProcesador(c1, d1, 1, Double.valueOf(d3), null, null,
										7,d2);
								factura.getMisComponentes().add(aux);
							}
							//componentes = new ArrayList<Componente>();
							//con2.close();
						}	catch(SQLException ex) {
							JOptionPane.showMessageDialog(null, ex.toString());
						}finally {
							try {
								con2.close();
							}catch(SQLException e) {
								System.out.println(e.toString());
							}
						}
						
						try {
		
							String e1 ="";
							String e2 ="";
							String e3 ="";

							String consulta4 = "select M.nombreMarca, descripcion, precio from componente C\r\n" + 
									"inner join tipo T on T.id_tipo = C.id_tipo\r\n" + 
									"inner join marca M on M.id_marca = C.id_marca\r\n" + 
									"where T.NombreComp = 'DiscoDuro' and C.numSerie = ?";
							con3 = ConexionSQL.getConexion();
							fact3 = con3.prepareStatement(consulta4);
							fact3.setString(1, c1);
					//		String con2 = "SELECT nombreMarca from dbo.marca M inner join componente C on M.id_marca = C.id_marca";
							ResultSet resultado4 = fact3.executeQuery();

							while (resultado4.next()) {
								e1 = resultado4.getString(1);
								e2 = resultado4.getString(2);
								e3 = resultado4.getString(3);
								DiscoDuro aux= new DiscoDuro(c1, e1, 1, Double.valueOf(e3), null, 0,
										null,e2);
								factura.getMisComponentes().add(aux);
							}
							
						//	sql.close();
						}	catch(SQLException ex) {
							JOptionPane.showMessageDialog(null, ex.toString());
						}finally {
							try {
								con3.close();
							}catch(SQLException e) {
								System.out.println(e.toString());
							}
						}
						
						
						try {
							
							String f1 ="";
							String f2 ="";
							String f3 ="";

							String consulta5 = "select M.nombreMarca, descripcion, precio from componente C\r\n" + 
									"inner join tipo T on T.id_tipo = C.id_tipo\r\n" + 
									"inner join marca M on M.id_marca = C.id_marca\r\n" + 
									"where T.NombreComp = 'TarjetaMadre' and C.numSerie = ?";
							con4 = ConexionSQL.getConexion();
							fact4 = con4.prepareStatement(consulta5);
							fact4.setString(1, c1);
					//		String con2 = "SELECT nombreMarca from dbo.marca M inner join componente C on M.id_marca = C.id_marca";
							ResultSet resultado5 = fact4.executeQuery();

							while (resultado5.next()) {
								f1 = resultado5.getString(1);
								f2 = resultado5.getString(2);
								f3 = resultado5.getString(3);
								TMadre aux= new TMadre(c1, f1, 1, Double.valueOf(f3), null, null,
										f2,null);
								factura.getMisComponentes().add(aux);
							}
							
						//	sql.close();
						}	catch(SQLException ex) {
							JOptionPane.showMessageDialog(null, ex.toString());
						}finally {
							try {
								con4.close();
							}catch(SQLException e) {
								System.out.println(e.toString());
							}
						}
						
						
						try {
							
							String g1 ="";
							String g2 ="";
							String g3 ="";

							String consulta6 = "select M.nombreMarca, descripcion, precio from componente C\r\n" + 
									"inner join tipo T on T.id_tipo = C.id_tipo\r\n" + 
									"inner join marca M on M.id_marca = C.id_marca\r\n" + 
									"where T.NombreComp = 'Ram' and C.numSerie = ?";
							con5 = ConexionSQL.getConexion();
							fact5 = con5.prepareStatement(consulta6);
							fact5.setString(1, c1);
					//		String con2 = "SELECT nombreMarca from dbo.marca M inner join componente C on M.id_marca = C.id_marca";
							ResultSet resultado6 = fact5.executeQuery();

							while (resultado6.next()) {
								g1 = resultado6.getString(1);
								g2 = resultado6.getString(2);
								g3 = resultado6.getString(3);
								Ram aux= new Ram(c1, g1, 1, Double.valueOf(g3), 0,
										null,g2);
								factura.getMisComponentes().add(aux);
							}
							
						
						}	catch(SQLException ex) {
							JOptionPane.showMessageDialog(null, ex.toString());
						}finally {
							try {
								con5.close();
							}catch(SQLException e) {
								System.out.println(e.toString());
							}
						}
					}
				//	ResultSet resultado2 = sql.executeQuery (consulta);
					
					
				}catch(SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.toString());
				}finally {
					try {
						con.close();
					}catch(SQLException e) {
						System.out.println(e.toString());
					}
				}

				
			}
			
		}	catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.toString());
		}
	}
	
	public void ComboDesdeBDFact() {
		String b1="";
		String b2 ="";
		String b3 ="";
		String b4 = "";
		
		try {
			  Connection con = null, con2=null, con3 = null, con4=null, con5=null, con6=null;
			  PreparedStatement fact, fact2, fact3, fact4, fact5, fact6;
			Statement sql = ConexionSQL.getConexion().createStatement();
			String consulta = "select id_combo, precio, cantidad, nombreCombo from combo";
			ResultSet resultado = sql.executeQuery (consulta);
			
			while (resultado.next()) {
				b1 = resultado.getString(1);
				b2 = resultado.getString(2);
				b3 = resultado.getString(3);
				b4 = resultado.getString(4);
				
				ArrayList<Componente> componentes = new ArrayList<Componente>();
				Combo combo = new Combo(null, componentes, b4, Integer.parseInt(b3));
				
				try {
					String c1 ="";
					String consulta2 = "select numSerie from ComponenteCombo "+
							 "where id_combo = ?";
			
					con = ConexionSQL.getConexion();
					fact = con.prepareStatement(consulta2);
					fact.setString(1, b1);
					
					ResultSet resultado2 = fact.executeQuery();
					
					while (resultado2.next()) {
						c1 = resultado2.getString(1);
						if(b1!=combo.getIdCombo()) {
							combo.setIdCombo(b1);
							TiendaComp.getInstance().InsertarCombo(combo);
						}
						try {
							String d1 ="";
							String d2 ="";
							String d3 ="";
							
							String consulta3 = "select M.nombreMarca, descripcion, precio from componente C\r\n" + 
									"inner join tipo T on T.id_tipo = C.id_tipo\r\n" + 
									"inner join marca M on M.id_marca = C.id_marca\r\n" + 
									"where T.NombreComp = 'Microprocesador' and C.numSerie = ?";
							con2 = ConexionSQL.getConexion();
							fact2 = con2.prepareStatement(consulta3);
							fact2.setString(1, c1);
							ResultSet resultado3 = fact2.executeQuery();

							while (resultado3.next()) {
								d1 = resultado3.getString(1);
								d2 = resultado3.getString(2);
								d3 = resultado3.getString(3);
								MicroProcesador aux= new MicroProcesador(c1, d1, 1, Double.valueOf(d3), null, null,
										7,d2);
								combo.getLosComponentes().add(aux);
							}
							//componentes = new ArrayList<Componente>();
							//con2.close();
						}	catch(SQLException ex) {
							JOptionPane.showMessageDialog(null, ex.toString());
						}finally {
							try {
								con2.close();
							}catch(SQLException e) {
								System.out.println(e.toString());
							}
						}
						
						try {
		
							String e1 ="";
							String e2 ="";
							String e3 ="";

							String consulta4 = "select M.nombreMarca, descripcion, precio from componente C\r\n" + 
									"inner join tipo T on T.id_tipo = C.id_tipo\r\n" + 
									"inner join marca M on M.id_marca = C.id_marca\r\n" + 
									"where T.NombreComp = 'DiscoDuro' and C.numSerie = ?";
							con3 = ConexionSQL.getConexion();
							fact3 = con3.prepareStatement(consulta4);
							fact3.setString(1, c1);
					//		String con2 = "SELECT nombreMarca from dbo.marca M inner join componente C on M.id_marca = C.id_marca";
							ResultSet resultado4 = fact3.executeQuery();

							while (resultado4.next()) {
								e1 = resultado4.getString(1);
								e2 = resultado4.getString(2);
								e3 = resultado4.getString(3);
								DiscoDuro aux= new DiscoDuro(c1, e1, 1, Double.valueOf(e3), null, 0,
										null,e2);
								combo.getLosComponentes().add(aux);
							}
							
						//	sql.close();
						}	catch(SQLException ex) {
							JOptionPane.showMessageDialog(null, ex.toString());
						}finally {
							try {
								con3.close();
							}catch(SQLException e) {
								System.out.println(e.toString());
							}
						}
						
						
						try {
							
							String f1 ="";
							String f2 ="";
							String f3 ="";

							String consulta5 = "select M.nombreMarca, descripcion, precio from componente C\r\n" + 
									"inner join tipo T on T.id_tipo = C.id_tipo\r\n" + 
									"inner join marca M on M.id_marca = C.id_marca\r\n" + 
									"where T.NombreComp = 'TarjetaMadre' and C.numSerie = ?";
							con4 = ConexionSQL.getConexion();
							fact4 = con4.prepareStatement(consulta5);
							fact4.setString(1, c1);
					//		String con2 = "SELECT nombreMarca from dbo.marca M inner join componente C on M.id_marca = C.id_marca";
							ResultSet resultado5 = fact4.executeQuery();

							while (resultado5.next()) {
								f1 = resultado5.getString(1);
								f2 = resultado5.getString(2);
								f3 = resultado5.getString(3);
								TMadre aux= new TMadre(c1, f1, 1, Double.valueOf(f3), null, null,
										f2,null);
								combo.getLosComponentes().add(aux);
							}
							
						//	sql.close();
						}	catch(SQLException ex) {
							JOptionPane.showMessageDialog(null, ex.toString());
						}finally {
							try {
								con4.close();
							}catch(SQLException e) {
								System.out.println(e.toString());
							}
						}
						
						
						try {
							
							String g1 ="";
							String g2 ="";
							String g3 ="";

							String consulta6 = "select M.nombreMarca, descripcion, precio from componente C\r\n" + 
									"inner join tipo T on T.id_tipo = C.id_tipo\r\n" + 
									"inner join marca M on M.id_marca = C.id_marca\r\n" + 
									"where T.NombreComp = 'Ram' and C.numSerie = ?";
							con5 = ConexionSQL.getConexion();
							fact5 = con5.prepareStatement(consulta6);
							fact5.setString(1, c1);
					//		String con2 = "SELECT nombreMarca from dbo.marca M inner join componente C on M.id_marca = C.id_marca";
							ResultSet resultado6 = fact5.executeQuery();

							while (resultado6.next()) {
								g1 = resultado6.getString(1);
								g2 = resultado6.getString(2);
								g3 = resultado6.getString(3);
								Ram aux= new Ram(c1, g1, 1, Double.valueOf(g3), 0,
										null,g2);
								combo.getLosComponentes().add(aux);
							}
							
						
						}	catch(SQLException ex) {
							JOptionPane.showMessageDialog(null, ex.toString());
						}finally {
							try {
								con5.close();
							}catch(SQLException e) {
								System.out.println(e.toString());
							}
						}
					}
				//	ResultSet resultado2 = sql.executeQuery (consulta);
					
					
				}catch(SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.toString());
				}finally {
					try {
						con.close();
					}catch(SQLException e) {
						System.out.println(e.toString());
					}
				}

				
			}
			
		}	catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.toString());
		}
	}

	public Persona BuscarPersona(int id_usuario) {
		
		for(Persona persona : TiendaComp.getInstance().getMisPersonas()) {
			if(String.valueOf(id_usuario).equalsIgnoreCase(persona.getCedula())) {
				return persona;
			}
		}
		return null;
		
	}
	public Combo BuscarCombo(int id_usuario) {
		
		for(Combo combo : TiendaComp.getInstance().getcVendidos()) {
			if(String.valueOf(id_usuario).equalsIgnoreCase(combo.getIdCombo())) {
				return combo;
			}
		}
		return null;
		
	}
	public void ClienteDesdeBDFact(){
		
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
