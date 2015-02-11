import java.io.*;
import java.util.*;

class Calendar1{

    /*above 1 million array
    boolean...bitset 
    return
    */
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		int count = 0;
		int interval = 0;
		
        while (in.hasNext()) {
			boolean flag = false;
			int nonRepeating = in.nextInt();
			int repeating = in.nextInt();
        	int[] calendar = new int[1000000];
        	
        	if(nonRepeating==0 && repeating==0){
        		break;
        	}

           	for(int i=0; i<nonRepeating;i++){

            	int start=in.nextInt();
            	int finish = in.nextInt();
            	if(start==0 && finish==0){
                    return;
            	}
            	for(int k= start; k < finish; k++){
            		if(calendar[k]==1){
            			flag = true;
            			break;
            		}
                    else{
            		calendar[k] = 1;
                    }
            	}
            	if(flag==true){
            		break;
            	}
            }
            if(flag==false){
            	for(int j=0; j<repeating;j++){
            		int start=in.nextInt();
            		int finish = in.nextInt();
                    if(start==0 && finish==0){
                        return;
                    }
            		interval = in.nextInt();
                    if(finish==0&&interval==0){
                        return;
                    }
            		int p;
            		int duration = finish - start;

                    //find each offset
                    for(int k=start; k<=finish; k+=interval){
            			if(start>1000000){
            				break;
            			}

                        //index from offset to finish	
            			for(p=start;p<=finish;p++){
                            if(p < 1000000){
            					if(calendar[p]==1 && p!=finish){
            						flag=true;
            						break;
            					}
            					calendar[p]=1;
                            }
                            else{
                                break;
                            }
            			}
                         //if theres an overlap
            		     if(flag==true){
            				break;
            			}
            		}
            	}

        	}

        	if(flag==false){
        		System.out.println("NO CONFLICT");
        	}
        	else{
        		System.out.println("CONFLICT");
        	}
        }
	}//end main function

}