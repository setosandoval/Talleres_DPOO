package uniandes.dpoo.estructuras.logica;

import java.util.*;

public class SandboxConjuntos {
    private TreeSet<String> arbolCadenas;

    public SandboxConjuntos() {
        arbolCadenas = new TreeSet<String>();
    }

    public List<String> getCadenasComoLista() {
        ArrayList<String> lista = new ArrayList<String>();
        for(String cadena : arbolCadenas) {
            lista.add(cadena);
        }
        return lista;
    }

    public List<String> getCadenasComoListaInvertida() {
        ArrayList<String> lista = new ArrayList<String>();
        for(String cadena : arbolCadenas) {
            lista.add(0, cadena);
        }
        return lista;
    }

    public String getPrimera() {
        if(arbolCadenas.size() == 0) {
            return null;
        }
        return arbolCadenas.first();
    }

    public String getUltima() {
        if(arbolCadenas.size() == 0) {
            return null;
        }
        return arbolCadenas.last();
    }

    public Collection<String> getSiguientes(String cadena) {
        ArrayList<String> siguientes = new ArrayList<String>();
        for(String str : arbolCadenas) {
            if(str.compareTo(cadena) >= 0) {
                siguientes.add(str);
            }
        }
        return siguientes;
    }

    public int getCantidadCadenas() {
        return arbolCadenas.size();
    }

    public void agregarCadena(String cadena) {
        if(cadena != null) {
            arbolCadenas.add(cadena);
        }
    }

    public void eliminarCadena(String cadena) {
        arbolCadenas.remove(cadena);
    }

    public void eliminarCadenaSinMayusculasOMinusculas(String cadena) {
        String aBorrar = null;
        for(String str : arbolCadenas) {
            if(str.toLowerCase().equals(cadena.toLowerCase())) {
                aBorrar = str;
                break;
            }
        }
        if(aBorrar != null) {
            arbolCadenas.remove(aBorrar);
        }
    }

    public void eliminarPrimera() {
        if(arbolCadenas.size() > 0) {
            arbolCadenas.remove(arbolCadenas.first());
        }
    }

    public void reiniciarConjuntoCadenas(List<Object> objetos) {
        arbolCadenas = new TreeSet<String>();
        for(Object obj : objetos) {
            arbolCadenas.add(obj + "");
        }
    }

    public void volverMayusculas() {
        TreeSet<String> temporal = new TreeSet<String>();
        for(String str : arbolCadenas) {
            temporal.add(str.toUpperCase());
        }
        arbolCadenas = temporal;
    }

    public TreeSet<String> invertirCadenas() {
        TreeSet<String> invertido = new TreeSet<String>();
        ArrayList<String> temporal = new ArrayList<String>();
        for(String str : arbolCadenas) {
            temporal.add(0, str);
        }
        for(String str : temporal) {
            invertido.add(str);
        }
        return invertido;
    }

    public boolean compararElementos(String[] otroArreglo) {
        boolean contieneTodos = true;
        for(int i = 0; i < otroArreglo.length; i++) {
            if(!arbolCadenas.contains(otroArreglo[i])) {
                contieneTodos = false;
                break;
            }
        }
        return contieneTodos;
    }
}