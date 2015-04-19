import java.util.*;
import java.io.*;

class A{

	public static int convert(char c){
		int num = c-48;
		return num;
	}

   public static String removeCharAt(String s, int pos) {
      return s.substring(0, pos) + s.substring(pos + 1);
   }

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int cases = Integer.parseInt(in.readLine());
		for(int i=0; i < cases; i++){
			String s = in.readLine();
			int length = String.valueOf(s).length();

			int acc = 0;
			for(int j=0; j < length; j++){
				acc+= convert(s.charAt(j));
			}
			int count = 0;
			while(true){
				//System.out.println("acc is " + acc + " length is " + length + " count is " + count);

				if(length == 0){
					break;
				}
				if(length > 1){
					boolean flag = false;
					for(int j=0; j < length; j++){
						int temp = convert(s.charAt(j));

						if( ((acc-temp) %3) == 0){
							int index = s.indexOf(s.charAt(j));
							//System.out.println("	index is " + index);
							s = removeCharAt(s, index);
							acc = acc - temp;
							flag = true;
							break;
						}
					}//end for
					if(flag == false){
						count++;
						break;
					}
				}
				else{
					length = length - 1;
					continue;
				}
				length = length - 1;
				
				count++;
				if(acc%3 != 0){
					break;
				}

			}//end while
			if(count%2 == 0)
				System.out.println("Case " + (i+1) + ": " + "S");
			else
				System.out.println("Case " + (i+1) + ": " + "T");
		}
	}//end main


}