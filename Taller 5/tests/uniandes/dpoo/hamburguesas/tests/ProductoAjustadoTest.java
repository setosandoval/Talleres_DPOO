package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {

    private ProductoMenu base;
    private ProductoAjustado ajustado;

    private Ingrediente queso;
    private Ingrediente tocineta;
    private Ingrediente cebolla;

    @BeforeEach
    void setUp() {
        base = new ProductoMenu("Hamburguesa Sencilla", 12000);
        ajustado = new ProductoAjustado(base);

        queso = new Ingrediente("Queso", 1000);
        tocineta = new Ingrediente("Tocineta", 2000);
        cebolla = new Ingrediente("Cebolla", 0);

        ajustado.getAgregados().add(queso);
        ajustado.getAgregados().add(tocineta);
        ajustado.getEliminados().add(cebolla);
    }

    @AfterEach
    void tearDown() throws Exception 
    {
    }

    @Test
    void testGetNombre() {
        assertEquals("Hamburguesa Sencilla", ajustado.getNombre(), "El nombre debería ser el del producto base.");
    }

    @Test
    void testGetPrecio() {
        // 12000 + 1000 + 2000 = 15000
        assertEquals(15000, ajustado.getPrecio(), "El precio no suma correctamente los ingredientes agregados.");
    }

    @Test
    void testGenerarTextoFactura() {
        String textoEsperado = "Hamburguesa Sencilla\n" +
                               "            15000\n" +
                               "            + Queso: 1000\n" +
                               "            + Tocineta: 2000\n" +
                               "            - Cebolla\n";
        assertEquals(textoEsperado, ajustado.generarTextoFactura(), "El texto de factura no es el esperado.");
    }

    @Test
    void testSinIngredientes() {
        ProductoAjustado sinNada = new ProductoAjustado(base);
        assertEquals(12000, sinNada.getPrecio());
        String esperado = "Hamburguesa Sencilla\n            12000\n";
        assertEquals(esperado, sinNada.generarTextoFactura());
    }
}
