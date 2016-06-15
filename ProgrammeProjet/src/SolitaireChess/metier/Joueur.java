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

	private Boolean[][] defisAcocmplis;
	private int[]       dernierDefi;

	private Controleur ctrl;


	public Joueur( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.defisAcocmplis = new Boolean[4][15];
		this.dernierDefi = new int[] { 1, 8};
	}


	public void setNom( String nom )
	{
		this.nom = nom;
	}

	public int[] getDernierDefi() { return dernierDefi; }

	public void setDernierDefi( int niveau, int defi )
	{
		dernierDefi = new int[]{ niveau, defi };
	}
}
