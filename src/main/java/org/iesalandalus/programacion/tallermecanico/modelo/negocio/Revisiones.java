package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Revisiones {

    private List<Revision> revisiones;

    public Revisiones() {
        revisiones = new ArrayList<>();
    }

    public List<Revision> get() {
        List<Revision> copia = new ArrayList<>();
        for (Revision r : revisiones) {
            copia.add(new Revision(r));
        }
        return copia;
    }

    public List<Revision> get(Cliente cliente) {
        List<Revision> resultado = new ArrayList<>();
        for (Revision r : revisiones) {
            if (r.getCliente().equals(cliente)) {
                resultado.add(new Revision(r));
            }
        }
        return resultado;
    }

    public List<Revision> get(Vehiculo vehiculo) {
        List<Revision> resultado = new ArrayList<>();
        for (Revision r : revisiones) {
            if (r.getVehiculo().equals(vehiculo)) {
                resultado.add(new Revision(r));
            }
        }
        return resultado;
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion {
        if (revision == null) {
            throw new NullPointerException("No se puede insertar una revisión nula.");
        }
        comprobarRevision(revision);
        if (revisiones.contains(revision)) {
            throw new TallerMecanicoExcepcion("Ya existe una revisión igual.");
        }
        revisiones.add(new Revision(revision));
    }

    public void comprobarRevision(Revision revision) throws TallerMecanicoExcepcion {
        Cliente cliente = revision.getCliente();
        Vehiculo vehiculo = revision.getVehiculo();
        LocalDate fechaInicio = revision.getFechaInicio();

        for (Revision r : revisiones) {
            boolean mismoCliente = r.getCliente().equals(cliente);
            boolean mismoVehiculo = r.getVehiculo().equals(vehiculo);

            if (r.getFechaFin() == null && (mismoCliente || mismoVehiculo)) {
                throw new TallerMecanicoExcepcion("Ya existe una revisión sin cerrar para el cliente o vehículo.");
            }
            if (r.getFechaFin() != null && (mismoCliente || mismoVehiculo)
                    && r.getFechaFin().isAfter(fechaInicio)) {
                throw new TallerMecanicoExcepcion("Existe una revisión cerrada con fecha de fin posterior a la nueva fecha de inicio.");
            }
        }
    }

    public Revision getRevision(Revision revision) throws TallerMecanicoExcepcion {
        if (revision == null) {
            throw new NullPointerException("La revisión no puede ser nula.");
        }
        int indice = revisiones.indexOf(revision);
        if (indice == -1) {
            throw new TallerMecanicoExcepcion("La revisión no existe.");
        }
        return revisiones.get(indice);
    }

    public Revision anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion {
        Revision existente = getRevision(revision);
        existente.anadirHoras(horas);
        return new Revision(existente);
    }

    public Revision anadirPrecioMaterial(Revision revision, float precio) throws TallerMecanicoExcepcion {
        Revision existente = getRevision(revision);
        existente.anadirPrecioMaterial(precio);
        return new Revision(existente);
    }

    public Revision cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Revision existente = getRevision(revision);
        existente.cerrar(fechaFin);
        return new Revision(existente);
    }

    public Revision buscar(Revision revision) {
        int indice = revisiones.indexOf(revision);
        if (indice == -1) {
            return null;
        }
        return new Revision(revisiones.get(indice));
    }

    public void borrar(Revision revision) throws TallerMecanicoExcepcion {
        if (revision == null) {
            throw new NullPointerException("No se puede borrar una revisión nula.");
        }
        if (!revisiones.remove(revision)) {
            throw new TallerMecanicoExcepcion("La revisión a borrar no existe.");
        }
    }
}