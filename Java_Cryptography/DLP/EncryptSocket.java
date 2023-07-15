package DLP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.stream.IntStream;

public class EncryptSocket implements SocketCS{

    // Data communication properties
    InetAddress host = InetAddress.getLocalHost();
    Socket socket = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    protected int port;
    
    // Encryption properties
    int g, p, A;
    private int secretKey;
    
    public EncryptSocket(int port) throws UnknownHostException, IOException{
        this.port = port;

        try {
            socket = new Socket(host.getHostName(), port);
            this.oos = new ObjectOutputStream(socket.getOutputStream());
        }

        catch (UnknownHostException e) {
            System.out.println("Unknown host");
        }

        catch (IOException e) {
            System.out.println("Super class IO Exception");
        }

    }


    // Writes messages to the server
    public void output(String message) throws IOException{
         oos.writeObject(message);
     }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Methods for DHKE

    // Assigns the public encryption variables to the socket instance
    public void assignPubParam(int g, int p) {

        this.g = g;
        this.p = p;
    }

    public int publicValue(int a) throws ClassCastException{
        this.A = (int) Math.pow(g, a) % p;
        return  A;
    }

    // Computes the privately shared key between Alice and Bob
    public int computeServerKey(double C, int c) throws ClassCastException{
        
        return (int) Math.pow(C,c) % p;
    }

    // Computes values for DHKE protocol
    public void computeKey(String incomingMessage) throws IOException, ClassNotFoundException{

        // Generates a random secret key for the Socket
        int a = (int) Math.round(Math.random()*p);

        // Computes public value
        String pubVal = String.valueOf(publicValue(a));
        this.output(pubVal);
        
        // Receives the server public value and computes the secret key
        int servPubVal = (int) ois.readObject();
        secretKey = (int) computeServerKey(servPubVal,a);
    }

}
