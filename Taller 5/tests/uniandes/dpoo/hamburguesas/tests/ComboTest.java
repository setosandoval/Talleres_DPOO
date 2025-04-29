package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {

    private ProductoMenu hamburguesa;
    private ProductoMenu papas;
    private Combo comboEspecial;
    private Combo comboSinDescuento;

    @BeforeEach
    void setUp() {
        hamburguesa = new ProductoMenu("Hamburguesa Sencilla", 12000);
        papas = new ProductoMenu("Papas Medianas", 5000);

        ArrayList<ProductoMenu> items = new ArrayList<>();
        items.add(hamburguesa);
        items.add(papas);

        comboEspecial = new Combo("Combo Especial", 0.8, items); // 20% descuento
        comboSinDescuento = new Combo("Combo Regular", 1.0, items); // 0% descuento
    }

    @AfterEach
    void tearDown() throws Exception 
    {
    }

    @Test
    void testGetNombre() {
        assertEquals("Combo Especial", comboEspecial.getNombre(), "El nombre del combo no es el esperado.");
        assertEquals("Combo Regular", comboSinDescuento.getNombre(), "El nombre del combo no es el esperado.");
    }

    @Test
    void testGetPrecio() {
        // Precio sin descuento: 12000 + 5000 = 17000
        // Con 20% de descuento (0.8 multiplicado): 17000 * 0.8 = 13600
        assertEquals(13600, comboEspecial.getPrecio(), "El precio del combo especial no es el esperado.");

        // Sin descuento (1.0 multiplicado): 17000 * 1.0 = 17000
        assertEquals(17000, comboSinDescuento.getPrecio(), "El precio del combo regular no es el esperado.");
    }

    @Test
    void testGenerarTextoFactura() {
        String textoEsperadoComboEspecial = "Combo Combo Especial\n" +
                                             " Descuento: 0.8\n" +
                                             "            13600\n";
        assertEquals(textoEsperadoComboEspecial, comboEspecial.generarTextoFactura(), "El texto de factura del combo especial no es el esperado.");

        String textoEsperadoComboRegular = "Combo Combo Regular\n" +
                                           " Descuento: 1.0\n" +
                                           "            17000\n";
        assertEquals(textoEsperadoComboRegular, comboSinDescuento.generarTextoFactura(), "El texto de factura del combo regular no es el esperado.");
    }
}
