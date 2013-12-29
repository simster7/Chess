package com.game;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * OnlineConnection is responsible for sending and receiveing moves and commands
 * between the host and the client during online play. It is executed in its own
 * thread to allow the GUI to update independently.
 */
public class OnlineConnection implements Runnable {

    private ObjectOutputStream output;              //Output Stream
    private ObjectInputStream input;                //Input Stream
    private ServerSocket serverSocket;              //Server Socket
    private Socket connection;                      //Socket
    private boolean host = true;                    //True if client is hosting game
    private Location[] moveQueue = new Location[2]; //Move waiting to be sent to own's Chess class

    /*
     * Constructor
     */
    public OnlineConnection(boolean host) {
        this.host = host;
    }

    /*
     * Starts a new client (as opposed to a host).
     */
    public void startClient() {
        try {
            connect();
            setupStreams();
            whilePlaying();
        } catch (EOFException e) {
            System.out.print("\nConnection ended");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            exit();
        }
    }

    /*
     * Starts a new host (as opposed to a client).
     */
    public void startServer() {

        try {
            serverSocket = new ServerSocket(1704, 100);
            while (true) {
                try {
                    waitForConnection();
                    setupStreams();
                    whilePlaying();
                } catch (EOFException e) {
                    System.out.print("\nServer ended the connection!");
                } finally {
                    exit();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
     * Seek connection if joining a game.
     */
    private void connect() throws IOException {
        String serverIP = JOptionPane.showInputDialog(null, "Enter host's IP address (don't include port):", "e.g. 75.214.1.69");

        Chess.chess.online.setConnectionStatus("Attempting connection...");
        connection = new Socket(InetAddress.getByName(serverIP), 1704);
        Chess.chess.online.setConnectionStatus("Connected");
        Chess.chess.online.setOpponent(connection.getInetAddress().getHostName());
    }

    /*
     * Wait for connection if hosting a game.
     */
    private void waitForConnection() throws IOException {

        Thread t = new Thread(new Runnable() {

            public void run() {
                try {
                    JOptionPane.showMessageDialog(null, "Your LOCAL IP address is: " + InetAddress.
                            getLocalHost() + "\nThis program uses port #1704.");
                } catch (UnknownHostException ex) {
                    Logger.getLogger(OnlineConnection.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        });
        t.start();

        Chess.chess.online.setConnectionStatus("Waiting for connection...");
        connection = serverSocket.accept();
        Chess.chess.online.setConnectionStatus("Connected");
        Chess.chess.online.setOpponent(connection.getInetAddress().getHostName());

    }

    /*
     * Setup corresponding Input/Output streams.
     */
    private void setupStreams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        System.out.print("Streams connected\n");
    }

    /*
     * Listen for new moves in the input stream.
     */
    private void whilePlaying() throws IOException {
        String message = "";
        do {
            try {
                message = (String) input.readObject();
                decodeMove(message);    //Decode move from String format to Location[] format
                Chess.moveRecieved();   //Alert Chess that a move has been recieved
            } catch (ClassNotFoundException e) {
                System.out.print("\nConnection Lost");
            }
        } while (!message.equals("/Exit"));
    }

    /*
     * Sends moves through the output stream.
     */
    public void sendMove(Location from, Location to) {
        String move = encodeMove(from, to); //Encodes Location[] format to String format
        try {
            output.writeObject(move);
            output.flush();
        } catch (IOException e) {
            System.out.print("\nERROR");
        }
    }

    /*
     * Closes connections.
     */
    public void exit() {
        try {
            output.close();
            input.close();
            connection.close();
            Chess.chess.online.setConnectionStatus("Not Connected");
            Chess.chess.online.setOpponent("None");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Decodes a move from its string format (e.g. "D2D4") and stores it in the
     * moveQueue.
     */
    private void decodeMove(String moveString) {

        moveQueue[0] = new Location((char) moveString.charAt(0), Character.
                getNumericValue(moveString.charAt(1)));
        moveQueue[1] = new Location((char) moveString.charAt(2), Character.
                getNumericValue(moveString.charAt(3)));
    }

    /*
     * Encodes a move from a Location[] format into a String format.
     */
    private String encodeMove(Location from, Location to) {
        String send = "";

        send += "" + from.getChessX() + from.getChessY() + to.getChessX() + to.
                getChessY();

        return send;
    }

    /*
     * Returns the last move recieved and clears the queue
     */
    public Location[] getLastMove() {
        Location[] send = moveQueue;
        moveQueue = new Location[2];

        return send;
    }

    /*
     * Run in new thread
     */
    @Override
    public void run() {
        if (host) startServer();
        else startClient();
    }
}
