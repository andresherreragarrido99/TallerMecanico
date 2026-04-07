package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Consola {

    public static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";
    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);

    private Consola() {
    }

    public static void mostrarCabecera(String mensaje) {
        System.out.println(mensaje);
        System.out.println("-".repeat(mensaje.length()));
    }

    public static void mostrarMenu() {
        mostrarCabecera("Gestión del taller mecánico");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return Entrada.cadena();
    }

    public static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Entrada.entero();
    }

    public static double leerReal(String mensaje) {
        System.out.print(mensaje);
        return Entrada.real();
    }

    public static LocalDate leerFecha(String mensaje) {
        while (true) {
            try {
                String cadena = leerCadena(mensaje + " (" + CADENA_FORMATO_FECHA + "): ");
                return LocalDate.parse(cadena, FORMATO_FECHA);
            } catch (Exception e) {
                System.out.println("Fecha no válida. Inténtalo de nuevo.");
            }
        }
    }

    public static Opcion elegirOpcion() {
        int codigo;
        do {
            codigo = leerEntero("Elige una opción: ");
            if (!Opcion.esValida(codigo)) {
                System.out.println("Opción no válida.");
            }
        } while (!Opcion.esValida(codigo));
        return Opcion.get(codigo);
    }

    public static Cliente leerCliente() throws TallerMecanicoExcepcion {
        String nombre = leerCadena("Nombre: ");
        String dni = leerCadena("DNI: ");
        String telefono = leerCadena("Teléfono: ");
        return new Cliente(nombre, dni, telefono);
    }

    public static Vehiculo leerVehiculo() {
        String marca = leerCadena("Marca: ");
        String modelo = leerCadena("Modelo: ");
        String matricula = leerCadena("Matrícula: ");
        return new Vehiculo(marca, modelo, matricula);
    }

    public static Revision leerRevision() throws TallerMecanicoExcepcion {
        Cliente cliente = leerCliente();
        Vehiculo vehiculo = leerVehiculo();
        LocalDate fechaInicio = leerFecha("Fecha de inicio");
        return new Revision(cliente, vehiculo, fechaInicio);
    }

    public static void mostrarCliente(Cliente cliente) {
        System.out.println(cliente);
    }

    public static void mostrarClientes(java.util.List<Cliente> clientes) {
        clientes.forEach(System.out::println);
    }

    public static void mostrarVehiculo(Vehiculo vehiculo) {
        System.out.println(vehiculo);
    }

    public static void mostrarVehiculos(java.util.List<Vehiculo> vehiculos) {
        vehiculos.forEach(System.out::println);
    }

    public static void mostrarRevision(Revision revision) {
        System.out.println(revision);
    }

    public static void mostrarRevisiones(java.util.List<Revision> revisiones) {
        revisiones.forEach(System.out::println);
    }
}