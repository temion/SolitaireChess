import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * Created by Thibaut on 13/06/2016.
 */
public class Fenetre extends JFrame implements ActionListener
{

	public Fenetre()
	{
		setTitle( "Fenêtre de test" );
		setSize( 400, 400 );
		setLocationRelativeTo( null );

		JButton b = new JButton( "Bouton" );
		b.addActionListener( this );
		add( b );

		setVisible( true );
	}


	public void actionPerformed( ActionEvent e )
	{
		try
		{
			Robot r = new Robot();
			r.keyPress( KeyEvent.VK_ALT );
			r.keyPress( KeyEvent.VK_F4 );
			r.keyRelease( ( KeyEvent.VK_ALT ) );
			r.keyRelease( KeyEvent.VK_F4 );
		} catch ( Exception exception ) {}
	}
}
