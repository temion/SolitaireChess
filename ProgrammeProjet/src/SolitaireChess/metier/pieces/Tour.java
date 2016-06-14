package SolitaireChess.metier.pieces;


import SolitaireChess.metier.Echiquier;

/**
 * SolitaireChess - Projet Tutoré
 * Classe métier qui gère les tours.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */
public class Tour extends Piece
{
	/**
	 * Construit une tour.
	 *
	 * @param echiquier l'échiquier auquel appartient la tour
	 */
	public Tour( Echiquier echiquier )
	{
		super( echiquier );
	}


	/**
	 * Permet de vérifier qu'on peut déplacer la tour
	 *
	 * @param x      la position horizontale de la pièce à déplacer
	 * @param y      la position verticale de la pièce à déplacer
	 * @param xCible la position horizontale vers laquelle déplacer
	 * @param yCible la position verticale vers laquelle déplacer
	 * @return vrai si on peut la déplacer, sinon faux
	 */
	@Override
	public boolean peutSeDeplacer( int x, int y, int xCible, int yCible )
	{
		return ( x == xCible || y == yCible ) && personneDansLeChamp( x, y, xCible, yCible );
	}


	/**
	 * Permet de vérifier qu'il n'y a aucune pièce entre la pièce selectionnée et sa destination
	 *
	 * @param x      la position horizontale de la pièce à déplacer
	 * @param y      la position verticale de la pièce à déplacer
	 * @param xCible la position horizontale vers laquelle déplacer
	 * @param yCible la position verticale vers laquelle déplacer
	 * @return vrai s'il n'y a pas de piece, sinon faux
	 */
	public boolean personneDansLeChamp( int x, int y, int xCible, int yCible )
	{
		if ( x == xCible )
		{
			int yMin = y < yCible ? y : yCible;
			int yMax = y > xCible ? y : yCible;

			for ( int i = yMin + 1; i < yMax; i++ )
				if ( echiquier.getEchiquier()[x][i] != null )
					return false;
		}
		else if ( y == yCible )
		{
			int xMin = x < xCible ? x : xCible;
			int xMax = x > xCible ? x : xCible;

			for ( int i = xMin + 1; i < xMax; i++ )
				if ( echiquier.getEchiquier()[i][y] != null )
					return false;
		}
		return true;
	}
}
