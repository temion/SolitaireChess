package SolitaireChess.ihm;


import javax.swing.*;
import java.awt.*;

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


	public ChoixNiveau(Jeu jeu) {
		this.jeu = jeu;

		setTitle("Choix d'un défi");
		setLocationRelativeTo(null);
		setSize(500, 500);

		niveaux = new JTabbedPane(JTabbedPane.TOP);

		niveau1 = new JPanel();
		creerChoix(niveau1);

		niveau2 = new JPanel();
		creerChoix(niveau2);

		niveau3 = new JPanel();
		creerChoix(niveau3);

		niveau4 = new JPanel();
		creerChoix(niveau4);

		niveaux.addTab("Débutant", niveau1);
		niveaux.addTab("Intermédiaire", niveau2);
		niveaux.addTab("Avancé", niveau3);
		niveaux.addTab("Expert", niveau4);

		add(niveaux);


		setVisible(true);
	}

	private void creerChoix(JPanel niveau) {
		niveau.setLayout(new GridLayout(3, 5));
		for (int i = 1; i < 16; i++) {
			niveau.add(new JButton("Défi n°" + i));
		}
	}
}

