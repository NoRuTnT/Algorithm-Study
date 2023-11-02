// 규칙은 찾았지만, 그 규칙으로 값을 찾는 방법이 도저히 떠오르지 않아서 블로그 찾아봄..
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int length = end - start;
			
			int num = (int)Math.sqrt(length);
			
			if(num == Math.sqrt(length)) {
				sb.append(2 * num - 1).append("\n");
			} else if(length <= num * num + num) {
				sb.append(2 * num).append("\n");
			} else {
				sb.append(2 * num + 1).append("\n");
			}
		}
		System.out.println(sb);
	}
}
