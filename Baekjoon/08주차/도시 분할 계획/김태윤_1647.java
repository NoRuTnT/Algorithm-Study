import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 김태윤_1647 {
	
	static class Edge{
		int v1;
		int v2;
		int value;
		
		Edge(){}
		Edge(int v1, int v2, int value){
			this.v1=v1;
			this.v2=v2;
			this.value=value;
		}
	}
	
	static int n;
	static int m;
	static int[] set;
	static int[] rank;
	static int town;
	static int ans=0;
	
	static PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {

		@Override
		public int compare(Edge e1, Edge e2) {
			if(e1.value<e2.value) return -1;
			else if(e1.value>e2.value) return 1; 
			else return 0;
		}
		
	});
	
	public static void main(String[] args) throws IOException{
		//union + MST 문제
		//MST 고르는걸 쭉 하면서, 길을 남기기로 정하면 union을 시켜준다
		//union을 하면서 집합이 2개만 남으면 완료! mst 계산한 값을 출력하자
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		set=new int[n+1]; // 초기 집합이 n개
		rank=new int[n+1]; // union의 rank
		
		for(int i=1;i<=n;i++) {
			set[i]=i; // makeSet
			rank[i]=1;
		}
		
		town=n; // 마을의 개수 -> union 과정에서 1씩 감소한다.
		
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int v1=Integer.parseInt(st.nextToken());
			int v2=Integer.parseInt(st.nextToken());
			int value=Integer.parseInt(st.nextToken());
			Edge edge=new Edge(v1,v2,value);
			pq.add(edge);
		}
		
		mst(); // 최소 스패닝 트리를 만들어서 길의 유지비를 구하자
		
		System.out.println(ans);
		
	}
	public static void mst() {
		while(town>2) {
			Edge edge=pq.poll(); // 비용이 가장 적은 간선을 뽑아낸다
			if(find(edge.v1)!=find(edge.v2)) { // 둘이 연결 안 된 경우 연결	
				ans+=edge.value;
				town--; // 둘이 연결되면 마을개수가 하나 줄어든다
				union(set[edge.v1], set[edge.v2]); // 조상을 합쳐준다
			}
		}
	}
	public static int find(int idx) {
		if(set[idx]!=idx) {
			set[idx]=find(set[idx]); //path compression
		}
		return set[idx];
	}
	public static void union(int idx1, int idx2) {
		if(rank[idx1]>rank[idx2]) {
			set[idx2]=idx1; // rank 낮은게 rank 큰 거 하위 트리가 된다
		}
		else if(rank[idx1]<rank[idx2]) {
			set[idx1]=idx2;
		}
		else {
			set[idx2]=idx1;
			rank[idx1]++; // 임의로 부모를 정하고, tree rank가 늘어났으니 +1 해준다
		}
	}
}
