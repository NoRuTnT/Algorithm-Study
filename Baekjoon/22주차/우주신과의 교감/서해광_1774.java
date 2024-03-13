// 1774. 우주신과의 교감
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[]p;
	static class Edge implements Comparable<Edge>{
		int S;
		int E;
		double W;
		public Edge(int S,int E,double W) {
			this.S=S;
			this.E=E;
			this.W=W;
		}
		public int compareTo(Edge o) {
			return Double.compare(this.W, o.W);
		}
		@Override
		public String toString() {
			return "Edge [S=" + S + ", E=" + E + ", W=" + W + "]";
		}
	}
	static class Node{
		int idx;
		double x;
		double y;
		public Node(int idx,double x,double y) {
			this.idx=idx;
			this.x=x;
			this.y=y;
		}
	}
	static List<Edge>graph;
	static double ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		graph=new ArrayList<Edge>();
		p=new int[N+1];
		Node[] nodes=new Node[N+1];
		for(int i=1;i<=N;i++) {
			st=new StringTokenizer(br.readLine());
			double A=Double.parseDouble(st.nextToken());
			double B=Double.parseDouble(st.nextToken());
			nodes[i]=new Node(i, A, B);
//			pair[i][0]=A;
//			pair[i][1]=B;
		}
		
		for(int i=1;i<=N;i++) {
			p[i]=i; // makeSet
		}
		
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int A=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());
			unionSet(A, B);
		}
		
		for(int i=1;i<=N-1;i++) {
			for(int j=i+1;j<=N;j++) {
				double W=getLength(nodes[i].x, nodes[i].y, nodes[j].x, nodes[j].y);
				graph.add(new Edge(i,j,W));
			}
		}
		Collections.sort(graph);
		for(int i=0;i<graph.size();i++) {
			int s=graph.get(i).S;
			int e=graph.get(i).E;
			if(findSet(s)!=findSet(e)) {
				unionSet(s, e);
				ans+=graph.get(i).W;
			}
		}
		System.out.printf("%.2f",ans);
	}
	
	public static double getLength(double x1,double y1,double x2,double y2) {
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}
	public static int findSet(int x) {
        if(x==p[x])
            return x;
        return p[x]=findSet(p[x]);
//		if(p[x]!=x) p[x]=findSet(p[x]);
//		return p[x];
	}
	public static void unionSet(int x, int y) {
		x=findSet(x);
		y=findSet(y);
		if(x!=y)
            p[y]=x;
	}
}
