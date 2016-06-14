package SolitaireChess.ihm; /**
 * SolitaireChess - Projet Tutoré
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Accueil extends JPanel implements ActionListener
{
	private FenetreJeu fenetre;

	private JButton jouer, supprimer;
	private JComboBox choixJoueur;
	private JButton nvPartie;
	private JButton quitter;
 
	public Accueil(FenetreJeu fenetre)
	{
		setLayout(new GridLayout(3,1,0,20));
		setPreferredSize(new Dimension(300,300));

		this.fenetre = fenetre;

		this.affichageCharger();


		nvPartie = new JButton("Nouvelle Partie");
		nvPartie.setMargin(new Insets(0,50,0,50));
		nvPartie.addActionListener(this);	
		add(nvPartie);

		quitter = new JButton("Quitter");
		quitter.setMargin(new Insets(0,50,0,50));
		quitter.addActionListener(this);	
		add(quitter);
	}

	private void affichageCharger()
	{
		JPanel pCharger = new JPanel(new BorderLayout(5,0));
		JPanel pBoutonCharger = new JPanel(new GridLayout(2,1,0,5));

		choixJoueur = new JComboBox();
		pCharger.add(choixJoueur);

		jouer = new JButton("Jouer");
		jouer.addActionListener(this);
		pBoutonCharger.add(jouer);	

		supprimer = new JButton("Supprimer");
		jouer.addActionListener(this);	
		pBoutonCharger.add(supprimer);

		pCharger.add(pBoutonCharger, "East");

		add(pCharger);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == jouer)
		{
			fenetre.afficherJeu();
		}
		if(e.getSource() == nvPartie)
		{
			JOptionPane.showInputDialog(this, "Nom du joueur : \n" );
			fenetre.afficherJeu();
		}
		else if(e.getSource() == quitter)
		{
			System.exit(0);
		}
	}
}
