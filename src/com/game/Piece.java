package com.game;

import java.awt.Image;
import java.awt.Toolkit;

/*
 * Piece is an abstract class that contains the piece's name (used for loading
 * the correct image), color, location and image. Piece also conatins two
 * abstract methods used to specify the pieces moves both when moving and taking
 * other pieces.
 */
public abstract class Piece {

    private String name;        //Piece name (e.g. "Pawn")
    public boolean white;       //Piece color
    public Location location;   //Piece location
    public Image img;           //Piece image
    public boolean hasMoved = false;

    public Piece(String name, Location location, boolean isWhite) {
        this.name = name;
        this.white = isWhite;
        this.location = location;

        String color = "";
        if (this.white) color = "white";
        else color = "black";
        this.img = getImage("src/com/res/" + color + "_" + name + ".png");
    }

    private Image getImage(String img) {
        return Toolkit.getDefaultToolkit().getImage(img);
    }

    /*
     * Returns whether or not the piece can legally move to the location
     * provided from its current location, based on the piece's moves.
     */
    public abstract boolean canMove(Location to);

    /*
     * Returns whether or not the piece can legally take another piece in the
     * location provided from its current location, usually the same as canMove
     * with the exception of Pawns.
     */
    public abstract boolean canTake(Location to);

    public boolean isWhite() {
        return white;
    }

    public Location getLocation() {
        return location;
    }

    public Image getImg() {
        return img;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        String color;
        if (this.white) color = "White";
        else color = "Black";
        return "" + color + " " + name;
    }
}
