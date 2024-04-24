// 11505. 구간 곱 구하기
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M,K;
	static final long MOD=1000000007;
	static long[]arr,seg;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		arr=new long[N+1];
		seg=new long[4*N];
		for(int i=1;i<=N;i++) {
			arr[i]=Long.parseLong(br.readLine());
		}
		initSegTree(1, 1, N);
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<M+K;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			if(a==1) {
				int b=Integer.parseInt(st.nextToken());
				long c=Long.parseLong(st.nextToken());
				modify(1, 1, N, b, c);
			}else {
				int b=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				sb.append(print(1, 1, N, b, c)).append("\n");
			}
		}
		System.out.println(sb);
	}
	public static void initSegTree(int node,int s,int e) {
		if(s==e) {
			seg[node]=arr[s];
			return;
		}
		int mid=(s+e)/2;
		initSegTree(node*2, s, mid);
		initSegTree(node*2+1, mid+1, e);
		seg[node]=seg[node*2]*seg[node*2+1]%MOD;
	}
	public static void modify(int node,int s,int e,int b,long c) {
		if(e<b||b<s) return;
		if(s==e) {
			seg[node]=c;
			return;
		}
		int mid=(s+e)/2;
		modify(node*2, s, mid, b, c);
		modify(node*2+1, mid+1, e, b, c);
		seg[node]=seg[node*2]*seg[node*2+1]%MOD;
	}
	public static long print(int node,int s,int e,int b,int c) {
		if(e<b||c<s) return 1;
		if(b<=s&&e<=c) {
			return seg[node];
		}
		int mid=(s+e)/2;
		return print(node*2, s, mid, b, c)*print(node*2+1, mid+1, e, b, c)%MOD;
	}
}
