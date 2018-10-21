package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

public class PromoUnidadDescuento extends Promocion implements VOPromoUnidadDescuento
{
	//-----------------------------------------------------
	// CONSTANTES
	//-----------------------------------------------------

	/**
	 * Constante que representa la descripción de la promoción
	 */
	public final static String DESCIRPCION = "Pague N y lleve el siguiente con D% de descuento.";

	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------

	/**
	 * Unidades que debe comprar para que la siguiente tenga descuento
	 */
	private int unidades;

	/**
	 * Descuento que obtiene en la sigueinte unidad comprada
	 */
	private double descuento;

	//-----------------------------------------------------
	// CONSTRUCTORES
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
	 * @param unidades Unidades que debe comprar. unidades > 0
	 * @param estado Estado de la promoción. estado != null
	 * @param descuento Descuento otorgado. descuento > 0
	 */
	public PromoUnidadDescuento(Timestamp fechaInicial, Timestamp fechaFinal, long idPromocion,
			String codigoBarras, int unidadesVendidas, int unidadesDisponibles, String estado, int unidades,
			double descuento) {		
		super(fechaInicial, fechaFinal, "", idPromocion, codigoBarras, unidadesVendidas, unidadesDisponibles,
				estado);
		String descripcion = DESCIRPCION.replace("N", ""+unidades).replace("D", ""+descuento);
		super.setDescripcion(descripcion);
		this.unidades = unidades;
		this.descuento = descuento;
	}

	/**
	 * Constructor por defecto
	 */
	public PromoUnidadDescuento() {		
		super();
		this.unidades = 0;
		this.descuento = 0;
	}

	//-----------------------------------------------------
	// METODOS
	//-----------------------------------------------------

	/**
	 * Retorna las unidades que debe comprar para que la siguiente tenga descuento.
	 */
	public int getUnidades() {
		return unidades;
	}

	/**
	 * Modifica las unidades
	 * @param unidades Nuevas unidades
	 */
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	/**
	 * Retorna el descuento que obtiene en la sigueinte unidad comprada
	 */
	public double getDescuento() {
		return descuento;
	}

	/**
	 * Modifica el descuento
	 * @param descuento Nuevo descuento.
	 */
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	@Override
	public String toString() {
		return "PromoUnidadDescuento [unidades=" + unidades + ", descuento=" + descuento + "]";
	}



}
