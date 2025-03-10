package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.HashMap;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Vuelo {
	
	// COMPLETADO
	
    private String fecha;
    private Ruta ruta;
    private Avion avion;
    private HashMap<String, Tiquete> tiquetes;

    public Vuelo(Ruta ruta, String fecha, Avion avion) {
        this.ruta = ruta;
        this.fecha = fecha;
        this.avion = avion;
        this.tiquetes = new HashMap<String, Tiquete>();
    }

    public Ruta getRuta() {
        return ruta;
    }

    public String getFecha() {
        return fecha;
    }

    public Avion getAvion() {
        return avion;
    }

    public Collection<Tiquete> getTiquetes() {
        return tiquetes.values();
    }

    public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) throws VueloSobrevendidoException {
        if (tiquetes.size() + cantidad > avion.getCapacidad()) {
            throw new VueloSobrevendidoException(this);
        }

        int total = 0;
        for (int i = 0; i < cantidad; i++) {
            int tarifa = (int) Math.round(calculadora.calcularTarifa(this, cliente));
            Tiquete tiquete = GeneradorTiquetes.generarTiquete(this, cliente, tarifa);
            tiquetes.put(tiquete.getCodigo(), tiquete);
            cliente.agregarTiquete(tiquete);
            total += tarifa;
        }

        return total;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vuelo otro = (Vuelo) obj;
        return fecha.equals(otro.fecha) &&
               ruta.equals(otro.ruta) &&
               avion.equals(otro.avion);
    }

}
