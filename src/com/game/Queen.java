package com.game;

public class Queen extends Piece {

    public Queen(Location loc, boolean isWhite) {
        super("Queen", loc, isWhite);
    }

    public boolean canMove(Location to) {
        if (new Bishop(this.location, this.white).canMove(to) || new Rook(this.location, this.white).
                canMove(to)) return true;
        else return false;
    }

    public boolean canTake(Location to) {
        return canMove(to);
    }
}
