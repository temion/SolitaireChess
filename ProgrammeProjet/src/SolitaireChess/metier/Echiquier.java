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
	private int       nbPiece;
	private boolean   aUnRoi;

	private Controleur ctrl;


	/**
	 * Construit un échiquier.
	 *
	 * @param niveau le niveau de l'échiquier
	 * @param defi   le défi de l'échiquier
	 */
	public Echiquier( int niveau, int defi, Controleur ctrl )
	{
		this.niveau = niveau;
		this.defi = defi;
		this.ctrl = ctrl;
		this.nbPiece = 0;
		this.aUnRoi = false;
		this.initNiveau();
	}


	/**
	 * Permet de déplacer une pièce sur l'échiquier.
	 *
	 * @param x1 la position horizontale de la pièce à déplacer
	 * @param y1 la position verticale de la pièce à déplacer
	 * @param x2 la position horizontale de la pièce vers laquelle déplacer
	 * @param y2 la position verticale de la pièce vers laquelle déplacer
	 * @return vrai si on peut déplacer la pièce, sinon faux
	 */
	public boolean deplacer( int x1, int y1, int x2, int y2 )
	{
		boolean estRoi = false;
		if ( aUnRoi ) estRoi = this.echiquier[x2][y2] instanceof Roi;

		if ( this.echiquier[x1][y1].deplacer( x2, y2 ) )
		{
			nbPiece--;

			if ( estRoi || aPerdu() ) recommencer();
			else if ( aGagne() ) incrementerDefi();
			else return true;
		}

		return false;
	}


	public void recommencer()
	{
		initNiveau();
		ctrl.getFenetre().majIHM();
	}


	private boolean aGagne()
	{
		return nbPiece == 1;
	}


	private boolean aPerdu()
	{
		if ( nbPiece > 1 )
			for ( int i = 0; i < ctrl.getNbLigne(); i++ )
				for ( int j = 0; j < ctrl.getNbColonne(); j++ )
					if ( echiquier[i][j].peutPrendreUnePiece() ) return false;

		return true;
	}


	/**
	 * Incrémente le defi  une fois que celui-ci est fini.
	 */
	private void incrementerDefi()
	{
		if ( this.defi < 60 )
			this.defi++;

		if ( this.defi % 15 == 0 )
			this.niveau++;

		this.initNiveau();
	}


	private void initNiveau()
	{
		String sFichier =
				String.format( "./niveaux/niveau%02d/defi%02d.data", this.niveau, this.defi );

		this.echiquier = new Piece[4][4];

		try
		{
			Scanner sc = new Scanner( new FileReader( sFichier ) );

			String ligSc = "";

			for ( int i = 0; i < this.echiquier.length && sc.hasNextLine(); i++ )
			{
				ligSc = sc.nextLine();

				for ( int j = 0; j < this.echiquier[0].length && j < ligSc.length(); j++ )
				{
					nbPiece = 0;
					ajouterPiece( i, j, ligSc.charAt( j ) );
				}
			}
			sc.close();
		} catch ( Exception e ) { e.printStackTrace(); }
	}


	private void ajouterPiece( int lig, int col, char piece )
	{
		if ( piece == 'R' )
		{
			this.echiquier[lig][col] = new Roi( lig, col, this );
			aUnRoi = true;
		}
		else if ( piece == 'D' ) this.echiquier[lig][col] = new Dame( lig, col, this );
		else if ( piece == 'C' ) this.echiquier[lig][col] = new Cavalier( lig, col, this );
		else if ( piece == 'T' ) this.echiquier[lig][col] = new Tour( lig, col, this );
		else if ( piece == 'F' ) this.echiquier[lig][col] = new Fou( lig, col, this );
		else if ( piece == 'P' ) this.echiquier[lig][col] = new Pion( lig, col, this );

		if ( this.echiquier[lig][col] != null ) this.nbPiece++;
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

	public String toString()
	{
		String s = "";
		for ( int i = 0; i < ctrl.getNbLigne(); i++ )
		{
			for ( int j = 0; j < ctrl.getNbColonne(); j++ )
				s += echiquier[i][j] + " ";
			s+= "\n";
		}

		return s;
	}
}
