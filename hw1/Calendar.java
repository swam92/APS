import java.io.*;
import java.util.*;

class Calendar{

	public static void main(String[] args) throws Exception{
		//BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Scanner in = new Scanner(System.in);
		Scanner first = new Scanner(System.in);
		int count = 0;
		
		int[][] inputArray = new int[30][3]; 

		while (in.hasNextLine()) {
			System.out.println("line");
			String line = in.nextLine();
            Scanner lineScan = new Scanner(line);
            int temp=0;
   		 	while (lineScan.hasNext()){
   		 		int num = lineScan.nextInt();
   		 		inputArray[count][temp] = num;
   		 		//System.out.println(num);
   		 		temp++;
            }
   		 	count++;
		}

		count = count; 
		System.out.println(count);


		/*while((line = in.readLine()) != null){
			String[] elems = line.split("\\s");
				
			//System.out.println(elems[0] + "   " + elems[1]);

			if(elems[0].equals("0") && elems[1].equals("0")) {
				break;
			}

			for(int i=0; i < elems.length; i++){
				inputArray[count][i] = Integer.parseInt(elems[i]);
			}
			count++;

		}//end while*/

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

		int practiceCount = 0;


		while(rowIndex < count){
			nonRepeatingTasks = arr[rowIndex][0];
			repeatingTasks = arr[rowIndex][1];
			tempTotal = nonRepeatingTasks + repeatingTasks;
			totalTasks = totalTasks + tempTotal;
			rowIndex = rowIndex + tempTotal + 1;
		}
		calendarSchedules = count - totalTasks;
		rowIndex=0;

		boolean flag = false;
		for(int i=0; i < calendarSchedules; i++){
			String answer = "NO CONFLICT";
			int[] calendar = new int[1000000];
			nonRepeatingTasks = arr[i+rowIndex][0];
			repeatingTasks = arr[i+rowIndex][1];
			total = nonRepeatingTasks + repeatingTasks;
			
			if(total == 0){
				flag = true;
				break;
			}

			for(int j=0; j<total; j++){
				practiceCount++;

				int start = arr[j+rowIndex+i+1][0];
				int finish = arr[j+rowIndex+i+1][1];

				if(j<nonRepeatingTasks){

					//System.out.println("start is " + start +" and finish is " + finish);

					for(int k =start; k < finish; k++){
						if(calendar[k] == 1){
							answer = "CONFLICT";
							break;
						}
						else{
							calendar[k] = 1;
						}
					}
				}

				else{
					int interval = arr[j+rowIndex+i+1][2];

					int times = (1000000 / interval);
					
					for(int k=0; k < times; k++){
						for(int h=(k*times); h < 1000000; h++){
							if(calendar[h] == 1){
								answer = "CONFLICT";
								break;
							}
							else{
							calendar[h] = 1;
							}
						}
					}
				}
			}
			if(flag == false){
			System.out.println(answer);
		}
			rowIndex = rowIndex + total;
		}

		
	}

}


