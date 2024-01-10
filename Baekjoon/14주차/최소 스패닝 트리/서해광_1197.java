// 1197. 최소 스패닝 트리
// (크루스칼)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int V, E, A, B, C, ans;
	public static int[] p;
	static class Edge implements Comparable<Edge>{
		int st;
		int ed;
		int w;
		public Edge(int st, int ed, int w) {
			this.st = st;
			this.ed = ed;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
	public static Edge[] graph;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new Edge[E];
		// makeset
		p = new int[V];
		for(int i=0 ; i<V ; i++) {
			p[i]=i;
		}
		for(int i=0 ; i<E ; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			graph[i] = new Edge(A-1, B-1, C);
		}
		Arrays.sort(graph);
		int idx = 1;
		int cnt = 1;
		ans = graph[0].w;
		unionset(graph[0].st, graph[0].ed);
		while(cnt!=V-1) {
			Edge e = graph[idx++];
			if(findset(e.st)!=findset(e.ed)) {
				unionset(findset(e.st), findset(e.ed)); // 노드끼리 이어주고
				ans += e.w; // 그 간선의 가중치를 더해주고
				cnt++; // 카운트 올려준다.
			}
		}
		System.out.println(ans);
	} // main
	public static int findset(int x) {
		if(x!=p[x]) p[x] = findset(p[x]);
		return p[x];
	}
	public static void unionset(int x, int y) {
		int px = findset(x);
		int py = findset(y);
		if(px<py) p[y] = px;
		else p[x] = py;
	}
}
