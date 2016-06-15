package SolitaireChess.metier.pieces;

/**
 * SolitaireChess - Projet Tutoré
 * Classe métier qui gère les dames.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import SolitaireChess.metier.Echiquier;

public class Dame extends Piece
{
	/**
	 * Construit une dame.
	 *
	 * @param x         la position horizontale de la dame
	 * @param y         la postition verticale de la dame
	 * @param echiquier l'échiquier auquel appartient la dame
	 */
	public Dame( int x, int y, Echiquier echiquier )
	{
		super( x, y, echiquier );
	}


	/**
	 * Permet de vérifier qu'on peut déplacer la dame.
	 *
	 * @param xCible la position horizontale vers laquelle déplacer
	 * @param yCible la position verticale vers laquelle déplacer
	 * @return vrai si on peut la déplacer, sinon faux
	 */
	@Override
	public boolean peutSeDeplacer( int xCible, int yCible )
	{
		return Math.abs( xCible - x ) == Math.abs( yCible - y ) || x == xCible || y == yCible;
	}

	/**
	 * Permet de vérifier qu'il n'y a aucune pièce entre la pièce selectionnée et sa destination
	 *
	 * @param xCible la position horizontale vers laquelle déplacer
	 * @param yCible la position verticale vers laquelle déplacer
	 * @return vrai s'il n'y a pas de piece, sinon faux
	 */
	private boolean personneDansLeChamp( int xCible, int yCible )
	{
		// Déplacement diagonales

		boolean xPlusGrand = x > xCible;
		boolean yPlusGrand = y > yCible;

		int i, j;
		i = x;
		j = y;

		while ( i != xCible && j != yCible )
		{
			if ( i != x && echiquier.getEchiquier()[i][j] != null )
				return false;

			if ( xPlusGrand )
				i--;
			else
				i++;

			if ( yPlusGrand )
				j--;
			else
				j++;
		}

		// Déplacement en lignes et colonnes

		if ( x == xCible )
		{
			int yMin = y < yCible ? y : yCible;
			int yMax = y > xCible ? y : yCible;

			for ( i = yMin + 1; i < yMax; i++ )
				if ( echiquier.getEchiquier()[x][i] != null )
					return false;
		}
		else if ( y == yCible )
		{
			int xMin = x < xCible ? x : xCible;
			int xMax = x > xCible ? x : xCible;

			for ( i = xMin + 1; i < xMax; i++ )
				if ( echiquier.getEchiquier()[i][y] != null )
					return false;
		}
		return true;
	}
}
