package SolitaireChess.metier.pieces;


import SolitaireChess.metier.Echiquier;

/**
 * SolitaireChess - Projet Tutoré
 * Classe métier qui gère les fous.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */
public class Fou extends Piece
{
	/**
	 * Construit un fou.
	 *
	 * @param x         la position horizontale du fou
	 * @param y         la position verticale du fou
	 * @param echiquier l'echiquier auquel appartient le fou
	 */
	public Fou( int x, int y, Echiquier echiquier )
	{
		super( x, y, echiquier );
	}


	/**
	 * Permet de vérifier qu'on peut déplacer le fou.
	 *
	 * @param xCible la position horizontale vers laquelle déplacer
	 * @param yCible la position verticale vers laquelle déplacer
	 * @return
	 */
	@Override
	public boolean peutSeDeplacer( int xCible, int yCible )
	{
		return Math.abs( xCible - x ) == Math.abs( yCible - y ) &&
			   personneDansLeChamp( xCible, yCible );
	}


	/**
	 * Permet de vérifier qu'il n'y a aucune pièce entre la pièce selectionnée et sa destination
	 *
	 * @param xCible la position horizontale vers laquelle déplacer
	 * @param yCible la position verticale vers laquelle déplacer
	 * @return vrai s'il n'y a pas de pièce, sinon faux
	 */
	private boolean personneDansLeChamp( int xCible, int yCible )
	{
		boolean xPlusGrand = x > xCible;
		boolean yPlusGrand = y > yCible;

		int i, j;
		i = x;
		j = y;

		while ( i != xCible && j != yCible )
		{
			if ( i != x && echiquier.getEchiquier()[i][j] != null )
				return false;

			if ( xPlusGrand )
				i--;
			else
				i++;

			if ( yPlusGrand )
				j--;
			else
				j++;
		}
		return true;
	}
}
