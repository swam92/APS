import java.io.*;
import java.util.*;

class UltraQS{

	static int arrSize;
	static int[] array;
	public static void merge(int asize, int bsize, int[] a, int[] b){
		int mergdex = asize + bsize;
		int[] merged = new int[mergdex+1];
		//System.out.println("asize : " +asize + "bsize : " + bsize + " length a " + a.length + " length b " + b.length );
		
		System.out.println("a is : " + "\n");
		for(int g: a){
			System.out.println(g);
		}
		System.out.println("b is : " + "\n");
		for(int g: b){
			System.out.println(g);
		}

		while(asize>0 && bsize > 0){
			System.out.println("b[bsize] = " + b[bsize] + " a[asize] = "+ a[asize]);
			if(b[bsize] >= a[asize]){
				merged[mergdex] = b[bsize];
				bsize=bsize-1;
			}
			else{
				merged[mergdex] = a[asize];
				asize=asize-1;
			}

			mergdex=mergdex-1;
		}

		if(asize >=1){
			while(asize != 0){
				merged[asize] = a[asize];
				asize=asize-1;
			}
		}
		else{
			while(bsize >=1){
				merged[bsize] = b[bsize];
				bsize = bsize -1;
			}
		}
			System.out.println("merged is : "+ "\n");
		for(int g: merged){
			System.out.println(g);
		}
	}

	public static void mergeSort(int n, int[] data){
		if(n>1){
			//System.out.println("size is " + n);
			int sizeb;
			int sizea = (n/2);
			if(n%2==0){
				sizeb=sizea;
			}
			else{
				sizeb = sizea+1;
			}

			int[] a = new int[sizea+1];
			int[] b = new int[sizeb+1];

			for(int j=1;j<=sizea;j++){
				a[j] = data[j];
			}
			int count = 1;
			for(int j=(sizea+1);j<=n;j++){
				b[count] = data[j];
				count++;
			}
			
			for(int g:a){
				System.out.println("a is: " + g);
			}
			for(int g:b){
				System.out.println("b is: " + g);
			}
			mergeSort(sizea,a);
			mergeSort(sizeb,b);
			
			merge(sizea, sizeb, a, b);
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = null;

		while((line=in.readLine()) != null){
			int sequenceLength = Integer.parseInt(line);
			if(sequenceLength==0){
				return;
			}
			 array = new int[sequenceLength+1];
			for(int i=1; i <sequenceLength+1;i++){
				int next = Integer.parseInt(in.readLine());
				array[i] = next;
			}//end for
			mergeSort(sequenceLength,array);

		}//end while
	}//end main


}