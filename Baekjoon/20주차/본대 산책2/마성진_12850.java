import java.io.*;
import java.util.*;

public class Main {

    static long[][] map = {
            {0, 1, 0, 1, 0, 0, 0, 0},
            {1, 0, 1, 1, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 1, 1, 0},
            {0, 0, 1, 1, 1, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 1, 0}
    };
    static int D;
    static int MOD = 1000000007;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        D = Integer.parseInt(br.readLine());

        long[][] result = pow(D);

        bw.write(result[0][0] + " ");
        bw.flush();
        bw.close();
    }

    public static long[][] pow(int n) {
        if (n == 1) {
            return map;
        }

        long[][] tmp = pow(n / 2);
        tmp = multiply(tmp, tmp);

        if (n % 2 == 1) {
            tmp = multiply(tmp, map);
        }

        return tmp;
    }

    public static long[][] multiply(long[][] a, long[][] b) {
        long[][] tmp = new long[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    tmp[i][j] = (tmp[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }

        return tmp;
    }
    
}
