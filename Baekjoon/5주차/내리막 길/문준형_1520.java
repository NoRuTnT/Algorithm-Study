package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1520 {
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static int[][] map,dp;
	static int m,n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[m][n];
		dp = new int[m][n]; 
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				dp[i][j]=-1; //-1로 초기화
			}
		}//입력
				
		System.out.println(dfs(0,0));
	}

	private static int dfs(int i, int j) {		
		if(i==m-1 && j==n-1) { //도착하면 +1
			return 1;
		}
		if(dp[i][j]!=-1) { //이미 도착한적이있는곳이면 저장된값 리턴
			return dp[i][j];
		}else {
			dp[i][j]=0;		//아니면 0
		}		
		int x =i;
		int y=j;
		for(int d=0;d<4;d++) {
			int r = x+dr[d];
			int c = y+dc[d];
			if(r>=0 && r<m && c>=0 && c<n) {
				if(map[x][y]>map[r][c]) {	//내리막길이라 진행가능하면					
					dp[x][y] += dfs(r,c); 	//이동전 위치 dp에 더해줌			
				}					
			}			
		}
		return dp[i][j];
	}

}
