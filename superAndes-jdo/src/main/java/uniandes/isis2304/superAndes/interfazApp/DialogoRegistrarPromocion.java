package uniandes.isis2304.superAndes.interfazApp;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import javax.swing.JButton;
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
    private JTextField txtDescripcion;

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

    

    // --------------------------------------------------------
    // Constructores
    // --------------------------------------------------------

    /**
     * Construye un nuevo dialogo para agregar un crédito
     * @param principalP es la referencia al panel padre del diálogo
     */
    public DialogoRegistrarPromocion( InterfazSuperAndesApp pPrincipal )
    {
        principal = pPrincipal;

        setLayout( new GridLayout( 8, 2 ) );
        this.setSize( 600, 200 );
        setTitle( "Agregar proveedor" );

        lbDescripcion = new JLabel( "Descripción:" );
        txtDescripcion = new JTextField( );

        add( lbDescripcion );
        add( txtDescripcion );

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
			Timestamp fechaInicio = Timestamp.valueOf(txtFechaInicio.getText()+" 00:00:00");
			Timestamp fechaFin = Timestamp.valueOf(txtFechaInicio.getText()+" 00:00:00");
			principal.adicionarPromocion2(fechaInicio, fechaFin, txtDescripcion.getText(), txtCodBarras.getText(), Integer.parseInt(txtUniDisponibles.getText()), 0, txtDireccion.getText(), txtCiudad.getText());
			this.dispose();
		}
		else
		{
			this.dispose();
		}
	}
}
