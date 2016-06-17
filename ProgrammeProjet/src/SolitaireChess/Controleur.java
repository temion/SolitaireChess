package SolitaireChess;

/**
 * SolitaireChess - Projet Tutoré
 * Classe passerelle entre le métier et l'IHM.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import SolitaireChess.ihm.Accueil;
import SolitaireChess.ihm.Jeu;
import SolitaireChess.metier.Echiquier;
import SolitaireChess.metier.Joueur;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Controleur implements Serializable
{
	private Accueil           accueil;
	private Echiquier         echiquier;
	private ArrayList<Joueur> alJoueur;
	private Joueur            joueurCourant;
	private Jeu               jeu;


	/**
	 * Construit un nouveau controleur.
	 */
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


	/**
	 * Déplace une pièce.
	 *
	 * @param x1 position horizontale de la pièce à déplacer
	 * @param y1 position verticale de la pièce à déplacer
	 * @param x2 nouvelle position horizontale de la pièce
	 * @param y2 nouvelle position verticale de la pièce
	 */
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


	/**
	 * Charge tous les profils existants.
	 */
	public void charger()
	{
		try
		{
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream( "./sauvegardes.data" ) );
			alJoueur = (ArrayList<Joueur>)in.readObject();

			in.close();
		} catch ( Exception e )
		{
			this.alJoueur = new ArrayList<>();
		}
	}


	/**
	 * Écrase la sauvegarde existante des profils.
	 */
	public void enregistrer()
	{
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream( "./sauvegardes.data" ) );
			out.writeObject( alJoueur );

			out.close();
		} catch ( Exception e )
		{
		}
	}


	/**
	 * Crée et ajoute à la liste de profils existants un nouveau profil avec le nom entré par l'utilisateur.
	 * Appelle ensuite la méthode enregistrer.
	 *
	 * @param nomJoueur nom du profil à créer
	 * @see Controleur#enregistrer()
	 */
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


	/**
	 * Met à jour la fenêtre de jeu.
	 *
	 * @see SolitaireChess.ihm.Jeu#majIHM()
	 */
	public void majIHM()
	{
		this.jeu.majIHM();
	}


	/**
	 * Retourne le chemin de l'image correspondant à la pièce dont les indices sont passées en paramètre en fonction du
	 * thème actuel.
	 *
	 * @param i premier indice de la pièce dans l'échiquier
	 * @param j deuxième indice de la pièce dans l'échiquier
	 * @return chemin de l'image correspondant à la pièce
	 */
	public String getImg( int i, int j )
	{
		String symbole = this.echiquier.getSymbole( i, j );
		return "./images/theme" + String.format( "%02d",
												 joueurCourant.getTheme() ) + "/" + symbole + "" +
			   ".png";
	}


	/**
	 * Retourne le jeu associé à ce controleur.
	 *
	 * @return jeu associée à ce controleur
	 */
	public Jeu getJeu()
	{
		return jeu;
	}


	/**
	 * Redéfinis le jeu associé à ce controleur.
	 *
	 * @param jeu nouveau jeu à associer au controleur
	 */
	public void setJeu( Jeu jeu )
	{
		this.jeu = jeu;
	}


	/**
	 * Retourne l'échiquier associé à ce controleur.
	 *
	 * @return échiquier associé à ce controleur
	 */
	public Echiquier getEchiquier()
	{
		return echiquier;
	}


	/**
	 * Retourne la taille des images composants l'échiquier.
	 *
	 * @return taille des images
	 */
	public int getTailleImg()
	{
		return 100;
	}


	/**
	 * Retourne la liste des profils existants.
	 *
	 * @return liste des profils existants
	 */
	public ArrayList<Joueur> getAlJoueur()
	{
		return alJoueur;
	}


	/**
	 * Retourne le nombre de lignes de l'échiquier associé à ce controleur.
	 *
	 * @return nombre de lignes de l'échiquier
	 */
	public int getNbLigne()
	{
		return echiquier.getNbLigne();
	}


	/**
	 * Retourne le nombre de colonnes de l'échiquier associé à ce controleur.
	 *
	 * @return nombre de colonnes de l'échiquier
	 */
	public int getNbColonne()
	{
		return echiquier.getNbColonne();
	}


	/**
	 * Retourne le joueur courant.
	 *
	 * @return joueur courant
	 */
	public Joueur getJoueurCourant()
	{
		return joueurCourant;
	}


	/**
	 * Redéfinis le joueur courant.
	 *
	 * @param joueur nouveau joueur courant
	 */
	public void setJoueurCourant( Joueur joueur )
	{
		this.joueurCourant = joueur;
	}


	/**
	 * Redéfinis la fenêtre de jeu.
	 *
	 * @param jeu nouvelle fenêtre de jeu
	 */
	public void setFenetreJeu( Jeu jeu )
	{
		this.jeu = jeu;
	}


	/**
	 * Indique si la case d'échiquier dont les indices sont passés en paramètre contient une pièce.
	 *
	 * @param i premier indice de la case dans l'échiquier
	 * @param j deuxième indice de la case dans l'échiquier
	 * @return état de la case (<b>true</b> indique que la case contient une pièce)
	 */
	public boolean contientPiece( int i, int j )
	{
		return i > - 1 && i < echiquier.getNbLigne() && j > - 1 && j < echiquier.getNbColonne
				() && echiquier.getEchiquier()[i][j] != null;
	}


	/**
	 * Affiche une boîte de dialogue fournissant différentes informations concernant le joueur courant.
	 */
	public void afficherInfosJoueur()
	{
		Object[] themes = new Object[jeu.getPlateau().getNbThemes()];
		for ( int i = 1; i <= themes.length; i++ )
		{
			themes[i - 1] = "theme " + i;
		}

		Object o = JOptionPane.showInputDialog( jeu, "Joueur : " + joueurCourant.getNom() + "\n" +
													 "Dernier défi : " +
													 joueurCourant.getDernierDefi()
															 [1] + "\n" +
													 "Mouvements : " +
													 joueurCourant.getNbMouvements() +
													 "\n" +
													 "Thème :",
												"Joueur",
												JOptionPane.PLAIN_MESSAGE,
												new ImageIcon( "./images/gandalf.png" ),
												themes,
												null
											  );

		joueurCourant.setTheme( Integer.parseInt( ( (String)( o ) ).replaceAll( "[^0-9]", "" ) ) );
		majIHM();
	}


	/**
	 * Affiche une boîte de dialogue fournissant différentes informations concernant le joueur courant.
	 */
	public void afficherInfosJoueur( String nomJoueur )
	{
		Joueur joueur = null;

		for ( Joueur j : alJoueur )
			if ( nomJoueur.equals( j.getNom() ) )
				joueur = j;

		JOptionPane.showMessageDialog( jeu, "Joueur : " + joueur.getNom() + "\n" +
											"Dernier défi : " +
											joueur.getDernierDefi()
													[1] + "\n" +
											"Mouvements : " +
											joueur.getNbMouvements() +
											"\n",
									   "Joueur",
									   JOptionPane.PLAIN_MESSAGE,
									   new ImageIcon( "./images/gandalf.png" )
									 );
	}


	/**
	 * Retourne le dernier défi que le joueur courant a atteint.
	 *
	 * @return dernier défi atteint par le joueur courant
	 */
	public int[] getDernierDefi()
	{
		return joueurCourant.getDernierDefi();
	}


	/**
	 * Définis le joueur courant à partir du nom passé en paramètre.
	 *
	 * @param nomJoueur nom du joueur
	 */
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


	/**
	 * Retourne le thème choisi par le joueur.
	 *
	 * @return thème choisi par le joueur
	 */
	public int getTheme()
	{
		return joueurCourant.getTheme();
	}


	/**
	 * Supprime le joueur dont le nom est passé en paramètre.
	 *
	 * @param nomJoueur nom du joueur à supprimer
	 */
	public void supprimerJoueur( String nomJoueur )
	{
		for ( Joueur j : alJoueur )
			if ( nomJoueur.equals( j.getNom() ) )
			{
				alJoueur.remove( j );
				enregistrer();
				return;
			}
	}


	/**
	 * Affiche le message passé en paramètre dans une boîte d'informations.
	 *
	 * @param message message à afficher
	 * @see SolitaireChess.ihm.Jeu#afficherMessage(String message)
	 */
	public void afficherMessage( String message )
	{
		jeu.afficherMessage( message );
	}


	/**
	 * Affiche le message passé en paramètre dans une boîte d'erreur.
	 *
	 * @param messageErreur message à afficher
	 * @see SolitaireChess.ihm.Jeu#afficherMessageErreur(String messageErreur)
	 */
	public void afficherMessageErreur( String messageErreur )
	{
		jeu.afficherMessageErreur( messageErreur );
	}


	/**
	 * Annule le dernier coup effectué.
	 *
	 * @see SolitaireChess.metier.Echiquier#annuler()
	 */
	public void annuler()
	{
		echiquier.annuler();
	}


	/**
	 * Recommence le niveau actuel.
	 *
	 * @see SolitaireChess.metier.Echiquier#recommencer()
	 */
	public void recommencer() { echiquier.recommencer(); }


	/**
	 * Retourne le nombre de défis accomplis par le joueur courant dans la difficulté rentrée en paramètre.
	 *
	 * @param i difficulté (niveau)
	 * @return nombre de défis accomplis dans la difficulté rentrée en paramètre
	 */
	public int getNbDefisAccomplis( int i )
	{
		int nbDefisReussis = 0;

		for ( boolean b : joueurCourant.getDefisAccomplis( i ) )
			if ( b )
				nbDefisReussis++;

		return nbDefisReussis;
	}


	/**
	 * Ouvre le navigateur web pour afficher les règles du jeu
	 * sur une page html.
	 */
	public void afficherRegles()
	{
		try
		{
			if ( Desktop.isDesktopSupported() )
			{
				Desktop.getDesktop().browse( new File( "./regles/index.html" ).toURI() );
			}
		} catch ( Exception exe ) {}
	}


	/**
	 * Ouvre une boite de dialogue qui demande confirmation à l'utilisateur avant de quitter
	 * la fenêtre.
	 */
	public void quitter()
	{
		if ( JOptionPane.showConfirmDialog( accueil.isActive() ? accueil : jeu, "Voulez-vous " +
																				"vraiment quitter ?",
											"Quitter le jeu",
											JOptionPane.YES_NO_OPTION ) == 0 )
		{
			System.exit( 0 );
		}
	}
}