package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {
	
	// COMPLETADO

    protected static final int COSTO_POR_KM_NATURAL = 600;
    protected static final int COSTO_POR_KM_CORPORATIVO = 900;
    protected static final double DESCUENTO_PEQ = 0.02;
    protected static final double DESCUENTO_MEDIANAS = 0.1;
    protected static final double DESCUENTO_GRANDES = 0.2;

    @Override
    protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
        Ruta ruta = vuelo.getRuta();
        int distancia = calcularDistanciaVuelo(ruta);

        if (cliente.getTipoCliente().equals(ClienteCorporativo.CORPORATIVO)) {
            return COSTO_POR_KM_CORPORATIVO * distancia;
        } else {
            return COSTO_POR_KM_NATURAL * distancia;
        }
    }

    @Override
    protected double calcularPorcentajeDescuento(Cliente cliente) {
        if (cliente instanceof ClienteCorporativo) {
            ClienteCorporativo clienteCorp = (ClienteCorporativo) cliente;
            int tamano = clienteCorp.getTamanoEmpresa();

            if (tamano == ClienteCorporativo.GRANDE) {
                return DESCUENTO_GRANDES;
            } else if (tamano == ClienteCorporativo.MEDIANA) {
                return DESCUENTO_MEDIANAS;
            } else if (tamano == ClienteCorporativo.PEQUENA) {
                return DESCUENTO_PEQ;
            }
        }
        return 0.0;
    }
}
