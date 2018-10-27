package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

public class PromoUnidades extends Promocion implements VOPromoUnidades
{
	//-----------------------------------------------------
	// CONSTANTES
	//-----------------------------------------------------

	/**
	 * Constante que representa la descripción de la promoción
	 */
	public final static String DESCIRPCION = "Pague N unidades, lleve M";

	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------

	/**
	 * Unidades a pagar del producto en promoción
	 */
	private int unidadesPagar;

	/**
	 * Unidades a llevar del producto en promoción
	 */
	private double unidadesLlevar;
	
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
	 * @param unidadesPagar Unidades a pagar. unidadesPagar > 0
	 * @param unidadesLlevar Unidades a llevar. unidadesLlevar > 0
	 */
	public PromoUnidades(Timestamp fechaInicial, Timestamp fechaFinal, long idPromocion,
			String codigoBarras, int unidadesVendidas, int unidadesDisponibles, String estado, int unidadesPagar,
			double unidadesLlevar) {
		super(fechaInicial, fechaFinal, "", idPromocion, codigoBarras, unidadesVendidas, unidadesDisponibles,
				estado);
		String descripcion = DESCIRPCION.replace("N", ""+unidadesPagar).replace("M", ""+unidadesLlevar);
		super.setDescripcion(descripcion);
		this.unidadesPagar = unidadesPagar;
		this.unidadesLlevar = unidadesLlevar;
	}
	
	/**
	 * Constructor por defecto
	 */
	public PromoUnidades() {
		super();
		this.unidadesPagar = 0;
		this.unidadesLlevar = 0;
	}

	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------
	
	/**
	 * 
	 */
	public int getUnidadesPagar() {
		return unidadesPagar;
	}

	/**
	 * 
	 * @param unidadesPagar
	 */
	public void setUnidadesPagar(int unidadesPagar) {
		this.unidadesPagar = unidadesPagar;
	}

	/**
	 * 
	 */
	public double getUnidadesLlevar() {
		return unidadesLlevar;
	}

	/**
	 * 
	 * @param unidadesLlevar
	 */
	public void setUnidadesLlevar(double unidadesLlevar) {
		this.unidadesLlevar = unidadesLlevar;
	}

	@Override
	public String toString() {
		return "PromoUnidades [unidadesPagar=" + unidadesPagar + ", unidadesLlevar=" + unidadesLlevar + "]";
	}

}
