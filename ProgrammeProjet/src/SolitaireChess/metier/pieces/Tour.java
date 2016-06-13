package SolitaireChess.metier.pieces;


import SolitaireChess.metier.Echiquier;

/**
 * SolitaireChess - Projet Tutoré
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */
public class Tour extends Piece
{
	public Tour( Echiquier echiquier )
	{
		super( echiquier );
	}

	@Override
	public boolean peutSeDeplacer( int x, int y, int xCible, int yCible )
	{
		return (x == xCible || y == yCible) && personneDansLeChamp( x, y, xCible, yCible );
	}

	public boolean personneDansLeChamp( int x, int y, int xCible, int yCible )
	{
		if( x == xCible )
		{
			int xMin = x > xCible ? x : xCible;
			int xMax = x < xCible ? x : xCible;

			for( int i = xMin + 1; i < xMax; i++ )
				System.out.println(i + " : " + y);
				/*if( echiquier.getEchiquier()[i][y] != null )
					return false;*/
			System.out.println( "gros zizi" );
		} else if( y == yCible )
		{
			int yMin = y < yCible ? y : yCible;
			int yMax = y > yCible ? y : yCible;

			for( int i = yMin + 1; i < yMax; i++ )
				System.out.println(x + " : " + i);
				/*if( echiquier.getEchiquier()[x][i] != null )
					return false;*/
			System.out.println( "coucou" );
		}
		return true;
	}
}
