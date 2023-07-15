package DLP.ClientSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import DLP.EncryptSocket;
import DLP.SocketCS;

/**
 * This class implements java socket client
 * @author pankaj
 *
 */
public class SocketClient extends EncryptSocket{

    InetAddress host = InetAddress.getLocalHost();;
    Socket socket = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    int port, g, p;

    private int secretKey;


    public SocketClient(int port) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {

        super(port);

        this.socket = new Socket(host.getHostName(), port);
        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.connect();

    }
    
    public void connect() throws IOException, ClassNotFoundException {
        System.out.println("Sending request to Socket Server");
        
        this.activeConnection();
    }

    // Sends messages to the server and receives messages from the server
    public void activeConnection() throws IOException, ClassNotFoundException{
        

        this.output("Hello, my namy is William");
        String outgoing;

        this.ois = new ObjectInputStream(socket.getInputStream());
        String incomingMessage = (String) ois.readObject();
        this.computeKey(incomingMessage);

        while (true) {
            
            
            switch (incomingMessage) {
                case "exit":
                    this.disconnect();
                    break;
                default:
                    System.out.println(incomingMessage);
                
                outgoing = System.console().readLine();

                this.output(outgoing);
            }   
        }
    }

    public void computeKey(String incomingMessage) throws IOException, ClassNotFoundException{

        // g is the primitive element and p is the prime modulus
        g = (int) incomingMessage.charAt(1);
        p = Integer.parseInt(incomingMessage.substring(3, 5));

        // Asks the user for the secret key
        int a = Integer.parseInt(System.console().readLine("Enter your secret value: "));

        // Computers public value and sends it to the server
        String pubVal = String.valueOf(publicValue(a));
        this.output(pubVal);
        
        // Receives the server public value and computes the secret key
        int servPubVal = (int) ois.readObject();
        secretKey = (int) computeKey(servPubVal,a);
    }

    public String[] encrypt(String m) {
        String[] encryptM = new String[m.length()];

        return encryptM;
    }


    public void disconnect() throws IOException{
        oos.writeObject("exit");
    }

    // Closes the input and output streams 
    public void closeResources() throws IOException{
        ois.close();
        oos.close();
    }
}