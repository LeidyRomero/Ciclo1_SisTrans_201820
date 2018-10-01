package uniandes.isis2304.superAndes.negocio;

import java.util.ArrayList;

/**
 * Clase para modelar el concepto CANTIDA EN ESTANTES del negocio de los SuperAndes
 * 
 * @author Mar�a Ocampo - mj.ocampov
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
	 * Cantidad m�nima en estante para cada producto 
	 */
	private int cantidadMinima;
	
	/**
	 * C�digo de barras del producto
	 */
	private int codigoBarrasProducto;
	
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
		codigoBarrasProducto = 0;
		idEstante = 0;
	}
	
	/**
	 * Crea una cantidad en estantes con los valores dados por par�metro.
	 * @param pCantidadActual Cantidad actual del producto en el estante. pCantidadActual != null && pCantidadActual >= 0
	 * @param pCantidadMinima Cantidad m�nima del producto en el estante. pCantidadMinima != null && pCantidadMinima >= 0
	 * @param pCodigoBarras Codigo de barras del producto. pCodigoBarras != null && pCodigoBarras >= 0
	 * @param pIdEstante Id del estante. pIdEstante != null && pIdEstante >= 0
	 */
	public CantidadEnEstantes(int pCantidadActual, int pCantidadMinima, int pCodigoBarras, long pIdEstante)
	{
		cantidadActual = pCantidadActual;
		cantidadMinima = pCantidadMinima;
		codigoBarrasProducto = pCodigoBarras;
		idEstante = pIdEstante;
	}
	
	//-----------------------------------------------------
	// M�TODOS
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
	 * Retorna la cantidad m�nima del producto en el estante.
	 */
	public int getCantidadMinima() {
		return cantidadMinima;
	}

	/**
	 * Modifica la cantidad m�nima del producto en el estante.
	 * @param pCantidadMinima Nueva cantidad m�nima
	 */
	public void setCantidadMinima(int pCantidadMinima) {
		this.cantidadMinima = pCantidadMinima;
	}

	/**
	 * Retorna el c�digo de barras del producto.
	 */
	public int getCodigoBarrasProducto() {
		return codigoBarrasProducto;
	}

	/**
	 * Modifica el c�digo de barras del producto.
	 * @param codigoBarrasProducto Nuevo c�digo de barras.
	 */
	public void setCodigoBarrasProducto(int codigoBarrasProducto) {
		this.codigoBarrasProducto = codigoBarrasProducto;
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
				+ ", codigoBarrasProducto=" + codigoBarrasProducto + ", idEstante=" + idEstante + "]";
	}

	
}
