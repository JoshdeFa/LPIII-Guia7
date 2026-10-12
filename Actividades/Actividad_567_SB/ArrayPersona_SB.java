package Actividad_567_SB;

public class ArrayPersona_SB {
	public Persona_SB arreglo[];
	final int max = 128;
	int tamano = 0;
	
	public ArrayPersona_SB() {
		this.arreglo = new Persona_SB[this.max];
	}
	
	public void printArray(String nombre) {
		for(int i = 0; i < this.tamano; i++) {
			if(this.arreglo[i].getNombre().equals(nombre)) {
				System.out.println(this.arreglo[i]);
			}
		}
	}
	
	public void addArray(Persona_SB persona) {
		if(this.tamano == max) {
			System.exit(1);
		}
		this.arreglo[this.tamano++] = persona;
	}
	
}
