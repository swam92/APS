import java.io.*;
import java.util.*;

public class Calendar{

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		int count = 0;
		int[][] inputArray = new int[30][3]; 
		
		while((line = in.readLine()) != null){
			String[] elems = line.split("\\s");
				
			System.out.println(elems[0] + "   " + elems[1]);

			if(elems[0].equals("0") && elems[1].equals("0")) {
				break;
			}

			for(int i=0; i < elems.length; i++){
				inputArray[count][i] = Integer.parseInt(elems[i]);
			}
			count++;

		}//end while

		for(int j=0;j<30;j++){
			for(int k=0; k< 3; k++){
				System.out.print(" " + inputArray[j][k]);
			}
			System.out.print("\n");
		}

		findConflict(inputArray, count);

	}//end main function

	public static void findConflict(int[][] arr, int count){
		int nonRepeatingTasks = 0;
		int repeatingTasks = 0;
		int total = 0;
		int rowCount = 0;
		
		int rowIndex = 0;
		int calendarSchedules = 0;
		int tempTotal = 0;
		int totalTasks = 0;
		while(rowIndex < count){
			nonRepeatingTasks = arr[rowIndex][0];
			repeatingTasks = arr[rowIndex][1];
			tempTotal = nonRepeatingTasks + repeatingTasks;
			totalTasks = totalTasks + tempTotal;
			rowIndex = rowIndex + tempTotal + 1;
		}
		calendarSchedules = count - totalTasks;
		rowIndex=0;

		for(int i=0; i < calendarSchedules; i++){
			String answer = "NO CONFLICT";
			int[] calendar = new int[1000000];
			nonRepeatingTasks = arr[i+rowIndex][0];
			repeatingTasks = arr[i+rowIndex][1];
			total = nonRepeatingTasks + repeatingTasks;
			
			for(int j=0; j<total; j++){
				if(j<nonRepeatingTasks){
					int start = arr[j+rowIndex][0];
					int finish = arr[j+rowIndex][1];
					for(int k =start; k < finish; k++){
						if(calendar[k] == 1){
							answer = "CONFLICT";
							break;
						}
					}
				}
				else{
					int start = arr[j+rowIndex][0];
					int finish = arr[j+rowIndex][1];
					int interval = arr[j+rowIndex][2];
				}
			}
			System.out.println(answer);
			rowIndex = rowIndex + total;
		}

		
	}

}


