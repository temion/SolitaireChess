package SolitaireChess.metier.pieces;


import SolitaireChess.metier.Echiquier;

/**
 * SolitaireChess - Projet Tutoré
 * Classe métier qui gère les cavaliers.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */
public class Cavalier extends Piece
{
	/**
	 * Construit un cavalier.
	 *
	 * @param echiquier l'echiquier auquel appartient le cavalier
	 */
	public Cavalier( Echiquier echiquier )
	{
		super( echiquier );
	}


	@Override
	/**
	 * Permet de vérifier qu'on peut déplacer le cavalier.
	 * @return vrai si on peut le déplacer, sinon faux
	 */
	public boolean peutSeDeplacer( int x, int y, int xCible, int yCible )
	{
		return Math.abs( x - xCible ) == 2 && Math.abs( y - yCible ) == 1 ||
			   Math.abs( y - yCible ) == 2
			   && Math.abs( x - xCible ) == 1;
	}
}