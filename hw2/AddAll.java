import java.io.*;
import java.util.*;

class AddAll{
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = null;

		while((line = in.readLine()) != null){
			int numOperands = Integer.parseInt(line);
			int cost = 0;
			ArrayList<Integer> arr = new ArrayList<Integer>();

			if(numOperands == 0){
				return;
			}

			line = in.readLine();
			String[] operands = line.split("\\s+");
			
			for(int i=0; i < operands.length;i++){
				arr.add(Integer.parseInt(operands[i]));
			}

			int work = 0;
			while(arr.size() > 2){
				Collections.sort(arr);
				int operand1 = arr.get(0);
				int operand2 = arr.get(1);
				int result = operand1+operand2;
				work+=result;
				arr.remove(0);
				arr.remove(0);
				arr.add(result);
			}
			System.out.println(arr.get(0) + arr.get(1) + work);

		}//end while
	}//end main

}