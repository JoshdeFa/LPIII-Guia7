package Actividad_567_SB;

import Actividad_SB.Fecha_SB;

public class Alumno_SB extends PersonaSerial_SB {
    private Fecha_SB fechaMatricula;

    public Alumno_SB(String nif, String nombre, int edad, Fecha_SB fechaMatricula) {
        super(nif, nombre, edad);
        this.fechaMatricula = new Fecha_SB();
        setFechaMatricula(fechaMatricula);
    }

    public Alumno_SB() {
    }

    public Fecha_SB getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Fecha_SB fechaMatricula) {
        this.fechaMatricula.setDia(fechaMatricula.getDia());
        this.fechaMatricula.setMes(fechaMatricula.getMes());
        this.fechaMatricula.setAnio(fechaMatricula.getAnio());
    }
}