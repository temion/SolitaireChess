import javax.swing.*;

/**
 * Created by Thibaut on 13/06/2016.
 */
 
public class Fenetre extends JFrame
{

	public Fenetre()
	{
		setTitle( "Fenêtre de test" );
		setSize( 400, 400 );
		setLocationRelativeTo( null );

		add( new JButton( "Bouton" ) );

		setVisible( true );
	}

}
