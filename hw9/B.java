import java.io.*;
import java.util.*;

class B{

	public static int[] buildKMPTable(String pattern)
	{
		int[] table = new int[pattern.length()+1];
		for (int i = 2; i < table.length; ++i)
		{
			int j = table[i-1];
			while (true) 
			{
				if (pattern.charAt(j) == pattern.charAt(i-1)) { table[i] = j+1; break;}
				else if (j == 0) break;
				else j = table[j];
			} 
		}
		return table;
	}
	/** Returns the final state when simulating the DFA built using pattern on the string text */
	public static int simulate(int[] table, String text, String pattern)
	{
		int state = 0;
		int max = -1;
		for (int i = 0; i < text.length(); ++i)
		{
			while (true)
			{
				//System.out.println(text.charAt(i) + " == " + pattern.charAt(state));
				if (text.charAt(i) == pattern.charAt(state)) { 
					state++;
					if(state >= max){
						max = state;
					}
					break; 
				}
				else if (state == 0) break;
				state = table[state];
				//System.out.println("HERE STATE IS " + state);

			} 
			if (state == table.length -1) break;
		}
		return max;
	}

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(in.readLine());

		for(int i=0; i < cases; i++){
			String text = in.readLine();
			String pattern = new StringBuilder(text).reverse().toString();
			int[] table = buildKMPTable(text);

			int finalState = simulate(table, pattern, text);
			String toPrint = new StringBuilder(text.substring(0,finalState)).reverse().toString();
			System.out.println(toPrint);
		}//end for

	}


}