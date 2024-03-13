package Week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 김태윤_1774 {
	static class Edge implements Comparable<Edge>{
		double distance;
		int end1;
		int end2;
		Edge(double distance, int end1, int end2){
			this.distance=distance;
			this.end1=end1;
			this.end2=end2;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.distance-o.distance<0) return -1;
			else if(this.distance-o.distance>0) return 1;
			return 0;
		}
	}
	static int n, m;
	static long[][] gods; // (x,y)
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static int[] parent;
	static double ans = 0D;
	public static void main(String[] args) throws IOException {
		input();
		makeEdge();
		mst();
		System.out.printf("%.2f", ans);
	}

	private static void input() throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		gods=new long[n+1][2];
		parent = new int[n+1];
		for(int i=1; i<=n; i++){
			parent[i]=i; // 초기화
			st=new StringTokenizer(br.readLine());
			gods[i][0]=Long.parseLong(st.nextToken()); //x
			gods[i][1]=Long.parseLong(st.nextToken()); //y
		}
		for(int i=0; i<m; i++){
			st=new StringTokenizer(br.readLine());
			int end1=Integer.parseInt(st.nextToken());
			int end2=Integer.parseInt(st.nextToken());
			if(findParent(end1)!=findParent(end2)){
				union(parent[end1], parent[end2]);
			}
		}
		br.close();
	}

	private static int findParent(int idx) {
		if(parent[idx]==idx) return  idx;
		else return parent[idx]=findParent(parent[idx]); //path-compression
	}
	private static void union(int parent1, int parent2){
		if(parent1<parent2) parent[parent2]=parent1;
		else parent[parent1]=parent2; // 그냥 index 작은게 조상으로 가도록
	}

	private static void makeEdge() {
		for(int i=1; i<=n; i++){
			for(int j=1; j<=n; j++){
				double distance = calcDistance(i, j);
				pq.offer(new Edge(distance, i, j));
			}
		}
	}

	private static double calcDistance(int i, int j){
		long xx = (gods[i][0]-gods[j][0])*(gods[i][0]-gods[j][0]);
		long yy = (gods[i][1]-gods[j][1])*(gods[i][1]-gods[j][1]);
		return Math.sqrt((double)xx+(double)yy);
	}

	private static void mst() {
		while(!pq.isEmpty()){
			Edge edge = pq.poll();
			if(findParent(edge.end1)==findParent(edge.end2)) continue; // 조상이 같은 경우 패스
			union(parent[edge.end1], parent[edge.end2]); // 길 연결하기
			ans+=edge.distance;
		}
	}
}
