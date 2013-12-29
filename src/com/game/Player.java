package com.game;

import java.util.ArrayList;

/*
 * Player only stores all the player's pieces in an ArrayList. Planned to
 * include the player's rating, record, and statistical information in a later
 * build.
 */
public class Player {

    private ArrayList<Piece> pieces;    //Stores player's pieces
    private boolean white;              //Player's color

    public Player(boolean isWhite) {
        this.white = isWhite;
        pieces = new ArrayList<>();
        fillPieces();
    }

    /*
     * Creates and puts the pieces in their default location mased on the
     * player's color.
     */
    private void fillPieces() {
        if (isWhite()) {
            Piece pawn1 = new Pawn(new Location('A', 2), isWhite());
            getPieces().add(pawn1);
            Piece pawn2 = new Pawn(new Location('B', 2), isWhite());
            getPieces().add(pawn2);
            Piece pawn3 = new Pawn(new Location('C', 2), isWhite());
            getPieces().add(pawn3);
            Piece pawn4 = new Pawn(new Location('D', 2), isWhite());
            getPieces().add(pawn4);
            Piece pawn5 = new Pawn(new Location('E', 2), isWhite());
            getPieces().add(pawn5);
            Piece pawn6 = new Pawn(new Location('F', 2), isWhite());
            getPieces().add(pawn6);
            Piece pawn7 = new Pawn(new Location('G', 2), isWhite());
            getPieces().add(pawn7);
            Piece pawn8 = new Pawn(new Location('H', 2), isWhite());
            getPieces().add(pawn8);

            Piece rook1 = new Rook(new Location('A', 1), isWhite());
            getPieces().add(rook1);
            Piece knight1 = new Knight(new Location('B', 1), isWhite());
            getPieces().add(knight1);
            Piece bishop1 = new Bishop(new Location('C', 1), isWhite());
            getPieces().add(bishop1);
            Piece queen = new Queen(new Location('D', 1), isWhite());
            getPieces().add(queen);
            Piece king = new King(new Location('E', 1), isWhite());
            getPieces().add(king);
            Piece bishop2 = new Bishop(new Location('F', 1), isWhite());
            getPieces().add(bishop2);
            Piece knight2 = new Knight(new Location('G', 1), isWhite());
            getPieces().add(knight2);
            Piece rook2 = new Rook(new Location('H', 1), isWhite());
            getPieces().add(rook2);
        } else {
            Piece pawn1 = new Pawn(new Location('A', 7), isWhite());
            getPieces().add(pawn1);
            Piece pawn2 = new Pawn(new Location('B', 7), isWhite());
            getPieces().add(pawn2);
            Piece pawn3 = new Pawn(new Location('C', 7), isWhite());
            getPieces().add(pawn3);
            Piece pawn4 = new Pawn(new Location('D', 7), isWhite());
            getPieces().add(pawn4);
            Piece pawn5 = new Pawn(new Location('E', 7), isWhite());
            getPieces().add(pawn5);
            Piece pawn6 = new Pawn(new Location('F', 7), isWhite());
            getPieces().add(pawn6);
            Piece pawn7 = new Pawn(new Location('G', 7), isWhite());
            getPieces().add(pawn7);
            Piece pawn8 = new Pawn(new Location('H', 7), isWhite());
            getPieces().add(pawn8);

            Piece rook1 = new Rook(new Location('A', 8), isWhite());
            getPieces().add(rook1);
            Piece knight1 = new Knight(new Location('B', 8), isWhite());
            getPieces().add(knight1);
            Piece bishop1 = new Bishop(new Location('C', 8), isWhite());
            getPieces().add(bishop1);
            Piece queen = new Queen(new Location('D', 8), isWhite());
            getPieces().add(queen);
            Piece king = new King(new Location('E', 8), isWhite());
            getPieces().add(king);
            Piece bishop2 = new Bishop(new Location('F', 8), isWhite());
            getPieces().add(bishop2);
            Piece knight2 = new Knight(new Location('G', 8), isWhite());
            getPieces().add(knight2);
            Piece rook2 = new Rook(new Location('H', 8), isWhite());
            getPieces().add(rook2);
        }
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public boolean isWhite() {
        return white;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    public void setWhite(boolean isWhite) {
        this.white = isWhite;
    }
}
