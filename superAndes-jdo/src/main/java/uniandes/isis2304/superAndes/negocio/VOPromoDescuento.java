package uniandes.isis2304.superAndes.negocio;

public interface VOPromoDescuento {

	/**
	 * @return El descuento aplicado al producto de la promoci�n
	 */
	public int getDescuento();
	
	/**
	 * @return Una candena con la informaci�n b�sica de la promoci�n
	 */
	@Override
	public String toString();
}
