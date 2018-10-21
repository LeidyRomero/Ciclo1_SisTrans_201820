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
	 * @return Una cadena con la informaci�n b�sica de la Promoci�n.
	 */
	public String toString();
}
