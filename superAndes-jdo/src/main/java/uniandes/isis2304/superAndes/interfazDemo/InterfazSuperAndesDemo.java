package uniandes.isis2304.superAndes.interfazDemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.superAndes.interfazApp.InterfazSuperAndesApp;
import uniandes.isis2304.superAndes.interfazApp.PanelDatos;
import uniandes.isis2304.superAndes.negocio.SuperAndes;
import uniandes.isis2304.superAndes.negocio.*;


@SuppressWarnings("serial")
public class InterfazSuperAndesDemo extends JFrame implements ActionListener{

	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazSuperAndesDemo.class.getName());
	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIGURACION_INTERFAZ = "./src/main/resources/config/interfaceConfigDemo.json"; 
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIGURACION_TABLAS = "./src/main/resources/config/TablasBD_A.json";
	// -----------------------------------------------------------------
	// Atributos 
	// -----------------------------------------------------------------
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * Asociación a la clase principal del negocio.
	 */
	private SuperAndes superAndes;
	// -----------------------------------------------------------------
	// Atributos de la interfaz
	// -----------------------------------------------------------------
	/**
	 * Objeto JSON con la configuración de interfaz de la app.
	 */
	private JsonObject guiConfig;
	/**
	 * Panel de despliegue de interacción para los requerimientos
	 */
	private PanelDatos panelDatos;
	/**
	 * Menú de la aplicación
	 */
	private JMenuBar menuSA;
	/**
	 * Construye la ventana principal de la aplicación. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazSuperAndesDemo()
	{
		// Carga la configuración de la interfaz desde un archivo JSON
		guiConfig = openConfig ("Interfaz", CONFIGURACION_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gráfica
		configurarFrame ( );

		if (guiConfig != null) 
		{
			crearMenu( guiConfig.getAsJsonArray("menu") );
		}

		String path = guiConfig.get("bannerPath").getAsString();

		tableConfig = openConfig ("Tablas BD", CONFIGURACION_TABLAS);
		superAndes = new SuperAndes(tableConfig);


		panelDatos = new PanelDatos ( );

		setLayout (new BorderLayout());
		add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
		add( panelDatos, BorderLayout.CENTER );   
	}
	// -----------------------------------------------------------------
	// Configuracion de la interfaz
	// -----------------------------------------------------------------
	/**
	 * Lee datos de configuración para la aplicacion, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuración deseada
	 * @param archConfig - Archivo Json que contiene la configuración
	 * @return Un objeto JSON con la configuración del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			File aja =new File(archConfig);
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido "+ e);			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Parranderos Demo", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	/**
	 * Método para configurar el frame principal de la aplicación
	 */
	private void configurarFrame(  )
	{
		int alto = 0;
		int ancho = 0;
		String titulo = "";	

		if ( guiConfig == null )
		{
			log.info ( "Se aplica configuración por defecto" );			
			titulo = "SuperAndes Demo Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
			titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocation (50,50);
		setResizable( true );
		setBackground( Color.WHITE );

		setTitle( titulo );
		setSize ( ancho, alto);        
	}
	/**
	 * Método para crear el menú de la aplicación con base em el objeto JSON leído
	 * Genera una barra de menú y los menús con sus respectivas opciones
	 * @param jsonMenu - Arreglo Json con los menùs deseados
	 */
	private void crearMenu(JsonArray jsonMenu )
	{    	
		// Creación de la barra de menús
		menuSA = new JMenuBar();       
		for (JsonElement men : jsonMenu)
		{
			// Creación de cada uno de los menús
			JsonObject jom = men.getAsJsonObject(); 

			String menuTitle = jom.get("menuTitle").getAsString();        	
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu( menuTitle);
			for (JsonElement op : opciones)
			{       	
				// Creación de cada una de las opciones del menú
				JsonObject jo = op.getAsJsonObject(); 
				String lb =   jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem( lb );
				mItem.addActionListener( this );
				mItem.setActionCommand(event);

				menu.add(mItem);
			}       
			menuSA.add( menu );
		}        
		setJMenuBar ( menuSA );	
	}

	//TODO all CRUD 
	//----------------------------------------------------------------------------------------------
	// BODEGA
	//----------------------------------------------------------------------------------------------
	public void demoBodega ( )
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			VOBodega bodega1 = superAndes.adicionarBodega("Congelados",1000, 50, "KR 163B #57-29", "Cl. 39 #20A-04", "Bogota");

			List <VOBodega> lista = superAndes.darVOBodegas ();

			long bodegasEliminadas = superAndes.eliminarBodega("KR 163B #57-29", "Cl. 39 #20A-04", "Bogota");

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Bodegas\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "Adicionado la bodega: " + bodega1 + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarBodegas (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += bodegasEliminadas + " Bodegas eliminados\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	private String listarBodegas (List<VOBodega> lista) 
	{
		String resp = "Las bodegas existentes son:\n";
		int i = 1;
		for (VOBodega bodega : lista)
		{
			resp += i++ + ". " + bodega.toString() + "\n";
		}
		return resp;
	}
	//----------------------------------------------------------------------------------------------
	// CANTIDAD EN BODEGA
	//----------------------------------------------------------------------------------------------
	public void demoCantidadEnBodega ( )
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			VOCantidadEnBodega cantidadEnBodega1 = superAndes.adicionarCantidadEnBodega("KR 163B #57-29", "Cl. 39 #20A-04", "Bogota", 20, 10, "F000AC6");

			List <VOCantidadEnBodega> lista = superAndes.darVOCantidadEnBodega ();

			long cantidadesEnBodegaEliminadas = superAndes.eliminarCantidadEnBodega("F000AC6","Cl. 39 #20A-04", "KR 163B #57-29", "Bogota");

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado del inventario en bodega\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "Adicionado la cantidad: " + cantidadEnBodega1 + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarCantidadesEnBodega (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += cantidadesEnBodegaEliminadas + "  eliminados\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	private String listarCantidadesEnBodega (List<VOCantidadEnBodega> lista) 
	{
		String resp = "Las cantidades existentes son:\n";
		int i = 1;
		for (VOCantidadEnBodega cantidadesEnBodega : lista)
		{
			resp += i++ + ". " + cantidadesEnBodega.toString() + "\n";
		}
		return resp;
	}

	//--------------------------------------------------------------------
	// Demo PROVEEDOR
	//--------------------------------------------------------------------
	public void demoProveedores()
	{
		try
		{
			//Ejecución de la demo y recolección de los resultados
			VOProveedor pro1 = superAndes.adicionarProveedor(123546987, "Alpina");

			List<VOProveedor> lista = superAndes.darVOProveedores();

			long proveedoresEliminados = superAndes.eliminarProveedorPorNit(123546987);

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Proveedores\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "Adicionado el proveedor: " + pro1 + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarProveedores (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += proveedoresEliminados + " Proveedores eliminados\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);	
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarProveedores (List<VOProveedor> lista) 
	{
		String resp = "Los proveedores existentes son:\n";
		int i = 1;
		for (VOProveedor proveedor : lista)
		{
			resp += i++ + ". " + proveedor.toString() + "\n";
		}
		return resp;
	}

	//--------------------------------------------------------------------
	// Demo CLIENTE
	//--------------------------------------------------------------------
	public void demoClientes()
	{
		try
		{
			//Ejecución de la demo y recolección de los resultados
			VOCliente cli1 = superAndes.adicionarCliente("majocava0417@hotmail.com", "María Ocampo");

			List<VOCliente> lista = superAndes.darVOClientes();

			long clientesEliminados = superAndes.eliminarClientePorCorreo("majocava0417@hotmail.com");

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Clientes\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "Adicionado el cliente: " + cli1 + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarClientes (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += clientesEliminados + " Clientes eliminados\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);	
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarClientes (List<VOCliente> lista) 
	{
		String resp = "Los clientes existentes son:\n";
		int i = 1;
		for (VOCliente cliente : lista)
		{
			resp += i++ + ". " + cliente.toString() + "\n";
		}
		return resp;
	}

	//--------------------------------------------------------------------
	// Demo SUCURSAL
	//--------------------------------------------------------------------
	public void demoSucursales()
	{
		try
		{
			//Ejecución de la demo y recolección de los resultados
			VOSucursal cli1 = superAndes.adicionarSucursal("50 m^2", "Calle 7 #5-74", "Bogotá", "Candelaria");

			List<VOSucursal> lista = superAndes.darVOSucursales();

			long clientesEliminados = superAndes.eliminarSucursal("Calle 7 #5-74", "Bogotá");

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Sucursales\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "Adicionado la sucursal: " + cli1 + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarSucursales (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += clientesEliminados + " Sucursales eliminados\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);	
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarSucursales (List<VOSucursal> lista) 
	{
		String resp = "Las sucursales existentes son:\n";
		int i = 1;
		for (VOSucursal cliente : lista)
		{
			resp += i++ + ". " + cliente.toString() + "\n";
		}
		return resp;
	}
	
	//--------------------------------------------------------------------
	// Demo PROMOCION
	//--------------------------------------------------------------------
	public void demoPromocion()
	{
		try
		{
			//Ejecución de la demo y recolección de los resultados
			VOSucursal suc1 = superAndes.adicionarSucursal("50 m^2", "Calle 7 #5-74", "Bogotá", "Candelaria");
			VOProducto pro1 = superAndes.adicionarProducto(500,"papas de pollo", "super ricas", "paqueton de 5 paquetes", "6000","buena", 1300.01,6000.00 ,10, "FFFF", null, "10", "32", "Perecedores");
			VOPromocion cli1 = superAndes.adicionarPromocion(Timestamp.valueOf("2018-10-3 00:00:00"), Timestamp.valueOf("2018-10-4 00:00:00"), "20% de descuento", pro1.getCodBarras(), 20, 0, suc1.getDireccionSucursal(), suc1.getCiudad());

			List<VOPromocion> lista = superAndes.darVOPromociones();
			
			superAndes.finalizarPromocion(cli1.getIdPromocion());

			long promoEliminada = superAndes.eliminarPromocion(cli1.getIdPromocion());
			superAndes.eliminarProducto(pro1.getCodBarras());
			superAndes.eliminarSucursal(suc1.getDireccionSucursal(), suc1.getCiudad());

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Promociones\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "Adicionado la promocion: " + cli1 + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarPromociones (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += promoEliminada + " Promociones eliminados\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);	
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarPromociones(List<VOPromocion> lista) 
	{
		String resp = "Las promociones existentes son:\n";
		int i = 1;
		for (VOPromocion cliente : lista)
		{
			resp += i++ + ". " + cliente.toString() + "\n";
		}
		return resp;
	}
	
	//--------------------------------------------------------------------
	// Demo PROMOCION
	//--------------------------------------------------------------------
	public void demoPedidos()
	{
		try
		{
			//Ejecución de la demo y recolección de los resultados
			VOSucursal suc1 = superAndes.adicionarSucursal("50 m^2", "Calle 7 #5-74", "Bogotá", "Candelaria");
			VOProducto pro1 = superAndes.adicionarProducto(500,"papas de pollo", "super ricas", "paqueton de 5 paquetes", "6000","buena", 1300.01,6000.00 ,10, "FFFF", null, "10", "32","Perecederos");
			VOBodega bog = superAndes.adicionarBodega("Tipo", 200, 300, "Calle 7 #5-74", "Calle 7 #5-74", "Bogotá");
			VOProveedor prov1 = superAndes.adicionarProveedor(123546987, "Alpina");
			VOOrdenPedido cli1 = superAndes.adicionarOrdenPedido(new Timestamp(System.currentTimeMillis()), prov1.getNitProveedor(), suc1.getCiudad(), suc1.getDireccionSucursal(), bog.getDireccionBodega(), pro1.getCodBarras(), 10, 5000);

			List<VOOrdenPedido> lista = superAndes.darVOPedidos();
			
			superAndes.llegadaOrdenPedido(cli1.getIdPedido(), "Bueno");

			long promoEliminada = superAndes.eliminarPedido(cli1.getIdPedido());
			superAndes.eliminarProveedorPorNit(prov1.getNitProveedor());
			superAndes.eliminarBodega(bog.getDireccionBodega(), suc1.getDireccionSucursal(), suc1.getCiudad());
			superAndes.eliminarProducto(pro1.getCodBarras());
			superAndes.eliminarSucursal(suc1.getDireccionSucursal(), suc1.getCiudad());

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Pedidos\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "Adicionado la pedido: " + cli1 + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarPedidos (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += promoEliminada + " Pedidos eliminados\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);	
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarPedidos(List<VOOrdenPedido> lista) 
	{
		String resp = "Los pedidos existentes son:\n";
		int i = 1;
		for (VOOrdenPedido cliente : lista)
		{
			resp += i++ + ". " + cliente.toString() + "\n";
		}
		return resp;
	}
	//----------------------------------------------------------------------------------------------
	// ESTANTE
	//----------------------------------------------------------------------------------------------
	public void demoEstante ( )
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			VOEstante estante1 = superAndes.adicionarEstante("Congelados", 2000, 1, 50, 10, "Cl. 39 #20A-04", "Bogota");

			List <VOEstante> lista = superAndes.darVOEstante();

			long estantesEliminados = superAndes.eliminarEstante(1);

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de estates\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "Adicionado el estante: " + estante1 + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarEstantes (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += estantesEliminados + "  eliminados\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	private String listarEstantes (List<VOEstante> lista) 
	{
		String resp = "Las  existentes son:\n";
		int i = 1;
		for (VOEstante estante : lista)
		{
			resp += i++ + ". " + estante.toString() + "\n";
		}
		return resp;
	}

	//----------------------------------------------------------------------------------------------
	// PRODUCTO
	//----------------------------------------------------------------------------------------------
	public void demoProducto ( )
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			VOProducto producto1 = superAndes.adicionarProducto(500,"papas de pollo", "super ricas", "paqueton de 5 paquetes", "6000","buena", 1300.01,6000.00 ,10, "FFFF", null, "10", "32","Perecederos");

			List <VOProducto> lista = superAndes.darVOProducto();

			long productosEliminados = superAndes.eliminarProducto("FFFF");

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de productos\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "Adicionado el poducto: " + producto1 + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarProductos (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += productosEliminados + " productos eliminados\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	private String listarProductos (List<VOProducto> lista) 
	{
		String resp = "Los productos existentes son:\n";
		int i = 1;
		for (VOProducto producto: lista)
		{
			resp += i++ + ". " + producto.toString() + "\n";
		}
		return resp;
	}
	//----------------------------------------------------------------------------------------------
	// CATEGORIA
	//----------------------------------------------------------------------------------------------
	public void demoCategoria ( )
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			VOCategoria categoria1 = superAndes.adicionarCategoria("Congelados");

			List <VOCategoria> lista = superAndes.darVOCategoria ();

			long categoriasEliminadas = superAndes.eliminarCategoria("Congelados");

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de categorias\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "Adicionado la categoria: " + categoria1 + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarCategorias (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += categoriasEliminadas + " categorias eliminadas\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	private String listarCategorias (List<VOCategoria> lista) 
	{
		String resp = "Las  existentes son:\n";
		int i = 1;
		for (VOCategoria categoria : lista)
		{
			resp += i++ + ". " + categoria.toString() + "\n";
		}
		return resp;
	}
	//----------------------------------------------------------------------------------------------
	// TIPO PRODUCTO
	//----------------------------------------------------------------------------------------------
	public void demoTipoProducto ( )
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			VOTipoProducto tipoProducto1 = superAndes.adicionarTipoProducto("Galletas", "No perecedero");

			List <VOTipoProducto> lista = superAndes.darVOTipoProducto ();

			long productosEliminadas = superAndes.eliminarTipoProducto("Galletas");

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de tipos\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "Adicionado el tipo: " + tipoProducto1 + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarTipoProductos (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += productosEliminadas + " tipos eliminados\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 

		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	private String listarTipoProductos (List<VOTipoProducto> lista) 
	{
		String resp = "Las  existentes son:\n";
		int i = 1;
		for (VOTipoProducto tipoProducto : lista)
		{
			resp += i++ + ". " + tipoProducto.toString() + "\n";
		}
		return resp;
	}
	//----------------------------------------------------------------------------------------------
	// PEDIDO PRODUCTO
	//----------------------------------------------------------------------------------------------
	public void demoPedidoProducto ( )
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			VOPedidoProducto pedidoProducto1 = superAndes.adicionarPedidoProducto("FFFFFFF",2, 1000, 1000);

			List <VOPedidoProducto> lista = superAndes.darVOPedidoProducto ();

			long pedidoProductosEliminadas = superAndes.eliminarPedidoProducto(2);

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de pedidos productos \n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "Adicionado el pedido producto: " + pedidoProducto1 + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarPedidoProductos (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += pedidoProductosEliminadas + " pedidos productos eliminados\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	private String listarPedidoProductos (List<VOPedidoProducto> lista) 
	{
		String resp = "Los pedidos productos existentes son:\n";
		int i = 1;
		for (VOPedidoProducto pedidoProducto : lista)
		{
			resp += i++ + ". " + pedidoProducto.toString() + "\n";
		}
		return resp;
	}
	//----------------------------------------------------------------------------------------------
	// EMPRESA
	//----------------------------------------------------------------------------------------------
	public void demoEmpresa ( )
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			VOEmpresa empresa1 = superAndes.adicionarEmpresa("KR. 100 #10-30", 11111111, "A@hotmail.com");

			List <VOEmpresa> lista = superAndes.darVOEmpresa ();

			long empresasEliminadas = superAndes.eliminarEmpresa("A@hotmail.com");

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de empresas\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "Adicionado la empresa: " + empresa1 + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarEmpresas (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += empresasEliminadas + " empresas eliminadas\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	private String listarEmpresas (List<VOEmpresa> lista) 
	{
		String resp = "Las empresas existentes son:\n";
		int i = 1;
		for (VOEmpresa empresa : lista)
		{
			resp += i++ + ". " + empresa.toString() + "\n";
		}
		return resp;
	}
	//----------------------------------------------------------------------------------------------
	// PERSONA NATURAL
	//----------------------------------------------------------------------------------------------
	public void demoPersonaNatural ( )
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			VOPersonaNatural personaNatural1 = superAndes.adicionarPersonaNatural("123334454", "B@hotmail.com");

			List <VOPersonaNatural> lista = superAndes.darVOPersonaNatural();

			long personaNaturalsEliminadas = superAndes.eliminarPersonaNatural("B@hotmail.com");

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de personas\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "Adicionado la pesona: " + personaNatural1 + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarPersonasNatural (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += personaNaturalsEliminadas + " personas eliminadas\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	private String listarPersonasNatural (List<VOPersonaNatural> lista) 
	{
		String resp = "Las  existentes son:\n";
		int i = 1;
		for (VOPersonaNatural personaNatural : lista)
		{
			resp += i++ + ". " + personaNatural.toString() + "\n";
		}
		return resp;
	}
	//----------------------------------------------------------------------------------------------
	// COMPRADOS
	//----------------------------------------------------------------------------------------------
	public void demoComprados( )
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			VOComprados comprados1 = superAndes.adicionarComprados("FFFF", 10, 5000.0, 2);

			List <VOComprados> lista = superAndes.darVOComprados ();

			long compradosEliminadas = superAndes.eliminarComprados("FFFF", 2);

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de comprados\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "Adicionado compra : " + comprados1 + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarComprados (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += compradosEliminadas + "  eliminados\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	private String listarComprados (List<VOComprados> lista) 
	{
		String resp = "Las  existentes son:\n";
		int i = 1;
		for (VOComprados comprados : lista)
		{
			resp += i++ + ". " + comprados.toString() + "\n";
		}
		return resp;
	}



	//-------------------------------------------------------------------------------------
	//* 			Métodos administrativos
	//--------------------------------------------------------------------------------------
	/**
	 * Muestra el log de Parranderos
	 */
	public void mostrarLogParranderos ()
	{
		mostrarArchivo ("parranderos.log");
	}

	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}

	/**
	 * Limpia el contenido del log de parranderos
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogParranderos ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("parranderos.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de parranderos ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de parranderos
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			//TODO Terminar SuperAndes
			//			long eliminados [] = superAndes.limpiarParranderos();
			//			
			//			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			//			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			//			resultado += eliminados [0] + " Gustan eliminados\n";
			//			resultado += eliminados [1] + " Sirven eliminados\n";
			//			resultado += eliminados [2] + " Visitan eliminados\n";
			//			resultado += eliminados [3] + " Bebidas eliminadas\n";
			//			resultado += eliminados [4] + " Tipos de bebida eliminados\n";
			//			resultado += eliminados [5] + " Bebedores eliminados\n";
			//			resultado += eliminados [6] + " Bares eliminados\n";
			//			resultado += "\nLimpieza terminada";
			//   
			//			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Muestra el modelo conceptual de Parranderos
	 */
	public void mostrarModeloConceptual ()
	{
		//TODO Poner el modelo conceptual 
		mostrarArchivo ("data/Modelo Conceptual Parranderos.pdf");
	}

	/**
	 * Muestra el esquema de la base de datos de Parranderos
	 */
	public void mostrarEsquemaBD ()
	{
		//TODO Esquema BD Parranderos
		mostrarArchivo ("data/Esquema BD Parranderos.pdf");
	}

	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		//TODO Script de BD
		mostrarArchivo ("data/EsquemaParranderos.sql");
	}

	/**
	 * Muestra la arquitectura de referencia para Parranderos
	 */
	public void mostrarArqRef ()
	{
		//TODO Arquitectura Referencia
		mostrarArchivo ("data/ArquitecturaReferencia.pdf");
	}

	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		//TODO Javadoc ¿?
		mostrarArchivo ("doc/index.html");
	}

	/**
	 * Muestra la información acerca del desarrollo de esta apicación
	 */
	public void acercaDe ()
	{
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: SuperAndes Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Leidy Romero\n";
		resultado += " * @author María Ocampo\n";
		resultado += " * Octubre de 2018\n";
		resultado += " * \n";
		resultado += " * Revisado por: Leidy Romero, María Ocampo\n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
	}

	//--------------------------------------------------------------------------------------
	// 			Métodos privados para la presentación de resultados y otras operaciones
	//--------------------------------------------------------------------------------------

	/**
	 * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
	 * @param e - La excepción recibida
	 * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
			//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// -----------------------------------------------------------------
	// Métodos de interacción
	// -----------------------------------------------------------------
	/**
	 * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
	 * Invoca al método correspondiente según el evento recibido
	 * @param pEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
		try 
		{
			Method req = InterfazSuperAndesDemo.class.getMethod ( evento );			
			req.invoke ( this );
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

	// -----------------------------------------------------------------
	// Programa principal
	// -----------------------------------------------------------------
	/**
	 * Este método ejecuta la aplicación, creando una nueva interfaz
	 * @param args Arreglo de argumentos que se recibe por línea de comandos
	 */
	public static void main( String[] args )
	{
		try
		{
			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
			InterfazSuperAndesDemo interfaz = new InterfazSuperAndesDemo( );
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
}
