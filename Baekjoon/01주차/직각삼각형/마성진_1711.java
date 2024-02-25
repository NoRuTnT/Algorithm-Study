import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] coor = new int[n][2];

        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
            coor[i][0] = Integer.parseInt(st.nextToken());
            coor[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(coor, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });

        int ans = 0;
        for (int i = 0; i < n; i++) {
            Map<List<Integer>, Integer> m = new HashMap<>();

            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int x = coor[i][0] - coor[j][0];
                int y = coor[i][1] - coor[j][1];
                int g = gcd(x, y);
                if (g < 0)
                    g = -g;
                x /= g;
                y /= g;
                List<Integer> key = Arrays.asList(x, y);
                m.put(key, m.getOrDefault(key, 0) + 1);
            }

            for (Map.Entry<List<Integer>, Integer> entry : m.entrySet()) {
                List<Integer> key = entry.getKey();
                int nowX = key.get(0).intValue();
                int nowY = key.get(1).intValue();
                if (m.containsKey(Arrays.asList(-nowY, nowX))) {
                    ans += entry.getValue() * m.get(Arrays.asList(-nowY, nowX));
                }
            }
        }

        System.out.println(ans);
    }
}
