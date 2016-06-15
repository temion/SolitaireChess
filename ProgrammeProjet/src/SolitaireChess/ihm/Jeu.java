package SolitaireChess.ihm; /**
 * SolitaireChess - Projet Tutoré
 * Classe ihm de l'écran du jeu
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import SolitaireChess.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Jeu extends JFrame implements ActionListener
{
	private Controleur ctrl;

	private PanelSolitaireChess plateau;

	private BarreAction barreAction;

	private JButton annuler;
	private JButton recommencer;

	private JLabel niveau;
	private JLabel defi;
	private JLabel score;
	private JLabel mouvements;

	/**
	 * Construit l'écran du jeu.
	 *
	 * @param ctrl la classe qui fera le lien entre la classe et la partie métier
	 */
	public Jeu( Controleur ctrl )
	{
		this.ctrl = ctrl;
		ctrl.setFenetreJeu(this);
		ctrl.getEchiquier().setDefi();

		setTitle("Jeu");
		setLocation(200, 200);

		setLayout( new BorderLayout() );

		barreAction = new BarreAction( this );
		add( barreAction, "North" );

		ajouterPanelEst();

		this.plateau = new PanelSolitaireChess( this.ctrl );
		add( this.plateau );

		ajouterPanelEst();

		pack();

		setVisible(true);

	}

	/**
	 * Ajoute un panel contenant beaucoup de composants à l'écran du jeu.
	 */
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

	/**
	 * Met à jour la représentation ihm de l'échiquier.
	 */
	public void majIHM()
	{
		this.plateau.repaint();
		majPanel();
	}

	/**
	 * Met à jour l'affichage du défi, du niveau et du score.
	 */
	private void majPanel()
	{
		defi.setText( "Defi n° " + ctrl.getEchiquier().getDefi() );
		niveau.setText( "Niveau : " + ctrl.getEchiquier().getNiveau() );
		score.setText( "Score : " + ctrl.getEchiquier().getScore() );
	}

	public Controleur getCtrl() {
		return ctrl;
	}

	/**
	 * Gère les appuis sur les boutons du composant.
	 * @param e un événement lié à l'appui sur l'un des boutons.
	 */
	@Override
	public void actionPerformed( ActionEvent e )
	{
		if (e.getSource() == annuler) {

		}

		if ( e.getSource() == recommencer )
		{
			ctrl.getEchiquier().recommencer();
		}
	}
}