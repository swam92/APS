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
			boolean overlapFlag = false;
			int nonRepeating = in.nextInt();
			int repeating = in.nextInt();
        	int[] calendar = new int[1000001];
        	if(nonRepeating==0 && repeating==0){
        		return;
        	}

           	for(int i=0; i<nonRepeating;i++){
            	int start=in.nextInt();
            	int finish = in.nextInt();
                int dur= finish-start;
            	if(start==0 && finish==0){
                    return;
            	}
            	for(int k= start; k <= finish; k++){
            		if(calendar[k]==1 && k!=finish && k!=start){
            			overlapFlag = true;
            			break;
            		}
                    else if(calendar[k]==1 && dur==1 && k==start && calendar[start+1]==1){
                        overlapFlag=true;
                        break;
                    }
                    else{
            		  calendar[k] = 1;
                    }
            	}
            }
            	for(int j=0; j<repeating;j++){
                    //if(overlapFlag==false){
            		int start=in.nextInt();
            		int finish = in.nextInt();
                    if(start==0 && finish==0){
                        return;
                    }
            		interval = in.nextInt();
                    if(finish==0&&interval==0){
                        return;
                    }
            		int duration = finish - start;
                    while(start<1000000 && overlapFlag==false){
                        for(int k=start; k<=finish; k++){
                            if(k < 1000000){
                                if(calendar[k]==1 && k!=finish && k!=start){
                                    overlapFlag=true;
                                    break;
                                }

                                else if(calendar[k]==1 && duration==1 && k==start &&calendar[start+1]==1){
                                    overlapFlag=true;
                                    break;
                                }
                                 calendar[k]=1;
                            }

                            else{
                                start = 1000002;
                                break;
                            }

            		    }//end for loop
                        
                        start+=interval;
                        finish+=interval;

                        if(overlapFlag==true){
                            break;
                        }	    
                    }//end while loop
                
                if(overlapFlag==true){
                    break;
                }

                //}//if
                //else{
                //    in.nextInt();
                //    in.nextInt();
                //    in.nextInt();
                //}
        	}//end repeating loop

        	if(overlapFlag==false){
        		System.out.println("NO CONFLICT");
        	}
        	else{
        		System.out.println("CONFLICT");
        	}
        }
	}//end main function

}