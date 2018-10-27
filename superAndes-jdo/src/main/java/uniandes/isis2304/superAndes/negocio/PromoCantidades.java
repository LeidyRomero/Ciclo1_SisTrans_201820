package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

public class PromoCantidades extends Promocion implements VOPromoCantidades
{
	//-----------------------------------------------------
	// CONSTANTES
	//-----------------------------------------------------

	/**
	 * Constante que representa la descripción de la promoción
	 */
	public final static String DESCIRPCION = "Pague X cantidad, lleve Y";

	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------

	/**
	 * Cantidad a pagar
	 */
	private int cantidadPagar;

	/**
	 * Cantidad a llevar
	 */
	private double cantidadLlevar;

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
	 * @param cantidadPagar Cantidad a pagar. cantidadPagar > 0
	 * @param cantidadLlevar Cantidad a llevar. cantidadLlevar > 0
	 */
	public PromoCantidades(Timestamp fechaInicial, Timestamp fechaFinal, long idPromocion,
			String codigoBarras, int unidadesVendidas, int unidadesDisponibles, String estado, int cantidadPagar,
			double cantidadLlevar) {
		super(fechaInicial, fechaFinal, "", idPromocion, codigoBarras, unidadesVendidas,
				unidadesDisponibles, estado);
		String descripcion = DESCIRPCION.replace("X", ""+cantidadPagar).replace("Y", ""+cantidadLlevar);
		super.setDescripcion(descripcion);
		this.cantidadPagar = cantidadPagar;
		this.cantidadLlevar = cantidadLlevar;
	}

	/**
	 * Constructor por defecto
	 */
	public PromoCantidades() {
		super();
		this.cantidadPagar = 0;
		this.cantidadLlevar = 0;
	}

	//-----------------------------------------------------
	// METODOS
	//-----------------------------------------------------

	/**
	 * Retorna la cantidad a pagar del producto en promoción
	 */
	public int getCantidadPagar() {
		return cantidadPagar;
	}

	/**
	 * Modifica la cantidad a pagar del producto en promoción.
	 * @param cantidadPagar Nueva cantidad a pagar
	 */
	public void setCantidadPagar(int cantidadPagar) {
		this.cantidadPagar = cantidadPagar;
	}

	/**
	 * Retorna la cantidad a llevar del producto en promoción
	 */
	public double getCantidadLlevar() {
		return cantidadLlevar;
	}

	/**
	 * Modifica la cantidad a llevar del producto en promoción.
	 * @param cantidadLlevar Nueva cantidad a llevar
	 */
	public void setCantidadLlevar(double cantidadLlevar) {
		this.cantidadLlevar = cantidadLlevar;
	}

	@Override
	public String toString() {
		return "PromoCantidades [cantidadPagar=" + cantidadPagar + ", cantidadLlevar=" + cantidadLlevar + "]";
	}


}
