package Ejercicio_1;

public class MainPersonajes {
    public static void main(String[] args) {
        Gestor gestor = new Gestor();

        gestor.mostrarPersonajes();

        System.out.println("--- PRUEBA 1: Añadir nuevo personaje (Mago) ---");
        gestor.añadirPersonaje(new Personaje("Mago", 3, 5, 1, 6));
        gestor.mostrarPersonajes();

        System.out.println("--- PRUEBA 2: Intentar duplicar personaje (Caballero) ---");
        gestor.añadirPersonaje(new Personaje("Caballero", 4, 2, 4, 2));

        System.out.println("\n--- PRUEBA 3: Modificar valores del Guerrero ---");
        gestor.modificarPersonaje("Guerrero", 3, 5, 3, 5);
        gestor.mostrarPersonajes();

        System.out.println("--- PRUEBA 4: Borrar al Arquero ---");
        gestor.borrarPersonaje("Arquero");
        gestor.mostrarPersonajes();
    }
}