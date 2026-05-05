package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public abstract class Persona {
    protected String nombre;
    protected String dni;
    protected String telefono;

    protected Persona(String nombre, String dni, String telefono) {
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    protected Persona(Persona persona) {
        if (persona == null) {
            throw new NullPointerException("No es posible copiar una persona nula.");
        }
        this.nombre = persona.nombre;
        this.dni = persona.dni;
        this.telefono = persona.telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract void setNombre(String nombre);

    public String getDni() {
        return dni;
    }

    protected abstract void setDni(String dni);

    public String getTelefono() {
        return telefono;
    }

    public abstract void setTelefono(String telefono);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return Objects.equals(dni, persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}
