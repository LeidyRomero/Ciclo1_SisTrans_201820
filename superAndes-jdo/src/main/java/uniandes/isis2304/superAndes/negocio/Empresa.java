package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class Empresa extends Cliente implements VOEmpresa{
	private int nit;
	private String direccion;

	public Empresa()
	{
		nit = 0;
		direccion = "";
	}
	public Empresa(int pNit,String pDireccion)
	{
		nit = pNit;
		direccion = pDireccion;
	}
	//TODO SET
	public int darNitEmpresa() {
		return nit;
	}
	public String darDireccion() {
		return direccion;
	}

	public String toString() {
		return "Empresa [nit=" + nit + ", direccion=" + direccion+ "]";
	}	
}
