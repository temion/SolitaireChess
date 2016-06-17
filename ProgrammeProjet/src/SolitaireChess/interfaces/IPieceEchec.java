package SolitaireChess.interfaces;


import java.awt.*;
import java.util.ArrayList;

/**
 * SolitaireChess - Projet Tutoré
 * Interface permettant d'indiquer toutes les cases accessibles par les pièces qui l'implémentent.
 *
 * @author Boulant Florian, Di Gregorio Thomas, Edouard Clemence et Emion Thibaut
 */
public interface IPieceEchec
{
	/**
	 * Indique toutes les cases accessibles par la pièce sélectionnée.
	 *
	 * @return ArrayList de points, représentants les cases accessibles par la pièce sélectionnée
	 */
	public ArrayList<Point> getDeplacementEchec();
}
