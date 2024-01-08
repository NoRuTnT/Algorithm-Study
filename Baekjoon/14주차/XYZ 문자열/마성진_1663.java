import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static long[] dp;
    public static long[][] cnt;
    public static String[] base;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        dp = new long[101];
        cnt = new long[101][3];
        base = new String[4];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 2;
        base[1] = "X";
        base[2] = "YZ";
        base[3] = "ZX";
        cnt[1][0] = 1;
        cnt[2][1] = cnt[2][2] = 1;
        cnt[3][0] = cnt[3][2] = 1;

        for (int i = 4; i <= N; i++) {
            dp[i] = dp[i - 3] + dp[i - 2];
            for (int j = 0; j < 3; j++) {
                cnt[i][j] = cnt[i - 3][j] + cnt[i - 2][j];
            }
        }

        if (T == 1) {
            System.out.println(dp[N]);
        } else if (T == 2) {
            long K = Long.parseLong(br.readLine());
            System.out.println(solve(K, N));
        } else {
            char K = br.readLine().charAt(0);
            System.out.println(cnt[N][K - 'X']);
        }


    }

    public static char solve(long idx, int depth) {
        if (depth <= 3) {
            return base[depth].charAt((int) (idx-1));
        }
        if (dp[depth - 3] >= idx) {
            return solve(idx, depth - 3);
        } else {
            return solve(idx - dp[depth - 3], depth - 2);
        }
    }

}
