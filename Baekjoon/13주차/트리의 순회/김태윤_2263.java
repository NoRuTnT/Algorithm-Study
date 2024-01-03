// https://loosie.tistory.com/345

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김태윤_2263 {

	static int n;
	static int[] inOrder;
	static int[] inIdx;
	static int[] postOrder;
	static int[] poIdx;
	static boolean[] check;
	
	static StringBuilder ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		
		inOrder=new int[n];
		postOrder=new int[n];
		inIdx=new int[n+1];
		poIdx=new int[n+1];
		check=new boolean[n+1];
		ans=new StringBuilder();
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			inOrder[i]=Integer.parseInt(st.nextToken());
			inIdx[inOrder[i]]=i;
		}
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			postOrder[i]=Integer.parseInt(st.nextToken());
			poIdx[postOrder[i]]=i;
		}
		
		recursion (0, n-1, 0, n-1);
		System.out.println(ans);
	}
	public static void recursion(int inStart, int inEnd, int poStart, int poEnd) {
		if(poEnd<0) return;
		int root = postOrder[poEnd];
		if(check[root]) return;
		ans.append(root).append(" ");
		check[root]=true;
		if(inStart>=inEnd) {
			return;
		}
		
		int idx=inIdx[root];
		
		recursion(inStart, idx-1, poStart, poStart+(idx-1-inStart));
		recursion(idx+1, inEnd, poEnd-1-(inEnd-idx-1), poEnd-1);
	}
}
