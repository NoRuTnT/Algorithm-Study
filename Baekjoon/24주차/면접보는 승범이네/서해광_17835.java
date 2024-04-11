// 17835. 면접보는 승범이네
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class Main {
	static int N,M,K;
	static List<Node>[]graph;
	static int[]arr;
	static long[]ans,total;
	static class Node implements Comparable<Node>{
		int n;
		long w;
		public Node(int n,long w) {
			this.n=n;
			this.w=w;
		}
		public int compareTo(Node o) {
			if(this.w<o.w) return -1;
			else if(this.w>o.w) return 1;
			return 0;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		graph=new List[N+1];
		arr=new int[K];
		ans=new long[2];
		total=new long[N+1];
		for(int i=0;i<N+1;i++) {
			graph[i]=new ArrayList<Node>();
		}
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			long w=Long.parseLong(st.nextToken());
			graph[b].add(new Node(a, w));
		}		
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<K;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		dijkstra(arr);
		for(int i=1;i<=N;i++) {
			if(total[i]>ans[1]||(total[i]==ans[1]&&i<ans[0])) {
				ans[0]=i;
				ans[1]=total[i];
			}
		}
		System.out.println(ans[0]);
		System.out.println(ans[1]);
	}
	public static void dijkstra(int[] list) {
		PriorityQueue<Node> pq=new PriorityQueue<>();
		for(int i=0;i<N+1;i++) {
			total[i]=Long.MAX_VALUE;
		}
		for(int i=0;i<list.length;i++) {
			pq.add(new Node(list[i], 0));
			total[list[i]]=0;			
		}
		while(!pq.isEmpty()) {
			Node curr=pq.remove();
			int currN = curr.n;
			long currW = curr.w;
			if(total[currN]<currW) continue;//chk
			for(Node next : graph[currN]) {
				int nextN = next.n;
				long nextW = next.w;
				if(total[nextN]>total[currN]+nextW) {
					total[nextN]=total[currN]+nextW;
					pq.add(new Node(nextN, total[nextN]));
				}
			}
		}
	}
}
