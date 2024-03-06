// 6087. 레이저 통신
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int W,H;
	static final int INF=987654321;
	static char[][]arr;
	static int[]dr={-1,0,1,0},dc= {0,1,0,-1};
	static class Node implements Comparable<Node>{
		int r;
		int c;
		int weight;
		int dir;
		public Node(int r, int c, int weight, int dir) {
			this.r = r;
			this.c = c;
			this.weight = weight;
			this.dir = dir;
		}
		public int compareTo(Node o) {
			return this.weight-o.weight;
		}
	}
	static List<Node>ep;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		W=Integer.parseInt(st.nextToken());
		H=Integer.parseInt(st.nextToken());
		ep=new ArrayList<>();
		arr=new char[H][W];
		for(int i=0;i<H;i++) {
			String str=br.readLine();
			for(int j=0;j<W;j++) {
				arr[i][j]=str.charAt(j);
				if(arr[i][j]=='C') {
					ep.add(new Node(i, j, 0, 0));
				}
			}
		}
		dijkstra();
	}
	public static void dijkstra() {
		int R1=ep.get(0).r;
		int C1=ep.get(0).c;
		int R2=ep.get(1).r;
		int C2=ep.get(1).c;
		int[][]cost=new int[H][W];
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				cost[i][j]=INF;
			}
		}
		PriorityQueue<Node>pq=new PriorityQueue<>();
		pq.add(new Node(R1, C1, 0, -1));
		cost[R1][C1]=0;
		while(!pq.isEmpty()) {
			Node node=pq.remove();
			int currR=node.r;
			int currC=node.c;
			if(currR==R2&&currC==C2)break;
			for(int d=0;d<4;d++) {
				int nr=currR+dr[d];
				int nc=currC+dc[d];
				if(nr<0||nr>=H||nc<0||nc>=W||arr[nr][nc]=='*')continue;
				int currWeight=node.weight;
				if(-1!=node.dir&&node.dir!=d)currWeight++;
				if(currWeight<=cost[nr][nc]) {
					cost[nr][nc]=currWeight;
					pq.add(new Node(nr, nc, cost[nr][nc], d));
				}
			}
		}
//		for(int i=0;i<H;i++) {
//			for(int j=0;j<W;j++) {
//				System.out.print(cost[i][j]==INF?"x":cost[i][j]);				
//				System.out.print(" ");				
//			}
//			System.out.println();
//		}
		System.out.println(cost[R2][C2]);
	}
}
