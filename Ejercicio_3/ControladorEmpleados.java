package Ejercicio_3;

public class ControladorEmpleados {
    private ModeloEmpleados modelo;
    private VistaEmpleados vista;

    public ControladorEmpleados(ModeloEmpleados modelo, VistaEmpleados vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        int opcion;
        do {
            opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1:
                    modelo.leerEmpleados();
                    vista.listarEmpleados(modelo.getListaEmpleados());
                    break;
                case 2:
                    Empleado nuevo = vista.solicitarDatosEmpleado();
                    if (nuevo != null) {
                        boolean agregado = modelo.agregarEmpleado(nuevo);
                        if (agregado) {
                            vista.mostrarMensaje("-> Éxito: Empleado agregado correctamente.");
                        } else {
                            vista.mostrarMensaje("-> Error: Ya existe un empleado con ese número.");
                        }
                    }
                    break;
                case 3:
                    int numBuscar = vista.solicitarNumeroEmpleado();
                    Empleado encontrado = modelo.buscarEmpleado(numBuscar);
                    if (encontrado != null) {
                        vista.mostrarEmpleado(encontrado);
                    } else {
                        vista.mostrarMensaje("-> Error: Empleado con número " + numBuscar + " no encontrado.");
                    }
                    break;
                case 4:
                    int numEliminar = vista.solicitarNumeroEmpleado();
                    boolean eliminado = modelo.eliminarEmpleado(numEliminar);
                    if (eliminado) {
                        vista.mostrarMensaje("-> Éxito: Empleado eliminado correctamente.");
                    } else {
                        vista.mostrarMensaje("-> Error: No se encontró un empleado con ese número.");
                    }
                    break;
                case 5:
                    vista.mostrarMensaje("Saliendo del programa...");
                    break;
                default:
                    vista.mostrarMensaje("-> Error: Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }
}