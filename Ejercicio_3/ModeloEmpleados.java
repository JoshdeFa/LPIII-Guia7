package Ejercicio_3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ModeloEmpleados {
    private List<Empleado> listaEmpleados;
    private final String nombreArchivo = "empleados.txt";

    public ModeloEmpleados() {
        listaEmpleados = new ArrayList<>();
        leerEmpleados();
    }

    public void leerEmpleados() {
        listaEmpleados.clear();
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            return;
        }
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    Empleado emp = Empleado.desdeString(linea);
                    if (emp != null) {
                        listaEmpleados.add(emp);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de empleados: " + e.getMessage());
        }
    }

    public void guardarEmpleados() {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Empleado emp : listaEmpleados) {
                escritor.println(emp.toString());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo de empleados: " + e.getMessage());
        }
    }

    public boolean agregarEmpleado(Empleado nuevo) {
        for (Empleado emp : listaEmpleados) {
            if (emp.getNumero() == nuevo.getNumero()) {
                return false;
            }
        }
        listaEmpleados.add(nuevo);
        guardarEmpleados();
        return true;
    }

    public Empleado buscarEmpleado(int numero) {
        for (Empleado emp : listaEmpleados) {
            if (emp.getNumero() == numero) {
                return emp;
            }
        }
        return null;
    }

    public boolean eliminarEmpleado(int numero) {
        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (listaEmpleados.get(i).getNumero() == numero) {
                listaEmpleados.remove(i);
                guardarEmpleados();
                return true;
            }
        }
        return false;
    }

    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }
}