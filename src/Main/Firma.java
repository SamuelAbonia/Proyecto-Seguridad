package Main;

import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class Firma {

    /**
     * @throws InvalidKeySpecException 
     * @dataFile: Archivo orignal a firmar
     * @pvt: Archivo con la clave privada
     * @signFile: ruta del archivo de salida firmado
     */
    public static void signature(String dataFile, String pvt, String signFile)
            throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, IOException, InvalidKeySpecException {
        
        //Crea un archivo tipo Signature con la clave privada
        // del archivo que entra por parámter
    	
    	Path path = Paths.get(pvt);
    	byte[] bytes = Files.readAllBytes(path);
    	 
    	/* Generate private key. */
    	PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
    	KeyFactory kf = KeyFactory.getInstance("RSA");
    	PrivateKey privateKey = kf.generatePrivate(ks);
    	
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(privateKey);
        
        // Se copia línea por línea el archivo que se desea firmar
        // y se pega en el objeto de Signature
        InputStream in = null;
        try {
            in = new FileInputStream(dataFile);
            byte[] buf = new byte[2048];
            int len;
            while ((len = in.read(buf)) != -1) {
            sign.update(buf, 0, len);
            }
        } finally {
            if ( in != null ) in.close();
        }
        
        // Se exporta un archivo en la ruta que llega por parámetro donde se le agrega la firma
        // del objeto de Signature
        OutputStream out = null;
        try {
            out = new FileOutputStream(signFile);
            byte[] signature = sign.sign();
            out.write(signature);
        } finally {
            if ( out != null ) out.close();
        }
    }
}