package org.iesalandalus.programacion.tallermecanico.vista;

public enum FabricaVista {
    CONSOLA {
        @Override
        public Vista crear() {
            return new Vista();
        }
    };

    public abstract Vista crear();
}

