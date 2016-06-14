package SolitaireChess.ihm;
/**
 * SolitaireChess - Projet Tutoré
 * Classe ihm de la fenêtre du jeu.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import SolitaireChess.Controleur;

import javax.swing.*;

public class FenetreJeu extends JFrame
{
	private Controleur ctrl;
	private Accueil    accueil;
	private Jeu        jeu;

	/**
	 * Contruit la fenêtre.
	 * @param ctrl la classe qui fera le lien entre la classe et la partie métier
	 */
	public FenetreJeu( Controleur ctrl )
	{
		setTitle( "SolitaireChess" );
		this.ctrl = ctrl;

		this.jeu = new Jeu( ctrl );
		this.accueil = new Accueil( this );
		add( this.accueil );

		pack();
		setLocationRelativeTo( null );
		setResizable( false );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		setVisible( true );
	}

	/**
	 * Affiche dans la fenêtre l'écran du jeu.
	 */
	public void afficherJeu()
	{
		this.remove( accueil );
		add( this.jeu );
		pack();
	}

	/**
	 *  Affiche dans la fenêtre l'écran du menu principal.
	 */
	public void afficherAccueil()
	{
		this.remove( jeu );
		add( this.accueil );
		pack();
	}

	/**
	 * Met à jour la représentation ihm de l'échiquier.
	 */
	public void majIHM() { this.jeu.majIHM(); }

	/**
	 * Remet l'échiquier à l'état initial du niveau.
	 */
	public void recommencer()
	{
		ctrl.getEchiquier().recommencer();
	}

}
