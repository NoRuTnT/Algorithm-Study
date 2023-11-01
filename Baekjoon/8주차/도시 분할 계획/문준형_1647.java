import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class b1647 {
	
	static class Node implements Comparable<Node>{
		int start, end, dist;
		
		public Node(int start, int end, int dist) {
			this.start = start;
			this.end= end;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist); //정렬기준변경
		}
	}
	static int n,m;
	static Node[] list;
	static int[] parent;
	public static boolean union(int x,int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) {
			return false;
		}
		if(x<y) {
			parent[y] =x;
		}else {
			parent[x] =y;
		}
		return true;
	}
	
	public static int find(int x) {
		if(parent[x]==x) {
			return x;
		}else {
			return find(parent[x]);
		}
	}
	//크루스칼사용위한 유니온파인드
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		list = new Node[m]; //노드저장리스트
		parent = new int[n+1]; //부모저장리스트
		for(int i=1;i<=n;i++) {
			parent[i]=i; //부모초기화
		}
		
		for( int i=0;i<m;i++) {
			st= new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			list[i] = new Node(a,b,c); //노드에 정보입력
		}
		
		
		System.out.println(kruskal());
		
	}
	private static int kruskal() {
		int res = 0;
		int cnt=0;
		Arrays.sort(list);	// 변경시킨 가중치기준으로정렬
		for(Node e:list) { //가중치작은거부터 뽑아서
			if(union(e.start,e.end)) { //부모가다르면
				res+=e.dist; //결과에 가중치더하고
				if(++cnt == n-2 || n==2) { //카운트1추가 카운트가 n-2개가 되거나 (n-2:2덩어리일때 n-1:하나로이어졌을때) 전체노드가 2개일때 결과출력
					return res;
				}
				
			}
		}
		return res;
	}
	
	



}
