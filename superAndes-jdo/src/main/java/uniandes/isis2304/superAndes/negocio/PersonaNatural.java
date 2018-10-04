package uniandes.isis2304.superAndes.negocio;
/**
 * Clase para modelar el concepto PERSONA NATURAL del negocio de los SuperAndes
 * @author lj.romero
 *
 */
public class PersonaNatural extends Cliente implements VOPersonaNatural{
	/**
	 * documento de la persona
	 */
	private String documento;
	/**
	 * constructor por defecto
	 */
	public PersonaNatural()
	{
		documento="";
	}
	/**
	 * crea una nueva persona con los valores recibidos por parametro
	 */
	public PersonaNatural(String pDocumento)
	{
		documento = pDocumento;
	}
	/**
	 * retonra el documento de la persona
	 */
	public String getDocumento() {
		return documento;
	}
	/**
	 * cambia el documento de la persona por el recibido por parametro
	 */
	public void setDocumento(String pDocumento)
	{
		this.documento = pDocumento;
	}
	/**
	 * retorna un string con la informacion del cliente
	 */
	public String toString() {
		return "PersonaNatural [documento=" + documento + "]";
	}


}
