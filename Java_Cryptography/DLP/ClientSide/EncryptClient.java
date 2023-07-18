package DLP.ClientSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import DLP.EncryptSocket;

/**
 * This class implements java socket client
 * @author pankaj
 *
 */
public class EncryptClient extends EncryptSocket{

    InetAddress host = InetAddress.getLocalHost();;
    Socket socket = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;


    public EncryptClient(int port) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {

        super(port);
        try {
            this.socket = new Socket(host, port);
        }

        catch (UnknownHostException e) {
            System.out.println("Unknown Host");
        }

         catch (IOException e) {
            System.out.println("IO Exception");
         }
         

    }
    
    public void connect() throws IOException, ClassNotFoundException {
        System.out.println("Sending request to Socket Server");

        this.oos = new ObjectOutputStream(socket.getOutputStream());
        
        this.activeConnection();
    }

    // Sends messages to the server and receives messages from the server
    public void activeConnection() throws IOException, ClassNotFoundException{
        

        this.output("Client Connect");
        String outgoing;

        this.ois = new ObjectInputStream(socket.getInputStream());
        String incomingMessage = (String) ois.readObject();
        this.output(this.computeKey(incomingMessage));

        while (true) {
            this.oos = new ObjectOutputStream(socket.getOutputStream());
        }
    }

    

    public String[] encrypt(String m) {
        String[] encryptM = new String[m.length()];

        return encryptM;
    }


    public void output(String message) throws IOException{
        oos.writeObject(message);
    }
}