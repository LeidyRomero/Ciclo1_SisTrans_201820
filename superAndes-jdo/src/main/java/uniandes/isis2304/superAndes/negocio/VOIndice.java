package uniandes.isis2304.superAndes.negocio;
/**
 * Interfaz para los métodos get de los indices de Bodega y Estante
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 * @author lj.romero
 *
 */
public interface VOIndice {
	/**
	 * @return identificador de la bodega o del estante
	 */
	public String getIdentificador();
	/**
	 * @return indice de ocupación
	 */
	public double getIndice();
	
}