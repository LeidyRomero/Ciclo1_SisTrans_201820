package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

/**
 * Clase para modelar el concepto PRODUCTO del negocio de los SuperAndes
 * @author lj.romero
 *
 */
public class Producto implements VOProducto{
	private String nombreProducto;
	private String marca;
	private String presentacion;
	private String unidadMedida;
	private String pesoProducto;
	private String volumenProducto;
	private String calidad;
	private double precioUnitario;
	private double precioUniMedida;
	private int cantPresentacion;
	private String codBarras;
	private Date fechaVencimiento;
	private String nombreCategoria;

	public Producto()
	{
		nombreProducto = "";
		marca = "";
		precioUniMedida = 0;
		presentacion = "";
		unidadMedida = "";
		pesoProducto= "";
		volumenProducto ="";
		calidad ="";
		precioUnitario=0;
		cantPresentacion = 0;
		codBarras = "";
		fechaVencimiento = null;
		nombreCategoria = "";
	}
	public Producto(String pNombre, String pMarca, String pUnidadMedida, String pPresentacion, String pCalidad, double pPrecioUnitario,double pPrecioUnidadMedida,int pCantidadPresentacion, String pCodigoBarras, Date pFechaVencimiento, String pPeso, String pVolumen,String pNombreCategoria)
	{
		nombreProducto = pNombre;
		marca = pMarca;
		precioUniMedida = pPrecioUnidadMedida;
		presentacion = pPresentacion;
		unidadMedida = pUnidadMedida;
		pesoProducto= pPeso;
		volumenProducto =pVolumen;
		calidad =pCalidad;
		precioUnitario=pPrecioUnitario;
		cantPresentacion = pCantidadPresentacion;
		codBarras = pCodigoBarras;
		fechaVencimiento = pFechaVencimiento;
		nombreCategoria = pNombreCategoria;
	}
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
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
	public String getPesoProducto() {
		return pesoProducto;
	}
	public void setPesoProducto(String pesoProducto) {
		this.pesoProducto = pesoProducto;
	}
	public String getVolumenProducto() {
		return volumenProducto;
	}
	public void setVolumenProducto(String volumenProducto) {
		this.volumenProducto = volumenProducto;
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
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public double getPrecioUniMedida() {
		return precioUniMedida;
	}
	public void setPrecioUniMedida(double precioUnidadMedida) {
		this.precioUniMedida = precioUnidadMedida;
	}
	public int getCantPresentacion() {
		return cantPresentacion;
	}
	public void setCantPresentacion(int cantPresentacion) {
		this.cantPresentacion = cantPresentacion;
	}
	public String getCodBarras() {
		return codBarras;
	}
	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	@Override
	public String toString() {
		return "Producto [nombreProducto=" + nombreProducto + ", marca=" + marca + ", presentacion=" + presentacion
				+ ", unidadMedida=" + unidadMedida + ", pesoProducto=" + pesoProducto + ", volumenProducto="
				+ volumenProducto + ", calidad=" + calidad + ", precioUnitario=" + precioUnitario
				+ ", precioUnidadMedida=" + precioUniMedida + ", cantPresentacion=" + cantPresentacion
				+ ", codBarras=" + codBarras + ", fechaVencimiento=" + fechaVencimiento + "]";
	}
	
}
