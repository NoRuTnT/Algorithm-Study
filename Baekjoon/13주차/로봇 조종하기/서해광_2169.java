// 2169. 로봇 조종하기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	public static int N, M;
	public static int[][] arr, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		dp = new int[N][M];
		for(int i=0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0 ; j<M ; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0] = arr[0][0];
		for(int j=1 ; j<M ; j++) {
			dp[0][j]=arr[0][j]+dp[0][j-1];
		}
		for(int i=1 ; i<N ; i++) {
			int[][]tmp = new int[2][M+2];
			tmp[0][0]=dp[i][0];
			for(int j=0 ; j<M ; j++) {
				if(j == 0) {
					tmp[0][j] = dp[i-1][j] + arr[i][j];
				} else {
					tmp[0][j] = Math.max(tmp[0][j-1], dp[i-1][j]) + arr[i][j];
				}
			}
			tmp[1][M-1]=dp[i-1][M-1];
			for(int j=M-1 ; j>=0 ; j--) {
				if(j == M-1) {
					tmp[1][j] = dp[i-1][j] + arr[i][j];
				} else {
					tmp[1][j] = Math.max(tmp[1][j+1], dp[i-1][j]) + arr[i][j];
				}
			}
			for(int j=0 ; j<M ; j++) {
				dp[i][j] = Math.max(tmp[0][j], tmp[1][j]);
			}
		}
		System.out.println(dp[N-1][M-1]);
	}
}
