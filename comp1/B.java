import java.io.*;
import java.util.*;

public class B{

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s = in.readLine();
		int sLength = s.length();

		char[] array = s.toCharArray();

		int numDays = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());

		for(int i =0; i < numDays; i++){
			int startPosition = Integer.parseInt(st.nextToken());
			int end = ((sLength-startPosition) + 1);
			int dist = (end-startPosition);
			Stack stack = new Stack();
			int dex = 0;
			for(int j=startPosition; j <= (startPosition+dist); j++){
				Character temp = array[end - dex -1];
				array[end-dex-1] = array[startPosition-1];
				array[startPosition-1] = temp;

			}
			int index = startPosition -1;

			while(!stack.empty()){
				array[index] = (char)stack.pop();
				index++;
			}

		}
		for(int i=0; i < array.length; i++){
			System.out.print(array[i]);
		}
	}

}