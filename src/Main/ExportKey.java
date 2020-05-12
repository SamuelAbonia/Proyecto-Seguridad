package Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.KeyPair;
import java.util.Base64;

public class ExportKey {
	
	private KeyGenerator keyGenerator;

	public void generateFiles() throws IOException {
		keyGenerator = new KeyGenerator();
		KeyPair keyPair = keyGenerator.generateKeys();
		String publicPath = "./prueba.pub";
		String privatePath = "./private.txt";

        File publicKeyFile = new File(publicPath);
        File privateKeyFile = new File(privatePath);
   
        Base64.Encoder encoder = Base64.getEncoder();
        
        byte[] privateKey = keyPair.getPrivate().getEncoded();
        
        Writer out = new FileWriter("./private" + ".key");
        out.write("-----BEGIN RSA PRIVATE KEY-----\n");
        out.write(encoder.encodeToString(privateKey));
        out.write("\n-----END RSA PRIVATE KEY-----\n");
        out.close();
        
        byte[] publicKey = keyPair.getPublic().getEncoded();
        
        Writer outPublic = new FileWriter("./public" + ".key");
        outPublic.write("-----BEGIN RSA PUBLIC KEY-----\n");
        outPublic.write(encoder.encodeToString(publicKey));
        outPublic.write("\n-----END RSA PUBLIC KEY-----\n");
        outPublic.close();
        

	}
}
