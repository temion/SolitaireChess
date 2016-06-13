/**
 * SolitaireChess - Projet Tutoré
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

package SolitaireChess.metier.pieces;


import SolitaireChess.metier.Echiquier;

public class Roi extends Piece
{
	public Roi( Echiquier echiquier )
	{
		super( echiquier );
	}

	@Override
	public boolean deplacer( int x, int y, int xCible, int yCible )
	{
		if( echiquier.getEchiquier()[xCible][yCible] != null && Math.abs(
				x - xCible ) < 2 && Math.abs(
				y - yCible ) < 2 )
		{
			echiquier.getEchiquier()[xCible][yCible] = this;
			echiquier.getEchiquier()[x][y] = null;
			return true;
		}

		return false;
	}

	public String toString()
	{
		return "JE SUIS LE ROI SALE CON";
	}
}
