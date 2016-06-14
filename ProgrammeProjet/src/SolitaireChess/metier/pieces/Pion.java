package SolitaireChess.metier.pieces;

/**
 * SolitaireChess - Projet Tutoré
 * Classe métier qui gère les pions.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import SolitaireChess.metier.Echiquier;

public class Pion extends Piece
{
	/**
	 * Construit un pion.
	 *
	 * @param x         la position horizontale du pion
	 * @param y         la position verticale du pion
	 * @param echiquier l'échiquier auquel appartient le pion
	 */
	public Pion( int x, int y, Echiquier echiquier )
	{
		super( x, y, echiquier );
	}


	/**
	 * Permet de vérifier qu'on peut déplacer le pion.
	 *
	 * @param xCible la position horizontale vers laquelle déplacer
	 * @param yCible la position verticale vers laquelle déplacer
	 * @return vrai si on peut le déplacer, sinon faux
	 */
	@Override
	public boolean peutSeDeplacer( int xCible, int yCible )
	{
		// Déplacement en diagonal de 1 uniquement vers le haut
		return Math.abs( y - yCible ) < 2 && x > xCible && y != yCible;
	}
}
