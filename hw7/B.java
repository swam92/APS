import java.util.*;
import java.io.*;

class B{

	public static int[] convertIntegers(ArrayList<Integer> integers)
	{
    	int[] ret = new int[integers.size()];
    	for (int i=0; i < ret.length; i++)
    	{
        	ret[i] = integers.get(i).intValue();
    	}
    	return ret;
	}

	public static int[] addBaseFour(int first, int second){
		int sum = (first+second);
		if(sum < 4){
			int[] toReturn = {sum,0};
			return toReturn;
		}
		else{
			int result = (sum % 4);
			int carry = 1;
			int[] toReturn = {result, carry};
			return toReturn;
		}
	}

	public static int[] A(int[] one, int[] two){
		ArrayList<Integer> newArray = new ArrayList<Integer>();

		for(int j=two.length-1;j>=1;j--){
			int[] result = addBaseFour(one[j], two[j]);
			if(result[1] == 0){
				newArray.add(result[0]);
			}
			else{
				newArray.add(result[0]);
				two[j-1]++;
			}
		}

		int[] result = addBaseFour(one[0], two[0]);
		newArray.add(result[0]);
		if(result[1] != 0){
			newArray.add(1);
		}
		for(int k=0; k < (8-newArray.size()); k++){
			newArray.add(-1);
		}

		Collections.reverse(newArray);
		int[] toReturn = convertIntegers(newArray);
		return toReturn;
	}

	public static int baseFourMap(String s){
		if(s.equals("V")){
			return 0;
		}
		else if(s.equals("U")){
			return 1;
		}
		else if(s.equals("C")){
			return 2;
		}
		else{
			return 3;
		}
	}

	/*Operation R causes Num2 to have its symbols shifted right one position,
	 with the rightmost symbol being lost and a V being placed into the leftmost position.
	  For example VVCDU would become VVVCD*/
	public static int[] R(int[] two){
		int[] newArray = new int[8];

		for(int i=0; i < (two.length - 1); i++){
			newArray[i+1] = two[i];
		}
		newArray[0] = 0;

		return newArray;
	}

	/*Operation L causes Num2 to have its symbols shifted left one position,
	 with the leftmost symbol being kept and a V placed into the rightmost position.
	 For example VVCDU would become VVCDUV*/
	public static int[] L(int[] two){
		int[] newArray = new int[8];

		for(int i=1; i < two.length; i++){
			newArray[i-1] = two[i];
		}
		newArray[7] = 0;

		return newArray;
	}

	public static int[] populate(String line){
		char[] temporary = line.toCharArray();
		int[] toReturn = new int[8];
		for(int k=0;k<5;k++){
			int toAdd = baseFourMap(String.valueOf(temporary[k]));
			toReturn[k+3] = toAdd;
		}
		return toReturn;
	}

	public static int[] populateLast(String line){
		char[] temporary = line.toCharArray();
		int[] toReturn = new int[8];
		for(int k=0;k<8;k++){
			int toAdd = baseFourMap(String.valueOf(temporary[k]));
			toReturn[k] = toAdd;
		}
		return toReturn;
	}

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;

		int numTablets = Integer.parseInt(in.readLine());
		System.out.println("COWCULATIONS OUTPUT");
		for(int i=0; i < numTablets; i++){
			String op1, op2, op3;
			int[] line1 = new int[8];
			int[] line2 = new int[8];
			int[] line6 = new int[8];

			line1 = populate(in.readLine());
			line2 = populate(in.readLine());

			for(int j=0; j<3;j++){
				String temp = in.readLine();

				if(temp.equals("A")){
					line2 = A(line1,line2);
				}
				else if(temp.equals("L")){
					line2 = L(line2);
				}
				else if(temp.equals("R")){
					line2 = R(line2);
				}
			}

			line6 = populateLast(in.readLine());

			for(int k=0; k < 8; k++){
				if(line6[k] != line2[k]){
					System.out.println("NO");
					break;
				}
				if(k==7){
					System.out.println("YES");
				}
			}

		}//end outer for
		System.out.println("END OF OUTPUT");
	}

}