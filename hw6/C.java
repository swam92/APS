import java.io.*;
import java.util.*;

class C{

	public static class Node implements Comparable<Node>{
		int value;
		Node left;
		Node right;

		public Node(int value){
			this.value=value;
			this.left = left;
			this.right =right;
		}

		public int getValue(){return value;}
		
		public int compareTo(Node o){
		 	return Integer.compare(this.value,o.getValue());
		}

	}

	static int bestSoFar = Integer.MAX_VALUE;
	static int bestNodeValue = 0;

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = null;

		while((line=in.readLine()) != null){
			StringTokenizer st = new StringTokenizer(line);
			int arrayLength = st.countTokens();
			int[] inorder = new int[arrayLength];
			int[] postorder = new int[arrayLength];
			
			/*FILL OUT INORDER AND POSTORDER ARRAYS*/
			for(int i=0;i<arrayLength;i++){
				inorder[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(in.readLine());
			for(int i=0;i<arrayLength;i++){
				postorder[i] = Integer.parseInt(st.nextToken());
			}
			/***************************************/

			Node tree = buildTree(inorder,0,arrayLength-1,postorder,0,arrayLength-1);

			shortestPath(tree, 0);
			System.out.println(bestNodeValue);
			bestSoFar = Integer.MAX_VALUE;
			bestNodeValue = 0;

		}//end while
	}//end main

	public static Node buildTree(int[] inorder, int iStart, int iEnd, int[] postorder, int pStart, int pEnd){
         
         if(iStart > iEnd){
             return null;
         }
         int rootVal = postorder[pEnd];
         int rootIndex = 0;
        
         for(int i = iStart; i <= iEnd; i++){
             if(inorder[i] == rootVal){
                 rootIndex = i;
                 break;
             }
         }
        
         int len = rootIndex - iStart;
         Node root = new Node(rootVal);
         root.left = buildTree(inorder, iStart, rootIndex-1, postorder, pStart, pStart+len-1);
         root.right = buildTree(inorder, rootIndex+1, iEnd, postorder, pStart+len, pEnd-1);
        
         return root;
	}

	public static void shortestPath(Node root, int cost){

		if(root==null){
			return;
		}

		int pathVal = cost + root.value;

		if(root.left==null && root.right==null){
			if(pathVal < bestSoFar){
				bestSoFar=pathVal;
				bestNodeValue = root.value;
			}
			/*if(pathVal == bestSoFar){
				if(bestNodeValue > root.value){
					bestNodeValue = root.value;
				}
			}*/
		}

		if(root.left != null){
			shortestPath(root.left,pathVal);
		}
		if(root.right != null){
			shortestPath(root.right, pathVal);
		}
	}
}