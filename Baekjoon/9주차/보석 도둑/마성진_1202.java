import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static class jewel {
		int m;
		int v;

		public jewel(int m, int v) {
			this.m = m;
			this.v = v;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		jewel[] jewels = new jewel[N];
		int[] bags = new int[K];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			jewels[i] = new jewel(m, v);
		}

		Arrays.sort(jewels, new Comparator<jewel>() {

			@Override
			public int compare(jewel o1, jewel o2) {
				if (o1.m == o2.m) {
					return o2.v - o1.v;
				}
				return o1.m - o2.m;
			}

		});

		for (int i = 0; i < K; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bags);

		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		long result = 0;

		for (int i = 0, j = 0; i < K; i++) {
			while (j < N && jewels[j].m <= bags[i]) {
				pq.offer(jewels[j++].v);
			}
			if (!pq.isEmpty()) {
				result += pq.poll();
			}
		}

		System.out.println(result);

	}

}
