package SolitaireChess.metier;


/**
 * SolitaireChess - Projet Tutoré
 * Classe métier qui gère les échiquiers.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import SolitaireChess.Controleur;
import SolitaireChess.metier.pieces.*;

import java.io.FileReader;
import java.util.Scanner;

public class Echiquier
{
	private Piece[][] echiquier;
	private int       defi;
	private int       niveau;

	private Controleur ctrl;


	/**
	 * Construit un échiquier.
	 * @param niveau le niveau de l'échiquier
	 * @param defi le défi de l'échiquier
	 */
	public Echiquier( int niveau, int defi, Controleur ctrl )
	{
		this.echiquier = new Piece[4][4];
		this.niveau = niveau;
		this.defi = defi;
		this.ctrl = ctrl;
		this.initNiveau();
	}


	/**
	 * Permet de déplacer une pièce sur l'échiquier.
	 * @param x1 la position horizontale de la pièce à déplacer
	 * @param y1 la position verticale de la pièce à déplacer
	 * @param x2 la position horizontale de la pièce vers laquelle déplacer
	 * @param y2 la position verticale de la pièce vers laquelle déplacer
	 * @return vrai si on peut déplacer la pièce, sinon faux
	 */
	public boolean deplacer( int x1, int y1, int x2, int y2 )
	{
		if ( this.echiquier[x2][y2] != null && ( x1 != x2 || y1 != y2 ) )
			return this.echiquier[x1][y1].deplacer( x1, y1, x2, y2 );

		return false;
	}


	public void incrementerNiveau()
	{
		if (  )
	}


	private void initNiveau()
	{
		String sFichier =
				String.format( "./niveaux/niveau%02d/defi%02d.data", this.niveau, this.defi );

		try
		{
			Scanner sc = new Scanner( new FileReader( sFichier ) );

			String ligSc = "";

			for ( int i = 0; i < this.echiquier.length && sc.hasNextLine(); i++ )
			{
				ligSc = sc.nextLine();

				for ( int j = 0; j < this.echiquier[0].length && j < ligSc.length(); j++ )
				{
					this.ajouterPiece( i, j, ligSc.charAt( j ) );
				}
			}
			sc.close();
		} catch ( Exception e ) { e.printStackTrace(); }
	}


	private void ajouterPiece( int lig, int col, char piece )
	{
		if ( piece == 'R' ) this.echiquier[lig][col] = new Roi( this );
		else if ( piece == 'D' ) this.echiquier[lig][col] = new Dame( this );
		else if ( piece == 'C' ) this.echiquier[lig][col] = new Cavalier( this );
		else if ( piece == 'T' ) this.echiquier[lig][col] = new Tour( this );
		else if ( piece == 'F' ) this.echiquier[lig][col] = new Fou( this );
		else if ( piece == 'P' ) this.echiquier[lig][col] = new Pion( this );
	}


	public String getSymbole( int i, int j )
	{
		if ( this.echiquier[i][j] != null )
			return this.echiquier[i][j].getSymbole();

		return null;
	}

	public Controleur getCtrl() { return this.ctrl; }

	public Piece[][] getEchiquier()
	{
		return this.echiquier;
	}


	public int getDefi()
	{
		return this.defi;
	}


	public int getNiveau()
	{
		return this.niveau;
	}


	public int getNbLigne()   { return 4; }


	public int getNbColonne() { return 4; }
}
