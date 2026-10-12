package Ejercicio_2;

public class MainPersonajes {
    public static void main(String[] args) {
        Gestor gestor = new Gestor();

        System.out.println("=== 1. ESTADO INICIAL ===");
        gestor.mostrarPersonajes();

        System.out.println("=== 2. CARGAR PERSONAJES ALEATORIOS ===");
        gestor.cargarPersonajesAleatorios(3);
        gestor.mostrarPersonajes();

        System.out.println("=== 3. MOSTRAR ESTADÍSTICAS ===");
        gestor.mostrarEstadisticas();

        System.out.println("=== 4. ACTUALIZAR ATRIBUTO INDIVIDUAL (Ataque del Guerrero) ===");
        gestor.actualizarAtributoIndividual("Guerrero", "ataque", 8);
        gestor.mostrarPersonajes();

        System.out.println("=== 5. SUBIR DE NIVEL A UN PERSONAJE (Caballero) ===");
        gestor.subirNivelPersonaje("Caballero");
        gestor.mostrarPersonajes();

        System.out.println("=== 6. FILTRAR / ORDENAR POR ATRIBUTO (Ataque) ===");
        gestor.ordenarPersonajes("ataque");

        System.out.println("=== 7. MOSTRAR ESTADÍSTICAS FINALES ===");
        gestor.mostrarEstadisticas();
    }
}