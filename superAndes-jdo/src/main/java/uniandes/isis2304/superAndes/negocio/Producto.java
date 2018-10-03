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
	private String peso;
	private String volumen;
	private String calidad;
	private double precioUnitario;
	private double precioUnidadMedida;
	private int cantidadPresentacion;
	private String codigoBarras;
	private Date fechaVencimiento;

	public Producto()
	{
		nombre = "";
		marca = "";
		precioUnidadMedida = 0;
		presentacion = "";
		unidadMedida = "";
		peso= "";
		volumen ="";
		calidad ="";
		precioUnitario=0;
		cantidadPresentacion = 0;
		codigoBarras = "";
		fechaVencimiento = null;
	}
	public Producto(String pNombre, String pMarca, String pUnidadMedida, String pPresentacion, String pCalidad, double pPrecioUnitario,double pPrecioUnidadMedida,int pCantidadPresentacion, String pCodigoBarras, Date pFechaVencimiento, String pPeso, String pVolumen)
	{
		nombre = pNombre;
		marca = pMarca;
		precioUnidadMedida = pPrecioUnidadMedida;
		presentacion = pPresentacion;
		unidadMedida = pUnidadMedida;
		peso= pPeso;
		volumen =pVolumen;
		calidad =pCalidad;
		precioUnitario=pPrecioUnitario;
		cantidadPresentacion = pCantidadPresentacion;
		codigoBarras = pCodigoBarras;
		fechaVencimiento = pFechaVencimiento;
	}
	//TODO SET
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getVolumen() {
		return volumen;
	}
	public void setVolumen(String volumen) {
		this.volumen = volumen;
	}
	public String getCalidad() {
		return calidad;
	}
	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", marca=" + marca + ", presentacion=" + presentacion + ", unidadMedida="
				+ unidadMedida + ", peso=" + peso + ", volumen=" + volumen + ", calidad=" + calidad
				+ ", precioUnitario=" + precioUnitario + ", precioUnidadMedida=" + precioUnidadMedida
				+ ", cantidadPresentacion=" + cantidadPresentacion + ", codigoBarras=" + codigoBarras
				+ ", fechaVencimiento=" + fechaVencimiento + "]";
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public double getPrecioUnidadMedida() {
		return precioUnidadMedida;
	}
	public void setPrecioUnidadMedida(double precioUnidadMedida) {
		this.precioUnidadMedida = precioUnidadMedida;
	}
	public int getCantidadPresentacion() {
		return cantidadPresentacion;
	}
	public void setCantidadPresentacion(int cantidadPresentacion) {
		this.cantidadPresentacion = cantidadPresentacion;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
}
