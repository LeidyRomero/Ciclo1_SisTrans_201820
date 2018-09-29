package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class PersonaNatural implements VOPersonaNatural{
	private String documento;
	private String correo;

	public PersonaNatural()
	{
		documento="";
		correo = "";
	}
	public PersonaNatural(String pDocumento, String pCorreo)
	{
		documento = pDocumento;
		correo = pCorreo;
	}
	public String darCorreo() {
		return correo;
	}
	public void cambiarCorreo(String pCorreo)
	{
		this.correo = pCorreo;
	}
	public String darDocumento() {
		return documento;
	}
	public void cambiarDocumento(String pDocumento)
	{
		this.documento = pDocumento;
	}
	//TODO 
	public String toString()
	{
		return "";
	}
}
