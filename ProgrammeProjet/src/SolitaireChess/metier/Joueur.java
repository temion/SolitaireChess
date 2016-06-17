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
	private int    mouvements;
	private int    theme;

	private boolean[][] defisAccomplis;
	private boolean[][] defisDebloques;
	private int[]       dernierDefi;

	private Controleur ctrl;


	public Joueur( String nom, Controleur ctrl )
	{
		this.nom = nom;
		this.mouvements = 0;
		this.theme = 1;

		this.ctrl = ctrl;
		this.defisAccomplis = new boolean[4][15];
		this.defisDebloques = new boolean[4][15];
		this.defisDebloques[0][0] = true;
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


	public int getTheme()
	{
		return theme;
	}


	public void setTheme( int theme )
	{
		this.theme = theme;
	}


	public boolean[] getDefisAccomplis( int i ) { return defisAccomplis[i]; }


	public void addDefiAccompli( int i, int j )
	{
		defisAccomplis[i-1][( j - 1 ) % 15] = true;
		defisDebloques[i-1][j % 15] = true;
	}


	public boolean getDefiDebloque( int i, int j )
	{
		return defisDebloques[i][j];
	}
}
