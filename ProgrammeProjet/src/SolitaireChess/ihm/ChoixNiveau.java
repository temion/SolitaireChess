package SolitaireChess.ihm;

/**
 * SolitaireChess - Projet Tutoré
 * Classe IHM de la fenêtre de sélection de niveau.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoixNiveau extends JFrame
{
	private Jeu jeu;

	private JTabbedPane niveaux;

	private JPanel niveau1;
	private JPanel niveau2;
	private JPanel niveau3;
	private JPanel niveau4;


	/**
	 * Construit une nouvelle fenêtre de sélection de niveau.
	 * Lui ajoute 4 onglets, correspondant aux différents niveaux de difficulté disponibles.
	 *
	 * @param jeu fenêtre auquelle est lié la fenêtre de choix de niveau créée
	 */
	public ChoixNiveau( Jeu jeu )
	{
		this.jeu = jeu;

		setTitle( "Choix d'un défi" );
		setSize( 800, 350 );
		setLocationRelativeTo( null );

		niveaux = new JTabbedPane( JTabbedPane.TOP );

		niveau1 = new JPanel();
		niveau2 = new JPanel();
		niveau3 = new JPanel();
		niveau4 = new JPanel();

		niveaux.addTab( "Débutant", niveau1 );
		niveaux.addTab( "Intermédiaire", niveau2 );
		niveaux.addTab( "Avancé", niveau3 );
		niveaux.addTab( "Expert", niveau4 );

		add( niveaux );

		for ( int i = 1; i < jeu.getCtrl().getNbLigne(); i++ )
			if ( jeu.getCtrl().getNbDefisAccomplis( i - 1 ) < 7 )
			{
				niveaux.setEnabledAt( i, false );
				niveaux.setToolTipTextAt( i,
										  "Terminez au moins 50% du niveau précédent afin d'accéder à ce niveau" );
			}

		creerChoix( niveau1, 1 );
		creerChoix( niveau2, 2 );
		creerChoix( niveau3, 3 );
		creerChoix( niveau4, 4 );

		setVisible( true );
	}


	/**
	 * Ajoute à l'onglet correspondant au niveau passé en paramètre 15 boutons défis.
	 * Cliquer sur un de ces boutons mets à jour le niveau courant.
	 *
	 * @param niveau onglet dans lequel on ajoute les boutons
	 * @param difficulte difficulté des défis à ajouter
	 */
	private void creerChoix( JPanel niveau, int difficulte )
	{
		if ( niveaux.isEnabledAt( difficulte - 1 ) )
		{
			niveau.setLayout( new GridLayout( 5, 3, 0, 2 ) );
			for ( int i = 1; i < 16; i++ )
			{
				if ( i == 1 || jeu.getCtrl().getJoueurCourant().getDefiDebloque( difficulte - 1, i
																								 -
																								 1 ) )
				{
					JButton b = new JButton( "Défi n°" + i, new ImageIcon( String.format( "" +
																						  "./images/apercus/niveau%02d/defi%02d" +
																						  ".png" +
																						  "",
																						  difficulte,
																						  i ) ) );
					b.setIconTextGap( 2 );
					int finalI = i;
					b.addActionListener( new ActionListener()
					{
						public void actionPerformed( ActionEvent e )
						{
							if ( e.getSource() == b )
							{
								int numDefi = finalI + ( difficulte - 1 ) *
													   15; // Les .data sont numérotés de 1 à 60, d'où la
								// formule
								jeu.getCtrl().getEchiquier().setEchiquier( difficulte, numDefi );
								jeu.getCtrl().majIHM();
								dispose();
							}
						}
					} );
					niveau.add( b );
				}
				else
				{
					JButton b = new JButton( "Défi n°" + i, new ImageIcon( String.format( "" +
																						  "./images/apercus/niveau%02d/defi%02d" +
																						  ".png" +
																						  "",
																						  difficulte,
																						  0 ) ) );
					b.setIconTextGap( 2 );
					niveau.add( b );
				}
			}
		}
	}
}

