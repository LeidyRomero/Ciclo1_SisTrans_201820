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
	 * Logger para escribir la traza de la ejecuci�n
	 */
	private static Logger log = Logger.getLogger(InterfazSuperAndesDemo.class.getName());
	/**
	 * Ruta al archivo de configuraci�n de la interfaz
	 */
	private static final String CONFIGURACION_INTERFAZ = "./src/main/resources/config/interfaceConfigDemo.json"; 
	/**
	 * Ruta al archivo de configuraci�n de los nombres de tablas de la base de datos
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
	 * Asociaci�n a la clase principal del negocio.
	 */
	private SuperAndes superAndes;
	// -----------------------------------------------------------------
	// Atributos de la interfaz
	// -----------------------------------------------------------------
	/**
	 * Objeto JSON con la configuraci�n de interfaz de la app.
	 */
	private JsonObject guiConfig;
	/**
	 * Panel de despliegue de interacci�n para los requerimientos
	 */
	private PanelDatos panelDatos;
	/**
	 * Men� de la aplicaci�n
	 */
	private JMenuBar menuSA;
	/**
	 * Construye la ventana principal de la aplicaci�n. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazSuperAndesDemo()
	{
		// Carga la configuraci�n de la interfaz desde un archivo JSON
		guiConfig = openConfig ("Interfaz", CONFIGURACION_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gr�fica
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
	 * Lee datos de configuraci�n para la aplicacion, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuraci�n deseada
	 * @param archConfig - Archivo Json que contiene la configuraci�n
	 * @return Un objeto JSON con la configuraci�n del tipo especificado
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
			log.info ("Se encontr� un archivo de configuraci�n v�lido: " + tipo);
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontr� un archivo de configuraci�n v�lido "+ e);			
			JOptionPane.showMessageDialog(null, "No se encontr� un archivo de configuraci�n de interfaz v�lido: " + tipo, "Parranderos Demo", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	/**
	 * M�todo para configurar el frame principal de la aplicaci�n
	 */
	private void configurarFrame(  )
	{
		int alto = 0;
		int ancho = 0;
		String titulo = "";	

		if ( guiConfig == null )
		{
			log.info ( "Se aplica configuraci�n por defecto" );			
			titulo = "SuperAndes Demo Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			log.info ( "Se aplica configuraci�n indicada en el archivo de configuraci�n" );
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
	 * M�todo para crear el men� de la aplicaci�n con base em el objeto JSON le�do
	 * Genera una barra de men� y los men�s con sus respectivas opciones
	 * @param jsonMenu - Arreglo Json con los men�s deseados
	 */
	private void crearMenu(JsonArray jsonMenu )
	{    	
		// Creaci�n de la barra de men�s
		menuSA = new JMenuBar();       
		for (JsonElement men : jsonMenu)
		{
			// Creaci�n de cada uno de los men�s
			JsonObject jom = men.getAsJsonObject(); 

			String menuTitle = jom.get("menuTitle").getAsString();        	
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu( menuTitle);
			for (JsonElement op : opciones)
			{       	
				// Creaci�n de cada una de las opciones del men�
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
			// Ejecuci�n de la demo y recolecci�n de los resultados
			// ATENCI�N: En una aplicaci�n real, los datos JAM�S est�n en el c�digo
			VOBodega bodega1 = superAndes.adicionarBodega("Congelados",1000, 50, "KR 163B #57-29", "Cl. 39 #20A-04", "Bogota");

			List <VOBodega> lista = superAndes.darVOBodegas ();

			long bodegasEliminadas = superAndes.eliminarBodega("KR 163B #57-29", "Cl. 39 #20A-04", "Bogota");

			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
			String resultado = "Demo de creaci�n y listado de Bodegas\n\n";
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
			// Ejecuci�n de la demo y recolecci�n de los resultados
			// ATENCI�N: En una aplicaci�n real, los datos JAM�S est�n en el c�digo
			VOCantidadEnBodega cantidadEnBodega1 = superAndes.adicionarCantidadEnBodega("KR 163B #57-29", "Cl. 39 #20A-04", "Bogota", 20, 10, "F000AC6");

			List <VOCantidadEnBodega> lista = superAndes.darVOCantidadEnBodega ();

			long cantidadesEnBodegaEliminadas = superAndes.eliminarCantidadEnBodega("F000AC6","Cl. 39 #20A-04", "KR 163B #57-29", "Bogota");

			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
			String resultado = "Demo de creaci�n y listado del inventario en bodega\n\n";
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


	//BORRADOR
	//	public void demoX ( )
	//	{
	//		try 
	//		{
	//			// Ejecuci�n de la demo y recolecci�n de los resultados
	//			// ATENCI�N: En una aplicaci�n real, los datos JAM�S est�n en el c�digo
	//			VOX x1 = superAndes.adicionarX();
	//
	//			List <VOX> lista = superAndes.darVOX ();
	//
	//			long xsEliminadas = superAndes.eliminarX();
	//
	//			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
	//			String resultado = "Demo de creaci�n y listado de \n\n";
	//			resultado += "\n\n************ Generando datos de prueba ************ \n";
	//			resultado += "Adicionado la : " + x1 + "\n";
	//			resultado += "\n\n************ Ejecutando la demo ************ \n";
	//			resultado += "\n" + listarYs (lista);
	//			resultado += "\n\n************ Limpiando la base de datos ************ \n";
	//			resultado += xsEliminadas + "  eliminados\n";
	//			resultado += "\n Demo terminada";
	//
	//			panelDatos.actualizarInterfaz(resultado);
	//		} 
	//		catch (Exception e) 
	//		{
	//			String resultado = generarMensajeError(e);
	//			panelDatos.actualizarInterfaz(resultado);
	//		}
	//	}
	//	private String listarX (List<VOX> lista) 
	//	{
	//		String resp = "Las  existentes son:\n";
	//		int i = 1;
	//		for (VOX x : lista)
	//		{
	//			resp += i++ + ". " + x.toString() + "\n";
	//		}
	//		return resp;
	//	}

	//--------------------------------------------------------------------
	// Demo PROVEEDOR
	//--------------------------------------------------------------------
	public void demoProveedores()
	{
		try
		{
			//Ejecuci�n de la demo y recolecci�n de los resultados
			VOProveedor pro1 = superAndes.adicionarProveedor(123546987, "Alpina");

			List<VOProveedor> lista = superAndes.darVOProveedores();

			long proveedoresEliminados = superAndes.eliminarProveedorPorNit(123546987);

			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
			String resultado = "Demo de creaci�n y listado de Proveedores\n\n";
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
			//Ejecuci�n de la demo y recolecci�n de los resultados
			VOCliente cli1 = superAndes.adicionarCliente("majocava0417@hotmail.com", "Mar�a Ocampo");

			List<VOCliente> lista = superAndes.darVOClientes();

			long clientesEliminados = superAndes.eliminarClientePorCorreo("majocava0417@hotmail.com");

			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
			String resultado = "Demo de creaci�n y listado de Clientes\n\n";
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
			//Ejecuci�n de la demo y recolecci�n de los resultados
			VOSucursal cli1 = superAndes.adicionarSucursal("50 m^2", "Calle 7 #5-74", "Bogot�", "Candelaria");

			List<VOSucursal> lista = superAndes.darVOSucursales();

			long clientesEliminados = superAndes.eliminarSucursal("Calle 7 #5-74", "Bogot�");

			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
			String resultado = "Demo de creaci�n y listado de Sucursales\n\n";
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
			//Ejecuci�n de la demo y recolecci�n de los resultados
			VOSucursal suc1 = superAndes.adicionarSucursal("50 m^2", "Calle 7 #5-74", "Bogot�", "Candelaria");
			VOProducto pro1 = superAndes.adicionarProducto("papas de pollo", "super ricas", "paqueton de 5 paquetes", "6000","buena", 1300.01,6000.00 ,10, "FFFF", null, "10", "32");
			VOPromocion cli1 = superAndes.adicionarPromocion(Timestamp.valueOf("2018-10-3 00:00:00"), Timestamp.valueOf("2018-10-4 00:00:00"), "20% de descuento", pro1.getCodBarras(), 20, 0, suc1.getDireccionSucursal(), suc1.getCiudad());

			List<VOPromocion> lista = superAndes.darVOPromociones();
			
			superAndes.finalizarPromocion(cli1.getIdPromocion());

			long promoEliminada = superAndes.eliminarPromocion(cli1.getIdPromocion());
			superAndes.eliminarProducto(pro1.getCodBarras());
			superAndes.eliminarSucursal(suc1.getDireccionSucursal(), suc1.getCiudad());

			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
			String resultado = "Demo de creaci�n y listado de Promociones\n\n";
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
			//Ejecuci�n de la demo y recolecci�n de los resultados
			VOSucursal suc1 = superAndes.adicionarSucursal("50 m^2", "Calle 7 #5-74", "Bogot�", "Candelaria");
			VOProducto pro1 = superAndes.adicionarProducto("papas de pollo", "super ricas", "paqueton de 5 paquetes", "6000","buena", 1300.01,6000.00 ,10, "FFFF", null, "10", "32");
			VOBodega bog = superAndes.adicionarBodega("Tipo", 200, 300, "Calle 7 #5-74", "Calle 7 #5-74", "Bogot�");
			VOProveedor prov1 = superAndes.adicionarProveedor(123546987, "Alpina");
			VOOrdenPedido cli1 = superAndes.adicionarOrdenPedido(new Timestamp(System.currentTimeMillis()), prov1.getNitProveedor(), suc1.getCiudad(), suc1.getDireccionSucursal(), bog.getDireccionBodega(), pro1.getCodBarras(), 10, 5000);

			List<VOOrdenPedido> lista = superAndes.darVOPedidos();
			
			superAndes.llegadaOrdenPedido(cli1.getIdPedido(), "Bueno");

			long promoEliminada = superAndes.eliminarPedido(cli1.getIdPedido());
			superAndes.eliminarProveedorPorNit(prov1.getNitProveedor());
			superAndes.eliminarBodega(bog.getDireccionBodega(), suc1.getDireccionSucursal(), suc1.getCiudad());
			superAndes.eliminarProducto(pro1.getCodBarras());
			superAndes.eliminarSucursal(suc1.getDireccionSucursal(), suc1.getCiudad());

			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
			String resultado = "Demo de creaci�n y listado de Pedidos\n\n";
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
			// Ejecuci�n de la demo y recolecci�n de los resultados
			// ATENCI�N: En una aplicaci�n real, los datos JAM�S est�n en el c�digo
			VOEstante estante1 = superAndes.adicionarEstante("Congelados", 2000, 1, 50, 10, "Cl. 39 #20A-04", "Bogota");

			List <VOEstante> lista = superAndes.darVOEstante();

			long estantesEliminados = superAndes.eliminarEstante(1);

			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
			String resultado = "Demo de creaci�n y listado de estates\n\n";
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
			// Ejecuci�n de la demo y recolecci�n de los resultados
			// ATENCI�N: En una aplicaci�n real, los datos JAM�S est�n en el c�digo
			VOProducto producto1 = superAndes.adicionarProducto("papas de pollo", "super ricas", "paqueton de 5 paquetes", "6000","buena", 1300.01,6000.00 ,10, "FFFF", null, "10", "32");

			List <VOProducto> lista = superAndes.darVOProducto();

			long productosEliminados = superAndes.eliminarProducto("FFFF");

			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
			String resultado = "Demo de creaci�n y listado de productos\n\n";
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
			// Ejecuci�n de la demo y recolecci�n de los resultados
			// ATENCI�N: En una aplicaci�n real, los datos JAM�S est�n en el c�digo
			VOCategoria categoria1 = superAndes.adicionarCategoria("Congelados");

			List <VOCategoria> lista = superAndes.darVOCategoria ();

			long categoriasEliminadas = superAndes.eliminarCategoria("Congelados");

			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
			String resultado = "Demo de creaci�n y listado de categorias\n\n";
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
			// Ejecuci�n de la demo y recolecci�n de los resultados
			// ATENCI�N: En una aplicaci�n real, los datos JAM�S est�n en el c�digo
			VOTipoProducto tipoProducto1 = superAndes.adicionarTipoProducto("Galletas", "No perecedero");

			List <VOTipoProducto> lista = superAndes.darVOTipoProducto ();

			long productosEliminadas = superAndes.eliminarTipoProducto("Galletas");

			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
			String resultado = "Demo de creaci�n y listado de tipos\n\n";
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
	// CATEGORIA PRODUCTOS
	//----------------------------------------------------------------------------------------------
	public void demoProductoCategoria ( )
	{
		try 
		{
			// Ejecuci�n de la demo y recolecci�n de los resultados
			// ATENCI�N: En una aplicaci�n real, los datos JAM�S est�n en el c�digo
			VOProductoCategoria categoriaProductos1 = superAndes.adicionarProductoCategoria("Congelados", "FFFFFFF");

			List <VOProductoCategoria> lista = superAndes.darVOProductoCategoria ();

			long categoriaProductosEliminados = superAndes.eliminarProductoCategoria("FFFFFFF", "Congelados");

			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
			String resultado = "Demo de creaci�n y listado de categorias productos\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "Adicionado categoria producto: " + categoriaProductos1 + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarProductoCategorias (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += categoriaProductosEliminados + " categorias productos eliminados\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	private String listarProductoCategorias (List<VOProductoCategoria> lista) 
	{
		String resp = "Los productos categoria existentes son:\n";
		int i = 1;
		for (VOProductoCategoria productoCategoria : lista)
		{
			resp += i++ + ". " + productoCategoria.toString() + "\n";
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
			// Ejecuci�n de la demo y recolecci�n de los resultados
			// ATENCI�N: En una aplicaci�n real, los datos JAM�S est�n en el c�digo
			VOPedidoProducto pedidoProducto1 = superAndes.adicionarPedidoProducto("FFFFFFF",2, 1000, 1000);

			List <VOPedidoProducto> lista = superAndes.darVOPedidoProducto ();

			long pedidoProductosEliminadas = superAndes.eliminarPedidoProducto(2);

			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
			String resultado = "Demo de creaci�n y listado de pedidos productos \n\n";
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
			// Ejecuci�n de la demo y recolecci�n de los resultados
			// ATENCI�N: En una aplicaci�n real, los datos JAM�S est�n en el c�digo
			VOEmpresa empresa1 = superAndes.adicionarEmpresa("KR. 100 #10-30", 11111111, "A@hotmail.com");

			List <VOEmpresa> lista = superAndes.darVOEmpresa ();

			long empresasEliminadas = superAndes.eliminarEmpresa("A@hotmail.com");

			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
			String resultado = "Demo de creaci�n y listado de empresas\n\n";
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
			// Ejecuci�n de la demo y recolecci�n de los resultados
			// ATENCI�N: En una aplicaci�n real, los datos JAM�S est�n en el c�digo
			VOPersonaNatural personaNatural1 = superAndes.adicionarPersonaNatural("123334454", "B@hotmail.com");

			List <VOPersonaNatural> lista = superAndes.darVOPersonaNatural();

			long personaNaturalsEliminadas = superAndes.eliminarPersonaNatural("B@hotmail.com");

			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
			String resultado = "Demo de creaci�n y listado de personas\n\n";
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
			// Ejecuci�n de la demo y recolecci�n de los resultados
			// ATENCI�N: En una aplicaci�n real, los datos JAM�S est�n en el c�digo
			VOComprados comprados1 = superAndes.adicionarComprados("FFFF", 10, 5000.0, 2);

			List <VOComprados> lista = superAndes.darVOComprados ();

			long compradosEliminadas = superAndes.eliminarComprados("FFFF", 2);

			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
			String resultado = "Demo de creaci�n y listado de comprados\n\n";
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
	//* 			M�todos administrativos
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
	 * Muestra en el panel de datos la traza de la ejecuci�n
	 */
	public void limpiarLogParranderos ()
	{
		// Ejecuci�n de la operaci�n y recolecci�n de los resultados
		boolean resp = limpiarArchivo ("parranderos.log");

		// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
		String resultado = "\n\n************ Limpiando el log de parranderos ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecuci�n
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecuci�n de la operaci�n y recolecci�n de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de parranderos
	 * Muestra en el panel de datos el n�mero de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
			// Ejecuci�n de la demo y recolecci�n de los resultados
			//TODO Terminar SuperAndes
			//			long eliminados [] = superAndes.limpiarParranderos();
			//			
			//			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
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
	 * Muestra el script de creaci�n de la base de datos
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
	 * Muestra la documentaci�n Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		//TODO Javadoc �?
		mostrarArchivo ("doc/index.html");
	}

	/**
	 * Muestra la informaci�n acerca del desarrollo de esta apicaci�n
	 */
	public void acercaDe ()
	{
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogot�	- Colombia)\n";
		resultado += " * Departamento	de	Ingenier�a	de	Sistemas	y	Computaci�n\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versi�n 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: SuperAndes Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Leidy Romero\n";
		resultado += " * @author Mar�a Ocampo\n";
		resultado += " * Octubre de 2018\n";
		resultado += " * \n";
		resultado += " * Revisado por: Leidy Romero, Mar�a Ocampo\n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
	}

	//--------------------------------------------------------------------------------------
	// 			M�todos privados para la presentaci�n de resultados y otras operaciones
	//--------------------------------------------------------------------------------------

	/**
	 * Genera una cadena de caracteres con la descripci�n de la excepcion e, haciendo �nfasis en las excepcionsde JDO
	 * @param e - La excepci�n recibida
	 * @return La descripci�n de la excepci�n, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
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
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicaci�n
	 * @param e - La excepci�n generada
	 * @return La cadena con la informaci�n de la excepci�n y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecuci�n\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para m�s detalles";
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
	 * Abre el archivo dado como par�metro con la aplicaci�n por defecto del sistema
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
	// M�todos de interacci�n
	// -----------------------------------------------------------------
	/**
	 * M�todo para la ejecuci�n de los eventos que enlazan el men� con los m�todos de negocio
	 * Invoca al m�todo correspondiente seg�n el evento recibido
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
	 * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
	 * @param args Arreglo de argumentos que se recibe por l�nea de comandos
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
