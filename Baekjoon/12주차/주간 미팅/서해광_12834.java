// 12843. 주간 미팅
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node>{
		int pos;
		int len;
		public Node(int pos, int len) {
			this.pos = pos;
			this.len = len;
		}
		@Override
		public int compareTo(Node o) {
			return this.len-o.len;
		}
	}
	public static int N, V, E, A, B;
	public static long ans;
	public static int[] H;
	public static int[] minimum;
	public static int[][] graph;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new int[V+1][V+1];
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		H = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1 ; i<=N ; i++) {
			H[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0 ; i<E ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[b][a] = graph[a][b] = w;
		}
		dij(new Node(A, 0));
		for(int i=1 ; i<=N ; i++) {
			if(minimum[H[i]]==Integer.MAX_VALUE) ans--;
			else ans += minimum[H[i]];
		}
		dij(new Node(B, 0));
		for(int i=1 ; i<=N ; i++) {
			if(minimum[H[i]]==Integer.MAX_VALUE) ans--;
			else ans += minimum[H[i]];
		}
		System.out.println(ans);
	}
	public static void dij(Node node) {
		minimum = new int[V+1];
		for(int i=1 ; i<=V ; i++) {
			minimum[i] = Integer.MAX_VALUE;
		}
		minimum[node.pos] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(node);
		while(!pq.isEmpty()) {
			Node n = pq.remove();
			if(minimum[n.pos]<n.len) continue;
			for(int i=1 ; i<=V ; i++) {
				if(graph[n.pos][i]==0 || minimum[i]<=n.len+graph[n.pos][i]) continue;
				minimum[i]=n.len+graph[n.pos][i];
				pq.add(new Node(i, minimum[i]));
			}
		}
	}
}
