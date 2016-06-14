package SolitaireChess.ihm;
/**
 * SolitaireChess - Projet Tutoré
 * Classe ihm de l'écran d'accueil.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil extends JPanel implements ActionListener
{
	private FenetreJeu fenetre;

	private JButton jouer, supprimer;
	private JComboBox choixJoueur;
	private JButton   nvPartie;
	private JButton   quitter;

	/**
	 * Construit l'écran du menu principal.
	 *
	 * @param fenetre la fenêtre contenant tous les composants ihm
	 */
	public Accueil( FenetreJeu fenetre )
	{
		setLayout( new GridLayout( 3, 1, 0, 20 ) );
		setPreferredSize( new Dimension( 300, 300 ) );

		this.fenetre = fenetre;

		this.affichageCharger();


		nvPartie = new JButton( "Nouvelle Partie" );
		nvPartie.setMargin( new Insets( 0, 50, 0, 50 ) );
		nvPartie.addActionListener( this );
		add( nvPartie );

		quitter = new JButton( "Quitter" );
		quitter.setMargin( new Insets( 0, 50, 0, 50 ) );
		quitter.addActionListener( this );
		add( quitter );
	}

	/**
	 * Ajoute des composants ihm dans l'écran du menu principal.
	 */
	private void affichageCharger()
	{
		JPanel pCharger       = new JPanel( new BorderLayout( 5, 0 ) );
		JPanel pBoutonCharger = new JPanel( new GridLayout( 2, 1, 0, 5 ) );

		choixJoueur = new JComboBox();
		choixJoueur.addItem( "Choisir une partie" );
		pCharger.add( choixJoueur );

		jouer = new JButton( "Jouer" );
		jouer.addActionListener( this );
		pBoutonCharger.add( jouer );

		supprimer = new JButton( "Supprimer" );
		jouer.addActionListener( this );
		pBoutonCharger.add( supprimer );

		pCharger.add( pBoutonCharger, "East" );

		add( pCharger );
	}

	/**
	 * Gère les appuis sur les boutons du composant.
	 * @param e un événement lié à l'appui sur l'un des boutons.
	 */
	@Override
	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == jouer && choixJoueur.getSelectedItem() != null &&
			 ! choixJoueur.getSelectedItem().equals
					 ( "Choisir une partie" ) )
		{
			fenetre.afficherJeu();
		}
		if ( e.getSource() == nvPartie )
		{
			String nom = JOptionPane.showInputDialog( this, "Nom du joueur : \n" );
			choixJoueur.addItem( nom );
			fenetre.afficherJeu();
		}
		else if ( e.getSource() == quitter )
		{
			if ( JOptionPane.showConfirmDialog(
					this,
					"Voulez-vous vraiment quitter le jeu ?",
					"Question",
					JOptionPane.YES_NO_OPTION ) == 0 )
			{
				System.exit( 0 );
			}
		}
	}
}
