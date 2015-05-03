import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class D {
	static final double EPS = 1e-9;
	static class Point
	{
		public double x, y;
		public Point(double x, double y) { this.x = x; this.y = y; }
		public String toString() { return String.format("(%.03f,%.03f)", x,y); }
	}	
	static double cross(Point a, Point b, Point c)
	{
		double y1 = b.y-a.y, y2 = c.y-b.y;
		double x1 = b.x-a.x, x2 = c.x-b.x;
		return x1*y2-x2*y1;
	}
	static double area(ArrayList<Point> al)
	{
		int N = al.size();
		if (N <= 2) return 0;
		double ret = 0;
		for (int i = 0; i < N; ++i)
		{
			int ii = (i+1)%N;
			ret += al.get(i).x*al.get(ii).y-al.get(ii).x*al.get(i).y;
		}
		return ret/2;			
	}
	static class PivotComp implements Comparator<Point>
	{
		Point p;
		public PivotComp(Point p) { this.p = p; }
		@Override
		public int compare(Point o1, Point o2)
		{
			double cr = cross(o1,p,o2);
			if (Math.abs(cr) < EPS) 
			{
				double d1 = Math.hypot(o1.y-p.y, o1.x-p.x);
				double d2 = Math.hypot(o2.y-p.y, o2.x-p.x);
				return d1 < d2 ? -1 : d1 > d2 ? 1 : 0;
			}
			return cr >= 0 ? 1 : -1;
		}
	}
	static boolean isLeft(Point a, Point b, Point c) { return cross(a,b,c) >= 0; }
	static ArrayList<Point> chull(ArrayList<Point> al)
	{
		ArrayList<Point> stack = new ArrayList<Point>();
		if (al.size() <= 3)
		{
			stack.addAll(al);
			return stack;
		}
		int best = 0;
		for (int i = 1; i < al.size(); ++i)
			if (al.get(i).y < al.get(best).y || Math.abs(al.get(i).y-al.get(best).y) < EPS && al.get(i).x < al.get(best).x) best = i;
		Point tmp = al.get(0); al.set(0, al.get(best)); al.set(best, tmp);
		tmp = al.remove(0);
		Collections.sort(al,new PivotComp(tmp));
		al.add(0,tmp);
		stack.add(al.get(0)); stack.add(al.get(1));
		for (int i = 2; i < al.size(); ++i)
		{
			stack.add(al.get(i));
			while (stack.size() > 2)
			{
				int S = stack.size();
				if (isLeft(stack.get(S-3),stack.get(S-2),stack.get(S-1))) break;
				else stack.remove(S-2);
			}
		}
		return stack;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		ArrayList<Point> points = new ArrayList<Point>(105);
		String line;
		int tile = 0;
		while(!(line=br.readLine().trim()).equals("0")){
			points.clear();
			int n = Integer.parseInt(line);
			for(int i=0; i<n; i++){
				line=br.readLine();
				st = new StringTokenizer(line);
				points.add(new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken())));
			}
			sb.append(String.format("Tile #%d\nWasted Space = %.2f %%\n\n", ++tile,
					100*(1 - (Math.abs(area(points))/Math.abs(area(chull(points)))))));
		}
		System.out.print(sb);
	}
}