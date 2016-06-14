package SolitaireChess.ihm; /**
 * SolitaireChess - Projet Tutoré
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import SolitaireChess.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Jeu extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private FenetreJeu          fenetre;
	private PanelSolitaireChess plateau;

	private BarreAction barreAction;

	private JButton annuler;
	private JButton recommencer;

	private JLabel niveau;
	private JLabel defi;
	private JLabel score;
	private JLabel mouvements;


	public Jeu( FenetreJeu fenetre, Controleur ctrl )
	{
		this.ctrl = ctrl;

		setLayout( new BorderLayout() );

		this.fenetre = fenetre;

		barreAction = new BarreAction( fenetre );
		add( barreAction, "North" );

		ajouterPanelEst();

		this.plateau = new PanelSolitaireChess( this.ctrl );
		add( this.plateau );

		ajouterPanelEst();

	}


	private void ajouterPanelEst()
	{
		JPanel pEst = new JPanel( new GridLayout( 6, 1, 0, 2 ) );

		pEst.setBorder( BorderFactory.createEtchedBorder() );//A LA FIN

		pEst.add( defi = new JLabel( "Defi n° " + ctrl.getEchiquier().getDefi(), JLabel.CENTER ) );
		pEst.add( niveau = new JLabel( "Niveau : " + ctrl.getEchiquier().getNiveau(),
									   JLabel.CENTER ) );
		pEst.add(
				score = new JLabel( "Score : " + ctrl.getEchiquier().getScore(), JLabel.CENTER ) );

		annuler = new JButton( "Annuler" );
		pEst.add( annuler );

		recommencer = new JButton( "Recommencer" );
		recommencer.addActionListener( this );
		pEst.add( recommencer );

		pEst.add( mouvements = new JLabel( "Mouvements : " + ctrl.getEchiquier().getNbMouvements()
		) );

		add( pEst, "East" );
	}


	public void majIHM()
	{
		this.plateau.repaint();
		majPanel();
	}


	private void majPanel()
	{
		defi.setText( "Defi n° " + ctrl.getEchiquier().getDefi() );
		niveau.setText( "Niveau : " + ctrl.getEchiquier().getNiveau() );
		score.setText( "Score : " + ctrl.getEchiquier().getScore() );
	}


	@Override
	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == recommencer )
		{
			fenetre.recommencer();
		}
	}
}
