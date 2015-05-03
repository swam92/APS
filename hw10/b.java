import java.io.*;
import java.util.*;

class B{

	public static double distance(double x1, double x2, double y1, double y2){
		double distance = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
		return distance;
	}

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(in.readLine());

		for(int i=0; i < cases; i++){
			StringTokenizer st = new StringTokenizer(in.readLine());
			double ax = Double.parseDouble(st.nextToken());
			double ay = Double.parseDouble(st.nextToken());
			double bx = Double.parseDouble(st.nextToken());
			double by = Double.parseDouble(st.nextToken());
			double cx, cy, dx, dy;
			cx=ax;
			cy=-ay;
			dx=-bx;
			dy=by;
			double acc = distance(cx,dx,cy,dy);
			acc+= distance(ax,bx,ay,by);
			System.out.printf("%.3f\n", acc); 

		}


	}


}