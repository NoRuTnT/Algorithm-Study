// 4485. 녹색 옷 입은 애가 젤다지?

package 준백;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
	public static int N;
	public static int[][] arr;
	public static final int INF = Integer.MAX_VALUE;
	public static int[] dr = {-1, 0, 1, 0}; // 12시부터 시계방향
	public static int[] dc = {0, 1, 0, -1};
	static class Node implements Comparable<Node>{
		int r;
		int c;
		int cost;
		public Node(int r, int c, int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testCase = 1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N==0) break;
			arr = new int[N][N];
			for(int i=0 ; i<N ; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0 ; j<N ; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 입력
			bw.write("Problem "+ (testCase++) +": "+dij()+"\n");
		}
		bw.flush();
		bw.close();
	} // main
	public static int dij(){
		int[][] length = new int[N][N];
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<N ; j++) {
				length[i][j] = INF;
			}
		}
		length[0][0] = arr[0][0];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0, length[0][0]));
		while(!pq.isEmpty()) {
			Node n = pq.remove();
			int c = n.cost;
			if(length[n.r][n.c] < c) continue;
			for(int d=0 ; d<4 ; d++) {
				int nr = n.r + dr[d];
				int nc = n.c + dc[d];
				if(nr<0 || nr>=N || nc<0 || nc>=N ) // 범위 밖을 벗어남
					continue;
				int c2 = length[n.r][n.c] + arr[nr][nc];
				if(c2 < length[nr][nc]) {
					length[nr][nc] = c2;
					pq.add(new Node(nr, nc, c2));
				}
//				length[nr][nc] = Math.min(length[nr][nc], length[n.r][n.c]+arr[n.r][n.c]);
			}
		}
		return length[N-1][N-1]; // 출발 위치 값을 추가
	}
}
