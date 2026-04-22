package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public class Mecanico {

    private String nombre;
    private String dni;
    private String telefono;
    private static final String ER_DNI = "\\d{8}[A-HJ-NP-TV-Z]";
    private static final String ER_TELEFONO = "[679]\\d{8}";

    public Mecanico(String nombre, String dni, String telefono) {
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    public Mecanico(Mecanico mecanico) {
        if (mecanico == null) {
            throw new NullPointerException("ERROR: No se puede copiar un mecánico nulo.");
        }
        this.nombre = mecanico.nombre;
        this.dni = mecanico.dni;
        this.telefono = mecanico.telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new NullPointerException("ERROR: El nombre no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    private void setDni(String dni) {
        if (dni == null || !dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
        }
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || !telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mecanico mecanico)) return false;
        return Objects.equals(dni, mecanico.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s", nombre, dni, telefono);
    }
}