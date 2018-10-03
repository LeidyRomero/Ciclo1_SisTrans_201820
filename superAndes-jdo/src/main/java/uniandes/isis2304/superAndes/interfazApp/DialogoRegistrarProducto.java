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
	/**
	 * Comando para cancelar la operaci�n
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
	 * Bot�n registrar
	 */
	private JButton btnRegistrar;
	/**
	 * Bot�n cancelar
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
	 * Construye un nuevo dialogo para agregar un cr�dito
	 * @param principalP es la referencia al panel padre del di�logo
	 */
	public DialogoRegistrarProducto( InterfazSuperAndesApp principalP )
	{
		principal = principalP;
		setLayout( new GridLayout( 13, 2 ) );
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

		btnCancelar = new JButton( "Cancelar" );
		btnCancelar.setActionCommand( CANCELAR );
		btnCancelar.addActionListener( this );

		add( btnRegistrar );
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
				int cantidadPrese = Integer.parseInt(txtCodigoBarras.getText());
				String [] especificaciones = txtEspecificacion.getText().split(",");
				principal.adicionarProducto2(txtNombre.getText(), txtMarca.getText(),precioU,txtPresentacion.getText(),precioUniMed,cantidadPrese,txtUnidadMedida.getText(),txtCodigoBarras.getText(),txtCalidad.getText(),txtFechaVencimiento.getText(), especificaciones[1], especificaciones[0]);
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog (this, "Valores ingresados no validos", "Agregar producto: no exitoso", JOptionPane.ERROR_MESSAGE);
			}

		}
		else
		{
			this.dispose();
		}
	}

}
