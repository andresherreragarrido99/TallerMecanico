package org.iesalandalus.programacion.tallermecanico;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.cascada.FabricaModelo;
import org.iesalandalus.programacion.tallermecanico.vista.FabricaVista;

public class Main {
    public static void main(String[] args) {
        Controlador controlador = new Controlador(FabricaModelo.MEMORIA, FabricaVista.CONSOLA);
        controlador.comenzar();
    }
}