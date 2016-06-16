package SolitaireChess.ihm;


import SolitaireChess.Controleur;
import SolitaireChess.interfaces.IPieceEchec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * SolitaireChess - Projet Tutoré
 * Classe ihm qui représente le plateau de jeu.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */
public class PanelSolitaireChess extends JPanel
{
	private final int TAILLE_CASE;

	private final int TAILLE_BORDURE = 2;

	private Controleur ctrl;

	private int sourisXPressed;
	private int sourisYPressed;

	private int sourisXMoved;
	private int sourisYMoved;

	private int sourisXClicked;
	private int sourisYClicked;

	private static final Color[][][] tabThemes = new Color[][][]{ { { new Color( 0x34B618 ),
																	  new Color( 0xFFDF50 ),
																	  new Color( 0xBF1C02 ),
																	  new Color( 0x9C000F ) },
																	{ new Color( 0xF0FFEB ) } },

																  { { new Color( 0x4BFF9D ),
																	  new Color( 0x3D79FF ),
																	  new Color( 0xBC9EFF ),
																	  new Color( 255, 0, 250 ) },
																	{ new Color( 0x0D0418 ) } },

																  { { new Color( 0xFFB8EE ),
																	  new Color( 0xFD87FF ),
																	  new Color( 0xFC50FF ),
																	  new Color( 248, 0, 255 ) },
																	{ new Color( 0x000000 ) } } };


	/**
	 * Construit le plateau de jeu des images.
	 *
	 * @param ctrl le contrôleur qui gère la fenêtre
	 */
	public PanelSolitaireChess( Controleur ctrl )
	{
		this.ctrl = ctrl;

		this.sourisXClicked = - 1;
		this.sourisYClicked = - 1;

		this.sourisXPressed = - 1;
		this.sourisYPressed = - 1;

		this.TAILLE_CASE = this.ctrl.getTailleImg();     // Défini la taille des images

		setPreferredSize( new Dimension( 400, 400 ) );  // Défini la taille du plateau

		GererSouris    evtSouris    = new GererSouris();      // Gère les événement liés à la souris
		GererMvtSouris evtMvtSouris = new GererMvtSouris();

		addMouseMotionListener( evtMvtSouris );
		addMouseListener( evtSouris );                  // sur le plateau
		setFocusable( true );                           // Le plateau est focussable
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

		g2.setColor( Color.BLACK );
		g2.fillRect( 0, 0, TAILLE_CASE * ctrl.getNbLigne(), TAILLE_CASE * ctrl.getNbColonne() );

		// On défini le fond du plateau
		boolean bCase = true;
		Color   c;

		for ( int i = 0; i < ctrl.getNbLigne(); i++ )
			for ( int j = 0; j < ctrl.getNbColonne(); j++ )
			{
				if ( bCase )
					c = tabThemes[ctrl.getTheme() - 1][1][0];
				else
					c = tabThemes[ctrl.getTheme() - 1][0][ctrl.getDernierDefi()[0] - 1];

				int taille = TAILLE_CASE - ( TAILLE_BORDURE * 2 );

				g2.setColor( c );
				g2.fillRect( ( i * TAILLE_CASE ) + TAILLE_BORDURE, ( j * TAILLE_CASE ) +
																   TAILLE_BORDURE, taille, taille );

				if ( j != ctrl.getNbColonne() - 1 )
					bCase = ! bCase;
			}


		if ( sourisXPressed > - 1 && sourisYPressed > - 1 ||
			 sourisXClicked > - 1 && sourisYClicked > - 1 )
		{
			IPieceEchec p = null;

			if ( sourisXPressed > - 1 && sourisXPressed < ctrl.getNbLigne() )
				p = ctrl.getEchiquier().getEchiquier()[sourisXPressed][sourisYPressed];
			else if ( sourisXClicked > - 1 && sourisXClicked < ctrl.getNbLigne() )
				p = ctrl.getEchiquier().getEchiquier()[sourisXClicked][sourisYClicked];

			if ( p != null )
			{
				for ( Point point : p.getDeplacementEchec() )
				{
					if ( ctrl.getEchiquier().getEchiquier()[(int)point.getY()][(int)point.getX()]
						 ==
						 null )
					{
						g2.setColor( negatifVide( tabThemes[0][1][0] ) );
						g2.fillRect( ( (int)point.getX() * TAILLE_CASE ) + TAILLE_BORDURE,
									 ( (int)point.getY() * TAILLE_CASE ) + TAILLE_BORDURE,
									 TAILLE_CASE - ( TAILLE_BORDURE * 2 ),
									 TAILLE_CASE - ( TAILLE_BORDURE * 2 ) );
					}
					else
					{
						//On ajoute un négatif qui fait un bon contraste
						g2.setColor(
								negatifPlein( tabThemes[0][0][ctrl.getDernierDefi()[0] - 1] ) );
						g2.fillRect( ( (int)point.getX() * TAILLE_CASE ) + TAILLE_BORDURE,
									 ( (int)point.getY() * TAILLE_CASE ) + TAILLE_BORDURE,
									 TAILLE_CASE - ( TAILLE_BORDURE * 2 ),
									 TAILLE_CASE - ( TAILLE_BORDURE * 2 ) );
					}

				}
			}
		}

		String sImg;
		Image  img;
		// On place graphiquement les pièces sur le plateau
		for ( int i = 0; i < this.ctrl.getNbLigne(); i++ )
			for ( int j = 0; j < this.ctrl.getNbColonne(); j++ )
			{
				if ( i != sourisXPressed ||
					 j != sourisYPressed ) //Positionne la pièce sur la souris
				{
					sImg = this.ctrl.getImg( i, j );
					img = getToolkit().getImage( sImg );
					g2.drawImage( img, j * TAILLE_CASE, i * TAILLE_CASE, this );
				}
			}

		//Positionne la pièce sur la souris, devant toutes les autres pièces
		if ( sourisXPressed > - 1 && sourisYPressed > - 1 )
		{
			sImg = this.ctrl.getImg( sourisXPressed, sourisYPressed );
			img = getToolkit().getImage( sImg );
			g2.drawImage( img, sourisXMoved - ( TAILLE_CASE / 2 ),
						  sourisYMoved - ( TAILLE_CASE / 2 ),
						  this );
		}
	}


	public Color negatifVide( Color c )
	{
		return new Color( 255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue(), 100 );
	}


	public Color negatifPlein( Color c )
	{
		return new Color( 255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue(), 200 );
	}


	private class GererMvtSouris extends MouseMotionAdapter
	{
		public void mouseDragged( MouseEvent e )
		{
			sourisXMoved = e.getX();
			sourisYMoved = e.getY();
			repaint();
		}
	}

	/**
	 * Classe interne qui gère les événements liés à  la souris.
	 */
	private class GererSouris extends MouseAdapter
	{
		public void mouseClicked( MouseEvent e )
		{
			if ( sourisXClicked >= 0 && sourisYClicked >= 0 )
			{
				try
				{
					ctrl.deplacer( sourisXClicked, sourisYClicked, e.getY() / TAILLE_CASE,
								   e.getX() / TAILLE_CASE );

					sourisXClicked = - 1;
					sourisYClicked = - 1;
					//ctrl.afficherMessage( "Choisissez votre pièce" );
				} catch ( Exception exc )
				{
					ctrl.afficherMessageErreur( "Evitez de sortir des limites" );
				}
			}
			else if ( ctrl.contientPiece( e.getY() / TAILLE_CASE, e.getX() / TAILLE_CASE ) )
			{
				sourisXClicked = e.getY() / TAILLE_CASE;
				sourisYClicked = e.getX() / TAILLE_CASE;
				//ctrl.afficherMessage( "Choisissez la pièce à prendre" );
			}
		}


		/**
		 * Si on clique sur la souris et qu'on reste appuyé.
		 *
		 * @param e l'évenement lié à la souris
		 */
		public void mousePressed( MouseEvent e )
		{
			// On capture les coordonnées de la pièce sur laquelle on a cliqué
			sourisXPressed = e.getY() / TAILLE_CASE;
			sourisYPressed = e.getX() / TAILLE_CASE;
		}


		/**
		 * Si on a cliqué sur la souris et qu'on relache le clic.
		 *
		 * @param e l'évenement lié à la souris
		 */
		public void mouseReleased( MouseEvent e )
		{
			// Dès qu'on relache la souris, on déplace la pièce sur laquelle on a cliqué vers
			// la pièce sur laquelle on vient de relacher la souris
			try
			{
				ctrl.deplacer( sourisXPressed, sourisYPressed, e.getY() / TAILLE_CASE,
							   e.getX() / TAILLE_CASE );

				sourisXPressed = - 1;
				sourisYPressed = - 1;

				repaint();
			} catch ( Exception exe )
			{
				ctrl.afficherMessageErreur( "Evitez de sortir des limites" );
			}
		}
	}


	public int getNbThemes()
	{
		return tabThemes.length;
	}
}