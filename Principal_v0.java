/**
 * Lab0: Leitura de Base de Dados N�o-Distribuida
 * 
 * Autor: Adalberto e Victor
 * Ultima atualizacao: 05/09/2023
 * 
 * Referencias: 
 * https://docs.oracle.com/javase/tutorial/essential/io
 * 
 */

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Principal_v0 {

	public final static Path path = Paths			
			.get("src\\fortune-br.txt");
	private int NUM_FORTUNES = 0;

	public class FileReader {

		public int countFortunes() throws FileNotFoundException {

			int lineCount = 0;

			InputStream is = new BufferedInputStream(new FileInputStream(
					path.toString()));
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					is))) {

				String line = "";
				while (!(line == null)) {

					if (line.equals("%"))
						lineCount++;

					line = br.readLine();

				}// fim while
				
			} catch (IOException e) {
				System.out.println("SHOW: Excecao na leitura do arquivo.");
			}
			return lineCount;
		}

		public void parser(HashMap<Integer, String> hm)
				throws FileNotFoundException {

			InputStream is = new BufferedInputStream(new FileInputStream(
					path.toString()));
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					is))) {

				int lineCount = 0;

				String line = "";
				while (!(line == null)) {

					if (line.equals("%"))
						lineCount++;

					line = br.readLine();
					StringBuffer fortune = new StringBuffer();
					while (!(line == null) && !line.equals("%")) {
						fortune.append(line + "\n");
						line = br.readLine();
						// System.out.print(lineCount + ".");
					}

					hm.put(lineCount, fortune.toString());
				
				}// fim while

			} catch (IOException e) {
				System.out.println("SHOW: Excecao na leitura do arquivo.");
			}
		}

		public void read(HashMap<Integer, String> hm)
				throws FileNotFoundException {
			
			Random random_object = new Random(); 
			int number = random_object.nextInt(hm.size());
			
			System.out.println(hm.get(number));
		}

		public void write(HashMap<Integer, String> hm, String new_mensage)
				throws FileNotFoundException {
			
						
			try {
				hm.put((hm.size()- 1), new_mensage);
				Writer wr = new FileWriter(path.toString(), true);
		        BufferedWriter br = new BufferedWriter(wr); 
		        
		        br.write("\n%\n");
		        
		        br.write(new_mensage);
		        
		        br.write("\n%");
		        
		        br.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	public void iniciar() {

		FileReader fr = new FileReader();
		try {
			NUM_FORTUNES = fr.countFortunes();
			HashMap hm = new HashMap<Integer, String>();
			fr.parser(hm);
			fr.read(hm);
			Scanner scanner = new Scanner(System.in);
			String new_mensage = scanner.nextLine();
			fr.write(hm, new_mensage);
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Principal_v0().iniciar();
	}

}
