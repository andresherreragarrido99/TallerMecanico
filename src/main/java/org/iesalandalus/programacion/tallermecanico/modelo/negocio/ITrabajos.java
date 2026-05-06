package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.TipoTrabajo;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ITrabajos {
    List<Trabajo> get();
    List<Trabajo> get(Vehiculo vehiculo);
    void insertar(Trabajo trabajo);
    Trabajo buscar(Trabajo trabajo);
    void borrar(Trabajo trabajo);
    void anadirHoras(Trabajo trabajo, int horas);
    void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial);
    void cerrar(Trabajo trabajo, LocalDate fechaFin);
    Map<TipoTrabajo, Integer> getEstadisticasMensuales(int mes);
}