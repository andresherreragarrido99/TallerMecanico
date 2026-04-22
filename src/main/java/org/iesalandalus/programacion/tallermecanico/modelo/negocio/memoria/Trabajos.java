package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;
import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Trabajos implements ITrabajos {

    private List<Trabajo> coleccionTrabajos;

    public Trabajos() {
        coleccionTrabajos = new ArrayList<>();
    }

    @Override
    public List<Trabajo> get() {
        List<Trabajo> copia = new ArrayList<>();
        for (Trabajo trabajo : coleccionTrabajos) {
            copia.add(Trabajo.copiar(trabajo));
        }
        return copia;
    }

    @Override
    public List<Trabajo> get(Vehiculo vehiculo) {
        List<Trabajo> trabajosVehiculo = new ArrayList<>();
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getVehiculo().equals(vehiculo)) {
                trabajosVehiculo.add(Trabajo.copiar(trabajo));
            }
        }
        return trabajosVehiculo;
    }

    @Override
    public void insertar(Trabajo trabajo) {
        if (trabajo == null) {
            throw new NullPointerException("ERROR: No se puede insertar un trabajo nulo.");
        }
        if (buscarTrabajoAbierto(trabajo.getVehiculo()) != null) {
            throw new IllegalArgumentException("ERROR: El vehículo ya tiene un trabajo abierto.");
        }
        coleccionTrabajos.add(Trabajo.copiar(trabajo));
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        int indice = coleccionTrabajos.indexOf(trabajo);
        return (indice == -1) ? null : Trabajo.copiar(coleccionTrabajos.get(indice));
    }

    @Override
    public void borrar(Trabajo trabajo) {
        if (trabajo == null) {
            throw new NullPointerException("ERROR: No se puede borrar un trabajo nulo.");
        }
        if (!coleccionTrabajos.remove(trabajo)) {
            throw new IllegalArgumentException("ERROR: No existe ningún trabajo igual.");
        }
    }

    @Override
    public void anadirHoras(Trabajo trabajo, int horas) {
        Trabajo encontrado = buscarTrabajoAbierto(trabajo.getVehiculo());
        if (encontrado == null) {
            throw new IllegalArgumentException("ERROR: No existe ningún trabajo abierto para dicho vehículo.");
        }
        encontrado.anadirHoras(horas);
    }

    @Override
    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) {
        Trabajo encontrado = buscarTrabajoAbierto(trabajo.getVehiculo());
        if (encontrado == null) {
            throw new IllegalArgumentException("ERROR: No existe ningún trabajo abierto para dicho vehículo.");
        }
        encontrado.anadirPrecioMaterial(precioMaterial);
    }

    @Override
    public void cerrar(Trabajo trabajo, LocalDate fechaFin) {
        Trabajo encontrado = buscarTrabajoAbierto(trabajo.getVehiculo());
        if (encontrado == null) {
            throw new IllegalArgumentException("ERROR: No existe ningún trabajo abierto para dicho vehículo.");
        }
        encontrado.cerrar(fechaFin);
    }

    private Trabajo buscarTrabajoAbierto(Vehiculo vehiculo) {
        for (Trabajo t : coleccionTrabajos) {
            if (t.getVehiculo().equals(vehiculo) && !t.estaCerrado()) {
                return t;
            }
        }
        return null;
    }
}