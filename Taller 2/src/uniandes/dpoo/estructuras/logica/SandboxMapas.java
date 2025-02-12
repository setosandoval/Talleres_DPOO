package uniandes.dpoo.estructuras.logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SandboxMapas {
    private HashMap<String, String> mapaCadenas;

    public SandboxMapas() {
        mapaCadenas = new HashMap<String, String>();
    }

    public List<String> getValoresComoLista() {
        ArrayList<String> valores = new ArrayList<String>();
        for(String valor : mapaCadenas.values()) {
            valores.add(valor);
        }
        
        for(int i = 0; i < valores.size() - 1; i++) {
            for(int j = 0; j < valores.size() - 1 - i; j++) {
                if(valores.get(j).compareTo(valores.get(j + 1)) > 0) {
                    String temp = valores.get(j);
                    valores.set(j, valores.get(j + 1));
                    valores.set(j + 1, temp);
                }
            }
        }
        return valores;
    }

    public List<String> getLlavesComoListaInvertida() {
        ArrayList<String> llaves = new ArrayList<String>();
        for(String llave : mapaCadenas.keySet()) {
            llaves.add(llave);
        }
        
        for(int i = 0; i < llaves.size() - 1; i++) {
            for(int j = 0; j < llaves.size() - 1 - i; j++) {
                if(llaves.get(j).compareTo(llaves.get(j + 1)) < 0) {
                    String temp = llaves.get(j);
                    llaves.set(j, llaves.get(j + 1));
                    llaves.set(j + 1, temp);
                }
            }
        }
        return llaves;
    }

    public String getPrimera() {
        if(mapaCadenas.size() == 0) {
            return null;
        }
        
        String primera = null;
        for(String llave : mapaCadenas.keySet()) {
            if(primera == null || llave.compareTo(primera) < 0) {
                primera = llave;
            }
        }
        return primera;
    }

    public String getUltima() {
        if(mapaCadenas.size() == 0) {
            return null;
        }
        
        String ultima = null;
        for(String valor : mapaCadenas.values()) {
            if(ultima == null || valor.compareTo(ultima) > 0) {
                ultima = valor;
            }
        }
        return ultima;
    }

    public List<String> getLlaves() {
        ArrayList<String> llavesMayusculas = new ArrayList<String>();
        for(String llave : mapaCadenas.keySet()) {
            llavesMayusculas.add(llave.toUpperCase());
        }
        return llavesMayusculas;
    }

    public int getCantidadCadenasDiferentes() {
        ArrayList<String> diferentes = new ArrayList<String>();
        for(String valor : mapaCadenas.values()) {
            boolean yaEsta = false;
            for(String existente : diferentes) {
                if(existente.equals(valor)) {
                    yaEsta = true;
                    break;
                }
            }
            if(!yaEsta) {
                diferentes.add(valor);
            }
        }
        return diferentes.size();
    }

    public void agregarCadena(String cadena) {
        String llaveInvertida = "";
        for(int i = cadena.length() - 1; i >= 0; i--) {
            llaveInvertida += cadena.charAt(i);
        }
        mapaCadenas.put(llaveInvertida, cadena);
    }

    public void eliminarCadenaConLLave(String llave) {
        mapaCadenas.remove(llave);
    }

    public void eliminarCadenaConValor(String valor) {
        ArrayList<String> llavesABorrar = new ArrayList<String>();
        for(String llave : mapaCadenas.keySet()) {
            if(mapaCadenas.get(llave).equals(valor)) {
                llavesABorrar.add(llave);
            }
        }
        for(String llave : llavesABorrar) {
            mapaCadenas.remove(llave);
        }
    }

    public void reiniciarMapaCadenas(List<Object> objetos) {
        mapaCadenas = new HashMap<String, String>();
        for(Object obj : objetos) {
            String cadena = obj + "";
            String llaveInvertida = "";
            for(int i = cadena.length() - 1; i >= 0; i--) {
                llaveInvertida += cadena.charAt(i);
            }
            mapaCadenas.put(llaveInvertida, cadena);
        }
    }

    public void volverMayusculas() {
        HashMap<String, String> nuevoMapa = new HashMap<String, String>();
        for(String llave : mapaCadenas.keySet()) {
            nuevoMapa.put(llave.toUpperCase(), mapaCadenas.get(llave));
        }
        mapaCadenas = nuevoMapa;
    }

    public boolean compararValores(String[] otroArreglo) {
        for(String valor : otroArreglo) {
            boolean encontrado = false;
            for(String valorMapa : mapaCadenas.values()) {
                if(valorMapa.equals(valor)) {
                    encontrado = true;
                    break;
                }
            }
            if(!encontrado) {
                return false;
            }
        }
        return true;
    }
}