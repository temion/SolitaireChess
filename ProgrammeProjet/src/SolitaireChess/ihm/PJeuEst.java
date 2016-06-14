package SolitaireChess.ihm; /**
 * SolitaireChess - Projet Tutoré
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PJeuEst extends JPanel
{
	private JButton annuler;
	private JButton recommencer;


	public PJeuEst()
	{
		setLayout( new GridLayout( 6, 1, 0, 2 ) );
		this.setBorder( BorderFactory.createEtchedBorder() );

		add( new Label( "Defi n° " ) );
		add( new Label( "Niveau : " ) );

		add( new Label( "Score : " ) );

		annuler = new JButton( "Annuler" );
		add( annuler );

		recommencer = new JButton( "Recommencer" );
		add( recommencer );

		add( new Label( "Mouvements : " ) );
	}
}
