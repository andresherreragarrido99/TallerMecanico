package org.iesalandalus.programacion.tallermecanico.modelo.cascada;

import org.iesalandalus.programacion.tallermecanico.modelo.IModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.*;

public abstract class ModeloCascada implements IModelo {

    private IClientes clientes;
    private IVehiculos vehiculos;
    private ITrabajos trabajos;
    private IFuenteDatos fuenteDatos;

    public ModeloCascada(IFuenteDatos fuenteDatos) {
        if (fuenteDatos == null) {
            throw new NullPointerException("ERROR: La fuente de datos no puede ser nula.");
        }
        this.fuenteDatos = fuenteDatos;
    }

    @Override
    public void comenzar() {
        clientes = fuenteDatos.crearClientes();
        vehiculos = fuenteDatos.crearVehiculos();
        trabajos = fuenteDatos.crearTrabajos();
    }

    @Override
    public void terminar() {
        System.out.println("Modelo finalizado.");
    }

    @Override
    public void insertar(Cliente cliente) {
        clientes.insertar(cliente);
    }

    @Override
    public void insertar(Vehiculo vehiculo) {
        vehiculos.insertar(vehiculo);
    }

    @Override
    public void insertar(Trabajo trabajo) {
        // Buscamos si existen para cumplir la integridad referencial
        Cliente cliente = clientes.buscar(trabajo.getCliente());
        Vehiculo vehiculo = vehiculos.buscar(trabajo.getVehiculo());

        if (cliente == null) {
            throw new IllegalArgumentException("ERROR: No existe el cliente del trabajo.");
        }
        if (vehiculo == null) {
            throw new IllegalArgumentException("ERROR: No existe el vehículo del trabajo.");
        }

        // Creamos el trabajo con las instancias reales de nuestra "BD" (memoria)
        // Usamos el método copiar de Trabajo para asegurar la jerarquía
        Trabajo nuevoTrabajo = Trabajo.copiar(trabajo);
        trabajos.insertar(nuevoTrabajo);
    }

    // ... Implementa el resto de métodos delegando en 'clientes', 'vehiculos' y 'trabajos' ...
    // Por ejemplo:
    @Override
    public Cliente buscar(Cliente cliente) { return clientes.buscar(cliente); }

    @Override
    public void anadirHoras(Trabajo trabajo, int horas) {
        trabajos.anadirHoras(trabajo, horas);
    }
}