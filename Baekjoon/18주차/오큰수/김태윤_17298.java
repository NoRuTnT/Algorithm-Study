import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] nums = new int[n];
		int[] stk = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int cnt = 0;
		while (st.hasMoreElements()) {
			nums[cnt++] = Integer.parseInt(st.nextToken());
		}
		cnt = -1;
		for (int i = 0; i < n; i++) {
			if (cnt >= 0 && nums[stk[cnt]] < nums[i]) {
				while (cnt >= 0 && nums[stk[cnt]] < nums[i]) {
					nums[stk[cnt]] = nums[i];
					stk[cnt--] = 0;
				}
			}
			stk[++cnt] = i;
		}
		for (int i = 0; i <= cnt; i++) {
			nums[stk[i]] = -1;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(nums[i]).append(" ");
		}
		System.out.println(sb);
	}
