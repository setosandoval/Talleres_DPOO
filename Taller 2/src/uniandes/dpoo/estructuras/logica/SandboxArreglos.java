package uniandes.dpoo.estructuras.logica;

import java.util.HashMap;
import java.util.Random;

public class SandboxArreglos {
    private int[] arregloEnteros;
    private String[] arregloCadenas;

    public SandboxArreglos() {
        arregloEnteros = new int[0];
        arregloCadenas = new String[0];
    }

    public int[] getCopiaEnteros() {
        int[] copia = new int[arregloEnteros.length];
        for(int i = 0; i < arregloEnteros.length; i++) {
            copia[i] = arregloEnteros[i];
        }
        return copia;
    }

    public String[] getCopiaCadenas() {
        String[] copia = new String[arregloCadenas.length];
        for(int i = 0; i < arregloCadenas.length; i++) {
            copia[i] = arregloCadenas[i];
        }
        return copia;
    }

    public int getCantidadEnteros() {
        return arregloEnteros.length;
    }

    public int getCantidadCadenas() {
        return arregloCadenas.length;
    }

    public void agregarEntero(int entero) {
        int[] nuevo = new int[arregloEnteros.length + 1];
        for(int i = 0; i < arregloEnteros.length; i++) {
            nuevo[i] = arregloEnteros[i];
        }
        nuevo[nuevo.length - 1] = entero;
        arregloEnteros = nuevo;
    }

    public void agregarCadena(String cadena) {
        String[] nuevo = new String[arregloCadenas.length + 1];
        for(int i = 0; i < arregloCadenas.length; i++) {
            nuevo[i] = arregloCadenas[i];
        }
        nuevo[nuevo.length - 1] = cadena;
        arregloCadenas = nuevo;
    }

    public void eliminarEntero(int valor) {
        int contador = 0;
        for(int i = 0; i < arregloEnteros.length; i++) {
            if(arregloEnteros[i] != valor) {
                contador++;
            }
        }
        
        int[] nuevo = new int[contador];
        int pos = 0;
        for(int i = 0; i < arregloEnteros.length; i++) {
            if(arregloEnteros[i] != valor) {
                nuevo[pos] = arregloEnteros[i];
                pos++;
            }
        }
        arregloEnteros = nuevo;
    }

    public void eliminarCadena(String cadena) {
        int contador = 0;
        for(int i = 0; i < arregloCadenas.length; i++) {
            if(!arregloCadenas[i].equals(cadena)) {
                contador++;
            }
        }
        
        String[] nuevo = new String[contador];
        int pos = 0;
        for(int i = 0; i < arregloCadenas.length; i++) {
            if(!arregloCadenas[i].equals(cadena)) {
                nuevo[pos] = arregloCadenas[i];
                pos++;
            }
        }
        arregloCadenas = nuevo;
    }

    public void insertarEntero(int entero, int posicion) {
        if(posicion < 0) {
            posicion = 0;
        }
        if(posicion > arregloEnteros.length) {
            posicion = arregloEnteros.length;
        }
        
        int[] nuevo = new int[arregloEnteros.length + 1];
        for(int i = 0; i < posicion; i++) {
            nuevo[i] = arregloEnteros[i];
        }
        nuevo[posicion] = entero;
        for(int i = posicion; i < arregloEnteros.length; i++) {
            nuevo[i + 1] = arregloEnteros[i];
        }
        arregloEnteros = nuevo;
    }

    public void eliminarEnteroPorPosicion(int posicion) {
        if(posicion >= 0 && posicion < arregloEnteros.length) {
            int[] nuevo = new int[arregloEnteros.length - 1];
            int pos = 0;
            for(int i = 0; i < arregloEnteros.length; i++) {
                if(i != posicion) {
                    nuevo[pos] = arregloEnteros[i];
                    pos++;
                }
            }
            arregloEnteros = nuevo;
        }
    }

    public void reiniciarArregloEnteros(double[] valores) {
        arregloEnteros = new int[valores.length];
        for(int i = 0; i < valores.length; i++) {
            arregloEnteros[i] = (int)valores[i];
        }
    }

    public void reiniciarArregloCadenas(Object[] objetos) {
        arregloCadenas = new String[objetos.length];
        for(int i = 0; i < objetos.length; i++) {
            arregloCadenas[i] = objetos[i] + "";
        }
    }

    public void volverPositivos() {
        for(int i = 0; i < arregloEnteros.length; i++) {
            if(arregloEnteros[i] < 0) {
                arregloEnteros[i] = arregloEnteros[i] * -1;
            }
        }
    }

    public void organizarEnteros() {
        for(int i = 0; i < arregloEnteros.length - 1; i++) {
            for(int j = 0; j < arregloEnteros.length - 1 - i; j++) {
                if(arregloEnteros[j] > arregloEnteros[j + 1]) {
                    int temp = arregloEnteros[j];
                    arregloEnteros[j] = arregloEnteros[j + 1];
                    arregloEnteros[j + 1] = temp;
                }
            }
        }
    }

    public void organizarCadenas() {
        for(int i = 0; i < arregloCadenas.length - 1; i++) {
            for(int j = 0; j < arregloCadenas.length - 1 - i; j++) {
                if(arregloCadenas[j].compareTo(arregloCadenas[j + 1]) > 0) {
                    String temp = arregloCadenas[j];
                    arregloCadenas[j] = arregloCadenas[j + 1];
                    arregloCadenas[j + 1] = temp;
                }
            }
        }
    }

    public int contarApariciones(int valor) {
        int contador = 0;
        for(int i = 0; i < arregloEnteros.length; i++) {
            if(arregloEnteros[i] == valor) {
                contador++;
            }
        }
        return contador;
    }

    public int contarApariciones(String cadena) {
        int contador = 0;
        for(int i = 0; i < arregloCadenas.length; i++) {
            if(arregloCadenas[i].toLowerCase().equals(cadena.toLowerCase())) {
                contador++;
            }
        }
        return contador;
    }

    public int[] buscarEntero(int valor) {
        int contador = 0;
        for(int num : arregloEnteros) {
            if(num == valor) {
                contador++;
            }
        }
        
        int[] resultado = new int[contador];
        int pos = 0;
        for(int num : arregloEnteros) {
            if(num == valor) {
                resultado[pos] = num;
                pos++;
            }
        }
        return resultado;
    }

    public int[] calcularRangoEnteros() {
        if(arregloEnteros.length == 0) {
            return new int[0];
        }
        
        int min = arregloEnteros[0];
        int max = arregloEnteros[0];
        
        for(int i = 1; i < arregloEnteros.length; i++) {
            if(arregloEnteros[i] < min) {
                min = arregloEnteros[i];
            }
            if(arregloEnteros[i] > max) {
                max = arregloEnteros[i];
            }
        }
        
        return new int[]{min, max};
    }

    public HashMap<Integer, Integer> calcularHistograma() {
        HashMap<Integer, Integer> histograma = new HashMap<>();
        for(int num : arregloEnteros) {
            if(histograma.containsKey(num)) {
                histograma.put(num, histograma.get(num) + 1);
            } else {
                histograma.put(num, 1);
            }
        }
        return histograma;
    }

    public int contarEnterosRepetidos() {
        int contador = 0;
        HashMap<Integer, Integer> hist = calcularHistograma();
        for(int cantidad : hist.values()) {
            if(cantidad > 1) {
                contador++;
            }
        }
        return contador;
    }

    public boolean compararArregloEnteros(int[] otroArreglo) {
        if(arregloEnteros.length != otroArreglo.length) {
            return false;
        }
        for(int i = 0; i < arregloEnteros.length; i++) {
            if(arregloEnteros[i] != otroArreglo[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean mismosEnteros(int[] otroArreglo) {
        if(arregloEnteros.length != otroArreglo.length) {
            return false;
        }
        
        int[] copia1 = getCopiaEnteros();
        int[] copia2 = new int[otroArreglo.length];
        for(int i = 0; i < otroArreglo.length; i++) {
            copia2[i] = otroArreglo[i];
        }
        
        organizarEnteros();
        for(int i = 0; i < copia2.length - 1; i++) {
            for(int j = 0; j < copia2.length - 1 - i; j++) {
                if(copia2[j] > copia2[j + 1]) {
                    int temp = copia2[j];
                    copia2[j] = copia2[j + 1];
                    copia2[j + 1] = temp;
                }
            }
        }
        
        for(int i = 0; i < copia1.length; i++) {
            if(copia1[i] != copia2[i]) {
                return false;
            }
        }
        return true;
    }

    public void generarEnteros(int cantidad, int minimo, int maximo) {
        Random rand = new Random();
        arregloEnteros = new int[cantidad];
        for(int i = 0; i < cantidad; i++) {
            arregloEnteros[i] = rand.nextInt(maximo - minimo + 1) + minimo;
        }
    }
}