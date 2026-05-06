package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.HashMap;
import java.util.Map;

public enum Opcion {

    RESERVAR_CITA(1, "Reservar cita"),
    BORRAR_CLIENTE(2, "Borrar cliente"),
    LISTAR_CLIENTES(3, "Listar clientes"),
    MOSTRAR_CLIENTE(4, "Mostrar cliente"),
    MOSTRAR_REVISION(5, "Mostrar revisión"),
    MOSTRAR_VEHICULO(6, "Mostrar vehículo"),
    MOSTRAR_REVISIONES(7, "Mostrar revisiones"),
    MOSTRAR_VEHICULOS(8, "Mostrar vehículos"),
    MOSTRAR_REVISIONES_CLIENTE(9, "Mostrar revisiones de un cliente"),
    MOSTRAR_REVISIONES_VEHICULO(10, "Mostrar revisiones de un vehículo"),
    ANADIR_CLIENTE(11, "Añadir cliente"),
    ANADIR_REVISION(12, "Añadir revisión"),
    ANADIR_VEHICULO(13, "Añadir vehículo"),
    BORRAR_REVISION(14, "Borrar revisión"),
    BORRAR_VEHICULO(15, "Borrar vehículo"),
    SALIR(0, "Salir"),
    MOSTRAR_ESTADISTICAS_MENSUALES(24, "Mostrar estadísticas mensuales");


    private static final Map<Integer, Opcion> MAPA_OPCIONES = new HashMap<>();

    static {
        for (Opcion opcion : values()) {
            MAPA_OPCIONES.put(opcion.codigo, opcion);
        }
    }

    private final int codigo;
    private final String texto;

    Opcion(int codigo, String texto) {
        this.codigo = codigo;
        this.texto = texto;
    }

    public static boolean esValida(int codigo) {
        return MAPA_OPCIONES.containsKey(codigo);
    }

    public static Opcion get(int codigo) {
        Opcion opcion = MAPA_OPCIONES.get(codigo);
        if (opcion == null) {
            throw new IllegalArgumentException("Opción no válida.");
        }
        return opcion;
    }

    @Override
    public String toString() {
        return String.format("%d - %s", codigo, texto);
    }
}