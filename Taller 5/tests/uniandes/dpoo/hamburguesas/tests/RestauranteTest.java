package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

/**
 * Clase de prueba para la clase Restaurante
 */
public class RestauranteTest {
    
    private Restaurante restaurante;
    
    private File archivoIngredientes;
    private File archivoMenu;
    private File archivoCombos;
    
    @BeforeEach
    public void setUp() {
        restaurante = new Restaurante();
        
        // Ubicación de los archivos de prueba
        archivoIngredientes = new File("data/ingredientes.txt");
        archivoMenu = new File("data/menu.txt");
        archivoCombos = new File("data/combos.txt");
    }
    
    @AfterEach
    public void tearDown() {
        restaurante = null;
        
        // Eliminar archivos generados en la carpeta de facturas
        File carpetaFacturas = new File("./facturas/");
        if (carpetaFacturas.exists()) {
            File[] archivos = carpetaFacturas.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    if (!archivo.isDirectory()) {
                        archivo.delete();
                    }
                }
            }
        }
    }
    
    @Test
    public void testIniciarPedido() throws YaHayUnPedidoEnCursoException {
        assertNull(restaurante.getPedidoEnCurso(), "Inicialmente no debe haber pedido en curso");
        // Iniciar pedido
        restaurante.iniciarPedido("Sergio Sandoval", "Calle Lejos");
        
        // Verificar que ahora hay un pedido en curso con los datos correctos
        Pedido pedido = restaurante.getPedidoEnCurso();
        assertNotNull(pedido, "Debe existir un pedido en curso después de iniciarlo");
        assertEquals("Sergio Sandoval", pedido.getNombreCliente(), "El nombre del cliente debe ser correcto");
    }
    
    @Test
    public void testIniciarPedidoConPedidoExistente() throws YaHayUnPedidoEnCursoException {
        // Iniciar un primer pedido
    	restaurante.iniciarPedido("Sergio Sandoval", "Calle Lejos");
        
        // Intentar iniciar un segundo pedido debe lanzar excepción
        assertThrows(YaHayUnPedidoEnCursoException.class, () -> {
            restaurante.iniciarPedido("Oskar Nutria", "Avenida Cerca");
        }, "Debe lanzar excepción al intentar iniciar un segundo pedido");
        
        // Verificar que el pedido original sigue activo
        assertEquals("Sergio Sandoval", restaurante.getPedidoEnCurso().getNombreCliente(), 
                "El pedido original debe seguir activo");
    }
    
    @Test
    public void testCerrarYGuardarPedido() throws YaHayUnPedidoEnCursoException, NoHayPedidoEnCursoException, IOException {
        // Iniciar pedido
        restaurante.iniciarPedido("Sergio Sandoval", "Calle Lejos");
        assertNotNull(restaurante.getPedidoEnCurso(), "Debe existir un pedido en curso");
        
        // Cerrar y guardar el pedido
        restaurante.cerrarYGuardarPedido();
        
        // Verificar que no hay pedido en curso después de cerrarlo
        assertNull(restaurante.getPedidoEnCurso(), "No debe haber pedido en curso después de cerrarlo");
        
        // Verificar que el pedido se añadió a la lista de pedidos
        ArrayList<Pedido> pedidos = restaurante.getPedidos();
        assertEquals(1, pedidos.size(), "Debe haber un pedido en la lista de pedidos cerrados");
        assertEquals("Sergio Sandoval", pedidos.get(0).getNombreCliente(), "El cliente del pedido debe ser correcto");
    }
    
    @Test
    public void testCerrarPedidoSinPedidoEnCurso() {
        // Intentar cerrar un pedido cuando no hay ninguno en curso debe lanzar excepción
        assertThrows(NoHayPedidoEnCursoException.class, () -> {
            restaurante.cerrarYGuardarPedido();
        }, "Debe lanzar excepción al intentar cerrar un pedido cuando no hay ninguno en curso");
    }
    

    @Test
    public void testGetPedidoEnCurso() throws YaHayUnPedidoEnCursoException {
        // Caso 1: Sin pedido en curso
        assertNull(restaurante.getPedidoEnCurso(), "No debe haber un pedido en curso inicialmente");
        
        // Caso 2: Con pedido en curso
        restaurante.iniciarPedido("Sergio Sandoval", "Calle Lejos");
        Pedido pedido = restaurante.getPedidoEnCurso();
        
        assertNotNull(pedido, "Debe existir un pedido en curso");
        assertEquals("Sergio Sandoval", pedido.getNombreCliente(), "El nombre del cliente debe ser correcto");
    }
    
    @Test
    public void testGetPedidos() throws YaHayUnPedidoEnCursoException, NoHayPedidoEnCursoException, IOException {
        // Caso 1: Sin pedidos
        assertNotNull(restaurante.getPedidos(), "La lista de pedidos no debe ser nula");
        assertTrue(restaurante.getPedidos().isEmpty(), "La lista de pedidos debe estar vacía inicialmente");
        
        // Caso 2: Con un pedido cerrado
        restaurante.iniciarPedido("Sergio Sandoval", "Calle Lejos");
        restaurante.cerrarYGuardarPedido();
        
        ArrayList<Pedido> pedidos = restaurante.getPedidos();
        assertEquals(1, pedidos.size(), "Debe haber un pedido en la lista después de cerrar uno");
        
        // Caso 3: Con múltiples pedidos cerrados
        restaurante.iniciarPedido("Oskar Nutria", "Avenida Cerca");
        restaurante.cerrarYGuardarPedido();
        
        pedidos = restaurante.getPedidos();
        assertEquals(2, pedidos.size(), "Debe haber dos pedidos en la lista después de cerrar dos");
    }
    

    @Test
    public void testGetMenuBase() {
        // Caso 1: Menú vacío
        assertNotNull(restaurante.getMenuBase(), "El menú base no debe ser nulo");
        assertTrue(restaurante.getMenuBase().isEmpty(), "El menú base debe estar vacío inicialmente");
        
        // Caso 2: Menú con productos
        ProductoMenu producto1 = new ProductoMenu("Hamburguesa Sencilla", 10000);
        ProductoMenu producto2 = new ProductoMenu("Papas Fritas", 5000);
        
        restaurante.getMenuBase().add(producto1);
        restaurante.getMenuBase().add(producto2);
        
        ArrayList<ProductoMenu> menuBase = restaurante.getMenuBase();
        assertEquals(2, menuBase.size(), "El menú base debe tener dos productos");
        assertEquals("Hamburguesa Sencilla", menuBase.get(0).getNombre(), "El nombre del primer producto debe ser correcto");
        assertEquals(10000, menuBase.get(0).getPrecio(), "El precio del primer producto debe ser correcto");
        assertEquals("Papas Fritas", menuBase.get(1).getNombre(), "El nombre del segundo producto debe ser correcto");
        assertEquals(5000, menuBase.get(1).getPrecio(), "El precio del segundo producto debe ser correcto");
    }
    
    @Test
    public void testGetMenuCombos() {
        // Caso 1: Menú vacío
        assertNotNull(restaurante.getMenuCombos(), "El menú de combos no debe ser nulo");
        assertTrue(restaurante.getMenuCombos().isEmpty(), "El menú de combos debe estar vacío inicialmente");
        
        // Caso 2: Menú con combos
        ArrayList<ProductoMenu> productosCombo = new ArrayList<>();
        productosCombo.add(new ProductoMenu("Hamburguesa Sencilla", 10000));
        productosCombo.add(new ProductoMenu("Papas Fritas", 5000));
        
        Combo combo = new Combo("Combo Económico", 0.1, productosCombo);
        restaurante.getMenuCombos().add(combo);
        
        ArrayList<Combo> menuCombos = restaurante.getMenuCombos();
        assertEquals(1, menuCombos.size(), "El menú de combos debe tener un combo");
        assertEquals("Combo Económico", menuCombos.get(0).getNombre(), "El nombre del combo debe ser correcto");
        
        // Verificar que el precio del combo tiene el descuento aplicado
        double precioEsperado = (10000 + 5000) * 0.9; // 10% de descuento
        assertEquals(precioEsperado, combo.getPrecio(), 0.01, "El precio del combo debe reflejar el descuento");
    }
    
    @Test
    public void testGetIngredientes() {
        // Caso 1: Lista vacía
        assertNotNull(restaurante.getIngredientes(), "La lista de ingredientes no debe ser nula");
        assertTrue(restaurante.getIngredientes().isEmpty(), "La lista de ingredientes debe estar vacía inicialmente");
        
        // Caso 2: Lista con ingredientes
        Ingrediente ingrediente1 = new Ingrediente("Queso", 2000);
        Ingrediente ingrediente2 = new Ingrediente("Tocineta", 3000);
        
        restaurante.getIngredientes().add(ingrediente1);
        restaurante.getIngredientes().add(ingrediente2);
        
        ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
        assertEquals(2, ingredientes.size(), "La lista de ingredientes debe tener dos elementos");
        assertEquals("Queso", ingredientes.get(0).getNombre(), "El nombre del primer ingrediente debe ser correcto");
        assertEquals(2000, ingredientes.get(0).getCostoAdicional(), "El costo del primer ingrediente debe ser correcto");
        assertEquals("Tocineta", ingredientes.get(1).getNombre(), "El nombre del segundo ingrediente debe ser correcto");
        assertEquals(3000, ingredientes.get(1).getCostoAdicional(), "El costo del segundo ingrediente debe ser correcto");
    }
    
    @Test
    public void testCargarInformacionRestaurante() throws HamburguesaException, IOException {
        // Verificar que las listas están inicialmente vacías
        assertTrue(restaurante.getIngredientes().isEmpty(), "La lista de ingredientes debe estar vacía inicialmente");
        assertTrue(restaurante.getMenuBase().isEmpty(), "El menú base debe estar vacío inicialmente");
        assertTrue(restaurante.getMenuCombos().isEmpty(), "El menú de combos debe estar vacío inicialmente");
        
        // Cargar la información del restaurante
        restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
        
        // Verificar que las listas ya no están vacías
        assertFalse(restaurante.getIngredientes().isEmpty(), "La lista de ingredientes no debe estar vacía después de cargar");
        assertFalse(restaurante.getMenuBase().isEmpty(), "El menú base no debe estar vacío después de cargar");
        assertFalse(restaurante.getMenuCombos().isEmpty(), "El menú de combos no debe estar vacío después de cargar");
        
        // Verificar la cantidad correcta de elementos cargados
        assertEquals(15, restaurante.getIngredientes().size(), "Debe haber 15 ingredientes");
        assertEquals(22, restaurante.getMenuBase().size(), "Debe haber 22 productos en el menú base");
        assertEquals(4, restaurante.getMenuCombos().size(), "Debe haber 4 combos");
        
        // Verificar algunos elementos específicos
        boolean encontroLechuga = false;
        for (Ingrediente ing : restaurante.getIngredientes()) {
            if (ing.getNombre().equals("lechuga")) {
                encontroLechuga = true;
                assertEquals(1000, ing.getCostoAdicional(), "El costo de la lechuga debe ser 1000");
                break;
            }
        }
        assertTrue(encontroLechuga, "Debe encontrar el ingrediente 'lechuga'");
        
        boolean encontroCorral = false;
        for (ProductoMenu prod : restaurante.getMenuBase()) {
            if (prod.getNombre().equals("corral")) {
                encontroCorral = true;
                assertEquals(14000, prod.getPrecio(), "El precio del corral debe ser 14000");
                break;
            }
        }
        assertTrue(encontroCorral, "Debe encontrar el producto 'corral'");
        
        boolean encontroComboCorral = false;
        for (Combo combo : restaurante.getMenuCombos()) {
            if (combo.getNombre().equals("combo corral")) {
                encontroComboCorral = true;
                break;
            }
        }
        assertTrue(encontroComboCorral, "Debe encontrar el 'combo corral'");
    }
    
    
    @Test
    public void testCargarIngredientesRepetidos() throws IOException {
        // Agregar manualmente un ingrediente que estará en el archivo
        restaurante.getIngredientes().add(new Ingrediente("lechuga", 1000));
        
        // Intentar cargar ingredientes debe lanzar excepción
        assertThrows(IngredienteRepetidoException.class, () -> {
            restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
        }, "Debe lanzar excepción al intentar cargar un ingrediente repetido");
    }

    @Test
    public void testCargarProductosRepetidos() throws IOException, HamburguesaException {
        // Cargar los ingredientes primero (para evitar errores en el archivo)
        // Pero usamos un archivo temporal para el menú para evitar interferencias
        File archivoTemporal = File.createTempFile("menu_test", ".txt");
        archivoTemporal.deleteOnExit();
        
        java.io.FileWriter writer = new java.io.FileWriter(archivoTemporal);
        writer.write("corral;14000\n");
        writer.write("papas medianas;5500\n");
        writer.write("corral;15000\n"); // Producto repetido
        writer.close();
        
        // Intentar cargar productos repetidos debe lanzar excepción
        assertThrows(ProductoRepetidoException.class, () -> {
            restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoTemporal, null);
        }, "Debe lanzar excepción al intentar cargar un producto repetido");
    }
    
    @Test
    public void testCargarCombosRepetidos() throws IOException, HamburguesaException {
        // Cargar los ingredientes y productos primero para poder crear combos
        restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, null);
        
        // Añadir manualmente un combo que estará en el archivo
        ArrayList<ProductoMenu> productosCombo = new ArrayList<>();
        for (ProductoMenu prod : restaurante.getMenuBase()) {
            if (prod.getNombre().equals("corral") || 
                prod.getNombre().equals("papas medianas") || 
                prod.getNombre().equals("gaseosa")) {
                productosCombo.add(prod);
            }
        }
        
        Combo combo = new Combo("combo corral", 0.1, productosCombo);
        restaurante.getMenuCombos().add(combo);
        
        // Intentar cargar combos debe lanzar excepción porque ya existe un combo con el mismo nombre
        assertThrows(ProductoRepetidoException.class, () -> {
            restaurante.cargarInformacionRestaurante(null, null, archivoCombos);
        }, "Debe lanzar excepción al intentar cargar un combo repetido");
    }
    
    @Test
    public void testCargarComboProductoFaltante() throws IOException, HamburguesaException {
        // Cargar ingredientes y productos primero (para tener una base válida)
        restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, null);
        
        // Crear un archivo temporal con un combo que hace referencia a un producto inexistente
        File archivoTemporal = File.createTempFile("combos_test", ".txt");
        archivoTemporal.deleteOnExit();
        
        java.io.FileWriter writer = new java.io.FileWriter(archivoTemporal);
        writer.write("combo prueba;10%;producto_inexistente;papas medianas;gaseosa\n");
        writer.close();
        
        // Intentar cargar el combo debe lanzar excepción
        assertThrows(ProductoFaltanteException.class, () -> {
            restaurante.cargarInformacionRestaurante(null, null, archivoTemporal);
        }, "Debe lanzar excepción al intentar cargar un combo con un producto inexistente");
    }
}