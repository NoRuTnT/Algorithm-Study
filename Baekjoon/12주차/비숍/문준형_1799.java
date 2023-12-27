package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1799 {
	static int[][] map;
	static int n,blackmax,whitemax;
	static int[] dr = {1,1,-1,-1};
	static int[] dc = {1,-1,1,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		boolean[][] visit = new boolean[n][n];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		blackmax=0;
		whitemax=0;
		blackboard(visit,0,0,0);
		whiteboard(visit,1,0,0);
		System.out.println(blackmax+whitemax);
	}
	// 아이디어 구글링.. 어짜피 대각이 안겹쳐서 2개로 나누어서하는게 시간이더적게듬
	private static void blackboard(boolean[][]visit,int x, int y, int cnt) {
		blackmax = Math.max(blackmax, cnt);
		if(x>=n) {
			y+=1;
			if(y%2==0) {
				x=0;
			}else {
				x=1;
			}
		}
		if(y>=n) {
			return;
		}
		if(check(visit,x,y)) {
			visit[x][y]=true;
			blackboard(visit,x+2,y,cnt+1);
			visit[x][y]=false;
		}
		blackboard(visit,x+2,y,cnt);
		
	}
	
	private static void whiteboard(boolean[][] visit, int x, int y, int cnt) {
		whitemax = Math.max(whitemax, cnt);
		if(x>=n) {
			y+=1;
			if(y%2==0) {
				x=1;
			}else {
				x=0;
			}
		}
		if(y>=n) {
			return;
		}
		if(check(visit,x,y)) {
			visit[x][y]=true;
			whiteboard(visit,x+2,y,cnt+1);
			visit[x][y]=false;
		}
		whiteboard(visit,x+2,y,cnt);
		
		
	}
	
	private static boolean check(boolean[][] visit, int x, int y) {
		if(map[x][y]==0) {
			return false;
		}
		for(int d=0;d<4;d++) {
			int r = x+dr[d];
			int c = y+dc[d];
			while(true) {
				if(r>=n||r<0 ||c >=n||c<0) {
					break;
				}
				if(visit[r][c]) {
					return false;
				}
				r+=dr[d];
				c+=dc[d];
			}
		}
		
		return true;
		
	}

}
