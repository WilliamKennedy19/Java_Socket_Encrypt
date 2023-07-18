package DLP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class EncryptSocket implements SocketCS{

    // Data communication properties
    protected InetAddress host = InetAddress.getLocalHost();
    Socket socket = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    protected int port;
    
    // Encryption properties
    protected int g, p, A, B, secretKey;
    
    public EncryptSocket(int port) throws UnknownHostException, IOException{
        this.port = port;

        //socket = new Socket(host, port);

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Methods for Socket

    // Writes messages to the server
    public void output(String message) throws IOException{
         oos.writeObject(message);
     }

     public void disconnect() throws IOException{
        oos.writeObject("exit");
    }

    // Closes the input and output streams 
    public void closeResources() throws IOException{
        ois.close();
        oos.close();
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Methods for message encryption

    public byte encryptMes(String m) {
        byte plainText = Byte.parseByte(m);

        byte cipherText = (byte) (plainText + secretKey);

        System.out.println(plainText);

        return cipherText;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Methods for DHKE

    // Assigns the public encryption variables to the socket instance
    public void assignPubParam(int g, int p) {

        this.g = g;
        this.p = p;
    }

    public int publicValue(int a) throws ClassCastException{

        this.A = (int) (Math.pow(g, a)) % p;
        return  A;
    }

    // Computes the privately shared key between Alice and Bob
    public int computeServerKey(double C, int c) throws ClassCastException{
        
        return (int) Math.pow(C,c) % p;
    }

    // Computes values for DHKE protocol
    public String computeKey(String incomingMessage) throws IOException, ClassNotFoundException{

        // Generates a random secret key for the Socket
        int a = (int) Math.round(Math.random()*p);

        this.B = incomingMessage.charAt(1);
        assignPubParam((int) incomingMessage.charAt(4), (int) incomingMessage.charAt(6));

        this.secretKey = computeServerKey(B, a);
        
        return Integer.toString(publicValue(a));

    }

    

}
