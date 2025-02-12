package uniandes.dpoo.estructuras.logica;

import java.util.*;

public class SandboxMapas {
    private Map<String, String> mapaCadenas;

    public SandboxMapas() {
        mapaCadenas = new HashMap<>();
    }

    public List<String> getValoresComoLista() {
        List<String> valores = new ArrayList<>(mapaCadenas.values());
        Collections.sort(valores);
        return valores;
    }

    public List<String> getLlavesComoListaInvertida() {
        List<String> llaves = new ArrayList<>(mapaCadenas.keySet());
        llaves.sort(Collections.reverseOrder());
        return llaves;
    }

    public String getPrimera() {
        return mapaCadenas.isEmpty() ? null : Collections.min(mapaCadenas.keySet());
    }

    public String getUltima() {
        return mapaCadenas.isEmpty() ? null : Collections.max(mapaCadenas.values());
    }

    public Collection<String> getLlaves() {
        List<String> llavesMayusculas = new ArrayList<>();
        for (String llave : mapaCadenas.keySet()) {
            llavesMayusculas.add(llave.toUpperCase());
        }
        return llavesMayusculas;
    }

    public int getCantidadCadenasDiferentes() {
        return new HashSet<>(mapaCadenas.values()).size();
    }

    public void agregarCadena(String cadena) {
        String llaveInvertida = new StringBuilder(cadena).reverse().toString();
        mapaCadenas.put(llaveInvertida, cadena);
    }

    public void eliminarCadenaConLLave(String llave) {
        mapaCadenas.remove(llave);
    }

    public void eliminarCadenaConValor(String valor) {
        mapaCadenas.values().removeIf(v -> v.equals(valor));
    }

    public void reiniciarMapaCadenas(List<Object> objetos) {
        mapaCadenas.clear();
        for (Object obj : objetos) {
            String cadena = obj.toString();
            String llaveInvertida = new StringBuilder(cadena).reverse().toString();
            mapaCadenas.put(llaveInvertida, cadena);
        }
    }

    public void volverMayusculas() {
        Map<String, String> nuevoMapa = new HashMap<>();
        for (Map.Entry<String, String> entry : mapaCadenas.entrySet()) {
            nuevoMapa.put(entry.getKey().toUpperCase(), entry.getValue());
        }
        mapaCadenas = nuevoMapa;
    }

    public boolean compararValores(String[] otroArreglo) {
        Set<String> valoresSet = new HashSet<>(mapaCadenas.values());
        for (String valor : otroArreglo) {
            if (!valoresSet.contains(valor)) {
                return false;
            }
        }
        return true;
    }
}