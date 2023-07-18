package DLP.ServerSide;
//package com.journaldev.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import DLP.EncryptSocket;

/**
 * This class implements java Socket server
 * @author pankaj
 *
 */
public class EncryptServer extends EncryptSocket{
    
    //static ServerSocket variable
    private ServerSocket server;
    protected int port;
    public Socket socket;
    public ObjectInputStream ois = null;
    public ObjectOutputStream oos = null;

    private int secretKey;
    public static int g = 2;
    public static int p = 13;
    public static int A;


    public EncryptServer(int port) throws IOException, ClassNotFoundException{

        super(port);

        this.port = port;

        // Sets up ServerSocket
        try {
            server = new ServerSocket(port);
        }

        catch (UnknownHostException e) {
            System.out.println("Unknown host");
        }

        catch (IOException e) {
            System.out.println("Super class IO Exception");
        }

        // Computes public key for DHKE
        this.assignPubParam(g,p);
        int a = (int) Math.round(Math.random()*p);
        A = this.publicValue(a);

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        EncryptServer eServer = new EncryptServer(9876);


        eServer.listenForConnections();
    }


    // Listens for clients trying to connect to the server
    public void listenForConnections() throws IOException, ClassNotFoundException{

        System.out.println("The server is waiting for connection");

        
        while (true) {

            this.socket = server.accept(); 
            this.oos = new ObjectOutputStream(socket.getOutputStream());

            output("("+A+","+g+","+p+")");

            ois = new ObjectInputStream(socket.getInputStream());

            String m = (String) ois.readObject();

            //this.secretKey = computeServerKey(Double.parseDouble(m), (int) Math.floor(Math.random()*p));

            System.out.println(m);

        }
    }

    public void output(String message) throws IOException{
        oos.writeObject(message);
    }

}
