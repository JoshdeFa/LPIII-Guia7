package Ejercicio_4;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class ContadorPalabras {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        File archivo = null;
        
        while (true) {
            int resultado = fileChooser.showOpenDialog(null);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                archivo = fileChooser.getSelectedFile();
                if (archivo.exists() && archivo.isFile()) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "El archivo no existe o es inválido. Por favor, seleccione otro archivo.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("Operación cancelada por el usuario.");
                return;
            }
        }

        int totalLineas = 0;
        long totalCaracteres = 0;
        int totalPalabras = 0;
        Map<String, Integer> frecuenciaPalabras = new HashMap<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                totalLineas++;
                totalCaracteres += linea.length();

                boolean enPalabra = false;
                StringBuilder palabraActual = new StringBuilder();

                for (int i = 0; i < linea.length(); i++) {
                    char c = linea.charAt(i);
                    if (Character.isLetterOrDigit(c)) {
                        palabraActual.append(Character.toLowerCase(c));
                        enPalabra = true;
                    } else {
                        if (enPalabra) {
                            String palabra = palabraActual.toString();
                            totalPalabras++;
                            frecuenciaPalabras.put(palabra, frecuenciaPalabras.getOrDefault(palabra, 0) + 1);
                            palabraActual.setLength(0);
                            enPalabra = false;
                        }
                    }
                }
                if (enPalabra) {
                    String palabra = palabraActual.toString();
                    totalPalabras++;
                    frecuenciaPalabras.put(palabra, frecuenciaPalabras.getOrDefault(palabra, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        double promedioPalabrasPorLinea = totalLineas > 0 ? (double) totalPalabras / totalLineas : 0;

        List<Map.Entry<String, Integer>> listaFrecuencia = new ArrayList<>(frecuenciaPalabras.entrySet());
        listaFrecuencia.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        System.out.println("\n========================================");
        System.out.println("          ESTADÍSTICAS DEL ARCHIVO      ");
        System.out.println("========================================");
        System.out.println("Total de líneas: " + totalLineas);
        System.out.println("Total de palabras: " + totalPalabras);
        System.out.println("Total de caracteres (sin fin de línea): " + totalCaracteres);
        System.out.printf("Promedio de palabras por línea: %.2f%n", promedioPalabrasPorLinea);
        System.out.println("\nPalabras más frecuentes:");
        int limite = Math.min(5, listaFrecuencia.size());
        for (int i = 0; i < limite; i++) {
            Map.Entry<String, Integer> entrada = listaFrecuencia.get(i);
            System.out.println("- " + entrada.getKey() + ": " + entrada.getValue() + " veces");
        }
        System.out.println("========================================");
    }
}