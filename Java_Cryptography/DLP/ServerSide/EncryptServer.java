package DLP.ServerSide;
//package com.journaldev.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

import DLP.EncryptSocket;

/**
 * This class implements java Socket server
 * @author pankaj
 *
 */
public class EncryptServer extends EncryptSocket{
    
    //static ServerSocket variable
    private ServerSocket server;

    private int secretKey;
    public static int g = 2;
    public static int p = 13;
    public static int A;

    public int port;
    public Socket socket;
    public static ObjectInputStream ois = null;
    public static ObjectOutputStream oos = null;

    public EncryptServer(int port) throws IOException, ClassNotFoundException{

        super(port);

        this.port = port;

        server = new ServerSocket(port);
        this.assignPubParam(g,p);
        int a = (int) Math.round(Math.random()*p);
        A = this.publicValue(a);
        this.listenForConnections();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        EncryptServer server = new EncryptServer(9876);
        
    }

    public void listenForConnections() throws IOException, ClassNotFoundException{
        
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
