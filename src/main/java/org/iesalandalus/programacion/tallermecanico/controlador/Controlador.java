package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.cascada.ModeloCascada;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Controlador {

    private ModeloCascada modeloCascada;
    private Vista vista;

    public Controlador(ModeloCascada modeloCascada, Vista vista) {
        Objects.requireNonNull(modeloCascada, "El modelo no puede ser nulo.");
        Objects.requireNonNull(vista, "La vista no puede ser nula.");
        this.modeloCascada = modeloCascada;
        this.vista = vista;
        this.vista.setControlador(this);
    }

    public void comenzar() {
        modeloCascada.comenzar();
        vista.comenzar();
    }

    public void terminar() {
        modeloCascada.terminar();
        vista.terminar();
    }

    public void insertar(Cliente cliente) {
        modeloCascada.insertar(cliente);
    }

    public void insertar(Vehiculo vehiculo) {
        modeloCascada.insertar(vehiculo);
    }

    public void insertar(Trabajo trabajo) {
        modeloCascada.insertar(trabajo);
    }

    public Cliente buscar(Cliente cliente) {
        return modeloCascada.buscar(cliente);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        return modeloCascada.buscar(vehiculo);
    }

    public Trabajo buscar(Trabajo trabajo) {
        return modeloCascada.buscar(trabajo);
    }

    public void modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        modeloCascada.modificar(cliente, nombre, telefono);
    }

    public void anadirHoras(Trabajo trabajo, int horas) {
        modeloCascada.anadirHoras(trabajo, horas);
    }

    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) {
        modeloCascada.anadirPrecioMaterial(trabajo, precioMaterial);
    }

    public void cerrar(Trabajo trabajo, LocalDate fechaFin) {
        modeloCascada.cerrar(trabajo, fechaFin);
    }

    public void borrar(Cliente cliente) {
        modeloCascada.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) {
        modeloCascada.borrar(vehiculo);
    }

    public void borrar(Trabajo trabajo) {
        modeloCascada.borrar(trabajo);
    }

    public List<Cliente> getClientes() {
        return modeloCascada.getClientes();
    }

    public List<Vehiculo> getVehiculos() {
        return modeloCascada.getVehiculos();
    }

    public List<Trabajo> getTrabajos() {
        return modeloCascada.getTrabajos();
    }

    public List<Trabajo> getTrabajos(Vehiculo vehiculo) {
        return modeloCascada.getTrabajos(vehiculo);
    }
}
