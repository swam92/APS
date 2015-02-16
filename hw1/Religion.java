import java.util.*;
import java.io.*;

class Religion{

	static int [] array;

	public static int find(int x){
		if(array[x] != x){
			array[x] = find(array[x]);
		}
		
		return array[x];
	}

	public static void union(int x, int y){
		int xRoot = find(x);
		int yRoot = find(y);
		array[yRoot] = xRoot;
	}

	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		int caseCount = 0;
		int count;

		while(in.hasNext()){
			count = 0;
			int numStudents = in.nextInt();
			int m = in.nextInt();

			if(numStudents==0 && m ==0){
				return;
			}
			caseCount++;

			array = new int[numStudents+1];
			for(int j=1;j<array.length;j++){
				array[j] = j;
			}

			for(int i=0; i < m; i++){
				int studentOne = in.nextInt();
				int studentTwo = in.nextInt();
				if(studentOne==0&&studentTwo==0){
					return;
				}

				if(array[studentTwo] == studentTwo){
					int studentOneParent = find(studentOne);
					union(studentOne,studentTwo);
				}
				else if(array[studentTwo] != studentTwo){
					int studentTwoParent = find(studentTwo);
					int studentOneParent = find(studentOne);
					union(studentOneParent,studentTwoParent);
				}
			}//end for

			for(int k=1; k< array.length;k++){
				if(array[k]!= k){
					count++;
				}
			}

			/*for(int y=1;y<array.length;y++){
				System.out.println(array[y]);
			}*/

		if(count==1){
			System.out.println("Case " + caseCount + ": " + count);
		}
		else{
		System.out.println("Case " + caseCount + ": " + (numStudents- count) );
		}

		}//end while
	
	}

}