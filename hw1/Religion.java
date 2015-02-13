import java.util.*;
import java.io.*;

class Religion{

	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		int caseCount = 0;
		
		while(in.hasNext()){
			caseCount++;
			int numStudents = in.nextInt();
			int m = in.nextInt();

			if(numStudents==0 && m ==0){
				break;
			}
			int[] peopleArray = new int[numStudents+1];

			for(int i=0; i < m; i++){
				int studentOne = in.nextInt();
				int studentTwo = in.nextInt();
				
				if(peopleArray[studentTwo] == 0){
					peopleArray[studentTwo] = studentOne;
				}
				else{
					int index = peopleArray[studentTwo];
					int previous = 0;
					while(index != 0){
						previous = index;
						index = peopleArray[index];
					}
					peopleArray[studentTwo] = previous;
				}

			}//end for
		int count =0;
		for(int k=1; k < peopleArray.length;k++){
			if(peopleArray[k]==0){
				count++;
			}
		}
		System.out.println("Case " + caseCount + ": " + count);


		}//end while
	
	}

}