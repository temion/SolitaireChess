package SolitaireChess;


import SolitaireChess.ihm.FenetreSolitaireChess;
import SolitaireChess.metier.Echiquier;

/**
 * SolitaireChess - Projet Tutoré
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */
public class Controleur
{
	private FenetreSolitaireChess fenetre;
	private Echiquier             echiquier;


	public Controleur()
	{
		this.echiquier = new Echiquier( 1, 1 );
		this.fenetre = new FenetreSolitaireChess( this );
	}


	public static void main( String[] arg )
	{
		new Controleur();
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


	public int getTailleImg() { return 100; }


	public int getNbLigne()   { return this.echiquier.getNbLigne(); }


	public int getNbColonne() {return this.echiquier.getNbColonne(); }
}
