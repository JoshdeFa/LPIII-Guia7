package Ejercicio_2;

import java.io.Serializable;

public class Personaje implements Serializable {
    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private int alcance;
    private int nivel;

    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance, int nivel) {
        if (vida <= 0 || ataque <= 0 || defensa <= 0 || alcance <= 0 || nivel <= 0) {
            throw new IllegalArgumentException("Todos los atributos y el nivel deben ser mayores que cero.");
        }
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.alcance = alcance;
        this.nivel = nivel;
    }

    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        this(nombre, vida, ataque, defensa, alcance, 1);
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

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) {
        if (nivel <= 0) throw new IllegalArgumentException("El nivel debe ser mayor que cero.");
        this.nivel = nivel;
    }

    public void subirNivel() {
        this.nivel++;
        this.vida += 2;
        this.ataque += 2;
        this.defensa += 1;
        this.alcance += 1;
    }

    @Override
    public String toString() {
        return nombre + "," + vida + "," + ataque + "," + defensa + "," + alcance + "," + nivel;
    }

    public static Personaje desdeString(String linea) {
        String[] partes = linea.split(",");
        if (partes.length >= 5) {
            String nom = partes[0].trim();
            int v = Integer.parseInt(partes[1].trim());
            int atq = Integer.parseInt(partes[2].trim());
            int def = Integer.parseInt(partes[3].trim());
            int alc = Integer.parseInt(partes[4].trim());
            int niv = partes.length >= 6 ? Integer.parseInt(partes[5].trim()) : 1;
            return new Personaje(nom, v, atq, def, alc, niv);
        }
        return null;
    }
}