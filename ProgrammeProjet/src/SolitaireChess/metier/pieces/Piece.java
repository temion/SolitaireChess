/**
 * SolitaireChess - Projet Tutoré
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

package SolitaireChess.metier.pieces;


import SolitaireChess.metier.Echiquier;

public abstract class Piece
{
	protected Echiquier echiquier;


	public Piece( Echiquier echiquier )
	{
		this.echiquier = echiquier;
	}


	public abstract boolean deplacer(int x, int y, int xCible, int yCible);


	public String getSymbole()
	{
		String[] str = getClass().getName().split( "[.]" );

		return str[str.length - 1];
	}

}
