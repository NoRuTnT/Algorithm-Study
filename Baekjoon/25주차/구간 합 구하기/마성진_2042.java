import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static long[] tree, arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N];
        tree = new long[N * 4];

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        init(0, N - 1, 1);

        while (M != 0 || K != 0) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            
            if (op == 1) {
                int idx = Integer.parseInt(st.nextToken()) - 1;
                long num = Long.parseLong(st.nextToken());
                long dif = num - arr[idx];
                update(0, N - 1, 1, idx, dif);
                arr[idx] = num;
                M--;
            } else {
                int left = Integer.parseInt(st.nextToken()) - 1;
                int right = Integer.parseInt(st.nextToken()) - 1;

                long sum = pSum(0, N - 1, 1, left, right);
                sb.append(sum).append("\n");

                K--;
            }
        }

        System.out.println(sb);
    }

    static long init(int start, int end, int node) {
        if (start == end) return tree[node] = arr[start];
        int mid = (start + end) / 2;

        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    static void update(int start, int end, int node, int idx, long dif) {
        if (start <= idx && idx <= end) {
            tree[node] += dif;
        } else return;

        if (start == end) return;

        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx, dif);
        update(mid + 1, end, node * 2 + 1, idx, dif);
    }

    static long pSum(int start, int end, int node, int l, int r) {
        if (r < start || l > end) return 0;
        if (l <= start && end <= r) return tree[node];

        int mid = (start + end) / 2;
        return pSum(start, mid, node * 2, l, r) + pSum(mid + 1, end, node * 2 + 1, l, r);
    }

}
