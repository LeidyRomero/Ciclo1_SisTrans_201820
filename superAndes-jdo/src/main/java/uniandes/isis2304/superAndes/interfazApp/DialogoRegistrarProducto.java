package uniandes.isis2304.superAndes.interfazApp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DialogoRegistrarProducto extends JDialog implements ActionListener{
	/**
	 * Comando para agregar un producto
	 */
	private final static String REGISTRAR = "REGISTRAR";
	private final static String REGISTRAR_CATEGORIA = "REGISTRAR_CATEGORIA";
	private final static String REGISTRAR_PRODUCTO_CATEGORIA = "REGISTRAR_PRODUCTO_CATEGORIA";
	private final static String REGISTRAR_TIPO = "REGISTRAR_TIPO";
	/**
	 * Comando para cancelar la operación
	 */
	private final static String CANCELAR = "CANCELAR";
	private InterfazSuperAndesApp principal;
	/**
	 * Etiqueta nombre
	 */
	private JLabel lbNombre;

	/**
	 * Etiqueta marca
	 */
	private JLabel lbMarca;

	/**
	 * Etiqueta precio unitario
	 */
	private JLabel lbPrecioUnitario;
	/**
	 * Etiqueta presentacion
	 */
	private JLabel lbPresentacion;

	/**
	 * Etiqueta precio unidad medida
	 */
	private JLabel lbPrecioUnidadMedida;
	private JLabel lbCategoria;
	/**
	 * Campo de texto para mostrar el nombre
	 */
	private JTextField txtNombre;

	/**
	 * Campo de texto para mostrar la presentacion
	 */
	private JTextField txtPresentacion;
	/**
	 * Campo de texto para mostrar la marca
	 */
	private JTextField txtMarca;
	private JTextField txtCategoria;

	/**
	 * Campo de texto para mostrar el precio unitario
	 */
	private JTextField txtPrecioUnitario;

	/**
	 * Campo de texto para mostrar el precio unidad medida
	 */
	private JTextField txtPrecioUnidadMedida;

	/**
	 * Botón registrar
	 */
	private JButton btnRegistrar;
	private JButton btnRegistrarCategoria;
	private JButton btnRegistrarProductoCategoria;
	private JButton btnRegistrarTipo;
	/**
	 * Botón cancelar
	 */
	private JButton btnCancelar;

	/**
	 * Etiqueta precio unidad medida
	 */
	private JLabel lbCantidadPresentacion;

	/**
	 * Campo de texto para mostrar el nombre
	 */
	private JTextField txtCantidadPresentacion;
	/**
	 * Etiqueta precio unidad medida
	 */
	private JLabel lbUnidadMedida;

	/**
	 * Campo de texto para mostrar el nombre
	 */
	private JTextField txtUnidadMedida;

	/**
	 * Etiqueta precio unidad medida
	 */
	private JLabel lbEspecificacion;

	/**
	 * Campo de texto para mostrar el nombre
	 */
	private JTextField txtCodigoBarras;
	/**
	 * Etiqueta precio unidad medida
	 */
	private JLabel lbCodigoBarras;

	/**
	 * Campo de texto para mostrar el nombre
	 */
	private JTextField txtEspecificacion;
	/**
	 * Etiqueta precio unidad medida
	 */
	private JLabel lbCalidad;

	/**
	 * Campo de texto para mostrar el nombre
	 */
	private JTextField txtCalidad;
	/**
	 * Etiqueta precio unidad medida
	 */
	private JLabel lbFechaVencimiento;

	/**
	 * Campo de texto para mostrar el nombre
	 */
	private JTextField txtFechaVencimiento;

	// --------------------------------------------------------
	// Constructores
	// --------------------------------------------------------

	/**
	 * Construye un nuevo dialogo para agregar un crédito
	 * @param principalP es la referencia al panel padre del diálogo
	 */
	public DialogoRegistrarProducto( InterfazSuperAndesApp principalP )
	{
		principal = principalP;
		setLayout( new GridLayout( 15, 2 ) );
		this.setSize( 800, 450 );
		setTitle( "Agregar producto" );

		lbNombre = new JLabel( "Nombre:" );
		txtNombre = new JTextField( );

		add( lbNombre );
		add( txtNombre );

		lbMarca = new JLabel( "Marca:" );
		txtMarca = new JTextField( );

		add( lbMarca );
		add( txtMarca );

		lbPrecioUnitario = new JLabel( "Precio unitario:" );
		txtPrecioUnitario = new JTextField( );

		add( lbPrecioUnitario );
		add( txtPrecioUnitario );

		lbPresentacion = new JLabel( "Presentacion:" );
		txtPresentacion = new JTextField( );

		add( lbPresentacion );
		add( txtPresentacion );

		lbPrecioUnidadMedida = new JLabel( "Precio por unidad de medida:" );
		txtPrecioUnidadMedida = new JTextField( );

		add( lbPrecioUnidadMedida );
		add( txtPrecioUnidadMedida );

		lbCantidadPresentacion = new JLabel( "Cantidad por presentacion:" );
		txtCantidadPresentacion = new JTextField( );

		add( lbCantidadPresentacion );
		add( txtCantidadPresentacion );

		lbUnidadMedida = new JLabel( "Unidad de medida:" );
		txtUnidadMedida = new JTextField( );

		add( lbUnidadMedida );
		add( txtUnidadMedida );

		lbEspecificacion = new JLabel( "Especificacion de empacado:" );
		txtEspecificacion = new JTextField( );

		add( lbEspecificacion );
		add( txtEspecificacion );

		lbCodigoBarras = new JLabel( "Codigo de barras:" );
		txtCodigoBarras = new JTextField( );

		add( lbCodigoBarras );
		add( txtCodigoBarras );

		lbCalidad = new JLabel( "Calidad:" );
		txtCalidad = new JTextField( );

		add( lbCalidad );
		add( txtCalidad );

		lbFechaVencimiento = new JLabel( "Fecha de vencimiento:" );
		txtFechaVencimiento = new JTextField( );

		add( lbFechaVencimiento );
		add( txtFechaVencimiento );
		
		lbCategoria = new JLabel( "Categoria:" );
		txtCategoria = new JTextField( );

		add( lbCategoria );
		add( txtCategoria );
		
		btnRegistrar = new JButton( "Registrar" );
		btnRegistrar.setActionCommand( REGISTRAR );
		btnRegistrar.addActionListener( this );

		btnRegistrarCategoria = new JButton( "Registrar categoria" );
		btnRegistrarCategoria.setActionCommand( REGISTRAR_CATEGORIA );
		btnRegistrarCategoria.addActionListener( this );
		
		btnRegistrarTipo = new JButton( "Registrar tipo" );
		btnRegistrarTipo.setActionCommand( REGISTRAR_TIPO );
		btnRegistrarTipo.addActionListener( this );
		
		btnRegistrarProductoCategoria = new JButton( "Registrar producto a una categoria" );
		btnRegistrarProductoCategoria.setActionCommand( REGISTRAR_PRODUCTO_CATEGORIA );
		btnRegistrarProductoCategoria.addActionListener( this );
		
		btnCancelar = new JButton( "Cancelar" );
		btnCancelar.setActionCommand( CANCELAR );
		btnCancelar.addActionListener( this );

		add( btnRegistrar );
		add( btnRegistrarCategoria );
		add( btnRegistrarTipo );
		add( btnRegistrarProductoCategoria );
		add( btnCancelar );

		setModal( true );
		setLocationRelativeTo( null );
	}
	
	public void actionPerformed(ActionEvent e) {
		if( e.getActionCommand( ).equals( REGISTRAR ) )
		{
			try
			{
				double precioU = Double.parseDouble(txtPrecioUnitario.getText());
				double precioUniMed = Double.parseDouble(txtPrecioUnidadMedida.getText());
				int codigo = Integer.parseInt(txtCodigoBarras.getText());
				int cantidadPrese = Integer.parseInt(txtCodigoBarras.getText());
				principal.adicionarProducto2(txtNombre.getText(), txtMarca.getText(),precioU,txtPresentacion.getText(),precioUniMed,cantidadPrese,txtUnidadMedida.getText(),txtEspecificacion.getText(),codigo,txtCalidad.getText(),txtFechaVencimiento.getText());
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog (this, "Valores ingresados no validos", "Agregar producto: no exitoso", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else if(e.getActionCommand( ).equals( REGISTRAR_CATEGORIA ))
		{
			principal.adicionarCategoria(JOptionPane.showInputDialog(this, "Nombre de la categoria: ", "Agregar categoria", JOptionPane.INFORMATION_MESSAGE));
		}
		else if(e.getActionCommand( ).equals( REGISTRAR_TIPO ))
		{
			DialogoRegistrarTipo registrar = new DialogoRegistrarTipo(principal);
			registrar.setVisible( true );
		}
		else if(e.getActionCommand( ).equals( REGISTRAR_PRODUCTO_CATEGORIA ))
		{
			DialogoRegistrarProductoCategoria registrar = new DialogoRegistrarProductoCategoria(principal);
			registrar.setVisible( true );
		}
		else
		{
			this.dispose();
		}
	}

}
