import java.io.*;
import java.util.*;

public class Main {

    static int[] arr, tree;
    static int N;
    static final int MAX = 2_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        tree = new int[MAX * 4];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if(T == 1)
                insert(1, MAX, 1, num);
            else
                sb.append(query(1, MAX, 1, num)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void insert(int left, int right, int node, int idx) {
        if (idx < left || right < idx) return;
        tree[node] += 1;
        if (left == right) return;
        int mid = (left + right) / 2;
        insert(left, mid, node * 2, idx);
        insert(mid + 1, right, node * 2 + 1, idx);
    }

    static int query(int left, int right, int node, int s) {
        tree[node] -= 1;
        if(left == right) return left;
        int mid = (left + right) / 2;
        if(tree[node * 2] < s)
            return query(mid+1, right, node * 2 + 1, s - tree[node * 2]);
        else
            return query(left, mid, node * 2, s);
    }

}
