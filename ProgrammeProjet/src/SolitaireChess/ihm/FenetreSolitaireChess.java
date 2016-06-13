package SolitaireChess.ihm;


import SolitaireChess.Controleur;

import javax.swing.*;

/**
 * SolitaireChess - Projet Tutoré
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */
public class FenetreSolitaireChess extends JFrame
{
	private Controleur          ctrl;
	private PanelSolitaireChess plateau;


	public FenetreSolitaireChess( Controleur ctrl )
	{
		setTitle( "SolitaireChess" );

		this.ctrl = ctrl;
		this.plateau = new PanelSolitaireChess( this.ctrl );
		add( this.plateau );

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		pack();
		setResizable( false );
		setLocationRelativeTo( null );
		setVisible( true );
	}
}
