package SolitaireChess.ihm;

/**
 * SolitaireChess - Projet Tutoré
 * Classe IHM de la barre de menus de la fenêtre de jeu.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

public class BarreMenu extends JMenuBar implements ActionListener
{
	private JMenuItem enregistrer;
	private JMenuItem quitter;

	private JMenuItem recommencer;
	private JMenuItem annuler;

	private JMenuItem choixDefi;
	private JMenuItem creerDefi;

	private JMenuItem aProposJoueur;
	private JMenuItem aProposJeu;
	private JMenuItem regles;

	private Jeu fenetre;


	/**
	 * Construit une nouvelle barre de menus.
	 *
	 * @param fenetre fenêtre sur laquelle sera placée la barre de menus
	 */
	public BarreMenu( Jeu fenetre )
	{
		this.fenetre = fenetre;

		initComposants();
	}


	/**
	 * Crée les différents menus de la barre, et leur affecte des raccourcis.
	 */
	private void initComposants()
	{
		JMenu fichier = new JMenu( "Fichier" );

		fichier.add( enregistrer = new JMenuItem( "Enregistrer" ) );
		fichier.addSeparator();
		fichier.add( quitter = new JMenuItem( "Quitter" ) );

		enregistrer.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_S, KeyEvent.CTRL_MASK ) );
		quitter.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_Q, KeyEvent.CTRL_MASK ) );

		enregistrer.addActionListener( this );
		quitter.addActionListener( this );

		add( fichier );


		JMenu jeu = new JMenu( "Jeu" );

		jeu.add( recommencer = new JMenuItem( "Recommencer" ) );
		jeu.add( annuler = new JMenuItem( "Annuler" ) );

		recommencer.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_R, KeyEvent.CTRL_MASK ) );
		annuler.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_Z, KeyEvent.CTRL_MASK ) );

		recommencer.addActionListener( this );
		annuler.addActionListener( this );

		add( jeu );


		JMenu niveau = new JMenu( "Niveau" );

		niveau.add( choixDefi = new JMenuItem( "Choisir un défi" ) );
		niveau.add( creerDefi = new JMenuItem( "Créer votre propre défi !" ) );

		choixDefi.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_O, KeyEvent.CTRL_MASK ) );
		creerDefi.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_N, KeyEvent.CTRL_MASK ) );

		choixDefi.addActionListener( this );
		creerDefi.addActionListener( this );

		add( niveau );


		JMenu aide = new JMenu( "Aide" );

		aide.add( aProposJoueur = new JMenuItem( "Infos sur le joueur" ) );
		aide.add( aProposJeu = new JMenuItem( "Infos sur le jeu" ) );
		aide.add( regles = new JMenuItem( "Règles du jeu" ) );

		aProposJoueur.addActionListener( this );
		aProposJeu.addActionListener( this );
		regles.addActionListener( this );

		add( aide );
	}


	/**
	 * Retourne la fenêtre auquel appartient la barre de menus courante.
	 *
	 * @return fenêtre auquel appartient la barre de menus courante
	 */
	public Jeu getFenetre()
	{
		return fenetre;
	}


	/**
	 * Redéfinis la fenêtre sur laquelle est placé la barre de menus courante.
	 *
	 * @param fenetre nouvelle fenêtre
	 */
	public void setFenetre( Jeu fenetre )
	{
		this.fenetre = fenetre;
	}


	/**
	 * Gère les appuis sur les boutons du composant.
	 *
	 * @param e un événement lié à l'appui sur l'un des boutons
	 */
	@Override
	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == enregistrer ) fenetre.getCtrl().enregistrer();

		else if ( e.getSource() == quitter )
		{
			if ( JOptionPane.showConfirmDialog( this, "Voulez-vous vraiment quitter ?",
												"Quitter le jeu",
												JOptionPane.YES_NO_OPTION ) == 0 )
			{
				System.exit( 0 );
			}
		}

		else if ( e.getSource() == recommencer ) { fenetre.getCtrl().recommencer(); }

		else if ( e.getSource() == annuler ) { fenetre.getCtrl().annuler(); }

		else if ( e.getSource() == choixDefi ) {new ChoixNiveau( fenetre );}

		else if ( e.getSource() == creerDefi ) {new CreerNiveau( fenetre.getCtrl() );}

		else if ( e.getSource() == aProposJoueur )
		{
			fenetre.getCtrl().afficherInfosJoueur();
		}

		else if ( e.getSource() == aProposJeu )
		{
			JOptionPane.showMessageDialog( fenetre, "© Boulant Florian, Di Gregorio Thomas, " +
													"Emion Thibaut, Edouard Clémence \n Projet " +
													"tutoré – Année universitaire 2015-2016",
										   "A Propos du jeu", JOptionPane.PLAIN_MESSAGE );
		}

		else if ( e.getSource() == regles )
		{
			try
			{
				if ( Desktop.isDesktopSupported() )
				{
					Desktop.getDesktop().browse( new File( "./regles/index.html" ).toURI() );
				}
			} catch ( Exception exe ) {}
		}
	}
}
