// 2206. 벽 부수고 이동하기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, ans;
	public static int[][] arr;
	public static boolean[][][] visited;
	public static int[] dr = {-1, 1, 0, 0};
	public static int[] dc = {0, 0, -1, 1};
	static class Node{
		int r;
		int c;
		int chk;
		int len;
		public Node(int r, int c, int chk, int len) {
			this.r = r;
			this.c = c;
			this.chk = chk;
			this.len = len;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;
		arr = new int[N][M];
		visited = new boolean[2][N][M];
		for(int i=0 ; i<N ; i++) {
			String str = br.readLine();
			for(int j=0 ; j<M ; j++) {
				arr[i][j] = str.charAt(j)-'0';
			}
		}
		bfs();
		System.out.println((ans==Integer.MAX_VALUE)?-1:ans);
		
	}
	public static void bfs() {
		visited[0][0][0] = visited[1][0][0] = true;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 0, 1)); // chk가 1이면 벽을 뚫은 상태
		while(!q.isEmpty()) {
			Node n = q.remove();
			if(n.len>=ans) continue; // 백트래킹
			if(n.r==N-1 && n.c==M-1) {
				ans = Math.min(ans, n.len);
				continue;
			}
			for(int d=0 ; d<4 ; d++) {
				int nr = n.r + dr[d];
				int nc = n.c + dc[d];
				// 맵을 벗어났을 때 + 이미 방문한 곳일 때
				if(nr<0 || nr>=N || nc<0 || nc>=M || visited[n.chk][nr][nc])
					continue;
				if(arr[nr][nc]==1) {
					if(n.chk==0) {
						q.add(new Node(nr, nc, 1, n.len+1));
						visited[1][nr][nc] = true; // 벽을 뚫은 경우를 담은 배열에 방문체크
					}else {
						continue;
					}
				}else { // 벽을 뚫지 않고 0인 곳으로 그냥 넘어가는 경우
					q.add(new Node(nr, nc, n.chk, n.len+1));
					visited[n.chk][nr][nc] = true;
				}
				
			}
		}
		
	}
}
