package uniandes.isis2304.superAndes.interfazApp;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DialogoRegistrarPromocion extends JDialog implements ActionListener{
	/**
	 * Comando para agregar un producto
	 */
	private final static String REGISTRAR = "REGISTRAR";

	/**
	 * Comando para cancelar la operación
	 */
	private final static String CANCELAR = "CANCELAR";
	/**
	 * 
	 */
	private InterfazSuperAndesApp principal;
	/**
	 * Etiqueta nombre
	 */
	private JLabel lbDescripcion;

	/**
	 * Etiqueta direccion
	 */
	private JLabel lbUniDisponibles; 

	/**
	 * Etiqueta direccion
	 */
	private JLabel lbCodigoBarras;

	/**
	 * Etiqueta fecha de inicio
	 */
	private JLabel lbFechaInicio;

	/**
	 * Etiqueta fecha de fin
	 */
	private JLabel lbFechaFin;

	/**
	 * Etiqueta fecha de inicio
	 */
	private JLabel lbDireccion;

	/**
	 * Etiqueta fecha de fin
	 */
	private JLabel lbCiudad;

	/**
	 * Campo de texto para mostrar el nombre
	 */
	private JComboBox opcionesPromo;

	/**
	 * Campo de texto para mostrar la direccion
	 */
	private JTextField txtUniDisponibles;

	/**
	 * Campo de texto para mostrar la direccion
	 */
	private JTextField txtCodBarras;

	/**
	 * Campo texto fecha inicio
	 */
	private JTextField txtFechaInicio;

	/**
	 * Campo texto fecha fin
	 */
	private JTextField txtFechaFin;

	/**
	 * Campo texto fecha inicio
	 */
	private JTextField txtDireccion;

	/**
	 * Campo texto fecha fin
	 */
	private JTextField txtCiudad;

	/**
	 * Botón registrar
	 */
	private JButton btnRegistrar;

	/**
	 * Botón cancelar
	 */
	private JButton btnCancelar;
	
	/**
	 * Cantidad/Unidad pagar
	 */
	private JTextField txtPagar;
	
	/**
	 * Cantidad/Unidad llevar
	 */
	private JTextField txtLlevar;
	
	/**
	 * Descuento
	 */
	private JTextField txtDescuento;
	
	/**
	 * Unidades
	 */
	private JTextField txtUnidades;
	
	/**
	 * Descripcion de la promocion
	 */
	private String descripcion;

	// --------------------------------------------------------
	// Constructores
	// --------------------------------------------------------

	/**
	 * Construye un nuevo dialogo para agregar un crédito
	 * @param principalP es la referencia al panel padre del diálogo
	 */
	public DialogoRegistrarPromocion( InterfazSuperAndesApp pPrincipal, String opcion, String[] opciones )
	{
		principal = pPrincipal;

		setLayout( new GridLayout( 9, 2 ) );
		this.setSize( 600, 200 );
		setTitle( "Agregar promocion - "+ opcion );

		lbUniDisponibles = new JLabel( "Unidades disponibles:" );
		txtUniDisponibles = new JTextField( );

		add( lbUniDisponibles );
		add( txtUniDisponibles );

		lbCodigoBarras = new JLabel( "Código de barras:" );
		txtCodBarras = new JTextField( );

		add( lbCodigoBarras );
		add( txtCodBarras );

		lbFechaInicio = new JLabel("Fecha de inicio: (2018-5-6)");
		txtFechaInicio = new JTextField();

		add(lbFechaInicio);
		add(txtFechaInicio);

		lbFechaFin = new JLabel("Fecha de fin: (2018-5-6)");
		txtFechaFin = new JTextField();

		add(lbFechaFin);
		add(txtFechaFin);

		lbDireccion = new JLabel("Dirección de la sucursal:");
		txtDireccion = new JTextField();

		add(lbDireccion);
		add(txtDireccion);

		lbCiudad = new JLabel("Ciudad de la sucursal:");
		txtCiudad = new JTextField();

		add(lbCiudad);
		add(txtCiudad);
		
		if(opcion.contains("cantidad, lleve"))
		{
			JLabel lbCantidadPagar = new JLabel("Pague");
			txtPagar = new JTextField();
			
			add(lbCantidadPagar);
			add(txtPagar);
			
			JLabel lbCantidadLlevar = new JLabel("Lleve");
			txtLlevar = new JTextField();
			
			add(lbCantidadLlevar);
			add(txtLlevar);
			
			descripcion = opcion;
		}

		else if(opcion.startsWith("Descuento del"))
		{
			JLabel lbDescuento = new JLabel("Descuento");
			txtDescuento = new JTextField();
			
			add(lbDescuento);
			add(txtDescuento);
			
			descripcion = opcion;
		}

		else if(opcion.contains("y lleve el siguiente con"))
		{
			JLabel lbUnidades = new JLabel("Pague");
			txtUnidades = new JTextField();
			
			add(lbUnidades);
			add(txtUnidades);
			
			JLabel lbDescuento = new JLabel("Lleve");
			txtDescuento = new JTextField();
			
			add(lbDescuento);
			add(txtDescuento);
			
			descripcion = opcion;
		}

		else if(opcion.contains("unidades, lleve"))
		{
			JLabel lbUnidadesPagar = new JLabel("Pague");
			txtPagar = new JTextField();
			
			add(lbUnidadesPagar);
			add(txtPagar);
			
			JLabel lbUnidadesLlevar = new JLabel("Lleve");
			txtLlevar = new JTextField();
			
			add(lbUnidadesLlevar);
			add(txtLlevar);
			
			descripcion = opcion;
		}

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
			if(!descripcion.equals(""))
			{
				Timestamp fechaInicio = Timestamp.valueOf(txtFechaInicio.getText()+" 00:00:00");
				Timestamp fechaFin = Timestamp.valueOf(txtFechaInicio.getText()+" 00:00:00");
				if(descripcion.contains("cantidad, lleve"))
				{
					descripcion = descripcion.replace("X", ""+txtPagar).replace("Y", ""+txtLlevar);
				}

				else if(descripcion.startsWith("Descuento del"))
				{
					descripcion = descripcion.replace("P", ""+txtDescuento.getText());
				}

				else if(descripcion.contains("y lleve el siguiente con"))
				{
					descripcion = descripcion.replace("N", ""+txtUnidades).replace("D", ""+txtDescuento);
				}

				else if(descripcion.contains("unidades, lleve"))
				{
					descripcion = descripcion.replace("N", ""+txtPagar).replace("M", ""+txtLlevar);
				}

				System.out.println(descripcion);
				principal.adicionarPromocion2(fechaInicio, fechaFin, descripcion, txtCodBarras.getText(), Integer.parseInt(txtUniDisponibles.getText()), 0, txtDireccion.getText(), txtCiudad.getText());
				this.dispose();
			}
		}
		else
		{
			this.dispose();
		}
	}
}
