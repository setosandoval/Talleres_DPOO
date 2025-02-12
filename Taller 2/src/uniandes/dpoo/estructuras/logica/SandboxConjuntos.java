package uniandes.dpoo.estructuras.logica;

import java.util.*;

public class SandboxConjuntos {
    private NavigableSet<String> arbolCadenas;

    public SandboxConjuntos() {
        arbolCadenas = new TreeSet<>();
    }

    public List<String> getCadenasComoLista() {
        return new ArrayList<>(arbolCadenas);
    }

    public List<String> getCadenasComoListaInvertida() {
        return new ArrayList<>(arbolCadenas.descendingSet());
    }

    public String getPrimera() {
        return arbolCadenas.isEmpty() ? null : arbolCadenas.first();
    }

    public String getUltima() {
        return arbolCadenas.isEmpty() ? null : arbolCadenas.last();
    }

    public Collection<String> getSiguientes(String cadena) {
        return arbolCadenas.tailSet(cadena, true);
    }

    public int getCantidadCadenas() {
        return arbolCadenas.size();
    }

    public void agregarCadena(String cadena) {
        arbolCadenas.add(cadena);
    }

    public void eliminarCadena(String cadena) {
        arbolCadenas.remove(cadena);
    }

    public void eliminarCadenaSinMayusculasOMinusculas(String cadena) {
        String match = null;
        for (String s : arbolCadenas) {
            if (s.equalsIgnoreCase(cadena)) {
                match = s;
                break;
            }
        }
        if (match != null) {
            arbolCadenas.remove(match);
        }
    }

    public void eliminarPrimera() {
        if (!arbolCadenas.isEmpty()) {
            arbolCadenas.pollFirst();
        }
    }

    public void reiniciarConjuntoCadenas(List<Object> objetos) {
        arbolCadenas.clear();
        for (Object obj : objetos) {
            arbolCadenas.add(obj.toString());
        }
    }

    public void volverMayusculas() {
        NavigableSet<String> nuevoArbol = new TreeSet<>();
        for (String cadena : arbolCadenas) {
            nuevoArbol.add(cadena.toUpperCase());
        }
        arbolCadenas = nuevoArbol;
    }

    public TreeSet<String> invertirCadenas() {
        return new TreeSet<>(arbolCadenas.descendingSet());
    }

    public boolean compararElementos(String[] otroArreglo) {
        for (String cadena : otroArreglo) {
            if (!arbolCadenas.contains(cadena)) {
                return false;
            }
        }
        return true;
    }
}