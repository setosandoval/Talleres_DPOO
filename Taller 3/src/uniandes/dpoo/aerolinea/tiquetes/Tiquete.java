package uniandes.dpoo.aerolinea.tiquetes;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class Tiquete {
	
	// COMPLETADO 
	
	private Vuelo vuelo; 
	private Cliente cliente;
	private String codigo; 
	private int tarifa;
	private boolean usado; 
	
	public Tiquete(String codigo, Vuelo vuelo, Cliente clienteComprador, int tarifa) {
		this.codigo = codigo;
		this.tarifa = tarifa;
		this.vuelo = vuelo;
		this.cliente = clienteComprador;
		this.usado = false;
	}
	
	public Cliente getCliente() {
		return cliente;
	} 
	
	public Vuelo getVuelo() {
		return vuelo;
	}

	public String getCodigo() {
		return codigo;
	}

	public int getTarifa() {
		return tarifa;
	}
	
	public void marcarComoUsado() {
		this.usado = true;
	}

	public boolean esUsado() {
		return usado;
	}

}