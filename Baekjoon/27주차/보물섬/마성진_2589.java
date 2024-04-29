import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int x, y, len;

        public Node(int x, int y, int len) {
            this.x = x;
            this.y = y;
            this.len = len;
        }
    }

    static char[][] map;
    static boolean[][] visited;
    static int N, M, answer;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        answer = 0;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') {
                    visited = new boolean[N][M];
                    bfs(new Node(i, j, 0));
                }
            }
        }
        System.out.println(answer);
    }

    static void bfs(Node p) {
        Queue<Node> q = new LinkedList<>();
        q.add(p);
        visited[p.x][p.y] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = node.x + dx[k];
                int ny = node.y + dy[k];

                if (validationCheck(nx, ny) && !visited[nx][ny] && map[nx][ny] == 'L') {
                    visited[nx][ny] = true;
                    q.add(new Node(nx, ny, node.len + 1));
                    answer = Math.max(answer, node.len + 1);
                }
            }
        }
    }

    static boolean validationCheck(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
