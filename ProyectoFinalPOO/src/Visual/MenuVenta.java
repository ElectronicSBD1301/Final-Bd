package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import SQL.ConexionSQL;
import lógico.Combo;
import lógico.Componente;
import lógico.DiscoDuro;
import lógico.Factura;
import lógico.MicroProcesador;
import lógico.Persona;
import lógico.Ram;
import lógico.TMadre;
import lógico.TiendaComp;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MenuVenta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Dimension dim; 
	private JTextField textMonto;
	private Factura factura;
	private  Connection con;
	private  PreparedStatement fact;
	private String nombreCombo = "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MenuVenta dialog = new MenuVenta();
			//dialog.setDefaultCloseOperation(0);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MenuVenta() {
		TiendaComp.getInstance().setMisComponentes(new ArrayList<Componente>());

		String b1="";
		String b2 ="";
		String b3 ="";
		String b4 = "";
		try {
			
			Statement sql = ConexionSQL.getConexion().createStatement();
			String consulta = "select numSerie, M.nombreMarca, descripcion, precio from componente C\r\n" + 
					"inner join tipo T on T.id_tipo = C.id_tipo\r\n" + 
					"inner join marca M on M.id_marca = C.id_marca\r\n" + 
					"where T.NombreComp = 'Microprocesador' and C.estado = 'disponible'";
	//		String con2 = "SELECT nombreMarca from dbo.marca M inner join componente C on M.id_marca = C.id_marca";
			ResultSet resultado = sql.executeQuery (consulta);

			while (resultado.next()) {
				b1 = resultado.getString(1);
				b2 = resultado.getString(2);
				b3 = resultado.getString(3);
				b4 = resultado.getString(4);
				MicroProcesador aux= new MicroProcesador(b2, b1, 1, Double.valueOf(b4), null, null,
						7,b3);
				TiendaComp.getInstance().InsertarComp(aux);
			}
			
			sql.close();
			 b1="";
			 b2 ="";
			 b3 ="";
			 b4 = "";
		}	catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.toString());
		}
		
		try {
			
			Statement sql = ConexionSQL.getConexion().createStatement();
			String consulta = "select numSerie, M.nombreMarca, descripcion, precio from componente C\r\n" + 
					"inner join tipo T on T.id_tipo = C.id_tipo\r\n" + 
					"inner join marca M on M.id_marca = C.id_marca\r\n" + 
					"where T.NombreComp = 'TarjetaMadre' and C.estado = 'disponible'";
	//		String con2 = "SELECT nombreMarca from dbo.marca M inner join componente C on M.id_marca = C.id_marca";
			ResultSet resultado = sql.executeQuery (consulta);

			while (resultado.next()) {
				b1 = resultado.getString(1);
				b2 = resultado.getString(2);
				b3 = resultado.getString(3);
				b4 = resultado.getString(4);
				TMadre aux= new TMadre(b2, b1, 1, Double.valueOf(b4), null, null,
						b3,null);
				TiendaComp.getInstance().InsertarComp(aux);
			}
			
			sql.close();
			 b1="";
			 b2 ="";
			 b3 ="";
			 b4 = "";
		}	catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.toString());
		}
		
		try {
			
			Statement sql = ConexionSQL.getConexion().createStatement();
			String consulta = "select numSerie, M.nombreMarca, descripcion, precio from componente C\r\n" + 
					"inner join tipo T on T.id_tipo = C.id_tipo\r\n" + 
					"inner join marca M on M.id_marca = C.id_marca\r\n" + 
					"where T.NombreComp = 'Ram' and C.estado = 'disponible'";
	//		String con2 = "SELECT nombreMarca from dbo.marca M inner join componente C on M.id_marca = C.id_marca";
			ResultSet resultado = sql.executeQuery (consulta);

			while (resultado.next()) {
				b1 = resultado.getString(1);
				b2 = resultado.getString(2);
				b3 = resultado.getString(3);
				b4 = resultado.getString(4);
				Ram aux= new Ram(b2, b1, 1, Double.valueOf(b4), 0,
						null,b3);
				TiendaComp.getInstance().InsertarComp(aux);
			}
			
			sql.close();
			 b1="";
			 b2 ="";
			 b3 ="";
			 b4 = "";
		}	catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.toString());
		}
		
		try {
			
			Statement sql = ConexionSQL.getConexion().createStatement();
			String consulta = "select numSerie, M.nombreMarca, descripcion, precio from componente C\r\n" + 
					"inner join tipo T on T.id_tipo = C.id_tipo\r\n" + 
					"inner join marca M on M.id_marca = C.id_marca\r\n" + 
					"where T.NombreComp = 'DiscoDuro' and C.estado = 'disponible'";
	//		String con2 = "SELECT nombreMarca from dbo.marca M inner join componente C on M.id_marca = C.id_marca";
			ResultSet resultado = sql.executeQuery (consulta);

			while (resultado.next()) {
				b1 = resultado.getString(1);
				b2 = resultado.getString(2);
				b3 = resultado.getString(3);
				b4 = resultado.getString(4);
				DiscoDuro aux= new DiscoDuro(b2, b1, 1, Double.valueOf(b4), null, 0,
						null,b3);
				TiendaComp.getInstance().InsertarComp(aux);
			}
			
			sql.close();
			 b1="";
			 b2 ="";
			 b3 ="";
			 b4 = "";
		}	catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.toString());
		}
		
		//	factura = null;
		factura = new Factura ((TiendaComp.getInstance().CrearCodigoFact()), null, null, null);
		TiendaComp.getInstance().InsertarFact(factura);
		setTitle("Menú de Ventas");
		//	TiendaComp.getInstance().GenerarComponentes();
		//TiendaComp.getInstance().GenerarPersona();
		setBackground(new Color(173, 216, 230));
		setForeground(new Color(173, 216, 230));
		//		TiendaComp.getInstance().GenerarComponentes();
		setBounds(100, 100, 533, 373);
		setSize(662, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 248, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		JFrame frame = new JFrame("Ejemplo de diálogo centrado");
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		JLabel label = new JLabel("Este es un diálogo centrado");
		panel.add(label);
		frame.getContentPane().add(panel);
		JDialog dialogo = new JDialog(frame, "Diálogo centrado", true);
		dialogo.setSize(200, 100);
		dialogo.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		int x = (screenSize.width - dialogo.getWidth()) / 2;
		int y = (screenSize.height - dialogo.getHeight()) / 2;
		dialogo.setLocation(x, y);


		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel1 = new JPanel();
			panel1.setBackground(new Color(255, 255, 255));
			panel1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel1.setBounds(10, 11, 624, 32);
			contentPanel.add(panel1);
			panel1.setLayout(null);
			{
				JLabel lblMenuVentas = new JLabel("Componentes diponibles: ");
				lblMenuVentas.setBounds(245, 11, 173, 14);
				panel1.add(lblMenuVentas);
			}
		}
		{
			JPanel panel1 = new JPanel();
			panel1.setBackground(Color.WHITE);
			panel1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel1.setBounds(10, 48, 624, 465);
			contentPanel.add(panel1);
			panel1.setLayout(null);
			{
				JLabel lblNewLabel_3 = new JLabel("Memorias Ram:");
				lblNewLabel_3.setBounds(506, 11, 114, 14);
				panel1.add(lblNewLabel_3);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Microprocesadores:");
				lblNewLabel_2.setBounds(335, 11, 127, 14);
				panel1.add(lblNewLabel_2);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Discos duros:");
				lblNewLabel_1.setBounds(184, 11, 96, 14);
				panel1.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel = new JLabel("Tarjetas madre:");
				lblNewLabel.setBounds(35, 11, 96, 14);
				panel1.add(lblNewLabel);
			}

			JScrollPane scrollTmadres = new JScrollPane();
			scrollTmadres.setBounds(10, 27, 137, 180);
			panel1.add(scrollTmadres);

			JList listTmadres = new JList();
			scrollTmadres.setViewportView(listTmadres);


			listTmadres.setModel(new AbstractListModel() {
				String[] values = TiendaComp.getInstance().mMadresList();
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});

			JScrollPane scrollDuros = new JScrollPane();
			scrollDuros.setBounds(166, 27, 137, 180);
			panel1.add(scrollDuros);

			JList listDiscosDuros = new JList();
			scrollDuros.setViewportView(listDiscosDuros);

			listDiscosDuros.setModel(new AbstractListModel() {
				String[] values = TiendaComp.getInstance().mDurosList();
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});


			JScrollPane scrollmProcesadores = new JScrollPane();
			scrollmProcesadores.setBounds(325, 27, 137, 180);
			panel1.add(scrollmProcesadores);

			JList listMicroprocesadores = new JList();
			scrollmProcesadores.setViewportView(listMicroprocesadores);

			listMicroprocesadores.setModel(new AbstractListModel() {
				String[] values = TiendaComp.getInstance().mProcesadoresList();
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});


			JScrollPane scrollRam = new JScrollPane();
			scrollRam.setBounds(483, 27, 137, 180);
			panel1.add(scrollRam);

			JList listMemoriaRam = new JList();
			scrollRam.setViewportView(listMemoriaRam);

			listMemoriaRam.setModel(new AbstractListModel() {
				String[] values = TiendaComp.getInstance().mRamList();
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});

			JScrollPane scrollCarrito = new JScrollPane();
			scrollCarrito.setBounds(253, 279, 143, 170);
			panel1.add(scrollCarrito);
			JList listCarrito = new JList();
			scrollCarrito.setViewportView(listCarrito);

			textMonto = new JTextField();
			textMonto.setEditable(false);
			textMonto.setBounds(506, 429, 86, 20);
			panel1.add(textMonto);
			textMonto.setColumns(10);


			JButton btnAgregarAlCarrito = new JButton("Agregar al carrito");
			btnAgregarAlCarrito.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(listDiscosDuros.getSelectedValue()!= null) {
						listCarrito.setModel(new AbstractListModel() {
							String[] values = TiendaComp.getInstance().ComponentesEnListaCarrito(listDiscosDuros.getSelectedValue().toString());
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});
						TiendaComp.getInstance().ActualizarListComp(listDiscosDuros.getSelectedValue().toString(), TiendaComp.getInstance().getDiscosD());
						textMonto.setText(TiendaComp.getInstance().monto());
						listDiscosDuros.setModel(new AbstractListModel() {
							String[] values = TiendaComp.getInstance().mDurosList();
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});
					}

					else if(listTmadres.getSelectedValue()!= null) {
						listCarrito.setModel(new AbstractListModel() {
							String[] values = TiendaComp.getInstance().ComponentesEnListaCarrito(listTmadres.getSelectedValue().toString());
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});
						TiendaComp.getInstance().ActualizarListComp(listTmadres.getSelectedValue().toString(), TiendaComp.getInstance().getTMadres());
						textMonto.setText(TiendaComp.getInstance().monto());
						listTmadres.setModel(new AbstractListModel() {
							String[] values = TiendaComp.getInstance().mMadresList();
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});
					}
					else if(listMicroprocesadores.getSelectedValue()!= null) {
						listCarrito.setModel(new AbstractListModel() {
							String[] values = TiendaComp.getInstance().ComponentesEnListaCarrito(listMicroprocesadores.getSelectedValue().toString());
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});
						TiendaComp.getInstance().ActualizarListComp(listMicroprocesadores.getSelectedValue().toString(), TiendaComp.getInstance().getmProcesadores());
						textMonto.setText(TiendaComp.getInstance().monto());
						listMicroprocesadores.setModel(new AbstractListModel() {
							String[] values = TiendaComp.getInstance().mProcesadoresList();
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});
					}
					else if(listMemoriaRam.getSelectedValue()!= null) {
						listCarrito.setModel(new AbstractListModel() {
							String[] values = TiendaComp.getInstance().ComponentesEnListaCarrito(listMemoriaRam.getSelectedValue().toString());
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});
						TiendaComp.getInstance().ActualizarListComp(listMemoriaRam.getSelectedValue().toString(), TiendaComp.getInstance().getRams());
						textMonto.setText(TiendaComp.getInstance().monto());
						listMemoriaRam.setModel(new AbstractListModel() {
							String[] values = TiendaComp.getInstance().mRamList();
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});
					}
				}
			});
			btnAgregarAlCarrito.setBounds(130, 218, 162, 23);
			panel1.add(btnAgregarAlCarrito);
			{
				JLabel lblNewLabel_4 = new JLabel("Carrito de compra:");
				lblNewLabel_4.setBounds(269, 254, 127, 14);
				panel1.add(lblNewLabel_4);
			}


			JButton btnEliminardeCarrito = new JButton("Eliminar de carrito");
			btnEliminardeCarrito.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (TiendaComp.getInstance().DeDondeEs(listCarrito.getSelectedValue().toString())==0){
						listCarrito.setModel(new AbstractListModel() {
							String[] values = TiendaComp.getInstance().ActualizarListCarrito(listCarrito.getSelectedValue().toString());
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});

						listMemoriaRam.setModel(new AbstractListModel() {
							String[] values = TiendaComp.getInstance().mRamList();
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});	
					}
					else if (TiendaComp.getInstance().DeDondeEs(listCarrito.getSelectedValue().toString())==1){

						listCarrito.setModel(new AbstractListModel() {
							String[] values = TiendaComp.getInstance().ActualizarListCarrito(listCarrito.getSelectedValue().toString());
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});

						listMicroprocesadores.setModel(new AbstractListModel() {
							String[] values = TiendaComp.getInstance().mProcesadoresList();
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});	
					}
					else if (TiendaComp.getInstance().DeDondeEs(listCarrito.getSelectedValue().toString())==2){
						listCarrito.setModel(new AbstractListModel() {
							String[] values = TiendaComp.getInstance().ActualizarListCarrito(listCarrito.getSelectedValue().toString());
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});

						listTmadres.setModel(new AbstractListModel() {
							String[] values = TiendaComp.getInstance().mMadresList();
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});	
					}
					else if (TiendaComp.getInstance().DeDondeEs(listCarrito.getSelectedValue().toString())==3){
						listCarrito.setModel(new AbstractListModel() {
							String[] values = TiendaComp.getInstance().ActualizarListCarrito(listCarrito.getSelectedValue().toString());
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});

						listDiscosDuros.setModel(new AbstractListModel() {
							String[] values = TiendaComp.getInstance().mDurosList();
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});	
					}
					textMonto.setText(TiendaComp.getInstance().monto());
				}
			});
			btnEliminardeCarrito.setBounds(342, 218, 162, 23);
			panel1.add(btnEliminardeCarrito);

			JLabel lblNewLabel_5 = new JLabel("Precio total:");
			lblNewLabel_5.setBounds(429, 432, 81, 14);
			panel1.add(lblNewLabel_5);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Comprar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//	TiendaComp.getInstance().InsertarFact(factura);
						if(factura.getcVendidos()!=null && TiendaComp.getInstance().Carrito!=null) {
							factura.setMisComponentes(TiendaComp.getInstance().AgregarCompFact(TiendaComp.getInstance().Carrito));
							//TiendaComp.getInstance().EliminarFact(factura);
							//TiendaComp.getInstance().InsertarFact(factura);
							factura.setPersona(TiendaComp.getInstance().PersonaLogg());
							//TiendaComp.getInstance().EliminarFact(factura);
							//TiendaComp.getInstance().InsertarFact(factura);
							FacturaHaciaBD(factura);
							JOptionPane.showMessageDialog(null, "Compra Satisfactoria!", "Informacion", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							
						}
						else if (factura.getcVendidos()==null) {
							//	factura.setCodigo(TiendaComp.getInstance().CrearCodigoFact());
							factura.setPersona(TiendaComp.getInstance().PersonaLogg());
							factura.setMisComponentes(TiendaComp.getInstance().AgregarCompFact(TiendaComp.getInstance().Carrito));
							//	factura.setcVendidos(null);
							//	TiendaComp.getInstance().InsertarFact(factura);
							FacturaHaciaBD(factura);
							JOptionPane.showMessageDialog(null, "Compra Satisfactoria!", "Informacion", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							
						}
						
						
						//factura.setMisComponentes(TiendaComp.getInstance().AgregarCompFact(TiendaComp.getInstance().Carrito));
						//factura = new Factura(TiendaComp.getInstance().CrearCodigoFact(TiendaComp.getInstance().Carrito), TiendaComp.getInstance().PersonaLogg(), null, TiendaComp.getInstance().AgregarCompFact(TiendaComp.getInstance().Carrito));
						//	TiendaComp.getInstance().InsertarFact(factura);
						//	factura.setMisComponentes(TiendaComp.getInstance().AgregarCompFact(TiendaComp.getInstance().Carrito));

						//	TiendaComp.getInstance().ArreglarFact(factura, TiendaComp.getInstance().Carrito);


						TiendaComp.getInstance().setCarrito(new String[100]);	
						TiendaComp.getInstance().setDiscosD(new String[100]);	
						TiendaComp.getInstance().setmProcesadores(new String[100]);
						TiendaComp.getInstance().setRams(new String[100]);	
						TiendaComp.getInstance().setTMadres(new String[100]);	
						TiendaComp.getInstance().setMisComponentes(new ArrayList<Componente>());

					//	TiendaComp.getInstance().InsertarFact(factura);
						//System.out.println(factura.getMisComponentes());
						//System.out.println(TiendaComp.getInstance().getMisComponentes());
						//	JOptionPane.showMessageDialog(null, "Compra Satisfactoria!", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						//	dispose();
					}
				});

				JButton btnCombos = new JButton("Ver combos");
				btnCombos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						VentaCombo list = new VentaCombo(factura);
						//		TiendaComp.getInstance().InsertarFact(factura);
						list.setModal(true);
						//TiendaComp.getInstance().InsertarFact(factura);
						list.setVisible(true);
						//TiendaComp.getInstance().InsertarFact(factura);
					}
				});
				buttonPane.add(btnCombos);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					//TiendaComp.getInstance().EliminarFact(factura);

						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public void FacturaHaciaBD (Factura factura) {
		
		int id = 0;
		
		String idCom ="22";
		if(!(factura.getcVendidos() == null || factura.getcVendidos().isEmpty()))
		{
			for(Combo combo : factura.getcVendidos()) {
				idCom=combo.getIdCombo();
				
			}
		}
		String consulta = "INSERT INTO factura (costoTotal, id_usuario, id_combo) VALUES (?,?,?)";	
		try {
			
			con =  ConexionSQL.getConexion();
			
			fact = (PreparedStatement)con.prepareStatement(consulta);
			fact.setFloat(1,factura.Precio(factura));
			fact.setInt(2, Integer. parseInt(factura.getPersona().getCedula()));
			fact.setInt(3,Integer.parseInt(idCom));
			fact.execute();
		}	catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.toString());
			
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.out.println(e.toString());
			}
		}
		
		String consulta3 = "select top 1 id_factura from factura \r\n" + 
				"order by id_factura\r\n" + 
				"desc";
		try {
			con =  ConexionSQL.getConexion();
			fact = con.prepareStatement(consulta3);
			ResultSet resultado = null; 
			resultado = fact.executeQuery();
			//System.out.println(id);
			if(resultado.next()) {
				//resultado.getInt(1);
				id=resultado.getInt(1);
			}
			//fact.execute();
		}	catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.toString());
			
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.out.println(e.toString());
			}
		}
		
		String consulta2 = "INSERT INTO detalle (numSerie, id_factura) VALUES (?,?)";
		for(Componente componente : factura.getMisComponentes()) {
			try {
				con = ConexionSQL.getConexion();
				fact = con.prepareStatement(consulta2);
			//	fact.setFloat(1,factura.Precio());
				fact.setInt(1, Integer.parseInt(componente.getNumSerie()));
				fact.setInt(2,id);
				fact.execute();
				System.out.println(id);

			}	catch(SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.toString());
				
			}finally {
				try {
					con.close();
				}catch(SQLException e) {
					System.out.println(e.toString());
				}
			}
		}
	}
}
