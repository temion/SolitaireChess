package SolitaireChess.ihm;


import SolitaireChess.Controleur;
import SolitaireChess.interfaces.IPieceEchec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import java.util.ArrayList;

/**
 * SolitaireChess - Projet Tutoré
 * Classe ihm qui représente le plateau de jeu.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */
public class PanelSolitaireChess extends JPanel
{
	private final int TAILLE_IMG;

	private Controleur ctrl;

	private int sourisXPressed;
	private int sourisYPressed;

	private int sourisXMoved;
	private int sourisYMoved;

	private int sourisXClicked;
	private int sourisYClicked;


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

		this.TAILLE_IMG = this.ctrl.getTailleImg();     // Défini la taille des images

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

		String sImg;
		Image  img;

		// On défini le fond du plateau
		boolean bCase = true;

		for ( int i = 0; i < ctrl.getNbLigne(); i++ )
			for ( int j = 0; j < ctrl.getNbColonne(); j++ )
			{
				if ( bCase )
					sImg = ctrl.getImgCaseVert();
				else
					sImg = ctrl.getImgCaseBlanche();

				img = getToolkit().getImage( sImg );
				g2.drawImage( img, i * TAILLE_IMG, j * TAILLE_IMG, this );

				if ( j != ctrl.getNbColonne() - 1 )
					bCase = ! bCase;
			}


		if ( sourisXPressed > - 1 && sourisYPressed > - 1 ||
			 sourisXClicked > - 1 && sourisYClicked > - 1 )
		{
			IPieceEchec p = null;

			if ( sourisXPressed > - 1 )
				p = ctrl.getEchiquier().getEchiquier()[sourisXPressed][sourisYPressed];
			else if ( sourisXClicked > - 1 )
				p = ctrl.getEchiquier().getEchiquier()[sourisXClicked][sourisYClicked];

			if ( p != null )
			{
				ArrayList<Point> alCoordEchec = p.getDeplacementEchec();
				//ArrayList<Point> alCoordSC = p.getDeplacementSC();

				for ( Point point : p.getDeplacementEchec() )
				{
					g2.setColor( new Color( 255, 0, 5, 170 ) );
					g2.fillRect( ( (int)point.getX() * TAILLE_IMG ) + 1,
								 ( (int)point.getY() * TAILLE_IMG ) + 1,
								 TAILLE_IMG - 2,
								 TAILLE_IMG - 2 );
				}
			}
		}

		// On place les graphiquement les pièces sur le plateau
		for ( int i = 0; i < this.ctrl.getNbLigne(); i++ )
			for ( int j = 0; j < this.ctrl.getNbColonne(); j++ )
			{
				if ( i == sourisXPressed && j == sourisYPressed )
				{
					sImg = this.ctrl.getImg( sourisXPressed, sourisYPressed );
					img = getToolkit().getImage( sImg );
					g2.drawImage( img, sourisXMoved - ( TAILLE_IMG / 2 ),
								  sourisYMoved - ( TAILLE_IMG / 2 ),
								  this );
				}
				else
				{
					sImg = this.ctrl.getImg( i, j );
					img = getToolkit().getImage( sImg );
					g2.drawImage( img, j * TAILLE_IMG, i * TAILLE_IMG, this );
				}
			}
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
					ctrl.deplacer( sourisXClicked, sourisYClicked, e.getY() / TAILLE_IMG,
								   e.getX() / TAILLE_IMG );

					sourisXClicked = - 1;
					sourisYClicked = - 1;
					ctrl.afficherMessage( "Choisissez votre pièce" );
				} catch ( Exception exc )
				{
					ctrl.afficherMessageErreur( "Evitez de sortir des limites" );
				}
			}
			else if ( ctrl.contientPiece( e.getY() / TAILLE_IMG, e.getX() / TAILLE_IMG ) )
			{
				sourisXClicked = e.getY() / TAILLE_IMG;
				sourisYClicked = e.getX() / TAILLE_IMG;
				ctrl.afficherMessage( "Choisissez la pièce à prendre" );
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
			sourisXPressed = e.getY() / TAILLE_IMG;
			sourisYPressed = e.getX() / TAILLE_IMG;
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
				ctrl.deplacer( sourisXPressed, sourisYPressed, e.getY() / TAILLE_IMG,
							   e.getX() / TAILLE_IMG );

				sourisXPressed = - 1;
				sourisYPressed = - 1;

				repaint();
			} catch ( Exception exe )
			{
				ctrl.afficherMessageErreur( "Evitez de sortir des limites" );
			}
		}
	}
}