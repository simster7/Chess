package com.game;

public class King extends Piece {

    public King(Location loc, boolean isWhite) {
        super("King", loc, isWhite);
    }

    public boolean canMove(Location to) {
        if (Math.abs(to.getChessX() - this.location.getChessX()) == 1 || Math.
                abs(to.getChessY() - this.location.getChessY()) == 1) {
            hasMoved = true;
            return true;
        } else if (Math.abs(to.getChessX() - this.location.getChessX()) == 2 && Math.
                abs(to.getChessY() - this.location.getChessY()) == 0 && !hasMoved()) {
            return true;
        } else return false;
    }

    public boolean canTake(Location to) {
        return canMove(to);
    }
}
