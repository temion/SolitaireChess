package SolitaireChess.ihm;

/**
 * SolitaireChess - Projet Tutoré
 * Classe IHM gérant la fenêtre d'accueil.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import SolitaireChess.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Accueil extends JFrame implements ActionListener
{

	private Controleur ctrl;

	private JComboBox<String> choixProfil;

	private Insets insets;

	private JButton valider;
	private JButton supprimer;
	private JButton nouvellePartie;
	private JButton infosJoueur;
	private JButton aide;
	private JButton quitter;


	/**
	 * Construit une nouvelle fenêtre d'accueil.
	 *
	 * @param ctrl controleur lié à la fenêtre courante
	 */
	public Accueil( Controleur ctrl )
	{
		setTitle( "SolitaireChess" );

		this.ctrl = ctrl;

		insets = getInsets();

		setSize( 400 + insets.left + insets.right,
				 600 + insets.top + insets.bottom );

		//getContentPane().setBackground( Color.BLACK );
		setLayout( null );

		initComposants();

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocationRelativeTo( null );
		setResizable( false );
		setVisible( true );
	}


	/**
	 * Crée les différents composants de la fenêtre.
	 */
	private void initComposants()
	{
		Dimension size = new Dimension( 200, 50 );

		JLabel lImage = new JLabel( new ImageIcon( "./images/cavalier.gif" ) );
		add( lImage );
		lImage.setBounds( 0, 150 + insets.top, size.width, size.height );

		size = new Dimension( 400, 200 );

		JLabel lImageAccueil = new JLabel( new ImageIcon( "./images/imageAccueil.png" ) );
		add( lImageAccueil );
		lImageAccueil.setBounds( 0, 20 + insets.top, size.width, size.height );

		size = new Dimension( 200, 50 );

		choixProfil = new JComboBox<String>();

		if ( ctrl.getAlJoueur().size() > 0 )
		{
			choixProfil.addItem( "Sélectionnez un profil" );

			for ( int i = ctrl.getAlJoueur().size() - 1; i > - 1; i-- )
				choixProfil.addItem( ctrl.getAlJoueur().get( i ).getNom() );

			add( choixProfil );
			choixProfil.setBounds( 100 + insets.left, 250 + insets.top, size.width, size.height );

			size = new Dimension( 95, 50 );

			valider = new JButton( "Valider" );
			valider.setForeground( new Color( 19, 177, 38 ) );
			valider.addActionListener( this );
			add( valider );
			valider.setBounds( 100 + insets.left, 300 + insets.top, size.width, size.height );


			supprimer = new JButton( "Supprimer" );
			supprimer.setForeground( new Color( 184, 15, 16 ) );
			supprimer.addActionListener( this );
			add( supprimer );
			supprimer.setBounds( 205 + insets.left, 300 + insets.top, size.width, size.height );


			size = new Dimension( 200, 50 );
		}

		nouvellePartie = new JButton( "Nouvelle partie" );
		nouvellePartie.addActionListener( this );
		add( nouvellePartie );
		nouvellePartie.setBounds( 100 + insets.left, 350 + insets.top, size.width, size.height );

		infosJoueur = new JButton( "Informations" );
		infosJoueur.addActionListener( this );
		add( infosJoueur );
		infosJoueur.setBounds( 100 + insets.left, 400 + insets.top, size.width, size.height );


		quitter = new JButton( "Quitter" );
		quitter.addActionListener( this );
		add( quitter );
		quitter.setBounds( 100 + insets.left, 450 + insets.top, size.width, size.height );


		size = new Dimension( 40, 40 );


		aide = new JButton( "?" );
		aide.addActionListener( this );
		add( aide );
		aide.setBounds( 350 + insets.left, 530 + insets.top, size.width, size.height );
	}


	/**
	 * Regroupe tous les profils dans une JComboBox, ensuite ajoutée à la fenêtre.
	 */
	private void initChoixJoueur()
	{
		Insets insets = getInsets();

		Dimension size = new Dimension( 200, 50 );

		choixProfil = new JComboBox<String>();

		choixProfil.addItem( "Sélectionnez un profil" );

		for ( int i = ctrl.getAlJoueur().size() - 1; i > - 1; i-- )
			choixProfil.addItem( ctrl.getAlJoueur().get( i ).getNom() );

		choixProfil.setBounds( 100 + insets.left, 228 + insets.top, size.width, size.height );
	}


	/**
	 * Gère les appuis sur les boutons du composant.
	 *
	 * @param e un événement lié à l'appui sur l'un des boutons
	 */
	@Override
	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == valider || e.getSource() == nouvellePartie )
		{
			if ( e.getSource() == nouvellePartie ||
				 choixProfil.getSelectedItem().equals( "Nouveau profil..." ) )
			{
				String s = (String)JOptionPane.showInputDialog(
						this,
						"Pseudo :",
						"Entrez un nouveau pseudo",
						JOptionPane.PLAIN_MESSAGE,
						new ImageIcon( "./images/gandalf.png" ), null, null
															  );

				if ( s != null && s.length() > 0 )
				{
					ctrl.ajouterJoueur( s );
					new Jeu( ctrl );
					dispose();
				}
			}
			else if ( ! choixProfil.getSelectedItem().equals( "Sélectionnez un profil" ) )
			{
				ctrl.definirJoueur( (String)choixProfil.getSelectedItem() );
				new Jeu( ctrl );
				dispose();
			}
		}

		if ( e.getSource() == aide )
		{
			try
			{
				if ( Desktop.isDesktopSupported() )
				{
					Desktop.getDesktop().browse( new File( "./regles/index.html" ).toURI() );
				}
			} catch ( Exception exe ) {}
		}

		if ( e.getSource() == supprimer )
		{
			if ( choixProfil.getSelectedItem() != null && ! choixProfil.getSelectedItem().equals(
					"Sélectionnez un profil" ) )
			{
				ctrl.supprimerJoueur( (String)choixProfil.getSelectedItem() );
				remove( choixProfil );
				if ( ctrl.getAlJoueur().size() > 0 )
				{
					this.initChoixJoueur();
					add( choixProfil );
				}
				else
				{
					remove( valider );
					remove( supprimer );
				}
				invalidate();
				revalidate();
				repaint();
			}
		}

		if ( e.getSource() == quitter )
		{
			//ctrl.quitter(this);
		}
	}

}