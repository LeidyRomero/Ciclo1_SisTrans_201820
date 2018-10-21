package uniandes.isis2304.superAndes.negocio;

public interface VOPromoUnidades 
{
	/**
	 * @return Las unidades a pagar del producto de la promoci�n
	 */
	public int getUnidadesPagar();

	/**
	 * @return Las unidades a llevar del producto de la promoci�n
	 */
	public double getUnidadesLlevar();

	/**
	 * @return Una candena con la informaci�n b�sica de la promoci�n
	 */
	@Override
	public String toString();

}
