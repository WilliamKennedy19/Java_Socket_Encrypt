package DLP;

import java.io.IOException;
import java.net.UnknownHostException;

import DLP.ClientSide.SocketClient;
import DLP.ServerSide.EncryptServer;

public class Main {
    
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {

    SocketClient client = new SocketClient(9876);
    client.connect();

    }
    
}



