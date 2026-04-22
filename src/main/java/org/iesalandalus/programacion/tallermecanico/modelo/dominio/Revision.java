package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

public class Revision {

    public static final float PRECIO_HORA = 30f;
    public static final float PRECIO_DIA = 10f;
    public static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Cliente cliente;
    private Vehiculo vehiculo;
    private static LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
        fechaFin = null;
        this.horas = 0;
        this.precioMaterial = 0;
    }

    public Revision(Revision revision) {
        if (revision == null) {
            throw new NullPointerException("La revisión no puede ser nula.");
        }

        cliente = revision.getCliente();
        vehiculo = revision.getVehiculo();
        fechaInicio = revision.getFechaInicio();
        fechaFin = revision.getFechaFin();
        horas = revision.getHoras();
        precioMaterial = revision.getPrecioMaterial();
    }

    private void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("El cliente no puede ser nulo.");
        }
        this.cliente = cliente;
    }

    private void setVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new NullPointerException("El vehículo no puede ser nulo.");
        }
        this.vehiculo = vehiculo;
    }

    private void setFechaInicio(LocalDate fechaInicio) {
        if (fechaInicio == null) {
            throw new NullPointerException("La fecha de inicio no puede ser nula.");
        }
        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.fechaInicio = fechaInicio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public static LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public int getHoras() {
        return horas;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    public void anadirHoras(int horas) throws TallerMecanicoExcepcion {
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        if (fechaFin != null) {
            throw new TallerMecanicoExcepcion("No se puede añadir horas, ya que la revisión está cerrada.");
        }
        this.horas += horas;
    }

    public void anadirPrecioMaterial(float precioMaterial) throws TallerMecanicoExcepcion {
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        if (fechaFin != null) {
            throw new TallerMecanicoExcepcion("No se puede añadir precio del material, ya que la revisión está cerrada.");
        }
        this.precioMaterial += precioMaterial;
    }

    public void cerrar(LocalDate fechaFin) throws TallerMecanicoExcepcion {
        if (fechaFin == null) {
            throw new NullPointerException("La fecha de fin no puede ser nula.");
        }
        if (fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        if (this.fechaFin != null) {
            throw new TallerMecanicoExcepcion("La revisión ya está cerrada.");
        }

        this.fechaFin = fechaFin;
    }
    public boolean estaCerrada() {
        return fechaFin != null;
    }

    public float getPrecio() {
        if (fechaFin == null) {
            return 0;
        }

        long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin) ;
        return horas * PRECIO_HORA + dias * PRECIO_DIA + precioMaterial * 1.5f;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, vehiculo, fechaInicio);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Revision other)) return false;
        return Objects.equals(cliente, other.cliente)
                && Objects.equals(vehiculo, other.vehiculo)
                && Objects.equals(fechaInicio, other.fechaInicio);
    }

    @Override
    public String toString() {
        String inicio = fechaInicio.format(FORMATO_FECHA);

        if (fechaFin == null) {
            return String.format("%s - %s: (%s - ), %d horas, %.2f € en material",
                    cliente, vehiculo, inicio, horas, precioMaterial);
        }

        String fin = fechaFin.format(FORMATO_FECHA);
        return String.format("%s - %s: (%s - %s), %d horas, %.2f € en material, %.2f € total",
                cliente, vehiculo, inicio, fin, horas, precioMaterial, getPrecio());
    }
}