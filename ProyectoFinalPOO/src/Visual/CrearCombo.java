package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
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

public class CrearCombo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textMonto;
	private JTextField textNombre;
	private JTextField textCantidad;
	Connection con;
	PreparedStatement fact;
	PreparedStatement fact2;
	/**
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearCombo dialog = new CrearCombo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearCombo() {
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
		
		
		
		setTitle("Creación de combo");
		
	//	TiendaComp.getInstance().GenerarComponentes();
		TiendaComp.getInstance().GenerarPersona();
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
			panel1.setBounds(10, 11, 624, 38);
			contentPanel.add(panel1);
			panel1.setLayout(null);
			
			JLabel lblNewLabel_6 = new JLabel("Ingrese el nombre del combo: ");
			lblNewLabel_6.setBounds(10, 11, 195, 14);
			panel1.add(lblNewLabel_6);
			
			textNombre = new JTextField();
			textNombre.setBounds(193, 11, 148, 20);
			panel1.add(textNombre);
			textNombre.setColumns(10);
			
			JLabel lblCantidadACrear = new JLabel("Cantidad a crear:");
			lblCantidadACrear.setBounds(386, 11, 195, 14);
			panel1.add(lblCantidadACrear);
			
			textCantidad = new JTextField();
			textCantidad.setColumns(10);
			textCantidad.setBounds(496, 11, 29, 20);
			panel1.add(textCantidad);
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
				lblNewLabel_3.setBounds(506, 19, 114, 14);
				panel1.add(lblNewLabel_3);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Microprocesadores:");
				lblNewLabel_2.setBounds(335, 19, 127, 14);
				panel1.add(lblNewLabel_2);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Discos duros:");
				lblNewLabel_1.setBounds(185, 19, 96, 14);
				panel1.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel = new JLabel("Tarjetas madre:");
				lblNewLabel.setBounds(35, 19, 96, 14);
				panel1.add(lblNewLabel);
			}
		
			JScrollPane scrollTmadres = new JScrollPane();
			scrollTmadres.setBounds(10, 44, 137, 180);
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
			scrollDuros.setBounds(165, 44, 137, 180);
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
			scrollmProcesadores.setBounds(325, 44, 137, 180);
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
			scrollRam.setBounds(483, 44, 137, 180);
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
			scrollCarrito.setRowHeaderView(listCarrito);
			
			textMonto = new JTextField();
			textMonto.setEditable(false);
			textMonto.setBounds(506, 429, 86, 20);
			panel1.add(textMonto);
			textMonto.setColumns(10);
		
			
			JButton btnAgregarAlCarrito = new JButton("Agregar al combo");
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
			btnAgregarAlCarrito.setBounds(119, 235, 162, 23);
			panel1.add(btnAgregarAlCarrito);
			{
				JLabel lblNewLabel_4 = new JLabel("Combo:");
				lblNewLabel_4.setBounds(296, 254, 127, 14);
				panel1.add(lblNewLabel_4);
			}
			
			
			JButton btnEliminardeCarrito = new JButton("Eliminar del combo");
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
			btnEliminardeCarrito.setBounds(348, 235, 162, 23);
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
				JButton okButton = new JButton("Crear");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(TiendaComp.getInstance().ComboValido(Integer.valueOf(textCantidad.getText().toString()), TiendaComp.getInstance().getCarrito())) {
							Combo combo = new Combo (TiendaComp.getInstance().CrearCodigoCombo(),TiendaComp.getInstance().AgregarCompCombo(TiendaComp.getInstance().getCarrito()), textNombre.getText().toString(), Integer.valueOf(textCantidad.getText().toString()));
							TiendaComp.getInstance().InsertarCombo(combo);
							ComboHaciaBD(combo);
							JOptionPane.showMessageDialog(null, "Combo creado!", "Informacion", JOptionPane.INFORMATION_MESSAGE);
							TiendaComp.getInstance().setCarrito(new String[100]);	
							TiendaComp.getInstance().setDiscosD(new String[100]);	
							TiendaComp.getInstance().setmProcesadores(new String[100]);
							TiendaComp.getInstance().setRams(new String[100]);	
							TiendaComp.getInstance().setTMadres(new String[100]);
							dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "Combo Invalido, no hay componentes suficientes!", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						}
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
						TiendaComp.getInstance().setCarrito(new String[100]);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			TiendaComp.getInstance().setCarrito(new String[100]);	
			TiendaComp.getInstance().setDiscosD(new String[100]);	
			TiendaComp.getInstance().setmProcesadores(new String[100]);
			TiendaComp.getInstance().setRams(new String[100]);	
			TiendaComp.getInstance().setTMadres(new String[100]);
		}
		
	}
	
	public void ComboHaciaBD(Combo combo) {
		
		
		String consulta = "INSERT INTO combo (descripcion, nombreCombo, cantidad, precio) VALUES (?,?,?)";
		try {
			con = ConexionSQL.getConexion();
			fact = con.prepareStatement(consulta);
			//fact.setInt(1, Integer.parseInt(combo.getIdCombo())); 
			fact.setString(1, "");
			fact.setString(2, combo.getNombre());
			fact.setInt(3, combo.getCant());
			fact.setInt(4, (int)combo.Precio());
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
		
		
		for(Componente componente : combo.getLosComponentes()) {
			
			String consulta3 = "INSERT INTO ComponenteCombo (id_combo, numSerie) VALUES (?,?)";
			String consulta2 = "select top 1 id_combo from combo \r\n" + 
					"order by id_combo\r\n" + 
					"desc";
			String result="";
			
			try {
				con = ConexionSQL.getConexion();
				fact2 = con.prepareStatement(consulta2);
			//	fact2.setString(1, combo.getNombre());
				
				ResultSet resultado = fact2.executeQuery();
				
				while (resultado.next()) {
					result = resultado.getString(1);
				}
		
			}	catch(SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.toString());
			
			}finally {
				try {
					
					con.close();
					
				}catch(SQLException e) {
					System.out.println(e.toString());
				}
			}
			combo.setIdCombo(result);
			
			try {	
				con = ConexionSQL.getConexion();
				fact = con.prepareStatement(consulta3);
				fact.setInt(1, Integer.parseInt(result));
				fact.setInt(2, Integer.parseInt(componente.getNumSerie())); 	
				fact.execute();
				
			}catch(SQLException ex){
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
