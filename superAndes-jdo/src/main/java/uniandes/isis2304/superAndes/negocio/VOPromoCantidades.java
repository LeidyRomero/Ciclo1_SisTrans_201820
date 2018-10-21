package uniandes.isis2304.superAndes.negocio;

public interface VOPromoCantidades 
{
	/**
	 * @return La cantidad de la que va a pagar el precio
	 */
	public int getCantidadPagar();
	
	/**
	 * @return La cantidad que va a llevar
	 */
	public double getCantidadLlevar();
	
	/**
	 * @return Una cadena con la información básica de la Promoción.
	 */
	public String toString();
}
