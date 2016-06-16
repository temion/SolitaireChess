package SolitaireChess.interfaces;


import SolitaireChess.metier.pieces.Piece;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Florian on 16/06/2016.
 */
public interface IPieceEchec
{
	public ArrayList<Point> getDeplacementEchec();
}
