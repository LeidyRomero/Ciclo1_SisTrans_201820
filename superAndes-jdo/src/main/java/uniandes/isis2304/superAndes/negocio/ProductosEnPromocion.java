package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class ProductosEnPromocion implements VOProductosEnPromocion{

	private long id;
	private int unidadesDisponibles;
	private int codigoBarras;
	
	public ProductosEnPromocion()
	{
		id = 0;
		unidadesDisponibles=0;
		codigoBarras=0;
	}
	public ProductosEnPromocion(long pId, int pUnidadDisponibles, int pCodigoBarras)
	{
		id = pId;
		unidadesDisponibles=pUnidadDisponibles;
		codigoBarras=pCodigoBarras;
	}
	
	public long darId() {
		return id;
	}
	
	public int darUnidadesDisponibles() {
		return unidadesDisponibles;
	}
	
	public int darCodigoBarras() {
		return codigoBarras;
	}
	@Override
	public String toString() {
		return "ProductosEnPromocion [id=" + id + ", unidadesDisponibles=" + unidadesDisponibles + ", codigoBarras="
				+ codigoBarras + "]";
	}

}
