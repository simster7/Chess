package com.game;

import java.awt.Graphics;

public class Bishop extends Piece {

    public Bishop(Location loc, boolean isWhite) {
        super("Bishop", loc, isWhite);
    }

    public boolean canMove(Location to) {
        if (Math.abs(to.getChessX() - this.location.getChessX()) == Math.abs(to.
                getChessY() - this.location.getChessY())) {

            int steps = Math.abs(to.getX() - this.location.getX());
            int xDir = (to.getX() - this.location.getX()) / steps;
            int yDir = (to.getY() - this.location.getY()) / steps;

            for (int i = 1; i < steps; i++) {
                if (Chess.chess.board.board[this.location.getY() + (i * yDir)][this.location.
                        getX() + (i * xDir)] != null) return false;
            }

            return true;
        } else return false;
    }

    public boolean canTake(Location to) {
        return canMove(to);
    }
}