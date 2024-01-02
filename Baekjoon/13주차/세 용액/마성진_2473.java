import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static int N;
	public static long[] arr;
	public static int result[];
	public static int start, mid, end;
	public static long min = Long.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new long[N];
		result = new int[3];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		loop:for (int i = 0; i < N - 2; i++) {
			start = i;
			mid = i + 1;
			end = N - 1;
			while (mid < end) {
				long sum = arr[start] + arr[mid] + arr[end];
				if (min > Math.abs(sum)) {
					min = Math.abs(sum);
					result[0] = start;
					result[1] = mid;
					result[2] = end;
					if (min == 0) {
						break loop;
					}
				}
				if (sum > 0) {
					end--;
				} else {
					mid++;
				}
			}
		}

		System.out.println(arr[result[0]] + " " + arr[result[1]] + " " + arr[result[2]]);

	}

}
