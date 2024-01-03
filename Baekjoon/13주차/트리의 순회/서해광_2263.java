// 2263. 트리의 순회
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int n;
	public static int[] in, post, index;
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		in = new int[n];
		post = new int[n];
		index = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0 ; i<n ; i++) {
			in[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0 ; i<n ; i++) {
			post[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0 ; i<n ; i++) {
			index[in[i]] = i;
		}
		dfs(0, n-1, 0, n-1);
		System.out.println(sb.toString());
	}
	public static void dfs(int leftStart, int leftend, int rightStart, int rightEnd) {
		if(leftStart > leftend || rightStart > rightEnd) return;
		int R = post[rightEnd];
		sb.append(R).append(" ");
		
		int RIdx = index[R];
		int length = RIdx - leftStart;
		dfs(leftStart, RIdx-1, rightStart, rightStart+length-1);
		dfs(RIdx+1, leftend, rightStart+length, rightEnd-1);
	}
}
