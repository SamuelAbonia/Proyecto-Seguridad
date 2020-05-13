package Main;

import java.io.*;
import java.security.*;
import java.security.spec.*;

public class DigitalSignature {
	
	// We need the next files:
	// - the public key
	// -the signature
	//- and the data files.
	
	
	public  String verifyDigitalSignature(String[] args) {
		
			String answer = "";
		
			try {
			
			
			// first Input and Convert the Encoded Public Key Bytes
			FileInputStream keyfis = new FileInputStream(args[0]);
			byte[] encKey = new byte[keyfis.available()];  
			keyfis.read(encKey);

			keyfis.close();
			
			
			X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
			
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			
			PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
			
			
			
			
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

