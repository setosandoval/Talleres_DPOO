
package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas{
	protected static final int COSTO_POR_KM = 1000;
	
	// COMPLETADO
	
	public CalculadoraTarifasTemporadaAlta() {
		super();
	}
	
	@Override
	public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		Ruta ruta = vuelo.getRuta();
		return Aeropuerto.calcularDistancia(ruta.getOrigen(), ruta.getDestino())*COSTO_POR_KM;
	}

	@Override
	public double calcularPorcentajeDescuento(Cliente cliente) {
		return 0;
	}


}