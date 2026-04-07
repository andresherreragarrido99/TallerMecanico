package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.util.ArrayList;
import java.util.List;

public class Vehiculos {

    private List<Vehiculo> vehiculos;

    public Vehiculos() {
        vehiculos = new ArrayList<>();
    }

    public List<Vehiculo> get() {
        return new ArrayList<>(vehiculos);
    }

    public void insertar(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new NullPointerException("No se puede insertar un vehículo nulo.");
        }
        if (vehiculos.contains(vehiculo)) {
            throw new IllegalArgumentException("Ya existe un vehículo con esa matrícula.");
        }
        vehiculos.add(vehiculo);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        int indice = vehiculos.indexOf(vehiculo);
        if (indice == -1) {
            return null;
        }
        return vehiculos.get(indice);
    }

    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        if (vehiculo == null) {
            throw new NullPointerException("No se puede borrar un vehículo nulo.");
        }
        if (!vehiculos.remove(vehiculo)) {
            throw new TallerMecanicoExcepcion("El vehículo a borrar no existe.");
        }
    }
}