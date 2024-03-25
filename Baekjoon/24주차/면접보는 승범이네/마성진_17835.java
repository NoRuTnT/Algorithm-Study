import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node> {
        int v;
        long w;

        public Node(int v, long w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(w, o.w);
        }
    }

    static int N, M, K;
    static ArrayList<Node>[] map;
    static Queue<Integer> que = new LinkedList<>();
    static long[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            map[i] = new ArrayList<>();
        }

        dist = new long[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[v].add(new Node(u, c));
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) {
            que.offer(Integer.parseInt(st.nextToken()));
        }

        dijikstra();

        long maxDist = 0;
        int maxNode = 0;

        for (int i = N; i > 0; i--) {
            if (maxDist <= dist[i]) {
                maxNode = i;
                maxDist = dist[i];
            }
        }

        System.out.println(maxNode);
        System.out.println(maxDist);

    }

    private static void dijikstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(dist, -1);

        for (int start : que) {
            dist[start] = 0;
            pq.offer(new Node(start, 0));
        }

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (dist[node.v] == -1 || dist[node.v] < node.w) continue;
            for (Node next : map[node.v]) {
                if (dist[next.v] == -1 || dist[next.v] > dist[node.v] + next.w) {
                    dist[next.v] = dist[node.v] + next.w;
                    pq.offer(new Node(next.v, dist[next.v]));
                }
            }
        }

    }
}
