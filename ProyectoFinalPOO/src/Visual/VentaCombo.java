package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import SQL.ConexionSQL;
import lógico.Combo;
import lógico.Componente;
import lógico.DiscoDuro;
import lógico.Factura;
import lógico.MicroProcesador;
import lógico.Ram;
import lógico.TMadre;
import lógico.TiendaComp;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentaCombo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String [] ListaCombo = new String[100];
	ArrayList<Combo> comboFact = new ArrayList<Combo>();

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Factura factura) {
		try {
			VentaCombo dialog = new VentaCombo(factura);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentaCombo(Factura factura) {
		TiendaComp.getInstance().setcVendidos(new ArrayList<Combo>());
		ComboDesdeBD();
		setBackground(new Color(173, 216, 230));

		ListaCombo = new String[100];
		
		ComboList();
		//ListaCombo = new String[100];
		setTitle("Venta de Combos");
		setBounds(100, 100, 535, 365);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 248, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setSize(700,450);
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 53, 664, 310);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 644, 288);
		panel.add(scrollPane);
		
		JList listComp = new JList();

		listComp.setModel(new AbstractListModel() {
			String[] values = ListaCombo;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(listComp);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 11, 664, 35);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Combos disponibles");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(255, 0, 139, 35);
		panel_1.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnNewButton = new JButton("Ver descripción");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						DescripcionCombo list = new DescripcionCombo(listComp.getSelectedValue().toString());
						list.setModal(true);
						list.setVisible(true);
					
				}
			});
			//factura.setCodigo(TiendaComp.getInstance().CrearCodigoFact());
			
			//TiendaComp.getInstance().InsertarFact(factura);
			buttonPane.add(btnNewButton);
			{
				JButton okButton = new JButton("Comprar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String n = listComp.getSelectedValue().toString();
						ActualizarLista(n);
						//TiendaComp.getInstance().InsertarFact(factura);
						
						if(BuscarFactura(factura.getCodigo())) {
							factura.setcVendidos(ComboFactura(n));
						}
						else {
							factura.setcVendidos(ComboFactura(n));
						//	factura.setcVendidos(ComboFactura(n));
					//		factura.setCodigo(TiendaComp.getInstance().CrearCodigoFact());
					//		TiendaComp.getInstance().InsertarFact(factura);
							factura.setPersona(TiendaComp.getInstance().PersonaLogg());
							factura.setMisComponentes(null);
						}
						//factura.setcVendidos(ComboFactura(n));
					//	factura.setPersona(TiendaComp.getInstance().PersonaLogg());
					//	factura.setMisComponentes(null);
						JOptionPane.showMessageDialog(null, "Combo adquirido!", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						//
						scrollPane.setViewportView(listComp);
						//TiendaComp.getInstance().InsertarFact(factura);
						
					//	dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public String [] ComboList() {
		
		int ind=0;
		for(Combo combo : TiendaComp.getInstance().getcVendidos()) {
			if(combo.getCant()>0) {
				ListaCombo[ind]=combo.getNombre()+"   ||   "+String.valueOf(CantMadres(combo))+"  Tarjetas Madre"+"   |   "+String.valueOf(CantMicro(combo))+"  Microprocesadores"+"   |   "+String.valueOf(CantDiscos(combo))+"  Discos duros"+"   |   "+String.valueOf(CantRams(combo))+"  Memorias Ram"+" || "+" $"+String.valueOf(combo.Precio()-(combo.Precio()*0.1));
				ind++;
			}
		}
		
		return null;
	}
	
	public ArrayList<Combo> ComboFactura(String string) {
		
		//ArrayList<Combo> comboFact = new ArrayList<Combo>();
		for(Combo combo : TiendaComp.getInstance().getcVendidos()) {
			if(string.contains(combo.getNombre())) {
				comboFact.add(combo);
			}
		}
		return comboFact;
	}
	
	public double PrecioCombo(Combo combo) {
		
		double monto = 0;
		for(Componente componente : combo.getLosComponentes()) {
			monto+=componente.getCant();
		}
		
		return monto;
	}
	
	public int CantMadres(Combo combo) {
		
		int cant =0;
		for(Componente componente : combo.getLosComponentes()) {
			if(componente instanceof TMadre) {
				cant++;
			}
		}
		
		return cant;
	}
	
	public int CantMicro(Combo combo) {
		
		int cant =0;
		for(Componente componente : combo.getLosComponentes()) {
			if(componente instanceof MicroProcesador) {
				cant++;
			}
		}
		
		return cant;
	}
	
	public int CantRams(Combo combo) {
		
		int cant =0;
		for(Componente componente : combo.getLosComponentes()) {
			if(componente instanceof Ram) {
				cant++;
			}
		}
		
		return cant;
	}
	
	public int CantDiscos(Combo combo) {
		
		int cant =0;
		for(Componente componente : combo.getLosComponentes()) {
			if(componente instanceof DiscoDuro) {
				cant++;
			}
		}
		
		return cant;
	}
	
	public void ActualizarLista(String seleccionado) {
		
		for(Combo combo : TiendaComp.getInstance().getcVendidos()) {
			if(seleccionado.contains(combo.getNombre())) {
				combo.setCant(combo.getCant()-1);
			}
			if(combo.getCant() == 0) {
				for(int ind =0; ListaCombo[ind]!= null; ind++) {
					if(ListaCombo[ind].equalsIgnoreCase(seleccionado)) {
						for(int indice = ind; ListaCombo[indice]!=null;) {
							ListaCombo[indice]=ListaCombo[indice+1];
							//combo.setCant(0);
							//System.out.println(componente.cant);
							
						}
					}
				}
			}
		}
		return;
	}
	
	public boolean BuscarFactura(String codigo) {
		
		if(codigo == null) {
			return false;
		}
		for(Factura factura : TiendaComp.getInstance().getMisFacturas()) {
			if(codigo.equalsIgnoreCase(factura.getCodigo())) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public void ComboDesdeBD() {
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
}
