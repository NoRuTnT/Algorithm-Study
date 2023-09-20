package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
class Node{
	int x;
	int y;
	Node(int x, int y){
		this.x=x;
		this.y=y;
	}
}
public class b14502 {
	static int n,m;
	static int[][] map;
	public static int maxSafe = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map= new int[n][m];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		dfs(0);
		System.out.println(maxSafe);		

	}
	//기둥3개두기
	public static void dfs(int depth) {
		if(depth==3) {
			bfs();
			return;
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]==0) {
					map[i][j]=1;
					dfs(depth+1);
					map[i][j]=0;
				}
			}
		}
	}
	//바이러스확산
	private static void bfs() {
		int[] dr = {0,1,0,-1};
		int[] dc = {1,0,-1,0};
		Queue<Node> q = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 2) {
					q.add(new Node(i, j));
				}
			}
		}
		int copyMap[][] = new int[n][m];
		for (int i = 0; i < n; i++) {
			copyMap[i] = map[i].clone();
		}
		while (!q.isEmpty()) {
			Node now = q.poll();
			int x = now.x; 
			int y = now.y;
			for (int k=0; k<4;k++) {
				int nx = x + dr[k];
				int ny = y + dc[k];
				if (0 <= nx && nx < n && 0 <= ny && ny < m) {
					if (copyMap[nx][ny] == 0) {
						q.add(new Node(nx, ny));
						copyMap[nx][ny] = 2;
					}
				}
			}
			
		}
		funcSafeZone(copyMap);
	}
			
		private static void funcSafeZone(int[][] copyMap) {
			int safeZone = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (copyMap[i][j] == 0) {
						safeZone++;
					}
				}
			}
			maxSafe = Math.max(maxSafe, safeZone);
		}

	}
