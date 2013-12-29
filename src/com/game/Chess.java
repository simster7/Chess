package com.game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Chess extends JFrame implements MouseListener {

    public Image image;             //JFrame component
    public Graphics graphics;       //JFrame component
    public Board board;             //Contains board and player info
    private Location fromLocation;  //Used to store the location of a piece before it is moved
    private Location toLocation;    //Used to store the location of a pieve after it is moved
    private boolean whiteTurn;      //Current player's turn
    private boolean playingOnline;  //True if playing an online game
    private boolean dynamicView;    //Switch perspective by turn on local games
    public OnlineGUI online;        //Online user inferface
    public static Chess chess;      //Self class used for alerts by children

    public Chess() {
        init();
        setTitle("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        addMouseListener(this);
        setSize(800, 880);
    }

    public void init() {
        board = new Board();
        whiteTurn = true;
        playingOnline = false;
        dynamicView = false;
    }

    /*
     * Paints the game into the JFrame, calls Board.drawBoard() to draw pieces
     * stored in the Board
     */
    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        paintComponent(graphics);
        g.drawImage(image, 0, 0, null);
        if (!playingOnline && dynamicView) board.setPerspective(whiteTurn);
        repaint();
    }

    public void paintComponent(Graphics g) {
        board.drawBoard(g);
        /* UI Components */
        g.setColor(Color.BLACK);
        g.drawRect(3, 25, 793, 800);
        g.drawRect(10, 825 + 6, 38, 38);
        if (this.whiteTurn) g.setColor(new Color(204, 204, 204));
        else g.setColor(new Color(102, 153, 153));
        g.fillRect(11, 825 + 7, 37, 37);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Tahoma", Font.PLAIN, 14));
        //g.drawString("/* PIECES TAKEN WILL BE SHOWN HERE */", 60, 850);
        g.setFont(new Font("Tahoma", Font.BOLD, 14));
        g.drawString("Chess v0.3", 660, 840);

        g.drawRect(435, 845, 80, 25);
        g.setFont(new Font("Tahoma", Font.PLAIN, 12));
        g.drawString("Switch View", 440, 862);

        g.drawRect(530, 845, 40, 25);
        g.setFont(new Font("Tahoma", Font.PLAIN, 12));
        g.drawString("Undo", 535, 862);

        g.setFont(new Font("Tahoma", Font.BOLD, 12));
        g.drawString("New:", 590, 862);

        g.drawRect(625, 845, 75, 25);
        g.setFont(new Font("Tahoma", Font.PLAIN, 12));
        g.drawString("Local Game", 630, 862);

        g.drawRect(710, 845, 75, 25);
        g.setFont(new Font("Tahoma", Font.PLAIN, 12));
        g.drawString("Online Game", 715, 862);
    }

    /*
     * Get a chess notation Location based on x and y mouse coordinates from the
     * JFrame and depending on which perspective is being used.
     */
    public Location getLocation(int x, int y) {
        if (board.getPerspective()) {   //If playing from white's perspective
            int yLoc = (int) ((925 - y) / 100);
            int xLoc = (int) ((100 + x) / 100) + 64;
            if (y > 825) return null;   //If location is underneath bottom border
            return new Location((char) xLoc, yLoc);
        } else {                        //If playing from black's perspective
            int yLoc = (int) ((y - 25) / 100) + 1;
            int xLoc = (int) ((1000 - x) / 100) + 63;
            if (y > 825) return null;   //If location is underneath bottom border
            return new Location((char) xLoc, yLoc);
        }
    }

    /*
     * Used by OnlineConnection to alert Chess that a move is pending in
     * moveQueue.
     */
    public static void moveRecieved() {
        chess.getMove();
    }

    /*
     * Start an online game.
     */
    public void online() {
        online = new OnlineGUI();
        playingOnline = true;
    }

    /*
     * Get move from OnlineGUI and OnlineConnection.
     */
    public void getMove() {

        Location[] temp = online.getLastMove();
        fromLocation = temp[0];
        toLocation = temp[1];

        movePiece();
    }

    public static void main(String[] args) {
        chess = new Chess();
    }

    /*
     * Move piece from fromLocation to toLocation after legal move checks.
     */
    public void movePiece() {
        if (fromLocation != null && toLocation != null) {                       //Check if location is inside board
            if (board.getPiece(fromLocation).isWhite() == this.whiteTurn) {     //Check turn
                if (board.getPiece(fromLocation).canMove(toLocation) && !board.
                        isOccupied(toLocation)) {                               //Check if valid move location
                    board.movePiece(fromLocation, toLocation);
                    whiteTurn ^= true;
                    if (fromLocation.getChessX() == 'E' && toLocation.getChessX() == 'G' && !board.
                            getPiece(new Location('H', toLocation.getChessY())).hasMoved) { //Move Rook if castling kingside
                        board.movePiece(new Location('H', toLocation.getChessY()), new Location('F', toLocation.
                                getChessY()));
                    }
                    if (fromLocation.getChessX() == 'E' && toLocation.getChessX() == 'C' && !board.
                            getPiece(new Location('A', toLocation.getChessY())).hasMoved) { //Move Rook if castling queenside
                        board.movePiece(new Location('A', toLocation.getChessY()), new Location('D', toLocation.
                                getChessY()));
                    }
                } else if (board.getPiece(fromLocation).canTake(toLocation) && board.
                        isOccupied(toLocation) && board.getPiece(toLocation).
                        isWhite() != board.getPiece(fromLocation).isWhite()) {  //Check if valid take location
                    board.movePiece(fromLocation, toLocation);
                    whiteTurn ^= true;
                } else {
                    System.out.println("Can't move, illegal move");
                }
            } else {
                System.out.println("Can't move, out of turn");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() > 625 && e.getX() < 700 && e.getY() > 845 && e.getX() < 870) {         //"New Game" button
            init();
        } else if (e.getX() > 710 && e.getX() < 785 && e.getY() > 845 && e.getX() < 870) {  //"Online Game" button
            online();
        } else if (e.getX() > 530 && e.getX() < 570 && e.getY() > 845 && e.getX() < 870) {  //"Undo" button
            if (playingOnline)
                JOptionPane.showMessageDialog(null, "In online mode, you AND your opponent must press\n\"Undo\" in order for both boards to reflect the change.");
            board.undo();
            whiteTurn ^= true;
        } else if (e.getX() > 435 && e.getX() < 515 && e.getY() > 845 && e.getX() < 870) {  //"Switch View" button
            board.switchPerspective();
        } else if (e.getX() > 370 && e.getX() < 430 && e.getY() > 845 && e.getX() < 870) {
            dynamicView ^= true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        fromLocation = getLocation(e.getX(), e.getY());
        System.out.println("Pressed: " + fromLocation);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        toLocation = getLocation(e.getX(), e.getY());
        System.out.println("Released: " + toLocation);

        movePiece();
        if (playingOnline) online.sendMove(fromLocation, toLocation);

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}