import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N, M;
    public static int[][] W, dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        W = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        M = (1 << N) - 1;

        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M - 1; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 1));

    }

    public static int dfs(int now, int visit) {

        if (visit == M) {
            if (W[now][0] == 0) return 987654321;
            return W[now][0];
        }

        if (dp[now][visit] != -1) return dp[now][visit];
        dp[now][visit] = 987654321;

        for (int i = 0; i < N; i++) {
            if ((visit & (1 << i)) == 0 && W[now][i] != 0) {
                dp[now][visit] = Math.min(dfs(i, visit | (1 << i)  ) + W[now][i], dp[now][visit]);
            }
        }

        return dp[now][visit];

    }

}
