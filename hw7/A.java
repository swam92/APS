import java.util.*;
import java.io.*;

class A{

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;

		while((line=in.readLine()) != null && !(line.equals(""))){
			StringTokenizer st = new StringTokenizer(line);
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			int count = n;
			int next = n;
			int modulus = 0;

			while(n >= k){
				count = count + (n/k);
				modulus = (n%k);
				n = (n/k) + modulus;

			}
			System.out.println(count);
		}

	}


}