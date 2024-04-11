import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 10007;
    static int[][] card = new int[53][53];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // i 개 중 j 개를 고르는 경우의 수
        for (int i = 0; i <= 52; i++) {
            card[i][0] = 1;
            card[i][i] = 1;
            for (int j = 1; j < i; j++) {
                card[i][j] = (card[i-1][j-1] + card[i-1][j]) % MOD;
                card[i][i-j] = card[i][j];
            }
        }

        int ans = 0;
        for (int i = 4; i <= n; i += 4) {
            if ((i / 4) % 2 == 1)
                ans = (ans + card[13][i / 4] * card[52 - i][n - i]) % MOD;
            else
                ans = (ans - card[13][i / 4] * card[52 - i][n - i]) % MOD;
        }

        if (ans < 0)
            ans += MOD;

        System.out.println(ans);
    }
}
