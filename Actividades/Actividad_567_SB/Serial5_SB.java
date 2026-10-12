package Actividad_567_SB;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Actividad_SB.Fecha_SB;

public class Serial5_SB {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        ObjectOutputStream salida = null;
        Alumno_SB a;
        Fecha_SB f; 
        try {
            fos = new FileOutputStream("C://Users//IDEAPAD//eclipse-workspace//Actividades_7//src//Actividad_SB//ficheros/alumnos.dat");
            salida = new ObjectOutputStream(fos);
            
            f = new Fecha_SB(5, 9, 2011);
            a = new Alumno_SB("12345678A", "Lucas González", 20, f);
            salida.writeObject(a);
            
            f = new Fecha_SB(7, 9, 2011);
            a = new Alumno_SB("98765432B", "Anacleto Jiménez", 19, f);
            salida.writeObject(a);
            
            f = new Fecha_SB(8, 9, 2011);
            a = new Alumno_SB("78234212Z", "María Zapata", 21, f);
            salida.writeObject(a);
            
            System.out.println("Alumnos guardados correctamente en alumnos.dat");
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fos != null) fos.close();
                if (salida != null) salida.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}