package Main;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;

public class Firma {

    /**
     * @dataFile: Archivo orignal a firmar
     * @pvt: Archivo con la clave privada
     * @signFile: ruta del archivo de salida firmado
     */
    public static void signature(String dataFile, PrivateKey pvt, String signFile)
            throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, IOException {
        
        //Crea un archivo tipo Signature con la clave privada
        // del archivo que entra por parámtero
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(pvt);
        
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