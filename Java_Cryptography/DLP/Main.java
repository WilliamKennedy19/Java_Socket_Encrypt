package DLP;

import java.io.IOException;
import java.net.UnknownHostException;

import DLP.ClientSide.SocketClient;

public class Main {
    
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {

    //SocketServer server = new SocketServer();

    SocketClient client = new SocketClient(9876);
    client.connect();

    dlp encrypt = new dlp(2, 13);
    
    }

   public dlp initiateEncryption(int g, int p) {
        
        dlp encrypt = new dlp(g,p);
        return encrypt;

    }
    
}



