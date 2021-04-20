package opensourceSW;

import java.util.Scanner;

public class getSnippet {
	public static void genSnippet() {
		Scanner scanner = new Scanner(System.in);
		   
		   public String[5][2] word=null;
		   Set<String> Word2 = new HashSet<>();
		   ArrayList<word> Word  = new ArrayList<>();
		  
		   public tomakeword() {
		            int count = 0;
		            File file = new File("input.txt");
		            try {
		               Scanner scan = new Scanner(file);
		               while (scan.hasNextLine()) {                 
		                  String temp = scan.nextLine();
		                  _word = temp.split(":");
		                  _word[0] = _word[0].trim();
		                  String[] Kor=_word[1].split("/");

		                  for(int i = 0; i<Kor.length; i++) {
		                   Kor[i] = Kor[i].trim();
		                  }
		                  word wordclass = new word(_word[0],Kor);
		       
		                  Word2.add(_word[0]);
		                  Word.add(wordclass);
		                  
		               }
		               scan.close();
		            } catch (FileNotFoundException e) {
		               // TODO Auto-generated catch block
		               System.out.println("파일 경로를 확인하세요.");
		            } 
		         }
}
