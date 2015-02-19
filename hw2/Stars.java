import java.util.*;
import java.io.*;

class Stars{
	
	static int[] array;
	static int numStars;

	public static int sumQuery(int a, int b) {
		return sumQuery(b) - sumQuery(a - 1);
	}

	public static int sumQuery(int k) {
		int ret = 0;
		while (k > 0) {
			//System.out.println(k);
			ret += array[k];
			k = (k & (k + 1)) - 1 ;
		}
		return ret;
	}

	public static void adjust(int i, int adj) {
        for (; i < array.length; i |= (i + 1)) {
            array[i] += adj;
        }
	}

	public static int getValue(int i) {
		return sumQuery(i, i);
	}

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		numStars = Integer.parseInt(in.readLine());
		array = new int[50000];
		int[] answers = new int[numStars];

		while((line = in.readLine()) != null){
			String[] elems = line.split("\\s+");
			int x = Integer.parseInt(elems[0]);
			int y = Integer.parseInt(elems[1]);
			int answer = sumQuery(x+1);
			answers[answer]++;
			adjust(x+1,1);
		}

		for(int i=0;i<answers.length;i++){
			System.out.println(answers[i]);
		}
		//System.out.println("0");

	}//end main

}