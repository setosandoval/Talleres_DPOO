package uniandes.dpoo.estructuras.logica;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SandboxListas {
    private ArrayList<Integer> listaEnteros;
    private LinkedList<String> listaCadenas;

    public SandboxListas() {
        listaEnteros = new ArrayList<Integer>();
        listaCadenas = new LinkedList<String>();
    }

    public List<Integer> getCopiaEnteros() {
        ArrayList<Integer> copia = new ArrayList<Integer>();
        for(Integer num : listaEnteros) {
            copia.add(num);
        }
        return copia;
    }

    public List<String> getCopiaCadenas() {
        LinkedList<String> copia = new LinkedList<String>();
        for(String palabra : listaCadenas) {
            copia.add(palabra);
        }
        return copia;
    }

    public int[] getEnterosComoArreglo() {
        int[] arreglo = new int[listaEnteros.size()];
        for(int i = 0; i < listaEnteros.size(); i++) {
            arreglo[i] = listaEnteros.get(i);
        }
        return arreglo;
    }

    public int getCantidadEnteros() {
        return listaEnteros.size();
    }

    public int getCantidadCadenas() {
        return listaCadenas.size();
    }

    public void agregarEntero(int entero) {
        listaEnteros.add(entero);
    }

    public void agregarCadena(String cadena) {
        listaCadenas.add(cadena);
    }

    public void eliminarEntero(int valor) {
        ArrayList<Integer> temporal = new ArrayList<Integer>();
        for(Integer num : listaEnteros) {
            if(num != valor) {
                temporal.add(num);
            }
        }
        listaEnteros = temporal;
    }

    public void eliminarCadena(String cadena) {
        LinkedList<String> temporal = new LinkedList<String>();
        for(String str : listaCadenas) {
            if(!str.equals(cadena)) {
                temporal.add(str);
            }
        }
        listaCadenas = temporal;
    }

    public void insertarEntero(int entero, int posicion) {
        if(posicion < 0) {
            posicion = 0;
        }
        if(posicion > listaEnteros.size()) {
            posicion = listaEnteros.size();
        }
        
        ArrayList<Integer> temporal = new ArrayList<Integer>();
        for(int i = 0; i < posicion; i++) {
            temporal.add(listaEnteros.get(i));
        }
        temporal.add(entero);
        for(int i = posicion; i < listaEnteros.size(); i++) {
            temporal.add(listaEnteros.get(i));
        }
        listaEnteros = temporal;
    }

    public void eliminarEnteroPorPosicion(int posicion) {
        if(posicion >= 0 && posicion < listaEnteros.size()) {
            ArrayList<Integer> temporal = new ArrayList<Integer>();
            for(int i = 0; i < listaEnteros.size(); i++) {
                if(i != posicion) {
                    temporal.add(listaEnteros.get(i));
                }
            }
            listaEnteros = temporal;
        }
    }

    public void reiniciarArregloEnteros(double[] valores) {
        listaEnteros = new ArrayList<Integer>();
        for(int i = 0; i < valores.length; i++) {
            listaEnteros.add((int)valores[i]);
        }
    }

    public void reiniciarArregloCadenas(List<Object> objetos) {
        listaCadenas = new LinkedList<String>();
        for(Object obj : objetos) {
            listaCadenas.add(obj + "");
        }
    }

    public void volverPositivos() {
        for(int i = 0; i < listaEnteros.size(); i++) {
            if(listaEnteros.get(i) < 0) {
                listaEnteros.set(i, listaEnteros.get(i) * -1);
            }
        }
    }

    public void organizarEnteros() {
        for(int i = 0; i < listaEnteros.size() - 1; i++) {
            for(int j = 0; j < listaEnteros.size() - 1 - i; j++) {
                if(listaEnteros.get(j) < listaEnteros.get(j + 1)) {
                    int temp = listaEnteros.get(j);
                    listaEnteros.set(j, listaEnteros.get(j + 1));
                    listaEnteros.set(j + 1, temp);
                }
            }
        }
    }

    public void organizarCadenas() {
        for(int i = 0; i < listaCadenas.size() - 1; i++) {
            for(int j = 0; j < listaCadenas.size() - 1 - i; j++) {
                if(listaCadenas.get(j).compareTo(listaCadenas.get(j + 1)) > 0) {
                    String temp = listaCadenas.get(j);
                    listaCadenas.set(j, listaCadenas.get(j + 1));
                    listaCadenas.set(j + 1, temp);
                }
            }
        }
    }

    public int contarApariciones(int valor) {
        int contador = 0;
        for(int num : listaEnteros) {
            if(num == valor) {
                contador++;
            }
        }
        return contador;
    }

    public int contarApariciones(String cadena) {
        int contador = 0;
        for(String str : listaCadenas) {
            if(str.toLowerCase().equals(cadena.toLowerCase())) {
                contador++;
            }
        }
        return contador;
    }

    public int contarEnterosRepetidos() {
        int contadorRepetidos = 0;
        for(int i = 0; i < listaEnteros.size(); i++) {
            boolean yaContado = false;
            for(int j = 0; j < i; j++) {
                if(listaEnteros.get(i).equals(listaEnteros.get(j))) {
                    yaContado = true;
                    break;
                }
            }
            if(!yaContado) {
                int apariciones = 0;
                for(int j = 0; j < listaEnteros.size(); j++) {
                    if(listaEnteros.get(i).equals(listaEnteros.get(j))) {
                        apariciones++;
                    }
                }
                if(apariciones > 1) {
                    contadorRepetidos++;
                }
            }
        }
        return contadorRepetidos;
    }

    public boolean compararArregloEnteros(int[] otroArreglo) {
        if(listaEnteros.size() != otroArreglo.length) {
            return false;
        }
        for(int i = 0; i < otroArreglo.length; i++) {
            if(listaEnteros.get(i) != otroArreglo[i]) {
                return false;
            }
        }
        return true;
    }

    public void generarEnteros(int cantidad, int minimo, int maximo) {
        listaEnteros = new ArrayList<Integer>();
        Random random = new Random();
        for(int i = 0; i < cantidad; i++) {
            int numeroAleatorio = random.nextInt(maximo - minimo + 1) + minimo;
            listaEnteros.add(numeroAleatorio);
        }
    }
}