package Ejercicio_3;

import java.util.List;
import java.util.Scanner;

public class VistaEmpleados {
    private Scanner scanner;

    public VistaEmpleados() {
        scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n========================================");
        System.out.println("          SISTEMA DE EMPLEADOS          ");
        System.out.println("========================================");
        System.out.println("1. Listar todos los empleados");
        System.out.println("2. Agregar un nuevo empleado");
        System.out.println("3. Buscar un empleado por su número");
        System.out.println("4. Eliminar un empleado por su número");
        System.out.println("5. Salir del programa");
        System.out.print("Elija una opción: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada inválida. Ingrese un número del 1 al 5: ");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        scanner.nextLine(); 
        return opcion;
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void listarEmpleados(List<Empleado> empleados) {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados en el archivo.");
            return;
        }
        System.out.println("\n----------------------------------------");
        System.out.println("           LISTA DE EMPLEADOS           ");
        System.out.println("----------------------------------------");
        System.out.printf("%-10s | %-20s | %-10s%n", "Número", "Nombre", "Sueldo");
        System.out.println("----------------------------------------");
        for (Empleado emp : empleados) {
            System.out.printf("%-10d | %-20s | %-10.2f%n", emp.getNumero(), emp.getNombre(), emp.getSueldo());
        }
        System.out.println("----------------------------------------");
    }

    public Empleado solicitarDatosEmpleado() {
        try {
            System.out.print("Ingrese número del empleado: ");
            int numero = Integer.parseInt(scanner.nextLine());
            System.out.print("Ingrese nombre del empleado: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese sueldo del empleado: ");
            double sueldo = Double.parseDouble(scanner.nextLine());
            return new Empleado(numero, nombre, sueldo);
        } catch (NumberFormatException e) {
            System.out.println("Error: Formato de número inválido.");
            return null;
        }
    }

    public int solicitarNumeroEmpleado() {
        System.out.print("Ingrese el número del empleado: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada inválida. Ingrese un número entero: ");
            scanner.next();
        }
        int num = scanner.nextInt();
        scanner.nextLine();
        return num;
    }

    public void mostrarEmpleado(Empleado emp) {
        System.out.println("\n----------------------------------------");
        System.out.println("          EMPLEADO ENCONTRADO           ");
        System.out.println("----------------------------------------");
        System.out.println("Número : " + emp.getNumero());
        System.out.println("Nombre : " + emp.getNombre());
        System.out.println("Sueldo : " + emp.getSueldo());
        System.out.println("----------------------------------------");
    }
}