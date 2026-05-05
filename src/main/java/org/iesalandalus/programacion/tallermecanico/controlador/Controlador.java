package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.IModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.cascada.FabricaModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.FabricaVista;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Controlador {

    private IModelo modelo;
    private Vista vista;

    public Controlador(FabricaModelo fabricaModelo, FabricaVista fabricaVista) {
        Objects.requireNonNull(fabricaModelo, "La fábrica de modelo no puede ser nula.");
        Objects.requireNonNull(fabricaVista, "La fábrica de vista no puede ser nula.");
        this.modelo = fabricaModelo.crear();
        this.vista = fabricaVista.crear();
        this.vista.setControlador(this);
    }

    public void comenzar() {
        modelo.comenzar();
        vista.comenzar();
    }

    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }

    public void insertar(Cliente cliente) {
        modelo.insertar(cliente);
    }

    public void insertar(Vehiculo vehiculo) {
        modelo.insertar(vehiculo);
    }

    public void insertar(Trabajo trabajo) {
        modelo.insertar(trabajo);
    }

    public Cliente buscar(Cliente cliente) {
        return modelo.buscar(cliente);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        return modelo.buscar(vehiculo);
    }

    public Trabajo buscar(Trabajo trabajo) {
        return modelo.buscar(trabajo);
    }

    public void modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        modelo.modificar(cliente, nombre, telefono);
    }

    public void anadirHoras(Trabajo trabajo, int horas) {
        modelo.anadirHoras(trabajo, horas);
    }

    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) {
        modelo.anadirPrecioMaterial(trabajo, precioMaterial);
    }

    public void cerrar(Trabajo trabajo, LocalDate fechaFin) {
        modelo.cerrar(trabajo, fechaFin);
    }

    public void borrar(Cliente cliente) {
        modelo.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) {
        modelo.borrar(vehiculo);
    }

    public void borrar(Trabajo trabajo) {
        modelo.borrar(trabajo);
    }

    public List<Cliente> getClientes() {
        return modelo.getClientes();
    }

    public List<Vehiculo> getVehiculos() {
        return modelo.getVehiculos();
    }

    public List<Trabajo> getTrabajos() {
        return modelo.getTrabajos();
    }

    public List<Trabajo> getTrabajos(Vehiculo vehiculo) {
        return modelo.getTrabajos(vehiculo);
    }
}
