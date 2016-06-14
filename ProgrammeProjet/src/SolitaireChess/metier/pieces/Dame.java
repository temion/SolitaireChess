package SolitaireChess.metier.pieces;


import SolitaireChess.metier.Echiquier;

/**
 * SolitaireChess - Projet Tutoré
 * Classe métier qui gère les dames.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */
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
}
