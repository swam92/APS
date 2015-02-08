import java.io.*;
import java.util.*;

public class Main{
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> words = new ArrayList<String>();
		String line = null;


		while((line = in.readLine()) != null){
			if(line.charAt(0) == '#'){
				break;
			}
			String[] elems = line.split("\\s+");
		
			for(String elem : elems){ 
//				if(elem.equals("#")){
//					break;
//				}
//				else{
					words.add(elem);
//				}				
	    	}
		}
		findAnanagrams(words);
	}

	public static void findAnanagrams(ArrayList<String> words){
		ArrayList<String> ananagrams = new ArrayList<String>();
		for(String inputWord: words){
			char[] toSort = inputWord.toLowerCase().toCharArray();
			Arrays.sort(toSort);
			
			String sortedInputWord = new String(toSort);

			int equalityCount = 0;
			for(String index:words){

				char[] sortToCheck = index.toLowerCase().toCharArray();
				Arrays.sort(sortToCheck);
				String sortedToCheck = new String(sortToCheck);

				if(sortedToCheck.equals(sortedInputWord)){
					equalityCount++;
				}
			}
			if(equalityCount == 1){
				ananagrams.add(inputWord);
			}
		}

		Collections.sort(ananagrams);
		for(String wordly:ananagrams){
			System.out.println(wordly);
		}

	}

}