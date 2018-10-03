package uniandes.isis2304.superAndes.interfazApp;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DialogoRegistrarEstante extends JDialog implements ActionListener{
	 /**
     * Comando para agregar un producto
     */
    private final static String REGISTRAR = "REGISTRAR";

    /**
     * Comando para cancelar la operación
     */
    private final static String CANCELAR = "CANCELAR";
	private InterfazSuperAndesApp principal;
	/**
     * Etiqueta nombre
     */
    private JLabel lbTipo;

    /**
     * Etiqueta marca
     */
    private JLabel lbVolumen;

    /**
     * Etiqueta precio unitario
     */
    private JLabel lbPeso;
    /**
     * Etiqueta presentacion
     */
    private JLabel lbId;

    /**
     * Etiqueta precio unidad medida
     */
    private JLabel lbNivelAbastecimiento;

    /**
     * Campo de texto para mostrar el nombre
     */
    private JTextField txtTipo;

    /**
     * Campo de texto para mostrar la marca
     */
    private JTextField txtVolumen;

    /**
     * Campo de texto para mostrar el precio unitario
     */
    private JTextField txtId;

    /**
     * Campo de texto para mostrar el precio unidad medida
     */
    private JTextField txtPeso;

    /**
     * Botón registrar
     */
    private JButton btnRegistrar;

    /**
     * Botón cancelar
     */
    private JButton btnCancelar;

    /**
     * Etiqueta precio unidad medida
     */
    private JLabel lbDireccionSucursal;

    /**
     * Campo de texto para mostrar el nombre
     */
    private JTextField txtDireccionSucursal;
    /**
     * Etiqueta precio unidad medida
     */
    private JLabel lbCiudad;

    /**
     * Campo de texto para mostrar el nombre
     */
    private JTextField txtCiudad;
    private JTextField txtNivelAbastecimiento;
    
    // --------------------------------------------------------
    // Constructores
    // --------------------------------------------------------

    /**
     * Construye un nuevo dialogo para agregar un crédito
     * @param principalP es la referencia al panel padre del diálogo
     */
    public DialogoRegistrarEstante( InterfazSuperAndesApp principalP )
    {
        principal = principalP;

        setLayout( new GridLayout( 8, 2 ) );
        this.setSize( 600, 280 );
        setTitle( "Agregar estante" );

        lbTipo = new JLabel( "Tipo:" );
        txtTipo = new JTextField( );

        add( lbTipo );
        add( txtTipo );

        lbVolumen = new JLabel( "Volumen:" );
        txtVolumen = new JTextField( );
        
        add( lbVolumen );
        add( txtVolumen );

        lbPeso = new JLabel( "Peso:" );
        txtPeso = new JTextField( );

        add( lbPeso );
        add( txtPeso );
        
        lbNivelAbastecimiento = new JLabel( "Nivel de abastecimiento:" );
        txtNivelAbastecimiento = new JTextField( );
        
        add( lbNivelAbastecimiento );
        add( txtNivelAbastecimiento );
        
        lbDireccionSucursal = new JLabel( "Direccion sucursal:" );
        txtDireccionSucursal = new JTextField( );
        
        add( lbDireccionSucursal );
        add( txtDireccionSucursal );
        
        lbCiudad = new JLabel( "Ciudad:" );
        txtCiudad = new JTextField( );
        
        add( lbCiudad );
        add( txtCiudad );
        
        lbId = new JLabel( "Id:" );
        txtId = new JTextField( );
        
        add( lbId );
        add( txtId );
        
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
				double peso = Double.parseDouble(txtPeso.getText());
				double volumen = Double.parseDouble(txtVolumen.getText());
				double nivel = Double.parseDouble(txtNivelAbastecimiento.getText());
				long id = Integer.parseInt(txtId.getText());
				principal.adicionarEstante2(txtTipo.getText(),volumen,id,peso, nivel,txtDireccionSucursal.getText(), txtCiudad.getText());
				this.dispose();
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog (this, "Valores ingresados no validos", "Agregar estante: no exitoso", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else
		{
			this.dispose();
		}
	}

}
