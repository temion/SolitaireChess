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
	public boolean peutSeDeplacer( int x, int y, int xCible, int yCible )
	{
		return Math.abs( x - xCible ) < 2 && Math.abs( y - yCible ) < 2;
	}

	public String toString()
	{
		return "JE SUIS LE ROI SALE CON";
	}
}
