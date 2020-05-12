package Main;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		ExportKey exportKey = new ExportKey();
		try {
			exportKey.generateFiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
