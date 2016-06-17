package SolitaireChess.ihm;
/**
 * SolitaireChess - Projet Tutoré
 * Classe IHM du composant comportant les boutons se trouvant en haut de la fenêtre de jeu.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarreAction extends JPanel implements ActionListener
{
	private Jeu fenetre;

	private JButton indices;
	private JButton niveaux;
	private JButton creerNiveau;
	private JButton joueur;
	private JButton regles;
	private JButton menuPrincipal;


	/**
	 * Construit le composant se trouvant en haut de l'écran du jeu.
	 *
	 * @param jeu la fenêtre contenant tous les composants ihm
	 */
	public BarreAction( Jeu fenetre )
	{
		this.fenetre = fenetre;

		setLayout( new GridLayout( 1, 6 ) );

		indices = new JButton( "Indices" );
		indices.addActionListener( this );
		add( indices );

		niveaux = new JButton( "Niveaux" );
		niveaux.addActionListener( this );
		add( niveaux );

		creerNiveau = new JButton( "Créer niveau" );
		creerNiveau.addActionListener( this );
		add( creerNiveau );

		regles = new JButton( "Règles" );
		regles.addActionListener( this );
		add( regles );
	}


	/**
	 * Gère les appuis sur les boutons du composant.
	 *
	 * @param e un événement lié à l'appui sur l'un des boutons.
	 */
	@Override
	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == indices )
		{
			fenetre.getCtrl().getJoueurCourant().incrementerMouvements();
			fenetre.getCtrl().getEchiquier().initIndiceDefi();
		}

		else if ( e.getSource() == niveaux )
		{
			new ChoixNiveau( fenetre );
		}

		else if(e.getSource() == creerNiveau)
		{
			new CreerNiveau( fenetre.getCtrl() );
		}

		else if ( e.getSource() == joueur )
		{
			fenetre.getCtrl().afficherInfosJoueur();
		}

		else if ( e.getSource() == regles)
		{
			fenetre.getCtrl().afficherRegles();
		}
	}
}