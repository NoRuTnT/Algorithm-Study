import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static long min, max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		min = Long.parseLong(st.nextToken());
		max = Long.parseLong(st.nextToken());
		int len = (int) (max - min + 1);
		boolean[] flag = new boolean[len];

		/*
		 * 최솟값부터 시작하므로 min % (i * i) 를 통해 
		 * 나누어 떨어지면 min / (i * i)의 몫부터 시작해서 몫 * (i * i) 의 값이 max와 같거나 커지기 전까지 반복 
		 * 나누어 떨어지지 않으면 min / (i * i)의 몫 + 1부터 시작해서 몫 * (i * i) 의 값이 max와 같거나 커지기 전까지 반복 
		 * 을 통해 몫 * (i * i) - min 을 인덱스로 가지는 boolean 배열 원소를 true로 설정한다.
		 */
		for (long i = 2; i * i <= max; i++) {
			long pow = i * i;
			long tmp = min / pow;
			if (min % pow != 0) {
				tmp++;
			}
			for (long j = tmp; j * pow <= max; j++) {
				if (!flag[(int) (j * pow - min)]) {
					flag[(int) (j * pow - min)] = true;
					len--;
				}
			}
		}

		System.out.println(len);

	}

}
