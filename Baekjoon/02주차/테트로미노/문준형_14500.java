package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class b14500 {
	//우하좌상
	static int[] dr = {0,1,0,-1}; 
	static int[] dc = {1,0,-1,0}; 
	static int n,m,max;
	static boolean[][] visit;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		max=0;
		visit = new boolean[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				visit[i][j]=true;
				dfs(i,j,1,map[i][j]);
				visit[i][j]=false;
				except(i, j);
			}
		}
		System.out.println(max);
	}
	//깊이4인 dfs탐색
	public static void dfs(int i, int j, int depth, int sum) {
		if(depth==4) {
			max = Math.max(sum, max);			
			return;
		}
			
		for(int k=0;k<4;k++) {
			int x = i+dr[k];
			int y = j+dc[k];
			if(x>=0 && x<n && y>=0 && y<m) {
				if(!visit[x][y]) {					
					visit[x][y]=true;
					dfs(x,y,depth+1,sum+map[x][y]);					
					visit[x][y]=false;
				}
			}
			
			
		}		
	}
	//dfs로 찾지못하는 모양 따로제작
	public static void except(int i, int j) {
        // ㅜ
        if (i >= 0 && i < n - 1 && j > 0 && j < m-1) {
            int sum = map[i][j] + map[i+1][j] + map[i][j+1] + map[i][j-1];
            max = Math.max(max, sum);
        }
        // ㅗ
        if (i > 0 && i < n  && j > 0 && j < m-1) {
            int sum = map[i][j] + map[i-1][j] + map[i][j-1] + map[i][j+1];
            max = Math.max(max, sum);
        }
        // ㅏ
        if (i > 0 && i < n - 1 && j >= 0 && j < m - 1) {
            int sum = map[i+1][j] + map[i-1][j] + map[i][j] + map[i][j+1];
            max = Math.max(max, sum);
        }
        // ㅓ
        if (i > 0 && i < n - 1 && j > 0 && j < m ) {
            int sum = map[i][j] + map[i+1][j] + map[i-1][j] + map[i][j-1];
            max = Math.max(max, sum);
        }
    }

}
