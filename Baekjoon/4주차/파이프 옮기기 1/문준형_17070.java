package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b17070 {
	static int n,cnt;
	static int[][]map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}			//입력
		cnt=0;
		int direction = 0; //파이프의현재보고있는방향설정
		dfs(0,1,direction);
		System.out.println(cnt);
	}

	private static void dfs(int i, int j, int direction) {
		if(i==n-1 && j==n-1) { //목표지점에도착하면 cnt증가시키고 리턴
			cnt++;
			return;
		}
		
		// 0 오른쪽 1 대각선 2 아래 모든상황별로 하나씩다만듬
		if(direction==0) {
			if(j+1<n && map[i][j+1]==0) {
				dfs(i,j+1,0);
			}
			if(i+1<n && j+1<n && map[i+1][j]==0 && map[i][j+1]==0 && map[i+1][j+1]==0) {
				dfs(i+1,j+1,1);
			}
		}else if(direction==1) {
			if(j+1<n && map[i][j+1]==0) {
				dfs(i,j+1,0);
			}
			if(i+1<n && map[i+1][j]==0) {
				dfs(i+1,j,2);
			}
			if(i+1<n && j+1<n && map[i+1][j]==0 && map[i][j+1]==0 && map[i+1][j+1]==0) {
				dfs(i+1,j+1,1);
			}
		}else if(direction==2) {
			if(i+1<n && map[i+1][j]==0) {
				dfs(i+1,j,2);
			}
			if(i+1<n && j+1<n && map[i+1][j]==0 && map[i][j+1]==0 && map[i+1][j+1]==0) {
				dfs(i+1,j+1,1);
			}
		}
		
	}

}
