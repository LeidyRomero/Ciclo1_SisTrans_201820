package uniandes.isis2304.superAndes.negocio;

import java.util.ArrayList;

/**
 * Clase para modelar el concepto CANTIDA EN ESTANTES del negocio de los SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
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
	private String codigoBarras;
	
	/**
	 * Id del estante
	 */
	private long idEstante;
	
	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	/**
	 * Constructor con los valores por defecto.
	 */
	public CantidadEnEstantes()
	{
		cantidadActual = 0;
		cantidadMinima = 0;
		codigoBarras = null;
		idEstante = 0;
	}
	
	/**
	 * Crea una cantidad en estantes con los valores dados por parámetro.
	 * @param pCantidadActual Cantidad actual del producto en el estante. pCantidadActual != null && pCantidadActual >= 0
	 * @param pCantidadMinima Cantidad mínima del producto en el estante. pCantidadMinima != null && pCantidadMinima >= 0
	 * @param pCodigoBarras Codigo de barras del producto. pCodigoBarras != null && pCodigoBarras >= 0
	 * @param pIdEstante Id del estante. pIdEstante != null && pIdEstante >= 0
	 */
	public CantidadEnEstantes(int pCantidadActual, int pCantidadMinima, String pCodigoBarras, long pIdEstante)
	{
		cantidadActual = pCantidadActual;
		cantidadMinima = pCantidadMinima;
		codigoBarras = pCodigoBarras;
		idEstante = pIdEstante;
	}
	
	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------
	
	/**
	 * Retorna la cantidad actual del producto en el estante.
	 */
	public int getCantidadActual() {
		return cantidadActual;
	}

	/**
	 * Modifica la cantidad actual del producto en el estante. 
	 * @param pCantidadActual Nueva cantidad actual
	 */
	public void setCantidadActual(int pCantidadActual) {
		this.cantidadActual = pCantidadActual;
	}

	/**
	 * Retorna la cantidad mínima del producto en el estante.
	 */
	public int getCantidadMinima() {
		return cantidadMinima;
	}

	/**
	 * Modifica la cantidad mínima del producto en el estante.
	 * @param pCantidadMinima Nueva cantidad mínima
	 */
	public void setCantidadMinima(int pCantidadMinima) {
		this.cantidadMinima = pCantidadMinima;
	}

	/**
	 * Retorna el código de barras del producto.
	 */
	public String getCodigoBarras() {
		return codigoBarras;
	}

	/**
	 * Modifica el código de barras del producto.
	 * @param codigoBarrasProducto Nuevo código de barras.
	 */
	public void setCodigoBarras(String codigoBarrasProducto) {
		this.codigoBarras = codigoBarrasProducto;
	}

	/**
	 * Retorna el id del estante.
	 */
	public long getIdEstante() {
		return idEstante;
	}

	/**
	 * Modifica el id del estante.
	 * @param idEstante Nuevo id del estante.
	 */
	public void setIdEstante(long idEstante) {
		this.idEstante = idEstante;
	}

	@Override
	public String toString() {
		return "CantidadEnEstantes [cantidadActual=" + cantidadActual + ", cantidadMinima=" + cantidadMinima
				+ ", codigoBarrasProducto=" + codigoBarras + ", idEstante=" + idEstante + "]";
	}
}
