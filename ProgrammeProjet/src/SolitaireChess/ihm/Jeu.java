package SolitaireChess.ihm; /**
 * SolitaireChess - Projet Tutoré
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import SolitaireChess.Controleur;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Jeu extends JPanel
{
	private Controleur ctrl;

	private FenetreJeu          fenetre;
	private PanelSolitaireChess plateau;

	private BarreAction barreAction;
	private PJeuEst     panelEst;


	public Jeu( FenetreJeu fenetre, Controleur ctrl )
	{
		this.ctrl = ctrl;

		setLayout( new BorderLayout() );

		this.fenetre = fenetre;

		barreAction = new BarreAction( fenetre );
		add( barreAction, "North" );

		panelEst = new PJeuEst();
		add( panelEst, "East" );

		this.plateau = new PanelSolitaireChess( this.ctrl );
		add( this.plateau );

	}

	public void majIHM() { this.plateau.repaint(); }

}
