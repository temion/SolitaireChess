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

		this.ctrl = ctrl;

		setLayout( new GridLayout( 4, 1 ) );

		add( new JLabel( "SolitaireChess", JLabel.CENTER ) );

		initChoix();

		aide = new JButton( "Aide" );
		aide.addActionListener( this );
		add( aide );

		quitter = new JButton( "Quitter" );
		quitter.addActionListener( this );
		add( quitter );

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		pack();
		setLocationRelativeTo( null );
		setResizable( false );
		setVisible( true );
	}


	private void initChoix()
	{
		JPanel pChoix = new JPanel();

		choixProfil = new JComboBox<String>();
		choixProfil.addItem( "Sélectionnez un profil" );

		for (int i=0; i<ctrl.getAlJoueur().size();i++)
			choixProfil.addItem( ctrl.getAlJoueur().get(i).getNom() );

		choixProfil.addItem( "Nouveau profil..." );

		pChoix.add( choixProfil );

		valider = new JButton( "Valider" );
		valider.addActionListener( this );
		pChoix.add( valider );

		pChoix.setSize( new Dimension( 400, 150 ) );
		add( pChoix );
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