package SolitaireChess.ihm; /**
 * SolitaireChess - Projet Tutoré
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarreAction extends JPanel implements ActionListener
{
	private FenetreJeu fenetre;

	private JButton indices;
	private JButton niveaux;
	private JButton creerNiveau;
	private JButton joueur;
	private JButton regles;
	private JButton menuPrincipal;


	public BarreAction( FenetreJeu fenetre )
	{
		this.fenetre = fenetre;

		indices = new JButton( "Indices" );
		indices.addActionListener( this );
		add( indices );

		niveaux = new JButton( "Niveaux" );
		niveaux.addActionListener( this );
		add( niveaux );

		creerNiveau = new JButton( "Creer niveau" );
		creerNiveau.addActionListener( this );
		add( creerNiveau );

		joueur = new JButton( "Joueur" );
		joueur.addActionListener( this );
		add( joueur );

		regles = new JButton( "Regles" );
		regles.addActionListener( this );
		add( regles );

		menuPrincipal = new JButton( "Menu principal" );
		menuPrincipal.addActionListener( this );
		add( menuPrincipal );
	}


	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == menuPrincipal )
		{
			if(JOptionPane.showConfirmDialog(
					this,
					"Voulez-vous vraiment revenir au menu principal ?",
					"Question",
					JOptionPane.YES_NO_OPTION) == 0)
			{
				fenetre.afficherAccueil();
			}

		}
		else if ( e.getSource() == joueur )
		{
			Object[] themes = { "theme 1", "theme 2" };
			JOptionPane.showInputDialog( this, "Joueur : \n" +
											   "Dernier defi : \n" +
											   "Score : \n",
										 "Joueur",
										 JOptionPane.PLAIN_MESSAGE,
										 null,
										 themes,
										 themes[0] );

		}
	}
}
