import java.util.*;
import java.io.*;

class Scarecrow{

	public static void main(String[] args) throws Exception{
		BufferedReader in =  new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		int numTestCases = Integer.parseInt(in.readLine());
		int caseNum =0 ;
		while((line = in.readLine()) != null){
						caseNum++;
			int fieldDimension = Integer.parseInt(line);
			String fieldDescription = in.readLine();
			int crowCount = 0;
			for(int i=0;i<fieldDimension;i++){
				if(fieldDescription.charAt(i) == '.'){
					i=i+2;
					crowCount++;
				}
			}//end for
			System.out.println("Case " + caseNum + ": " + crowCount);
		}//end out while

	}//end main

}