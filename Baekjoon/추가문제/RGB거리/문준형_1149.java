import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
//		int[] house = new int[n+1];
		int[][] cost = new int[3][n];
		for(int i=0;i<n;i++) { 				//0:빨 1:초 2:파
			st = new StringTokenizer(br.readLine());
			cost[0][i] = Integer.parseInt(st.nextToken()); 
			cost[1][i] = Integer.parseInt(st.nextToken()); 
			cost[2][i] = Integer.parseInt(st.nextToken()); 
		}
		int[][]dp = new int[3][n];
		dp[0][0]=cost[0][0];
		dp[1][0]=cost[1][0];
		dp[2][0]=cost[2][0];
		for(int i=1;i<n;i++) {
			dp[0][i] = Math.min(dp[1][i-1],dp[2][i-1])+cost[0][i];
			dp[1][i] = Math.min(dp[0][i-1],dp[2][i-1])+cost[1][i];
			dp[2][i] = Math.min(dp[0][i-1],dp[1][i-1])+cost[2][i];
			}
		int ans = Math.min(Math.min(dp[0][n-1], dp[1][n-1]),dp[2][n-1]);
		System.out.println(ans);
		
		
	}

}
