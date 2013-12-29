package com.game;

import com.game.Location;
import com.game.Piece;
import java.awt.Graphics;

public class Rook extends Piece {

    public Rook(Location loc, boolean isWhite) {
        super("Rook", loc, isWhite);
    }

    public boolean canMove(Location to) {
        if (to.getChessX() == this.location.getChessX()) {
            int steps = Math.abs(to.getY() - this.location.getY());
            int dir = (to.getY() - this.location.getY()) / steps;
            for (int i = 1; i < steps; i++)
                if (Chess.chess.board.board[this.location.getY() + (i * dir)][to.
                        getX()] != null) return false;
            hasMoved = true;
            return true;
        } else if (to.getChessY() == this.location.getChessY()) {
            int steps = Math.abs(to.getX() - this.location.getX());
            int dir = (to.getX() - this.location.getX()) / steps;
            for (int i = 1; i < steps; i++)
                if (Chess.chess.board.board[to.getY()][this.location.getX() + (i * dir)] != null)
                    return false;
            hasMoved = true;
            return true;
        } else return false;
    }

    public boolean canTake(Location to) {
        return canMove(to);
    }
}