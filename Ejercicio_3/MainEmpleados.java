package Ejercicio_3;

public class MainEmpleados {
    public static void main(String[] args) {
        ModeloEmpleados modelo = new ModeloEmpleados();
        VistaEmpleados vista = new VistaEmpleados();
        ControladorEmpleados controlador = new ControladorEmpleados(modelo, vista);
        controlador.iniciar();
    }
}