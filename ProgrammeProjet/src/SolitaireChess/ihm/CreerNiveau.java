package SolitaireChess.ihm;

/**
 * SolitaireChess - Projet Tutoré
 * Classe IHM gérant la fenêtre de création de niveau.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 * @date 13/06/2016
 */

import SolitaireChess.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

public class CreerNiveau extends JFrame implements ActionListener
{
	private Controleur ctrl;

	private JButton valider;
	private JButton abandonner;

	private PanelCreerNiveau panelCreation;


	/**
	 * Construit une nouvelle fenêtre de création de niveau.
	 *
	 * @param ctrl constructeur lié à la fenêtre courante
	 */
	public CreerNiveau( Controleur ctrl )
	{
		this.ctrl = ctrl;

		setTitle( "Creation de niveau" );

		getContentPane().setBackground( new Color( 0x0D0418 ) );

		JPanel p = new JPanel();
		p.add( this.valider = new JButton( "valider" ) );
		p.add( this.abandonner = new JButton( "abandonner" ) );
		add( p, BorderLayout.NORTH );

		add( this.panelCreation = new PanelCreerNiveau( this ) );

		this.valider.addActionListener( this );
		this.abandonner.addActionListener( this );

		pack();
		setLocationRelativeTo( null );
		setResizable( false );
		setVisible( true );
	}


	/**
	 * Retourne le controleur associé à la fenêtre courante.
	 *
	 * @return controleur associé à la fenêtre courante
	 */
	public Controleur getCtrl()
	{
		return ctrl;
	}


	public static void main( String[] arg )
	{
		new CreerNiveau( null );
	}


	@Override
	/**
	 * Gère les appuis sur les boutons du composant.
	 *
	 * @param e un événement lié à l'appui sur l'un des boutons
	 */
	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == valider )
		{
			ecrireFichierNiveau();
			dispose();
		}
		if ( e.getSource() == abandonner )
		{
			dispose();
		}
	}

	private void ecrireFichierNiveau()
	{
		FileWriter fw;
		File f;
		int i=1;

		while(true)
		{
			f=new File("./niveaux/niveauUtilisateur/defi"+String.format("%02d",i)+".data");
			if(! f.exists())
				break;
			i++;
		}

		String str="";

		try
		{
			if(i<=15)
			{
				fw = new FileWriter( f );
				char[][] tab = getDefi();

				for(i=0; i<tab.length; i++)
				{
					for(int j=0; j<tab[i].length; j++)
						str += tab[i][j]=='\u0000'?' ':tab[i][j];
					str+='\n';
				}

				System.out.println(str);
				fw.write(str);
				fw.close();
			}
		}catch (Exception e){}
	}

	public char[][] getDefi()
	{
		return panelCreation.getDefi();
	}
}
