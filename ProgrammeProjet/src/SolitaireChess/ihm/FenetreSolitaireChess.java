package SolitaireChess.ihm;


import SolitaireChess.Controleur;

import javax.swing.*;

/**
 * SolitaireChess - Projet Tutoré
 * Classe ihm qui représente la fenêtre de jeu.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */
public class FenetreSolitaireChess extends JFrame
{
	private Controleur          ctrl;
	private PanelSolitaireChess plateau;


	/**
	 * Construit une fenêtre avec le plateau de jeu.
	 *
	 * @param ctrl le contrôleur qui gère la fenêtre
	 */
	public FenetreSolitaireChess( Controleur ctrl )
	{
		setTitle( "SolitaireChess" );                          // Le titre de la fenêtre

		this.ctrl = ctrl;

		// Ajout du plateau de jeu
		this.plateau = new PanelSolitaireChess( this.ctrl );
		add( this.plateau );

		setDefaultCloseOperation(
				JFrame.EXIT_ON_CLOSE );      // Fermeture de la fenêtre avec la croix
		pack();                                                // Taille de la fenêtre définie dynamiquement
		setResizable( false );                                 // Taille de la fenêtre fixe
		setLocationRelativeTo( null );                         // Fenêtre centrée
		setVisible( true );                                    // Fenêtre visible
	}


	/**
	 * Permet de mettre à jour le plateau.
	 */
	public void majIHM() { this.plateau.repaint(); }
}
