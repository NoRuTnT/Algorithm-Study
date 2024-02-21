import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] memory;
    static int[] cost;
    static int[][] dp;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        memory = new int[N];
        cost = new int[N];
        dp = new int[N][10001];
        ans = 10000;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int m = memory[i];
            int c = cost[i];
            for (int j = 0; j <= 10000; j++) {
                if (i == 0) {
                    if (j >= c) dp[i][j] = m;
                } else {
                    if (j >= c) dp[i][j] = Math.max(dp[i - 1][j - c] + m, dp[i - 1][j]);
                    else dp[i][j] = dp[i - 1][j];
                }
                if (dp[i][j] >= M) ans = Math.min(ans, j);
            }
        }

        bw.write(ans + " ");
        bw.flush();
        bw.close();
    }

}
