package uniandes.isis2304.superAndes.negocio;

public interface VOPromoDescuento {

	/**
	 * @return El descuento aplicado al producto de la promoción
	 */
	public int getDescuento();
	
	/**
	 * @return Una candena con la información básica de la promoción
	 */
	@Override
	public String toString();
}
