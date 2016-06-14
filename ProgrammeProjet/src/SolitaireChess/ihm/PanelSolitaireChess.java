package SolitaireChess.ihm;


import SolitaireChess.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * SolitaireChess - Projet Tutoré
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */
public class PanelSolitaireChess extends JPanel
{
	private final int        TAILLE_IMG;
	private       Controleur ctrl;

	private int sourisXPressed;
	private int sourisYPressed;

	private int sourisXClicked;
	private int sourisYClicked;


	/**
	 * @param ctrl
	 */
	public PanelSolitaireChess( Controleur ctrl )
	{
		this.ctrl = ctrl;

		this.sourisXClicked = - 1;
		this.sourisYClicked = - 1;

		this.TAILLE_IMG = this.ctrl.getTailleImg();
		setPreferredSize( new Dimension( 400, 400 ) );

		GererSouris evtSouris = new GererSouris();
		addMouseListener( evtSouris );
		setFocusable( true );
	}


	/**
	 * @param g
	 */
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );

		Graphics2D g2 = (Graphics2D)g;

		String sImg;
		Image  img;

		sImg = this.ctrl.getImgFond();
		img = getToolkit().getImage( sImg );
		g2.drawImage( img, 0, 0, this );

		for ( int i = 0; i < this.ctrl.getNbLigne(); i++ )
			for ( int j = 0; j < this.ctrl.getNbColonne(); j++ )
			{
				sImg = this.ctrl.getImg( i, j );
				img = getToolkit().getImage( sImg );
				g2.drawImage( img, j * TAILLE_IMG, i * TAILLE_IMG, this );
			}
	}


	private class GererSouris extends MouseAdapter
	{
		public void mouseClicked( MouseEvent e )
		{
			if ( sourisXClicked >= 0 && sourisYClicked >= 0 )
			{
				try
				{
					ctrl.deplacer( sourisXClicked, sourisYClicked, e.getY() / TAILLE_IMG, e.getX() / TAILLE_IMG );

					sourisXClicked = - 1;
					sourisYClicked = - 1;
					System.out.println("Choisissez votre pièce");
				} catch ( Exception exc ) { System.out.println( "Evitez de sortir des limites, poto" );}

			}
			else if ( ctrl.contientPiece( e.getY() / TAILLE_IMG, e.getX() / TAILLE_IMG ) )
			{
				sourisXClicked = e.getY() / TAILLE_IMG;
				sourisYClicked = e.getX() / TAILLE_IMG;
				System.out.println("Choisissez la pièce à prendre");
			}
		}


		/**
		 * @param e
		 */
		public void mousePressed( MouseEvent e )
		{

			sourisXPressed = e.getY() / TAILLE_IMG;
			sourisYPressed = e.getX() / TAILLE_IMG;
		}


		/**
		 * @param e
		 */
		public void mouseReleased( MouseEvent e )
		{
			try
			{
				ctrl.deplacer( sourisXPressed, sourisYPressed, e.getY() / TAILLE_IMG, e.getX() / TAILLE_IMG );
			} catch ( Exception exc ) { System.out.println( "Evitez de sortir des limites, poto" );}

		}
	}
}
