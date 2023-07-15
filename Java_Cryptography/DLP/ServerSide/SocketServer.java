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
public class SocketServer extends EncryptSocket{
    
    //static ServerSocket variable
    private ServerSocket server;

    private int secretKey;
    public static int g = 2;
    public static int p = 13;
    public static String A;

    public int port;
    public Socket socket;
    public static ObjectInputStream ois = null;
    public static ObjectOutputStream oos = null;

    public SocketServer(int port) throws IOException, ClassNotFoundException{

        super(port);

        this.port = port;

        server = new ServerSocket(port);
        this.socket = server.accept(); 
        oos = new ObjectOutputStream(socket.getOutputStream());
    }

    public void listenForConnections() throws IOException{
        
        while (true) {

            this.socket = server.accept(); 

        }
    }

}
