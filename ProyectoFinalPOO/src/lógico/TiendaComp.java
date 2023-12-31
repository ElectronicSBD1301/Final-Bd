package lógico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.omg.PortableInterceptor.USER_EXCEPTION;

public class TiendaComp implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<Componente> misComponentes;
	private ArrayList<Combo> cVendidos;
	private ArrayList<Persona> misPersonas;
	private ArrayList<Factura> MisFacturas;
	public static TiendaComp tienda = null; // para manejar singelton
	public String [] Carrito;
	public String [] TMadres;
	public String [] DiscosD;
	public String [] Rams;
	public String [] mProcesadores; 
	public int [] cant1;
	public static int mCodigo =1; 
	public static Persona personaLogeada; // para saber que persona se logeo' si un admin o otra persona
	public static Empleado empleadoLogeado;
	public static Cliente clienteLogeado;

	public TiendaComp() {
		super();
		misComponentes = new ArrayList<Componente>();
		cVendidos = new ArrayList<Combo>();
		misPersonas = new ArrayList<Persona>();
		MisFacturas = new ArrayList<Factura>();
		this.Carrito = new String[100];
		this.TMadres = new String[100];
		this.DiscosD = new String[100];
		this.Rams = new String[100];
		this.mProcesadores = new String[100];
		this.cant1 = new int [4];
	}

	public static TiendaComp getInstance(){
		if(tienda==null){
			tienda = new TiendaComp();
		}
		return tienda;
	}

	public ArrayList<Componente> getMisComponentes() {
		return misComponentes;
	}

	public void setMisComponentes(ArrayList<Componente> misComponentes) {
		this.misComponentes = misComponentes;
	}

	public ArrayList<Combo> getcVendidos() {
		return cVendidos;
	}

	public void setcVendidos(ArrayList<Combo> cVendidos) {
		this.cVendidos = cVendidos;
	}

	public ArrayList<Persona> getMisPersonas() {
		return misPersonas;
	}

	public void setMisPersonas(ArrayList<Persona> misPersonas) {
		this.misPersonas = misPersonas;
	}

	public ArrayList<Factura> getMisFacturas() {
		return MisFacturas;
	}

	public void setMisFacturas(ArrayList<Factura> misFacturas) {
		MisFacturas = misFacturas;
	}

	public String[] getCarrito() {
		return Carrito;
	}

	public void setCarrito(String[] carrito) {
		Carrito = carrito;
	}

	public String[] getTMadres() {
		return TMadres;
	}

	public void setTMadres(String[] tMadres) {
		TMadres = tMadres;
	}

	public String[] getDiscosD() {
		return DiscosD;
	}

	public void setDiscosD(String[] discosD) {
		DiscosD = discosD;
	}

	public String[] getRams() {
		return Rams;
	}

	public void setRams(String[] rams) {
		Rams = rams;
	}

	public String[] getmProcesadores() {
		return mProcesadores;
	}

	public void setmProcesadores(String[] mProcesadores) {
		this.mProcesadores = mProcesadores;
	}

	public void InsertarComp(Componente componente) {
		misComponentes.add(componente);
	}

	public void InsertarFact(Factura factura) {
		MisFacturas.add(factura);
	}

	public void InsertarPersona(Persona persona) {
		misPersonas.add(persona);
	}
	public void InsertarCombo(Combo combo) {
		cVendidos.add(combo);
	}

	public void EliminarComp(Componente com) {

		for(Componente aux : misComponentes) {
			if(com.getNumSerie().equalsIgnoreCase(aux.getNumSerie())) {
				aux=null;
			}
		}
	}
	public void EliminarFact(Factura factura) {

		for(Factura fact : MisFacturas) {
			if(factura.getCodigo().equalsIgnoreCase(fact.getCodigo())) {
				fact=null;
			}
		}
	}

	public boolean confirmLogin(String usuario, String pass) { //esto es para confirmar si lo que se digito son usuarios de verdad guardados
		boolean login = false;

		for(Persona usuari: misPersonas) {
			if (usuari instanceof Empleado) {
				if(usuari.getUsuarioString().equals(usuario) && usuari.getPassword().equals(pass)) {
					empleadoLogeado = (Empleado) usuari;
					usuari.setEstado(true);
					login=true;
					break;
				}
			}
			if(usuari instanceof Cliente){

				if(usuari.getUsuarioString().equals(usuario) && usuari.getPassword().equals(pass)) {
					clienteLogeado = (Cliente) usuari;
					usuari.setEstado(true);
					login=true;
					break;
				}
			}

		}


		return login;
	}

	public void GenerarComponentes() {    //Esta funcion es temporal, es solo para crear los compenentes y no tener que crealos cada vex que se corra el programa/

		MicroProcesador mProcesador = new MicroProcesador("Apple", "26", 5, 420, "rojo", "Inalambrica", 20, null); 
		MicroProcesador mProcesador2 = new MicroProcesador("Tuyo", "80", 3, 663, "amarillo", "Inalambrica", 20, null);
		Ram ram = new Ram ("Android", "23", 3, 500, 50, "grande", "MB");
		DiscoDuro DiscoD = new DiscoDuro ("LT", "22", 3, 300, "azul", 64, "Fijo", "GB");
		String[] string = {"usb", "rjo"};
		TMadre tMadre = new TMadre("Merch", "33", 2, 500, "Verde", "Alambrico", "LT", string);
		InsertarComp(mProcesador);
		InsertarComp(mProcesador2);
		InsertarComp(ram);
		InsertarComp(DiscoD);
		InsertarComp(tMadre);
	}

	public void GenerarPersona() {    //Esta funcion es temporal, es solo para crear los clientes y no tener que crealos cada vex que se corra el programa/

		Cliente cliente = new Cliente("000", "Jose", "Micasa", "999", true,
				"Jela", "jose01", "Estudiante", 0); 

		Cliente cliente2 = new Cliente("005", "Juan", "Micasa", "000", false,
				"pepe", "juan00", "Empleado", 0); 

		InsertarPersona(cliente);
		InsertarPersona(cliente2);

	}


	public String[] mProcesadoresList() {

		int ind=0;

		for(Componente componente : misComponentes ) {
			if(componente instanceof MicroProcesador) {
				if(componente.cant>0) {
					mProcesadores[ind]="("+componente.cant+") "+componente.Marca+" | "+componente.precio+" | "+componente.numSerie+" | "+componente.Dato1()+" | "+componente.Dato2()+" | "+ componente.Dato3();
					ind++;
				}
			}
		}
		return mProcesadores;
	}

	public String[] mRamList() {

		int ind=0, cantidad= 0;

		for(Componente componente : misComponentes ) {
			if(componente instanceof Ram) {
				if(componente.cant>0) {
					cantidad = componente.cant;
					Rams[ind]="("+cantidad+") "+componente.Marca+" | "+componente.precio+" | "+componente.numSerie+" | "+componente.Dato1()+" | "+componente.Dato2()+" | "+ componente.Dato3();
					ind++;
				}
			}
		}
		return Rams;
	}

	public String[] mDurosList() {

		int ind=0, cantidad= 0;

		for(Componente componente : misComponentes ) {
			if(componente instanceof DiscoDuro) {
				if(componente.cant>0) {
					//System.out.println(componente.cant);
					cantidad = componente.cant;
					DiscosD[ind]="("+cantidad+") "+componente.Marca+" | "+componente.precio+" | "+componente.numSerie+" | "+componente.Dato1()+" | "+componente.Dato2()+" | "+ componente.Dato3();
					ind++;
				}
			}
		}
		return DiscosD;
	}

	public String[] mMadresList() {

		int ind=0, cantidad= 0;

		for(Componente componente : misComponentes ) {
			if(componente instanceof TMadre) {
				if(componente.cant>0) {
					cantidad = componente.cant;
					TMadres[ind]="("+cantidad+") "+componente.Marca+" | "+componente.precio+" | "+componente.numSerie+" | "+componente.Dato1()+" | "+componente.Dato2()+" | "+ componente.Dato3();
					ind++;
				}
			}
		}
		return TMadres;
	}


	public String[] ComponentesEnListaCarrito(String string) {

		int ind=0, cont=0;

		for(Componente componente : misComponentes ) {
			if(string.contains(componente.numSerie)) {

				for(ind=0; Carrito[ind]!=null; ind++) {
					cont++;
					if(Carrito[ind].contains(componente.numSerie) && string.contains(componente.numSerie)) {
						//ind2 = ind;
						if(componente instanceof MicroProcesador) {
							cant1[0]++;
							Carrito[ind]="("+cant1[0]+") "+componente.Marca+" | "+componente.precio+" | "+componente.numSerie+" | "+componente.Dato1()+" | "+componente.Dato2()+" | "+ componente.Dato3();
							return Carrito;
						}
						else if(componente instanceof TMadre) {
							cant1[1]++;
							Carrito[ind]="("+cant1[1]+") "+componente.Marca+" | "+componente.precio+" | "+componente.numSerie+" | "+componente.Dato1()+" | "+componente.Dato2()+" | "+ componente.Dato3();
							return Carrito;
						}
						else if(componente instanceof Ram) {
							cant1[2]++;
							Carrito[ind]="("+cant1[2]+") "+componente.Marca+" | "+componente.precio+" | "+componente.numSerie+" | "+componente.Dato1()+" | "+componente.Dato2()+" | "+ componente.Dato3();
							return Carrito;
						}
						else if(componente instanceof DiscoDuro) {
							cant1[3]++;
							Carrito[ind]="("+cant1[3]+") "+componente.Marca+" | "+componente.precio+" | "+componente.numSerie+" | "+componente.Dato1()+" | "+componente.Dato2()+" | "+ componente.Dato3();
							return Carrito;
						}
					}

				}
				if(componente instanceof MicroProcesador) {
					cant1[0]=1;
				}
				else if(componente instanceof TMadre) {
					cant1[1]=1;
				}
				else if(componente instanceof Ram) {
					cant1[2]=1;
				}
				else if(componente instanceof DiscoDuro) {
					cant1[3]=1;
				}
				Carrito[cont]="("+1+") "+componente.Marca+" | "+componente.precio+" | "+componente.numSerie+" | "+componente.Dato1()+" | "+componente.Dato2()+" | "+ componente.Dato3();
				Carrito[cont+1]=null;
			}
		}
		return Carrito;
	}

	public void ActualizarListComp(String string, String[] lista) {

		for(Componente componente : misComponentes) {
			if(string.contains(componente.numSerie)) {
				componente.cant--;
			}
			if(componente.cant == 0) {
				for(int ind =0; lista[ind]!= null; ind++) {

					if(lista[ind].equalsIgnoreCase(string)) {
						for(int indice = ind; lista[indice]!=null;) {
							lista[indice]=lista[indice+1];
							componente.setCant(0);
							//System.out.println(componente.cant);
							return;
						}
					}
				}
			}
		}
	}

	public String[] ActualizarListCarrito(String string) {

		int ind;
		for(Componente componente : misComponentes) {

			for(ind=0; Carrito[ind]!=null; ind++) {
				//	if(string.contains(componente.numSerie)) {
				//		componente.cant++;
				if(Carrito[ind].contains(componente.numSerie) && string.contains(componente.numSerie)) {
					componente.cant++;
					if(componente instanceof MicroProcesador) {
						if(cant1[0]-1 != 0) {
							cant1[0]--;
							Carrito[ind]="("+cant1[0]+") "+componente.Marca+" | "+componente.precio+" | "+componente.numSerie+" | "+componente.Dato1()+" | "+componente.Dato2()+" | "+ componente.Dato3();
							return Carrito;
						}
					}
					else if(componente instanceof TMadre) {
						if(cant1[1]-1 != 0) {
							cant1[1]--;
							Carrito[ind]="("+cant1[1]+") "+componente.Marca+" | "+componente.precio+" | "+componente.numSerie+" | "+componente.Dato1()+" | "+componente.Dato2()+" | "+ componente.Dato3();
							return Carrito;
						}
					}
					else if(componente instanceof Ram) {
						if(cant1[2]-1 != 0) {
							cant1[2]--;
							Carrito[ind]="("+cant1[2]+") "+componente.Marca+" | "+componente.precio+" | "+componente.numSerie+" | "+componente.Dato1()+" | "+componente.Dato2()+" | "+ componente.Dato3();
							return Carrito;
						}
					}
					else if(componente instanceof DiscoDuro) {
						if(cant1[3]-1 != 0) {
							cant1[3]--;
							Carrito[ind]="("+cant1[3]+") "+componente.Marca+" | "+componente.precio+" | "+componente.numSerie+" | "+componente.Dato1()+" | "+componente.Dato2()+" | "+ componente.Dato3();
							return Carrito;
						}
					}
				}
			}
		}

		//for(Componente componente : misComponentes) {

		//	}

		for(int indi =0; Carrito[indi]!= null; indi++) {

			if(Carrito[indi].contains(string)) {
				for(int indice = indi; Carrito[indice]!=null; indice++) {
					Carrito[indice]=Carrito[indice+1];
				}
			}
		}
		return Carrito;
	}

	public int DeDondeEs(String string) {

		for(Componente componente : misComponentes) {
			if(string.contains(componente.numSerie)) {
				if(componente instanceof Ram) {
					return 0;
				}
				else if(componente instanceof MicroProcesador) {
					return 1;
				}
				else if(componente instanceof TMadre) {
					return 2;
				}
				else if(componente instanceof DiscoDuro) {
					return 3;
				}

			}
		}
		return 5;
	}

	public String monto() {

		double total=0; 
		for(int ind=0; Carrito[ind]!=null; ind++) {

			for(Componente componente : misComponentes) {
				if(Carrito[ind].contains(Double.toString(componente.precio))){
					total+=componente.precio;
					if(componente instanceof MicroProcesador && cant1[0]!=0) {
						total*=cant1[0];
					}
					else if(componente instanceof TMadre && cant1[1]!=0){
						total*=cant1[1];
					}
					else if(componente instanceof Ram && cant1[2]!=0){
						total*=cant1[2];
					}
					else if(componente instanceof DiscoDuro && cant1[3]!=0){
						total*=cant1[3];
					}
				}
			}
		}	
		return "$"+Double.toString(total);
	}

	public ArrayList<Componente> AgregarCompFact(String [] carrito) {

		ArrayList<Componente> componente = new ArrayList<Componente>();
		String c=null;
		for(int ind=0; carrito[ind]!= null; ind++) {	
			for(Componente comp : misComponentes) {
				if(carrito[ind].contains(comp.numSerie)) {

					for(int indice = 1; carrito[ind].charAt(indice) != ' '; indice++) {

						if(carrito[ind].charAt(indice)==')') {
							//System.out.println(carrito[ind].charAt(indice));
							c=carrito[ind].substring(1, indice);
							//System.out.println(c);
						}							
					}
					//comp.setCant((Integer.valueOf(c)));;		//comp.setCant((Integer.valueOf(c)));
					//factura.getMisComponentes().add(comp);
					//System.out.println("hola");
					//c = null;
					/*for(Componente compo : factura.getMisComponentes()) {
							if(carrito[ind].contains(compo.numSerie)) {
								//System.out.println(c);
								compo.setCant((Integer.valueOf(c)));
							//	System.out.println(compo.cant);
								//componente.add(compo);
							}
						}*/
					//comp.cant = Integer.valueOf(c);
					for(int indice=0; indice<Integer.valueOf(c); indice++) {
						componente.add(comp);
					}

					//System.out.println(comp.cant);
					//c = null;
					//System.out.println(comp.cant);
					//c=null;

				}
			}
		}

		return componente;	

	}

	public Persona PersonaLogg() {

		for(Persona persona : misPersonas) {
			if(persona.estado) {
				return persona;
			}
			
		}


		return null;
	}

	public String CrearCodigoFact() {

		String codigo = null;

		for(Componente componente : misComponentes) {
			codigo =  String.valueOf(mCodigo);
			mCodigo++;
			return codigo;
		}


		return null;
	}
	
	public String CrearCodigoCombo() {

		String codigo = null;

		for(Componente componente : misComponentes) {
			codigo =  String.valueOf(mCodigo);
			mCodigo++;
			return codigo;
		}


		return null;
	}
	

	public boolean ComboValido(int cantidad, String [] carrito) {

		String c = null;
		for(int ind=0; carrito[ind]!=null; ind++) {	
			for(Componente componente : misComponentes) {
				if(carrito[ind].contains(componente.numSerie)){
					for(int indice = 1; carrito[ind].charAt(indice) != ' '; indice++) {
						if(carrito[ind].charAt(indice)==')') {
							c=carrito[ind].substring(1, indice);
						}							
					}
					if(Integer.valueOf(c)+componente.cant<cantidad*Integer.valueOf(c)) {
						return false;
					}

				}
			}
		}
		if(cantidad>1) {
			for(int ind=0; carrito[ind]!=null; ind++) {	
				for(Componente componente : misComponentes) {
					if(carrito[ind].contains(componente.numSerie)){
						for(int indice = 1; carrito[ind].charAt(indice) != ' '; indice++) {
							if(carrito[ind].charAt(indice)==')') {
								c=carrito[ind].substring(1, indice);
							}							
						}
						componente.cant = componente.cant-Integer.valueOf(c)*cantidad;
						componente.cant++;

					}
				}
			}
			return true;
		}

		return true;
	}

	public ArrayList<Componente> AgregarCompCombo(String [] carrito) {

		ArrayList<Componente> componente = new ArrayList<Componente>();
		String c=null;
		for(int ind=0; carrito[ind]!= null; ind++) {	
			for(Componente comp : misComponentes) {
				if(carrito[ind].contains(comp.numSerie)) {

					for(int indice = 1; carrito[ind].charAt(indice) != ' '; indice++) {

						if(carrito[ind].charAt(indice)==')') {
							c=carrito[ind].substring(1, indice);
						}							
					}
					for(int indice=0; indice<Integer.valueOf(c); indice++) {
						componente.add(comp);
					}					
				}
			}
		}
		return componente;	

	}

	public static TiendaComp getTienda() {
		return tienda;
	}

	public static void setTienda(TiendaComp tienda) {
		TiendaComp.tienda = tienda;
	}

	public int[] getCant1() {
		return cant1;
	}

	public void setCant1(int[] cant1) {
		this.cant1 = cant1;
	}

	public static int getmCodigo() {
		return mCodigo;
	}

	public static void setmCodigo(int mCodigo) {
		TiendaComp.mCodigo = mCodigo;
	}

	public static Persona getPersonaLogeada() {
		return personaLogeada;
	}

	public static void setPersonaLogeada(Persona personaLogeada) {
		TiendaComp.personaLogeada = personaLogeada;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Empleado getEmpleadoLogeado() {
		return empleadoLogeado;
	}

	public static void setEmpleadoLogeado(Empleado empleadoLogeado) {
		TiendaComp.empleadoLogeado = empleadoLogeado;
	}

	public static Cliente getClienteLogeado() {
		return clienteLogeado;
	}

	public static void setClienteLogeado(Cliente clienteLogeado) {
		TiendaComp.clienteLogeado = clienteLogeado;
	}
}
