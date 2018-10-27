package uniandes.isis2304.superAndes.interfazApp;

import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import uniandes.isis2304.superAndes.negocio.Producto;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Clase de interfaz para mostrar los resultados de la ejecución de las 
 * operaciones realizadas por el usuario
 */
@SuppressWarnings("serial")
public class PanelDatos extends JPanel{
	// -----------------------------------------------------------------
	// Atributos de interfaz
	// -----------------------------------------------------------------
	/**
	 * Área de texto con barras de deslizamiento
	 * Area donde van a aparecer los resultados
	 */
	private JTextArea textArea;
	/**
	 * Lista de todos los productos(nombres)
	 */
	private JList listaProductos;
	/**
	 * Scroll para la lista de todos los productos
	 */
	private JScrollPane scroll;
	
	/**
	 * Lista de productos en el carrito
	 */
	private JList listaProductosCarrito;
	/**
	 * Scroll para la lista de productos en el carrito
	 */
	private JScrollPane scrollCarrito;
	
	/**
	 * Lista de productos
	 */
	private ArrayList<Producto> productos;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Construye el panel
	 * 
	 */
	public PanelDatos ()
	{
		setBorder (new TitledBorder ("Panel de información"));
		setLayout( new BorderLayout() );

		textArea = new JTextArea("Aquí sale el resultado de las operaciones solicitadas");
		textArea.setEditable(false);
		add (new JScrollPane(textArea), BorderLayout.CENTER);
		
		productos = null;
	}
	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Actualiza el panel con la información recibida por parámetro.
	 * @param texto El texto con el que actualiza el área
	 */
	public void actualizarInterfaz (String texto)
	{
		textArea.setText(texto);
	}
	/**
	 * 
	 * @param productos
	 */
	public void actualizar(ArrayList<Producto> pProductos)
	{
		this.productos = pProductos;

		//TODO refrescar
		this.repaint();
		listaProductos = new JList( );
		scroll = new JScrollPane( listaProductos);

		listaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		listaProductos.setListData( productos.toArray( ));
		listaProductos.setSelectedIndex( 0 );
		 
		add( scroll, BorderLayout.WEST );
		
		textArea = new JTextArea("Aquí sale el resultado de las operaciones solicitadas");
		textArea.setEditable(false);
		add (new JScrollPane(textArea), BorderLayout.CENTER);
		
		listaProductosCarrito.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		scrollCarrito.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		scrollCarrito.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		listaProductosCarrito.setSelectedIndex( 0 );
		 
		add( scroll, BorderLayout.EAST );
		
	}
	public void agregarProducto(int pCantidad,Producto pProducto)
	{
		//disminuir la cantidad en la lista de productos, y agregar a la lista del carrito
		listaProductosCarrito.add();
	}
	public void quitarProducto(int pCantidad, Producto pProducto)
	{
		//quita el producto del carrito y aumenta la cantidad de la otra lista (BD)
		listaProductosCarrito.remove();
	}
	public void devolverAbandonarCarrito()
	{
		//vaciar la lista
		//actualizar la interfaz para que las listas no se vean
		//listaProductosCarrito;
	}
}
