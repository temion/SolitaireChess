/**
 * SolitaireChess - Projet Tutoré
 * Classe métier qui gère les pièces du jeu.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

package SolitaireChess.metier.pieces;


import SolitaireChess.metier.Echiquier;

public abstract class Piece
{
	protected Echiquier echiquier;
	protected int       x;
	protected int       y;


	/**
	 * Construit une pièce.
	 *
	 * @param echiquier l'echiquier auquel appartient la pièce
	 */
	public Piece( int x, int y, Echiquier echiquier )
	{
		this.x = x;
		this.y = y;
		this.echiquier = echiquier;
	}


	/**
	 * Permet de déplacer une pièce.
	 *
	 * @param xCible la position horizontale vers laquelle déplacer
	 * @param yCible la position verticale vers laquelle déplacer
	 * @return vrai si on a pu déplacer la pièce, sinon faux
	 */
	public boolean deplacer( int xCible, int yCible )
	{
		if ( this.echiquier.getEchiquier()[xCible][yCible] != null &&
			 ( x != xCible || y != yCible ) && this.peutSeDeplacer( xCible, yCible ) )
		{
			echiquier.getEchiquier()[xCible][yCible] = this;
			echiquier.getEchiquier()[x][y] = null;
			x = xCible;
			y = yCible;
			return true;
		}
		return false;
	}


	public boolean peutPrendreUnePiece()
	{
		for ( int i = 0; i < echiquier.getNbLigne(); i++ )
			for ( int j = 0; j < echiquier.getNbColonne(); j++ )
				if( echiquier.getEchiquier()[x][y].peutSeDeplacer( i, j ) && i != x && j != y )
					return true;

		return false;
	}


	/**
	 * Permet de vérifier si on peut déplacer une pièce en fonction de ses règles de déplacement
	 *
	 * @param xCible la position horizontale vers laquelle déplacer
	 * @param yCible la position verticale vers laquelle déplacer
	 * @return vrai si on peut déplacer la pièce selon ses règles de déplacement, sinon faux
	 */
	public abstract boolean peutSeDeplacer( int xCible, int yCible );


	/**
	 * Permet d'obtenir le symbole de la pièce pour la dessiner.
	 *
	 * @return le nom de la classe de la pièce qu'on veut dessiner
	 */
	public String getSymbole()
	{
		// On ne prend que le nom de la classe et pas tous les packages dans lesquelles elle est
		// contenue
		String[] str = getClass().getName().split( "[.]" );

		return str[str.length - 1];
	}

}
