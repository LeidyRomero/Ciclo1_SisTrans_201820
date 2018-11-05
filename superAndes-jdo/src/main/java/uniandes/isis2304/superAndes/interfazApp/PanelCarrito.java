package uniandes.isis2304.superAndes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.isis2304.superAndes.negocio.ActualizacionListasListener;
import uniandes.isis2304.superAndes.negocio.CantidadEnEstantes;
import uniandes.isis2304.superAndes.negocio.ProductosCarrito;

/**
 * Clase de interfaz para mostrar los resultados de la ejecución de las 
 * operaciones realizadas por el usuario
 */
@SuppressWarnings("serial")
public class PanelCarrito extends JPanel implements ActionListener, ActualizacionListasListener
{
	private JList<String> listaCantidadEstante;

	private JList<String> listaProductosCarrito;

	private JButton btnAgregarAlCarrito;

	private JButton btnDevolverDelCarrito;

	private JButton btnPagarCarrito;

	private InterfazSuperAndesApp superAndesApp;

	private String[] dataCantidad;

	private String[] dataCarrito;

	public PanelCarrito(InterfazSuperAndesApp interfaz) 
	{
		setLayout(new BorderLayout());
		setSize(new Dimension(300,200));

		superAndesApp = interfaz;

		JPanel panelNombres= new JPanel(); 
		panelNombres.setLayout(new GridLayout());

		panelNombres.add(new JLabel("Productos en estante"));
		panelNombres.add(new JLabel("Productos en carrito"));

		add(panelNombres, BorderLayout.NORTH);

		JScrollPane scroll = new JScrollPane( );
		scroll.setPreferredSize(new Dimension(250, 150));
		listaCantidadEstante = new JList<String>( );
		scroll.getViewport( ).add( listaCantidadEstante );
		add(scroll, BorderLayout.WEST); 

		JScrollPane scroll2 = new JScrollPane( );
		scroll2.setPreferredSize(new Dimension(250, 150));
		listaProductosCarrito = new JList<String>( );
		scroll2.getViewport( ).add( listaProductosCarrito );
		add(scroll2, BorderLayout.EAST);

		JPanel panelOpciones= new JPanel(); 
		panelOpciones.setLayout(new GridLayout());

		btnAgregarAlCarrito = new JButton("Agregar al carrito");
		btnAgregarAlCarrito.setActionCommand("AGREGAR");
		btnAgregarAlCarrito.addActionListener(this);
		panelOpciones.add(btnAgregarAlCarrito);

		btnDevolverDelCarrito = new JButton("Devolver del carrito");
		btnDevolverDelCarrito.setActionCommand("DEVOLVER");
		btnDevolverDelCarrito.addActionListener(this);
		panelOpciones.add(btnDevolverDelCarrito);

		btnPagarCarrito = new JButton("Pagar compra");
		btnPagarCarrito.setActionCommand("PAGAR");
		btnPagarCarrito.addActionListener(this);
		panelOpciones.add(btnPagarCarrito);

		add(panelOpciones, BorderLayout.SOUTH);
		setBorder( new TitledBorder( "Carrito" ) );
	}

	public JList<String> darListaCantidadEstante()
	{
		return listaCantidadEstante;
	}

	public JList<String> darListaProductosCarrito()
	{
		return listaProductosCarrito;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();

		if(com.equals("AGREGAR"))
		{
			JTextField txtCantidad = new JTextField(15);
			JComboBox opcionesUnidad = new JComboBox<>(dataCantidad);
			JPanel aux = new JPanel();
			aux.add(new JLabel("Producto:"));
			aux.add(opcionesUnidad);
			aux.add(Box.createHorizontalStrut(15)); // a spacer
			aux.add(new JLabel("Cantidad:"));
			aux.add(txtCantidad);
			int result = JOptionPane.showConfirmDialog(null, aux,"Agregar producto al carrito", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) 
			{
				if(!opcionesUnidad.getSelectedItem().toString().equals("") && !txtCantidad.getText().equals(""))
				{
					String[] data = opcionesUnidad.getSelectedItem().toString().split(" - ");
					int cantidadE = Integer.parseInt(data[2].trim().substring(10, data[2].length()));
					int cantidadC = Integer.parseInt(txtCantidad.getText());
					if(cantidadC <= cantidadE)
					{
						String codBarras = data[1].trim().substring(11, data[1].length());
						superAndesApp.agregarProductoAlCarrito(codBarras,cantidadC);
					}
					else
					{
						JOptionPane.showMessageDialog(this, "La cantidad no puede ser mayor a la que está en estante");
					}
				}

				else
					JOptionPane.showMessageDialog(this, "Ingrese valores válidos");
			}
		}

		else if(com.equals("DEVOLVER"))
		{
			JTextField txtCodBarras = new JTextField(15);
			JTextField txtCantidad = new JTextField(15);

			JPanel aux = new JPanel();
			aux.add(new JLabel("Código de barras:"));
			aux.add(txtCodBarras);
			aux.add(Box.createHorizontalStrut(15)); // a spacer
			aux.add(new JLabel("Cantidad:"));
			aux.add(txtCantidad);

			int result = JOptionPane.showConfirmDialog(null, aux,"Devolver producto del carrito", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) 
			{
				if(!txtCodBarras.getText().equals("") && !txtCantidad.getText().equals(""))
					superAndesApp.devolverProductoDelCarrito(txtCodBarras.getText(), Integer.parseInt(txtCantidad.getText()));

				else
					JOptionPane.showMessageDialog(this, "Ingrese valores válidos");
			}
		}

		else if(com.equals("PAGAR"))
		{
			superAndesApp.registrarVenta();
		}
	}

	@Override
	public void actualizarLista(List<Object[]>[] lista) {
		List<String> data = new LinkedList<String>();

		if(lista[0] == null)
		{
			JOptionPane.showMessageDialog(this, "Se ha abandonado el carrito", "Carrito", JOptionPane.INFORMATION_MESSAGE);
			superAndesApp.abandonarCarrito();
		}

		else if(lista[0].size() == 0)
		{
			JOptionPane.showMessageDialog(this, "No hay productos en la sucursal", "Carrito", JOptionPane.INFORMATION_MESSAGE);	
		}

		else
		{
			for(Object[] actual : lista[0])
			{
				CantidadEnEstantes ce = (CantidadEnEstantes) actual[0];
				String cadena = "Nombre: "+actual[1]+" - CodBarras: "+ce.getCodigoBarras() + " - Cantidad: "+ce.getCantidadActual();
				data.add(cadena);
			}
			dataCantidad = new String[data.size()];
			listaCantidadEstante.setListData(data.toArray(dataCantidad));
			listaCantidadEstante.repaint();
			listaCantidadEstante.updateUI();

			List<String> data3 = new LinkedList<String>();
			for(Object[] actual : lista[1])
			{
				ProductosCarrito prod = (ProductosCarrito) actual[0];
				String cadena = "Cantidad: "+prod.getCantidad()+ "- CodBarras: " + prod.getCodBarras();
				data3.add(cadena);
			}
			dataCarrito = new String[data3.size()];
			listaProductosCarrito.setListData(data3.toArray(dataCarrito));
			listaProductosCarrito.repaint();
			listaProductosCarrito.updateUI();	
		}
	}

}
