import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static int N;
	public static List<Integer> numList;
	public static boolean[] flag;
	public static int result;

	public static void main(String[] args) throws IOException {
		input();
		eratos();
		int size = numList.size();
		for (int i = 0; i < size; i++) {
			int sum = 0;
			for (int j = i; j < size; j++) {
				sum += numList.get(j);
				if (sum >= N) {
					break;
				}
			}
			if (sum == N) {
				result++;
			}
		}
		System.out.println(result);
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numList = new ArrayList<>();
		flag = new boolean[N + 1];
		result = 0;
	}

	public static void eratos() {
		flag[0] = flag[1] = true;
		for (int i = 2; i * i <= N; i++) {
			if (!flag[i]) {
				for (int j = i * i; j <= N; j += i) {
					flag[j] = true;
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			if (!flag[i]) {
				numList.add(i);
			}
		}
	}

}
