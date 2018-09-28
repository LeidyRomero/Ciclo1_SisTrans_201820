package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class Empresa implements VOEmpresa{
	private int nit;
	private String direccion;
	private String correo;

	public Empresa()
	{
		nit = 0;
		direccion = "";
		correo ="";
	}
	public Empresa(int pNit,String pDireccion, String pCorreo )
	{
		nit = pNit;
		direccion = pDireccion;
		correo = pCorreo;
	}
	//TODO SET
	public int darNitEmpresa() {
		return nit;
	}
	public String darDireccion() {
		return direccion;
	}
	public String darCorreo() {
		return correo;
	}
	//TODO completar
	public String toString()
	{
		return "";
	}
}
