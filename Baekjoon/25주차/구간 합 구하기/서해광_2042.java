// 2042. 구간 합 구하기
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	static int N,M,K;
	static long[]arr,segTree;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		arr=new long[N+1];
		segTree=new long[4*N];
		for(int i=1;i<=N;i++) {
			arr[i]=Long.parseLong(br.readLine());
		}
		initSegTree(1, N, 1);
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<M+K;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			if(a==1) {
				int b=Integer.parseInt(st.nextToken());
				long c=Long.parseLong(st.nextToken());
				modify(1, N, 1, b, c);
			}else {
				int b=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				sb.append(print(1, N, b, c, 1)).append("\n");
			}
		}
		System.out.println(sb);
	}
	public static void initSegTree(int s,int e,int node) {
		if(s==e) {
			segTree[node]=arr[s];
			return;
		}
		int mid=(s+e)/2;
		initSegTree(s, mid, node*2);
		initSegTree(mid+1, e, node*2+1);
		segTree[node]=segTree[node*2]+segTree[node*2+1];
	}
	public static void modify(int s,int e,int node,int b,long c) {
		if(b<s||e<b) return;
		if(s==e) {
			segTree[node]=c;
			arr[b]=c;
			return;
		}
		int mid=(s+e)/2;
		modify(s, mid, node*2, b, c);
		modify(mid+1, e, node*2+1, b, c);
		segTree[node]=segTree[node*2]+segTree[node*2+1];
	}
	public static long print(int s,int e,int b,int c,int idx) {
		if(e<b||c<s) return 0;
		if(b<=s&&e<=c) return segTree[idx];
		int mid=(s+e)/2;
		return print(s, mid, b, c, idx*2)+print(mid+1, e, b, c, idx*2+1);
	}
}
