package SolitaireChess;


import SolitaireChess.ihm.Accueil;
import SolitaireChess.ihm.Jeu;
import SolitaireChess.metier.Echiquier;
import SolitaireChess.metier.Joueur;

import java.util.ArrayList;

/**
 * SolitaireChess - Projet Tutoré
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */
public class Controleur
{
	private Accueil           accueil;
	private Echiquier         echiquier;
	private ArrayList<Joueur> alJoueur;
	private Joueur            joueurCourant;

	private Jeu jeu;


	public Controleur()
	{
		this.echiquier = new Echiquier( this );
		this.accueil = new Accueil( this );
		this.alJoueur = new ArrayList<Joueur>();
		this.joueurCourant = new Joueur( this );
		this.jeu = null;
	}


	public static void main( String[] arg )
	{
		new Controleur();
	}


	public void deplacer( int x1, int y1, int x2, int y2 )
	{
		if( x2 >= 0 && x2 < getNbLigne() && y2 >= 0 && y2 < getNbColonne() )// On ne teste pas x1 ni y1 car ils
			// seront toujours
			// dans les limites
			// du panel
			if( this.echiquier.getEchiquier()[x2][y2] != null && this.echiquier.deplacer( x1, y1,
																						  x2, y2 ) )
				majIHM();
	}

	public void majIHM()
	{
		this.jeu.majIHM();
	}


	public String getImgFond()
	{
		return "./images/fond.png";
	}


	public String getImg( int i, int j )
	{
		String symbole = this.echiquier.getSymbole( i, j );

		return "./images/" + symbole + ".png";
	}


	public Jeu getJeu()
	{
		return jeu;
	}


	public Echiquier getEchiquier()
	{
		return echiquier;
	}


	public int getTailleImg()
	{
		return 100;
	}


	public int getNbLigne()
	{
		return this.echiquier.getNbLigne();
	}


	public int getNbColonne()
	{
		return this.echiquier.getNbColonne();
	}


	public void setJeu( Jeu jeu )
	{
		this.jeu = jeu;
	}


	public Joueur getJoueurCourant()
	{
		return joueurCourant;
	}


	public void setFenetreJeu( Jeu jeu )
	{
		this.jeu = jeu;
	}


	public boolean contientPiece( int i, int j )
	{
		if( i > -1 && i < echiquier.getNbLigne() && j > -1 && j < echiquier.getNbColonne() )
			return echiquier.getEchiquier()[i][j] != null;
		return false;
	}
}