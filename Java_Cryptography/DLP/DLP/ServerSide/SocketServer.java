package DLP.ServerSide;
//package com.journaldev.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

import DLP.EncryptSocket;
import DLP.SocketCS;
import DLP.dlp;

/**
 * This class implements java Socket server
 * @author pankaj
 *
 */
public class SocketServer extends EncryptSocket{
    
    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9876;

    private int secretKey;

    public static int g = 2;
    public static int p = 13;
    public static String A;

    public static ObjectInputStream ois = null;
    public static ObjectOutputStream oos = null;

    public SocketServer() throws IOException, ClassNotFoundException{
        super(port,g, p);
        
    }
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{

        server = new ServerSocket(port);
        

        while(true){
            System.out.println("Waiting for the client request");
            //creating socket and waiting for client connection
            Socket socket = server.accept();    
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);
            //create ObjectOutputStream object
            oos = new ObjectOutputStream(socket.getOutputStream());
            //write object to Socket
            oos.writeObject("(" + g + "," + p + "," + A + ")");
            //close resources
            //ois.close();
            //oos.close();
            //socket.close();
            //terminate the server if client sends exit request
            if(message.equalsIgnoreCase("exit")) break;
        }
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();
    }

    public void encrypt(String incomingMessage) throws IOException, ClassNotFoundException{

        int a = (int) Math.round(Math.random()*p);
        A = String.valueOf(publicValue(a));
        this.output(A);
        

        int servPubVal = (int) ois.readObject();
        secretKey = (int) computeKey(servPubVal,a);
    }

    public void output(String message) throws IOException{
        oos.writeObject(message);
    }
}