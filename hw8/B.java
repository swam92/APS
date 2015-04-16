import java.io.*;
import java.util.*;
import java.math.*;

class B{

	public static BigInteger[] factorial = new BigInteger[201];

	public static void calculateFactorials(){
		BigInteger fac = new BigInteger("1");
		factorial[0] = new BigInteger("1");
		factorial[1] = fac;
		for(int i=2;i<=200;i++){
			fac = fac.multiply(BigInteger.valueOf(i));
			factorial[i] = fac;
		}
	}
	//w+1
	//w-g+1
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int numCases = Integer.parseInt(in.readLine());
		calculateFactorials();
		for(int i=0; i<numCases; i++){
			StringTokenizer st = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			int accumulator = 0;
			for(int j=0; j<k; j++){
				accumulator += Integer.parseInt(st.nextToken());
			}

			if(k >= ((n+1)/2)){
				System.out.println(0);
				continue;
			}
			int top = ((n-accumulator) + 1);
			int bottom = ((n-accumulator) - k + 1);

			BigInteger one = factorial[top];
			BigInteger two = factorial[bottom];
			BigInteger three = factorial[top-bottom];

			System.out.println(one.divide(two.multiply(three)));

		}

	}


}