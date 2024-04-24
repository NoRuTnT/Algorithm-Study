import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long MOD = 1000000007;
	static int n, m, k;
	static long[] nums;
	static long[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		StringBuilder sb=new StringBuilder();
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		nums=new long[n+1];
		tree=new long[4*n+4];
		for(int i=1; i<=n; i++){
			nums[i]=Long.parseLong(br.readLine());
		}
		init(1, n, 1);
		for(int i=0; i<m+k; i++){
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			long c=Long.parseLong(st.nextToken());
			if(a==1) {
				nums[b]=c;
				changeNum(b, c, 1, n, 1);
			}
			else
				sb.append(printNum(b, (int)c, 1, n, 1)).append("\n");
		}
		System.out.print(sb.toString());
	}
	private static void changeNum(int b, long c, int left, int right, int node){
		if(left==right){
			tree[node]=c;
			return;
		}
		int mid=(left+right)/2;
		if(b<=mid) changeNum(b, c, left, mid, node*2);
		else changeNum(b, c, mid+1, right, node*2+1);
		tree[node]=(tree[node*2]*tree[node*2+1])%MOD;
	}
	private static long printNum(int b, int c, int left, int right, int node){
		if(c<left || b>right) return 1;
		if(b<=left && c>=right) return tree[node];
		int mid=(left+right)/2;
		return (printNum(b,c,left,mid,node*2)*printNum(b,c,mid+1,right,node*2+1))%MOD;
	}
	private static void init(int left, int right, int node){
		if(left==right){
			tree[node]=nums[left];
			return;
		}
		int mid=(left+right)/2;
		init(left, mid, node*2);
		init(mid+1, right, node*2+1);
		tree[node]=(tree[node*2]*tree[node*2+1])%MOD;
	}
}
