import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;

import javax.crypto;

public class DataEncryption {
    
    public static void main(String args[]) throws Exception {


        Signature mySignature = Signature.getInstance("SHA258withRSA");
        System.out.println("\n The signature instance -> "+ mySignature);
        System.out.println("Hello World");
        
        KeyPairGenerator key_pair_gen = KeyPairGenerator.getInstance("RSA");
        System.out.println("\n The key pair generator -> "+ key_pair_gen);

        key_pair_gen.initialize(2048);
    }
}