import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class pos {
		int r;
		int c;
		int move; // 움직인 횟수
		int wall; // 벽 부순 개수

		pos() {
		}

		pos(int r, int c, int move, int wall) {
			this.r = r;
			this.c = c;
			this.move = move;
			this.wall=wall;
		}
	}

	static int n;
	static int m;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		for(int i=0;i<n;i++) {
			map[i]=br.readLine().toCharArray();
		}
		br.close();
		boolean[][][] isVisited=new boolean[2][n][m];
		Queue<pos> queue=new LinkedList<>();
		int[] dr = { 1, 0, -1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		
		queue.offer(new pos(0,0,1,0));
		isVisited[0][0][0]=true;
		int ans=-1;
		while(ans<0 && !queue.isEmpty()) {
			pos curr=queue.poll();
			if(curr.r==n-1 && curr.c==m-1) {
				ans=curr.move;
				break;
			}
			for(int k=0;k<4;k++) {
				int nextr=curr.r+dr[k];
				int nextc=curr.c+dc[k];
				if(nextr<0 || nextr>=n || nextc<0 || nextc>=m) continue;
				//경계조건
				pos next=new pos(nextr, nextc, curr.move+1, curr.wall+map[nextr][nextc]-'0');
				if(next.wall>1) continue; // 벽 두번 x
				
				if(isVisited[next.wall][next.r][next.c]) continue;
				queue.offer(next);
				isVisited[next.wall][next.r][next.c]=true;
				
			}
		}
		
		System.out.println(ans);
	}
}
