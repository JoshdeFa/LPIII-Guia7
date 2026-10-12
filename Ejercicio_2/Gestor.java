package Ejercicio_2;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

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
            System.out.println("Error: " + e.getMessage());
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
                System.out.println("-> Aviso: El personaje '" + nuevo.getNombre() + "' ya existe.");
                return false;
            }
        }
        personajes.add(nuevo);
        guardarEnArchivo();
        System.out.println("-> Éxito: Personaje '" + nuevo.getNombre() + "' añadido.");
        return true;
    }

    public void mostrarPersonajes() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        System.out.println("\n=======================================================");
        System.out.println("                  LISTA DE PERSONAJES                  ");
        System.out.println("=======================================================");
        System.out.printf("%-12s | %-6s | %-6s | %-7s | %-7s | %-6s%n", "Nombre", "Vida", "Ataque", "Defensa", "Alcance", "Nivel");
        System.out.println("-------------------------------------------------------");
        for (Personaje p : personajes) {
            System.out.printf("%-12s | %-6d | %-6d | %-7d | %-7d | %-6d%n",
                p.getNombre(), p.getVida(), p.getAtaque(), p.getDefensa(), p.getAlcance(), p.getNivel());
        }
        System.out.println("=======================================================\n");
    }

    public boolean modificarPersonaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                p.setVida(vida);
                p.setAtaque(ataque);
                p.setDefensa(defensa);
                p.setAlcance(alcance);
                guardarEnArchivo();
                System.out.println("-> Éxito: Personaje '" + nombre + "' modificado.");
                return true;
            }
        }
        System.out.println("-> Error: No se encontró al personaje '" + nombre + "'.");
        return false;
    }

    public boolean borrarPersonaje(String nombre) {
        for (int i = 0; i < personajes.size(); i++) {
            if (personajes.get(i).getNombre().equalsIgnoreCase(nombre)) {
                personajes.remove(i);
                guardarEnArchivo();
                System.out.println("-> Éxito: Personaje '" + nombre + "' eliminado.");
                return true;
            }
        }
        System.out.println("-> Error: No se encontró al personaje '" + nombre + "'.");
        return false;
    }

    public void actualizarAtributoIndividual(String nombre, String atributo, int valor) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                switch (atributo.toLowerCase()) {
                    case "vida": p.setVida(valor); break;
                    case "ataque": p.setAtaque(valor); break;
                    case "defensa": p.setDefensa(valor); break;
                    case "alcance": p.setAlcance(valor); break;
                    case "nivel": p.setNivel(valor); break;
                    default:
                        System.out.println("-> Error: Atributo no válido.");
                        return;
                }
                guardarEnArchivo();
                System.out.println("-> Éxito: Atributo '" + atributo + "' actualizado para '" + nombre + "'.");
                return;
            }
        }
        System.out.println("-> Error: Personaje no encontrado.");
    }

    public void subirNivelPersonaje(String nombre) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                p.subirNivel();
                guardarEnArchivo();
                System.out.println("-> Éxito: El personaje '" + nombre + "' subió al nivel " + p.getNivel() + ".");
                return;
            }
        }
        System.out.println("-> Error: Personaje no encontrado.");
    }

    public void ordenarPersonajes(String criterio) {
        Comparator<Personaje> comparador;
        switch (criterio.toLowerCase()) {
            case "vida": comparador = Comparator.comparingInt(Personaje::getVida).reversed(); break;
            case "ataque": comparador = Comparator.comparingInt(Personaje::getAtaque).reversed(); break;
            case "defensa": comparador = Comparator.comparingInt(Personaje::getDefensa).reversed(); break;
            case "alcance": comparador = Comparator.comparingInt(Personaje::getAlcance).reversed(); break;
            case "nivel": comparador = Comparator.comparingInt(Personaje::getNivel).reversed(); break;
            default:
                System.out.println("Criterio de ordenamiento inválido.");
                return;
        }
        personajes.sort(comparador);
        System.out.println("-> Personajes ordenados por " + criterio + " (descendente):");
        mostrarPersonajes();
    }

    public void cargarPersonajesAleatorios(int cantidad) {
        String[] nombresBase = {"Mago", "Asesino", "Tanque", "Clérigo", "Paladín", "Nigromante", "Cazador", "Bárbaro"};
        Random rand = new Random();
        int anadidos = 0;
        for (int i = 0; i < cantidad; i++) {
            String nombreGen = nombresBase[rand.nextInt(nombresBase.length)] + "_" + (rand.nextInt(90) + 10);
            int v = rand.nextInt(5) + 1;
            int a = rand.nextInt(5) + 1;
            int d = rand.nextInt(5) + 1;
            int al = rand.nextInt(10) + 1;
            Personaje nuevo = new Personaje(nombreGen, v, a, d, al);
            boolean existe = false;
            for (Personaje p : personajes) {
                if (p.getNombre().equalsIgnoreCase(nuevo.getNombre())) {
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                personajes.add(nuevo);
                anadidos++;
            }
        }
        guardarEnArchivo();
        System.out.println("-> Se cargaron " + anadidos + " personajes aleatorios.");
    }

    public void mostrarEstadisticas() {
        if (personajes.isEmpty()) {
            System.out.println("No hay estadísticas disponibles.");
            return;
        }
        int total = personajes.size();
        double sumVida = 0, sumAtaque = 0, sumDefensa = 0, sumAlcance = 0, sumNivel = 0;
        for (Personaje p : personajes) {
            sumVida += p.getVida();
            sumAtaque += p.getAtaque();
            sumDefensa += p.getDefensa();
            sumAlcance += p.getAlcance();
            sumNivel += p.getNivel();
        }
        System.out.println("\n=======================================================");
        System.out.println("                 ESTADÍSTICAS GENERALES                ");
        System.out.println("=======================================================");
        System.out.println("Total de personajes: " + total);
        System.out.printf("Promedio de Vida:    %.2f%n", (sumVida / total));
        System.out.printf("Promedio de Ataque:  %.2f%n", (sumAtaque / total));
        System.out.printf("Promedio de Defensa: %.2f%n", (sumDefensa / total));
        System.out.printf("Promedio de Alcance: %.2f%n", (sumAlcance / total));
        System.out.printf("Promedio de Nivel:   %.2f%n", (sumNivel / total));
        System.out.println("=======================================================\n");
    }

    public void importarDesdeArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            System.out.println("-> Error: El archivo externo no existe.");
            return;
        }
        int importados = 0;
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    Personaje p = Personaje.desdeString(linea);
                    if (p != null) {
                        boolean existe = false;
                        for (Personaje existente : personajes) {
                            if (existente.getNombre().equalsIgnoreCase(p.getNombre())) {
                                existe = true;
                                break;
                            }
                        }
                        if (!existe) {
                            personajes.add(p);
                            importados++;
                        }
                    }
                }
            }
            guardarEnArchivo();
            System.out.println("-> Éxito: Se importaron " + importados + " personajes desde '" + rutaArchivo + "'.");
        } catch (IOException e) {
            System.out.println("-> Error al importar el archivo: " + e.getMessage());
        }
    }
}