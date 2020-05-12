package Main;
import java.security.*;

public class KeyGenerator {

	public KeyGenerator() {

	}

	public KeyPair generateKeys() {
		KeyPairGenerator generator;
		try {

			generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(2048);
			KeyPair keyPair = generator.generateKeyPair();
			return keyPair;

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

}
