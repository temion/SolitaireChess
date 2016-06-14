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
	 * @param x         la position horizontale du roi
	 * @param y         la position verticale du roi
	 * @param echiquier l'échiquier auquel appartient le roi
	 */
	public Roi( int x, int y, Echiquier echiquier )
	{
		super( x, y, echiquier );
	}


	/**
	 * Permet de vérifier qu'on peut déplacer le roi.
	 *
	 * @param xCible la position horizontale vers laquelle déplacer
	 * @param yCible la position verticale vers laquelle déplacer
	 * @return vrai on si peut le déplacer, sinon faux
	 */
	@Override
	public boolean peutSeDeplacer( int xCible, int yCible )
	{
		return Math.abs( x - xCible ) < 2 && Math.abs( y - yCible ) < 2;
	}
}
