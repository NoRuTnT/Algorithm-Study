import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        long[] time = new long[N];
        long max = 0;
        for (int i = 0; i < N; i++) {
            time[i] = Long.parseLong(br.readLine());
            max = Math.max(time[i], max);
        }
        Arrays.sort(time);
        long left = 0L;
        long right = max * M;
        long result = Long.MAX_VALUE;
        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0;
            for (int i = 0; i < N; i++) {
                long cnt = mid / time[i];

                if (sum >= M) break;
                sum += cnt;
            }

            if (sum < M) {
                left = mid + 1;
            } else {
                right = mid - 1;
                result = Math.min(result, mid);
            }

        }
        System.out.println(result);
    }
}
