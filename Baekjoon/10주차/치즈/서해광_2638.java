// 2638. 치즈

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, ans;
	public static int[][] map;
	public static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	static class Node{
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	public static List<Node> cheese;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0 ; j<M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bfs(0, 0); // 초기화
		cheese = new ArrayList<>();
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<M ; j++) {
				if(map[i][j]==1) cheese.add(new Node(i, j));
			}
		}
		while(true) {
			if(cheese.size()==0) break;
			ans++;
			List<Node> toZero = new ArrayList<>();
			List<Integer> removeIdx = new ArrayList<>();
			for(int i=0 ; i<cheese.size() ; i++) {
				if(melting(cheese.get(i))) {
					toZero.add(cheese.get(i));
				}
			}
			for(int i=0 ; i<toZero.size() ; i++) {
				Node nd = toZero.get(i);
				if(cheese.contains(nd)) {
					map[nd.r][nd.c] = 0;
					cheese.remove(nd);
				}
			}
			for(int i=0 ; i<removeIdx.size() ; i++) {
				cheese.remove(removeIdx.get(i));
			}
			bfs(0, 0);
			
		}
		System.out.println(ans);
	}
	public static void bfs(int r, int c) {
		boolean[][] visited = new boolean[N][M];
		map[r][c] = -1;
		visited[r][c] = true;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(r, c));
		while(!q.isEmpty()) {
			Node n = q.remove();
			for(int d=0 ; d<4 ; d++) {
				int nr = n.r+dr[d];
				int nc = n.c+dc[d];
				if(nr<0 || nr>=N || nc<0 || nc>=M || map[nr][nc]==1)
					continue;
				if(visited[nr][nc])
					continue;
				map[nr][nc] = -1;
				visited[nr][nc] = true;
				q.add(new Node(nr, nc));
			}
		}
	}
	public static boolean melting(Node node) {
		int idx=0;
		int nr=0, nc=0;
		for(int d=0 ; d<4 ; d++) {
			nr = node.r + dr[d];
			nc = node.c + dc[d];
			if(map[nr][nc]==-1) idx++;
		}
		if(idx>=2) {
			return true;
		}
		return false;
	}
	
}
