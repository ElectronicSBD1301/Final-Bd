package lógico;

import java.io.Serializable;
import java.util.ArrayList;

public class Combo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String idCombo;
	private String nombre;
	private ArrayList<Componente> losComponentes;
	private int cant;
	
	public Combo(String idCombo, ArrayList<Componente> losComponentes, String nombre, int cant) {
		super();
		this.idCombo = idCombo;
		this.losComponentes = losComponentes;
		this.nombre = nombre;
		this.cant = cant;
	}

	public ArrayList<Componente> getLosComponentes() {
		return losComponentes;
	}

	public void setLosComponentes(ArrayList<Componente> losComponentes) {
		this.losComponentes = losComponentes;
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getIdCombo() {
		return idCombo;
	}
	
	public void setIdCombo(String idCombo) {
		this.idCombo = idCombo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCant() {
		return cant;
	}
	
	public String getNombre(String nombre) {
		return nombre;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}
	
	public double Precio() {
		
		double precio = 0;
		
		for(Componente componente : losComponentes) {
			precio += componente.precio;
		}
		
		return precio - precio*0.1;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
