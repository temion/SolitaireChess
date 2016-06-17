package SolitaireChess.metier.pieces;

/**
 * SolitaireChess - Projet Tutoré
 * Classe métier qui gère les tours.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import SolitaireChess.metier.Echiquier;

public class Tour extends Piece
{
	/**
	 * Construit une tour.
	 *
	 * @param x         la position horizontale du pion
	 * @param y         la position verticale du pion
	 * @param echiquier l'échiquier auquel appartient la tour
	 */
	public Tour( int x, int y, Echiquier echiquier )
	{
		super( x, y, echiquier );
	}


	/**
	 * Permet de vérifier qu'on peut déplacer la tour
	 *
	 * @param xCible la position horizontale vers laquelle déplacer
	 * @param yCible la position verticale vers laquelle déplacer
	 * @return <b>true</b> si on peut le déplacer
	 */
	@Override
	public boolean peutSeDeplacer( int xCible, int yCible )
	{
		// Déplacement en ligne ou en colonne
		return ( x == xCible || y == yCible ) && personneDansLeChamp( xCible, yCible );
	}


	/**
	 * Permet de vérifier qu'il n'y a aucune pièce entre la pièce selectionnée et sa destination
	 *
	 * @param xCible la position horizontale vers laquelle déplacer
	 * @param yCible la position verticale vers laquelle déplacer
	 * @return <b>true</b> s'il n'y a pas de piece
	 */
	private boolean personneDansLeChamp( int xCible, int yCible )
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
