package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

/**
 * 
 * @author lj.romero
 *
 */
public class Producto implements VOProducto{
	private String nombre;
	private String marca;
	private String presentacion;
	private String unidadMedida;
	private String especificacionEmpacado;
	private String calidad;
	private double precioUnitario;
	private double precioUnidadMedida;
	private int cantidadPresentacion;
	private int codigoBarras;
	private Date fechaVencimiento;

	public Producto()
	{
		nombre = "";
		marca = "";
		precioUnidadMedida = 0;
		presentacion = "";
		unidadMedida = "";
		especificacionEmpacado= "";
		calidad ="";
		precioUnitario=0;
		cantidadPresentacion = 0;
		codigoBarras = 0;
		fechaVencimiento = null;
	}
	public Producto(String pNombre, String pMarca, String pUnidadMedida, String pPresentacion, String pEspecificacionEmpacado, String pCalidad, double pPrecioUnitario,double pPrecioUnidadMedida,int pCantidadPresentacion, int pCodigoBarras, Date pFechaVencimiento)
	{
		nombre = pNombre;
		marca = pMarca;
		precioUnidadMedida = pPrecioUnidadMedida;
		presentacion = pPresentacion;
		unidadMedida = pUnidadMedida;
		especificacionEmpacado= pEspecificacionEmpacado;
		calidad =pCalidad;
		precioUnitario=pPrecioUnitario;
		cantidadPresentacion = pCantidadPresentacion;
		codigoBarras = pCodigoBarras;
		fechaVencimiento = pFechaVencimiento;
	}
	//TODO SET
	public String darNombre() {
		return nombre;
	}
	public String darMarca() {
		return marca;
	}
	public double darPrecioUnitario() {
		return precioUnitario;
	}

	public String darPresentacion() {
		return presentacion;
	}
	public double darPrecioUnitarioMedida() {
		return precioUnidadMedida;
	}
	public int darCantidadPresentacion() {
		return cantidadPresentacion;
	}
	public String darUnidadMedida() {
		return unidadMedida;
	}
	public String darEspecificacionEmpacado() {
		return especificacionEmpacado;
	}
	public int darCodigoBarras() {
		return codigoBarras;
	}
	public String darCalidad() {
		return calidad;
	}
	public Date darFechaVencimiento() {
		return fechaVencimiento;
	}
	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", marca=" + marca + ", presentacion=" + presentacion + ", unidadMedida="
				+ unidadMedida + ", especificacionEmpacado=" + especificacionEmpacado + ", calidad=" + calidad
				+ ", precioUnitario=" + precioUnitario + ", precioUnidadMedida=" + precioUnidadMedida
				+ ", cantidadPresentacion=" + cantidadPresentacion + ", codigoBarras=" + codigoBarras
				+ ", fechaVencimiento=" + fechaVencimiento + "]";
	}
	
}
