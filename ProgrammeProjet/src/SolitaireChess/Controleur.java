package SolitaireChess;


import SolitaireChess.ihm.Accueil;
import SolitaireChess.ihm.Jeu;
import SolitaireChess.metier.Echiquier;
import SolitaireChess.metier.Joueur;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * SolitaireChess - Projet Tutoré
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */
public class Controleur implements Serializable
{
	private Accueil           accueil;
	private Echiquier         echiquier;
	private ArrayList<Joueur> alJoueur;
	private Joueur            joueurCourant;

	private Jeu jeu;


	public Controleur()
	{
		charger();
		this.echiquier = new Echiquier( this );
		this.accueil = new Accueil( this );
		this.joueurCourant = null;
		this.jeu = null;
	}


	public static void main( String[] arg )
	{
		new Controleur();
	}


	public void deplacer( int x1, int y1, int x2, int y2 )
	{
		if ( x2 >= 0 && x2 < getNbLigne() && y2 >= 0 &&
			 y2 < getNbColonne() )// On ne teste pas x1 ni y1 car ils
			// seront toujours
			// dans les limites
			// du panel
			if ( this.echiquier.getEchiquier()[x2][y2] != null && this.echiquier.deplacer( x1, y1,
																						   x2,
																						   y2 ) )
				majIHM();
	}


	public void charger()
	{
		try
		{
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream( "./sauvegardes.data" ) );
			alJoueur = (ArrayList<Joueur>)in.readObject();

			in.close();
		} catch ( Exception e ) {this.alJoueur = new ArrayList<>(); }
	}


	public void enregistrer()
	{
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream( "./sauvegardes.data" ) );
			out.writeObject( alJoueur );

			out.close();
		} catch ( Exception e ) {}
	}


	public void ajouterJoueur( String nomJoueur )
	{
		if ( ! alJoueur.contains( nomJoueur ) )
		{
			alJoueur.add( new Joueur( nomJoueur, this ) );
			joueurCourant = alJoueur.get( alJoueur.size() - 1 );
			enregistrer();
		}
		else
		{
			definirJoueur( nomJoueur );
		}
	}


	public boolean contientJoueur( String nomJoueur )
	{
		for ( Joueur j : alJoueur )
			if ( nomJoueur.equals( j.getNom() ) )
				return true;

		return false;
	}


	public void majIHM()
	{
		this.jeu.majIHM();
	}


	public String getImgFond()
	{
		return "./images/fond.png";
	}


	public String getImgCaseVert()    { return "./images/case_verte.png"; }


	public String getImgCaseBlanche() { return "./images/case_blanche.png"; }


	public String getImg( int i, int j )
	{
		String symbole = this.echiquier.getSymbole( i, j );

		return "./images/theme02/" + symbole + ".png";
	}


	public Jeu getJeu()
	{
		return jeu;
	}


	public void setJeu( Jeu jeu )
	{
		this.jeu = jeu;
	}


	public Echiquier getEchiquier()
	{
		return echiquier;
	}


	public int getTailleImg()
	{
		return 100;
	}


	public ArrayList<Joueur> getAlJoueur()
	{
		return alJoueur;
	}


	public int getNbLigne()
	{
		return echiquier.getNbLigne();
	}


	public int getNbColonne()
	{
		return echiquier.getNbColonne();
	}


	public Joueur getJoueurCourant()
	{
		return joueurCourant;
	}


	public void setJoueurCourant( Joueur joueur )
	{
		this.joueurCourant = joueur;
	}


	public void setFenetreJeu( Jeu jeu )
	{
		this.jeu = jeu;
	}


	public boolean contientPiece( int i, int j )
	{
		if ( i > - 1 && i < echiquier.getNbLigne() && j > - 1 && j < echiquier.getNbColonne() )
			return echiquier.getEchiquier()[i][j] != null;
		return false;
	}


	public void afficherInfosJoueur()
	{
		Object[] themes = { "theme 1", "theme 2" };
		JOptionPane.showInputDialog( jeu, "Joueur : " + joueurCourant.getNom() + "\n" +
										  "Dernier défi : " + joueurCourant.getDernierDefi()
												  [1] + "\n" +
										  "Score : " + joueurCourant.getScore() + "\n" +
										  "Thème :",
									 "Joueur",
									 JOptionPane.PLAIN_MESSAGE,
									 new ImageIcon( "./images/gandalf.png" ),
									 themes,
									 null
								   );
	}

	public int[] getDernierDefi()
	{
		return joueurCourant.getDernierDefi();
	}

	public void definirJoueur( String nomJoueur )
	{
		for ( Joueur j : alJoueur )
			if ( nomJoueur.equals( j.getNom() ) )
			{
				joueurCourant = j;
				enregistrer();
				return;
			}
	}


	public void supprimerJoueur( String nomJoueur )
	{
		for(Joueur j : alJoueur)
			if ( nomJoueur.equals( j.getNom() ) )
			{
				alJoueur.remove( j );
				enregistrer();
				return;
			}
	}


	public void afficherMessage( String message )
	{
		jeu.afficherMessage( message );
	}


	public void afficherMessageErreur( String messageErreur )
	{
		jeu.afficherMessageErreur( messageErreur );
	}

	public void annuler()
	{
		echiquier.annuler();
	}
}