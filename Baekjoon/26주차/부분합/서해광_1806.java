// 1806. 부분합
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, S, ans;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int s = 0;
        int e = 0;
        long curr = 0;
        ans = N + 1; 
        while (s < N) { 
            if (curr >= S) {
                ans = Math.min(ans, e - s); 
                curr -= arr[s++];
            } else if (e == N) break; 
            else curr += arr[e++];
        }
        System.out.println(ans == N + 1 ? 0 : ans);
    }
}
