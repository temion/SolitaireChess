package SolitaireChess.metier.pieces;


import SolitaireChess.metier.Echiquier;

/**
 * SolitaireChess - Projet Tutoré
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */
public class Fou extends Piece
{
	public Fou( Echiquier echiquier )
	{
		super( echiquier );
	}


	@Override
	public boolean peutSeDeplacer( int x, int y, int xCible, int yCible )
	{
		return Math.abs( xCible - x ) == Math.abs( yCible - y ) && personneDansLeChamp( x, y, xCible, yCible );
	}


	private boolean personneDansLeChamp( int x, int y, int xCible, int yCible )
	{
		boolean xPlusGrand = x > xCible;
		boolean yPlusGrand = y > yCible;

		int i,j;
		i=x;
		j=y;

		while(i != xCible && j != yCible)
		{
			if(i != x && echiquier.getEchiquier()[i][j] != null)
				return false;

			if(xPlusGrand)
				i--;
			else
				i++;

			if(yPlusGrand)
				j--;
			else
				j++;
		}
		return true;
	}
}
