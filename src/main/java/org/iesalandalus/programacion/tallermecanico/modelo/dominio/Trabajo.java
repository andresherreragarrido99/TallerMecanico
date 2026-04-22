package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Trabajo {

    protected static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;

    protected Trabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
        this.horas = 0;
        this.precioMaterial = 0;
    }

    protected Trabajo(Trabajo trabajo) {
        if (trabajo == null) {
            throw new NullPointerException("ERROR: No se puede copiar un trabajo nulo.");
        }
        this.cliente = new Cliente(trabajo.cliente);
        this.vehiculo = trabajo.vehiculo;
        this.fechaInicio = trabajo.fechaInicio;
        this.fechaFin = trabajo.fechaFin;
        this.horas = trabajo.horas;
        this.precioMaterial = trabajo.precioMaterial;
    }

    public static Trabajo copiar(Trabajo trabajo) {
        if (trabajo instanceof Revision revision) {
            return new Revision(revision);
        }
        return null;
    }

    public static Trabajo get(Vehiculo vehiculo) {
        return new Revision(Cliente.get("11111111H"), vehiculo, LocalDate.now());
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("ERROR: El cliente no puede ser nulo.");
        }
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    private void setVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new NullPointerException("ERROR: El vehículo no puede ser nulo.");
        }
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(LocalDate fechaInicio) {
        if (fechaInicio == null) {
            throw new NullPointerException("ERROR: La fecha de inicio no puede ser nula.");
        }
        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("ERROR: La fecha de inicio no puede ser futura.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    private void setFechaFin(LocalDate fechaFin) {
        if (fechaFin == null) {
            throw new NullPointerException("ERROR: La fecha de fin no puede ser nula.");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("ERROR: La fecha de fin no puede ser anterior a la de inicio.");
        }
        if (fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("ERROR: La fecha de fin no puede ser futura.");
        }
        this.fechaFin = fechaFin;
    }

    public int getHoras() {
        return horas;
    }

    public void anadirHoras(int horas) {
        if (estaCerrado()) {
            throw new IllegalStateException("ERROR: No se pueden añadir horas a un trabajo cerrado.");
        }
        if (horas <= 0) {
            throw new IllegalArgumentException("ERROR: Las horas a añadir deben ser mayores que cero.");
        }
        this.horas += horas;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    public void anadirPrecioMaterial(float precioMaterial) {
        if (estaCerrado()) {
            throw new IllegalStateException("ERROR: No se puede añadir precio de material a un trabajo cerrado.");
        }
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("ERROR: El precio del material a añadir debe ser mayor que cero.");
        }
        this.precioMaterial += precioMaterial;
    }

    public boolean estaCerrado() {
        return fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) {
        if (estaCerrado()) {
            throw new IllegalStateException("ERROR: El trabajo ya está cerrado.");
        }
        setFechaFin(fechaFin);
    }

    public float getPrecioFinal() {
        return (getHoras() * 30) + getPrecioMaterial() + getPrecioEspecifico();
    }

    public abstract float getPrecioEspecifico();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trabajo trabajo)) return false;
        return Objects.equals(cliente, trabajo.cliente) &&
                Objects.equals(vehiculo, trabajo.vehiculo) &&
                Objects.equals(fechaInicio, trabajo.fechaInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, vehiculo, fechaInicio);
    }
}