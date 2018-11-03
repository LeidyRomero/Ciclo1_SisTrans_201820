package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.*;

/**
 * 
 * @author lj.romero / mj.ocampov
 *
 */
class SQLUtil
{
	//--------------------------------------------------------
	// CONSTANTES
	//--------------------------------------------------------
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	//--------------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------------
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;

	//--------------------------------------------------------
	// CONSTRUCTOR
	//--------------------------------------------------------
	/**
	 * Constructor
	 * @param persistencia - El manejador de persistencia de la aplicación
	 */
	public SQLUtil(PersistenciaSuperAndes persistencia) 
	{
		this.pp = persistencia;
	}
	
	//--------------------------------------------------------
	// MÉTODOS
	//--------------------------------------------------------
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia para los pedidos
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	@SuppressWarnings("rawtypes")
	public long nextvalPedidos (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.getSecuenciaPedidos() + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	@SuppressWarnings("rawtypes")
	public long nextvalPromociones (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.getSecuenciaPromociones() + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	@SuppressWarnings("rawtypes")
	public long nextvalFacturas(PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.getSecuenciaFacturas() + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}
	
	/**
	 * Crea y ejecuta una sentencia SQL para cada tabla de la base de datos - 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 24 números que indican el número de tuplas borrasdas en las tablas de la base de datos de SuperAndes
	 */
	@SuppressWarnings("rawtypes")
	public long [] limpiarSuperAndes(PersistenceManager pm)
	{
        Query qProductosOfrecidos = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlProductosOfrecidos());
        Query qCantidadEstantes = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlCantidadEnEstantes());
        Query qCantidadBodega = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlCantidadEnBodega());
        Query qComprados = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlComprados());
        Query qPedidoProducto = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlPedidoProducto());
        Query qOrdenPedido = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlOrdenPedido());
        Query qBodega = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlBodega());
        Query qPromoUnidades = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlPromoUnidades());
        Query qPromoCantidades = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlPromoCantidades());
        Query qPromoUniDescuento = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlPromoUnidadDescuento());
        Query qPromoDescuento = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlPromoDescuento());
        Query qPromociones = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlSucursalPromociones());
        Query qPromocion = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlPromocion());
        Query qEstante = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlEstante());
        Query qProveen = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlProveen());
        Query qFactura = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlFactura());
        Query qTipoProducto = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlTipoProducto());
        Query qProveedor = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlProveedor());
        Query qEmpresa = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlEmpresa());
        Query qPersona = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlPersonaNatural());
        Query qCliente = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlCliente());
        Query qSucursal = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlSucursal());
        Query qProducto = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlProducto());
        Query qCategoria = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlCategoria());

        long poEliminados = (long) qProductosOfrecidos.executeUnique();
        long ceEliminados = (long) qCantidadEstantes.executeUnique();
        long cbEliminados = (long) qCantidadBodega.executeUnique();
        long cEliminados = (long) qComprados.executeUnique();
        long ppElimiandos = (long) qPedidoProducto.executeUnique();
        long opEliminados = (long) qOrdenPedido.executeUnique();
        long bEliminados = (long) qBodega.executeUnique();
        long puEliminados = (long) qPromoUnidades.executeUnique();
        long pcEliminados = (long) qPromoCantidades.executeUnique();
        long pudEliminados = (long) qPromoUniDescuento.executeUnique();
        long pdEliminados = (long) qPromoDescuento.executeUnique();
        long psEliminados = (long) qPromociones.executeUnique();
        long pEliminados = (long) qPromocion.executeUnique();
        long eEliminados = (long) qEstante.executeUnique();
        long pvEliminados = (long) qProveen.executeUnique();
        long fEliminados = (long) qFactura.executeUnique();
        long tpEliminados = (long) qTipoProducto.executeUnique();
        long prEliminados = (long) qProveedor.executeUnique();
        long emEliminados = (long) qEmpresa.executeUnique();
        long pnEliminados = (long) qPersona.executeUnique();
        long clEliminados = (long) qCliente.executeUnique();
        long sEliminados = (long) qSucursal.executeUnique();
        long prodEliminados = (long) qProducto.executeUnique();
        long catEliminados = (long) qCategoria.executeUnique();
        return new long[] {poEliminados, ceEliminados, cbEliminados,cEliminados,ppElimiandos, opEliminados, bEliminados, puEliminados, pcEliminados, pudEliminados, pdEliminados, psEliminados, pEliminados, eEliminados, pvEliminados,fEliminados, tpEliminados, prEliminados, emEliminados, pnEliminados, clEliminados, sEliminados, prodEliminados, catEliminados};
	}
}