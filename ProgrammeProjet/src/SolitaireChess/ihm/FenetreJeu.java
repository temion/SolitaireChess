package SolitaireChess.ihm; /**
 * SolitaireChess - Projet Tutoré
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import SolitaireChess.Controleur;

public class FenetreJeu extends JFrame
{
	private Controleur ctrl;
	private Accueil    accueil;
	private Jeu        jeu;


	public FenetreJeu( Controleur ctrl )
	{
		setTitle( "SolitaireChess" );
		this.ctrl = ctrl;

		this.jeu = new Jeu( this, ctrl );
		this.accueil = new Accueil( this );
		add( this.accueil );

		pack();
		setLocationRelativeTo( null );
		setResizable( false );
		setVisible( true );
	}


	public void afficherJeu()
	{
		this.remove( accueil );
		add( this.jeu );
		pack();
	}


	public void afficherAccueil()
	{
		this.remove( jeu );
		add( this.accueil );
		pack();
	}


	public void majIHM() { this.jeu.majIHM(); }

}
