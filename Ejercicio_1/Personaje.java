package Ejercicio_1;

import java.io.Serializable;

public class Personaje implements Serializable {
    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private int alcance;

    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        if (vida <= 0 || ataque <= 0 || defensa <= 0 || alcance <= 0) {
            throw new IllegalArgumentException("Todos los atributos deben ser mayores que cero.");
        }
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.alcance = alcance;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getVida() { return vida; }
    public void setVida(int vida) {
        if (vida <= 0) throw new IllegalArgumentException("La vida debe ser mayor que cero.");
        this.vida = vida;
    }

    public int getAtaque() { return ataque; }
    public void setAtaque(int ataque) {
        if (ataque <= 0) throw new IllegalArgumentException("El ataque debe ser mayor que cero.");
        this.ataque = ataque;
    }

    public int getDefensa() { return defensa; }
    public void setDefensa(int defensa) {
        if (defensa <= 0) throw new IllegalArgumentException("La defensa debe ser mayor que cero.");
        this.defensa = defensa;
    }

    public int getAlcance() { return alcance; }
    public void setAlcance(int alcance) {
        if (alcance <= 0) throw new IllegalArgumentException("El alcance debe ser mayor que cero.");
        this.alcance = alcance;
    }

    @Override
    public String toString() {
        return nombre + "," + vida + "," + ataque + "," + defensa + "," + alcance;
    }

    public static Personaje desdeString(String linea) {
        String[] partes = linea.split(",");
        if (partes.length == 5) {
            return new Personaje(
                partes[0].trim(),
                Integer.parseInt(partes[1].trim()),
                Integer.parseInt(partes[2].trim()),
                Integer.parseInt(partes[3].trim()),
                Integer.parseInt(partes[4].trim())
            );
        }
        return null;
    }
}