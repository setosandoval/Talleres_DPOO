package uniandes.dpoo.estructuras.logica;

import java.util.*;

public class SandboxListas {
    private List<Integer> listaEnteros;
    private List<String> listaCadenas;

    public SandboxListas() {
        listaEnteros = new ArrayList<>();
        listaCadenas = new LinkedList<>();
    }

    public List<Integer> getCopiaEnteros() {
        return new ArrayList<>(listaEnteros);
    }

    public List<String> getCopiaCadenas() {
        return new LinkedList<>(listaCadenas);
    }

    public int[] getEnterosComoArreglo() {
        int[] arreglo = new int[listaEnteros.size()];
        for (int i = 0; i < listaEnteros.size(); i++) {
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
        listaEnteros.removeIf(e -> e == valor);
    }

    public void eliminarCadena(String cadena) {
        listaCadenas.removeIf(e -> e.equals(cadena));
    }

    public void insertarEntero(int entero, int posicion) {
        if (posicion < 0) posicion = 0;
        if (posicion > listaEnteros.size()) posicion = listaEnteros.size();
        listaEnteros.add(posicion, entero);
    }

    public void eliminarEnteroPorPosicion(int posicion) {
        if (posicion >= 0 && posicion < listaEnteros.size()) {
            listaEnteros.remove(posicion);
        }
    }

    public void reiniciarArregloEnteros(double[] valores) {
        listaEnteros.clear();
        for (double valor : valores) {
            listaEnteros.add((int) valor);
        }
    }

    public void reiniciarArregloCadenas(List<Object> objetos) {
        listaCadenas.clear();
        for (Object obj : objetos) {
            listaCadenas.add(obj.toString());
        }
    }

    public void volverPositivos() {
        for (int i = 0; i < listaEnteros.size(); i++) {
            if (listaEnteros.get(i) < 0) {
                listaEnteros.set(i, -listaEnteros.get(i));
            }
        }
    }

    public void organizarEnteros() {
        listaEnteros.sort(Collections.reverseOrder());
    }

    public void organizarCadenas() {
        Collections.sort(listaCadenas);
    }

    public int contarApariciones(int valor) {
        int count = 0;
        for (int num : listaEnteros) {
            if (num == valor) count++;
        }
        return count;
    }

    public int contarApariciones(String cadena) {
        int count = 0;
        for (String str : listaCadenas) {
            if (str.equalsIgnoreCase(cadena)) count++;
        }
        return count;
    }

    public int contarEnterosRepetidos() {
        Set<Integer> unique = new HashSet<>();
        Set<Integer> repeated = new HashSet<>();
        for (int num : listaEnteros) {
            if (!unique.add(num)) {
                repeated.add(num);
            }
        }
        return repeated.size();
    }

    public boolean compararArregloEnteros(int[] otroArreglo) {
        if (listaEnteros.size() != otroArreglo.length) return false;
        for (int i = 0; i < otroArreglo.length; i++) {
            if (listaEnteros.get(i) != otroArreglo[i]) return false;
        }
        return true;
    }

    public void generarEnteros(int cantidad, int minimo, int maximo) {
        listaEnteros.clear();
        Random random = new Random();
        for (int i = 0; i < cantidad; i++) {
            listaEnteros.add(random.nextInt(maximo - minimo + 1) + minimo);
        }
    }
}
