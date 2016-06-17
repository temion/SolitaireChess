package SolitaireChess.ihm;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Thibaut on 16/06/2016.
 */
public class ChoixNiveau extends JFrame
{
	private Jeu jeu;

	private JTabbedPane niveaux;

	private JPanel niveau1;
	private JPanel niveau2;
	private JPanel niveau3;
	private JPanel niveau4;


	public ChoixNiveau( Jeu jeu )
	{
		this.jeu = jeu;

		setTitle( "Choix d'un défi" );
		setSize( 800, 350 );
		setLocationRelativeTo( null );

		niveaux = new JTabbedPane( JTabbedPane.TOP );

		niveau1 = new JPanel();
		creerChoix( niveau1, 1 );

		niveau2 = new JPanel();
		creerChoix( niveau2, 2 );

		niveau3 = new JPanel();
		creerChoix( niveau3, 3 );

		niveau4 = new JPanel();
		creerChoix( niveau4, 4 );

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

		setVisible( true );
	}


	private void creerChoix( JPanel niveau, int difficulte )
	{
		niveau.setLayout( new GridLayout( 5, 3, 0, 2 ) );
		for ( int i = 1; i < 16; i++ )
		{
			if ( jeu.getCtrl().getJoueurCourant().getDefiDebloque( difficulte - 1, i - 1 ) )
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

