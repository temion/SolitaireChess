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

import javax.swing.*;
import java.io.FileReader;
import java.util.Scanner;

public class Echiquier
{
	private Piece[][] echiquier;
	private int       defi;
	private int       niveau;
	private int       score;
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
		this.aUnRoi = false;
		this.initDefi();
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
		if ( aUnRoi ) estRoi = echiquier[x2][y2] instanceof Roi;

		if ( echiquier[x1][y1].deplacer( x2, y2 ) )
		{
			nbPiece--;

			if ( estRoi || nbPiece > 1 && aPerdu() ) recommencer();
			else if ( aGagne() )
			{
				JOptionPane.showConfirmDialog( ctrl.getFenetre(), "Gagné mon con" );
				incrementerDefi();
			}
			else return true;
		}

		return false;
	}


	/**
	 * Permet de recommencer un niveau en cours.
	 */
	public void recommencer()
	{
		JOptionPane.showConfirmDialog( ctrl.getFenetre(), "Perdu gros con" );
		initDefi();
		ctrl.getFenetre().majIHM();
	}


	/**
	 * Permet de savoir si on a gagné le niveau en cours.
	 *
	 * @return vrai si on gagné, sinon faux
	 */
	private boolean aGagne()
	{
		return nbPiece == 1;
	}


	/**
	 * Permet de savoir si on a perdu le niveau en cours.
	 *
	 * @return vrai si on a perdu, sinon faux
	 */
	private boolean aPerdu()
	{
		for ( int i = 0; i < ctrl.getNbLigne(); i++ )
			for ( int j = 0; j < ctrl.getNbColonne(); j++ )
				if ( echiquier[i][j] != null && echiquier[i][j].peutPrendreUnePiece() )
					return false;

		JOptionPane.showConfirmDialog( ctrl.getFenetre(), "Perdu gros con" );
		return true;
	}


	/**
	 * Incrémente le defi  une fois que celui-ci est fini.
	 */
	private void incrementerDefi()
	{
		if ( defi % 15 == 0 )
			niveau++;

		if ( defi < 60 )
			defi++;

		initDefi();
		ctrl.getFenetre().majIHM();
	}


	/**
	 * Initialise le défi cours en fonction du niveau.
	 */
	private void initDefi()
	{
		String sFichier =
				String.format( "./niveaux/niveau%02d/defi%02d.data", niveau, defi );

		echiquier = new Piece[4][4];

		try
		{
			Scanner sc = new Scanner( new FileReader( sFichier ) );

			nbPiece = 0;

			String ligSc = "";

			for ( int i = 0; i < echiquier.length && sc.hasNextLine(); i++ )
			{
				ligSc = sc.nextLine();

				for ( int j = 0; j < echiquier[0].length && j < ligSc.length(); j++ )
					ajouterPiece( i, j, ligSc.charAt( j ) );
			}
			sc.close();
		} catch ( Exception e ) { e.printStackTrace(); }
	}


	/**
	 * Ajoute un pièce à l'échiquier.
	 *
	 * @param lig   la position horizontale de la pièce à créer
	 * @param col   la position verticale de la pièce à créer
	 * @param piece le caractère permettant de savoir quelle pièce créer
	 */
	private void ajouterPiece( int lig, int col, char piece )
	{
		if ( piece == 'R' )
		{
			echiquier[lig][col] = new Roi( lig, col, this );
			aUnRoi = true;
		}
		else if ( piece == 'D' ) echiquier[lig][col] = new Dame( lig, col, this );
		else if ( piece == 'C' ) echiquier[lig][col] = new Cavalier( lig, col, this );
		else if ( piece == 'T' ) echiquier[lig][col] = new Tour( lig, col, this );
		else if ( piece == 'F' ) echiquier[lig][col] = new Fou( lig, col, this );
		else if ( piece == 'P' ) echiquier[lig][col] = new Pion( lig, col, this );

		if ( echiquier[lig][col] != null ) nbPiece++;
	}


	/**
	 * Permet d'obtenir le nom de la pièce d'une case de l'échiquier.
	 *
	 * @param i la position horizontale de la pièce
	 * @param j la position verticale de la pièce
	 * @return nom de la pièce
	 */
	public String getSymbole( int i, int j )
	{
		if ( echiquier[i][j] != null )
			return echiquier[i][j].getSymbole();

		return null;
	}


	/**
	 * Permet d'obtenir le controleur associé à l'échiquier.
	 *
	 * @return le controleur associé à l'échiquier
	 */
	public Controleur getCtrl() { return ctrl; }


	/**
	 * Permet de retourner l'échiquier.
	 *
	 * @return l'échiquier.
	 */
	public Piece[][] getEchiquier()
	{
		return echiquier;
	}


	/**
	 * Permet d'obtenir le numéro du défi en cours.
	 *
	 * @return le numéro du défi en cours
	 */
	public int getDefi()
	{
		return defi;
	}


	/**
	 * Permet d'obtenir le numéro du niveau en cours.
	 *
	 * @return le numéro du niveau en cours
	 */
	public int getNiveau()
	{
		return niveau;
	}


	/**
	 * Permet d'obtenir le nombre de lignes de l'échiquier.
	 *
	 * @return le nombre de lignes de l'échiquier
	 */
	public int getNbLigne() { return 4; }


	/**
	 * Permet d'obtenir le nombre de colonnes de l'échiquier.
	 *
	 * @return le nombre de colonnes de l'échiquier
	 */
	public int getNbColonne() { return 4; }


	/**
	 * Permet de savoir si un roi est présent sur le niveau en cours.
	 * @return vrai si un roi est sur l'échiquier du niveau actuel, sinon faux
	 */
	public boolean isaUnRoi()
	{
		return aUnRoi;
	}


	/**
	 * Permet d'obtenir le nombre de pièces restantes sur l'échiquier.
	 * @return le nombre de pièces restantes sur l'échiquier
	 */
	public int getNbPiece()
	{
		return nbPiece;
	}


	/**
	 * Permet d'obtenir le score.
	 * @return le score
	 */
	public int getScore()
	{
		return score;
	}


	/**
	 * Permet d'obtenir le nombre de mouvements.
	 * @return le nombre de mouvements
	 */
	public int getNbMouvements()
	{
		return 0;
	}


	/**
	 * Permet d'afficher l'état de l'échiquier.
	 *
	 * @return l'état de l'échiquier
	 */
	public String toString()
	{
		String s = "";
		for ( int i = 0; i < ctrl.getNbLigne(); i++ )
		{
			for ( int j = 0; j < ctrl.getNbColonne(); j++ )
				s += echiquier[i][j] + " ";
			s += "\n";
		}

		return s;
	}
}
