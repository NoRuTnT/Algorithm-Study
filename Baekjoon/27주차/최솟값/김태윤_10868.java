package Week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김태윤_10868 {
	static int[] nums;
	static int[] tree;
	static int n, m;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		StringBuilder ans=new StringBuilder();
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		nums=new int[n+1];
		tree=new int[4*n+1];
		for(int i=1; i<=n; i++){
			nums[i]=Integer.parseInt(br.readLine());
		}
		makeTree(1,n,1);
		for(int i=0; i<m; i++){
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			ans.append(searchTree(a,b,1,n,1)).append("\n");
		}
		System.out.print(ans.toString());
	}
	private static int searchTree(int left, int right, int start, int end, int node){
		if(right<start || left>end) return Integer.MAX_VALUE;
		if(left<=start && right>=end) return tree[node];
		int mid=(start+end)/2;
		return Math.min(searchTree(left, right, start, mid, node*2), searchTree(left, right, mid+1, end, node*2+1));
	}
	private static void makeTree(int start, int end, int node){
		if(start==end) {
			tree[node]=nums[start];
			return;
		}
		int mid=(start+end)/2;
		makeTree(start, mid, node*2);
		makeTree(mid+1, end, node*2+1);
		tree[node]=Math.min(tree[node*2], tree[node*2+1]);
	}
}
