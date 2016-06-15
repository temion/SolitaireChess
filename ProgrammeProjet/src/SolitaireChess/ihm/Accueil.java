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
	private JButton aide;
	private JButton quitter;


	public Accueil( Controleur ctrl )
	{
		setTitle( "SolitaireChess" );
		setLocation( 200, 200 );
		setSize( 500, 300 );

		this.ctrl = ctrl;

		setLayout( new GridLayout( 4, 1 ) );

		add( new JLabel( "SolitaireChess", JLabel.CENTER ) );

		initChoix();

		aide = new JButton( "Aide" );
		aide.setSize(100, 20);
		aide.addActionListener( this );
		add( aide );

		quitter = new JButton( "Quitter" );
		quitter.setSize(100, 20);
		quitter.addActionListener( this );
		add( quitter );

		setVisible( true );
	}


	private void initChoix()
	{
		JPanel choix = new JPanel();

		choixProfil = new JComboBox<String>();
		choixProfil.addItem( "Sélectionnez un profil" );
		choixProfil.addItem( "Nouveau profil..." );

		choix.add( choixProfil );

		valider = new JButton( "Valider" );
		valider.addActionListener( this );
		choix.add( valider );

		choix.setSize( new Dimension( 400, 150 ) );
		add( choix );
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
			if (choixProfil.getSelectedItem().equals("Nouveau profil...")) {
				System.out.println("Nouveau");
			}

			System.out.println( "Valider" );
			dispose();
			new Jeu( ctrl );
		}

		if ( e.getSource() == aide )
		{
			System.out.println( "Aide" );
		}

		if ( e.getSource() == quitter )
		{
			if ( JOptionPane.showConfirmDialog( this, "Voulez-vous vraiment quitter ?", "Quitter le jeu",
												JOptionPane.YES_NO_OPTION ) == 0) {
				System.exit( 0 );
			}
		}
	}

}