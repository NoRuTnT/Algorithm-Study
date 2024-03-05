import java.util.*;
import java.io.*;

public class Main {

    static char[][] map;
    static int[][][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int W, H, startX, startY, endX, endY, result;

    static class Node implements Comparable<Node> {
        int x, y, dir, cost;

        public Node(int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        visited = new int[H][W][4];
        result = Integer.MAX_VALUE;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                for (int k = 0; k < 4; k++) {
                    visited[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        startX = -1;
        startY = -1;

        for (int i = 0; i < H; i++) {
            String str = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'C') {
                    if (startX == -1) {
                        startX = i;
                        startY = j;
                    } else {
                        endX = i;
                        endY = j;
                    }

                }
            }
        }

        bfs();

        System.out.println(result);

    }

    static void bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startX, startY, -5, -1));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.x == endX && now.y == endY) {
                result = Math.min(result, now.cost);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < H && ny < W && map[nx][ny] != '*') {
                    if (Math.abs(i - now.dir) == 2) continue;
                    int cost = (now.dir == i) ? now.cost : now.cost + 1;
                    if (visited[nx][ny][i] > cost) {
                        visited[nx][ny][i] = cost;
                        pq.add(new Node(nx, ny, i, cost));
                    }
                }
            }

        }
    }
}
