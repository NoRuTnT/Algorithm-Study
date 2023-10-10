// 1520. 내리막 길
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int M, N;
	public static int[][] arr, dp;
	public static int[] dr = {-1, 1, 0, 0}; // 상하좌우
	public static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[M][N];
		dp = new int[M][N];
		for(int i=0 ; i<M ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0 ; j<N ; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		System.out.println(dfs(0, 0));
	}
	public static int dfs(int r, int c) {
		if(r==M-1 && c==N-1) return 1; // 끝에 도달
		if(dp[r][c]!=-1) return dp[r][c]; // 이미 계산된 경우
		// 이제 계산해야 되는 경우
		dp[r][c] = 0;
		for(int d=0 ; d<4 ; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr<0 || nr>=M || nc<0 || nc>=N)
				continue;
			if(arr[nr][nc]<arr[r][c])
				dp[r][c]+=dfs(nr, nc);
		}
		return dp[r][c];
	}
	
}
