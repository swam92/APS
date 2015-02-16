import java.io.*;
import java.util.*;

class Pot{

	public static void measure(int x, int y){

	}

	public static void set(int x, int y){

	}

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int numResistors = Integer.parseInt(in.readLine());
		int count = 0;
		String line = null;
		int[] ohmArray = new int[numResistors];

		while((line = in.readLine()) != null){
			String[] elems = line.split("\\s+");
			
			if(elems[0].equals("END")){
				count = 0;
				continue;
			}

			if(elems[0].equals("0")){
				return;
			}

			/*THE LINE TELLS US OHMS IN A GIVEN RESISTOR*/
			if(count < numResistors){
				ohmArray[count] = Integer.parseInt(elems[0]);
			}

			/*In THE ELSE CASE THE LINE IS AN INSTRUCTION*/
			else{
				//set instruction
				if(elems[0].equals("S")){
					set(Integer.parseInt(elems[1]), Integer.parseInt(elems[2]));
				}
				//measure instruction
				else{
					set(Integer.parseInt(elems[1]), Integer.parseInt(elems[2]));
				}
			}
			

		}


	}


}