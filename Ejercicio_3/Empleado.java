package Ejercicio_3;
import java.io.Serializable;
public class Empleado implements Serializable {
    private int numero;
    private String nombre;
    private double sueldo;

    public Empleado(int numero, String nombre, double sueldo) {
        this.numero = numero;
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getSueldo() { return sueldo; }
    public void setSueldo(double sueldo) { this.sueldo = sueldo; }

    @Override
    public String toString() {
        return numero + "," + nombre + "," + sueldo;
    }

    public static Empleado desdeString(String linea) {
        String[] partes = linea.split(",");
        if (partes.length == 3) {
            int num = Integer.parseInt(partes[0].trim());
            String nom = partes[1].trim();
            double suel = Double.parseDouble(partes[2].trim());
            return new Empleado(num, nom, suel);
        }
        return null;
    }
}