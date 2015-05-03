import java.io.*;
import java.util.*;

public class Main
{
	private static final int INF = Integer.MAX_VALUE-1;
	private int last;
	private String str;
	private Node root, sentinel;

	/**
	 * Edge of the SuffixTree.  
	 */
	public class Edge
	{
		/** An inclusive range [a,b] of indices into str representing the edge label. */
		public int a, b;
		/** Node where the edge points to */
		public Node end;
		public Edge(int a, int b, Node end) { this.a = a; this.b = b; this.end = end; }
		/** Returns the first character on the edge. */
		public char getFirst() { return str.charAt(a); }
		/** Length of the string on the edge label. */
		public int length() { return Math.min(last,b) - a + 1; } 
		@Override
		public String toString() { return str.substring(a, Math.min(b,last)+1); }
	}
	/**
	 * Node of the SuffixTree.
	 */
	public class Node implements Iterable<Edge>
	{
		/** Maps first letters to edges leading to children */
		public TreeMap<Character,Edge> edges = new TreeMap<Character,Edge>();
		/** Suffix link used in linear tree building algorithm */
		private Node suffix;
		/** An inclusive range [a,b] of indices into str representing a prefix leading to this node */
		public int a, b, path_label_length;
		public boolean discovered = false;
		public void set(){this.discovered = true;}
		public void set_path_label_length(int n){this.path_label_length = n;}
		public int get_path_label_length(){return this.path_label_length;}

		/** Adds an edge to the map */
		public void add(Edge e) { edges.put(e.getFirst(),e); }
		/** Gets the edge using the given first character */
		public Edge get(char c) { return edges.get(c); }
		/** Returns the number of children */
		public int numChildren() { return edges.size(); }
		@Override
		public Iterator<Edge> iterator() { return edges.values().iterator(); }
		public String toString() { return str.substring(a,Math.min(b,last)+1); }
	}
	public  Main(String str)
	{
		this.str = str;
		buildTree();
		setPrefix(root,null,0);
	}
	@Override
	/** Pretty prints the tree.  For each node it prints the prefix leading to that node in quotes.
	 * Then it prints each edge leaving the node indexed by first letter, the edge label, the inclusive range of indices,
	 * and the length of the label.
	 */
	public String toString() { return prettyFormat(new StringBuilder(), root, new StringBuilder()).toString(); }
	private int fix(int x) { return x == INF ? last : x; }
	private StringBuilder prettyFormat(StringBuilder sb, Node n, StringBuilder tab)
	{
		sb.append(tab).append('"').append(n).append('"').append('\n');
		for (Edge e : n)
		{
			char c = e.getFirst();
			sb.append(tab).append(c).append(" : ").append(e).append(" = ").append(e.a).append(',').append(fix(e.b)).append(',').append(e.length()).append('\n');
			tab.append("  ");
			prettyFormat(sb,e.end,tab);
			tab.delete(tab.length()-2, tab.length());
		}
		return sb;
	}
	/** Returns the string used to make the suffix tree */
	public String getStr() { return str; }
	/** Returns the root node */
	public Node getRoot() { return root; }
	private void setPrefix(Node n, Edge e, int len)
	{
		for (Edge edge : n) setPrefix(edge.end,edge,len+Math.min(edge.b,last)-edge.a+1);
		if (e == null) { n.a = 0; n.b = -1; }
		else { n.b = Math.min(e.b,last); n.a = n.b - len+1; }
	}
	private void buildTree()
	{
		root = new Node();
		sentinel = new Node();
		root.suffix = sentinel;
		Node s = root;
		int[] k = {0};
		last = -1;
		for (int i = 0; i < str.length(); ++i)
		{
			last++;
			s = update(s,k,i);
			s = canonize(s,k,i);
		}
	}
	private Node update(Node s, int[] k, int i)
	{
		Node oldr = root, r = testAndSplit(s,k[0],i-1,str.charAt(i));
		while (r != null)
		{
			Node rp = new Node();
			Edge e = new Edge(i,INF,rp);
			r.add(e);
			if (oldr != root) oldr.suffix = r;
			oldr = r;
			s = canonize(s.suffix,k,i-1);
			r = testAndSplit(s,k[0],i-1,str.charAt(i));
		}
		if (oldr != root) oldr.suffix = s;
		return s;
	}
	private Node testAndSplit(Node s, int k, int p, char c)
	{
		if (k > p) return s == sentinel ? null : s.get(c) == null ? s : null;
		Edge e = s.get(str.charAt(k));
		if (c == str.charAt(e.a+p-k+1)) return null; //check if char after implicit node is c
		Node r = new Node();
		Edge re = new Edge(e.a+p-k+1,e.b,e.end);
		r.add(re);
		Edge se = new Edge(e.a,e.a+p-k,r);
		s.add(se);
		return r;
	}
	private Node canonize(Node s, int[] k, int p)
	{
		if (p < k[0]) return s;
		if (s == sentinel) { s = root; k[0]++; if (p < k[0]) return s; }
		Edge e = s.get(str.charAt(k[0]));
		while (e.b - e.a <= p - k[0])
		{
			k[0] = k[0] + e.b - e.a + 1;
			s = e.end;
			if (k[0] <= p) e = s.get(str.charAt(k[0]));
		}
		return s;
	}


	public static int DFS(Node tree){
		Stack<Node> stack = new Stack<Node>();
		String resultString = "";
		int result = 0;
		tree.set_path_label_length(0);
		stack.push(tree);

		while(!stack.empty()){
			Node v = stack.pop();
			if(v.discovered == false){
				v.set();

			for(Edge e: v.edges.values()){
				Node toPush = e.end;
				int previous = v.get_path_label_length();
				toPush.set_path_label_length(previous + e.length());
				int path_length = toPush.get_path_label_length();

				if(toPush.numChildren() > 0){
					stack.push(toPush);
					if( (path_length >= result) ){
						result = path_length;
					}
				}
			}
			
			}
		}

		return result;
	}

	/** Can be used to test the suffix tree on a given string given as a cmdline argument. */
	public static void main(String[] args) throws Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(in.readLine());
		for(int i=0; i < cases; i++){
			Main tree = new Main(in.readLine() + '$');
			int answer = DFS(tree.getRoot());
			System.out.println(answer);
		}

	}
}




