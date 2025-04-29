package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {

    private ProductoMenu hamburguesa;
    private ProductoMenu papas;
    private ProductoMenu gaseosa;

    @BeforeEach
    void setUp() {
        hamburguesa = new ProductoMenu("Hamburguesa Sencilla", 12000);
        papas = new ProductoMenu("Papas Medianas", 5000);
        gaseosa = new ProductoMenu("Gaseosa", 0); // caso 0
    }

    @AfterEach
    void tearDown() throws Exception 
    {
    }

    @Test
    void testGetNombre() {
        assertEquals("Hamburguesa Sencilla", hamburguesa.getNombre(), "El nombre no es el esperado.");
        assertEquals("Papas Medianas", papas.getNombre(), "El nombre no es el esperado.");
    }

    @Test
    void testGetPrecio() {
        assertEquals(12000, hamburguesa.getPrecio(), "El precio no es el esperado.");
        assertEquals(5000, papas.getPrecio(), "El precio no es el esperado.");
        assertEquals(0, gaseosa.getPrecio(), "El precio no es el esperado.");
    }


    @Test
    void testGenerarTextoFactura() {
        String textoEsperado = "Hamburguesa Sencilla\n            12000\n";
        assertEquals(textoEsperado, hamburguesa.generarTextoFactura(), "El texto de factura no es el esperado.");

        String textoEsperadoGratis = "Gaseosa\n            0\n";
        assertEquals(textoEsperadoGratis, gaseosa.generarTextoFactura(), "El texto de factura no es el esperado.");
    }

}
