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
	//TODO 
	public String toString()
	{
		return "";
	}
}
