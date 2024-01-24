// 2357. 최솟값과 최댓값
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int N, M, h, segLength;
    public static int[] arr, minSeg, maxSeg;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        h = (int) (Math.ceil(Math.log(N) / Math.log(2)));
        arr = new int[N];
        segLength = (int) Math.pow(2, h + 1) - 1; 
        minSeg = new int[segLength];
        maxSeg = new int[segLength];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        initMaxSeg(1, 0, N - 1);
        initMinSeg(1, 0, N - 1);

        for (int j = 0; j < M; j++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(minFind(1, 0, N - 1, a - 1, b - 1)).append(" ") 
                    .append(maxFind(1, 0, N - 1, a - 1, b - 1)).append("\n");
        }
        System.out.println(sb);
    } // main

    public static int initMaxSeg(int node, int s, int e) {
        if (s == e) {
            return maxSeg[node] = arr[s];
        }
        int mid = (s + e) / 2;
        return maxSeg[node] = Math.max(initMaxSeg(node * 2, s, mid), initMaxSeg(node * 2 + 1, mid + 1, e));
    }

    public static int initMinSeg(int node, int s, int e) {
        if (s == e) {
            return minSeg[node] = arr[s];
        }
        int mid = (s + e) / 2;
        return minSeg[node] = Math.min(initMinSeg(node * 2, s, mid), initMinSeg(node * 2 + 1, mid + 1, e));
    }

    public static int maxFind(int node, int s, int e, int a, int b) {
        if (b < s || e < a) return Integer.MIN_VALUE;
        if (a <= s && e <= b) return maxSeg[node];
        int mid = (s + e) / 2;
        return Math.max(maxFind(node * 2, s, mid, a, b), maxFind(node * 2 + 1, mid + 1, e, a, b));
    }

    public static int minFind(int node, int s, int e, int a, int b) {
        if (b < s || e < a) return Integer.MAX_VALUE;
        if (a <= s && e <= b) return minSeg[node];
        int mid = (s + e) / 2;
        return Math.min(minFind(node * 2, s, mid, a, b), minFind(node * 2 + 1, mid + 1, e, a, b));
    }
}
