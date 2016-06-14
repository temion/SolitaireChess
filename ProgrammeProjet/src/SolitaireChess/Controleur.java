package SolitaireChess;


import SolitaireChess.ihm.FenetreJeu;
import SolitaireChess.metier.Echiquier;

/**
 * SolitaireChess - Projet Tutoré
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */
public class Controleur
{
	private FenetreJeu fenetre;
	private Echiquier  echiquier;


	public Controleur()
	{
		this.echiquier = new Echiquier( 1, 1, this );
		this.fenetre = new FenetreJeu( this );
	}


	public static void main( String[] arg )
	{
		new Controleur();
	}


	public void deplacer( int x1, int y1, int x2, int y2 )
	{
		if ( this.echiquier.deplacer( x1, y1, x2, y2 ) )
		{
			System.out.print( "déplacé" );
			this.fenetre.majIHM();
		}
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


	public FenetreJeu getFenetre()
	{
		return fenetre;
	}


	public Echiquier getEchiquier()
	{
		return echiquier;
	}


	public int getTailleImg() { return 100; }


	public int getNbLigne()   { return this.echiquier.getNbLigne(); }


	public int getNbColonne() {return this.echiquier.getNbColonne(); }


	public boolean contientPiece( int i, int j )
	{
		if ( i > - 1 && i < echiquier.getNbLigne() && j > - 1 && j < echiquier.getNbColonne() )
			return echiquier.getEchiquier()[i][j] != null;
		return false;
	}
}