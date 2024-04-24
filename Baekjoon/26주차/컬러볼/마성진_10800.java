import java.io.*;
import java.util.*;

public class Main {

    static class Ball {
        int idx;
        int color;

        public Ball(int idx, int color) {
            this.idx = idx;
            this.color = color;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int total = 0;
        int[] color = new int[200001];
        int[] result = new int[N];
        ArrayList<Ball>[] balls = new ArrayList[2001];

        for (int i = 1; i <= 2000; i++) {
            balls[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            balls[s].add(new Ball(i, c));
        }

        for (int i = 1; i <= 2000; i++) {
            for (Ball ball : balls[i]) {
                result[ball.idx] = total - color[ball.color];
            }
            for (Ball ball : balls[i]) {
                color[ball.color] += i;
            }
            total += balls[i].size() * i;
        }

        for (int i = 0; i < N; i++) {
            sb.append(result[i]).append("\n");
        }

        System.out.println(sb);
    }
}
