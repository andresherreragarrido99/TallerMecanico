package org.iesalandalus.programacion.tallermecanico.modelo.cascada;

import org.iesalandalus.programacion.tallermecanico.modelo.IModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;

public enum FabricaModelo {
    MEMORIA {
        @Override
        public IModelo crear() {
            return new ModeloCascadaMemoria(FabricaFuenteDatos.MEMORIA.crear());
        }
    };

    public abstract IModelo crear();
}

