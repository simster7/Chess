package com.game;

import com.game.Location;
import com.game.Piece;
import java.awt.Graphics;

public class Pawn extends Piece {

    public Pawn(Location loc, boolean isWhite) {
        super("Pawn", loc, isWhite);
    }

    public boolean canMove(Location to) {

        if (to.getChessY() == this.location.getChessY() + 1 && to.getChessX() == this.location.
                getChessX() && this.isWhite())  //White move forward
            return true;
        else if (to.getChessY() == this.location.getChessY() - 1 && to.getChessX() == this.location.
                getChessX() && !this.isWhite()) //Black move forward
            return true;
        else if (this.location.getChessY() == 2 && to.getChessY() == 4 && to.
                getChessX() == this.location.getChessX() && this.isWhite() && Chess.chess.board.board[5][this.location.
                getX()] == null)                //White move two forward first turn
            return true;
        else if (this.location.getChessY() == 7 && to.getChessY() == 5 && to.
                getChessX() == this.location.getChessX() && !this.isWhite() && Chess.chess.board.board[2][this.location.
                getX()] == null)                //Black move two forward first turn
            return true;
        else return false;
    }

    public boolean canTake(Location to) {
        if (to.getChessY() == this.location.getChessY() + 1 && Math.abs(to.
                getChessX() - this.location.getChessX()) == 1 && this.isWhite())
            return true;
        else if (to.getChessY() == this.location.getChessY() - 1 && Math.abs(to.
                getChessX() - this.location.getChessX()) == 1 && !this.isWhite())
            return true;
        else return false;
    }
}