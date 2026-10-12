package Actividad_567_SB;

public class Persona_SB {
	protected String nombre;
	protected String telefono;
	protected String direccion;
	public Persona_SB(String nombre, String telefono, String direccion) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String toString() {
		return this.nombre + "\t" + this.telefono + "\t" + this.direccion + "\n";
	}

}


