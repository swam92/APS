import java.util.*;
import java.io.*;

class Change{
	static long[] cumulativeAnswer = new long[30005];

	public static void getWays(){
		
		cumulativeAnswer[0] = 1;

		for(int i=1;i<=30000;i++){
			cumulativeAnswer[i]+=cumulativeAnswer[i-1];
		}

		for(int k=5;k<=30000;k++){
			cumulativeAnswer[k]+=cumulativeAnswer[k-5];
		}


		for(int g=10;g<=30000;g++){
			cumulativeAnswer[g]+=cumulativeAnswer[g-10];	
		}

		for(int f=25;f<=30000;f++){
			cumulativeAnswer[f]+=cumulativeAnswer[f-25];
		}


		for(int j=50;j<=30000;j++){
			if(j>25000 && j<28000){
			}
			cumulativeAnswer[j]+=cumulativeAnswer[j-50];
		}

	}

	public static void main(String[] args) throws Exception{

		BufferedReader in  = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		getWays();
		while((line = in.readLine()) != null){
			int number = Integer.parseInt(line);
			if(number < 5){
				System.out.println("There is only 1 way to produce " + number+ " cents change." );
			}

			else{
				System.out.println("There are " + cumulativeAnswer[number] + " ways to produce " + number + " cents change.");
			}

		}//end while
	}//end main
}