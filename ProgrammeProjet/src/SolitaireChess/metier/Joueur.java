package SolitaireChess.metier;


import SolitaireChess.Controleur;

import java.io.Serializable;

/**
 * SolitaireChess - Projet Tutoré
 * Classe métier qui désigne un joueur.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

public class Joueur implements Serializable
{
	private String nom;
	private int    score;
	private int    mouvements;
	private int    theme;

	private Boolean[][] defisAccomplis;
	private int[]       dernierDefi;

	private Controleur ctrl;


	public Joueur( String nom, Controleur ctrl )
	{
		this.nom = nom;
		this.score = 0;
		this.mouvements = 0;
		this.theme = 1;

		this.ctrl = ctrl;
		this.defisAccomplis = new Boolean[4][15];
		this.dernierDefi = new int[]{ 1, 1 };
	}

	public void incrementerMouvements()
	{
		mouvements++;
	}

	public int getNbMouvements()
	{
		return mouvements;
	}

	public void setNom( String nom )
	{
		this.nom = nom;
	}


	public int[] getDernierDefi()
	{
		return dernierDefi;
	}


	public void setDernierDefi( int niveau, int defi )
	{
		dernierDefi = new int[]{ niveau, defi };
	}

	public String getNom()
	{
		return nom;
	}

	/**
	 * Permet d'obtenir le score.
	 *
	 * @return le score
	 */
	public int getScore()
	{
		return score;
	}

	public int getTheme()
	{
		return theme;
	}

	public void setTheme( int theme )
	{
		this.theme = theme;
	}
}
