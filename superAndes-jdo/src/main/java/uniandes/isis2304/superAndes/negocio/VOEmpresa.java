package uniandes.isis2304.superAndes.negocio;
/**
 * Interfaz para los métodos get de EMPRESA
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 * @author lj.romero
 *
 */
public interface VOEmpresa extends VOCliente{
	/**
	 * @return nit de la empresa
	 */
	public int getNitEmpresa();
	/**
	 * @return direccion de la empresa
	 */
	public String getDireccion();
	/**
	 * @return Una cadena con la información básica de la empresa
	 */
	public String toString();
	
}
