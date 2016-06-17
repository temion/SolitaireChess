package SolitaireChess.ihm;

/**
 * SolitaireChess - Projet Tutoré
 * Classe IHM qui représente le plateau de création de niveau.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class PanelCreerNiveau extends JPanel
{
	private final int TAILLE_CASE = 100;

	private final int TAILLE_BORDURE = 2;

	private static final String[] NOMS_PIECES = { "Tour", "Cavalier", "Dame", "Roi", "Pion", "Fou"};

	private CreerNiveau creerNiveau;

	// Position du curseur lors d'un appui "drag"
	private int sourisXPressed;
	private int sourisYPressed;

	// Position du curseur lors d'un mouvement "drag"
	private int sourisXMoved;
	private int sourisYMoved;

	// Le thème utilisé pour l'éditeur
	private static final Color[] tabThemes = new Color[]{ new Color( 0x3D79FF ),
	                                                      new Color( 0x0D0418 ) };

	// Tableau représentant l'échiquier que le joueur veut créer
	private char[][] tabPiecesMises;

	// On ne peut mettre que ces pièces-ci, pas plus
	private char[][] tabPiecesAMettre = { { 'R', 'D', 'F', 'F', 'T' },
	                                      { 'T', 'C', 'C', 'P', 'P' } };


	/**
	 * Construit un nouveau plateau vide.
	 *
	 * @param creerNiveau la fenêtre parent
	 */
	public PanelCreerNiveau( CreerNiveau creerNiveau )
	{
		this.creerNiveau = creerNiveau;

		this.tabPiecesMises = new char[4][4];

		this.sourisXPressed = - 1;
		this.sourisYPressed = - 1;

		setPreferredSize( new Dimension( 500, 600 ) );  // Défini la taille du plateau

		setBackground( new Color( 0x3A054B ) );

		GererSouris    evtSouris    = new GererSouris();      // Gère les événement liés à la souris
		GererMvtSouris evtMvtSouris = new GererMvtSouris();

		addMouseMotionListener( evtMvtSouris );
		addMouseListener( evtSouris );
		setFocusable( true ); // Le plateau est focussable
	}


	/**
	 * Permet de redessiner totalement le plateau.
	 *
	 * @param g l'objet qui permet de dessiner le plateau
	 */
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );

		Graphics2D g2 = (Graphics2D)g;

		// On dessine le fond du plateau
		g2.setColor( Color.WHITE );
		g2.fillRect( 0, 0, 400, 400 );


		boolean bCase = true;
		Color   c;

		// On dessine les cases
		for ( int i = 0; i < 4; i++ )
			for ( int j = 0; j < 4; j++ )
			{
				if ( bCase )
					c = tabThemes[1];
				else
					c = tabThemes[0];

				int taille = TAILLE_CASE - ( TAILLE_BORDURE * 2 );

				g2.setColor( c );
				g2.fillRect( ( i * TAILLE_CASE ) + TAILLE_BORDURE, ( j * TAILLE_CASE ) +
																   TAILLE_BORDURE, taille, taille );

				if ( j != 3 )
					bCase = ! bCase;
			}


		// On dessine les pièces immobiles de l'échiquier
		String sImg;
		Image  img;
		for ( int i = 0; i < tabPiecesMises.length; i++ )
			for ( int j = 0; j < tabPiecesMises[i].length; j++ )
			{
				if ( tabPiecesMises[i][j] != '\u0000' )
				{
					sImg = "./images/theme02/" + getSymbole( tabPiecesMises[i][j] ) + ".png";
					img = getToolkit().getImage( sImg );

					g2.drawImage( img, j * TAILLE_CASE, i * TAILLE_CASE, this );
				}
			}

		// On dessine les pièces immobiles du tas de pièces à mettre
		for ( int i = 0; i < tabPiecesAMettre.length; i++ )
		{
			for ( int j = 0; j < tabPiecesAMettre[i].length; j++ )
			{
				if ( tabPiecesAMettre[i][j] != '\u0000' )
				{
					sImg = "./images/theme02/" + getSymbole( tabPiecesAMettre[i][j] ) + ".png";
					img = getToolkit().getImage( sImg );

					g2.drawImage( img, j * TAILLE_CASE, 400 + i * TAILLE_CASE, this );
				}
			}
		}

		// On dessine la pièce mobile choisie par l'utilisateur
		if ( sourisXPressed != - 1 && sourisYPressed != - 1 )
		{
			if ( sourisYPressed > 400 )
			{
				sImg = "./images/theme02/" + getSymbole(
						tabPiecesAMettre[sourisYPressed / TAILLE_CASE - 4][sourisXPressed /
																		   TAILLE_CASE] )
					   + ".png";
				img = getToolkit().getImage( sImg );

				g2.drawImage( img, sourisXMoved - ( TAILLE_CASE / 2 ),
							  sourisYMoved - ( TAILLE_CASE / 2 ),
							  this );
			}
			else if ( sourisXPressed <= 400 )
			{
				sImg = "./images/theme02/" + getSymbole(
						tabPiecesMises[sourisYPressed / TAILLE_CASE][sourisXPressed /
																	 TAILLE_CASE] )
					   + ".png";
				img = getToolkit().getImage( sImg );

				g2.drawImage( img, sourisXMoved - ( TAILLE_CASE / 2 ),
							  sourisYMoved - ( TAILLE_CASE / 2 ),
							  this );
			}
		}
	}

	/**
	 * Classe interne qui gère les déplacements de la souris.
	 */
	private class GererMvtSouris extends MouseMotionAdapter
	{
		/**
		 * Capture la position de la souris quand l'utilisateur fait un "drag".
		 *
		 * @param e événement lié au "drag"
		 */
		@Override
		public void mouseDragged( MouseEvent e )
		{
			sourisXMoved = e.getX();
			sourisYMoved = e.getY();
			repaint();
		}
	}


	/**
	 * Classe interne qui gère les événements liés à la souris.
	 */
	private class GererSouris extends MouseAdapter
	{
		/**
		 * Capture la position de la souris quand l'utilisateur a fait le clic de "drag".
		 *
		 * @param e événement lié au clic de "drag".
		 */
		@Override
		public void mousePressed( MouseEvent e )
		{
			sourisXPressed = e.getX();
			sourisYPressed = e.getY();
		}


		/**
		 * Place la case de l'échiquier au tas de pièces, ou place la pièce du tas dans l'échiquier
		 *
		 * @param e l'événement relié au "drop" du clic
		 */
		@Override
		public void mouseReleased( MouseEvent e )
		{
			// Si l'utilisateur souhaite mettre une pièce du tas dans l'échiquier
			if ( sourisYPressed > 400 && e.getX() <= 400 && e.getY() <= 400 &&
				 tabPiecesMises[e.getY() / TAILLE_CASE][e.getX() / TAILLE_CASE] == '\u0000' )
			{
				char piecePrise = tabPiecesAMettre[sourisYPressed / TAILLE_CASE - 4][sourisXPressed
																					 / TAILLE_CASE];
				tabPiecesAMettre[sourisYPressed / TAILLE_CASE - 4][sourisXPressed
																   / TAILLE_CASE] = '\u0000';

				tabPiecesMises[e.getY() / TAILLE_CASE][e.getX() / TAILLE_CASE] = piecePrise;
			}
			// S'il veut remettre une pièce de l'échiquier dans le tas
			else if ( peutReplacer( e ) )
			{
				char pieceARemettre = tabPiecesMises[sourisYPressed /
													 TAILLE_CASE][sourisXPressed /
																  TAILLE_CASE];

				tabPiecesMises[sourisYPressed / TAILLE_CASE][sourisXPressed / TAILLE_CASE] =
						'\u0000';

				tabPiecesAMettre[e.getY() / TAILLE_CASE - 4][e.getX() / TAILLE_CASE] =
						pieceARemettre;
			}

			// On réinitialise la valeur des boutons liés au clic "drag"
			sourisXPressed = - 1;
			sourisYPressed = - 1;
			repaint();
		}

		/**
		 * Vérifie si on peut remettre la piece dans le tas de pièces.
		 *
		 * @param e l'événement lié au "drag" de la souris
		 * @return si la pièce peut se replacer dans le tas
		 */
		public boolean peutReplacer( MouseEvent e )
		{
			//Vérifir si on peut remettre la piece dans le tas de pièces
			return sourisXPressed <= 400 && sourisYPressed <= 400 && e.getY() > 400 &&
				   tabPiecesMises[sourisYPressed / TAILLE_CASE][sourisXPressed / TAILLE_CASE] !=
				   '\u0000' &&
				   tabPiecesAMettre[e.getY() / TAILLE_CASE - 4][e.getX() / TAILLE_CASE] ==
				   '\u0000';
		}


	}


	/**
	 * Retourne une chaine représentant le nom d'une image en fonction d'un caractère
	 *
	 * @param c le caractère donné
	 * @return la chaine correspondant
	 */
	public String getSymbole( char c )
	{
		String s="";

		for(String s1:NOMS_PIECES)
			if(s1.charAt( 0 ) == c)
				s=s1;

		return s;
	}

	/**
	 * Renvoie l'échiquier créé par l'utilisateur
	 *
	 * @return le tableau de caractères correspondant à l'échiquier créé par l'utilisateur
	 */
	public char[][] getDefi()
	{
		return tabPiecesMises;
	}

}