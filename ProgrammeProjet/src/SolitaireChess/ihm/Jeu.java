package SolitaireChess.ihm; /**
 * SolitaireChess - Projet Tutoré
 * Classe ihm de l'écran du jeu
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import SolitaireChess.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Jeu extends JFrame implements ActionListener
{
	private Controleur ctrl;

	private PanelSolitaireChess plateau;

	private JButton annuler;
	private JButton recommencer;
	private JButton menuPrincipal;

	private JLabel niveau;
	private JLabel defi;
	private JLabel mouvements;

	private static final String[] TAB_NOM_NIVEAU = new String[] {"Débutant", "Intermédiaire", "Avancé",
													"Expert"};

	/**
	 * Construit l'écran du jeu.
	 *
	 * @param ctrl la classe qui fera le lien entre la classe et la partie métier
	 */
	Jeu( Controleur ctrl )
	{
		setTitle( "Jeu" );
		setLocation( 200, 200 );

		this.ctrl = ctrl;

		ctrl.setFenetreJeu( this );
		ctrl.getEchiquier().setDefi();

		setJMenuBar( new BarreMenu( this ) );

		add( new BarreAction( this ), "North" );

		initPanelUtilitaire();

		this.plateau = new PanelSolitaireChess( ctrl );
		add( plateau );

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		pack();
		setLocationRelativeTo( null );
		setResizable( false );
		setVisible( true );

	}


	/**
	 * Ajoute un panel contenant beaucoup de composants à l'écran du jeu.
	 */
	private void initPanelUtilitaire()
	{
		JPanel pUtilitaire = new JPanel( new GridLayout( 6, 1, 0, 2 ) );

		pUtilitaire.add(
				defi = new JLabel( "Défi n° " + ctrl.getEchiquier().getDefi(), JLabel.CENTER ) );

		pUtilitaire.add( niveau = new JLabel( "Niveau : " + niveauToString(),
		                                      JLabel.CENTER ) );

		pUtilitaire.add(
				mouvements = new JLabel( "Mouvements : " + ctrl.getJoueurCourant()
						.getNbMouvements(), JLabel.CENTER ) );

		annuler = new JButton(new ImageIcon( "./images/annuler.png" ));
		annuler.addActionListener( this );
		pUtilitaire.add( annuler );

		recommencer = new JButton( new ImageIcon( "./images/recommencer.png" ) );
		recommencer.addActionListener( this );
		pUtilitaire.add( recommencer );

		menuPrincipal = new JButton( new ImageIcon( "./images/menuPrincipal.png" ) );
		menuPrincipal.addActionListener( this );
		pUtilitaire.add( menuPrincipal );

		add( pUtilitaire, "East" );
	}


	/**
	 * Met à jour la représentation ihm de l'échiquier.
	 */
	public void majIHM()
	{
		plateau.repaint();
		majPanel();
	}


	/**
	 * Met à jour l'affichage du défi, du niveau et du score.
	 */
	public void majPanel()
	{
		defi.setText( "Défi n° " + ctrl.getEchiquier().getDefi() );
		niveau.setText( "Niveau " + niveauToString() );
		mouvements.setText( "Mouvements : " + ctrl.getJoueurCourant().getNbMouvements() );
	}

	private String niveauToString()
	{
		return Jeu.TAB_NOM_NIVEAU[ctrl.getEchiquier().getNiveau() -1];
	}


	public void afficherMessage( String message )
	{
		JOptionPane.showMessageDialog( this, message, "Bon à savoir !", JOptionPane.PLAIN_MESSAGE );
	}


	public void afficherMessageErreur( String messageErreur )
	{
		JOptionPane.showMessageDialog( this, messageErreur, "Bon à savoir !", JOptionPane
				.ERROR_MESSAGE );
	}


	public Controleur getCtrl()
	{
		return ctrl;
	}


	/**
	 * Gère les appuis sur les boutons du composant.
	 *
	 * @param e un événement lié à l'appui sur l'un des boutons.
	 */
	@Override
	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() == annuler )
		{
			ctrl.annuler();
			majIHM();
		}

		else if( e.getSource() == recommencer )
		{
			ctrl.getEchiquier().recommencer();
		}

		else if ( e.getSource() == menuPrincipal )
		{
			if ( JOptionPane.showConfirmDialog(
					this,
					"Voulez-vous vraiment revenir au menu principal ?",
					"Question",
					JOptionPane.YES_NO_OPTION ) == 0 )
			{
				new Accueil( ctrl );
				dispose();
			}

		}
	}

	public PanelSolitaireChess getPlateau()
	{
		return plateau;
	}
}