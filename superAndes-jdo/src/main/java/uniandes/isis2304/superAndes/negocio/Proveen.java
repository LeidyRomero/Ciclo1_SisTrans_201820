package uniandes.isis2304.superAndes.negocio;

public class Proveen implements VOProveen {

	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	
	/**
	 * Nit del proveedor 
	 */
	private int nitProveedor;
	
	/**
	 * Codigo de barras del producto
	 */
	private int codigoBarras;
	
	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	
	public Proveen()
	{
		nitProveedor = 0;
		codigoBarras = 0;
	}

	public Proveen(int nitProveedor, int codigoBarras) 
	{
		this.nitProveedor = nitProveedor;
		this.codigoBarras = codigoBarras;
	}
	
	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------
	
	public int getNitProveedor() {
		return nitProveedor;
	}

	public void setNitProveedor(int nitProveedor) {
		this.nitProveedor = nitProveedor;
	}

	public int getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(int codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	@Override
	public String toString() {
		return "Proveen [nitProveedor=" + nitProveedor + ", codigoBarras=" + codigoBarras + "]";
	}
}
