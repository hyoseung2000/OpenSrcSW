package opensourceSW;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class getSnippet {
	public static void genSnippet() {
		Scanner scanner = new Scanner(System.in);
		   
		   public String[] word;
		   
		  
		   public void parseword() {
		            int count = 0;
		            File file = new File("input.txt");
		            try {
		               Scanner scan = new Scanner(file);
		               while (scan.hasNextLine()) {                 
		                  String temp = scan.nextLine();
		                  word = temp.split(" ");

		                
		                  
		               }
		               scan.close();
		            } catch (FileNotFoundException e) {
		               // TODO Auto-generated catch block
		               System.out.println("파일 경로를 확인하세요.");
		            } 
		         }
}
