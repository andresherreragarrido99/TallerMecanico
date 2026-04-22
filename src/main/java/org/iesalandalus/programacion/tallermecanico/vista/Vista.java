package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

public class Vista {

    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        if (controlador == null) {
            throw new NullPointerException("El controlador no puede ser nulo.");
        }
        this.controlador = controlador;
    }

    public void comenzar() {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Opcion.SALIR);
    }

    public void terminar() {
        System.out.println("Hasta pronto.");
    }

    private void ejecutar(Opcion opcion) {
        try {
            switch (opcion) {
                case ANADIR_CLIENTE -> anadirCliente();
                case ANADIR_VEHICULO -> anadirVehiculo();
                case ANADIR_REVISION -> anadirRevision();
                case LISTAR_CLIENTES -> listarClientes();
                case MOSTRAR_CLIENTE -> listarClientes();
                case MOSTRAR_VEHICULOS -> listarVehiculos();
                case MOSTRAR_REVISIONES -> listarRevisiones();
                case BORRAR_CLIENTE -> borrarCliente();
                case BORRAR_VEHICULO -> borrarVehiculo();
                case BORRAR_REVISION -> borrarRevision();
                case SALIR -> terminar();
                default -> System.out.println("Opción aún no implementada.");
            }
        } catch (TallerMecanicoExcepcion | IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void anadirCliente() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Añadir cliente");
        Cliente cliente = Consola.leerCliente();
        controlador.insertar(cliente);
    }

    private void anadirVehiculo() {
        Consola.mostrarCabecera("Añadir vehículo");
        Vehiculo vehiculo = Consola.leerVehiculo();
        controlador.insertar(vehiculo);
    }

    private void anadirRevision() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Añadir revisión");
        Revision revision = Consola.leerRevision();
        controlador.insertar(revision);
    }

    private void listarClientes() {
        Consola.mostrarCabecera("Listado de clientes");
        Consola.mostrarClientes(controlador.getClientes());
    }

    private void listarVehiculos() {
        Consola.mostrarCabecera("Listado de vehículos");
        Consola.mostrarVehiculos(controlador.getVehiculos());
    }

    private void listarRevisiones() {
        Consola.mostrarCabecera("Listado de revisiones");
        Consola.mostrarRevisiones(controlador.getRevisiones());
    }

    private void borrarCliente() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Borrar cliente");
        String dni = Consola.leerCadena("DNI del cliente: ");
        Cliente cliente = Cliente.get(dni);
        controlador.borrar(cliente);
    }

    private void borrarVehiculo() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Borrar vehículo");
        String matricula = Consola.leerCadena("Matrícula del vehículo: ");
        Vehiculo vehiculo = Vehiculo.get(matricula);
        controlador.borrar(vehiculo);
    }

    private void borrarRevision() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Borrar revisión");
        Revision revision = Consola.leerRevision();
        controlador.borrar(revision);
    }
}