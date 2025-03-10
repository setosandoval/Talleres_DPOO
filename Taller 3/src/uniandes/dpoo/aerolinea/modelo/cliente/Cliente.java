package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;

import java.util.Iterator;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente {
	
	// COMPLETADO
	
    protected ArrayList<Tiquete> tiquetesSinUsar;
    protected ArrayList<Tiquete> tiquetesUsados;

    public Cliente() {
        this.tiquetesSinUsar = new ArrayList<Tiquete>();
        this.tiquetesUsados = new ArrayList<Tiquete>();
        
    }

    public abstract String getTipoCliente();
    public abstract String getIdentificador();

    public void agregarTiquete(Tiquete tiquete) {
        tiquetesSinUsar.add(tiquete);
    }

    public int calcularValorTotalTiquetes() {
        int total = 0;
        for (Tiquete tiquete : tiquetesSinUsar) {
            total += tiquete.getTarifa();
        }
        return total;
    }

    public void usarTiquetes(Vuelo vuelo) {
        Iterator<Tiquete> iterator = tiquetesSinUsar.iterator();
        while (iterator.hasNext()) {
            Tiquete tiquete = iterator.next();
            if (tiquete.getVuelo().equals(vuelo)) {
                tiquete.marcarComoUsado();
                iterator.remove(); 
                tiquetesUsados.add(tiquete);
            }
        }
    }
}
