package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

public class PromoDescuento extends Promocion implements VOPromoDescuento
{
	//-----------------------------------------------------
	// CONSTANTES
	//-----------------------------------------------------

	/**
	 * Constante que representa la descripción de la promoción
	 */
	public final static String DESCIRPCION = "Descuento del D%.";

	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------

	/**
	 * Descuento
	 */
	private int descuento;

	//-----------------------------------------------------
	// CONSTRUCOTRES
	//-----------------------------------------------------
	
	/**
	 * Constructor con valores
	 * @param fechaInicial Fecha inicio. fechaInicial != null
	 * @param fechaFinal Fecha final. fechaFinal != null
	 * @param descripcion Descripcion de la promocion. descripcion != null && descripcion != ""
	 * @param idPromocion Id de la promocion. idPromocion >= 0
	 * @param codigoBarras Codigo barras del producto. codigoBarras > 0
	 * @param unidadesDisponibles Unidades disponibles. unidadesDisponibles > 0
	 * @param unidadesVendidas Unidades vendidas. unidadesVendidas > 0
	 * @param estado
	 * @param descuento
	 */
	public PromoDescuento(Timestamp fechaInicial, Timestamp fechaFinal, long idPromocion,
			String codigoBarras, int unidadesVendidas, int unidadesDisponibles, String estado, int descuento) {
		super(fechaInicial, fechaFinal, "", idPromocion, codigoBarras, unidadesVendidas, unidadesDisponibles,
				estado);
		String descripcion = DESCIRPCION.replace("N", ""+descuento);
		super.setDescripcion(descripcion);
		this.descuento = descuento;
	}
	
	/**
	 * Constructor por defecto
	 */
	public PromoDescuento() {
		super();
		this.descuento = 0;
	}

	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------
	
	/**
	 * Retorna el descuento sobre el producto de la promoción
	 */
	public int getDescuento() {
		return descuento;
	}

	/**
	 * Modifica el descuento
	 * @param descuento Nuevo descuento
	 */
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	@Override
	public String toString() {
		return "PromoDescuento [descuento=" + descuento + "]";
	}

	
}
