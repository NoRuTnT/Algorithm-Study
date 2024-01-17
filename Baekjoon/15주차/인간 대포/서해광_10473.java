// 10473. 인간 대포
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node>{
		int node;
		double len;
		public Node(int node, double len) {
			super();
			this.node = node;
			this.len = len;
		}
		@Override
		public int compareTo(Node o) {
			if(this.len<o.len) return -1;
			else if(this.len>o.len) return 1;
			return 0;
		}
	}
	public static int n;
	public static double[][] p, pos, graph;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		p = new double[2][2];
		StringTokenizer st = new StringTokenizer(br.readLine());
		p[0][0] = Double.parseDouble(st.nextToken());
		p[0][1] = Double.parseDouble(st.nextToken());
		st = new StringTokenizer(br.readLine());
		p[1][0] = Double.parseDouble(st.nextToken());
		p[1][1] = Double.parseDouble(st.nextToken());
		n = Integer.parseInt(br.readLine());
		pos = new double[n+2][2];
		graph = new double[n+2][n+2];
		pos[0] = p[0]; // 왜 안되겠어? 내가 미쳤지.
		pos[n+1] = p[1];
		pos[0][0] = p[0][0];
		pos[0][1] = p[0][1];
		pos[n+1][0] = p[1][0];
		pos[n+1][1] = p[1][1];
		for(int i=0 ; i<n ; i++) {
			st = new StringTokenizer(br.readLine());
			pos[i+1][0] = Double.parseDouble(st.nextToken());
			pos[i+1][1] = Double.parseDouble(st.nextToken());
		}
		for(int i=1 ; i<n+2 ; i++) {
			graph[0][i] = getLength(pos[0], pos[i])/5;
		}
		for(int i=1 ; i<n+1 ; i++) {
			for(int j=0 ; j<n+2 ; j++) {
				if(i!=j) {
					graph[i][j]=getTime(pos[i], pos[j]);
				}
			}
		}
		for(int i=0 ; i<n+1 ; i++) {
			graph[n+1][i] = getLength(pos[n+1], pos[i])/5;
		}
		System.out.println(dijkstra());
		for(int i=0 ; i<n+2 ; i++) {
			for(int j=0 ; j<n+2 ; j++) {
				System.out.print(graph[i][j]+" ");
			}
			System.out.println();
		}
	} // main
	public static double dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[n+2];
		double[] length = new double[n+2];
		for(int i=1 ; i<n+2 ; i++) {
			length[i] = Double.MAX_VALUE;
			
		}
		pq.add(new Node(0, 0));
		while(!pq.isEmpty()) {
			Node nn = pq.remove();
			if(visited[nn.node]) continue;
			visited[nn.node] = true;
			for(int i=0 ; i<n+2 ; i++) {
				if(graph[nn.node][i]>0 && !visited[i]) {
					length[i] = Math.min(length[i], length[nn.node]+graph[nn.node][i]);
					pq.add(new Node(i, length[i]));
				}
			}
		}
		return length[n+1];
	}
	
	public static double getTime(double[] p1, double[] p2) {
		// 직접 걸어가거나 타고 가거나 2가지를 비교해야 됨.
		return Math.min(2+Math.abs(getLength(p1, p2)-50)/5, getLength(p1, p2)/5);
	}
	public static double getLength(double[] p1, double[] p2) {
		return Math.sqrt((p1[0]-p2[0])*(p1[0]-p2[0])+(p1[1]-p2[1])*(p1[1]-p2[1]));
	}
}
