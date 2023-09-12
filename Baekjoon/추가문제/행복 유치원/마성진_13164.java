import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int result = 0;
		int[] arr = new int[N];
		List<Integer> list = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		arr[0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			list.add(arr[i] - arr[i - 1]);
		}

		Collections.sort(list);

		for (int i = 0; i < N - K; i++) {
			result += list.get(i);
		}

		System.out.println(result);
		
	}
}
