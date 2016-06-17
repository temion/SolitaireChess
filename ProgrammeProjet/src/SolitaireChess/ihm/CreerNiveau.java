package SolitaireChess.ihm;


import SolitaireChess.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Florian on 17/06/2016.
 */
public class CreerNiveau extends JFrame implements ActionListener
{
	private Controleur ctrl;

	private JButton valider;
	private JButton abandonner;

	private PanelCreerNiveau panelCreation;


	public CreerNiveau( Controleur ctrl )
	{
		this.ctrl = ctrl;

		setTitle( "Creation de niveau" );

		getContentPane().setBackground( new Color( 0x0D0418 ) );

		JPanel p = new JPanel();
		p.add( this.valider = new JButton( "valider" ) );
		p.add( this.abandonner = new JButton( "abandonner" ) );
		add( p, BorderLayout.NORTH );

		add( this.panelCreation = new PanelCreerNiveau( this ) );

		this.valider.addActionListener( this );
		this.abandonner.addActionListener( this );

		setLocationRelativeTo( null );
		pack();
		setVisible( true );
	}


	public Controleur getCtrl()
	{
		return ctrl;
	}


	public static void main( String[] arg )
	{
		new CreerNiveau( null );
	}


	@Override
	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == valider )
		{
		}
		if ( e.getSource() == abandonner )
		{
			dispose();
		}
	}
}
