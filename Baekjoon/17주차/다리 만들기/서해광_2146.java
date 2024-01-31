// 2146. 다리 만들기
// BFS에서 방문 체크를 어디서 하는지에 따라
// 큐에서 메모리 초과가 발생할 수 있다.
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N, index, ans;
	public static int[][] board;
	public static boolean[][] visited;
	public static int[] dr = {0, 1, 0, -1};
	public static int[] dc = {1, 0, -1, 0};	
	static class Node{
		int r;
		int c;
		int t;
		public Node(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;
		board = new int[N][N];
		visited = new boolean[N][N];
		index = 1;
		for(int i=0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0 ; j<N ; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<N ; j++) {
				if(board[i][j]==1) {
					++index;
					dfs(i, j);
				}
			}
		}
		// index번호까지
//		System.out.println(index);
//		for(int i=0 ; i<N ; i++) {
//			for(int j=0 ; j<N ; j++) {
//				System.out.print(board[i][j]+" ");
//			}
//			System.out.println();
//		}
		for(int i=2 ; i<=index ; i++) {
			bfs(i);
		}
		
		System.out.println(ans-1);
	} // main
	public static boolean sideChk(int r, int c) {
		if(board[r][c]==0) return false; // 현재 물 위인 영역은 당연히 아님
		for(int d=0 ; d<4 ; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
			if(board[nr][nc]==0) return true;
		}
		return false;
	}
	
	public static void dfs(int r, int c) {
		board[r][c] = index;
		for(int d=0 ; d<4 ; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N || board[nr][nc]==0) continue;
			if(board[nr][nc]!=index)
				dfs(nr, nc);
		}
	}
	
	public static void bfs(int num) {
		Queue<Node> q = new LinkedList<>();
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<N ; j++) {
				if(board[i][j]==num) {
//					visited[i][j]=true;
					if(sideChk(i, j)) q.add(new Node(i, j, 1));
				}else visited[i][j]=false;
			}
		}
		while(!q.isEmpty()) {
			Node node = q.remove();
			if(visited[node.r][node.c]) continue; // 메모리 초과를 해결한 구문
			visited[node.r][node.c]=true;
			for(int d=0 ; d<4 ; d++) {
				int nr = node.r+dr[d];
				int nc = node.c+dc[d];
				if(nr<0 || nr>=N || nc<0 || nc>=N || visited[nr][nc])
					continue;
				if(board[nr][nc]!=num && board[nr][nc]!=0) {
					ans = Math.min(ans, node.t);
					return;
				}
				q.add(new Node(nr, nc, node.t+1));
			}
		}
	} // bfs
}
