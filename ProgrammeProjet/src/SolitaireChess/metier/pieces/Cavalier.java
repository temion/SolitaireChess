package SolitaireChess.metier.pieces;

/**
 * SolitaireChess - Projet Tutoré
 * Classe métier qui gère les cavaliers.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import SolitaireChess.metier.Echiquier;

public class Cavalier extends Piece
{
	/**
	 * Construit un cavalier.
	 *
	 * @param x         la position horizontale du cavalier
	 * @param y         la position verticale du cavalier
	 * @param echiquier l'echiquier auquel appartient le cavalier
	 */
	public Cavalier( int x, int y, Echiquier echiquier )
	{
		super( x, y, echiquier );
	}


	/**
	 * Permet de vérifier qu'on peut déplacer le cavalier.
	 *
	 * @param xCible la position horizontale vers laquelle déplacer
	 * @param yCible la position verticale vers laquelle déplacer
	 * @return <b>true</b> si on peut le déplacer
	 */
	@Override
	public boolean peutSeDeplacer( int xCible, int yCible )
	{
		// Déplacement en 'L'
		return Math.abs( x - xCible ) == 2 && Math.abs( y - yCible ) == 1 ||
			   Math.abs( y - yCible ) == 2
			   && Math.abs( x - xCible ) == 1;
	}
}