package usecase.design.chess.pieces;

import usecase.design.chess.game.PiecesEnum;

public interface Piece {

    public PiecesEnum getType();

    public boolean isValidMove(int x, int y);

    public int[][] drawPath(int startX, int startY, int finalX, int finalY);
}
