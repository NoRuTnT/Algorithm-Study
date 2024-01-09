import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N, H;
    public static char[] map;
    public static char[] type = {'S', 'R', 'W'};

    public static int result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        String str = br.readLine();
        map = new char[N];

        for (int i = 0; i < N; i++) {
            map[i] = str.charAt(i);
        }

        result = 0;

        solve();

        System.out.println(result);

    }

    public static void solve() {
        if (N > 3 && H > 3) {
            result = -1;
            return;
        }
        if (N == 1 || H == 1) {
            return;
        }
        if (H == 2) {
            for (int i = 1; i < N; i++) {
                if (map[i - 1] == map[i]) {
                    result++;
                    i++;
                }
            }
            return;
        }
        if (N == 2) {
            if (map[0] == map[1]) {
                result = 1;
            }
            return;
        }
        if (N == 3) {
            if (map[0] == map[1] && map[0] == map[2]) {
                result = 2;
            } else if (map[0] == map[1] || map[1] == map[2] || map[2] == map[0]) {
                result = 1;
            }
            return;
        }
        result = 1000000;
        int tmp = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) continue;
                for (int k = 0; k < 3; k++) {
                    if (i == k || j == k) continue;
                    tmp = 0;
                    for (int l = 0; l < N; l++) {
                        if (l % 3 == 0) {
                            if (map[l] != type[i]) {
                                tmp++;
                            }
                        } else if (l % 3 == 1) {
                            if (map[l] != type[j]) {
                                tmp++;
                            }
                        } else {
                            if (map[l] != type[k]) {
                                tmp++;
                            }
                        }
                        if (result < tmp) {
                            break;
                        }
                    }
                    result = Math.min(result, tmp);
                }
            }
        }
    }

}
