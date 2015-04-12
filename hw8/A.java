import java.io.*;
import java.util.*;

class A{

	public static boolean[] primes;

	public static boolean isPrime(long n) {
	    if (n <= 3) {
	        return n > 1;
	    } else if (n % 2 == 0 || n % 3 == 0) {
	        return false;
	    } else {
	        double sqrtN = Math.floor(Math.sqrt(n));
	        for (int i = 5; i <= sqrtN; i += 6) {
	            if (n % i == 0 || n % (i + 2) == 0) {
	                return false;
	            }
	        }
	        return true;
	    }
	}

	public static void main(String[] args) throws Exception{
		primes = new boolean[1000000];
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;

		for(int i=0; i < 1000000; i++){
			primes[i] = isPrime(i);
		}

		while((line=in.readLine()) != null){
			int num = Integer.parseInt(line);
			if(num == 0){
				break;
			}
			boolean flag = true;
			for(int i=0; i < num; i++){
				if(primes[i] == true){
					if(primes[num-i] == true){
						System.out.println(num + " = " + i + " + " + (num-i) );
						flag = false;
						break;
					}
				}
			}
			if(flag == true){
				System.out.println("Goldbach's conjecture is wrong");
			}

		}


	}


}