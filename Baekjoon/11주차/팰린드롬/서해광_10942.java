// 10942. 팰린드롬?
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1 ; i<=N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		M = Integer.parseInt(br.readLine());
		for(int i=0 ; i<M ; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if(x==y) {
				sb.append(1).append("\n");
				continue;
			}
			boolean flag = true;
			for(int j=0 ; j<(y-x+1)/2 ; j++) {
				if(arr[x+j]!=arr[y-j]) {
					flag = false;
					break;
				}
			}
			if(flag) sb.append(1);
			else sb.append(0);
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}
