package SolitaireChess.ihm;


import SolitaireChess.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil extends JFrame implements ActionListener
{

	private Controleur ctrl;

	private JComboBox<String> choixProfil;

	private JButton valider;
	private JButton supprimer;
	private JButton créer;
	private JButton aide;
	private JButton quitter;


	public Accueil( Controleur ctrl )
	{
		setTitle( "SolitaireChess" );

		this.ctrl = ctrl;

		setLayout( null );

		initComposants();

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocationRelativeTo( null );
		setResizable( false );
		setVisible( true );
	}


	private void initComposants()
	{
		Insets    insets = getInsets();
		Dimension size   = new Dimension( 200, 50 );

		add( new JLabel( "SolitaireChess", JLabel.CENTER ) );

		Icon   icone  = new ImageIcon( "./images/iconeChoisirPseudo.png" );
		JLabel lImage = new JLabel( icone );
		lImage.setBounds( 0, 450 + insets.top, icone.getIconWidth(),
						  icone.getIconHeight() );
		add( lImage );

		choixProfil = new JComboBox<String>();
		choixProfil.addItem( "Sélectionnez un profil" );

		for ( int i = 0; i < ctrl.getAlJoueur().size(); i++ )
			choixProfil.addItem( ctrl.getAlJoueur().get( i ).getNom() );

		choixProfil.addItem( "Nouveau profil..." );
		add( choixProfil );

		choixProfil.setBounds( 100 + insets.left, 300 + insets.top, size.width, size.height );


		size = new Dimension( 95, 50 );


		valider = new JButton( "Valider" );
		valider.setForeground( Color.GREEN );
		valider.addActionListener( this );
		add( valider );
		valider.setBounds( 100 + insets.left, 350 + insets.top, size.width, size.height );


		supprimer = new JButton( "Supprimer" );
		supprimer.setForeground( Color.RED );
		supprimer.addActionListener( this );
		add( supprimer );
		supprimer.setBounds( 205 + insets.left, 350 + insets.top, size.width, size.height );


		size = new Dimension( 200, 50 );


		aide = new JButton( "Aide" );
		aide.addActionListener( this );
		add( aide );
		aide.setBounds( 100 + insets.left, 400 + insets.top, size.width, size.height );


		quitter = new JButton( "Quitter" );
		quitter.addActionListener( this );
		add( quitter );
		quitter.setBounds( 100 + insets.left, 450 + insets.top, size.width, size.height );


		setSize( 400 + insets.left + insets.right,
				 600 + insets.top + insets.bottom );
	}


	/**
	 * Invoked when an action occurs.
	 *
	 * @param e
	 */
	@Override
	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == valider )
		{
			if ( choixProfil.getSelectedItem().equals( "Nouveau profil..." ) )
			{
				String s = (String)JOptionPane.showInputDialog(
						this,
						"Pseudo :",
						"Entrez un nouveau pseudo",
						JOptionPane.PLAIN_MESSAGE,
						new ImageIcon( "./images/iconeChoisirPseudo.png" ), null, null
															  );

				if ( s != null && s.length() > 0 )
				{
					ctrl.ajouterJoueur( s );
					ctrl.enregistrer();
					new Jeu( ctrl );
					dispose();
				}
			}
			else if ( ! choixProfil.getSelectedItem().equals( "Sélectionnez un profil" ) )
			{
				ctrl.definirJoueur( (String)choixProfil.getSelectedItem() );
				new Jeu( ctrl );
				setVisible( false );
			}
		}

		if ( e.getSource() == aide )
		{
			System.out.println( "Aide" );
		}

		if ( e.getSource() == quitter )
		{
			if ( JOptionPane.showConfirmDialog( this, "Voulez-vous vraiment quitter ?",
												"Quitter le jeu",
												JOptionPane.YES_NO_OPTION ) == 0 )
			{
				System.exit( 0 );
			}
		}
	}

}