package Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import java.util.Base64.Decoder;


public class DigitalSignature {
	
	public DigitalSignature() {
		// TODO Auto-generated constructor stub

	}
	
	// We need the next files:
	// - the public key
	// -the signature
	//- and the data files.
	
	
	public  String verifyDigitalSignature(String[] args) {
		
		
			String answer = "";
		
			try {
			
			
//			// first Input and Convert the Encoded Public Key Bytes
//			FileInputStream keyfis = new FileInputStream(args[0]);
//			byte[] encKey = new byte[keyfis.available()];  
//			keyfis.read(encKey);
//
//			keyfis.close();
//			
//		    Path path = Paths.get(args[0]);
//		    byte[] bytes = Files.readAllBytes(path);
//		    	 
//			X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(bytes);
//			
//			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//			
//			PublicKePublicKey pubKey = keyFactory.generatePublic(pubKeySpec);y pubKey = keyFactory.generatePublic(pubKeySpec);
			
				 File f = new File(args[0]);
		          FileInputStream fis = new FileInputStream(f);
		          DataInputStream dis = new DataInputStream(fis);
		          byte[] keyBytes = new byte[(int) f.length()];
		          dis.readFully(keyBytes);
		          dis.close();

		          String temp = new String(keyBytes);
		          String publicKeyPEM = temp.replace("-----BEGIN RSA PUBLIC KEY-----\n", "");
		          publicKeyPEM = publicKeyPEM.replace("\n-----END RSA PUBLIC KEY-----\n", "");


		          Decoder b64= Base64.getDecoder();
		          byte[] decoded = b64.decode(publicKeyPEM);

		          X509EncodedKeySpec spec =
		                new X509EncodedKeySpec(decoded);
		          KeyFactory kf = KeyFactory.getInstance("RSA");
		          PublicKey pubKey = kf.generatePublic(spec);
			
			
			
			// second Input the Signature Bytes
			
			FileInputStream sigfis = new FileInputStream(args[1]);
			byte[] sigToVerify = new byte[sigfis.available()]; 
			sigfis.read(sigToVerify);
			sigfis.close();
			
			
			// initialize the process to verify the signature
			
				
			
			// get the instande of de the signature
			
			Signature sig = Signature.getInstance("SHA256withRSA");
			
			// initialize the signature object
			
			sig.initVerify(pubKey);
			
			// Supply the Signature Object With the Data to be Verified
			
			FileInputStream datafis = new FileInputStream(args[2]);
			BufferedInputStream bufin = new BufferedInputStream(datafis);
			
			byte[] buffer = new byte[1024];
			int len;
			while (bufin.available() != 0) {
			    len = bufin.read(buffer);
			    sig.update(buffer, 0, len);
			};

			bufin.close();
			
			
			// Verify the Signature
			
			boolean verifies = sig.verify(sigToVerify);
			answer = "signature verifies: " + (verifies ? "OK" : "Not OK");

			return answer;
	
			
			
			
			
		}catch (Exception e) {
			System.err.println("Caught exception " + e.toString());

		}
		
			return answer;
		
	}
	
	
}

