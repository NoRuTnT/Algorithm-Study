import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Edge{
		int v1;
		int v2;
		int val;
		Edge(){}
		Edge(int v1, int v2, int val){
			this.v1=v1;
			this.v2=v2;
			this.val=val;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int v=Integer.parseInt(st.nextToken());
		int e=Integer.parseInt(st.nextToken());
		PriorityQueue<Edge> pq=new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				if(o1.val<o2.val) return -1;
				else if(o1.val>o2.val) return 1; 
				return 0;
			}	
		});
		for(int i=0;i<e;i++) {
			st=new StringTokenizer(br.readLine());
			int v1=Integer.parseInt(st.nextToken());
			int v2=Integer.parseInt(st.nextToken());
			int val=Integer.parseInt(st.nextToken());
			Edge edge=new Edge(v1, v2, val);
			pq.offer(edge);
		}
		int[] tree=new int[v+1];
		int[] rank=new int[v+1];
		for(int i=1;i<=v;i++) {
			tree[i]=i; // makeTree
			rank[i]=1;
		}
		int cnt=0;
		int ans=0;
		while(cnt<v-1) {
			Edge edge=pq.poll();
			int p1=find(edge.v1, tree);
			int p2=find(edge.v2, tree);
			if(p1==p2) continue; // 조상 같은 경우 : 얘네끼리 이으면 cycle 생긴다 
			union(p1, p2, tree, rank);
			ans+=edge.val;
			cnt++;
		}
		System.out.println(ans);
	}
	public static int find(int node, int[] tree) {
		if(tree[node]!=node) {
			tree[node]=find(tree[node], tree); // path compression
		}
		return tree[node];
	}
	public static void union(int node1, int node2, int[] tree, int[] rank) {
		if(rank[node1]>rank[node2]) {
			tree[node1]=node2;
		}
		else{
			tree[node2]=node1;
			if(rank[node1]==rank[node2]) rank[node1]++;
		}
	}
}
