package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.util.ArrayList;
import java.util.List;

public class Clientes {

    private List<Cliente> clientes;

    public Clientes() {
        clientes = new ArrayList<>();
    }

    public List<Cliente> get() {
        return new ArrayList<>(clientes);
    }

    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        if (cliente == null) {
            throw new NullPointerException("No se puede insertar un cliente nulo.");
        }
        if (clientes.contains(cliente)) {
            throw new TallerMecanicoExcepcion("Ya existe un cliente con ese DNI.");
        }
        clientes.add(new Cliente(cliente));
    }

    public Cliente buscar(Cliente cliente) {
        int indice = clientes.indexOf(cliente);
        if (indice == -1) {
            return null;
        }
        return new Cliente(clientes.get(indice));
    }

    public Cliente modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        if (cliente == null) {
            throw new NullPointerException("No se puede modificar un cliente nulo.");
        }
        int indice = clientes.indexOf(cliente);
        if (indice == -1) {
            throw new TallerMecanicoExcepcion("El cliente no existe.");
        }
        Cliente existente = clientes.get(indice);
        if (nombre != null && !nombre.isBlank()) {
            existente.setNombre(nombre);
        }
        if (telefono != null && !telefono.isBlank()) {
            existente.setTelefono(telefono);
        }
        return new Cliente(existente);
    }

    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        if (cliente == null) {
            throw new NullPointerException("No se puede borrar un cliente nulo.");
        }
        if (!clientes.remove(cliente)) {
            throw new TallerMecanicoExcepcion("El cliente a borrar no existe.");
        }
    }
}