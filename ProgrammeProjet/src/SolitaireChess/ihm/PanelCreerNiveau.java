package SolitaireChess.ihm;

/**
 * SolitaireChess - Projet Tutoré
 * Classe IHM qui représente le plateau de création de niveau.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import SolitaireChess.Controleur;
import SolitaireChess.interfaces.IPieceEchec;

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

	private int sourisXPressed;
	private int sourisYPressed;

	private int sourisXMoved;
	private int sourisYMoved;

	private static final Color[] tabThemes = new Color[]{ new Color( 0x3D79FF ),
														  new Color( 0x0D0418 ) };

	private char[][] tabPiecesMises;

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

		g2.setColor( Color.WHITE );
		g2.fillRect( 0, 0, 400, 400 );

		// On défini le fond du plateau
		boolean bCase = true;
		Color   c;

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
	 * Retourne le négatif de la couleur passée en paramètre.
	 *
	 * @param c couleur à transformer
	 * @return nouvelle couleur
	 */
	public Color negatifVide( Color c )
	{
		return new Color( 255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue(), 100 );
	}

	/**
	 * Retourne le négatif de la couleur passée en paramètre.
	 *
	 * @param c couleur à transformer
	 * @return nouvelle couleur
	 */
	public Color negatifPlein( Color c )
	{
		return new Color( 255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue(), 200 );
	}


	/**
	 * Classe interne qui gère les déplacements de la souris.
	 */
	private class GererMvtSouris extends MouseMotionAdapter
	{
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
		@Override
		public void mousePressed( MouseEvent e )
		{
			sourisXPressed = e.getX();
			sourisYPressed = e.getY();
		}


		@Override
		public void mouseReleased( MouseEvent e )
		{
			if ( sourisYPressed > 400 && e.getX() <= 400 && e.getY() <= 400 &&
				 tabPiecesMises[e.getY() / TAILLE_CASE][e.getX() / TAILLE_CASE] == '\u0000' )
			{
				char piecePrise = tabPiecesAMettre[sourisYPressed / TAILLE_CASE - 4][sourisXPressed
																					 / TAILLE_CASE];
				tabPiecesAMettre[sourisYPressed / TAILLE_CASE - 4][sourisXPressed
																   / TAILLE_CASE] = '\u0000';

				tabPiecesMises[e.getY() / TAILLE_CASE][e.getX() / TAILLE_CASE] = piecePrise;
				System.out.println( piecePrise );
			}
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


			sourisXPressed = - 1;
			sourisYPressed = - 1;
			repaint();
		}


		public boolean peutReplacer( MouseEvent e )
		{
			return sourisXPressed <= 400 && sourisYPressed <= 400 && e.getY() > 400 &&
				   tabPiecesMises[sourisYPressed / TAILLE_CASE][sourisXPressed / TAILLE_CASE] !=
				   '\u0000' &&
				   tabPiecesAMettre[e.getY() / TAILLE_CASE - 4][e.getX() / TAILLE_CASE] ==
				   '\u0000';
		}


	}


	public String getSymbole( char c )
	{
		String s="";

		for(String s1:NOMS_PIECES)
			if(s1.charAt( 0 ) == c)
				s=s1;

		return s;
	}

	public char[][] getNiveau()
	{
		return tabPiecesMises;
	}

}