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
	private Accueil accueil;
	private Jeu     jeu;

	public FenetreJeu(Controleur ctrl)
	{
		this.ctrl = ctrl;

		setTitle("Solitaire Chess");
		setLocation(50,50);
		this.accueil = new Accueil(this);
		add(this.accueil);

		pack();
		setVisible(true);
	}

	public void afficherJeu()
	{
		this.remove(accueil);
		this.jeu = new Jeu(this, ctrl);
		add(this.jeu);
		pack();
	}

	public void afficherAccueil()
	{
		this.remove(jeu);
		this.accueil = new Accueil(this);
		add(this.accueil);
		pack();
	}

	public void majIHM() { this.jeu.majIHM(); }

}
