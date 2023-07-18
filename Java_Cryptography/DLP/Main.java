package DLP;

import java.io.IOException;
import java.net.UnknownHostException;

import DLP.ClientSide.EncryptClient;
import DLP.ServerSide.EncryptServer;

public class Main {
    
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {

    EncryptClient client = new EncryptClient(9876);
    client.connect();

    }
    
}



