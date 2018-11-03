package uniandes.isis2304.superAndes.negocio;
/**
 * Clase para modelar el concepto indice del negocio de los SuperAndes
 * @author lj.romero
 *
 */
public class Indice implements VOIndice{
	/**
	 * identificador
	 */
	private String identificador;
	/**
	 * inidice de ocupación
	 */
	private double indice;
	/**
	 * constructor por defecto
	 */
	public Indice()
	{
		identificador = "";
		indice = 0;
	}
	/**
	 * Crea un nuevo indice con los valores recibidos por parametro
	 */
	public Indice(String pDireccion, double pIndice)
	{
		identificador  = pDireccion;
		indice = pIndice;
	}
	/**
	 * 
	 */
	public String getIdentificador() {
		return identificador;
	}
	/**
	 * 
	 * @param direccion
	 */
	public void setIdentificador(String direccion) {
		this.identificador = direccion;
	}
	/**
	 * 
	 */
	public double getIndice() {
		return indice;
	}
	/**
	 * 
	 * @param indice
	 */
	public void setIndice(double indice) {
		this.indice = indice;
	}
	/**
	 * 
	 */
	public String toString() {
		return "IndiceBodega [identificador=" + identificador + ", indice=" + indice + "]";
	}

}
