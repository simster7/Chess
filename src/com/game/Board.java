package com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;

/*
 * Board contains the game board, the two players and their respective pieces,
 * and all the methods used to access and modify the pieces in the board. Pieces
 * are stored in a two-dimensional array and are painted into the JFrame from it
 * via the drawBoard method.
 */
public class Board extends JFrame {

    public Piece[][] board;     //Matrix where pieces are stored
    private Piece[][] unBoard;  //A copy of board for undo
    public Player whitePlayer;
    public Player blackPlayer;
    public boolean whiteView;   //True if white's prespective
    public Image boardImage;    //Board background

    public Board() {
        board = new Piece[8][8];
        unBoard = new Piece[8][8];
        whitePlayer = new Player(true);
        blackPlayer = new Player(false);
        whiteView = true;
        boardImage = getImage("src/com/res/board.png");
        for (Piece p : whitePlayer.getPieces()) {
            putPiece(p, p.getLocation());
        }
        for (Piece p : blackPlayer.getPieces()) {
            putPiece(p, p.getLocation());
        }
    }

    private Image getImage(String img) {
        return Toolkit.getDefaultToolkit().getImage(img);
    }

    public void putPiece(Piece piece, Location loc) {
        board[loc.getY()][loc.getX()] = piece;
    }

    /*
     * Moves a piece and creates a copy of the board for use in the "Undo"
     * button function.
     */
    public void movePiece(Location from, Location to) {
        createArrayCopy(board, unBoard);            //Creates a copy of the board
        Piece p = board[from.getY()][from.getX()];
        board[to.getY()][to.getX()] = p;
        board[from.getY()][from.getX()] = null;
        p.setLocation(to);
    }

    public Piece getPiece(Location loc) {
        return board[loc.getY()][loc.getX()];
    }

    public boolean isOccupied(int y, int x) {
        if (board[y][x] == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isOccupied(Location loc) {
        if (board[loc.getY()][loc.getX()] == null) {
            return false;
        } else {
            return true;
        }
    }

    public void createArrayCopy(Piece[][] from, Piece[][] to) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                to[y][x] = from[y][x];
            }
        }
    }

    /*
     * Undoes the last move by replaceing board with unBoard, the board as it
     * appeared before the last move was made.
     */
    public void undo() {
        createArrayCopy(unBoard, board);

    }

    /*
     * Draws the board into the JFrame from the board array. isOccupied is used
     * to avoid NullPointerExceptions when accessing the array.
     */
    public void drawBoard(Graphics g) {
        if (whiteView) {
            g.drawImage(boardImage, 0, 25, 800, 800, null);
            for (int y = 0; y < board.length; y++) {
                for (int x = 0; x < board[y].length; x++) {
                    if (board[y][x] != null) {
                        g.drawImage(board[y][x].getImg(), x * 100, 25 + (y * 100), 100, 100, null);
                    }
                }
            }
        } else {
            g.drawImage(boardImage, 0, 25, 800, 800, null);
            for (int y = 0; y < board.length; y++) {
                for (int x = 0; x < board[y].length; x++) {
                    if (board[7 - y][7 - x] != null) {
                        g.drawImage(board[7 - y][7 - x].getImg(), x * 100, 25 + (y * 100), 100, 100, null);
                    }
                }
            }
        }
    }

    public void switchPerspective() {
        whiteView ^= true;
        System.out.println(whiteView);
    }

    public void setPerspective(boolean p) {
        whiteView = p;
    }

    public boolean getPerspective() {
        return whiteView;
    }

    public void printBoard(Piece[][] b) {
        String out = "";

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (isOccupied(y, x)) out += "" + b[y][x] + "\t| ";
                else out += "\t\t| ";
            }
            out += "\n";
        }

        System.out.println(out);
    }
}
