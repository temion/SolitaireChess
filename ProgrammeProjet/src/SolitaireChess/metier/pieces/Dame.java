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
	 * @param echiquier l'échiquier auquel appartient la dame
	 */
	public Dame( Echiquier echiquier )
	{
		super( echiquier );
	}


	/**
	 * Permet de vérifier qu'on peut déplacer la dame.
	 *
	 * @param x      la position horizontale de la pièce à déplacer
	 * @param y      la position verticale de la pièce à déplacer
	 * @param xCible la position horizontale vers laquelle déplacer
	 * @param yCible la position verticale vers laquelle déplacer
	 * @return vrai si on peut la déplacer, sinon faux
	 */
	@Override
	public boolean peutSeDeplacer( int x, int y, int xCible, int yCible )
	{
		return Math.abs( xCible - x ) == Math.abs( yCible - y ) || x == xCible || y == yCible;
	}
}
