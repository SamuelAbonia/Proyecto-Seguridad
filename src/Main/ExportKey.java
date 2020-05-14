package Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.KeyPair;
import java.security.PrivateKey;
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
//        
//        byte[] privateKey = keyPair.getPrivate().getEncoded();
        System.out.println("Formato privada: "+keyPair.getPrivate().getFormat());
//        
//        Writer out = new FileWriter("./private" + ".key");
//        out.write("-----BEGIN RSA PRIVATE KEY-----\n");
//        out.write(encoder.encodeToString(privateKey));
//        out.write("\n-----END RSA PRIVATE KEY-----\n");
//        out.close();
//        
        byte[] publicKey = keyPair.getPublic().getEncoded();
        System.out.println("Formato publica: "+keyPair.getPublic().getFormat());
        
        Writer outPublic = new FileWriter("./public" + ".pub");
        outPublic.write("-----BEGIN RSA PUBLIC KEY-----\n");
        outPublic.write(encoder.encodeToString(publicKey));
        outPublic.write("\n-----END RSA PUBLIC KEY-----\n");
        outPublic.close();
        
        PrivateKey pvt = keyPair.getPrivate();
        FileOutputStream out = new FileOutputStream("./private" + ".key");
        out.write(pvt.getEncoded());
        out.close();
        
//        PrivateKey pub = keyPair.getPrivate();
//        FileOutputStream out2 = new FileOutputStream("./public" + ".pub");
//        out2.write(pub.getEncoded());
//        out2.close();

	}
}
