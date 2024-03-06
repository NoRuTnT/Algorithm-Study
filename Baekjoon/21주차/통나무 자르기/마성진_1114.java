import java.util.*;
import java.io.*;

public class Main {

    static int L, K, C;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[K + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        arr[K] = L;
        Arrays.sort(arr);
        System.out.println(binarySearch());

    }

    static String binarySearch() {
        int min = 0, max = L, mid;
        String answer = "";
        int result;
        for (int i = 0; i < 30; i++) {
            mid = (min + max) / 2;
            result = woodSlice(mid);
            if(result != -1) {
                answer = mid + " " + result;
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return answer;
    }

    static int woodSlice(int l) {
        int left = K, right = K, cnt = 0;
        while(left != 0 && cnt < C) {
            left--;
            if(arr[right] - arr[left] > l) {
                if(right - left == 1) return -1;
                cnt++;
                left = right = left + 1;
            }
        }
        if(arr[right] - arr[left] > l || arr[left] > l) return -1;
        return arr[left];
    }

}
