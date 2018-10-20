package uniandes.isis2304.superAndes.negocio;
/**
 * Clase para modelar el concepto CARRITO del negocio de los SuperAndes
 * @author lj.romero
 *
 */
public class Carrito implements VOCarrito{

	//----------------------------------------------
	//Atributos
	//----------------------------------------------
	/**
	 * Id del carrito
	 */
	private long idCarrito;
	/**
	 * Correo del cliente
	 */
	private String correo;
	//----------------------------------------------
	//Constructores
	//----------------------------------------------
	/**
	 * Constructor por defecto
	 */
	public Carrito()
	{
		this.idCarrito=0;
		this.correo ="";
	}
	/**
	 * Crea un nuevo carrito con los valores ingresados por parametro
	 * @param 
	 */
	public Carrito(long Id, String pCorreo)
	{
		this.idCarrito=Id;
		this.correo =pCorreo;
	}
	//----------------------------------------------
	//Metodos
	//----------------------------------------------
	/**
	 * @return id del carrito
	 */
	public long getIdCarrito() {
		return idCarrito;
	}
	/**
	 * Cambia el id del carrito por el recibido por parametro
	 * @param idCarrito nuevo id
	 */
	public void setIdCarrito(long idCarrito) {
		this.idCarrito = idCarrito;
	}
	/**
	 * @return correo del cliente
	 */
	public String getCorreo() {
		return correo;
	}
	/**
	 * Cambia el correo dle cliente
	 * @param correo nuevo correo
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	/**
	 * @return mensaje con la informacion basica del carrito.
	 */
	public String toString() {
		return "Carrito [idCarrito=" + idCarrito + ", correo=" + correo + "]";
	}
	
}
