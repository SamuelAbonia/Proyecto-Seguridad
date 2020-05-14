package Main;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		String bienvenida = ""
				+ "  ____  _                           _     _             \n" + 
				" |  _ \\(_)                         (_)   | |  ____      \n" + 
				" | |_) |_  ___ _ ____   _____ _ __  _  __| | / __ \\ ___ \n" + 
				" |  _ <| |/ _ \\ '_ \\ \\ / / _ \\ '_ \\| |/ _` |/ / _` / __|\n" + 
				" | |_) | |  __/ | | \\ V /  __/ | | | | (_| | | (_| \\__ \\\n" + 
				" |____/|_|\\___|_| |_|\\_/ \\___|_| |_|_|\\__,_|\\ \\__,_|___/\n" + 
				"                                             \\____/     \n" + 
				"                                                        "
				+ "\nEsta es la aplicación de seguridad\n"
				+ "Para comenzar a utilizarlo puedes elegir entre estas tres opciones "
				+ "escribe:\n"
				+ "1 : Crear llave publica y privada \n"
				+ "2 : Firmar archivo\n"
				+ "3 : Verificación de la firma\n"
				+ "Exit : para terminar la ejecución del programa";
		
		System.out.println(bienvenida);
		
		Scanner scan = new Scanner(System.in);
		String reader = "";
		
		while(!reader.equalsIgnoreCase("Exit")) {
			reader = scan.next();
			if(reader.equals("1")) {
				String opciones = "Generando archivos\n"
						+ "----------------"
						+ "----------------"
						+ "----------------";
				System.out.println(opciones);
				
				ExportKey exportKey = new ExportKey();
				try {
					exportKey.generateFiles();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				opciones = "Archivos generados";
				System.out.println(opciones);
				
			}else if(reader.equals("2")) {
				
				String opciones = "Ahora ingrese las siguientes rutas\n"
						+ "1: Ruta del archivo a firmar\n"
						+ "2: Ruta del archivo con de llave privada\n"
						+ "3: Ruta donde guardar el archivo";
				
				System.out.println(opciones);
				String toSign = scan.next();
				String pvt = scan.next();
				String saved = scan.next();
				
				Firma firma = new Firma();
				try {
					firma.signature(toSign, pvt, saved);
				} catch (InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SignatureException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}else if(reader.equals("3")) {
				int cantidad = 0;
				String opciones = "Ahora ingrese las siguientes rutas\n"
						+ "1: Ruta del archivo de llave publica\n"
						+ "2: Ruta de archivo que contiene la firma\n"
						+ "3: Ruta de archivo con los datos\n";
				System.out.println(opciones);
				String[] rutas = new String[3];
				while(cantidad<=2) {
					rutas[cantidad] = scan.next();

					cantidad++;
				}
				DigitalSignature digitalSignature = new DigitalSignature();
				System.out.println(digitalSignature.verifyDigitalSignature(rutas));
				
			}
			
		}
		

	}

}
