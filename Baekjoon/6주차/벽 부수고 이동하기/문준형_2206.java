package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class b2206 {
	
	static int[] dr= {1,0,-1,0};
	static int[] dc= {0,1,0,-1};
	static int n,m;
	static int[][] map;
	static boolean[][][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visit = new boolean[n][m][2]; //3차원배열 벽꺴을때,안꺴을때 따로저장
		for(int i=0;i<n;i++) {
			String[] str = br.readLine().split("");
			for(int j=0;j<m;j++) {
				int a = Integer.parseInt(str[j]);				
				map[i][j]=a;
			}
		}
		bfs(0,0);
		
	}

	private static void bfs(int i, int j) {
		Queue<int[]>queue = new LinkedList<>();
		queue.offer(new int[] {i,j,1,0}); // {r,c,cnt,wall}들고다님
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0];
			int y = now[1];
			int cnt = now[2];
			int wall = now[3];
			if(x==n-1 && y==m-1) { //도착하면 cnt리턴
				System.out.println(cnt);
				return;
			}
			
			for(int d=0;d<4;d++) {
				int r = x+dr[d];
				int c = y+dc[d];
				if(r>=0 && r<n && c>=0 && c<m) {
					if(map[r][c]==0) { //벽이아닐때
						if(wall==0 && !visit[r][c][0]) { //벽을안부쉈을때 중복방문체크
							queue.offer(new int[] {r,c,cnt+1,wall});
							visit[r][c][0]=true;
						}else if(wall==1 && !visit[r][c][1]) { //벽을부쉈을때 중복방문체크
							queue.offer(new int[] {r,c,cnt+1,wall});
							visit[r][c][1]=true;
						}						
					}else if(map[r][c]==1){ //벽일때
						if(wall==0) { //한번도안부셨으면 부순거 큐에추가
							queue.offer(new int[] {r,c,cnt+1,wall+1});
							visit[r][c][1]=true;
						}
					}
				}
			}
		}
		System.out.println(-1);
		
	}

}
