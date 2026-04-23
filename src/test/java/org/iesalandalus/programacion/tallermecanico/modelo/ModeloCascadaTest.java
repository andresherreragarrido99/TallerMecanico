package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.cascada.ModeloCascada;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ModeloCascadaTest {

    @Mock
    private static Clientes clientes;
    @Mock
    private static Vehiculos vehiculos;
    @Mock
    private static Revisiones revisiones;
    @InjectMocks
    private ModeloCascada modeloCascada;

    private static Cliente cliente;
    private static Vehiculo vehiculo;
    private static Revision revision;

    private AutoCloseable procesadorAnotaciones;
    private MockedConstruction<Cliente> controladorCreacionMockCliente;
    private MockedConstruction<Clientes> controladorCreacionMockClientes;
    private MockedConstruction<Vehiculos> controladorCreacionMockVehiculos;
    private MockedConstruction<Revision> controladorCreacionMockRevision;
    private MockedConstruction<Revisiones> controladorCreacionMockRevisiones;


    @BeforeAll
    public static void setup() {
        cliente = mock();
        when(cliente.getNombre()).thenReturn("Bob Esponja");
        when(cliente.getDni()).thenReturn("11223344B");
        when(cliente.getTelefono()).thenReturn("950112233");
        vehiculo = mock();
        when(vehiculo.marca()).thenReturn("Seat");
        when(vehiculo.modelo()).thenReturn("León");
        when(vehiculo.matricula()).thenReturn("1234BCD");
        revision = mock();
        when(revision.getCliente()).thenReturn(cliente);
        when(revision.getVehiculo()).thenReturn(vehiculo);
        when(revision.getFechaInicio()).thenReturn(LocalDate.now().minusDays(1));
    }

    @BeforeEach
    void init() {
        controladorCreacionMockCliente = mockConstruction(Cliente.class);
        controladorCreacionMockClientes = mockConstruction(Clientes.class);
        controladorCreacionMockVehiculos = mockConstruction(Vehiculos.class);
        controladorCreacionMockRevision = mockConstruction(Revision.class);
        controladorCreacionMockRevisiones = mockConstruction(Revisiones.class);
        procesadorAnotaciones = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void close() throws Exception {
        procesadorAnotaciones.close();
        controladorCreacionMockCliente.close();
        controladorCreacionMockClientes.close();
        controladorCreacionMockVehiculos.close();
        controladorCreacionMockRevision.close();
        controladorCreacionMockRevisiones.close();
    }

    @Test
    void terminarNoHaceNada() {
        assertDoesNotThrow(() -> modeloCascada.terminar());
    }

    @Test
    void insertarClienteLlamaClientesInsertar() {
        assertDoesNotThrow(() -> modeloCascada.insertar(cliente));
        assertDoesNotThrow(() -> verify(clientes).insertar(any(Cliente.class)));
        assertDoesNotThrow(() -> verify(clientes, times(0)).insertar(cliente));
    }

    @Test
    void insertarVehiculoLlamaVehiculosInsertar() {
        assertDoesNotThrow(() -> modeloCascada.insertar(vehiculo));
        assertDoesNotThrow(() -> verify(vehiculos).insertar(vehiculo));
    }

    @Test
    void insertarRevisionLlamaClientesBuscarVehiculosBuscarRevisionesInsertar() {
        InOrder orden = inOrder(clientes, vehiculos, revisiones);
        when(clientes.buscar(cliente)).thenReturn(cliente);
        when(vehiculos.buscar(vehiculo)).thenReturn(vehiculo);
        assertDoesNotThrow(() -> modeloCascada.insertar(revision));
        orden.verify(clientes).buscar(cliente);
        orden.verify(vehiculos).buscar(vehiculo);
        assertDoesNotThrow(() -> orden.verify(revisiones).insertar(any(Revision.class)));
        assertDoesNotThrow(() -> verify(revisiones, times(0)).insertar(revision));
    }

    @Test
    void buscarClienteLlamaClientesBuscar() {
        assertDoesNotThrow(() -> modeloCascada.insertar(cliente));
        when(clientes.buscar(cliente)).thenReturn(cliente);
        Cliente clienteEncontrado = modeloCascada.buscar(cliente);
        verify(clientes).buscar(cliente);
        assertNotSame(cliente, clienteEncontrado);
    }

    @Test
    void buscarVehiculoLlamaVehiculosBuscar() {
        assertDoesNotThrow(() -> modeloCascada.insertar(vehiculo));
        when(vehiculos.buscar(vehiculo)).thenReturn(vehiculo);
        modeloCascada.buscar(vehiculo);
        verify(vehiculos).buscar(vehiculo);
    }

    @Test
    void buscarRevisionLlamaRevisionesBuscar() {
        assertDoesNotThrow(() -> modeloCascada.insertar(revision));
        when(revisiones.buscar(revision)).thenReturn(revision);
        Revision revisionEncontrada = modeloCascada.buscar(revision);
        verify(revisiones).buscar(revision);
        assertNotSame(revision, revisionEncontrada);
    }

    @Test
    void modificarClienteLlamaClientesModificar() {
        assertDoesNotThrow(() -> modeloCascada.modificar(cliente, "Patricio Estrella", "950123456"));
        assertDoesNotThrow(() -> verify(clientes).modificar(cliente, "Patricio Estrella", "950123456"));
    }

    @Test
    void anadirHorasLlamaRevisionesAnadirHoras() {
        assertDoesNotThrow(() -> modeloCascada.anadirHoras(revision, 10));
        assertDoesNotThrow(() -> verify(revisiones).anadirHoras(revision, 10));
    }

    @Test
    void anadirPrecioMateriaLlamaRevisionesAnadirPrecioMaterial() {
        assertDoesNotThrow(() -> modeloCascada.anadirPrecioMaterial(revision, 100f));
        assertDoesNotThrow(() -> verify(revisiones).anadirPrecioMaterial(revision, 100f));
    }

    @Test
    void cerrarLlamaRevisionesCerrar() {
        assertDoesNotThrow(() -> modeloCascada.cerrar(revision, LocalDate.now()));
        assertDoesNotThrow(() -> verify(revisiones).cerrar(revision, LocalDate.now()));
    }

    @Test
    void borrarClienteLlamaRevisionesGetClienteRevisionesBorrarClientesBorrar() {
        simularClientesConRevisiones();
        InOrder orden = inOrder(clientes, revisiones);
        assertDoesNotThrow(() -> modeloCascada.borrar(cliente));
        orden.verify(revisiones).get(cliente);
        for (Revision revision : revisiones.get(cliente)) {
            assertDoesNotThrow(() -> orden.verify(revisiones).borrar(revision));
        }
        assertDoesNotThrow(() -> orden.verify(clientes).borrar(cliente));
    }

    private void simularClientesConRevisiones() {
        when(revisiones.get(cliente)).thenReturn(new ArrayList<>(List.of(mock(), mock())));
    }

    @Test
    void borrarVehiculoLlamaRevisionesGetVehiculoRevisionesBorrarVehiculosBorrar() {
        simularVehiculosConRevisiones();
        InOrder orden = inOrder(vehiculos, revisiones);
        assertDoesNotThrow(() -> modeloCascada.borrar(vehiculo));
        orden.verify(revisiones).get(vehiculo);
        for (Revision revision : revisiones.get(vehiculo)) {
            assertDoesNotThrow(() -> orden.verify(revisiones).borrar(revision));
        }
        assertDoesNotThrow(() -> orden.verify(vehiculos).borrar(vehiculo));
    }

    private void simularVehiculosConRevisiones() {
        when(revisiones.get(vehiculo)).thenReturn(new ArrayList<>(List.of(mock(), mock())));
    }

    @Test
    void borrarRevisionLlamaRevisionesBorrar() {
        assertDoesNotThrow(() -> modeloCascada.borrar(revision));
        assertDoesNotThrow(() -> verify(revisiones).borrar(revision));
    }

    @Test
    void getClientesLlamaClientesGet() {
        when(clientes.get()).thenReturn(new ArrayList<>(List.of(cliente)));
        List<Cliente> clientesExistentes = modeloCascada.getClientes();
        verify(clientes).get();
        assertNotSame(cliente, clientesExistentes.get(0));
    }

    @Test
    void getVehiculosLlamaVehiculosGet() {
        when(vehiculos.get()).thenReturn(new ArrayList<>(List.of(vehiculo)));
        List<Vehiculo> vehiculosExistentes = modeloCascada.getVehiculos();
        verify(vehiculos).get();
        assertSame(vehiculo, vehiculosExistentes.get(0));
    }

    @Test
    void getRevisionesLlamaRevisionesGet() {
        when(revisiones.get()).thenReturn(new ArrayList<>(List.of(revision)));
        List<Revision> revisionesExistentes = modeloCascada.getRevisiones();
        verify(revisiones).get();
        assertNotSame(revision, revisionesExistentes.get(0));
    }

    @Test
    void getRevisionesClienteLlamaRevisionesGetCliente() {
        when(revisiones.get(cliente)).thenReturn(new ArrayList<>(List.of(revision)));
        List<Revision> revisionesCliente = modeloCascada.getRevisiones(cliente);
        verify(revisiones).get(cliente);
        assertNotSame(revision,revisionesCliente.get(0));
    }

    @Test
    void getRevisionesVehiculoLlamaRevisionesGetVehiculo() {
        when(revisiones.get(vehiculo)).thenReturn(new ArrayList<>(List.of(revision)));
        List<Revision> revisionesVehiculo = modeloCascada.getRevisiones(vehiculo);
        verify(revisiones).get(vehiculo);
        assertNotSame(revision,revisionesVehiculo.get(0));
    }

}