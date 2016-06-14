/**
 * SolitaireChess - Projet Tutoré
 * Classe métier qui gère les rois.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

package SolitaireChess.metier.pieces;


import SolitaireChess.metier.Echiquier;

public class Roi extends Piece
{
	/**
	 * Construit un roi.
	 *
	 * @param echiquier l'échiquier auquel appartient le roi
	 */
	public Roi( Echiquier echiquier )
	{
		super( echiquier );
	}


	/**
	 * Permet de vérifier qu'on peut déplacer le roi.
	 *
	 * @param x      la position horizontale de la pièce à déplacer
	 * @param y      la position verticale de la pièce à déplacer
	 * @param xCible la position horizontale vers laquelle déplacer
	 * @param yCible la position verticale vers laquelle déplacer
	 * @return vrai on si peut le déplacer, sinon faux
	 */
	@Override
	public boolean peutSeDeplacer( int x, int y, int xCible, int yCible )
	{
		return Math.abs( x - xCible ) < 2 && Math.abs( y - yCible ) < 2;
	}
}
