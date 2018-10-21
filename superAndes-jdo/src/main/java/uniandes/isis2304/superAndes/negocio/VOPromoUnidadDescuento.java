package uniandes.isis2304.superAndes.negocio;

public interface VOPromoUnidadDescuento 
{
	/**
	 * @return El descuento de la promocion
	 */
	public int getUnidades();

	/**
	 * @return Las unidades que debe comprar de la promocion
	 */
	public double getDescuento();
	
	/** 
	 * @returnUna Una cadena con la informaci�n b�sica de la Promoci�n.
	 */
	public String toString();
}
