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
    private static int port;
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
        
        try {
            server = new ServerSocket(port);
        }

        catch (UnknownHostException e) {
            System.out.println("Unknown host");
        }

        catch (IOException e) {
            System.out.println("Super class IO Exception");
        }

        socket = server.accept();

        this.assignPubParam(g,p);
        int a = (int) Math.round(Math.random()*p);
        A = this.publicValue(a);
        this.oos = new ObjectOutputStream(socket.getOutputStream());
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        EncryptServer eServer = new EncryptServer(port);

        eServer.listenForConnections();
    }

    public void listenForConnections() throws IOException, ClassNotFoundException{

        this.oos = new ObjectOutputStream(socket.getOutputStream());
        
        while (true) {

            this.socket = server.accept(); 

            output(Integer.toString(A));

            ois = new ObjectInputStream(socket.getInputStream());

            String m = (String) ois.readObject();

            if (m == "Client Connect") {
                oos.writeObject("(" + g + "," + p + ")");
            }
        }
    }

}
