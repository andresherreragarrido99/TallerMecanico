package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import java.time.LocalDate;
import java.util.List;

public interface IModelo {
    void comenzar();
    void terminar();
    void insertar(Cliente cliente);
    void insertar(Vehiculo vehiculo);
    void insertar(Trabajo trabajo);
    Cliente buscar(Cliente cliente);
    Vehiculo buscar(Vehiculo vehiculo);
    Trabajo buscar(Trabajo trabajo);
    void modificar(Cliente cliente, String nombre, String telefono);
    void borrar(Cliente cliente);
    void borrar(Vehiculo vehiculo);
    void borrar(Trabajo trabajo);
    List<Cliente> getClientes();
    List<Vehiculo> getVehiculos();
    List<Trabajo> getTrabajos();
    List<Trabajo> getTrabajos(Vehiculo vehiculo);
    void anadirHoras(Trabajo trabajo, int horas);
    void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial);
    void cerrar(Trabajo trabajo, LocalDate fechaFin);
}