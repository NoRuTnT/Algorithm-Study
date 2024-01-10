// 2098. 외판원 순회
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static final int INF = Integer.MAX_VALUE;
	public static int[][] W, dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		W = new int[N][N];
		dp = new int[N][(1<<N)-1];
		for(int i=0 ; i<N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0 ; j<N ; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0 ; i<N ; i++) {
			Arrays.fill(dp[i], INF);
		}
		System.out.println(dfs(0, 1));
	} // main
	public static int dfs(int tmpNode, int visited) {
		if(visited==(1<<N)-1) {
			if(W[tmpNode][0]==0)
				return INF;
			return W[tmpNode][0];
		}
		if(dp[tmpNode][visited]!=INF) {
			return dp[tmpNode][visited];
		}
		for(int i=0 ; i<N ; i++) {
			if((visited&(1<<i))==0&&W[tmpNode][i]!=0) {
				dp[tmpNode][visited] = Math.min(dp[tmpNode][visited], dfs(i, visited|(1<<i))+W[tmpNode][i]);
			}
		}
		return dp[tmpNode][visited];
	}
}
