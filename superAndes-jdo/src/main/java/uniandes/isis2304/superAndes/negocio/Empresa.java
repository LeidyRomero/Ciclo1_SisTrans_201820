package uniandes.isis2304.superAndes.negocio;
/**
 * Clase para modelar el concepto EMPRESA del negocio de los SuperAndes
 * @author lj.romero
 *
 */
public class Empresa extends Cliente implements VOEmpresa{
	/**
	 * nit de la empresa
	 */
	private int nitEmpresa;
	/**
	 * direccion de la empresa
	 */
	private String direccion;
	/**
	 * constructor por defecto
	 */
	public Empresa()
	{
		nitEmpresa = 0;
		direccion = "";
	}
	/**
	 * Crea una nueva empresa
	 * @param pNit nit de la empresa
	 * @param pDireccion direccion de la empres
	 */
	public Empresa(int pNit,String pDireccion)
	{
		nitEmpresa = pNit;
		direccion = pDireccion;
	}
	/**
	 * retorna el nit de la empresa
	 */
	public int getNitEmpresa() {
		return nitEmpresa;
	}
	/**
	 * cambia el nit de la empresa por el nit recibido por parametro
	 */
	public void setNitEmpresa(int nitEmpresa) {
		this.nitEmpresa = nitEmpresa;
	}
	/**
	 * retorna la direccion de la empresa
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * cambia la direccion de la empresa por la recibida por parametro
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * retorna un string con la informacion de la empresa
	 */
	public String toString() {
		return "Empresa [nitEmpresa=" + nitEmpresa + ", direccion=" + direccion + "]";
	}

}
