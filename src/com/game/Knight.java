package com.game;

import java.awt.Graphics;

public class Knight extends Piece {

    public Knight(Location loc, boolean isWhite) {
        super("Knight", loc, isWhite);
    }

    public boolean canMove(Location to) {
        if (Math.abs(to.getChessX() - this.location.getChessX()) == 2 && Math.
                abs(to.getChessY() - this.location.getChessY()) == 1)
            return true;
        else if (Math.abs(to.getChessX() - this.location.getChessX()) == 1 && Math.
                abs(to.getChessY() - this.location.getChessY()) == 2)
            return true;
        else return false;
    }

    public boolean canTake(Location to) {
        return canMove(to);
    }
}