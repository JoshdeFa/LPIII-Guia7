package Ejercicio_1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Gestor {
    private List<Personaje> personajes;
    private final String nombreArchivo = "personajes.txt";

    public Gestor() {
        personajes = new ArrayList<>();
        cargarDesdeArchivo();
        
        if (personajes.isEmpty()) {
            inicializarDatosPorDefecto();
        }
    }

    private void inicializarDatosPorDefecto() {
        try {
            añadirPersonaje(new Personaje("Caballero", 4, 2, 4, 2));
            añadirPersonaje(new Personaje("Guerrero", 2, 4, 2, 4));
            añadirPersonaje(new Personaje("Arquero", 2, 4, 1, 8));
        } catch (Exception e) {
            System.out.println("Error al inicializar datos por defecto: " + e.getMessage());
        }
    }

    public void cargarDesdeArchivo() {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            return;
        }
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    Personaje p = Personaje.desdeString(linea);
                    if (p != null) {
                        personajes.add(p);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void guardarEnArchivo() {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Personaje p : personajes) {
                escritor.println(p.toString());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }

    public boolean añadirPersonaje(Personaje nuevo) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nuevo.getNombre())) {
                System.out.println("-> Aviso: El personaje '" + nuevo.getNombre() + "' ya existe. No se puede crear.");
                return false;
            }
        }
        personajes.add(nuevo);
        guardarEnArchivo();
        System.out.println("-> Éxito: Personaje '" + nuevo.getNombre() + "' añadido y guardado.");
        return true;
    }

    public void mostrarPersonajes() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        System.out.println("\n=============================================");
        System.out.println("             LISTA DE PERSONAJES             ");
        System.out.println("=============================================");
        System.out.printf("%-12s | %-6s | %-6s | %-7s | %-7s%n", "Nombre", "Vida", "Ataque", "Defensa", "Alcance");
        System.out.println("---------------------------------------------");
        for (Personaje p : personajes) {
            System.out.printf("%-12s | %-6d | %-6d | %-7d | %-7d%n",
                p.getNombre(), p.getVida(), p.getAtaque(), p.getDefensa(), p.getAlcance());
        }
        System.out.println("=============================================\n");
    }

    public boolean modificarPersonaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                p.setVida(vida);
                p.setAtaque(ataque);
                p.setDefensa(defensa);
                p.setAlcance(alcance);
                guardarEnArchivo();
                System.out.println("-> Éxito: Personaje '" + nombre + "' modificado correctamente.");
                return true;
            }
        }
        System.out.println("-> Error: No se encontró al personaje '" + nombre + "' para modificar.");
        return false;
    }

    public boolean borrarPersonaje(String nombre) {
        for (int i = 0; i < personajes.size(); i++) {
            if (personajes.get(i).getNombre().equalsIgnoreCase(nombre)) {
                personajes.remove(i);
                guardarEnArchivo();
                System.out.println("-> Éxito: Personaje '" + nombre + "' eliminado correctamente.");
                return true;
            }
        }
        System.out.println("-> Error: No se encontró al personaje '" + nombre + "' para borrar.");
        return false;
    }
}