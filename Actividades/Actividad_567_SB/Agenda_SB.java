package Actividad_567_SB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Agenda_SB {
	ArrayPersona_SB lista;
	FileInputStream agendaFile;
	final int longLinea = 32;
	
	public Agenda_SB() {
		this.lista = cargaContactos();
		
	}
	
	public void bucle() {
		String nombre;
		System.out.println("Introduzca nombre o <Enter>");
		try {
			while(!"".equals(nombre = leeEntrada(System.in))) {
				lista.printArray(nombre);
				System.out.println("Introduzca nombre o <Enter>");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private String leeEntrada(InputStream entrada) throws IOException {
		byte chars[] = new byte[longLinea];
		int contador = 0;
		while(contador < longLinea && (chars[contador++] = (byte) entrada.read()) != '\n') {
			if(chars[contador -1] == -1) {
				return null;
			}
		}
		return (new String(chars,0,contador -1));
	}
	
	private Persona_SB cargaAgenda() throws IOException{
		String nombre = leeEntrada(agendaFile);
		if(nombre == null) {
			return null;
		}
		String telefono = leeEntrada(agendaFile);
		String direccion = leeEntrada(agendaFile);
		return new Persona_SB(nombre, telefono, direccion);
	}
	
	private ArrayPersona_SB cargaContactos() {
		ArrayPersona_SB directorio = new ArrayPersona_SB();
		Persona_SB nuevaPersona;
		try {
			agendaFile = new FileInputStream("/ficheros/agenda.txt");
			while(true) {
				nuevaPersona = cargaAgenda();
				if(nuevaPersona == null) {
					return directorio;
				}
				directorio.addArray(nuevaPersona);
			}
			
		}catch(FileNotFoundException e) {
			System.out.println("No hay arcihvo de agenda");
		}catch(Exception e) {
			System.out.println("Error en la carga de contactos");
			System.exit(1);
		}return directorio;
	}
}
