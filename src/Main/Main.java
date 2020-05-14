package Main;

import java.io.IOException;
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
		String reader = scan.next();
		
		while(!reader.equalsIgnoreCase("Exit")) {
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
				String opciones = "Ahora ingrese ";
				
				
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
					System.out.println(cantidad);
					cantidad++;
				}
				System.out.println(rutas);
			}
			
		}
		

	}

}
