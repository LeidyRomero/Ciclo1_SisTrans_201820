package uniandes.isis2304.superAndes.negocio;

import java.util.ArrayList;

public class CantidadEnEstantes implements VOCantidadEnEstantes {

	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	
	/**
	 * Cantidad actual de cada producto en estante
	 */
	private int cantidadActual;
	
	/**
	 * Cantidad mínima en estante para cada producto 
	 */
	private int cantidadMinima;
	
	/**
	 * Código de barras del producto
	 */
	private int codigoBarrasProducto;
	
	/**
	 * Id del estante
	 */
	private long idEstante;
	
	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	public CantidadEnEstantes()
	{
		cantidadActual = 0;
		cantidadMinima = 0;
		codigoBarrasProducto = 0;
		idEstante = 0;
	}
	
	public CantidadEnEstantes(int pCantidadActual, int pCantidadMinima, int pCodigoBarras, long pIdEstante)
	{
		cantidadActual = pCantidadActual;
		cantidadMinima = pCantidadMinima;
		codigoBarrasProducto = pCodigoBarras;
		idEstante = pIdEstante;
	}
	
	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------
	
	public int getCantidadActual() {
		return cantidadActual;
	}

	public void setCantidadActual(int cantidadActual) {
		this.cantidadActual = cantidadActual;
	}

	public int getCantidadMinima() {
		return cantidadMinima;
	}

	public void setCantidadMinima(int cantidadMinima) {
		this.cantidadMinima = cantidadMinima;
	}

	public int getCodigoBarrasProducto() {
		return codigoBarrasProducto;
	}

	public void setCodigoBarrasProducto(int codigoBarrasProducto) {
		this.codigoBarrasProducto = codigoBarrasProducto;
	}

	public long getIdEstante() {
		return idEstante;
	}

	public void setIdEstante(long idEstante) {
		this.idEstante = idEstante;
	}

	@Override
	public String toString() {
		return "CantidadEnEstantes [cantidadActual=" + cantidadActual + ", cantidadMinima=" + cantidadMinima
				+ ", codigoBarrasProducto=" + codigoBarrasProducto + ", idEstante=" + idEstante + "]";
	}

	
}
