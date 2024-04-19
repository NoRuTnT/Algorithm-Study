import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static long[] tree;
    static int[] arr;
    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        tree = new long[N * 4];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        init(0, N - 1, 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            if (op == 1) {
                int idx = Integer.parseInt(st.nextToken()) - 1;
                int num = Integer.parseInt(st.nextToken());
                arr[idx] = num;
                update(0, N - 1, 1, idx, num);
            } else {
                int left = Integer.parseInt(st.nextToken()) - 1;
                int right = Integer.parseInt(st.nextToken()) - 1;
                sb.append(multiply(0, N - 1, 1, left, right)).append("\n");
            }
        }

        System.out.println(sb);
    }

    public static void init(int start, int end, int node) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        init(start, mid, node * 2);
        init(mid + 1, end, node * 2 + 1);
        tree[node] = tree[node * 2] * tree[node * 2 + 1] % MOD;
    }

    public static void update(int start, int end, int node, int idx, int num) {
        if (start > idx || idx > end) {
            return;
        }
        if (start == end) {
            tree[node] = num;
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx, num);
        update(mid + 1, end, node * 2 + 1, idx, num);

        tree[node] = tree[node * 2] * tree[node * 2 + 1] % MOD;
    }

    private static long multiply(int start, int end, int node, int left, int right) {
        if (start > right || end < left) {
            return 1;
        }
        if (start >= left && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;

        return multiply(start, mid, node * 2, left, right)
                * multiply(mid + 1, end, node * 2 + 1, left, right)
                % MOD;
    }

}
