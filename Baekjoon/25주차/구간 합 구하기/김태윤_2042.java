package Week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김태윤_2042 {
	static int n, m, k;
	static long[] nums;
	static long[] tree;
	public static void main(String[] args) throws IOException{
		StringBuilder ans=new StringBuilder();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		nums=new long[n+1];
		tree=new long[n*4+4];
		for(int i=1; i<=n; i++){
			nums[i]=Long.parseLong(br.readLine());
		}
		initTree(1, n, 1);
		for(int i=0; i<m+k; i++){
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			long c=Long.parseLong(st.nextToken());
			if(a==1) changeTree(b,c,1,n,1);
			else ans.append(printValue(b,(int)c,1,n,1)).append("\n");
		}
		br.close();
		System.out.print(ans.toString());
	}
	private static long printValue(int b, int c, int left, int right, int node){
		//아예 범위 밖인 경우 더하지 않는다
		if(c<left || b>right) return 0;
		//left, right가 주어진 b c 안에 있으면 더해준다
		if(b<=left && c>=right) return tree[node];
		//걸쳐져 있는 경우 쪼개져야 하니까 반반 나눠서 더 탐색
		int mid=(left+right)/2;
		return printValue(b, c, left, mid, node*2)+printValue(b, c, mid+1, right, node*2+1);
	}
	private static void changeTree(int target, long value, int left, int right, int node){
		if(left==right){
			tree[node]=value;
			return;
		}
		int mid=(left+right)/2;
		if(target<=mid) changeTree(target, value, left, mid, node*2);
		else changeTree(target, value, mid+1, right, node*2+1);
		tree[node]=tree[node*2]+tree[node*2+1];
	}
	private static void initTree(int left, int right, int node){
		if(left==right){
			tree[node]=nums[left];
			return;
		}
		int mid=(left+right)/2;
		initTree(left, mid, node*2);
		initTree(mid+1, right, node*2+1);
		tree[node]=tree[node*2]+tree[node*2+1];
	}
}
