package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {

    private Pedido pedido;
    private ProductoMenu hamburguesa;
    private ProductoMenu papas;

    @BeforeEach
    void setUp() {
        pedido = new Pedido("Sergio Sandoval", "Calle Lejos");
        hamburguesa = new ProductoMenu("Hamburguesa Sencilla", 12000);
        papas = new ProductoMenu("Papas Medianas", 5000);
    }

    @AfterEach
    void tearDown() throws Exception 
    {
    }

    @Test
    void testGetIdPedido() {
        int id = pedido.getIdPedido();
        assertTrue(id >= 0, "El id del pedido debería ser un número no negativo.");
    }

    @Test
    void testGetNombreCliente() {
        assertEquals("Sergio Sandoval", pedido.getNombreCliente(), "El nombre del cliente no es el esperado.");
    }

    @Test
    void testAgregarProducto() {
        pedido.agregarProducto(hamburguesa);
        pedido.agregarProducto(papas);

        // Precio esperado neto: 12000 + 5000 = 17000
        // IVA esperado: 17000 * 0.19 ≈ 3230
        // Precio total esperado: 17000 + 3230 = 20230

        assertEquals(20230, pedido.getPrecioTotalPedido(), "El precio total del pedido no es el esperado.");
    }

    @Test
    void testGenerarTextoFactura() {
        pedido.agregarProducto(hamburguesa);
        pedido.agregarProducto(papas);

        String facturaGenerada = pedido.generarTextoFactura();

        assertTrue(facturaGenerada.contains("Cliente: Sergio Sandoval"), "La factura debe contener el nombre del cliente.");
        assertTrue(facturaGenerada.contains("Dirección: Calle Lejos"), "La factura debe contener la dirección del cliente.");
        assertTrue(facturaGenerada.contains("Hamburguesa Sencilla"), "La factura debe contener la hamburguesa.");
        assertTrue(facturaGenerada.contains("Papas Medianas"), "La factura debe contener las papas.");
        assertTrue(facturaGenerada.contains("Precio Neto:  17000"), "La factura debe mostrar el precio neto correcto.");
        assertTrue(facturaGenerada.contains("IVA:          3230"), "La factura debe mostrar el IVA correcto.");
        assertTrue(facturaGenerada.contains("Precio Total: 20230"), "La factura debe mostrar el precio total correcto.");
    }

    @Test
    void testGuardarFactura() throws FileNotFoundException {
        pedido.agregarProducto(hamburguesa);
        pedido.agregarProducto(papas);

        File archivo = new File("test_factura.txt");
        pedido.guardarFactura(archivo);

        // Leemos el archivo para comprobar que el contenido corresponde
        Scanner scanner = new Scanner(archivo);
        StringBuilder contenido = new StringBuilder();
        while (scanner.hasNextLine()) {
            contenido.append(scanner.nextLine()).append("\n");
        }
        scanner.close();

        String textoArchivo = contenido.toString();

        assertTrue(textoArchivo.contains("Cliente: Sergio Sandoval"), "El archivo debe contener el nombre del cliente.");
        assertTrue(textoArchivo.contains("Dirección: Calle Lejos"), "El archivo debe contener la dirección del cliente.");
        assertTrue(textoArchivo.contains("Hamburguesa Sencilla"), "El archivo debe contener la hamburguesa.");
        assertTrue(textoArchivo.contains("Papas Medianas"), "El archivo debe contener las papas.");
        assertTrue(textoArchivo.contains("Precio Total: 20230"), "El archivo debe contener el precio total correcto.");

        // Elimina el archivo después de probar
        archivo.delete();
    }
}
