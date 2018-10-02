package uniandes.isis2304.superAndes.interfazApp;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DialogoRegistrarProductoCategoria extends JDialog implements ActionListener{
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
    private JLabel lbCodigoBarras;

    /**
     * Etiqueta marca
     */
    private JLabel lbCategoria;

    
    /**
     * Campo de texto para mostrar el nombre
     */
    private JTextField txtCodigoBarras;

    /**
     * Campo de texto para mostrar la marca
     */
    private JTextField txtCategoria;

    /**
     * Bot�n registrar
     */
    private JButton btnRegistrar;

    /**
     * Bot�n cancelar
     */
    private JButton btnCancelar;
    
    // --------------------------------------------------------
    // Constructores
    // --------------------------------------------------------

    /**
     * Construye un nuevo dialogo para agregar un cr�dito
     * @param principalP es la referencia al panel padre del di�logo
     */
    public DialogoRegistrarProductoCategoria( InterfazSuperAndesApp principalP )
    {
        principal = principalP;

        setLayout( new GridLayout( 3, 2 ) );
        this.setSize( 300, 100 );
        setTitle( "Agregar producto a una categoria" );

        lbCodigoBarras = new JLabel( "C�digo de barras:" );
        txtCodigoBarras = new JTextField( );

        add( lbCodigoBarras );
        add( txtCodigoBarras );

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
			principal.adicionarProductoCategoria(txtCodigoBarras.getText(),txtCategoria.getText());
		}
		else
		{
			this.dispose();
		}
	}

}
