package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class Empresa extends Cliente implements VOEmpresa{
	private int nitEmpresa;
	private String direccion;

	public Empresa()
	{
		nitEmpresa = 0;
		direccion = "";
	}
	public Empresa(int pNit,String pDireccion)
	{
		nitEmpresa = pNit;
		direccion = pDireccion;
	}
	public int getNitEmpresa() {
		return nitEmpresa;
	}
	public void setNitEmpresa(int nitEmpresa) {
		this.nitEmpresa = nitEmpresa;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	@Override
	public String toString() {
		return "Empresa [nitEmpresa=" + nitEmpresa + ", direccion=" + direccion + "]";
	}

}
