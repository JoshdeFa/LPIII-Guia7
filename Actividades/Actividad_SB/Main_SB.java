package Actividad_SB;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main_SB {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String cadena;
		
		 try(PrintWriter salida = new PrintWriter("ficheros/datos.txt")) {
			 System.out.println("Introduce el texto, para parar la cadena FIN:");
			 cadena = sc.nextLine();
			 while(!cadena.equalsIgnoreCase("FIN")){
				 salida.println(cadena);
				 cadena = sc.nextLine();
			 }
		 }catch(FileNotFoundException e) {
			 System.out.println(e.getMessage());
		 }
		 sc.close();
	}

}
