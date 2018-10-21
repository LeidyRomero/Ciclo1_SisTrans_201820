package uniandes.isis2304.superAndes.negocio;

public interface VOPromoUnidades 
{
	/**
	 * @return Las unidades a pagar del producto de la promoción
	 */
	public int getUnidadesPagar();

	/**
	 * @return Las unidades a llevar del producto de la promoción
	 */
	public double getUnidadesLlevar();

	/**
	 * @return Una candena con la información básica de la promoción
	 */
	@Override
	public String toString();

}
