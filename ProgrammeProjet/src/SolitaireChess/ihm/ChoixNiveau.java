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


	public ChoixNiveau(Jeu jeu) {
		this.jeu = jeu;

		setTitle("Choix d'un défi");
		setLocationRelativeTo(null);
		setSize(500, 500);

		niveaux = new JTabbedPane(JTabbedPane.TOP);

		niveau1 = new JPanel();
		creerChoix(niveau1, 1);

		niveau2 = new JPanel();
		creerChoix(niveau2, 2);

		niveau3 = new JPanel();
		creerChoix(niveau3, 3);

		niveau4 = new JPanel();
		creerChoix(niveau4, 4);

		niveaux.addTab("Débutant", niveau1);
		niveaux.addTab("Intermédiaire", niveau2);
		niveaux.addTab("Avancé", niveau3);
		niveaux.addTab("Expert", niveau4);

		add(niveaux);


		setVisible(true);
	}

	private void creerChoix(JPanel niveau, int difficulte) {
		niveau.setLayout(new GridLayout(3, 5));
		for (int i = 1; i < 16; i++) {
			JButton b = new JButton("Défi n°" + i);
			int     finalI = i;
			b.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e ) {
					if (e.getSource() == b) {
						int numDefi = finalI + (difficulte - 1) * 15; // Les .data sont numérotés de 1 à 60, d'où la
						// formule
						jeu.getCtrl().getEchiquier().setEchiquier( difficulte, numDefi );
						jeu.getCtrl().majIHM();
					}
				}
			} );
			niveau.add(b);
		}
	}
}

